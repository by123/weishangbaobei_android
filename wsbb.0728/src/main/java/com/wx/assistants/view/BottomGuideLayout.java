package com.wx.assistants.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.wx.assistant.R;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.webview.WebViewActivity;

public class BottomGuideLayout extends FrameLayout implements View.OnClickListener {
    private RelativeLayout bt_image_tutorial;
    private RelativeLayout bt_video_tutorial;
    private Context context;
    private String id;
    private TextView text_describe;

    public BottomGuideLayout(Context context2) {
        super(context2);
        init(context2, (AttributeSet) null, 0);
    }

    public BottomGuideLayout(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        init(context2, attributeSet, 0);
    }

    public BottomGuideLayout(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        init(context2, attributeSet, i);
    }

    private void init(Context context2, AttributeSet attributeSet, int i) {
        this.context = context2;
        View inflate = LayoutInflater.from(context2).inflate(2131427488, (ViewGroup) null);
        addView(inflate);
        this.bt_video_tutorial = (RelativeLayout) inflate.findViewById(2131296424);
        this.bt_image_tutorial = (RelativeLayout) inflate.findViewById(2131296417);
        this.text_describe = (TextView) inflate.findViewById(2131297497);
        this.bt_video_tutorial.setOnClickListener(this);
        this.bt_image_tutorial.setOnClickListener(this);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context2.getTheme().obtainStyledAttributes(attributeSet, R.styleable.BottomGuideLayout, i, 0);
            String string = obtainStyledAttributes.getString(0);
            obtainStyledAttributes.recycle();
            this.text_describe.setText(string);
        }
    }

    public void onClick(View view) {
        int id2 = view.getId();
        if (id2 == 2131296417) {
            WebViewActivity.startActivity(MyApplication.getConText(), "", "http://h5.abcvabc.com/course/mptssyq/index.html");
        } else if (id2 == 2131296424) {
            WebViewActivity.startActivity(MyApplication.getConText(), "", "http://wechatbox.oss-cn-beijing.aliyuncs.com/mptsgsyq.mp4");
        }
    }

    public void setId(String str) {
        this.id = str;
    }
}
