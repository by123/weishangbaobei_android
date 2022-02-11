package com.luck.picture.lib;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.compress.OnCompressListener;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.dialog.PictureDialog;
import com.luck.picture.lib.entity.EventEntity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.immersive.ImmersiveManage;
import com.luck.picture.lib.rxbus2.RxBus;
import com.luck.picture.lib.tools.AttrsUtils;
import com.luck.picture.lib.tools.DateUtils;
import com.luck.picture.lib.tools.DoubleUtils;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropMulti;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureBaseActivity extends FragmentActivity {
    protected String cameraPath;
    protected int colorPrimary;
    protected int colorPrimaryDark;
    protected PictureDialog compressDialog;
    protected PictureSelectionConfig config;
    protected PictureDialog dialog;
    protected Context mContext;
    protected boolean numComplete;
    protected boolean openWhiteStatusBar;
    protected String originalPath;
    protected String outputCameraPath;
    protected List<LocalMedia> selectionMedias;

    public boolean isImmersive() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.app.Activity, com.luck.picture.lib.PictureBaseActivity] */
    public void immersive() {
        ImmersiveManage.immersiveAboveAPI23(this, this.colorPrimaryDark, this.colorPrimary, this.openWhiteStatusBar);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, android.support.v4.app.FragmentActivity, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.config = (PictureSelectionConfig) bundle.getParcelable(PictureConfig.EXTRA_CONFIG);
            this.cameraPath = bundle.getString("CameraPath");
            this.originalPath = bundle.getString(PictureConfig.BUNDLE_ORIGINAL_PATH);
        } else {
            this.config = PictureSelectionConfig.getInstance();
        }
        setTheme(this.config.themeStyleId);
        PictureBaseActivity.super.onCreate(bundle);
        this.mContext = this;
        initConfig();
        if (isImmersive()) {
            immersive();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.luck.picture.lib.PictureBaseActivity] */
    private void initConfig() {
        this.outputCameraPath = this.config.outputCameraPath;
        this.openWhiteStatusBar = AttrsUtils.getTypeValueBoolean(this, R.attr.picture_statusFontColor);
        this.numComplete = AttrsUtils.getTypeValueBoolean(this, R.attr.picture_style_numComplete);
        this.config.checkNumMode = AttrsUtils.getTypeValueBoolean(this, R.attr.picture_style_checkNumMode);
        this.colorPrimary = AttrsUtils.getTypeValueColor(this, R.attr.colorPrimary);
        this.colorPrimaryDark = AttrsUtils.getTypeValueColor(this, R.attr.colorPrimaryDark);
        this.selectionMedias = this.config.selectionMedias;
        if (this.selectionMedias == null) {
            this.selectionMedias = new ArrayList();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        PictureBaseActivity.super.onSaveInstanceState(bundle);
        bundle.putString("CameraPath", this.cameraPath);
        bundle.putString(PictureConfig.BUNDLE_ORIGINAL_PATH, this.originalPath);
        bundle.putParcelable(PictureConfig.EXTRA_CONFIG, this.config);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void startActivity(Class cls, Bundle bundle) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClass(this, cls);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void startActivity(Class cls, Bundle bundle, int i) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClass(this, cls);
            intent.putExtras(bundle);
            startActivityForResult(intent, i);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void showPleaseDialog() {
        if (!isFinishing()) {
            dismissDialog();
            this.dialog = new PictureDialog(this);
            this.dialog.show();
        }
    }

    /* access modifiers changed from: protected */
    public void dismissDialog() {
        try {
            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void showCompressDialog() {
        if (!isFinishing()) {
            dismissCompressDialog();
            this.compressDialog = new PictureDialog(this);
            this.compressDialog.show();
        }
    }

    /* access modifiers changed from: protected */
    public void dismissCompressDialog() {
        try {
            if (!isFinishing() && this.compressDialog != null && this.compressDialog.isShowing()) {
                this.compressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void compressImage(final List<LocalMedia> list) {
        showCompressDialog();
        if (this.config.synOrAsy) {
            Flowable.just(list).observeOn(Schedulers.io()).map(new Function<List<LocalMedia>, List<File>>() {
                public List<File> apply(@NonNull List<LocalMedia> list) throws Exception {
                    List<File> list2 = Luban.with(PictureBaseActivity.this.mContext).setTargetDir(PictureBaseActivity.this.config.compressSavePath).ignoreBy(PictureBaseActivity.this.config.minimumCompressSize).loadLocalMedia(list).get();
                    return list2 == null ? new ArrayList() : list2;
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<File>>() {
                public void accept(@NonNull List<File> list) throws Exception {
                    PictureBaseActivity.this.handleCompressCallBack(list, list);
                }
            });
        } else {
            Luban.with(this).loadLocalMedia(list).ignoreBy(this.config.minimumCompressSize).setTargetDir(this.config.compressSavePath).setCompressListener(new OnCompressListener() {
                public void onStart() {
                }

                public void onSuccess(List<LocalMedia> list) {
                    RxBus.getDefault().post(new EventEntity((int) PictureConfig.CLOSE_PREVIEW_FLAG));
                    PictureBaseActivity.this.onResult(list);
                }

                public void onError(Throwable th) {
                    RxBus.getDefault().post(new EventEntity((int) PictureConfig.CLOSE_PREVIEW_FLAG));
                    PictureBaseActivity.this.onResult(list);
                }
            }).launch();
        }
    }

    /* access modifiers changed from: private */
    public void handleCompressCallBack(List<LocalMedia> list, List<File> list2) {
        if (list2.size() == list.size()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String path = list2.get(i).getPath();
                LocalMedia localMedia = list.get(i);
                boolean z = !TextUtils.isEmpty(path) && PictureMimeType.isHttp(path);
                localMedia.setCompressed(!z);
                if (z) {
                    path = "";
                }
                localMedia.setCompressPath(path);
            }
        }
        RxBus.getDefault().post(new EventEntity((int) PictureConfig.CLOSE_PREVIEW_FLAG));
        onResult(list);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, android.app.Activity, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void startCrop(String str) {
        UCrop.Options options = new UCrop.Options();
        int typeValueColor = AttrsUtils.getTypeValueColor(this, R.attr.picture_crop_toolbar_bg);
        int typeValueColor2 = AttrsUtils.getTypeValueColor(this, R.attr.picture_crop_status_color);
        int typeValueColor3 = AttrsUtils.getTypeValueColor(this, R.attr.picture_crop_title_color);
        options.setToolbarColor(typeValueColor);
        options.setStatusBarColor(typeValueColor2);
        options.setToolbarWidgetColor(typeValueColor3);
        options.setCircleDimmedLayer(this.config.circleDimmedLayer);
        options.setShowCropFrame(this.config.showCropFrame);
        options.setShowCropGrid(this.config.showCropGrid);
        options.setDragFrameEnabled(this.config.isDragFrame);
        options.setScaleEnabled(this.config.scaleEnabled);
        options.setRotateEnabled(this.config.rotateEnabled);
        options.setCompressionQuality(this.config.cropCompressQuality);
        options.setHideBottomControls(this.config.hideBottomControls);
        options.setFreeStyleCropEnabled(this.config.freeStyleCropEnabled);
        boolean isHttp = PictureMimeType.isHttp(str);
        String lastImgType = PictureMimeType.getLastImgType(str);
        Uri parse = isHttp ? Uri.parse(str) : Uri.fromFile(new File(str));
        String diskCacheDir = PictureFileUtils.getDiskCacheDir(this);
        UCrop.of(parse, Uri.fromFile(new File(diskCacheDir, System.currentTimeMillis() + lastImgType))).withAspectRatio((float) this.config.aspect_ratio_x, (float) this.config.aspect_ratio_y).withMaxResultSize(this.config.cropWidth, this.config.cropHeight).withOptions(options).start(this);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, android.app.Activity, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void startCrop(ArrayList<String> arrayList) {
        UCropMulti.Options options = new UCropMulti.Options();
        int typeValueColor = AttrsUtils.getTypeValueColor(this, R.attr.picture_crop_toolbar_bg);
        int typeValueColor2 = AttrsUtils.getTypeValueColor(this, R.attr.picture_crop_status_color);
        int typeValueColor3 = AttrsUtils.getTypeValueColor(this, R.attr.picture_crop_title_color);
        options.setToolbarColor(typeValueColor);
        options.setStatusBarColor(typeValueColor2);
        options.setToolbarWidgetColor(typeValueColor3);
        options.setCircleDimmedLayer(this.config.circleDimmedLayer);
        options.setShowCropFrame(this.config.showCropFrame);
        options.setDragFrameEnabled(this.config.isDragFrame);
        options.setShowCropGrid(this.config.showCropGrid);
        options.setScaleEnabled(this.config.scaleEnabled);
        options.setRotateEnabled(this.config.rotateEnabled);
        options.setHideBottomControls(true);
        options.setCompressionQuality(this.config.cropCompressQuality);
        options.setCutListData(arrayList);
        options.setFreeStyleCropEnabled(this.config.freeStyleCropEnabled);
        String str = arrayList.size() > 0 ? arrayList.get(0) : "";
        boolean isHttp = PictureMimeType.isHttp(str);
        String lastImgType = PictureMimeType.getLastImgType(str);
        Uri parse = isHttp ? Uri.parse(str) : Uri.fromFile(new File(str));
        String diskCacheDir = PictureFileUtils.getDiskCacheDir(this);
        UCropMulti.of(parse, Uri.fromFile(new File(diskCacheDir, System.currentTimeMillis() + lastImgType))).withAspectRatio((float) this.config.aspect_ratio_x, (float) this.config.aspect_ratio_y).withMaxResultSize(this.config.cropWidth, this.config.cropHeight).withOptions(options).start(this);
    }

    /* access modifiers changed from: protected */
    public void rotateImage(int i, File file) {
        if (i > 0) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                PictureFileUtils.saveBitmapFile(PictureFileUtils.rotaingImageView(i, BitmapFactory.decodeFile(file.getAbsolutePath(), options)), file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void handlerResult(List<LocalMedia> list) {
        if (this.config.isCompress) {
            compressImage(list);
        } else {
            onResult(list);
        }
    }

    /* access modifiers changed from: protected */
    public void createNewFolder(List<LocalMediaFolder> list) {
        int i;
        if (list.size() == 0) {
            LocalMediaFolder localMediaFolder = new LocalMediaFolder();
            if (this.config.mimeType == PictureMimeType.ofAudio()) {
                i = R.string.picture_all_audio;
            } else {
                i = R.string.picture_camera_roll;
            }
            localMediaFolder.setName(getString(i));
            localMediaFolder.setPath("");
            localMediaFolder.setFirstImagePath("");
            list.add(localMediaFolder);
        }
    }

    /* access modifiers changed from: protected */
    public LocalMediaFolder getImageFolder(String str, List<LocalMediaFolder> list) {
        File parentFile = new File(str).getParentFile();
        for (LocalMediaFolder next : list) {
            if (next.getName().equals(parentFile.getName())) {
                return next;
            }
        }
        LocalMediaFolder localMediaFolder = new LocalMediaFolder();
        localMediaFolder.setName(parentFile.getName());
        localMediaFolder.setPath(parentFile.getAbsolutePath());
        localMediaFolder.setFirstImagePath(str);
        list.add(localMediaFolder);
        return localMediaFolder;
    }

    /* access modifiers changed from: protected */
    public void onResult(List<LocalMedia> list) {
        Log.e("wq", "onResult images size == " + list.size());
        dismissCompressDialog();
        if (this.config.camera && this.config.selectionMode == 2 && this.selectionMedias != null) {
            list.addAll(list.size() > 0 ? list.size() - 1 : 0, this.selectionMedias);
        }
        setResult(-1, PictureSelector.putIntentResult(list));
        closeActivity();
    }

    /* access modifiers changed from: protected */
    public void closeActivity() {
        finish();
        if (this.config.camera) {
            overridePendingTransition(0, R.anim.fade_out);
        } else {
            overridePendingTransition(0, R.anim.a3);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        PictureBaseActivity.super.onDestroy();
        dismissCompressDialog();
        dismissDialog();
    }

    /* access modifiers changed from: protected */
    public int getLastImageId(boolean z) {
        int i;
        int i2;
        try {
            String dCIMCameraPath = PictureFileUtils.getDCIMCameraPath();
            String str = "_data like ?";
            Cursor query = getContentResolver().query(z ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[]) null, str, new String[]{dCIMCameraPath + "%"}, "_id DESC");
            if (!query.moveToFirst()) {
                return -1;
            }
            if (z) {
                i = query.getColumnIndex("_id");
            } else {
                i = query.getColumnIndex("_id");
            }
            int i3 = query.getInt(i);
            if (z) {
                i2 = query.getColumnIndex("duration");
            } else {
                i2 = query.getColumnIndex("date_added");
            }
            int dateDiffer = DateUtils.dateDiffer(query.getLong(i2));
            query.close();
            if (dateDiffer <= 30) {
                return i3;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public void removeImage(int i, boolean z) {
        try {
            getContentResolver().delete(z ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{Long.toString((long) i)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public String getAudioPath(Intent intent) {
        boolean z = Build.VERSION.SDK_INT <= 19;
        if (intent == null || this.config.mimeType != PictureMimeType.ofAudio()) {
            return "";
        }
        try {
            Uri data = intent.getData();
            if (z) {
                return data.getPath();
            }
            return getAudioFilePathFromUri(data);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public String getAudioFilePathFromUri(Uri uri) {
        try {
            Cursor query = getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            query.moveToFirst();
            return query.getString(query.getColumnIndex("_data"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
