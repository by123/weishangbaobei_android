package com.umeng.socialize.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.stub.StubApp;
import com.umeng.social.tool.UMImageMark;
import com.umeng.socialize.a.a.a;
import com.umeng.socialize.media.UMediaObject;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.SocializeUtils;
import com.umeng.socialize.utils.UmengText;
import com.yalantis.ucrop.view.CropImageView;
import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class UMImage extends BaseMediaObject {
    public static int BINARY_IMAGE = 5;
    public static int BITMAP_IMAGE = 4;
    public static int FILE_IMAGE = 1;
    public static int MAX_HEIGHT = 1024;
    public static int MAX_WIDTH = 768;
    public static int RES_IMAGE = 3;
    public static int URL_IMAGE = 2;
    public Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    public CompressStyle compressStyle = CompressStyle.SCALE;
    private ConfiguredConvertor f = null;
    private UMImage g;
    private UMImageMark h;
    private int i = 0;
    public boolean isLoadImgByCompress = true;
    private boolean j;

    class BinaryConvertor extends ConfiguredConvertor {
        private byte[] b;

        public BinaryConvertor(byte[] bArr) {
            this.b = bArr;
        }

        public byte[] asBinary() {
            return this.b;
        }

        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(asBinary());
            }
            return null;
        }

        public File asFile() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.c(asBinary());
            }
            return null;
        }

        public String asUrl() {
            return null;
        }
    }

    class BitmapConvertor extends ConfiguredConvertor {
        private Bitmap b;

        public BitmapConvertor(Bitmap bitmap) {
            this.b = bitmap;
        }

        public byte[] asBinary() {
            return a.b(this.b, UMImage.this.compressFormat);
        }

        public Bitmap asBitmap() {
            return this.b;
        }

        public File asFile() {
            byte[] b2 = a.b(this.b, UMImage.this.compressFormat);
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.c(b2);
            }
            return null;
        }

        public String asUrl() {
            return null;
        }
    }

    public enum CompressStyle {
        SCALE,
        QUALITY
    }

    static abstract class ConfiguredConvertor implements IImageConvertor {
        ConfiguredConvertor() {
        }
    }

    class FileConvertor extends ConfiguredConvertor {
        private File b;

        public FileConvertor(File file) {
            this.b = file;
        }

        public byte[] asBinary() {
            return a.a(this.b, UMImage.this.compressFormat);
        }

        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(UMImage.this.asBinImage());
            }
            return null;
        }

        public File asFile() {
            return this.b;
        }

        public String asUrl() {
            return null;
        }
    }

    interface IImageConvertor {
        byte[] asBinary();

        Bitmap asBitmap();

        File asFile();

        String asUrl();
    }

    class ResConvertor extends ConfiguredConvertor {
        private Context b;
        private int c = 0;

        public ResConvertor(Context context, int i) {
            this.b = context;
            this.c = i;
        }

        public byte[] asBinary() {
            return a.a(this.b, this.c, UMImage.this.isLoadImgByCompress, UMImage.this.compressFormat);
        }

        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(asBinary());
            }
            return null;
        }

        public File asFile() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.c(asBinary());
            }
            return null;
        }

        public String asUrl() {
            return null;
        }
    }

    class UrlConvertor extends ConfiguredConvertor {
        private String b = null;

        public UrlConvertor(String str) {
            this.b = str;
        }

        public byte[] asBinary() {
            return a.a(this.b);
        }

        public Bitmap asBitmap() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.b(asBinary());
            }
            return null;
        }

        public File asFile() {
            if (SocializeUtils.assertBinaryInvalid(asBinary())) {
                return a.c(asBinary());
            }
            return null;
        }

        public String asUrl() {
            return this.b;
        }
    }

    public UMImage(Context context, int i2) {
        a(context, (Object) Integer.valueOf(i2));
    }

    public UMImage(Context context, int i2, UMImageMark uMImageMark) {
        a(context, Integer.valueOf(i2), uMImageMark);
    }

    public UMImage(Context context, Bitmap bitmap) {
        a(context, (Object) bitmap);
    }

    public UMImage(Context context, Bitmap bitmap, UMImageMark uMImageMark) {
        a(context, bitmap, uMImageMark);
    }

    public UMImage(Context context, File file) {
        a(context, (Object) file);
    }

    public UMImage(Context context, String str) {
        super(str);
        a((Context) new WeakReference(context).get(), (Object) str);
    }

    public UMImage(Context context, byte[] bArr) {
        a(context, (Object) bArr);
    }

    public UMImage(Context context, byte[] bArr, UMImageMark uMImageMark) {
        a(context, bArr, uMImageMark);
    }

    private float a(float f2, float f3, float f4, float f5) {
        if (f2 <= f5 && f3 <= f5) {
            return -1.0f;
        }
        float f6 = f2 / f4;
        float f7 = f3 / f5;
        return f6 <= f7 ? f7 : f6;
    }

    private Bitmap a(Context context, int i2) {
        InputStream inputStream;
        Bitmap bitmap = null;
        if (!(i2 == 0 || context == null || this.h == null)) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                inputStream = context.getResources().openRawResource(i2);
                try {
                    BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                    a((Closeable) inputStream);
                    int a = (int) a((float) options.outWidth, (float) options.outHeight, (float) MAX_WIDTH, (float) MAX_HEIGHT);
                    if (a > 0) {
                        options.inSampleSize = a;
                    }
                    options.inJustDecodeBounds = false;
                    inputStream = context.getResources().openRawResource(i2);
                } catch (Exception e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        a((Closeable) inputStream);
                        return bitmap;
                    } catch (Throwable th) {
                        th = th;
                        a((Closeable) inputStream);
                        throw th;
                    }
                }
                try {
                    bitmap = a(BitmapFactory.decodeStream(inputStream, (Rect) null, options), false);
                    a((Closeable) inputStream);
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    a((Closeable) inputStream);
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    a((Closeable) inputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                inputStream = null;
                e.printStackTrace();
                a((Closeable) inputStream);
                return bitmap;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                a((Closeable) inputStream);
                throw th;
            }
        }
        return bitmap;
    }

    private Bitmap a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float a = a((float) width, (float) height, (float) MAX_WIDTH, (float) MAX_HEIGHT);
        if (a < CropImageView.DEFAULT_ASPECT_RATIO) {
            return bitmap;
        }
        float f2 = 1.0f / a;
        Matrix matrix = new Matrix();
        matrix.postScale(f2, f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        b(bitmap);
        return createBitmap;
    }

    private Bitmap a(Bitmap bitmap, boolean z) {
        if (this.h == null) {
            return bitmap;
        }
        if (bitmap == null) {
            return null;
        }
        if (z) {
            try {
                bitmap = a(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.h.compound(bitmap);
    }

    private Bitmap a(byte[] bArr) {
        if (bArr == null || this.h == null) {
            return null;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            int a = (int) a((float) options.outWidth, (float) options.outHeight, (float) MAX_WIDTH, (float) MAX_HEIGHT);
            if (a > 0) {
                options.inSampleSize = a;
            }
            options.inJustDecodeBounds = false;
            return a(BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options), false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void a(Context context, Object obj) {
        a(context, obj, (UMImageMark) null);
    }

    private void a(Context context, Object obj, UMImageMark uMImageMark) {
        Bitmap bitmap = null;
        if (uMImageMark != null) {
            this.j = true;
            this.h = uMImageMark;
            this.h.setContext(context);
        }
        if (ContextUtil.getContext() == null) {
            ContextUtil.setContext(StubApp.getOrigApplicationContext(context.getApplicationContext()));
        }
        if (obj instanceof File) {
            this.i = FILE_IMAGE;
            this.f = new FileConvertor((File) obj);
        } else if (obj instanceof String) {
            this.i = URL_IMAGE;
            this.f = new UrlConvertor((String) obj);
        } else if (obj instanceof Integer) {
            this.i = RES_IMAGE;
            if (isHasWaterMark()) {
                bitmap = a(context, ((Integer) obj).intValue());
            }
            if (bitmap != null) {
                this.f = new BitmapConvertor(bitmap);
            } else {
                this.f = new ResConvertor(StubApp.getOrigApplicationContext(context.getApplicationContext()), ((Integer) obj).intValue());
            }
        } else if (obj instanceof byte[]) {
            this.i = BINARY_IMAGE;
            if (isHasWaterMark()) {
                bitmap = a((byte[]) obj);
            }
            if (bitmap != null) {
                this.f = new BitmapConvertor(bitmap);
            } else {
                this.f = new BinaryConvertor((byte[]) obj);
            }
        } else if (obj instanceof Bitmap) {
            this.i = BITMAP_IMAGE;
            if (isHasWaterMark()) {
                bitmap = a((Bitmap) obj, true);
            }
            this.f = new BitmapConvertor(bitmap == null ? (Bitmap) obj : bitmap);
        } else {
            throw new RuntimeException(UmengText.UNKNOW_UMIMAGE);
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void b(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] asBinImage() {
        if (this.f == null) {
            return null;
        }
        return this.f.asBinary();
    }

    public Bitmap asBitmap() {
        if (this.f == null) {
            return null;
        }
        return this.f.asBitmap();
    }

    public File asFileImage() {
        if (this.f == null) {
            return null;
        }
        return this.f.asFile();
    }

    public String asUrlImage() {
        if (this.f == null) {
            return null;
        }
        return this.f.asUrl();
    }

    public int getImageStyle() {
        return this.i;
    }

    public UMediaObject.MediaType getMediaType() {
        return UMediaObject.MediaType.IMAGE;
    }

    public UMImage getThumbImage() {
        return this.g;
    }

    public boolean isHasWaterMark() {
        return this.j;
    }

    public void setThumb(UMImage uMImage) {
        this.g = uMImage;
    }

    public byte[] toByte() {
        return asBinImage();
    }

    public final Map<String, Object> toUrlExtraParams() {
        HashMap hashMap = new HashMap();
        if (isUrlMedia()) {
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FURL, this.a);
            hashMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FTYPE, getMediaType());
        }
        return hashMap;
    }
}
