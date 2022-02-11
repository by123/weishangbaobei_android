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

public class FloatBottomView extends LinearLayout {
    private LinearLayout alertLayout;
    private TextView bottomTextView;
    private int color;
    public int height;
    private Context mContext;
    private LinearLayout mLinearLayout;
    private MyWindowManager myWindowManager;
    private TextView versionNameText;
    public int width;

    public FloatBottomView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FloatBottomView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatBottomView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
        String str = "";
        String str2 = "";
        try {
            str2 = PackageUtils.getWXVersionName(this.mContext);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            i2 = PackageUtils.getWXVersionCode(this.mContext);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            i2 = 0;
        }
        str = versionName != null ? versionName.substring(0, versionName.lastIndexOf(".")) : str;
        OperationParameterModel operationParameterModel = MyApplication.instance.getOperationParameterModel();
        if (operationParameterModel != null) {
            try {
                if (Integer.valueOf(Integer.parseInt(operationParameterModel.getTaskNum())).intValue() > 1000) {
                    try {
                        str2 = PackageUtils.getWXVersionNameCompany(this.mContext);
                    } catch (PackageManager.NameNotFoundException e3) {
                        e3.printStackTrace();
                    }
                    try {
                        i2 = PackageUtils.getWXVersionCodeCompany(this.mContext);
                    } catch (PackageManager.NameNotFoundException e4) {
                        e4.printStackTrace();
                    }
                    TextView textView = this.versionNameText;
                    textView.setText(str + "_" + str2 + "_" + i2);
                    return;
                }
                TextView textView2 = this.versionNameText;
                textView2.setText(str + "_" + str2 + "_" + i2);
            } catch (Exception unused) {
                TextView textView3 = this.versionNameText;
                textView3.setText(str + "_" + str2 + "_" + i2);
            }
        }
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
}
