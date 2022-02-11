package com.luck.picture.lib.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.luck.picture.lib.R;
import com.luck.picture.lib.adapter.PictureAlbumDirectoryAdapter;
import com.luck.picture.lib.decoration.RecycleViewDivider;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.tools.AttrsUtils;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.StringUtils;
import com.yalantis.ucrop.view.CropImageView;
import java.util.List;

public class FolderPopWindow extends PopupWindow implements View.OnClickListener {
    private PictureAlbumDirectoryAdapter adapter;
    private Animation animationIn;
    private Animation animationOut;
    private Context context;
    private Drawable drawableDown;
    private Drawable drawableUp;
    private LinearLayout id_ll_root;
    /* access modifiers changed from: private */
    public boolean isDismiss = false;
    private int mimeType;
    private TextView picture_title;
    private RecyclerView recyclerView;
    private View window;

    public FolderPopWindow(Context context2, int i) {
        this.context = context2;
        this.mimeType = i;
        this.window = LayoutInflater.from(context2).inflate(R.layout.picture_window_folder, (ViewGroup) null);
        setContentView(this.window);
        setWidth(ScreenUtils.getScreenWidth(context2));
        setHeight(ScreenUtils.getScreenHeight(context2));
        setAnimationStyle(R.style.WindowStyle);
        setFocusable(true);
        setOutsideTouchable(true);
        update();
        setBackgroundDrawable(new ColorDrawable(Color.argb(123, 0, 0, 0)));
        this.drawableUp = AttrsUtils.getTypeValuePopWindowImg(context2, R.attr.picture_arrow_up_icon);
        this.drawableDown = AttrsUtils.getTypeValuePopWindowImg(context2, R.attr.picture_arrow_down_icon);
        this.animationIn = AnimationUtils.loadAnimation(context2, R.anim.photo_album_show);
        this.animationOut = AnimationUtils.loadAnimation(context2, R.anim.photo_album_dismiss);
        initView();
    }

    /* access modifiers changed from: private */
    public void dismiss4Pop() {
        new Handler().post(new Runnable() {
            public void run() {
                FolderPopWindow.super.dismiss();
            }
        });
    }

    public void bindFolder(List<LocalMediaFolder> list) {
        this.adapter.setMimeType(this.mimeType);
        this.adapter.bindFolderData(list);
    }

    public void dismiss() {
        if (!this.isDismiss) {
            StringUtils.modifyTextViewDrawable(this.picture_title, this.drawableDown, 2);
            this.isDismiss = true;
            this.recyclerView.startAnimation(this.animationOut);
            dismiss();
            this.animationOut.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    boolean unused = FolderPopWindow.this.isDismiss = false;
                    if (Build.VERSION.SDK_INT <= 16) {
                        FolderPopWindow.this.dismiss4Pop();
                    } else {
                        FolderPopWindow.super.dismiss();
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
        }
    }

    public void initView() {
        this.id_ll_root = (LinearLayout) this.window.findViewById(R.id.id_ll_root);
        this.adapter = new PictureAlbumDirectoryAdapter(this.context);
        this.recyclerView = this.window.findViewById(R.id.folder_list);
        ViewGroup.LayoutParams layoutParams = this.recyclerView.getLayoutParams();
        double screenHeight = (double) ScreenUtils.getScreenHeight(this.context);
        Double.isNaN(screenHeight);
        layoutParams.height = (int) (screenHeight * 0.6d);
        this.recyclerView.addItemDecoration(new RecycleViewDivider(this.context, 0, ScreenUtils.dip2px(this.context, CropImageView.DEFAULT_ASPECT_RATIO), ContextCompat.getColor(this.context, R.color.transparent)));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.recyclerView.setAdapter(this.adapter);
        this.id_ll_root.setOnClickListener(this);
    }

    public void notifyDataCheckedStatus(List<LocalMedia> list) {
        try {
            List<LocalMediaFolder> folderData = this.adapter.getFolderData();
            for (LocalMediaFolder checkedNum : folderData) {
                checkedNum.setCheckedNum(0);
            }
            if (list.size() > 0) {
                for (LocalMediaFolder next : folderData) {
                    int i = 0;
                    for (LocalMedia path : next.getImages()) {
                        String path2 = path.getPath();
                        for (LocalMedia path3 : list) {
                            if (path2.equals(path3.getPath())) {
                                int i2 = i + 1;
                                next.setCheckedNum(i2);
                                i = i2;
                            }
                        }
                    }
                }
            }
            this.adapter.bindFolderData(folderData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.id_ll_root) {
            dismiss();
        }
    }

    public void setOnItemClickListener(PictureAlbumDirectoryAdapter.OnItemClickListener onItemClickListener) {
        this.adapter.setOnItemClickListener(onItemClickListener);
    }

    public void setPictureTitleView(TextView textView) {
        this.picture_title = textView;
    }

    public void showAsDropDown(View view) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                setHeight(view.getResources().getDisplayMetrics().heightPixels - rect.bottom);
            }
            super.showAsDropDown(view);
            this.isDismiss = false;
            this.recyclerView.startAnimation(this.animationIn);
            StringUtils.modifyTextViewDrawable(this.picture_title, this.drawableUp, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
