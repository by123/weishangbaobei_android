package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AlertActionSheetDialog {
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_content;
    private View mBottomContainer;
    private ImageView mIvPopConcel;
    private RelativeLayout mRlMainTitleContainer;
    private TextView mTvMainTitle;
    private ScrollView sLayout_content;
    private List<SheetItem> sheetItemList;
    private boolean showTitle = false;
    private TextView txt_cancel;
    private TextView txt_title;

    public interface OnSheetItemClickListener {
        void onClick(int i);
    }

    public AlertActionSheetDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public AlertActionSheetDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427726, (ViewGroup) null);
        this.mBottomContainer = inflate.findViewById(2131296409);
        inflate.setMinimumWidth(this.display.getWidth());
        this.mRlMainTitleContainer = (RelativeLayout) inflate.findViewById(2131296993);
        this.mIvPopConcel = (ImageView) inflate.findViewById(2131297151);
        this.mTvMainTitle = (TextView) inflate.findViewById(2131296992);
        this.sLayout_content = (ScrollView) inflate.findViewById(2131297271);
        this.lLayout_content = (LinearLayout) inflate.findViewById(2131296882);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_cancel = (TextView) inflate.findViewById(2131297601);
        this.txt_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertActionSheetDialog.this.dialog.dismiss();
            }
        });
        this.dialog = new Dialog(this.context, 2131755012);
        this.dialog.setContentView(inflate);
        setGravity(83);
        return this;
    }

    public AlertActionSheetDialog setGravity(int i) {
        Window window = this.dialog.getWindow();
        window.setGravity(i);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        return this;
    }

    public AlertActionSheetDialog setBottomContentVisibility(int i) {
        this.mBottomContainer.setVisibility(i);
        return this;
    }

    public AlertActionSheetDialog setAnimation(int i) {
        this.dialog.getWindow().setWindowAnimations(i);
        return this;
    }

    public AlertActionSheetDialog setTitle(String str) {
        if (str != null || !"".equals(str)) {
            this.showTitle = true;
            this.txt_title.setVisibility(0);
            this.txt_title.setText(str);
        }
        return this;
    }

    public AlertActionSheetDialog setTitle(String str, int i) {
        if (str != null || !"".equals(str)) {
            this.showTitle = true;
            this.txt_title.setVisibility(0);
            this.txt_title.setText(str);
            this.txt_title.setGravity(i);
        }
        return this;
    }

    public AlertActionSheetDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertActionSheetDialog setCanceledOnTouchOutside(boolean z) {
        this.dialog.setCanceledOnTouchOutside(z);
        return this;
    }

    public AlertActionSheetDialog setOndismisListener(DialogInterface.OnDismissListener onDismissListener) {
        if (onDismissListener != null) {
            this.dialog.setOnDismissListener(onDismissListener);
        }
        return this;
    }

    public AlertActionSheetDialog isShowMainTile(boolean z) {
        this.mRlMainTitleContainer.setVisibility(z ? 0 : 8);
        if (z) {
            this.mIvPopConcel.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AlertActionSheetDialog.this.dialog != null) {
                        AlertActionSheetDialog.this.dialog.dismiss();
                    }
                }
            });
        }
        return this;
    }

    public AlertActionSheetDialog setMainTile(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mTvMainTitle.setText(str);
            isShowMainTile(true);
        }
        return this;
    }

    public AlertActionSheetDialog addSheetItem(String str, SheetItemColor sheetItemColor, OnSheetItemClickListener onSheetItemClickListener) {
        if (this.sheetItemList == null) {
            this.sheetItemList = new ArrayList();
        }
        this.sheetItemList.add(new SheetItem(str, sheetItemColor, onSheetItemClickListener));
        return this;
    }

    public AlertActionSheetDialog addSheetItem(String str, SheetItemColor sheetItemColor, boolean z, OnSheetItemClickListener onSheetItemClickListener) {
        if (this.sheetItemList == null) {
            this.sheetItemList = new ArrayList();
        }
        this.sheetItemList.add(new SheetItem(str, sheetItemColor, z, onSheetItemClickListener));
        return this;
    }

    private void setSheetItems() {
        if (this.sheetItemList != null && this.sheetItemList.size() > 0) {
            int size = this.sheetItemList.size();
            if (size >= 7) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.sLayout_content.getLayoutParams();
                layoutParams.height = this.display.getHeight() / 2;
                this.sLayout_content.setLayoutParams(layoutParams);
            }
            for (final int i = 1; i <= size; i++) {
                SheetItem sheetItem = this.sheetItemList.get(i - 1);
                String str = sheetItem.name;
                SheetItemColor sheetItemColor = sheetItem.color;
                final OnSheetItemClickListener onSheetItemClickListener = sheetItem.itemClickListener;
                View inflate = LayoutInflater.from(this.context).inflate(2131427355, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(2131296835);
                ImageView imageView = (ImageView) inflate.findViewById(2131296830);
                textView.setText(str);
                if (sheetItemColor == null) {
                    textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
                } else {
                    textView.setTextColor(Color.parseColor(sheetItemColor.getName()));
                }
                imageView.setVisibility(sheetItem.isChecked ? 0 : 8);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        onSheetItemClickListener.onClick(i);
                        AlertActionSheetDialog.this.dialog.dismiss();
                    }
                });
                this.lLayout_content.addView(inflate);
            }
        }
    }

    public AlertActionSheetDialog show() {
        setSheetItems();
        this.dialog.show();
        return this;
    }

    public class SheetItem {
        SheetItemColor color;
        boolean isChecked;
        OnSheetItemClickListener itemClickListener;
        String name;

        public SheetItem(String str, SheetItemColor sheetItemColor, OnSheetItemClickListener onSheetItemClickListener) {
            this.name = str;
            this.color = sheetItemColor;
            this.itemClickListener = onSheetItemClickListener;
        }

        public SheetItem(String str, SheetItemColor sheetItemColor, boolean z, OnSheetItemClickListener onSheetItemClickListener) {
            this.name = str;
            this.color = sheetItemColor;
            this.itemClickListener = onSheetItemClickListener;
            this.isChecked = z;
        }
    }

    public enum SheetItemColor {
        Blue("#037BFF"),
        Black("#000000"),
        Green("#FB4A4A"),
        Red("#E64C49"),
        Gray("#888888");
        
        private String name;

        private SheetItemColor(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public Dialog getDialog() {
        return this.dialog;
    }
}
