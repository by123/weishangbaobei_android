package com.meiqia.meiqiasdk.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
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

    public interface OnImageClickListener {
        void onImageClicked(String str);
    }

    /* access modifiers changed from: private */
    public void resizeDrawable(Drawable drawable) {
        float f = this.textView.getResources().getDisplayMetrics().density;
        if (f > 2.0f) {
            f = (f * 0.25f) + (f / 2.0f);
        }
        float intrinsicWidth = (float) drawable.getIntrinsicWidth();
        float intrinsicHeight = (float) drawable.getIntrinsicHeight();
        int i = (int) (intrinsicWidth * f);
        int i2 = (int) (f * intrinsicHeight);
        int dip2px = MQUtils.dip2px(this.textView.getContext(), 205.0f);
        if (i > dip2px) {
            i2 = (int) (intrinsicHeight / (intrinsicWidth / ((float) dip2px)));
            i = dip2px;
        }
        drawable.setBounds(0, 0, i, i2);
    }

    public RichText fromHtml(String str) {
        this.richTextStr = str;
        return this;
    }

    public RichText setOnImageClickListener(OnImageClickListener onImageClickListener2) {
        this.onImageClickListener = onImageClickListener2;
        return this;
    }

    /* JADX WARNING: type inference failed for: r1v11, types: [java.lang.CharSequence] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void into(android.widget.TextView r5) {
        /*
            r4 = this;
            r4.textView = r5
            android.text.method.MovementMethod r0 = android.text.method.LinkMovementMethod.getInstance()
            r5.setMovementMethod(r0)
            java.lang.String r5 = r4.richTextStr
            android.text.Html$ImageGetter r0 = r4.imageGetter
            android.text.Html$TagHandler r1 = r4.tagHandler
            android.text.Spanned r5 = android.text.Html.fromHtml(r5, r0, r1)
            r0 = 0
            int r1 = r5.length()     // Catch:{ Exception -> 0x003e }
            r2 = 2
            if (r1 < r2) goto L_0x003e
            int r1 = r5.length()     // Catch:{ Exception -> 0x003e }
            int r1 = r1 + -1
            char r1 = r5.charAt(r1)     // Catch:{ Exception -> 0x003e }
            r3 = 10
            if (r1 != r3) goto L_0x003e
            int r1 = r5.length()     // Catch:{ Exception -> 0x003e }
            int r1 = r1 - r2
            char r1 = r5.charAt(r1)     // Catch:{ Exception -> 0x003e }
            if (r1 != r3) goto L_0x003e
            int r1 = r5.length()     // Catch:{ Exception -> 0x003e }
            int r1 = r1 - r2
            java.lang.CharSequence r1 = r5.subSequence(r0, r1)     // Catch:{ Exception -> 0x003e }
            r5 = r1
        L_0x003e:
            android.widget.TextView r1 = r4.textView
            r1.setText(r5)
            android.widget.TextView r5 = r4.textView
            r5.setVisibility(r0)
            android.widget.TextView r5 = r4.textView
            r5.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.util.RichText.into(android.widget.TextView):void");
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

    public class MyTagHandler implements Html.TagHandler {
        public MyTagHandler() {
        }

        public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
            if (str.toLowerCase(Locale.getDefault()).equals(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG)) {
                int length = editable.length();
                int i = length - 1;
                editable.setSpan(new ClickableImage(((ImageSpan[]) editable.getSpans(i, length, ImageSpan.class))[0].getSource()), i, length, 33);
            }
        }

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
    }
}
