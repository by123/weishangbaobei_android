package com.luck.picture.lib;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.fb.jjyyzjy.videocomplex.VideoComplexActivity;
import com.google.gson.Gson;
import com.luck.picture.lib.adapter.GridImageAdapter;
import com.luck.picture.lib.adapter.PictureAlbumDirectoryAdapter;
import com.luck.picture.lib.adapter.PictureImageGridAdapter;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.dialog.CustomDialog;
import com.luck.picture.lib.dialog.MyCustomDialog;
import com.luck.picture.lib.entity.EventEntity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import com.luck.picture.lib.model.LocalMediaLoader;
import com.luck.picture.lib.model.LocalVideoLoader;
import com.luck.picture.lib.observable.ImagesObservable;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.rxbus2.RxBus;
import com.luck.picture.lib.rxbus2.Subscribe;
import com.luck.picture.lib.rxbus2.ThreadMode;
import com.luck.picture.lib.tools.DateUtils;
import com.luck.picture.lib.tools.DoubleUtils;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.luck.picture.lib.tools.ScreenUtils;
import com.luck.picture.lib.tools.StringUtils;
import com.luck.picture.lib.tools.ToastManage;
import com.luck.picture.lib.widget.FolderPopWindow;
import com.luck.picture.lib.widget.PhotoPopupWindow;
import com.stub.StubApp;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PictureSelectorActivity extends PictureBaseActivity implements View.OnClickListener, PictureAlbumDirectoryAdapter.OnItemClickListener, CompoundButton.OnCheckedChangeListener, PictureImageGridAdapter.OnPhotoSelectChangedListener, PhotoPopupWindow.OnItemClickListener {
    private static final int DISMISS_DIALOG = 1;
    public static final int SELECT_PIC_CHANGE = 1;
    private static final int SHOW_DIALOG = 0;
    private static final String TAG = "PictureSelectorActivity";
    /* access modifiers changed from: private */
    public PictureImageGridAdapter adapter;
    private boolean anim = false;
    private Animation animation = null;
    /* access modifiers changed from: private */
    public CustomDialog audioDialog;
    private int audioH;
    private GridImageAdapter bottomdapter;
    private Button btMake;
    private CheckBox cbOnlyVideo;
    /* access modifiers changed from: private */
    public FolderPopWindow folderWindow;
    /* access modifiers changed from: private */
    public List<LocalMediaFolder> foldersList = new ArrayList();
    private boolean fromAddCovers;
    private boolean fromChangePic;
    public Handler handler = new Handler();
    private LinearLayout id_ll_ok;
    /* access modifiers changed from: private */
    public List<LocalMedia> images = new ArrayList();
    private boolean isPlayAudio = false;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 0:
                    PictureSelectorActivity.this.showPleaseDialog();
                    return;
                case 1:
                    PictureSelectorActivity.this.dismissDialog();
                    return;
                default:
                    return;
            }
        }
    };
    private LocalMediaLoader mediaLoader;
    /* access modifiers changed from: private */
    public MediaPlayer mediaPlayer;
    /* access modifiers changed from: private */
    public SeekBar musicSeekBar;
    private TextView picture_id_preview;
    private ImageView picture_left_back;
    private RecyclerView picture_recycler;
    private TextView picture_right;
    private TextView picture_title;
    private TextView picture_tv_img_num;
    private TextView picture_tv_ok;
    private PhotoPopupWindow popupWindow;
    private RecyclerView recyclerView;
    private RelativeLayout rl_bottom_new;
    private RelativeLayout rl_picture_title;
    public Runnable runnable = new Runnable() {
        public void run() {
            try {
                if (PictureSelectorActivity.this.mediaPlayer != null) {
                    PictureSelectorActivity.this.tv_musicTime.setText(DateUtils.timeParse((long) PictureSelectorActivity.this.mediaPlayer.getCurrentPosition()));
                    PictureSelectorActivity.this.musicSeekBar.setProgress(PictureSelectorActivity.this.mediaPlayer.getCurrentPosition());
                    PictureSelectorActivity.this.musicSeekBar.setMax(PictureSelectorActivity.this.mediaPlayer.getDuration());
                    PictureSelectorActivity.this.tv_musicTotal.setText(DateUtils.timeParse((long) PictureSelectorActivity.this.mediaPlayer.getDuration()));
                    PictureSelectorActivity.this.handler.postDelayed(PictureSelectorActivity.this.runnable, 200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private RxPermissions rxPermissions;
    private List<LocalMedia> selectList = new ArrayList();
    /* access modifiers changed from: private */
    public TextView tvSelectNum;
    /* access modifiers changed from: private */
    public TextView tv_PlayPause;
    private TextView tv_Quit;
    private TextView tv_Stop;
    /* access modifiers changed from: private */
    public TextView tv_empty;
    /* access modifiers changed from: private */
    public TextView tv_musicStatus;
    /* access modifiers changed from: private */
    public TextView tv_musicTime;
    /* access modifiers changed from: private */
    public TextView tv_musicTotal;

    public class audioOnClick implements View.OnClickListener {
        /* access modifiers changed from: private */
        public String path;

        public audioOnClick(String str) {
            this.path = str;
        }

        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.tv_PlayPause) {
                PictureSelectorActivity.this.playAudio();
            }
            if (id == R.id.tv_Stop) {
                PictureSelectorActivity.this.tv_musicStatus.setText(PictureSelectorActivity.this.getString(R.string.picture_stop_audio));
                PictureSelectorActivity.this.tv_PlayPause.setText(PictureSelectorActivity.this.getString(R.string.picture_play_audio));
                PictureSelectorActivity.this.stop(this.path);
            }
            if (id == R.id.tv_Quit) {
                PictureSelectorActivity.this.handler.removeCallbacks(PictureSelectorActivity.this.runnable);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        PictureSelectorActivity.this.stop(audioOnClick.this.path);
                    }
                }, 30);
                try {
                    if (PictureSelectorActivity.this.audioDialog != null && PictureSelectorActivity.this.audioDialog.isShowing()) {
                        PictureSelectorActivity.this.audioDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static {
        StubApp.interface11(5610);
    }

    private void audioDialog(final String str) {
        this.audioDialog = new CustomDialog(this.mContext, -1, this.audioH, R.layout.picture_audio_dialog, R.style.Theme_dialog);
        this.audioDialog.getWindow().setWindowAnimations(R.style.Dialog_Audio_StyleAnim);
        this.tv_musicStatus = (TextView) this.audioDialog.findViewById(R.id.tv_musicStatus);
        this.tv_musicTime = (TextView) this.audioDialog.findViewById(R.id.tv_musicTime);
        this.musicSeekBar = (SeekBar) this.audioDialog.findViewById(R.id.musicSeekBar);
        this.tv_musicTotal = (TextView) this.audioDialog.findViewById(R.id.tv_musicTotal);
        this.tv_PlayPause = (TextView) this.audioDialog.findViewById(R.id.tv_PlayPause);
        this.tv_Stop = (TextView) this.audioDialog.findViewById(R.id.tv_Stop);
        this.tv_Quit = (TextView) this.audioDialog.findViewById(R.id.tv_Quit);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                PictureSelectorActivity.this.initPlayer(str);
            }
        }, 30);
        this.tv_PlayPause.setOnClickListener(new audioOnClick(str));
        this.tv_Stop.setOnClickListener(new audioOnClick(str));
        this.tv_Quit.setOnClickListener(new audioOnClick(str));
        this.musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    PictureSelectorActivity.this.mediaPlayer.seekTo(i);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.audioDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                PictureSelectorActivity.this.handler.removeCallbacks(PictureSelectorActivity.this.runnable);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        PictureSelectorActivity.this.stop(str);
                    }
                }, 30);
                try {
                    if (PictureSelectorActivity.this.audioDialog != null && PictureSelectorActivity.this.audioDialog.isShowing()) {
                        PictureSelectorActivity.this.audioDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.handler.post(this.runnable);
        this.audioDialog.show();
    }

    /* access modifiers changed from: private */
    public void initPlayer(String str) {
        this.mediaPlayer = new MediaPlayer();
        try {
            this.mediaPlayer.setDataSource(str);
            this.mediaPlayer.prepare();
            this.mediaPlayer.setLooping(true);
            playAudio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [android.content.Context, com.luck.picture.lib.adapter.PictureImageGridAdapter$OnPhotoSelectChangedListener, android.view.View$OnClickListener, com.luck.picture.lib.adapter.PictureAlbumDirectoryAdapter$OnItemClickListener, com.luck.picture.lib.PictureSelectorActivity, com.luck.picture.lib.widget.PhotoPopupWindow$OnItemClickListener, android.widget.CompoundButton$OnCheckedChangeListener] */
    private void initView(Bundle bundle) {
        this.cbOnlyVideo = (CheckBox) findViewById(R.id.cb_only_video);
        if (this.fromAddCovers) {
            this.cbOnlyVideo.setVisibility(8);
        }
        this.cbOnlyVideo.setOnCheckedChangeListener(this);
        this.rl_bottom_new = (RelativeLayout) findViewById(R.id.rl_bottom_new);
        if (this.fromChangePic || this.fromAddCovers) {
            this.rl_bottom_new.setVisibility(8);
        }
        this.recyclerView = findViewById(R.id.recycler);
        this.recyclerView.getItemAnimator().setSupportsChangeAnimations(false);
        this.recyclerView.setLayoutManager(new FullyGridLayoutManager(this, 1, 0, false));
        this.recyclerView.addItemDecoration(new GridSpacingItemDecoration(9, ScreenUtils.dip2px(this, 4.0f), false));
        this.bottomdapter = new GridImageAdapter(this, (GridImageAdapter.onAddPicClickListener) null);
        this.bottomdapter.setList(this.selectList);
        this.bottomdapter.setSelectMax(9);
        this.recyclerView.setAdapter(this.bottomdapter);
        this.bottomdapter.setSelectNumChangeListener(new GridImageAdapter.OnSelectNumChangeListener() {
            public void onSelectNumChange(int i) {
                TextView access$000 = PictureSelectorActivity.this.tvSelectNum;
                access$000.setText("已经选择" + i + "个图片或视频");
                PictureSelectorActivity.this.updateButtonStatus(i);
            }
        });
        this.tvSelectNum = (TextView) findViewById(R.id.tv_select_num);
        this.btMake = (Button) findViewById(R.id.bt_make);
        this.btMake.setOnClickListener(this);
        this.rl_picture_title = (RelativeLayout) findViewById(R.id.rl_picture_title);
        this.picture_left_back = (ImageView) findViewById(R.id.picture_left_back);
        this.picture_title = (TextView) findViewById(R.id.picture_title);
        this.picture_right = (TextView) findViewById(R.id.picture_right);
        this.picture_tv_ok = (TextView) findViewById(R.id.picture_tv_ok);
        this.picture_id_preview = (TextView) findViewById(R.id.picture_id_preview);
        this.picture_tv_img_num = (TextView) findViewById(R.id.picture_tv_img_num);
        this.picture_recycler = findViewById(R.id.picture_recycler);
        this.id_ll_ok = (LinearLayout) findViewById(R.id.id_ll_ok);
        this.tv_empty = (TextView) findViewById(R.id.tv_empty);
        isNumComplete(this.numComplete);
        if (this.config.mimeType == PictureMimeType.ofAll()) {
            this.popupWindow = new PhotoPopupWindow(this);
            this.popupWindow.setOnItemClickListener(this);
        }
        this.picture_id_preview.setOnClickListener(this);
        if (this.config.mimeType == PictureMimeType.ofAudio()) {
            this.picture_id_preview.setVisibility(8);
            this.audioH = ScreenUtils.getScreenHeight(this.mContext) + ScreenUtils.getStatusBarHeight(this.mContext);
        } else {
            this.picture_id_preview.setVisibility(this.config.mimeType == 2 ? 8 : 0);
        }
        this.picture_left_back.setOnClickListener(this);
        this.picture_right.setOnClickListener(this);
        this.id_ll_ok.setOnClickListener(this);
        this.picture_title.setOnClickListener(this);
        this.picture_title.setText(this.config.mimeType == PictureMimeType.ofAudio() ? getString(R.string.picture_all_audio) : getString(R.string.picture_camera_roll));
        this.folderWindow = new FolderPopWindow(this, this.config.mimeType);
        this.folderWindow.setPictureTitleView(this.picture_title);
        this.folderWindow.setOnItemClickListener(this);
        this.picture_recycler.setHasFixedSize(true);
        this.picture_recycler.addItemDecoration(new GridSpacingItemDecoration(this.config.imageSpanCount, ScreenUtils.dip2px(this, 1.0f), false));
        this.picture_recycler.setLayoutManager(new GridLayoutManager(this, this.config.imageSpanCount));
        this.picture_recycler.getItemAnimator().setSupportsChangeAnimations(false);
        this.mediaLoader = new LocalMediaLoader(this, this.config.mimeType, this.config.isGif, (long) this.config.videoMaxSecond, (long) this.config.videoMinSecond);
        this.rxPermissions.request("android.permission.READ_EXTERNAL_STORAGE").subscribe(new Observer<Boolean>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onNext(Boolean bool) {
                if (bool.booleanValue()) {
                    PictureSelectorActivity.this.mHandler.sendEmptyMessage(0);
                    PictureSelectorActivity.this.readLocalMedia(PictureSelectorActivity.this.config.mimeType);
                    return;
                }
                ToastManage.s(PictureSelectorActivity.this.mContext, PictureSelectorActivity.this.getString(R.string.picture_jurisdiction));
            }

            public void onSubscribe(Disposable disposable) {
            }
        });
        this.tv_empty.setText(this.config.mimeType == PictureMimeType.ofAudio() ? getString(R.string.picture_audio_empty) : getString(R.string.picture_empty));
        StringUtils.tempTextFont(this.tv_empty, this.config.mimeType);
        if (bundle != null) {
            this.selectionMedias = PictureSelector.obtainSelectorList(bundle);
        }
        this.adapter = new PictureImageGridAdapter(this.mContext, this.config);
        this.adapter.setOnPhotoSelectChangedListener(this);
        this.adapter.bindSelectImages(this.selectionMedias);
        this.picture_recycler.setAdapter(this.adapter);
        String trim = this.picture_title.getText().toString().trim();
        if (this.config.isCamera) {
            this.config.isCamera = StringUtils.isCamera(trim);
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.luck.picture.lib.PictureSelectorActivity] */
    private void isNumComplete(boolean z) {
        String string;
        TextView textView = this.picture_tv_ok;
        if (z) {
            string = getString(R.string.picture_done_front_num, new Object[]{0, Integer.valueOf(this.config.selectionMode == 1 ? 1 : this.config.maxSelectNum)});
        } else {
            string = getString(R.string.picture_please_select);
        }
        textView.setText(string);
        if (!z) {
            this.animation = AnimationUtils.loadAnimation(this, R.anim.modal_in);
        }
        this.animation = z ? null : AnimationUtils.loadAnimation(this, R.anim.modal_in);
    }

    private void manualSaveFolder(LocalMedia localMedia) {
        try {
            createNewFolder(this.foldersList);
            LocalMediaFolder imageFolder = getImageFolder(localMedia.getPath(), this.foldersList);
            LocalMediaFolder localMediaFolder = this.foldersList.size() > 0 ? this.foldersList.get(0) : null;
            if (localMediaFolder != null && imageFolder != null) {
                localMediaFolder.setFirstImagePath(localMedia.getPath());
                localMediaFolder.setImages(this.images);
                localMediaFolder.setImageNum(localMediaFolder.getImageNum() + 1);
                imageFolder.setImageNum(imageFolder.getImageNum() + 1);
                imageFolder.getImages().add(0, localMedia);
                imageFolder.setFirstImagePath(this.cameraPath);
                this.folderWindow.bindFolder(this.foldersList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Uri parUri(File file) {
        return Build.VERSION.SDK_INT > 23 ? FileProvider.getUriForFile(this.mContext, getPackageName() + ".provider", file) : Uri.fromFile(file);
    }

    /* access modifiers changed from: private */
    public void playAudio() {
        if (this.mediaPlayer != null) {
            this.musicSeekBar.setProgress(this.mediaPlayer.getCurrentPosition());
            this.musicSeekBar.setMax(this.mediaPlayer.getDuration());
        }
        if (this.tv_PlayPause.getText().toString().equals(getString(R.string.picture_play_audio))) {
            this.tv_PlayPause.setText(getString(R.string.picture_pause_audio));
            this.tv_musicStatus.setText(getString(R.string.picture_play_audio));
            playOrPause();
        } else {
            this.tv_PlayPause.setText(getString(R.string.picture_play_audio));
            this.tv_musicStatus.setText(getString(R.string.picture_pause_audio));
            playOrPause();
        }
        if (!this.isPlayAudio) {
            this.handler.post(this.runnable);
            this.isPlayAudio = true;
        }
    }

    private void readLocalVideo() {
        LocalVideoLoader.loadVideo(this.images, new LocalVideoLoader.LocalVideoLoaderListener() {
            public void videoLoaderComplete(final List<LocalMedia> list) {
                if (PictureSelectorActivity.this.adapter != null) {
                    PictureSelectorActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            PictureSelectorActivity.this.adapter.bindImagesData(list);
                            PictureSelectorActivity.this.tv_empty.setVisibility(list.size() > 0 ? 4 : 0);
                            if (PictureSelectorActivity.this.folderWindow.isShowing()) {
                                PictureSelectorActivity.this.folderWindow.dismiss();
                            }
                        }
                    });
                }
            }
        });
    }

    private void singleRadioMediaImage() {
        List<LocalMedia> selectedImages;
        if (this.adapter != null && (selectedImages = this.adapter.getSelectedImages()) != null && selectedImages.size() > 0) {
            selectedImages.clear();
        }
    }

    /* access modifiers changed from: private */
    public void updateButtonStatus(int i) {
        if (i == 0) {
            this.btMake.setBackgroundColor(getResources().getColor(R.color.gray));
            this.btMake.setTextColor(getResources().getColor(R.color.white_70));
        }
        if (i > 0) {
            this.btMake.setBackgroundColor(getResources().getColor(R.color.orange));
            this.btMake.setTextColor(getResources().getColor(R.color.white));
        }
    }

    public void changeImageNumber(List<LocalMedia> list) {
        String pictureType = list.size() > 0 ? list.get(0).getPictureType() : "";
        if (this.config.mimeType == PictureMimeType.ofAudio()) {
            this.picture_id_preview.setVisibility(8);
        } else {
            this.picture_id_preview.setVisibility(!PictureMimeType.isVideo(pictureType) ? this.config.mimeType == 2 ? 8 : 0 : 8);
        }
        if (list.size() != 0) {
            this.id_ll_ok.setEnabled(true);
            this.picture_id_preview.setEnabled(true);
            this.picture_id_preview.setSelected(true);
            this.picture_tv_ok.setSelected(true);
            if (this.numComplete) {
                this.picture_tv_ok.setText(getString(R.string.picture_done_front_num, new Object[]{Integer.valueOf(list.size()), Integer.valueOf(this.config.selectionMode == 1 ? 1 : this.config.maxSelectNum)}));
                return;
            }
            if (!this.anim) {
                this.picture_tv_img_num.startAnimation(this.animation);
            }
            this.picture_tv_img_num.setVisibility(0);
            this.picture_tv_img_num.setText(String.valueOf(list.size()));
            this.picture_tv_ok.setText(getString(R.string.picture_completed));
            this.anim = false;
            return;
        }
        this.id_ll_ok.setEnabled(false);
        this.picture_id_preview.setEnabled(false);
        this.picture_id_preview.setSelected(false);
        this.picture_tv_ok.setSelected(false);
        if (this.numComplete) {
            this.picture_tv_ok.setText(getString(R.string.picture_done_front_num, new Object[]{0, Integer.valueOf(this.config.selectionMode == 1 ? 1 : this.config.maxSelectNum)}));
            return;
        }
        this.picture_tv_img_num.setVisibility(4);
        this.picture_tv_ok.setText(getString(R.string.picture_please_select));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBus(EventEntity eventEntity) {
        boolean z = true;
        Log.e("wq", "obj  what == " + eventEntity.what);
        int i = eventEntity.what;
        if (i == 1) {
            List<LocalMedia> list = eventEntity.medias;
            if (list != null && list.size() > 0 && this.bottomdapter != null) {
                this.bottomdapter.setList(list);
                this.bottomdapter.notifyDataSetChanged();
                this.selectList.clear();
                this.selectList.addAll(list);
                if (this.adapter != null) {
                    this.adapter.updateSelectedImages(list);
                }
                if (this.tvSelectNum != null) {
                    this.tvSelectNum.setText("已经选择" + list.size() + "个图片或视频");
                }
            }
        } else if (i == 2771) {
            List<LocalMedia> list2 = eventEntity.medias;
            if (list2.size() > 0) {
                String pictureType = list2.get(0).getPictureType();
                if (!this.config.isCompress || !pictureType.startsWith("image")) {
                    onResult(list2);
                } else {
                    compressImage(list2);
                }
            }
        } else if (i == 2774) {
            List<LocalMedia> list3 = eventEntity.medias;
            if (list3.size() <= 0) {
                z = false;
            }
            this.anim = z;
            int i2 = eventEntity.position;
            Log.i("刷新下标:", String.valueOf(i2));
            this.adapter.bindSelectImages(list3);
            this.adapter.notifyItemChanged(i2);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBus(List<LocalMedia> list) {
        Log.e("wq", "eventBus" + list);
        if (this.bottomdapter != null) {
            this.bottomdapter.setList(list);
            this.bottomdapter.notifyDataSetChanged();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, java.lang.Runtime] */
    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }

    public void onBackPressed() {
        super.onBackPressed();
        closeActivity();
    }

    public void onChange(List<LocalMedia> list) {
        Log.e("wq", "onChange selectImages size == " + list.size());
        if (!this.fromChangePic && !this.fromAddCovers) {
            changeImageNumber(list);
            this.bottomdapter.setList(list);
            this.bottomdapter.notifyDataSetChanged();
            TextView textView = this.tvSelectNum;
            textView.setText("已经选择" + list.size() + "个图片或视频");
            updateButtonStatus(list.size());
        } else if (list.size() != 0) {
            LocalMedia localMedia = null;
            if (!(list == null || list.size() == 0)) {
                localMedia = list.get(list.size() - 1);
            }
            if (localMedia != null) {
                setResult(101, new Intent().putExtra("new_picture", new Gson().toJson(localMedia)));
            }
            finish();
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Log.e("wq", "onCheckedChanged b == " + z);
        if (z) {
            readLocalVideo();
        } else {
            this.adapter.bindImagesData(this.images);
        }
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [android.content.Context, com.luck.picture.lib.PictureSelectorActivity] */
    public void onClick(View view) {
        LocalMedia localMedia = null;
        int id = view.getId();
        if (id == R.id.bt_make) {
            Log.e("wq", "onClick selectList == " + Arrays.toString(this.bottomdapter.getList().toArray()));
            Intent intent = new Intent(this, VideoComplexActivity.class);
            intent.putExtra("extra_data_media", new Gson().toJson(this.bottomdapter.getList().toArray()));
            startActivity(intent);
        }
        if (id == R.id.picture_left_back || id == R.id.picture_right) {
            if (this.folderWindow.isShowing()) {
                this.folderWindow.dismiss();
            } else {
                Log.e("wq", "selectList size == " + this.bottomdapter.getList().size());
                if (this.bottomdapter.getList().size() == 0) {
                    closeActivity();
                } else {
                    new MyCustomDialog(this).show("放弃此次编辑吗？", (String) null, (String) null, new MyCustomDialog.OnOkClickListener() {
                        public void onOkClick() {
                            PictureSelectorActivity.this.finish();
                        }
                    }, new MyCustomDialog.OnCancelClickListener() {
                        public void onCancelClick() {
                            PictureSelectorActivity.this.dialog.dismiss();
                        }
                    });
                }
            }
        }
        if (id == R.id.picture_title) {
            if (this.folderWindow.isShowing()) {
                this.folderWindow.dismiss();
            } else if (this.images != null && this.images.size() > 0) {
                this.folderWindow.showAsDropDown(this.rl_picture_title);
                this.folderWindow.notifyDataCheckedStatus(this.adapter.getSelectedImages());
            }
        }
        if (id == R.id.picture_id_preview) {
            List<LocalMedia> selectedImages = this.adapter.getSelectedImages();
            ArrayList arrayList = new ArrayList();
            for (LocalMedia add : selectedImages) {
                arrayList.add(add);
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("previewSelectList", arrayList);
            bundle.putSerializable(PictureConfig.EXTRA_SELECT_LIST, (Serializable) selectedImages);
            bundle.putBoolean(PictureConfig.EXTRA_BOTTOM_PREVIEW, true);
            startActivity(PicturePreviewActivity.class, bundle, this.config.selectionMode == 1 ? 69 : 609);
            overridePendingTransition(R.anim.a5, 0);
        }
        if (id == R.id.id_ll_ok) {
            List<LocalMedia> selectedImages2 = this.adapter.getSelectedImages();
            if (selectedImages2.size() > 0) {
                localMedia = selectedImages2.get(0);
            }
            String pictureType = localMedia != null ? localMedia.getPictureType() : "";
            int size = selectedImages2.size();
            boolean startsWith = pictureType.startsWith("image");
            if (this.config.minSelectNum > 0 && this.config.selectionMode == 2 && size < this.config.minSelectNum) {
                ToastManage.s(this.mContext, startsWith ? getString(R.string.picture_min_img_num, new Object[]{Integer.valueOf(this.config.minSelectNum)}) : getString(R.string.picture_min_video_num, new Object[]{Integer.valueOf(this.config.minSelectNum)}));
            } else if (!this.config.enableCrop || !startsWith) {
                if (!this.config.isCompress || !startsWith) {
                    onResult(selectedImages2);
                } else {
                    compressImage(selectedImages2);
                }
            } else if (this.config.selectionMode == 1) {
                this.originalPath = localMedia.getPath();
                startCrop(this.originalPath);
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (LocalMedia path : selectedImages2) {
                    arrayList2.add(path.getPath());
                }
                startCrop((ArrayList<String>) arrayList2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.luck.picture.lib.PictureSelectorActivity, java.lang.Object, com.luck.picture.lib.PictureBaseActivity] */
    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (RxBus.getDefault().isRegistered(this)) {
            RxBus.getDefault().unregister(this);
        }
        ImagesObservable.getInstance().clearLocalMedia();
        if (this.animation != null) {
            this.animation.cancel();
            this.animation = null;
        }
        if (!(this.mediaPlayer == null || this.handler == null)) {
            this.handler.removeCallbacks(this.runnable);
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
        Glide.get(this).clearMemory();
    }

    public void onItemClick(int i) {
        switch (i) {
            case 0:
                startOpenCamera();
                return;
            case 1:
                startOpenCameraVideo();
                return;
            default:
                return;
        }
    }

    public void onItemClick(String str, List<LocalMedia> list) {
        boolean isCamera = StringUtils.isCamera(str);
        if (!this.config.isCamera) {
            isCamera = false;
        }
        this.adapter.setShowCamera(isCamera);
        this.picture_title.setText(str);
        Log.e("wq", "images size == " + list.size());
        this.images = list;
        if (this.cbOnlyVideo.isChecked()) {
            readLocalVideo();
            return;
        }
        this.adapter.bindImagesData(list);
        this.folderWindow.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String stringExtra = getIntent().getStringExtra("from");
        Log.e("wq", "onNewIntent from == " + stringExtra);
        this.fromChangePic = TextUtils.equals("change_picture", stringExtra);
        this.fromAddCovers = TextUtils.equals("add_covers", stringExtra);
        if (this.fromChangePic || this.fromAddCovers) {
            this.rl_bottom_new.setVisibility(8);
        }
        if (this.fromAddCovers) {
            this.cbOnlyVideo.setVisibility(8);
        }
    }

    public void onPictureClick(LocalMedia localMedia, int i) {
        Log.e("wq", "onPictureClick position == " + i);
        if (this.fromChangePic) {
            setResult(101, new Intent().putExtra("new_picture", new Gson().toJson(localMedia)));
            finish();
            return;
        }
        startPreview(this.adapter.getImages(), i);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.adapter != null) {
            PictureSelector.saveSelectorList(bundle, this.adapter.getSelectedImages());
        }
    }

    public void onTakePhoto() {
        this.rxPermissions.request("android.permission.CAMERA").subscribe(new Observer<Boolean>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onNext(Boolean bool) {
                if (bool.booleanValue()) {
                    PictureSelectorActivity.this.startCamera();
                    return;
                }
                ToastManage.s(PictureSelectorActivity.this.mContext, PictureSelectorActivity.this.getString(R.string.picture_camera));
                if (PictureSelectorActivity.this.config.camera) {
                    PictureSelectorActivity.this.closeActivity();
                }
            }

            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    public void playOrPause() {
        try {
            if (this.mediaPlayer == null) {
                return;
            }
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.pause();
            } else {
                this.mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void readLocalMedia(int i) {
        this.mediaLoader.loadAllMedia(i, new LocalMediaLoader.LocalMediaLoadListener() {
            public void loadComplete(List<LocalMediaFolder> list) {
                if (list.size() > 0) {
                    List unused = PictureSelectorActivity.this.foldersList = list;
                    LocalMediaFolder localMediaFolder = list.get(0);
                    localMediaFolder.setChecked(true);
                    List unused2 = PictureSelectorActivity.this.images = localMediaFolder.getImages();
                    PictureSelectorActivity.this.folderWindow.bindFolder(list);
                }
                if (PictureSelectorActivity.this.adapter != null) {
                    if (PictureSelectorActivity.this.images == null) {
                        List unused3 = PictureSelectorActivity.this.images = new ArrayList();
                    }
                    PictureSelectorActivity.this.adapter.bindImagesData(PictureSelectorActivity.this.images);
                    PictureSelectorActivity.this.tv_empty.setVisibility(PictureSelectorActivity.this.images.size() > 0 ? 4 : 0);
                }
                PictureSelectorActivity.this.mHandler.sendEmptyMessage(1);
            }
        });
    }

    public void startCamera() {
        if (!DoubleUtils.isFastDoubleClick() || this.config.camera) {
            switch (this.config.mimeType) {
                case 0:
                    if (this.popupWindow != null) {
                        if (this.popupWindow.isShowing()) {
                            this.popupWindow.dismiss();
                        }
                        this.popupWindow.showAsDropDown(this.rl_picture_title);
                        return;
                    }
                    startOpenCamera();
                    return;
                case 1:
                    startOpenCamera();
                    return;
                case 2:
                    startOpenCameraVideo();
                    return;
                case 3:
                    startOpenCameraAudio();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.luck.picture.lib.PictureSelectorActivity] */
    public void startOpenCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            File createCameraFile = PictureFileUtils.createCameraFile(this, this.config.mimeType == 0 ? 1 : this.config.mimeType, this.outputCameraPath, this.config.suffixType);
            this.cameraPath = createCameraFile.getAbsolutePath();
            intent.putExtra("output", parUri(createCameraFile));
            startActivityForResult(intent, PictureConfig.REQUEST_CAMERA);
        }
    }

    public void startOpenCameraAudio() {
        this.rxPermissions.request("android.permission.RECORD_AUDIO").subscribe(new Observer<Boolean>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onNext(Boolean bool) {
                if (bool.booleanValue()) {
                    Intent intent = new Intent("android.provider.MediaStore.RECORD_SOUND");
                    if (intent.resolveActivity(PictureSelectorActivity.this.getPackageManager()) != null) {
                        PictureSelectorActivity.this.startActivityForResult(intent, PictureConfig.REQUEST_CAMERA);
                        return;
                    }
                    return;
                }
                ToastManage.s(PictureSelectorActivity.this.mContext, PictureSelectorActivity.this.getString(R.string.picture_audio));
            }

            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.luck.picture.lib.PictureSelectorActivity] */
    public void startOpenCameraVideo() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        if (intent.resolveActivity(getPackageManager()) != null) {
            File createCameraFile = PictureFileUtils.createCameraFile(this, this.config.mimeType == 0 ? 2 : this.config.mimeType, this.outputCameraPath, this.config.suffixType);
            this.cameraPath = createCameraFile.getAbsolutePath();
            intent.putExtra("output", parUri(createCameraFile));
            intent.putExtra("android.intent.extra.durationLimit", this.config.recordVideoSecond);
            intent.putExtra("android.intent.extra.videoQuality", this.config.videoQuality);
            startActivityForResult(intent, PictureConfig.REQUEST_CAMERA);
        }
    }

    public void startPreview(List<LocalMedia> list, int i) {
        LocalMedia localMedia = list.get(i);
        String pictureType = localMedia.getPictureType();
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        switch (PictureMimeType.isPictureType(pictureType)) {
            case 1:
                List<LocalMedia> selectedImages = this.adapter.getSelectedImages();
                ImagesObservable.getInstance().saveLocalMedia(list);
                bundle.putSerializable(PictureConfig.EXTRA_SELECT_LIST, (Serializable) selectedImages);
                bundle.putInt("position", i);
                startActivity(PicturePreviewActivity.class, bundle, this.config.selectionMode == 1 ? 69 : 609);
                overridePendingTransition(R.anim.a5, 0);
                return;
            case 2:
                if (this.config.selectionMode == 1) {
                    arrayList.add(localMedia);
                    onResult(arrayList);
                    return;
                }
                bundle.putString("video_path", localMedia.getPath());
                startActivity(PictureVideoPlayActivity.class, bundle);
                return;
            case 3:
                if (this.config.selectionMode == 1) {
                    arrayList.add(localMedia);
                    onResult(arrayList);
                    return;
                }
                audioDialog(localMedia.getPath());
                return;
            default:
                return;
        }
    }

    public void stop(String str) {
        if (this.mediaPlayer != null) {
            try {
                this.mediaPlayer.stop();
                this.mediaPlayer.reset();
                this.mediaPlayer.setDataSource(str);
                this.mediaPlayer.prepare();
                this.mediaPlayer.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
