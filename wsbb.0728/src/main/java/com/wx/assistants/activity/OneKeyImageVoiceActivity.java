package com.wx.assistants.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bluefox.moduel.weixinanyvideo.convert.Convert;
import com.bluefox.moduel.weixinanyvideo.convert.ConvertListener;
import com.bluefox.moduel.weixinanyvideo.util.common.Util;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.SelectVoiceEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.utils.manager.MediaManager;
import com.wx.assistants.webview.WebViewActivity;
import com.yongchun.library.adapter.ImageListAdapter;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyImageVoiceActivity extends BaseActivity {
    @BindView(2131296319)
    LinearLayout addCoverLayout;
    @BindView(2131296323)
    LinearLayout addVoiceLayout;
    @BindView(2131296505)
    LinearLayout cleanCover;
    /* access modifiers changed from: private */
    public boolean isHasPlay = false;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297147)
    Button playVoice;
    @BindView(2131297308)
    ImageView selectCover;
    /* access modifiers changed from: private */
    public ArrayList<String> selectCovers = new ArrayList<>();
    @BindView(2131297321)
    ImageView selectVoice;
    /* access modifiers changed from: private */
    public ArrayList<String> selectVoices = new ArrayList<>();
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297426)
    Button startWx2;
    @BindView(2131297450)
    Button switchVoice;
    @BindView(2131297461)
    TextView text;
    @BindView(2131297462)
    TextView text2;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;
    private String voicePath = "";

    static {
        StubApp.interface11(6754);
    }

    /* access modifiers changed from: private */
    public String formatFileSize(long j) {
        if (j >= 1073741824) {
            float f = ((float) j) / 1.07374182E9f;
            int indexOf = String.valueOf(f).indexOf(".");
            return (f + "000").substring(0, indexOf + 3) + "GB";
        } else if (j >= 1048576) {
            float f2 = ((float) j) / 1048576.0f;
            int indexOf2 = String.valueOf(f2).indexOf(".");
            return (f2 + "000").substring(0, indexOf2 + 3) + "MB";
        } else if (j >= ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) {
            float f3 = ((float) j) / 1024.0f;
            int indexOf3 = String.valueOf(f3).indexOf(".");
            return (f3 + "000").substring(0, indexOf3 + 3) + "KB";
        } else if (j >= ConstantsAPI.AppSupportContentFlag.MMAPP_SUPPORT_XLS) {
            return null;
        } else {
            return Long.toString(j) + "B";
        }
    }

    public void execute(final boolean z) {
        if (this.selectVoices != null && this.selectVoices.size() > 0 && this.selectVoices.get(0).endsWith(".mp3")) {
            showLoadingDialog("正在处理", false);
            new Thread(new Runnable() {
                /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
                public void run() {
                    String str = FileUtils.getSDPath() + "/tencent/MicroMsg/WeiXin/" + System.currentTimeMillis() + PictureFileUtils.POST_VIDEO;
                    new Thread(new Runnable() {
                        public void run() {
                            OneKeyImageVoiceActivity.this.runOnUiThread(new Runnable() {
                                /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
                                public void run() {
                                    try {
                                        String str = (String) SPUtils.get(OneKeyImageVoiceActivity.this, "last_img_add_voice_path", "");
                                        if (str != null && !"".equals(str)) {
                                            Util.delFile(str);
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            });
                        }
                    }).start();
                    Convert convert = new Convert(OneKeyImageVoiceActivity.this);
                    convert.setConvertListener(new ConvertListener() {
                        public void invalid(int i) {
                            PrintStream printStream = System.out;
                            printStream.println("BIG_VIDEO_progress:" + i);
                        }

                        public void onError(String str) {
                            OneKeyImageVoiceActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    OneKeyImageVoiceActivity.this.dismissLoadingDialog();
                                }
                            });
                            PrintStream printStream = System.out;
                            printStream.println("BIG_VIDEO_progress:" + str);
                        }

                        public void onFinish(final String str) {
                            OneKeyImageVoiceActivity.this.runOnUiThread(new Runnable() {
                                /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
                                /* JADX WARNING: type inference failed for: r1v11, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
                                /* JADX WARNING: type inference failed for: r0v25, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
                                /* JADX WARNING: type inference failed for: r0v31, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
                                /* JADX WARNING: type inference failed for: r0v40, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
                                public void run() {
                                    try {
                                        if (str != null && !"".equals(str)) {
                                            SPUtils.put(OneKeyImageVoiceActivity.this, "last_img_add_voice_path", str);
                                        }
                                    } catch (Exception e) {
                                    }
                                    OneKeyImageVoiceActivity.this.dismissLoadingDialog();
                                    AlbumUtil.insertVideoToMediaStore2(OneKeyImageVoiceActivity.this, str);
                                    PrintStream printStream = System.out;
                                    printStream.println("BIG_VIDEO_finish_origin---:" + OneKeyImageVoiceActivity.this.formatFileSize(new File((String) OneKeyImageVoiceActivity.this.selectVoices.get(0)).length()));
                                    PrintStream printStream2 = System.out;
                                    printStream2.println("BIG_VIDEO_finish---:" + OneKeyImageVoiceActivity.this.formatFileSize(new File(str).length()));
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("_data", str);
                                    OneKeyImageVoiceActivity.this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    } catch (Throwable th) {
                                        AlbumUtil.insertVideoToMediaStore2(OneKeyImageVoiceActivity.this, str);
                                        throw th;
                                    }
                                    AlbumUtil.insertVideoToMediaStore2(OneKeyImageVoiceActivity.this, str);
                                    if (z) {
                                        OneKeyImageVoiceActivity.this.executeStartWx();
                                        return;
                                    }
                                    ? r0 = OneKeyImageVoiceActivity.this;
                                    ToastUtils.showToast(r0, "已保存到:" + str);
                                }
                            });
                        }

                        public void onProgress(final int i, long j) {
                            OneKeyImageVoiceActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    OneKeyImageVoiceActivity oneKeyImageVoiceActivity = OneKeyImageVoiceActivity.this;
                                    oneKeyImageVoiceActivity.updateLoadingDialog("视频处理 " + i + " %");
                                }
                            });
                        }
                    });
                    System.out.println("BIG_VIDEO_PATH:" + ((String) OneKeyImageVoiceActivity.this.selectVoices.get(0)));
                    System.out.println("BIG_VIDEO_COVER_PATH:" + ((String) OneKeyImageVoiceActivity.this.selectCovers.get(0)));
                    System.out.println("BIG_VIDEO_OUT_PATH:" + str);
                    convert.convertMp3((String) OneKeyImageVoiceActivity.this.selectCovers.get(0), (String) OneKeyImageVoiceActivity.this.selectVoices.get(0), str);
                }
            }).start();
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
    public void executeOperation(final boolean z) {
        if (!PerformClickUtils.isFastClick()) {
            if (this.selectVoices == null || this.selectVoices.size() == 0) {
                ToastUtils.showToast(this, "请设置音频");
            } else if (this.selectCovers == null || this.selectCovers.size() == 0) {
                ToastUtils.showToast(this, "请设置封面");
            } else {
                PermissionUtils.checkReadAndWriteExternalStorage(this, new PermissionListener() {
                    public void permissionDenied(@NonNull String[] strArr) {
                    }

                    public void permissionGranted(@NonNull String[] strArr) {
                        if (z) {
                            OneKeyImageVoiceActivity.this.startCheck("68", true, new BaseActivity.OnStartCheckListener() {
                                public void checkEnd() {
                                    OneKeyImageVoiceActivity.this.execute(z);
                                }
                            });
                        } else {
                            OneKeyImageVoiceActivity.this.execute(z);
                        }
                    }
                });
            }
        }
    }

    public void executeStartWx() {
        OperationParameterModel operationParameterModel = new OperationParameterModel();
        operationParameterModel.setTaskNum("68");
        operationParameterModel.setMessageSendType(1);
        operationParameterModel.setLocalImgUrl("xxx.png");
        operationParameterModel.setMaterialPicCount(1);
        operationParameterModel.setMaterialText("");
        MyApplication.instance.setOperationParameterModel(operationParameterModel);
        startWX(operationParameterModel);
    }

    public void initView() {
        this.navRightImg.setVisibility(0);
        ImageListAdapter.max_duration = 240;
        this.navTitle.setText("图片音频");
        this.startWx.setText("保存到本地");
        this.startWx2.setText("保存并转发到朋友圈");
        this.startWx2.setVisibility(0);
        this.selectCover.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                OneKeyImageVoiceActivity.this.imgLongClickPreview(OneKeyImageVoiceActivity.this.selectCovers);
                return false;
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ImageListAdapter.max_duration = 300;
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    @Subscribe
    public void onEventMainThread(SelectVoiceEvent selectVoiceEvent) {
        if (selectVoiceEvent != null && selectVoiceEvent.getVoicePath() != null) {
            this.voicePath = selectVoiceEvent.getVoicePath();
            this.selectVoices.clear();
            this.selectVoices.add(this.voicePath);
            this.addVoiceLayout.setVisibility(8);
            this.selectVoice.setVisibility(0);
            this.playVoice.setVisibility(0);
            this.switchVoice.setVisibility(0);
            SPUtils.put(MyApplication.getConText(), "voice_origin_path", this.selectVoices.get(0));
            this.selectVoice.setBackgroundResource(2131558658);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            String str = (String) SPUtils.get(MyApplication.getConText(), "voice_origin_path", "");
            if (str != null && !"".equals(str)) {
                this.selectVoices = new ArrayList<>();
                this.selectVoices.add(str);
                this.addVoiceLayout.setVisibility(8);
                this.selectVoice.setVisibility(0);
                this.playVoice.setVisibility(0);
                this.switchVoice.setVisibility(0);
                this.selectVoice.setBackgroundResource(2131558658);
            }
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "video_cover_origin_path", "");
            if (str2 != null && !"".equals(str2)) {
                this.selectCovers = new ArrayList<>();
                this.selectCovers.add(str2);
                this.addCoverLayout.setVisibility(8);
                this.selectCover.setVisibility(0);
                this.cleanCover.setVisibility(8);
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectCover, str2, new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            OneKeyImageVoiceActivity.this.cleanCover.performClick();
                        }
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyImageVoiceActivity] */
    @OnClick({2131297147, 2131297450, 2131297052, 2131296505, 2131297049, 2131297425, 2131297426, 2131296323, 2131296319, 2131297321, 2131297308, 2131297636})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296319:
                selectSingleAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            ArrayList unused = OneKeyImageVoiceActivity.this.selectCovers = arrayList;
                            OneKeyImageVoiceActivity.this.addCoverLayout.setVisibility(8);
                            OneKeyImageVoiceActivity.this.selectCover.setVisibility(0);
                            OneKeyImageVoiceActivity.this.cleanCover.setVisibility(8);
                            SPUtils.put(MyApplication.getConText(), "video_cover_origin_path", OneKeyImageVoiceActivity.this.selectCovers.get(0));
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyImageVoiceActivity.this.selectCover, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyImageVoiceActivity.this.cleanCover.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131296323:
            case 2131297450:
                startActivity(new Intent(this, OneKeySelectForwardVoiceActivity.class));
                return;
            case 2131296505:
                this.cleanCover.setVisibility(8);
                this.selectCovers = null;
                this.addCoverLayout.setVisibility(0);
                this.selectCover.setVisibility(8);
                SPUtils.put(MyApplication.getConText(), "video_cover_origin_path", "");
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectCover, "", new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            OneKeyImageVoiceActivity.this.cleanCover.performClick();
                        }
                    }
                });
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.此功能可以使用自己录制的音频也可以使用微信的音频文件。\n1.此功能会员专享受\n");
                return;
            case 2131297147:
                playVoice();
                return;
            case 2131297308:
                selectSingleAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            ArrayList unused = OneKeyImageVoiceActivity.this.selectCovers = arrayList;
                            OneKeyImageVoiceActivity.this.addCoverLayout.setVisibility(8);
                            OneKeyImageVoiceActivity.this.selectCover.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "video_cover_origin_path", OneKeyImageVoiceActivity.this.selectCovers.get(0));
                            OneKeyImageVoiceActivity.this.cleanCover.setVisibility(8);
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyImageVoiceActivity.this.selectCover, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyImageVoiceActivity.this.cleanCover.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131297321:
                startActivity(new Intent(this, OneKeySelectForwardVoiceActivity.class));
                return;
            case 2131297425:
                executeOperation(false);
                return;
            case 2131297426:
                executeOperation(true);
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "图片加音频", QBangApis.IMAGE_ADD_VOICE);
                return;
            default:
                return;
        }
    }

    public void playVoice() {
        if (!this.isHasPlay) {
            this.isHasPlay = true;
            this.playVoice.setText("暂停播放");
            MediaManager.release();
            MediaManager.playSound(this.selectVoices.get(0), new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    boolean unused = OneKeyImageVoiceActivity.this.isHasPlay = false;
                    MediaManager.release();
                }
            });
            return;
        }
        this.playVoice.setText("播放");
        this.isHasPlay = false;
        MediaManager.release();
    }
}
