package com.yongchun.library.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import com.yongchun.library.R;
import com.yongchun.library.adapter.ImageFolderAdapter;
import com.yongchun.library.model.LocalMediaFolder;
import com.yongchun.library.utils.ScreenUtils;
import java.lang.reflect.Method;
import java.util.List;

public class FolderWindow extends PopupWindow {
    private ImageFolderAdapter adapter;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public boolean isDismiss = false;
    private RecyclerView recyclerView;
    private View window;

    public void registerListener() {
    }

    public FolderWindow(Context context2) {
        this.context = context2;
        this.window = LayoutInflater.from(context2).inflate(R.layout.window_folder, (ViewGroup) null);
        setContentView(this.window);
        setWidth(ScreenUtils.getScreenWidth(context2));
        setHeight(ScreenUtils.getScreenHeight(context2) - ScreenUtils.dip2px(context2, 69.0f));
        setAnimationStyle(R.style.WindowStyle);
        setFocusable(true);
        setOutsideTouchable(true);
        update();
        setBackgroundDrawable(new ColorDrawable(Color.argb(153, 0, 0, 0)));
        initView();
        registerListener();
        setPopupWindowTouchModal(this, false);
    }

    public void initView() {
        this.adapter = new ImageFolderAdapter(this.context);
        this.recyclerView = this.window.findViewById(R.id.folder_list);
        this.recyclerView.addItemDecoration(new ItemDivider());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.recyclerView.setAdapter(this.adapter);
    }

    public void bindFolder(List<LocalMediaFolder> list) {
        this.adapter.bindFolder(list);
    }

    public void showAsDropDown(View view) {
        super.showAsDropDown(view);
        this.recyclerView.startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.up_in));
    }

    public void setOnItemClickListener(ImageFolderAdapter.OnItemClickListener onItemClickListener) {
        this.adapter.setOnItemClickListener(onItemClickListener);
    }

    public void dismiss() {
        if (!this.isDismiss) {
            this.isDismiss = true;
            Animation loadAnimation = AnimationUtils.loadAnimation(this.context, R.anim.down_out);
            this.recyclerView.startAnimation(loadAnimation);
            dismiss();
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    boolean unused = FolderWindow.this.isDismiss = false;
                    FolderWindow.super.dismiss();
                }
            });
        }
    }

    public static void setPopupWindowTouchModal(PopupWindow popupWindow, boolean z) {
        if (popupWindow != null) {
            Class<PopupWindow> cls = PopupWindow.class;
            try {
                Method declaredMethod = cls.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(popupWindow, new Object[]{Boolean.valueOf(z)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class ItemDivider extends RecyclerView.ItemDecoration {
        private Drawable mDrawable;

        public ItemDivider() {
            this.mDrawable = FolderWindow.this.context.getResources().getDrawable(R.drawable.item_divider);
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
            int dip2px = ScreenUtils.dip2px(recyclerView.getContext(), 16.0f);
            int width = recyclerView.getWidth() - dip2px;
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View childAt = recyclerView.getChildAt(i);
                int bottom = childAt.getBottom() + childAt.getLayoutParams().bottomMargin;
                this.mDrawable.setBounds(dip2px, bottom, width, this.mDrawable.getIntrinsicHeight() + bottom);
                this.mDrawable.draw(canvas);
            }
        }

        public void getItemOffsets(Rect rect, int i, RecyclerView recyclerView) {
            rect.set(0, 0, 0, this.mDrawable.getIntrinsicWidth());
        }
    }
}
