package com.wx.assistants.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import com.example.wx.assistant.R;
import com.wx.assistants.utils.fileutil.ListUtils;

public class AutoLinkStyleTextView extends AppCompatTextView {
    private static final int TYPE_CONTENT_TEXT = 1;
    private static final int TYPE_START_IMAGE = 0;
    /* access modifiers changed from: private */
    public static int defaultColor = Color.parseColor("#f23218");
    private static int styleType = 1;
    private String defaultTextValue;
    /* access modifiers changed from: private */
    public boolean hasUnderLine;
    /* access modifiers changed from: private */
    public ClickCallBack mClickCallBack;
    private int startImageDes;

    private class CenteredImageSpan extends ImageSpan {
        private CenteredImageSpan(Context context, int i) {
            super(context, i);
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            int i6 = fontMetricsInt.ascent;
            canvas.save();
            canvas.translate(f, (float) (((i6 + ((fontMetricsInt.descent + i4) + i4)) / 2) - (drawable.getBounds().bottom / 2)));
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    public interface ClickCallBack {
        void onClick(int i);
    }

    public AutoLinkStyleTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AutoLinkStyleTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoLinkStyleTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.defaultTextValue = null;
        this.hasUnderLine = true;
        init(context, attributeSet, i);
    }

    private void addStyle() {
        if (!TextUtils.isEmpty(this.defaultTextValue) && this.defaultTextValue.contains(ListUtils.DEFAULT_JOIN_SEPARATOR)) {
            String[] split = this.defaultTextValue.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            SpannableString spannableString = new SpannableString(getText().toString().trim());
            for (final int i = 0; i < split.length; i++) {
                spannableString.setSpan(new ClickableSpan() {
                    public void onClick(View view) {
                        if (AutoLinkStyleTextView.this.mClickCallBack != null) {
                            AutoLinkStyleTextView.this.mClickCallBack.onClick(i);
                        }
                    }

                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.setColor(AutoLinkStyleTextView.defaultColor);
                        textPaint.setUnderlineText(AutoLinkStyleTextView.this.hasUnderLine);
                    }
                }, getText().toString().trim().indexOf(split[i]), getText().toString().trim().indexOf(split[i]) + split[i].length(), 33);
            }
            setText(spannableString);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AutoLinkStyleTextView, i, 0);
        styleType = obtainStyledAttributes.getInt(4, 1);
        this.defaultTextValue = obtainStyledAttributes.getString(3);
        defaultColor = obtainStyledAttributes.getColor(0, defaultColor);
        this.hasUnderLine = obtainStyledAttributes.getBoolean(1, this.hasUnderLine);
        this.startImageDes = obtainStyledAttributes.getResourceId(2, 0);
        addStyle();
        obtainStyledAttributes.recycle();
    }

    public void setOnClickCallBack(ClickCallBack clickCallBack) {
        this.mClickCallBack = clickCallBack;
    }

    public void setStartImageText(CharSequence charSequence) {
        if (styleType == 0 && !TextUtils.isEmpty(charSequence) && this.startImageDes != 0) {
            SpannableString spannableString = new SpannableString("   " + charSequence);
            spannableString.setSpan(new CenteredImageSpan(getContext(), this.startImageDes), 0, 1, 33);
            setText(spannableString);
        }
    }
}
