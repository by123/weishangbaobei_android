package com.wx.assistants.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.luck.picture.lib.R;

public class TitleLayout extends FrameLayout implements View.OnClickListener {
    private LinearLayout arrow_back;
    private ImageView icon_img;
    private TitleListener titleListener;
    private RelativeLayout title_layout;
    private TextView title_text;

    public interface TitleListener {
        void onBackListener();
    }

    public TitleLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public TitleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public TitleLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        View inflate = LayoutInflater.from(context).inflate(2131427712, (ViewGroup) null);
        addView(inflate);
        this.title_text = (TextView) inflate.findViewById(2131297521);
        this.title_layout = (RelativeLayout) inflate.findViewById(R.id.title_layout);
        this.icon_img = (ImageView) inflate.findViewById(2131296754);
        this.arrow_back = (LinearLayout) inflate.findViewById(2131296353);
        this.arrow_back.setOnClickListener(this);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, com.example.wx.assistant.R.styleable.TitleLayout, i, 0);
            int color = obtainStyledAttributes.getColor(0, -16777216);
            String string = obtainStyledAttributes.getString(3);
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(2);
            obtainStyledAttributes.recycle();
            this.title_text.setTextColor(color);
            this.title_text.setText(string);
            this.title_layout.setBackground(drawable);
            this.icon_img.setImageDrawable(drawable2);
        }
    }

    public void onClick(View view) {
        if (this.titleListener != null && view.getId() == 2131296353) {
            this.titleListener.onBackListener();
        }
    }

    public void setTitleListener(TitleListener titleListener2) {
        this.titleListener = titleListener2;
    }
}
