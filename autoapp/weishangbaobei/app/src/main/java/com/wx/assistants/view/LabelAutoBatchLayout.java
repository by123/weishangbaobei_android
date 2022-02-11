package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.yalantis.ucrop.view.CropImageView;

public class LabelAutoBatchLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public TextView ffTime;
    /* access modifiers changed from: private */
    public int groupNum = SubsamplingScaleImageView.ORIENTATION_180;
    /* access modifiers changed from: private */
    public OnAutoBatchModelListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    private Switch remarkSwitchBtn;

    public interface OnAutoBatchModelListener {
        void isOpen(boolean z);

        void resultGroupNum(int i);
    }

    public interface OnFFModelListener {
        void ffMode(int i);

        void ffModeTime(int i, int i2);
    }

    public void setDefaultGroupNum(int i, boolean z) {
        this.groupNum = i;
        TextView textView = this.ffTime;
        textView.setText("系统自动按每组【" + i + "】人进行分组、命名。假如设置的名称为：客源。则实际保存的名称依次为：客源【1】、客源【2】、客源【3】...点击文本可更改每组人数。");
        FontUtils.changeFontColor(this.mContext, this.ffTime, "组", "人进行分组");
        this.remarkSwitchBtn.setChecked(z);
    }

    public void setOnAutoBatchModelListener(OnAutoBatchModelListener onAutoBatchModelListener) {
        if (onAutoBatchModelListener != null) {
            this.listener = onAutoBatchModelListener;
        }
    }

    public LabelAutoBatchLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public LabelAutoBatchLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public LabelAutoBatchLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427761, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.remarkSwitchBtn = (Switch) findViewById(2131297234);
        this.ffTime = (TextView) findViewById(2131296661);
        TextView textView = this.ffTime;
        textView.setText("系统自动按每组【" + this.groupNum + "】人进行分组、命名。假如设置的名称为：客源。则实际保存的名称依次为：客源【1】、客源【2】、客源【3】...点击文本可更改每组人数。");
        FontUtils.changeFontColor(this.mContext, this.ffTime, "组", "人进行分组");
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    if (LabelAutoBatchLayout.this.listener != null) {
                        LabelAutoBatchLayout.this.listener.isOpen(true);
                    }
                } else if (LabelAutoBatchLayout.this.listener != null) {
                    LabelAutoBatchLayout.this.listener.isOpen(false);
                }
            }
        });
        this.ffTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogUIUtils.dialogSetStartPoint(LabelAutoBatchLayout.this.mContext, "设置每组的数量", LabelAutoBatchLayout.this.groupNum, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 1, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        if (LabelAutoBatchLayout.this.listener != null) {
                            LabelAutoBatchLayout.this.listener.resultGroupNum(i);
                            TextView access$300 = LabelAutoBatchLayout.this.ffTime;
                            access$300.setText("系统自动按每组【" + i + "】人进行分组、命名。假如设置的名称为：客源。则实际保存的名称依次为：客源【1】、客源【2】、客源【3】...点击文本可更改每组人数。");
                            FontUtils.changeFontColor(LabelAutoBatchLayout.this.mContext, LabelAutoBatchLayout.this.ffTime, "组", "人进行分组");
                        }
                    }
                });
            }
        });
    }
}
