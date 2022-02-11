package com.yongchun.library.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stub.StubApp;
import com.yongchun.library.R;
import com.yongchun.library.adapter.ImageFolderAdapter;
import com.yongchun.library.adapter.ImageListAdapter;
import com.yongchun.library.model.LocalMedia;
import com.yongchun.library.utils.FileUtils;
import com.yongchun.library.utils.GridSpacingItemDecoration;
import com.yongchun.library.utils.ScreenUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageSelectorActivity extends AppCompatActivity {
    public static final String BUNDLE_CAMERA_PATH = "CameraPath";
    public static final String EXTRA_ENABLE_CROP = "EnableCrop";
    public static final String EXTRA_ENABLE_PREVIEW = "EnablePreview";
    public static final String EXTRA_MAX_SELECT_NUM = "MaxSelectNum";
    public static final String EXTRA_SELECT_MODE = "SelectMode";
    public static final String EXTRA_SHOW_CAMERA = "ShowCamera";
    public static final String EXTRA_SHOW_TYPE = "showType";
    public static final int MODE_MULTIPLE = 1;
    public static final int MODE_SINGLE = 2;
    public static final int REQUEST_CAMERA = 67;
    public static final int REQUEST_IMAGE = 66;
    public static final String REQUEST_OUTPUT = "outputList";
    public static List<LocalMedia> previewImages = new ArrayList();
    public static List<LocalMedia> selectImages = new ArrayList();
    private String cameraPath;
    /* access modifiers changed from: private */
    public TextView doneText;
    /* access modifiers changed from: private */
    public boolean enableCrop = false;
    /* access modifiers changed from: private */
    public boolean enablePreview = true;
    private LinearLayout folderLayout;
    /* access modifiers changed from: private */
    public TextView folderName;
    /* access modifiers changed from: private */
    public FolderWindow folderWindow;
    /* access modifiers changed from: private */
    public ImageListAdapter imageAdapter;
    /* access modifiers changed from: private */
    public int maxSelectNum = 9;
    /* access modifiers changed from: private */
    public TextView preText;
    /* access modifiers changed from: private */
    public TextView previewText;
    private RecyclerView recyclerView;
    private int selectMode = 1;
    private boolean showCamera = true;
    private int showType = 1;
    private int spanCount = 4;
    /* access modifiers changed from: private */
    public Toolbar toolbar;

    static {
        StubApp.interface11(12667);
    }

    public static void start(Activity activity, int i, int i2, boolean z, boolean z2, boolean z3) {
        previewImages = new ArrayList();
        selectImages = new ArrayList();
        Intent intent = new Intent(activity, ImageSelectorActivity.class);
        intent.putExtra(EXTRA_MAX_SELECT_NUM, i);
        intent.putExtra(EXTRA_SELECT_MODE, i2);
        intent.putExtra(EXTRA_SHOW_CAMERA, z);
        intent.putExtra(EXTRA_ENABLE_PREVIEW, z2);
        intent.putExtra(EXTRA_ENABLE_CROP, z3);
        intent.putExtra(EXTRA_SHOW_TYPE, 1);
        activity.startActivityForResult(intent, 66);
    }

    public static void start(Fragment fragment, int i, int i2, boolean z, boolean z2, boolean z3) {
        previewImages = new ArrayList();
        selectImages = new ArrayList();
        Intent intent = new Intent(fragment.getActivity(), ImageSelectorActivity.class);
        intent.putExtra(EXTRA_MAX_SELECT_NUM, i);
        intent.putExtra(EXTRA_SELECT_MODE, i2);
        intent.putExtra(EXTRA_SHOW_CAMERA, z);
        intent.putExtra(EXTRA_ENABLE_PREVIEW, z2);
        intent.putExtra(EXTRA_ENABLE_CROP, z3);
        intent.putExtra(EXTRA_SHOW_TYPE, 1);
        fragment.startActivityForResult(intent, 66);
    }

    public static void startImageVideo(Activity activity, int i, int i2, boolean z, boolean z2, boolean z3) {
        previewImages = new ArrayList();
        selectImages = new ArrayList();
        Intent intent = new Intent(activity, ImageSelectorActivity.class);
        intent.putExtra(EXTRA_MAX_SELECT_NUM, i);
        intent.putExtra(EXTRA_SELECT_MODE, i2);
        intent.putExtra(EXTRA_SHOW_CAMERA, z);
        intent.putExtra(EXTRA_ENABLE_PREVIEW, z2);
        intent.putExtra(EXTRA_ENABLE_CROP, z3);
        intent.putExtra(EXTRA_SHOW_TYPE, 3);
        activity.startActivityForResult(intent, 66);
    }

    public static void startImageVideo(Fragment fragment, int i, int i2, boolean z, boolean z2, boolean z3) {
        previewImages = new ArrayList();
        selectImages = new ArrayList();
        Intent intent = new Intent(fragment.getActivity(), ImageSelectorActivity.class);
        intent.putExtra(EXTRA_MAX_SELECT_NUM, i);
        intent.putExtra(EXTRA_SELECT_MODE, i2);
        intent.putExtra(EXTRA_SHOW_CAMERA, z);
        intent.putExtra(EXTRA_ENABLE_PREVIEW, z2);
        intent.putExtra(EXTRA_ENABLE_CROP, z3);
        intent.putExtra(EXTRA_SHOW_TYPE, 3);
        fragment.startActivityForResult(intent, 66);
    }

    public static void startVideo(Activity activity, int i, int i2, boolean z, boolean z2, boolean z3) {
        previewImages = new ArrayList();
        selectImages = new ArrayList();
        Intent intent = new Intent(activity, ImageSelectorActivity.class);
        intent.putExtra(EXTRA_MAX_SELECT_NUM, i);
        intent.putExtra(EXTRA_SELECT_MODE, i2);
        intent.putExtra(EXTRA_SHOW_CAMERA, z);
        intent.putExtra(EXTRA_ENABLE_PREVIEW, z2);
        intent.putExtra(EXTRA_ENABLE_CROP, z3);
        intent.putExtra(EXTRA_SHOW_TYPE, 2);
        activity.startActivityForResult(intent, 66);
    }

    public static void startVideo(Fragment fragment, int i, int i2, boolean z, boolean z2, boolean z3) {
        previewImages = new ArrayList();
        selectImages = new ArrayList();
        Intent intent = new Intent(fragment.getActivity(), ImageSelectorActivity.class);
        intent.putExtra(EXTRA_MAX_SELECT_NUM, i);
        intent.putExtra(EXTRA_SELECT_MODE, i2);
        intent.putExtra(EXTRA_SHOW_CAMERA, z);
        intent.putExtra(EXTRA_ENABLE_PREVIEW, z2);
        intent.putExtra(EXTRA_ENABLE_CROP, z3);
        intent.putExtra(EXTRA_SHOW_TYPE, 2);
        fragment.startActivityForResult(intent, 66);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.yongchun.library.view.ImageSelectorActivity, android.content.Context] */
    public void initView() {
        int i = 8;
        this.folderWindow = new FolderWindow(this);
        this.toolbar = findViewById(R.id.toolbar);
        if (this.showType == 1) {
            this.toolbar.setTitle(R.string.picture);
        } else if (this.showType == 2) {
            this.toolbar.setTitle(R.string.videos);
        } else {
            this.toolbar.setTitle(R.string.take_picture_video);
        }
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationIcon(R.mipmap.ic_back);
        this.preText = (TextView) findViewById(R.id.preText);
        this.doneText = (TextView) findViewById(R.id.done_text);
        this.doneText.setVisibility(this.selectMode == 1 ? 0 : 8);
        this.previewText = (TextView) findViewById(R.id.preview_text);
        TextView textView = this.previewText;
        if (this.enablePreview) {
            i = 0;
        }
        textView.setVisibility(i);
        this.folderLayout = (LinearLayout) findViewById(R.id.folder_layout);
        this.folderName = (TextView) findViewById(R.id.folder_name);
        if (this.showType == 1) {
            this.folderName.setText(getString(R.string.all_image));
        } else if (this.showType == 2) {
            this.folderName.setText(getString(R.string.all_videos));
        } else {
            this.folderName.setText(getString(R.string.all_images_video));
        }
        this.recyclerView = findViewById(R.id.folder_list);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(this.spanCount, ScreenUtils.dip2px(this, 2.0f), false));
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, this.spanCount));
        this.imageAdapter = new ImageListAdapter(this, this.maxSelectNum, this.selectMode, this.showCamera, this.enablePreview);
        this.recyclerView.setAdapter(this.imageAdapter);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            return;
        }
        if (i == 67) {
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(this.cameraPath))));
            if (this.enableCrop) {
                startCrop(this.cameraPath);
            } else {
                onSelectDone(this.cameraPath);
            }
        } else if (i == 68) {
            boolean booleanExtra = intent.getBooleanExtra(ImagePreviewActivity.OUTPUT_ISDONE, false);
            List<LocalMedia> list = selectImages;
            if (booleanExtra) {
                onSelectDone(list);
            } else {
                this.imageAdapter.bindSelectImages(list);
            }
        } else if (i == 69) {
            onSelectDone(intent.getStringExtra(ImageCropActivity.OUTPUT_PATH));
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void onResult(ArrayList<String> arrayList) {
        setResult(-1, new Intent().putStringArrayListExtra("outputList", arrayList));
        finish();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"MissingSuperCall"})
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("CameraPath", this.cameraPath);
    }

    public void onSelectDone(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        onResult(arrayList);
    }

    public void onSelectDone(List<LocalMedia> list) {
        ArrayList arrayList = new ArrayList();
        for (LocalMedia path : list) {
            arrayList.add(path.getPath());
        }
        onResult(arrayList);
    }

    public void registerListener() {
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageSelectorActivity.this.finish();
            }
        });
        this.folderLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ImageSelectorActivity.this.folderWindow.isShowing()) {
                    ImageSelectorActivity.this.folderWindow.dismiss();
                } else {
                    ImageSelectorActivity.this.folderWindow.showAsDropDown(ImageSelectorActivity.this.toolbar);
                }
            }
        });
        this.imageAdapter.setOnImageSelectChangedListener(new ImageListAdapter.OnImageSelectChangedListener() {
            public void onChange(List<LocalMedia> list) {
                boolean z = list.size() != 0;
                ImageSelectorActivity.this.doneText.setEnabled(z);
                ImageSelectorActivity.this.previewText.setEnabled(z);
                if (z) {
                    ImageSelectorActivity.this.doneText.setText(ImageSelectorActivity.this.getString(R.string.done_num, new Object[]{Integer.valueOf(list.size()), Integer.valueOf(ImageSelectorActivity.this.maxSelectNum)}));
                    ImageSelectorActivity.this.previewText.setText(ImageSelectorActivity.this.getString(R.string.preview_num, new Object[]{Integer.valueOf(list.size())}));
                    return;
                }
                ImageSelectorActivity.this.doneText.setText(R.string.done);
                ImageSelectorActivity.this.previewText.setText(R.string.preview);
            }

            public void onPictureClick(LocalMedia localMedia, int i) {
                if (ImageSelectorActivity.this.enablePreview) {
                    ImageSelectorActivity.this.startPreview(ImageSelectorActivity.this.imageAdapter.getImages(), i);
                } else if (ImageSelectorActivity.this.enableCrop) {
                    ImageSelectorActivity.this.startCrop(localMedia.getPath());
                } else {
                    ImageSelectorActivity.this.onSelectDone(localMedia.getPath());
                }
            }

            public void onTakePhoto() {
                ImageSelectorActivity.this.startCamera();
            }
        });
        this.doneText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageSelectorActivity.this.onSelectDone(ImageSelectorActivity.this.imageAdapter.getSelectedImages());
            }
        });
        this.folderWindow.setOnItemClickListener(new ImageFolderAdapter.OnItemClickListener() {
            public void onItemClick(String str, List<LocalMedia> list) {
                ImageSelectorActivity.this.folderWindow.dismiss();
                ImageSelectorActivity.this.imageAdapter.bindImages(list);
                ImageSelectorActivity.this.folderName.setText(str);
            }
        });
        this.previewText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageSelectorActivity.this.startPreview(ImageSelectorActivity.this.imageAdapter.getSelectedImages(), 0);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.yongchun.library.view.ImageSelectorActivity, android.content.Context] */
    public void startCamera() {
        if (Build.VERSION.SDK_INT >= 24) {
            File createCameraFile = FileUtils.createCameraFile(this);
            this.cameraPath = createCameraFile.getAbsolutePath();
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", FileProvider.getUriForFile(this, "com.example.wx.assistant.select.img.fileprovider", createCameraFile));
            startActivityForResult(intent, 67);
            return;
        }
        Intent intent2 = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent2.resolveActivity(getPackageManager()) != null) {
            File createCameraFile2 = FileUtils.createCameraFile(this);
            this.cameraPath = createCameraFile2.getAbsolutePath();
            intent2.putExtra("output", Uri.fromFile(createCameraFile2));
            startActivityForResult(intent2, 67);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.yongchun.library.view.ImageSelectorActivity, android.app.Activity] */
    public void startCrop(String str) {
        ImageCropActivity.startCrop((Activity) this, str);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.yongchun.library.view.ImageSelectorActivity, android.app.Activity] */
    public void startPreview(List<LocalMedia> list, int i) {
        previewImages.clear();
        previewImages.addAll(list);
        selectImages.clear();
        selectImages.addAll(this.imageAdapter.getSelectedImages());
        ImagePreviewActivity.startPreview(this, this.maxSelectNum, i);
    }
}
