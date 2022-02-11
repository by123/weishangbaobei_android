package com.meiqia.meiqiasdk.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.umeng.socialize.sina.params.ShareRequestParam;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.xml.sax.XMLReader;

public class RichText {
    /* access modifiers changed from: private */
    public static Map<String, SoftReference<Drawable>> sHtmlDrawableCache = new HashMap();
    private Html.ImageGetter imageGetter = new Html.ImageGetter() {
        public Drawable getDrawable(final String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (RichText.sHtmlDrawableCache.get(str) != null && ((SoftReference) RichText.sHtmlDrawableCache.get(str)).get() != null) {
                return (Drawable) ((SoftReference) RichText.sHtmlDrawableCache.get(str)).get();
            }
            Drawable drawableFromFile = MQUtils.getDrawableFromFile(RichText.this.textView.getContext(), str);
            if (drawableFromFile != null) {
                RichText.this.resizeDrawable(drawableFromFile);
                RichText.sHtmlDrawableCache.put(str, new SoftReference(drawableFromFile));
                return drawableFromFile;
            }
            final URLDrawable uRLDrawable = new URLDrawable();
            MQImage.downloadImage(RichText.this.textView.getContext(), str, new MQImageLoader.MQDownloadImageListener() {
                public void onFailed(String str) {
                }

                public void onSuccess(String str, final Bitmap bitmap) {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                    RichText.this.resizeDrawable(bitmapDrawable);
                    RichText.sHtmlDrawableCache.put(str, new SoftReference(bitmapDrawable));
                    uRLDrawable.drawable = bitmapDrawable;
                    RichText.this.fromHtml(RichText.this.richTextStr).setOnImageClickListener(RichText.this.onImageClickListener).into(RichText.this.textView);
                    new Thread(new Runnable() {
                        public void run() {
                            MQUtils.saveBitmap(RichText.this.textView.getContext(), str, bitmap);
                        }
                    }).start();
                }
            });
            return uRLDrawable;
        }
    };
    /* access modifiers changed from: private */
    public OnImageClickListener onImageClickListener;
    /* access modifiers changed from: private */
    public String richTextStr;
    private Html.TagHandler tagHandler = new MyTagHandler();
    /* access modifiers changed from: private */
    public TextView textView;

    public class MyTagHandler implements Html.TagHandler {

        private class ClickableImage extends ClickableSpan {
            private String url;

            public ClickableImage(String str) {
                this.url = str;
            }

            public void onClick(View view) {
                if (RichText.this.onImageClickListener != null) {
                    RichText.this.onImageClickListener.onImageClicked(this.url);
                }
            }
        }

        public MyTagHandler() {
        }

        public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
            if (str.toLowerCase(Locale.getDefault()).equals(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG)) {
                int length = editable.length();
                int i = length - 1;
                editable.setSpan(new ClickableImage(((ImageSpan[]) editable.getSpans(i, length, ImageSpan.class))[0].getSource()), i, length, 33);
            }
        }
    }

    public interface OnImageClickListener {
        void onImageClicked(String str);
    }

    private class URLDrawable extends BitmapDrawable {
        protected Drawable drawable;

        private URLDrawable() {
        }

        public void draw(Canvas canvas) {
            if (this.drawable != null) {
                this.drawable.draw(canvas);
            }
        }
    }

    /* access modifiers changed from: private */
    public void resizeDrawable(Drawable drawable) {
        float f = this.textView.getResources().getDisplayMetrics().density;
        if (f > 2.0f) {
            f = (f / 2.0f) + (0.25f * f);
        }
        float intrinsicWidth = (float) drawable.getIntrinsicWidth();
        float intrinsicHeight = (float) drawable.getIntrinsicHeight();
        int i = (int) (intrinsicWidth * f);
        int i2 = (int) (f * intrinsicHeight);
        int dip2px = MQUtils.dip2px(this.textView.getContext(), 205.0f);
        if (i > dip2px) {
            i2 = (int) (intrinsicHeight / (intrinsicWidth / ((float) dip2px)));
        } else {
            dip2px = i;
        }
        drawable.setBounds(0, 0, dip2px, i2);
    }

    public RichText fromHtml(String str) {
        this.richTextStr = str;
        return this;
    }

    public void into(TextView textView2) {
        CharSequence charSequence;
        this.textView = textView2;
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        Spanned fromHtml = Html.fromHtml(this.richTextStr, this.imageGetter, this.tagHandler);
        try {
            int length = fromHtml.length();
            charSequence = fromHtml;
            if (length >= 2) {
                char charAt = fromHtml.charAt(fromHtml.length() - 1);
                charSequence = fromHtml;
                if (charAt == 10) {
                    char charAt2 = fromHtml.charAt(fromHtml.length() - 2);
                    charSequence = fromHtml;
                    if (charAt2 == 10) {
                        charSequence = fromHtml.subSequence(0, fromHtml.length() - 2);
                    }
                }
            }
        } catch (Exception e) {
            charSequence = fromHtml;
        }
        this.textView.setText(charSequence);
        this.textView.setVisibility(0);
        this.textView.invalidate();
    }

    public RichText setOnImageClickListener(OnImageClickListener onImageClickListener2) {
        this.onImageClickListener = onImageClickListener2;
        return this;
    }
}
