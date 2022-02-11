package com.meiqia.meiqiasdk.imageloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.stub.StubApp;

public class MQGlideImageLoader4 extends MQImageLoader {
    private MQGlideImageLoader3 mGlideImageLoader3;

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void displayImage(android.app.Activity r14, final android.widget.ImageView r15, java.lang.String r16, @android.support.annotation.DrawableRes int r17, @android.support.annotation.DrawableRes int r18, int r19, int r20, com.meiqia.meiqiasdk.imageloader.MQImageLoader.MQDisplayImageListener r21) {
        /*
            r13 = this;
            r9 = r13
            r10 = r15
            r11 = r16
            java.lang.String r0 = r13.getPath(r11)
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Error -> 0x0060 }
            r2 = 29
            if (r1 < r2) goto L_0x0025
            r12 = r14
            android.net.Uri r3 = com.meiqia.meiqiasdk.util.MQUtils.getImageContentUri(r14, r11)     // Catch:{ Error -> 0x0061 }
            r0 = r13
            r1 = r14
            r2 = r15
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r0.displayImage((android.app.Activity) r1, (android.widget.ImageView) r2, (android.net.Uri) r3, (int) r4, (int) r5, (int) r6, (int) r7, (com.meiqia.meiqiasdk.imageloader.MQImageLoader.MQDisplayImageListener) r8)     // Catch:{ Error -> 0x0061 }
            goto L_0x0089
        L_0x0025:
            r12 = r14
            com.bumptech.glide.RequestManager r1 = com.bumptech.glide.Glide.with(r14)     // Catch:{ Error -> 0x0061 }
            com.bumptech.glide.RequestBuilder r1 = r1.load(r0)     // Catch:{ Error -> 0x0061 }
            com.bumptech.glide.request.RequestOptions r2 = new com.bumptech.glide.request.RequestOptions     // Catch:{ Error -> 0x0061 }
            r2.<init>()     // Catch:{ Error -> 0x0061 }
            r4 = r17
            com.bumptech.glide.request.BaseRequestOptions r2 = r2.placeholder(r4)     // Catch:{ Error -> 0x0063 }
            com.bumptech.glide.request.RequestOptions r2 = (com.bumptech.glide.request.RequestOptions) r2     // Catch:{ Error -> 0x0063 }
            r5 = r18
            com.bumptech.glide.request.BaseRequestOptions r2 = r2.error(r5)     // Catch:{ Error -> 0x0065 }
            com.bumptech.glide.request.RequestOptions r2 = (com.bumptech.glide.request.RequestOptions) r2     // Catch:{ Error -> 0x0065 }
            r6 = r19
            r7 = r20
            com.bumptech.glide.request.BaseRequestOptions r2 = r2.override(r6, r7)     // Catch:{ Error -> 0x0069 }
            com.bumptech.glide.RequestBuilder r1 = r1.apply(r2)     // Catch:{ Error -> 0x0069 }
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader4$1 r2 = new com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader4$1     // Catch:{ Error -> 0x0069 }
            r8 = r21
            r2.<init>(r8, r15, r0)     // Catch:{ Error -> 0x005e }
            com.bumptech.glide.RequestBuilder r0 = r1.listener(r2)     // Catch:{ Error -> 0x005e }
            r0.into(r15)     // Catch:{ Error -> 0x005e }
            goto L_0x0089
        L_0x005e:
            goto L_0x006b
        L_0x0060:
            r12 = r14
        L_0x0061:
            r4 = r17
        L_0x0063:
            r5 = r18
        L_0x0065:
            r6 = r19
            r7 = r20
        L_0x0069:
            r8 = r21
        L_0x006b:
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3 r0 = r9.mGlideImageLoader3
            if (r0 != 0) goto L_0x0076
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3 r0 = new com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3
            r0.<init>()
            r9.mGlideImageLoader3 = r0
        L_0x0076:
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3 r0 = r9.mGlideImageLoader3
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r0.displayImage((android.app.Activity) r1, (android.widget.ImageView) r2, (java.lang.String) r3, (int) r4, (int) r5, (int) r6, (int) r7, (com.meiqia.meiqiasdk.imageloader.MQImageLoader.MQDisplayImageListener) r8)
        L_0x0089:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader4.displayImage(android.app.Activity, android.widget.ImageView, java.lang.String, int, int, int, int, com.meiqia.meiqiasdk.imageloader.MQImageLoader$MQDisplayImageListener):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0065  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void displayImage(android.app.Activity r17, android.widget.ImageView r18, android.net.Uri r19, int r20, int r21, int r22, int r23, com.meiqia.meiqiasdk.imageloader.MQImageLoader.MQDisplayImageListener r24) {
        /*
            r16 = this;
            r6 = r16
            com.bumptech.glide.RequestManager r0 = com.bumptech.glide.Glide.with(r17)     // Catch:{ Error -> 0x0055 }
            r10 = r19
            com.bumptech.glide.RequestBuilder r0 = r0.load(r10)     // Catch:{ Error -> 0x0052 }
            com.bumptech.glide.request.RequestOptions r1 = new com.bumptech.glide.request.RequestOptions     // Catch:{ Error -> 0x0052 }
            r1.<init>()     // Catch:{ Error -> 0x0052 }
            r11 = r20
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.placeholder(r11)     // Catch:{ Error -> 0x004f }
            com.bumptech.glide.request.RequestOptions r1 = (com.bumptech.glide.request.RequestOptions) r1     // Catch:{ Error -> 0x004f }
            r12 = r21
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.error(r12)     // Catch:{ Error -> 0x004c }
            com.bumptech.glide.request.RequestOptions r1 = (com.bumptech.glide.request.RequestOptions) r1     // Catch:{ Error -> 0x004c }
            r13 = r22
            r14 = r23
            com.bumptech.glide.request.BaseRequestOptions r1 = r1.override(r13, r14)     // Catch:{ Error -> 0x0049 }
            com.bumptech.glide.RequestBuilder r7 = r0.apply(r1)     // Catch:{ Error -> 0x0049 }
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader4$2 r8 = new com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader4$2     // Catch:{ Error -> 0x0049 }
            r0 = r8
            r1 = r16
            r2 = r24
            r3 = r18
            r4 = r17
            r5 = r19
            r0.<init>(r2, r3, r4, r5)     // Catch:{ Error -> 0x0049 }
            com.bumptech.glide.RequestBuilder r0 = r7.listener(r8)     // Catch:{ Error -> 0x0049 }
            r1 = r18
            r0.into(r1)     // Catch:{ Error -> 0x0047 }
            goto L_0x0081
        L_0x0047:
            goto L_0x0061
        L_0x0049:
            r1 = r18
            goto L_0x0061
        L_0x004c:
            r1 = r18
            goto L_0x005d
        L_0x004f:
            r1 = r18
            goto L_0x005b
        L_0x0052:
            r1 = r18
            goto L_0x0059
        L_0x0055:
            r1 = r18
            r10 = r19
        L_0x0059:
            r11 = r20
        L_0x005b:
            r12 = r21
        L_0x005d:
            r13 = r22
            r14 = r23
        L_0x0061:
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3 r0 = r6.mGlideImageLoader3
            if (r0 != 0) goto L_0x006c
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3 r0 = new com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3
            r0.<init>()
            r6.mGlideImageLoader3 = r0
        L_0x006c:
            com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader3 r7 = r6.mGlideImageLoader3
            r8 = r17
            r9 = r18
            r10 = r19
            r11 = r20
            r12 = r21
            r13 = r22
            r14 = r23
            r15 = r24
            r7.displayImage((android.app.Activity) r8, (android.widget.ImageView) r9, (android.net.Uri) r10, (int) r11, (int) r12, (int) r13, (int) r14, (com.meiqia.meiqiasdk.imageloader.MQImageLoader.MQDisplayImageListener) r15)
        L_0x0081:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.imageloader.MQGlideImageLoader4.displayImage(android.app.Activity, android.widget.ImageView, android.net.Uri, int, int, int, int, com.meiqia.meiqiasdk.imageloader.MQImageLoader$MQDisplayImageListener):void");
    }

    public void downloadImage(Context context, String str, final MQImageLoader.MQDownloadImageListener mQDownloadImageListener) {
        final String path = getPath(str);
        try {
            Glide.with(StubApp.getOrigApplicationContext(context.getApplicationContext())).load(path).into(new SimpleTarget<Drawable>() {
                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    if (mQDownloadImageListener != null) {
                        mQDownloadImageListener.onSuccess(path, MQUtils.drawableToBitmap(drawable));
                    }
                }

                public void onLoadFailed(@Nullable Drawable drawable) {
                    if (mQDownloadImageListener != null) {
                        mQDownloadImageListener.onFailed(path);
                    }
                }
            });
        } catch (Error unused) {
            if (this.mGlideImageLoader3 == null) {
                this.mGlideImageLoader3 = new MQGlideImageLoader3();
            }
            this.mGlideImageLoader3.downloadImage(context, str, mQDownloadImageListener);
        }
    }
}
