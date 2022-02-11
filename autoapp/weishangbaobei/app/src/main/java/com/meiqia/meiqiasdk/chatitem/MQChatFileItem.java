package com.meiqia.meiqiasdk.chatitem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.content.FileProvider;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.callback.OnDownloadFileCallback;
import com.meiqia.meiqiasdk.model.FileMessage;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQTimeUtils;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.CircularProgressBar;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;
import com.yalantis.ucrop.view.CropImageView;
import gdut.bsx.share2.ShareContentType;
import java.io.File;
import java.text.DecimalFormat;
import org.json.JSONObject;

public class MQChatFileItem extends MQBaseCustomCompositeView implements View.OnTouchListener {
    /* access modifiers changed from: private */
    public boolean isCancel;
    /* access modifiers changed from: private */
    public Callback mCallback;
    /* access modifiers changed from: private */
    public FileMessage mFileMessage;
    private CircularProgressBar mProgressBar;
    private View mRightIv;
    private TextView mSubTitleTv;
    private TextView mTitleTv;
    private View root;

    public interface Callback {
        void notifyDataSetChanged();

        void onFileMessageDownloadFailure(FileMessage fileMessage, int i, String str);

        void onFileMessageExpired(FileMessage fileMessage);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    public MQChatFileItem(Context context) {
        super(context);
    }

    public MQChatFileItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MQChatFileItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_file_layout;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.root = findViewById(R.id.root);
        this.mProgressBar = (CircularProgressBar) findViewById(R.id.progressbar);
        this.mTitleTv = (TextView) findViewById(R.id.mq_file_title_tv);
        this.mSubTitleTv = (TextView) findViewById(R.id.mq_file_sub_title_tv);
        this.mRightIv = findViewById(R.id.mq_right_iv);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.root.setOnClickListener(this);
        this.mRightIv.setOnClickListener(this);
        this.mProgressBar.setOnTouchListener(this);
    }

    public void onClick(View view) {
        if (this.mFileMessage != null) {
            int id = view.getId();
            if (id == R.id.mq_right_iv) {
                this.root.performClick();
            } else if (id == R.id.progressbar) {
                cancelDownloading();
            } else if (id == R.id.root) {
                int fileState = this.mFileMessage.getFileState();
                if (fileState != 0) {
                    switch (fileState) {
                        case 2:
                            this.isCancel = false;
                            this.mFileMessage.setFileState(1);
                            downloadingState();
                            MQConfig.getController(getContext()).downloadFile(this.mFileMessage, new OnDownloadFileCallback() {
                                public void onSuccess(File file) {
                                    if (!MQChatFileItem.this.isCancel) {
                                        MQChatFileItem.this.mFileMessage.setFileState(0);
                                        MQChatFileItem.this.mCallback.notifyDataSetChanged();
                                    }
                                }

                                public void onProgress(int i) {
                                    MQChatFileItem.this.mFileMessage.setProgress(i);
                                    MQChatFileItem.this.mCallback.notifyDataSetChanged();
                                }

                                public void onFailure(int i, String str) {
                                    if (i != 20006) {
                                        MQChatFileItem.this.mFileMessage.setFileState(3);
                                        MQChatFileItem.this.downloadFailedState();
                                        MQChatFileItem.this.cancelDownloading();
                                        MQChatFileItem.this.mCallback.onFileMessageDownloadFailure(MQChatFileItem.this.mFileMessage, i, str);
                                    }
                                }
                            });
                            return;
                        case 3:
                            this.mFileMessage.setFileState(2);
                            this.root.performClick();
                            return;
                        case 4:
                            this.mCallback.onFileMessageExpired(this.mFileMessage);
                            return;
                        default:
                            return;
                    }
                } else {
                    openFile();
                }
            }
        }
    }

    public void initFileItem(Callback callback, FileMessage fileMessage) {
        this.mCallback = callback;
        this.mFileMessage = fileMessage;
        downloadInitState();
    }

    private void openFile() {
        Uri uri;
        File file = new File(MQUtils.getFileMessageFilePath(this.mFileMessage));
        if (Build.VERSION.SDK_INT >= 24) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Context context = getContext();
            uri = FileProvider.getUriForFile(context, getContext().getPackageName() + ".fileProvider", file);
        } else {
            uri = Uri.fromFile(new File(MQUtils.getFileMessageFilePath(this.mFileMessage)));
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, ShareContentType.FILE);
        intent.addFlags(268435456);
        try {
            getContext().startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(getContext(), R.string.mq_no_app_open_file, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public void cancelDownloading() {
        this.isCancel = true;
        this.mFileMessage.setFileState(2);
        MQConfig.getController(getContext()).cancelDownload(this.mFileMessage.getUrl());
        MQUtils.delFile(MQUtils.getFileMessageFilePath(this.mFileMessage));
        this.mCallback.notifyDataSetChanged();
    }

    public void setProgress(int i) {
        this.mProgressBar.setProgress((float) i);
    }

    public void downloadInitState() {
        this.mProgressBar.setProgress(CropImageView.DEFAULT_ASPECT_RATIO);
        this.mProgressBar.setVisibility(8);
        displayFileInfo();
    }

    public void downloadSuccessState() {
        displayFileInfo();
        this.mProgressBar.setVisibility(8);
        setProgress(100);
        this.mRightIv.setVisibility(8);
    }

    public void downloadFailedState() {
        this.mProgressBar.setVisibility(8);
    }

    public void downloadingState() {
        this.mSubTitleTv.setText(String.format("%s%s", new Object[]{getSubTitlePrefix(), getResources().getString(R.string.mq_downloading)}));
        this.mRightIv.setVisibility(8);
        this.mProgressBar.setVisibility(0);
    }

    private void displayFileInfo() {
        String str;
        this.mTitleTv.setText(getExtraStringValue("filename"));
        if (MQUtils.isFileExist(MQUtils.getFileMessageFilePath(this.mFileMessage))) {
            str = getResources().getString(R.string.mq_download_complete);
            this.mRightIv.setVisibility(8);
        } else {
            long parseTimeToLong = MQTimeUtils.parseTimeToLong(getExtraStringValue("expire_at")) - System.currentTimeMillis();
            if (parseTimeToLong <= 0) {
                str = getResources().getString(R.string.mq_expired);
                this.mRightIv.setVisibility(8);
                this.mFileMessage.setFileState(4);
            } else {
                String format = new DecimalFormat("#.0").format((double) (((float) parseTimeToLong) / 3600000.0f));
                str = getContext().getString(R.string.mq_expire_after, new Object[]{format});
                this.mRightIv.setVisibility(0);
            }
        }
        this.mSubTitleTv.setText(getSubTitlePrefix() + str);
        this.mProgressBar.setVisibility(8);
    }

    private String getExtraStringValue(String str) {
        try {
            return new JSONObject(this.mFileMessage.getExtra()).optString(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getSubTitlePrefix() {
        String formatShortFileSize = Formatter.formatShortFileSize(getContext(), getExtraLongValue("size"));
        return formatShortFileSize + " · ";
    }

    private long getExtraLongValue(String str) {
        try {
            return new JSONObject(this.mFileMessage.getExtra()).optLong(str);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        cancelDownloading();
        return false;
    }
}
