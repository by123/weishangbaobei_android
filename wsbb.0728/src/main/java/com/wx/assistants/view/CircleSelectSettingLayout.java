package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.wx.assistants.activity.OneKeyCircleSettingActivity;

public class CircleSelectSettingLayout extends LinearLayout {
    private Context mContext;
    private String selectGroups = "";
    @BindView(2131297310)
    TextView selectGroupsResult;
    @BindView(2131297312)
    LinearLayout selectLayout;
    private String selectModel = "公开";
    @BindView(2131297313)
    TextView selectModelTv;
    @BindView(2131297316)
    LinearLayout selectResultLayout;
    private String selectTags = "";
    @BindView(2131297319)
    TextView selectTagsResult;

    public CircleSelectSettingLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public CircleSelectSettingLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public CircleSelectSettingLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427736, this, true);
        ButterKnife.bind(this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    @OnClick({2131297312})
    public void onViewClicked() {
        Intent intent = new Intent(this.mContext, OneKeyCircleSettingActivity.class);
        intent.putExtra("selectModel", this.selectModel);
        intent.putExtra("selectTags", this.selectTags);
        intent.putExtra("selectGroups", this.selectGroups);
        this.mContext.startActivity(intent);
    }

    public void setResult(String str, String str2, String str3) {
        this.selectModel = str;
        this.selectTags = str2;
        this.selectGroups = str3;
        this.selectModelTv.setText(str);
        if (!"".equals(str2) || !"".equals(str3)) {
            this.selectResultLayout.setVisibility(0);
            if (!"".equals(str2)) {
                this.selectTagsResult.setVisibility(0);
                TextView textView = this.selectTagsResult;
                textView.setText("标签：" + str2);
            } else {
                this.selectTagsResult.setVisibility(8);
                this.selectTagsResult.setText("");
            }
            if (!"".equals(str3)) {
                this.selectGroupsResult.setVisibility(0);
                TextView textView2 = this.selectGroupsResult;
                textView2.setText("群聊：" + str3);
                return;
            }
            this.selectGroupsResult.setVisibility(8);
            this.selectGroupsResult.setText("");
            return;
        }
        this.selectResultLayout.setVisibility(8);
    }
}
