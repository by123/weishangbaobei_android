package com.yongchun.library.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.isseiaoki.simplecropview.CropImageView;
import com.stub.StubApp;
import com.yongchun.library.R;
import com.yongchun.library.utils.CropUtil;
import com.yongchun.library.utils.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageCropActivity extends AppCompatActivity {
    public static final String EXTRA_PATH = "extraPath";
    public static final String OUTPUT_PATH = "outputPath";
    public static final int REQUEST_CROP = 69;
    private static final int SIZE_DEFAULT = 2048;
    private static final int SIZE_LIMIT = 4096;
    /* access modifiers changed from: private */
    public CropImageView cropImageView;
    private TextView doneText;
    private final Handler handler = new Handler();
    /* access modifiers changed from: private */
    public Uri saveUri;
    private Uri sourceUri;
    private Toolbar toolbar;

    static {
        StubApp.interface11(12650);
    }

    private int calculateBitmapSampleSize(Uri uri) throws IOException {
        InputStream inputStream = null;
        int i = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            inputStream = getContentResolver().openInputStream(uri);
            try {
                BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                CropUtil.closeSilently(inputStream);
                int maxImageSize = getMaxImageSize();
                while (true) {
                    if (options.outHeight / i <= maxImageSize && options.outWidth / i <= maxImageSize) {
                        return i;
                    }
                    i <<= 1;
                }
            } catch (Throwable th) {
                th = th;
                CropUtil.closeSilently(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            CropUtil.closeSilently(inputStream);
            throw th;
        }
    }

    private int getMaxImageSize() {
        int maxTextureSize = getMaxTextureSize();
        if (maxTextureSize == 0) {
            return 2048;
        }
        return Math.min(maxTextureSize, 4096);
    }

    private int getMaxTextureSize() {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        return iArr[0];
    }

    /* access modifiers changed from: private */
    public void saveOutput(final Bitmap bitmap) {
        OutputStream outputStream = null;
        if (this.saveUri != null) {
            try {
                outputStream = getContentResolver().openOutputStream(this.saveUri);
                if (outputStream != null) {
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                    } catch (IOException e) {
                        e = e;
                        try {
                            e.printStackTrace();
                            CropUtil.closeSilently(outputStream);
                            setResult(-1, new Intent().putExtra(OUTPUT_PATH, this.saveUri.getPath()));
                            this.handler.post(new Runnable() {
                                public void run() {
                                    bitmap.recycle();
                                }
                            });
                            finish();
                        } catch (Throwable th) {
                            th = th;
                            CropUtil.closeSilently(outputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        CropUtil.closeSilently(outputStream);
                        throw th;
                    }
                }
                CropUtil.closeSilently(outputStream);
            } catch (IOException e2) {
                e = e2;
            }
            setResult(-1, new Intent().putExtra(OUTPUT_PATH, this.saveUri.getPath()));
        }
        this.handler.post(new Runnable() {
            public void run() {
                bitmap.recycle();
            }
        });
        finish();
    }

    public static void startCrop(Activity activity, String str) {
        Intent intent = new Intent(activity, ImageCropActivity.class);
        intent.putExtra(EXTRA_PATH, str);
        activity.startActivityForResult(intent, 69);
    }

    public static void startCrop(Fragment fragment, String str) {
        Intent intent = new Intent(fragment.getActivity(), ImageCropActivity.class);
        intent.putExtra(EXTRA_PATH, str);
        fragment.startActivityForResult(intent, 69);
    }

    public Matrix getRotateMatrix(Bitmap bitmap, int i) {
        Matrix matrix = new Matrix();
        if (!(bitmap == null || i == 0)) {
            matrix.preTranslate((float) (-(bitmap.getWidth() / 2)), (float) (-(bitmap.getHeight() / 2)));
            matrix.postRotate((float) i);
            matrix.postTranslate((float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2));
        }
        return matrix;
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [android.content.Context, com.yongchun.library.view.ImageCropActivity] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0092=Splitter:B:17:0x0092, B:12:0x0089=Splitter:B:12:0x0089} */
    public void initView() {
        InputStream inputStream;
        InputStream inputStream2 = null;
        this.toolbar = findViewById(R.id.toolbar);
        this.toolbar.setTitle("");
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationIcon(R.mipmap.ic_back);
        this.doneText = (TextView) findViewById(R.id.done_text);
        this.cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        this.cropImageView.setHandleSizeInDp(10);
        int exifRotation = CropUtil.getExifRotation(CropUtil.getFromMediaUri(this, getContentResolver(), this.sourceUri));
        try {
            int calculateBitmapSampleSize = calculateBitmapSampleSize(this.sourceUri);
            inputStream = getContentResolver().openInputStream(this.sourceUri);
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = calculateBitmapSampleSize;
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect) null, options);
                if (decodeStream == null) {
                    CropUtil.closeSilently(inputStream);
                    return;
                }
                this.cropImageView.setImageBitmap(Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), getRotateMatrix(decodeStream, exifRotation % 360), true));
                CropUtil.closeSilently(inputStream);
                return;
            } catch (IOException e) {
                e = e;
                inputStream2 = inputStream;
            } catch (OutOfMemoryError e2) {
                e = e2;
                inputStream2 = inputStream;
                try {
                    e.printStackTrace();
                    CropUtil.closeSilently(inputStream2);
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStream2;
                }
            } catch (Throwable th2) {
                th = th2;
                CropUtil.closeSilently(inputStream);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (OutOfMemoryError e4) {
            e = e4;
            e.printStackTrace();
            CropUtil.closeSilently(inputStream2);
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            CropUtil.closeSilently(inputStream);
            throw th;
        }
        e.printStackTrace();
        CropUtil.closeSilently(inputStream2);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ImageCropActivity.super.onDestroy();
    }

    public void registerListener() {
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageCropActivity.this.finish();
            }
        });
        this.doneText.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.yongchun.library.view.ImageCropActivity] */
            public void onClick(View view) {
                Uri unused = ImageCropActivity.this.saveUri = Uri.fromFile(FileUtils.createCropFile(ImageCropActivity.this));
                ImageCropActivity.this.saveOutput(ImageCropActivity.this.cropImageView.getCroppedBitmap());
            }
        });
    }
}
