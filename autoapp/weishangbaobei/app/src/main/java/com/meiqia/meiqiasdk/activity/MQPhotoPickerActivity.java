package com.meiqia.meiqiasdk.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.model.ImageFolderModel;
import com.meiqia.meiqiasdk.pw.MQPhotoFolderPw;
import com.meiqia.meiqiasdk.util.MQAsyncTask;
import com.meiqia.meiqiasdk.util.MQImageCaptureManager;
import com.meiqia.meiqiasdk.util.MQLoadPhotoTask;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQImageView;
import com.stub.StubApp;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class MQPhotoPickerActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, MQAsyncTask.Callback<ArrayList<ImageFolderModel>> {
    private static final String EXTRA_IMAGE_DIR = "EXTRA_IMAGE_DIR";
    private static final String EXTRA_MAX_CHOOSE_COUNT = "EXTRA_MAX_CHOOSE_COUNT";
    private static final String EXTRA_SELECTED_IMAGES = "EXTRA_SELECTED_IMAGES";
    private static final String EXTRA_TOP_RIGHT_BTN_TEXT = "EXTRA_TOP_RIGHT_BTN_TEXT";
    private static final int REQUEST_CODE_PREVIEW = 2;
    private static final int REQUEST_CODE_TAKE_PHOTO = 1;
    public static ArrayList<String> sPreviewImages;
    /* access modifiers changed from: private */
    public ImageView mArrowIv;
    private GridView mContentGv;
    /* access modifiers changed from: private */
    public ImageFolderModel mCurrentImageFolderModel;
    private MQImageCaptureManager mImageCaptureManager;
    private ArrayList<ImageFolderModel> mImageFolderModels;
    private long mLastShowPhotoFolderTime;
    private MQLoadPhotoTask mLoadPhotoTask;
    private Dialog mLoadingDialog;
    /* access modifiers changed from: private */
    public int mMaxChooseCount = 1;
    private MQPhotoFolderPw mPhotoFolderPw;
    /* access modifiers changed from: private */
    public PicAdapter mPicAdapter;
    private TextView mSubmitTv;
    private boolean mTakePhotoEnabled;
    private RelativeLayout mTitleRl;
    private TextView mTitleTv;
    private String mTopRightBtnText;

    static {
        StubApp.interface11(6105);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public static Intent newIntent(Context context, File file, int i, ArrayList<String> arrayList, String str) {
        Intent intent = new Intent(context, MQPhotoPickerActivity.class);
        intent.putExtra(EXTRA_IMAGE_DIR, file);
        intent.putExtra(EXTRA_MAX_CHOOSE_COUNT, i);
        intent.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, arrayList);
        intent.putExtra(EXTRA_TOP_RIGHT_BTN_TEXT, str);
        return intent;
    }

    public static ArrayList<String> getSelectedImages(Intent intent) {
        return intent.getStringArrayListExtra(EXTRA_SELECTED_IMAGES);
    }

    private void initView() {
        setContentView(R.layout.mq_activity_photo_picker);
        this.mTitleRl = (RelativeLayout) findViewById(R.id.title_rl);
        this.mTitleTv = (TextView) findViewById(R.id.title_tv);
        this.mArrowIv = (ImageView) findViewById(R.id.arrow_iv);
        this.mSubmitTv = (TextView) findViewById(R.id.submit_tv);
        this.mContentGv = (GridView) findViewById(R.id.content_gv);
    }

    private void initListener() {
        findViewById(R.id.back_iv).setOnClickListener(this);
        findViewById(R.id.folder_ll).setOnClickListener(this);
        this.mSubmitTv.setOnClickListener(this);
        this.mContentGv.setOnItemClickListener(this);
    }

    private void processLogic(Bundle bundle) {
        File file = (File) getIntent().getSerializableExtra(EXTRA_IMAGE_DIR);
        if (file != null) {
            this.mTakePhotoEnabled = true;
            this.mImageCaptureManager = new MQImageCaptureManager(this, file);
        }
        this.mMaxChooseCount = getIntent().getIntExtra(EXTRA_MAX_CHOOSE_COUNT, 1);
        if (this.mMaxChooseCount < 1) {
            this.mMaxChooseCount = 1;
        }
        this.mTopRightBtnText = getIntent().getStringExtra(EXTRA_TOP_RIGHT_BTN_TEXT);
        this.mPicAdapter = new PicAdapter();
        this.mPicAdapter.setSelectedImages(getIntent().getStringArrayListExtra(EXTRA_SELECTED_IMAGES));
        this.mContentGv.setAdapter(this.mPicAdapter);
        renderTopRightBtn();
        this.mTitleTv.setText(R.string.mq_all_image);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.mLoadPhotoTask == null) {
            showLoadingDialog();
            this.mLoadPhotoTask = new MQLoadPhotoTask(this, this, this.mTakePhotoEnabled).perform();
        }
    }

    private void showLoadingDialog() {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = new Dialog(this, R.style.MQDialog);
            this.mLoadingDialog.setContentView(R.layout.mq_dialog_loading_photopicker);
            this.mLoadingDialog.setCancelable(false);
        }
        this.mLoadingDialog.show();
    }

    private void dismissLoadingDialog() {
        if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.back_iv) {
            onBackPressed();
        } else if (view.getId() == R.id.folder_ll && System.currentTimeMillis() - this.mLastShowPhotoFolderTime > 300) {
            showPhotoFolderPw();
            this.mLastShowPhotoFolderTime = System.currentTimeMillis();
        } else if (view.getId() == R.id.submit_tv) {
            returnSelectedImages(this.mPicAdapter.getSelectedImages());
        }
    }

    private void returnSelectedImages(ArrayList<String> arrayList) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(EXTRA_SELECTED_IMAGES, arrayList);
        setResult(-1, intent);
        finish();
    }

    private void showPhotoFolderPw() {
        if (this.mPhotoFolderPw == null) {
            this.mPhotoFolderPw = new MQPhotoFolderPw(this, this.mTitleRl, new MQPhotoFolderPw.Callback() {
                public void onSelectedFolder(int i) {
                    MQPhotoPickerActivity.this.reloadPhotos(i);
                }

                public void executeDismissAnim() {
                    ViewCompat.animate(MQPhotoPickerActivity.this.mArrowIv).setDuration(300).rotation(CropImageView.DEFAULT_ASPECT_RATIO).start();
                }
            });
        }
        this.mPhotoFolderPw.setDatas(this.mImageFolderModels);
        this.mPhotoFolderPw.show();
        ViewCompat.animate(this.mArrowIv).setDuration(300).rotation(-180.0f).start();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mMaxChooseCount == 1) {
            if (!this.mCurrentImageFolderModel.isTakePhotoEnabled() || i != 0) {
                changeToPreview(i);
            } else {
                takePhoto();
            }
        } else if (!this.mCurrentImageFolderModel.isTakePhotoEnabled() || i != 0) {
            changeToPreview(i);
        } else if (this.mPicAdapter.getSelectedCount() == this.mMaxChooseCount) {
            toastMaxCountTip();
        } else {
            takePhoto();
        }
    }

    private void changeToPreview(int i) {
        if (this.mCurrentImageFolderModel.isTakePhotoEnabled()) {
            i--;
        }
        int i2 = i;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                ArrayList<Uri> dataUri = this.mPicAdapter.getDataUri();
                ArrayList arrayList = new ArrayList();
                Iterator<Uri> it = dataUri.iterator();
                while (it.hasNext()) {
                    arrayList.add(MQUtils.getRealFilePath(this, it.next()));
                }
                this.mPicAdapter.setData(arrayList);
            }
            sPreviewImages = this.mPicAdapter.getData();
            startActivityForResult(MQPhotoPickerPreviewActivity.newIntent(this, this.mMaxChooseCount, this.mPicAdapter.getSelectedImages(), i2, this.mTopRightBtnText, false), 2);
        } catch (Exception unused) {
            MQUtils.show((Context) this, R.string.mq_photo_not_support);
        }
    }

    /* access modifiers changed from: private */
    public void toastMaxCountTip() {
        MQUtils.show((Context) this, (CharSequence) getString(R.string.mq_toast_photo_picker_max, new Object[]{Integer.valueOf(this.mMaxChooseCount)}));
    }

    private void takePhoto() {
        try {
            startActivityForResult(this.mImageCaptureManager.getTakePictureIntent(), 1);
        } catch (Exception unused) {
            MQUtils.show((Context) this, R.string.mq_photo_not_support);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 1) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(this.mImageCaptureManager.getCurrentPhotoPath());
                try {
                    sPreviewImages = arrayList;
                    startActivityForResult(MQPhotoPickerPreviewActivity.newIntent(this, 1, arrayList, 0, this.mTopRightBtnText, true), 2);
                } catch (Exception unused) {
                    MQUtils.show((Context) this, R.string.mq_photo_not_support);
                }
            } else if (i == 2) {
                if (MQPhotoPickerPreviewActivity.getIsFromTakePhoto(intent)) {
                    this.mImageCaptureManager.refreshGallery();
                }
                returnSelectedImages(MQPhotoPickerPreviewActivity.getSelectedImages(intent));
            }
        } else if (i2 != 0 || i != 2) {
        } else {
            if (MQPhotoPickerPreviewActivity.getIsFromTakePhoto(intent)) {
                this.mImageCaptureManager.deletePhotoFile();
                return;
            }
            this.mPicAdapter.setSelectedImages(MQPhotoPickerPreviewActivity.getSelectedImages(intent));
            renderTopRightBtn();
        }
    }

    /* access modifiers changed from: private */
    public void renderTopRightBtn() {
        if (this.mPicAdapter.getSelectedCount() == 0) {
            this.mSubmitTv.setEnabled(false);
            this.mSubmitTv.setText(this.mTopRightBtnText);
            return;
        }
        this.mSubmitTv.setEnabled(true);
        TextView textView = this.mSubmitTv;
        textView.setText(this.mTopRightBtnText + "(" + this.mPicAdapter.getSelectedCount() + "/" + this.mMaxChooseCount + ")");
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.mTakePhotoEnabled) {
            this.mImageCaptureManager.onSaveInstanceState(bundle);
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        if (this.mTakePhotoEnabled) {
            this.mImageCaptureManager.onRestoreInstanceState(bundle);
        }
        super.onRestoreInstanceState(bundle);
    }

    /* access modifiers changed from: private */
    public void reloadPhotos(int i) {
        if (i < this.mImageFolderModels.size()) {
            this.mCurrentImageFolderModel = this.mImageFolderModels.get(i);
            this.mTitleTv.setText(this.mCurrentImageFolderModel.name);
            if (Build.VERSION.SDK_INT >= 29) {
                this.mPicAdapter.setDataUri(this.mCurrentImageFolderModel.getImageUri());
            } else {
                this.mPicAdapter.setData(this.mCurrentImageFolderModel.getImages());
            }
        }
    }

    public void onPostExecute(ArrayList<ImageFolderModel> arrayList) {
        dismissLoadingDialog();
        this.mImageFolderModels = arrayList;
        reloadPhotos(this.mPhotoFolderPw == null ? 0 : this.mPhotoFolderPw.getCurrentPosition());
    }

    public void onTaskCancelled() {
        dismissLoadingDialog();
        this.mLoadPhotoTask = null;
    }

    private void cancelLoadPhotoTask() {
        if (this.mLoadPhotoTask != null) {
            this.mLoadPhotoTask.cancelTask();
            this.mLoadPhotoTask = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        dismissLoadingDialog();
        cancelLoadPhotoTask();
        sPreviewImages = null;
        super.onDestroy();
    }

    private class PicAdapter extends BaseAdapter {
        private ArrayList<String> mData;
        /* access modifiers changed from: private */
        public ArrayList<Uri> mDataUri;
        private int mImageHeight;
        private int mImageWidth;
        private ArrayList<String> mSelectedImages = new ArrayList<>();

        public long getItemId(int i) {
            return 0;
        }

        public PicAdapter() {
            if (Build.VERSION.SDK_INT >= 29) {
                this.mDataUri = new ArrayList<>();
            } else {
                this.mData = new ArrayList<>();
            }
            this.mImageWidth = MQUtils.getScreenWidth(StubApp.getOrigApplicationContext(MQPhotoPickerActivity.this.getApplicationContext())) / 10;
            this.mImageHeight = this.mImageWidth;
        }

        public int getCount() {
            if (Build.VERSION.SDK_INT >= 29) {
                return this.mDataUri.size();
            }
            return this.mData.size();
        }

        public String getItem(int i) {
            return this.mData.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            PicViewHolder picViewHolder;
            String str;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mq_item_square_image, viewGroup, false);
                picViewHolder = new PicViewHolder();
                picViewHolder.photoIv = (MQImageView) view.findViewById(R.id.photo_iv);
                picViewHolder.tipTv = (TextView) view.findViewById(R.id.tip_tv);
                picViewHolder.flagIv = (ImageView) view.findViewById(R.id.flag_iv);
                view.setTag(picViewHolder);
            } else {
                picViewHolder = (PicViewHolder) view.getTag();
            }
            if (Build.VERSION.SDK_INT >= 29) {
                str = MQUtils.getRealFilePath(MQPhotoPickerActivity.this, getDataUri().get(i));
            } else {
                str = getItem(i);
            }
            if (!MQPhotoPickerActivity.this.mCurrentImageFolderModel.isTakePhotoEnabled() || i != 0) {
                picViewHolder.tipTv.setVisibility(4);
                picViewHolder.photoIv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                MQImage.displayImage(MQPhotoPickerActivity.this, picViewHolder.photoIv, str, R.drawable.mq_ic_holder_dark, R.drawable.mq_ic_holder_dark, this.mImageWidth, this.mImageHeight, (MQImageLoader.MQDisplayImageListener) null);
                picViewHolder.flagIv.setVisibility(0);
                if (this.mSelectedImages.contains(str)) {
                    picViewHolder.flagIv.setImageResource(R.drawable.mq_ic_cb_checked);
                    picViewHolder.photoIv.setColorFilter(MQPhotoPickerActivity.this.getResources().getColor(R.color.mq_photo_selected_color));
                } else {
                    picViewHolder.flagIv.setImageResource(R.drawable.mq_ic_cb_normal);
                    picViewHolder.photoIv.setColorFilter((ColorFilter) null);
                }
                setFlagClickListener(picViewHolder.flagIv, i);
            } else {
                picViewHolder.tipTv.setVisibility(0);
                picViewHolder.photoIv.setScaleType(ImageView.ScaleType.CENTER);
                picViewHolder.photoIv.setImageResource(R.drawable.mq_ic_gallery_camera);
                picViewHolder.flagIv.setVisibility(4);
                picViewHolder.photoIv.setColorFilter((ColorFilter) null);
            }
            return view;
        }

        private void setFlagClickListener(ImageView imageView, final int i) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String str;
                    if (Build.VERSION.SDK_INT >= 29) {
                        str = MQUtils.getRealFilePath(MQPhotoPickerActivity.this, (Uri) PicAdapter.this.mDataUri.get(i));
                    } else {
                        str = MQPhotoPickerActivity.this.mPicAdapter.getItem(i);
                    }
                    if (MQPhotoPickerActivity.this.mMaxChooseCount == 1) {
                        if (MQPhotoPickerActivity.this.mPicAdapter.getSelectedCount() <= 0) {
                            MQPhotoPickerActivity.this.mPicAdapter.getSelectedImages().add(str);
                        } else if (!TextUtils.equals(MQPhotoPickerActivity.this.mPicAdapter.getSelectedImages().remove(0), str)) {
                            MQPhotoPickerActivity.this.mPicAdapter.getSelectedImages().add(str);
                        }
                        PicAdapter.this.notifyDataSetChanged();
                        MQPhotoPickerActivity.this.renderTopRightBtn();
                    } else if (MQPhotoPickerActivity.this.mPicAdapter.getSelectedImages().contains(str) || MQPhotoPickerActivity.this.mPicAdapter.getSelectedCount() != MQPhotoPickerActivity.this.mMaxChooseCount) {
                        if (MQPhotoPickerActivity.this.mPicAdapter.getSelectedImages().contains(str)) {
                            MQPhotoPickerActivity.this.mPicAdapter.getSelectedImages().remove(str);
                        } else {
                            MQPhotoPickerActivity.this.mPicAdapter.getSelectedImages().add(str);
                        }
                        PicAdapter.this.notifyDataSetChanged();
                        MQPhotoPickerActivity.this.renderTopRightBtn();
                    } else {
                        MQPhotoPickerActivity.this.toastMaxCountTip();
                    }
                }
            });
        }

        public void setData(ArrayList<String> arrayList) {
            if (arrayList != null) {
                this.mData = arrayList;
            } else {
                this.mData.clear();
            }
            notifyDataSetChanged();
        }

        public void setDataUri(ArrayList<Uri> arrayList) {
            if (arrayList != null) {
                this.mDataUri = arrayList;
            } else {
                this.mDataUri.clear();
            }
            notifyDataSetChanged();
        }

        public ArrayList<String> getData() {
            return this.mData;
        }

        public ArrayList<Uri> getDataUri() {
            return this.mDataUri;
        }

        public void setSelectedImages(ArrayList<String> arrayList) {
            if (arrayList != null) {
                this.mSelectedImages = arrayList;
            }
            notifyDataSetChanged();
        }

        public ArrayList<String> getSelectedImages() {
            return this.mSelectedImages;
        }

        public int getSelectedCount() {
            return this.mSelectedImages.size();
        }
    }

    private class PicViewHolder {
        public ImageView flagIv;
        public MQImageView photoIv;
        public TextView tipTv;

        private PicViewHolder() {
        }
    }
}
