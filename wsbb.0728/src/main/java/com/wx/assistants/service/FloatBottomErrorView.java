package com.wx.assistants.service;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.utils.PackageUtils;

public class FloatBottomErrorView extends LinearLayout {
    private LinearLayout alertLayout;
    private TextView bottomTextView;
    private int color;
    public int height;
    private Context mContext;
    private LinearLayout mLinearLayout;
    private MyWindowManager myWindowManager;
    private TextView versionNameText;
    public int width;

    public FloatBottomErrorView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatBottomErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatBottomErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        String str;
        int i2;
        this.color = 0;
        this.mContext = context;
        LayoutInflater.from(context).inflate(2131427695, this);
        this.mLinearLayout = (LinearLayout) findViewById(2131296402);
        this.alertLayout = (LinearLayout) findViewById(2131296329);
        this.bottomTextView = (TextView) this.mLinearLayout.findViewById(2131296406);
        this.versionNameText = (TextView) this.mLinearLayout.findViewById(2131297630);
        this.width = this.mLinearLayout.getLayoutParams().width;
        this.height = this.mLinearLayout.getLayoutParams().height;
        String versionName = PackageUtils.getVersionName(this.mContext);
        String str2 = "";
        try {
            str = PackageUtils.getWXVersionName(this.mContext);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            str = "";
        }
        try {
            i2 = PackageUtils.getWXVersionCode(this.mContext);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            i2 = 0;
        }
        str2 = versionName != null ? versionName.substring(0, versionName.lastIndexOf(".")) : str2;
        OperationParameterModel operationParameterModel = MyApplication.instance.getOperationParameterModel();
        if (operationParameterModel != null) {
            try {
                if (Integer.valueOf(Integer.parseInt(operationParameterModel.getTaskNum())).intValue() > 1000) {
                    try {
                        str = PackageUtils.getWXVersionNameCompany(this.mContext);
                    } catch (PackageManager.NameNotFoundException e3) {
                        e3.printStackTrace();
                    }
                    try {
                        i2 = PackageUtils.getWXVersionCodeCompany(this.mContext);
                    } catch (PackageManager.NameNotFoundException e4) {
                        e4.printStackTrace();
                    }
                    this.versionNameText.setText(str2 + "_" + str + "_" + i2);
                } else {
                    this.versionNameText.setText(str2 + "_" + str + "_" + i2);
                }
            } catch (Exception e5) {
                this.versionNameText.setText(str2 + "_" + str + "_" + i2);
            }
        }
        this.versionNameText.setTextColor(this.mContext.getResources().getColor(2131100063));
    }

    public void setBackGround(int i) {
        if (this.color != -1) {
            this.alertLayout.setBackgroundResource(i);
            this.bottomTextView.setTextColor(this.mContext.getResources().getColor(2131100063));
        }
    }

    public void setBottomText(String str) {
        if (this.bottomTextView != null && str != null) {
            this.bottomTextView.setText(str);
        }
    }

    public void setBottomText(String str, long j) {
        if (!(this.bottomTextView == null || str == null)) {
            this.bottomTextView.setText(str);
        }
        if (j > 0) {
            this.myWindowManager.removeBottomView();
        }
    }

    public void setBottomText(String str, boolean z) {
        if (this.bottomTextView != null && str != null) {
            this.bottomTextView.setText(str);
            if (z) {
                this.bottomTextView.setGravity(17);
            }
        }
    }
}
