package com.wx.assistants.activity;

import android.content.ContentValues;
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
import com.bluefox.moduel.weixinanyvideo.convert.MediaInfo;
import com.bluefox.moduel.weixinanyvideo.util.common.Util;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.umeng.commonsdk.proguard.c;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.webview.WebViewActivity;
import com.yongchun.library.adapter.ImageListAdapter;
import com.zhouyou.view.seekbar.SignSeekBar;
import io.microshow.rxffmpeg.RxFFmpegInvoke;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyForwardBigVideoActivity extends BaseActivity {
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296508)
    LinearLayout cleanImage;
    /* access modifiers changed from: private */
    public int codeRate = 20;
    @BindView(2131296616)
    EditTextWithScrollView editLeavingMessage;
    @BindView(2131296617)
    LinearLayout editLeavingMessage2;
    /* access modifiers changed from: private */
    public int executeCount = 0;
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
    double quality = 0.1d;
    private String sayContent = "";
    @BindView(2131297311)
    ImageView selectImg;
    /* access modifiers changed from: private */
    public ArrayList<String> selectImgs = new ArrayList<>();
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297382)
    SignSeekBar signSeekBar;
    private int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297461)
    TextView text;
    @BindView(2131297462)
    TextView text2;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6749);
    }

    static /* synthetic */ int access$008(OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity) {
        int i = oneKeyForwardBigVideoActivity.executeCount;
        oneKeyForwardBigVideoActivity.executeCount = i + 1;
        return i;
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

    public void compressSaveVideo() {
        showLoadingDialog("正在处理视频", false);
        compressSaveVideo((List<String>) this.selectImgs, (FileUtil.OnConvertMp4Listener) new FileUtil.OnConvertMp4Listener() {
            public void convertFail() {
                OneKeyForwardBigVideoActivity.this.dismissLoadingDialog();
            }

            public void convertProgress(int i) {
                OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity = OneKeyForwardBigVideoActivity.this;
                oneKeyForwardBigVideoActivity.updateLoadingDialog("视频处理 " + i + " %");
            }

            public void convertSuccess(String str) {
                OneKeyForwardBigVideoActivity.this.dismissLoadingDialog();
                OneKeyForwardBigVideoActivity.this.executeStartWx();
            }
        });
    }

    public void executeStartWx() {
        this.executeCount = 0;
        OperationParameterModel operationParameterModel = new OperationParameterModel();
        operationParameterModel.setTaskNum("66");
        operationParameterModel.setMessageSendType(1);
        operationParameterModel.setLocalImgUrl("xxx.png");
        operationParameterModel.setMaterialPicCount(1);
        operationParameterModel.setMaterialText(this.sayContent);
        MyApplication.instance.setOperationParameterModel(operationParameterModel);
        startWX(operationParameterModel);
    }

    public void initView() {
        this.navRightImg.setVisibility(0);
        ImageListAdapter.max_duration = 240;
        this.navTitle.setText("发布大视频");
        this.startWx.setText("启动微信开始发布");
        this.selectImg.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                int unused = OneKeyForwardBigVideoActivity.this.executeCount = 0;
                OneKeyForwardBigVideoActivity.this.imgLongClickPreview(OneKeyForwardBigVideoActivity.this.selectImgs);
                return false;
            }
        });
        this.signSeekBar.setOnProgressChangedListener(new SignSeekBar.OnProgressChangedListener() {
            public void getProgressOnActionUp(SignSeekBar signSeekBar, int i, float f) {
                int unused = OneKeyForwardBigVideoActivity.this.codeRate = i;
            }

            public void getProgressOnFinally(SignSeekBar signSeekBar, int i, float f, boolean z) {
            }

            public void onProgressChanged(SignSeekBar signSeekBar, int i, float f, boolean z) {
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            String str = (String) SPUtils.get(MyApplication.getConText(), "forward_big_video_content", "");
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "forward_big_video_path", "");
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
            if (str2 != null && !"".equals(str2)) {
                this.selectImgs = new ArrayList<>();
                this.selectImgs.add(str2);
                this.editLeavingMessage2.setVisibility(8);
                this.selectImg.setVisibility(0);
                this.cleanImage.setVisibility(0);
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectImg, str2, new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            OneKeyForwardBigVideoActivity.this.cleanImage.performClick();
                        }
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
    @OnClick({2131296507, 2131297052, 2131296508, 2131297049, 2131297425, 2131296616, 2131296617, 2131297311, 2131297636})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.editLeavingMessage.setText("");
                return;
            case 2131296508:
                this.executeCount = 0;
                this.cleanImage.setVisibility(8);
                this.selectImgs = null;
                this.editLeavingMessage2.setVisibility(0);
                this.selectImg.setVisibility(8);
                SPUtils.put(MyApplication.getConText(), "forward_big_video_path", "");
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectImg, "", new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            OneKeyForwardBigVideoActivity.this.cleanImage.performClick();
                        }
                    }
                });
                return;
            case 2131296617:
                selectSingleVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            int unused = OneKeyForwardBigVideoActivity.this.executeCount = 0;
                            ArrayList unused2 = OneKeyForwardBigVideoActivity.this.selectImgs = arrayList;
                            OneKeyForwardBigVideoActivity.this.editLeavingMessage2.setVisibility(8);
                            OneKeyForwardBigVideoActivity.this.selectImg.setVisibility(0);
                            OneKeyForwardBigVideoActivity.this.cleanImage.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "forward_big_video_path", OneKeyForwardBigVideoActivity.this.selectImgs.get(0));
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyForwardBigVideoActivity.this.selectImg, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyForwardBigVideoActivity.this.cleanImage.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.此功能只能选择4分钟以内的大视频。\n2.程序会自动帮您把大视频压缩到符合微信规则的大小，压缩后的清晰度根据视频情况会有所不同。\n3.点启动微信开始发布，等视频压缩完后，在点辅助开始按钮，可自动发布到朋友圈。\n4.压缩完视频后，若发现压缩后的大小大于5MB,发布后的视频微信有可能会自动剪切导致不完整，我们建议您换个视频发布。\n5.此功能半年及以上会员专享受\n");
                return;
            case 2131297311:
                selectSingleVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            int unused = OneKeyForwardBigVideoActivity.this.executeCount = 0;
                            ArrayList unused2 = OneKeyForwardBigVideoActivity.this.selectImgs = arrayList;
                            OneKeyForwardBigVideoActivity.this.editLeavingMessage2.setVisibility(8);
                            OneKeyForwardBigVideoActivity.this.selectImg.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "forward_big_video_path", OneKeyForwardBigVideoActivity.this.selectImgs.get(0));
                            OneKeyForwardBigVideoActivity.this.cleanImage.setVisibility(0);
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyForwardBigVideoActivity.this.selectImg, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyForwardBigVideoActivity.this.cleanImage.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131297425:
                if (!PerformClickUtils.isFastClick()) {
                    this.sayContent = this.editLeavingMessage.getText().toString();
                    if (this.sayContent != null && !"".equals(this.sayContent)) {
                        SPUtils.put(MyApplication.getConText(), "text_img_friend_content", this.sayContent);
                    }
                    if (this.selectImgs == null || this.selectImgs.size() <= 0) {
                        ToastUtils.showToast(this, "请设置要发送的视频");
                        return;
                    } else {
                        PermissionUtils.checkReadAndWriteExternalStorage(this, new PermissionListener() {
                            public void permissionDenied(@NonNull String[] strArr) {
                            }

                            public void permissionGranted(@NonNull String[] strArr) {
                                OneKeyForwardBigVideoActivity.this.startCheck("5", true, new BaseActivity.OnStartCheckListener() {
                                    public void checkEnd() {
                                        boolean z;
                                        LogUtils.log("WS_BABY_checkend");
                                        if (OneKeyForwardBigVideoActivity.this.selectImgs != null && OneKeyForwardBigVideoActivity.this.selectImgs.size() > 0 && ((String) OneKeyForwardBigVideoActivity.this.selectImgs.get(0)).endsWith(PictureFileUtils.POST_VIDEO)) {
                                            try {
                                                RxFFmpegInvoke.getInstance().setDebug(false);
                                                MediaInfo parse = MediaInfo.parse(RxFFmpegInvoke.getInstance().getMediaInfo((String) OneKeyForwardBigVideoActivity.this.selectImgs.get(0)));
                                                long j = parse.duration;
                                                if (j > 240000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity = OneKeyForwardBigVideoActivity.this;
                                                    double access$000 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$000);
                                                    oneKeyForwardBigVideoActivity.quality = 0.06d - (access$000 * 0.01d);
                                                } else if (j > 230000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity2 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0002 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0002);
                                                    oneKeyForwardBigVideoActivity2.quality = 0.07d - (access$0002 * 0.01d);
                                                } else if (j > 220000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity3 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0003 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0003);
                                                    oneKeyForwardBigVideoActivity3.quality = 0.08d - (access$0003 * 0.01d);
                                                } else if (j > 210000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity4 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0004 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0004);
                                                    oneKeyForwardBigVideoActivity4.quality = 0.09d - (access$0004 * 0.02d);
                                                } else if (j > 200000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity5 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0005 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0005);
                                                    oneKeyForwardBigVideoActivity5.quality = 0.1d - (access$0005 * 0.02d);
                                                } else if (j > 190000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity6 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0006 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0006);
                                                    oneKeyForwardBigVideoActivity6.quality = 0.11d - (access$0006 * 0.02d);
                                                } else if (j > 180000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity7 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0007 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0007);
                                                    oneKeyForwardBigVideoActivity7.quality = 0.12d - (access$0007 * 0.02d);
                                                } else if (j > 170000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity8 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0008 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0008);
                                                    oneKeyForwardBigVideoActivity8.quality = 0.13d - (access$0008 * 0.02d);
                                                } else if (j > 160000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity9 = OneKeyForwardBigVideoActivity.this;
                                                    double access$0009 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$0009);
                                                    oneKeyForwardBigVideoActivity9.quality = 0.14d - (access$0009 * 0.02d);
                                                } else if (j > 140000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity10 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00010 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00010);
                                                    oneKeyForwardBigVideoActivity10.quality = 0.15d - (access$00010 * 0.02d);
                                                } else if (j > 130000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity11 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00011 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00011);
                                                    oneKeyForwardBigVideoActivity11.quality = 0.16d - (access$00011 * 0.02d);
                                                } else if (j > 125000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity12 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00012 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00012);
                                                    oneKeyForwardBigVideoActivity12.quality = 0.17d - (access$00012 * 0.02d);
                                                } else if (j > 120000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity13 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00013 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00013);
                                                    oneKeyForwardBigVideoActivity13.quality = 0.18d - (access$00013 * 0.02d);
                                                } else if (j > 115000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity14 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00014 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00014);
                                                    oneKeyForwardBigVideoActivity14.quality = 0.19d - (access$00014 * 0.02d);
                                                } else if (j > 110000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity15 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00015 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00015);
                                                    oneKeyForwardBigVideoActivity15.quality = 0.2d - (access$00015 * 0.03d);
                                                } else if (j > 105000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity16 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00016 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00016);
                                                    oneKeyForwardBigVideoActivity16.quality = 0.21d - (access$00016 * 0.03d);
                                                } else if (j > 100000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity17 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00017 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00017);
                                                    oneKeyForwardBigVideoActivity17.quality = 0.22d - (access$00017 * 0.04d);
                                                } else if (j > 95000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity18 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00018 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00018);
                                                    oneKeyForwardBigVideoActivity18.quality = 0.23d - (access$00018 * 0.04d);
                                                } else if (j > 90000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity19 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00019 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00019);
                                                    oneKeyForwardBigVideoActivity19.quality = 0.24d - (access$00019 * 0.04d);
                                                } else if (j > 85000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity20 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00020 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00020);
                                                    oneKeyForwardBigVideoActivity20.quality = 0.25d - (access$00020 * 0.04d);
                                                } else if (j > 80000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity21 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00021 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00021);
                                                    oneKeyForwardBigVideoActivity21.quality = 0.26d - (access$00021 * 0.04d);
                                                } else if (j > 75000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity22 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00022 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00022);
                                                    oneKeyForwardBigVideoActivity22.quality = 0.27d - (access$00022 * 0.04d);
                                                } else if (j > 70000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity23 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00023 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00023);
                                                    oneKeyForwardBigVideoActivity23.quality = 0.28d - (access$00023 * 0.04d);
                                                } else if (j > 65000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity24 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00024 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00024);
                                                    oneKeyForwardBigVideoActivity24.quality = 0.29d - (access$00024 * 0.04d);
                                                } else if (j > 60000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity25 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00025 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00025);
                                                    oneKeyForwardBigVideoActivity25.quality = 0.3d - (access$00025 * 0.04d);
                                                } else if (j > 50000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity26 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00026 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00026);
                                                    oneKeyForwardBigVideoActivity26.quality = 0.35d - (access$00026 * 0.04d);
                                                } else if (j > 40000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity27 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00027 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00027);
                                                    oneKeyForwardBigVideoActivity27.quality = 0.4d - (access$00027 * 0.05d);
                                                } else if (j > c.d) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity28 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00028 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00028);
                                                    oneKeyForwardBigVideoActivity28.quality = 0.45d - (access$00028 * 0.05d);
                                                } else if (j > 15000) {
                                                    OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity29 = OneKeyForwardBigVideoActivity.this;
                                                    double access$00029 = (double) OneKeyForwardBigVideoActivity.this.executeCount;
                                                    Double.isNaN(access$00029);
                                                    oneKeyForwardBigVideoActivity29.quality = 0.55d - (access$00029 * 0.05d);
                                                }
                                                z = parse.duration > 15000;
                                                try {
                                                    System.out.println("BIG_VIDEO_rate_1_" + OneKeyForwardBigVideoActivity.this.quality);
                                                    try {
                                                        if (OneKeyForwardBigVideoActivity.this.codeRate < 20) {
                                                            double access$200 = (double) (20 - OneKeyForwardBigVideoActivity.this.codeRate);
                                                            Double.isNaN(access$200);
                                                            double d = access$200 / 100.0d;
                                                            System.out.println("BIG_VIDEO_rate_2_" + d);
                                                            if (OneKeyForwardBigVideoActivity.this.quality <= d || OneKeyForwardBigVideoActivity.this.quality - d <= 0.0d) {
                                                                OneKeyForwardBigVideoActivity.this.quality = 0.02d;
                                                            } else {
                                                                OneKeyForwardBigVideoActivity.this.quality -= d;
                                                                OneKeyForwardBigVideoActivity.this.quality = Double.parseDouble(String.format("%.2f", new Object[]{Double.valueOf(OneKeyForwardBigVideoActivity.this.quality)}));
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                    }
                                                    System.out.println("BIG_VIDEO_duration:" + parse.duration + ListUtils.DEFAULT_JOIN_SEPARATOR + z + ListUtils.DEFAULT_JOIN_SEPARATOR + OneKeyForwardBigVideoActivity.this.quality);
                                                } catch (Exception e2) {
                                                }
                                            } catch (Exception e3) {
                                                z = true;
                                            }
                                            if (z) {
                                                OneKeyForwardBigVideoActivity.this.showLoadingDialog("正在处理视频", false);
                                                new Thread(new Runnable() {
                                                    /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                    public void run() {
                                                        String str = FileUtils.getSDPath() + "/tencent/MicroMsg/WeiXin/" + System.currentTimeMillis() + PictureFileUtils.POST_VIDEO;
                                                        new Thread(new Runnable() {
                                                            public void run() {
                                                                OneKeyForwardBigVideoActivity.this.runOnUiThread(new Runnable() {
                                                                    /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                                    public void run() {
                                                                        try {
                                                                            String str = (String) SPUtils.get(OneKeyForwardBigVideoActivity.this, "last_big_video_path", "");
                                                                            if (str != null && !"".equals(str)) {
                                                                                Util.delFile(str);
                                                                            }
                                                                        } catch (Exception e) {
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        }).start();
                                                        Convert convert = new Convert(OneKeyForwardBigVideoActivity.this);
                                                        convert.setConvertListener(new ConvertListener() {
                                                            public void invalid(int i) {
                                                                PrintStream printStream = System.out;
                                                                printStream.println("BIG_VIDEO_progress:" + i);
                                                            }

                                                            public void onError(String str) {
                                                                OneKeyForwardBigVideoActivity.this.runOnUiThread(new Runnable() {
                                                                    public void run() {
                                                                        OneKeyForwardBigVideoActivity.this.dismissLoadingDialog();
                                                                    }
                                                                });
                                                                PrintStream printStream = System.out;
                                                                printStream.println("BIG_VIDEO_progress:" + str);
                                                            }

                                                            public void onFinish(final String str) {
                                                                OneKeyForwardBigVideoActivity.this.runOnUiThread(new Runnable() {
                                                                    /* JADX WARNING: type inference failed for: r0v10, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                                    /* JADX WARNING: type inference failed for: r1v22, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                                    /* JADX WARNING: type inference failed for: r0v68, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                                    /* JADX WARNING: type inference failed for: r1v28, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                                    /* JADX WARNING: type inference failed for: r0v87, types: [android.content.Context] */
                                                                    /* JADX WARNING: type inference failed for: r0v119, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                                    /* JADX WARNING: type inference failed for: r0v127, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
                                                                    /* JADX WARNING: type inference failed for: r0v128 */
                                                                    /* JADX WARNING: type inference failed for: r0v129 */
                                                                    /* JADX WARNING: Multi-variable type inference failed */
                                                                    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
                                                                    /* JADX WARNING: Unknown variable types count: 1 */
                                                                    public void run() {
                                                                        ? r0;
                                                                        try {
                                                                            if (str != null && !"".equals(str)) {
                                                                                SPUtils.put(OneKeyForwardBigVideoActivity.this, "last_big_video_path", str);
                                                                            }
                                                                        } catch (Exception e) {
                                                                        }
                                                                        OneKeyForwardBigVideoActivity.this.dismissLoadingDialog();
                                                                        AlbumUtil.insertVideoToMediaStore2(OneKeyForwardBigVideoActivity.this, str);
                                                                        PrintStream printStream = System.out;
                                                                        printStream.println("BIG_VIDEO_finish_origin---:" + OneKeyForwardBigVideoActivity.this.formatFileSize(new File((String) OneKeyForwardBigVideoActivity.this.selectImgs.get(0)).length()));
                                                                        PrintStream printStream2 = System.out;
                                                                        printStream2.println("BIG_VIDEO_finish---:" + OneKeyForwardBigVideoActivity.this.formatFileSize(new File(str).length()));
                                                                        ContentValues contentValues = new ContentValues();
                                                                        contentValues.put("_data", str);
                                                                        OneKeyForwardBigVideoActivity.this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                                                                        TextView textView = OneKeyForwardBigVideoActivity.this.text;
                                                                        textView.setText("原始大小：" + OneKeyForwardBigVideoActivity.this.formatFileSize(new File((String) OneKeyForwardBigVideoActivity.this.selectImgs.get(0)).length()));
                                                                        TextView textView2 = OneKeyForwardBigVideoActivity.this.text2;
                                                                        textView2.setText("(压缩后大小:" + OneKeyForwardBigVideoActivity.this.formatFileSize(new File(str).length()) + ")");
                                                                        String access$300 = OneKeyForwardBigVideoActivity.this.formatFileSize(new File(str).length());
                                                                        if (access$300 != null && !"".equals(access$300)) {
                                                                            try {
                                                                                if (Double.valueOf(Double.parseDouble(access$300.replace("MB", ""))).doubleValue() <= 4.0d) {
                                                                                    try {
                                                                                        Thread.sleep(500);
                                                                                        r0 = OneKeyForwardBigVideoActivity.this;
                                                                                    } catch (InterruptedException e2) {
                                                                                        e2.printStackTrace();
                                                                                        r0 = OneKeyForwardBigVideoActivity.this;
                                                                                    } catch (Throwable th) {
                                                                                        AlbumUtil.insertVideoToMediaStore2(OneKeyForwardBigVideoActivity.this, str);
                                                                                        throw th;
                                                                                    }
                                                                                    AlbumUtil.insertVideoToMediaStore2(r0, str);
                                                                                    OneKeyForwardBigVideoActivity.this.executeStartWx();
                                                                                } else if (OneKeyForwardBigVideoActivity.this.executeCount > 4) {
                                                                                    ToastUtils.showToast(OneKeyForwardBigVideoActivity.this, "自动帮你尝试了四次，由于视频太大了，实在达不到微信的规则了！抱歉！");
                                                                                } else {
                                                                                    OneKeyForwardBigVideoActivity.access$008(OneKeyForwardBigVideoActivity.this);
                                                                                    OneKeyForwardBigVideoActivity.this.startWx.performClick();
                                                                                }
                                                                            } catch (Exception e3) {
                                                                                try {
                                                                                    Thread.sleep(500);
                                                                                } catch (InterruptedException e4) {
                                                                                    e4.printStackTrace();
                                                                                } catch (Throwable th2) {
                                                                                    AlbumUtil.insertVideoToMediaStore2(OneKeyForwardBigVideoActivity.this, str);
                                                                                    throw th2;
                                                                                }
                                                                                AlbumUtil.insertVideoToMediaStore2(OneKeyForwardBigVideoActivity.this, str);
                                                                                OneKeyForwardBigVideoActivity.this.executeStartWx();
                                                                            }
                                                                        }
                                                                    }
                                                                });
                                                            }

                                                            public void onProgress(final int i, long j) {
                                                                OneKeyForwardBigVideoActivity.this.runOnUiThread(new Runnable() {
                                                                    public void run() {
                                                                        PrintStream printStream = System.out;
                                                                        printStream.println("BIG_VIDEO_progress:" + i);
                                                                        if (OneKeyForwardBigVideoActivity.this.executeCount == 1) {
                                                                            OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity = OneKeyForwardBigVideoActivity.this;
                                                                            oneKeyForwardBigVideoActivity.updateLoadingDialog("二次处理 " + i + " %");
                                                                        } else if (OneKeyForwardBigVideoActivity.this.executeCount == 2) {
                                                                            OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity2 = OneKeyForwardBigVideoActivity.this;
                                                                            oneKeyForwardBigVideoActivity2.updateLoadingDialog("三次处理 " + i + " %");
                                                                        } else if (OneKeyForwardBigVideoActivity.this.executeCount == 3) {
                                                                            OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity3 = OneKeyForwardBigVideoActivity.this;
                                                                            oneKeyForwardBigVideoActivity3.updateLoadingDialog("四次处理 " + i + " %");
                                                                        } else {
                                                                            OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity4 = OneKeyForwardBigVideoActivity.this;
                                                                            oneKeyForwardBigVideoActivity4.updateLoadingDialog("视频处理 " + i + " %");
                                                                        }
                                                                    }
                                                                });
                                                            }
                                                        });
                                                        System.out.println("BIG_VIDEO_INPUT_PATH:" + ((String) OneKeyForwardBigVideoActivity.this.selectImgs.get(0)));
                                                        System.out.println("BIG_VIDEO_OUT_PATH:" + str);
                                                        convert.convertVideo(OneKeyForwardBigVideoActivity.this.quality, (String) OneKeyForwardBigVideoActivity.this.selectImgs.get(0), str);
                                                    }
                                                }).start();
                                                return;
                                            }
                                            OneKeyForwardBigVideoActivity.this.compressSaveVideo();
                                        }
                                    }
                                });
                            }
                        });
                        return;
                    }
                } else {
                    return;
                }
            case 2131297636:
                WebViewActivity.startActivity(this, "发布大视频", QBangApis.FORWARD_BIG_VIDEO);
                return;
            default:
                return;
        }
    }
}
