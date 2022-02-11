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
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.webview.WebViewActivity;
import com.yongchun.library.adapter.ImageListAdapter;
import io.microshow.rxffmpeg.RxFFmpegInvoke;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyChangeVideoCoverActivity extends BaseActivity {
    @BindView(2131296319)
    LinearLayout addCoverLayout;
    @BindView(2131296322)
    LinearLayout addVideoLayout;
    @BindView(2131296505)
    LinearLayout cleanCover;
    @BindView(2131296511)
    LinearLayout cleanVideo;
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
    @BindView(2131297308)
    ImageView selectCover;
    /* access modifiers changed from: private */
    public ArrayList<String> selectCovers = new ArrayList<>();
    @BindView(2131297320)
    ImageView selectVideo;
    /* access modifiers changed from: private */
    public ArrayList<String> selectVideos = new ArrayList<>();
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
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
        StubApp.interface11(6745);
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

    public double getQuality() {
        try {
            RxFFmpegInvoke.getInstance().setDebug(false);
            MediaInfo parse = MediaInfo.parse(RxFFmpegInvoke.getInstance().getMediaInfo(this.selectVideos.get(0)));
            long j = parse.duration;
            if (j > 240000) {
                double d = (double) this.executeCount;
                Double.isNaN(d);
                this.quality = 0.03d - (d * 0.01d);
            } else if (j > 230000) {
                double d2 = (double) this.executeCount;
                Double.isNaN(d2);
                this.quality = 0.03d - (d2 * 0.01d);
            } else if (j > 220000) {
                double d3 = (double) this.executeCount;
                Double.isNaN(d3);
                this.quality = 0.03d - (d3 * 0.01d);
            } else if (j > 210000) {
                double d4 = (double) this.executeCount;
                Double.isNaN(d4);
                this.quality = 0.03d - (d4 * 0.01d);
            } else if (j > 200000) {
                double d5 = (double) this.executeCount;
                Double.isNaN(d5);
                this.quality = 0.03d - (d5 * 0.01d);
            } else if (j > 190000) {
                double d6 = (double) this.executeCount;
                Double.isNaN(d6);
                this.quality = 0.03d - (d6 * 0.01d);
            } else if (j > 180000) {
                double d7 = (double) this.executeCount;
                Double.isNaN(d7);
                this.quality = 0.03d - (d7 * 0.01d);
            } else if (j > 170000) {
                double d8 = (double) this.executeCount;
                Double.isNaN(d8);
                this.quality = 0.03d - (d8 * 0.01d);
            } else if (j > 160000) {
                double d9 = (double) this.executeCount;
                Double.isNaN(d9);
                this.quality = 0.03d - (d9 * 0.01d);
            } else if (j > 140000) {
                double d10 = (double) this.executeCount;
                Double.isNaN(d10);
                this.quality = 0.03d - (d10 * 0.01d);
            } else if (j > 130000) {
                double d11 = (double) this.executeCount;
                Double.isNaN(d11);
                this.quality = 0.03d - (d11 * 0.01d);
            } else if (j > 125000) {
                double d12 = (double) this.executeCount;
                Double.isNaN(d12);
                this.quality = 0.03d - (d12 * 0.01d);
            } else if (j > 120000) {
                double d13 = (double) this.executeCount;
                Double.isNaN(d13);
                this.quality = 0.03d - (d13 * 0.01d);
            } else if (j > 115000) {
                double d14 = (double) this.executeCount;
                Double.isNaN(d14);
                this.quality = 0.03d - (d14 * 0.01d);
            } else if (j > 110000) {
                double d15 = (double) this.executeCount;
                Double.isNaN(d15);
                this.quality = 0.03d - (d15 * 0.01d);
            } else if (j > 105000) {
                double d16 = (double) this.executeCount;
                Double.isNaN(d16);
                this.quality = 0.03d - (d16 * 0.01d);
            } else if (j > 100000) {
                double d17 = (double) this.executeCount;
                Double.isNaN(d17);
                this.quality = 0.03d - (d17 * 0.01d);
            } else if (j > 95000) {
                double d18 = (double) this.executeCount;
                Double.isNaN(d18);
                this.quality = 0.03d - (d18 * 0.01d);
            } else if (j > 90000) {
                double d19 = (double) this.executeCount;
                Double.isNaN(d19);
                this.quality = 0.03d - (d19 * 0.01d);
            } else if (j > 85000) {
                double d20 = (double) this.executeCount;
                Double.isNaN(d20);
                this.quality = 0.03d - (d20 * 0.01d);
            } else if (j > 80000) {
                double d21 = (double) this.executeCount;
                Double.isNaN(d21);
                this.quality = 0.03d - (d21 * 0.01d);
            } else if (j > 75000) {
                double d22 = (double) this.executeCount;
                Double.isNaN(d22);
                this.quality = 0.03d - (d22 * 0.01d);
            } else if (j > 70000) {
                double d23 = (double) this.executeCount;
                Double.isNaN(d23);
                this.quality = 0.03d - (d23 * 0.01d);
            } else if (j > 65000) {
                double d24 = (double) this.executeCount;
                Double.isNaN(d24);
                this.quality = 0.03d - (d24 * 0.01d);
            } else if (j > 60000) {
                double d25 = (double) this.executeCount;
                Double.isNaN(d25);
                this.quality = 0.03d - (d25 * 0.01d);
            } else if (j > 50000) {
                double d26 = (double) this.executeCount;
                Double.isNaN(d26);
                this.quality = 0.03d - (d26 * 0.01d);
            } else if (j > 40000) {
                double d27 = (double) this.executeCount;
                Double.isNaN(d27);
                this.quality = 0.03d - (d27 * 0.01d);
            } else if (j > c.d) {
                double d28 = (double) this.executeCount;
                Double.isNaN(d28);
                this.quality = 0.03d - (d28 * 0.01d);
            } else if (j > 15000) {
                double d29 = (double) this.executeCount;
                Double.isNaN(d29);
                this.quality = 1.0d - (d29 * 0.01d);
            }
            try {
                this.quality = Double.parseDouble(String.format("%.3f", new Object[]{Double.valueOf(this.quality)}));
            } catch (Exception e) {
                this.quality = 0.03d;
            }
            PrintStream printStream = System.out;
            printStream.println("BIG_VIDEO_duration:" + parse.duration + ListUtils.DEFAULT_JOIN_SEPARATOR + this.quality);
            return this.quality;
        } catch (Exception e2) {
            this.quality = 0.03d;
            return 0.03d;
        }
    }

    public void initView() {
        this.navRightImg.setVisibility(0);
        ImageListAdapter.max_duration = 240;
        this.navTitle.setText("视频换封面");
        this.startWx.setText("保存到本地");
        this.selectVideo.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                int unused = OneKeyChangeVideoCoverActivity.this.executeCount = 0;
                OneKeyChangeVideoCoverActivity.this.imgLongClickPreview(OneKeyChangeVideoCoverActivity.this.selectVideos);
                return false;
            }
        });
        this.selectCover.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                int unused = OneKeyChangeVideoCoverActivity.this.executeCount = 0;
                OneKeyChangeVideoCoverActivity.this.imgLongClickPreview(OneKeyChangeVideoCoverActivity.this.selectCovers);
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
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
            String str = (String) SPUtils.get(MyApplication.getConText(), "video_origin_path", "");
            if (str != null && !"".equals(str)) {
                this.selectVideos = new ArrayList<>();
                this.selectVideos.add(str);
                this.addVideoLayout.setVisibility(8);
                this.selectVideo.setVisibility(0);
                this.cleanVideo.setVisibility(0);
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectVideo, str, new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            OneKeyChangeVideoCoverActivity.this.cleanVideo.performClick();
                        }
                    }
                });
            }
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "video_cover_origin_path", "");
            if (str2 != null && !"".equals(str2)) {
                this.selectCovers = new ArrayList<>();
                this.selectCovers.add(str2);
                this.addCoverLayout.setVisibility(8);
                this.selectCover.setVisibility(0);
                this.cleanCover.setVisibility(0);
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectCover, str2, new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            OneKeyChangeVideoCoverActivity.this.cleanCover.performClick();
                        }
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
    @OnClick({2131297052, 2131296511, 2131296505, 2131297049, 2131297425, 2131296322, 2131296319, 2131297320, 2131297308, 2131297636})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296319:
                selectSingleAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            int unused = OneKeyChangeVideoCoverActivity.this.executeCount = 0;
                            ArrayList unused2 = OneKeyChangeVideoCoverActivity.this.selectCovers = arrayList;
                            OneKeyChangeVideoCoverActivity.this.addCoverLayout.setVisibility(8);
                            OneKeyChangeVideoCoverActivity.this.selectCover.setVisibility(0);
                            OneKeyChangeVideoCoverActivity.this.cleanCover.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "video_cover_origin_path", OneKeyChangeVideoCoverActivity.this.selectCovers.get(0));
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyChangeVideoCoverActivity.this.selectCover, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyChangeVideoCoverActivity.this.cleanCover.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131296322:
                selectSingleVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            int unused = OneKeyChangeVideoCoverActivity.this.executeCount = 0;
                            ArrayList unused2 = OneKeyChangeVideoCoverActivity.this.selectVideos = arrayList;
                            OneKeyChangeVideoCoverActivity.this.addVideoLayout.setVisibility(8);
                            OneKeyChangeVideoCoverActivity.this.selectVideo.setVisibility(0);
                            OneKeyChangeVideoCoverActivity.this.cleanVideo.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "video_origin_path", OneKeyChangeVideoCoverActivity.this.selectVideos.get(0));
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyChangeVideoCoverActivity.this.selectVideo, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyChangeVideoCoverActivity.this.cleanVideo.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131296505:
                this.executeCount = 0;
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
                            OneKeyChangeVideoCoverActivity.this.cleanCover.performClick();
                        }
                    }
                });
                return;
            case 2131296511:
                this.executeCount = 0;
                this.cleanVideo.setVisibility(8);
                this.selectVideos = null;
                this.addVideoLayout.setVisibility(0);
                this.selectVideo.setVisibility(8);
                SPUtils.put(MyApplication.getConText(), "video_origin_path", "");
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectVideo, "", new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            OneKeyChangeVideoCoverActivity.this.cleanVideo.performClick();
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
            case 2131297308:
                selectSingleAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            int unused = OneKeyChangeVideoCoverActivity.this.executeCount = 0;
                            ArrayList unused2 = OneKeyChangeVideoCoverActivity.this.selectCovers = arrayList;
                            OneKeyChangeVideoCoverActivity.this.addCoverLayout.setVisibility(8);
                            OneKeyChangeVideoCoverActivity.this.selectCover.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "video_cover_origin_path", OneKeyChangeVideoCoverActivity.this.selectCovers.get(0));
                            OneKeyChangeVideoCoverActivity.this.cleanCover.setVisibility(0);
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyChangeVideoCoverActivity.this.selectCover, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyChangeVideoCoverActivity.this.cleanCover.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131297320:
                selectSingleVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                    public void result(ArrayList<String> arrayList) {
                        if (arrayList.size() > 0) {
                            int unused = OneKeyChangeVideoCoverActivity.this.executeCount = 0;
                            ArrayList unused2 = OneKeyChangeVideoCoverActivity.this.selectVideos = arrayList;
                            OneKeyChangeVideoCoverActivity.this.addVideoLayout.setVisibility(8);
                            OneKeyChangeVideoCoverActivity.this.selectVideo.setVisibility(0);
                            SPUtils.put(MyApplication.getConText(), "video_origin_path", OneKeyChangeVideoCoverActivity.this.selectVideos.get(0));
                            OneKeyChangeVideoCoverActivity.this.cleanVideo.setVisibility(0);
                            GlideUtils.showImageViewGone(MyApplication.getConText(), OneKeyChangeVideoCoverActivity.this.selectVideo, arrayList.get(0), new GlideUtils.OnLoadImgListener() {
                                public void loadResult(int i) {
                                    PrintStream printStream = System.out;
                                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                    if (i == 1) {
                                        OneKeyChangeVideoCoverActivity.this.cleanVideo.performClick();
                                    }
                                }
                            });
                        }
                    }
                });
                return;
            case 2131297425:
                if (PerformClickUtils.isFastClick()) {
                    return;
                }
                if (this.selectVideos == null || this.selectVideos.size() == 0) {
                    ToastUtils.showToast(this, "请设置视频");
                    return;
                } else if (this.selectCovers == null || this.selectCovers.size() == 0) {
                    ToastUtils.showToast(this, "请设置封面");
                    return;
                } else {
                    PermissionUtils.checkReadAndWriteExternalStorage(this, new PermissionListener() {
                        public void permissionDenied(@NonNull String[] strArr) {
                        }

                        public void permissionGranted(@NonNull String[] strArr) {
                            OneKeyChangeVideoCoverActivity.this.checkMember("5", new BaseActivity.OnStartCheckListener() {
                                public void checkEnd() {
                                    LogUtils.log("WS_BABY_checkend");
                                    if (OneKeyChangeVideoCoverActivity.this.selectVideos != null && OneKeyChangeVideoCoverActivity.this.selectVideos.size() > 0 && ((String) OneKeyChangeVideoCoverActivity.this.selectVideos.get(0)).endsWith(PictureFileUtils.POST_VIDEO)) {
                                        OneKeyChangeVideoCoverActivity.this.showLoadingDialog("正在处理视频", false);
                                        new Thread(new Runnable() {
                                            /* JADX WARNING: type inference failed for: r1v6, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                            public void run() {
                                                String str = FileUtils.getSDPath() + "/tencent/MicroMsg/WeiXin/" + System.currentTimeMillis() + PictureFileUtils.POST_VIDEO;
                                                new Thread(new Runnable() {
                                                    public void run() {
                                                        OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                            /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                            public void run() {
                                                                try {
                                                                    String str = (String) SPUtils.get(OneKeyChangeVideoCoverActivity.this, "last_video_add_cover_path", "");
                                                                    if (str != null && !"".equals(str)) {
                                                                        Util.delFile(str);
                                                                    }
                                                                } catch (Exception e) {
                                                                }
                                                            }
                                                        });
                                                    }
                                                }).start();
                                                double quality = OneKeyChangeVideoCoverActivity.this.getQuality();
                                                Convert convert = new Convert(OneKeyChangeVideoCoverActivity.this);
                                                convert.setConvertListener(new ConvertListener() {
                                                    public void invalid(int i) {
                                                        PrintStream printStream = System.out;
                                                        printStream.println("BIG_VIDEO_progress:" + i);
                                                    }

                                                    public void onError(String str) {
                                                        OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                            public void run() {
                                                                OneKeyChangeVideoCoverActivity.this.dismissLoadingDialog();
                                                            }
                                                        });
                                                        PrintStream printStream = System.out;
                                                        printStream.println("BIG_VIDEO_progress:" + str);
                                                    }

                                                    public void onFinish(final String str) {
                                                        OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                            /* JADX WARNING: type inference failed for: r0v10, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                            /* JADX WARNING: type inference failed for: r1v15, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                            /* JADX WARNING: type inference failed for: r0v35, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                            /* JADX WARNING: type inference failed for: r0v43, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                            /* JADX WARNING: type inference failed for: r0v48, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                            public void run() {
                                                                try {
                                                                    if (str != null && !"".equals(str)) {
                                                                        SPUtils.put(OneKeyChangeVideoCoverActivity.this, "last_video_add_cover_path", str);
                                                                        ? r0 = OneKeyChangeVideoCoverActivity.this;
                                                                        ToastUtils.showToast(r0, "已保存到:" + str);
                                                                    }
                                                                } catch (Exception e) {
                                                                }
                                                                OneKeyChangeVideoCoverActivity.this.dismissLoadingDialog();
                                                                AlbumUtil.insertVideoToMediaStore2(OneKeyChangeVideoCoverActivity.this, str);
                                                                PrintStream printStream = System.out;
                                                                printStream.println("BIG_VIDEO_finish_origin---:" + OneKeyChangeVideoCoverActivity.this.formatFileSize(new File((String) OneKeyChangeVideoCoverActivity.this.selectVideos.get(0)).length()));
                                                                PrintStream printStream2 = System.out;
                                                                printStream2.println("BIG_VIDEO_finish---:" + OneKeyChangeVideoCoverActivity.this.formatFileSize(new File(str).length()));
                                                                ContentValues contentValues = new ContentValues();
                                                                contentValues.put("_data", str);
                                                                OneKeyChangeVideoCoverActivity.this.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                                                                try {
                                                                    Thread.sleep(500);
                                                                } catch (InterruptedException e2) {
                                                                    e2.printStackTrace();
                                                                } catch (Throwable th) {
                                                                    AlbumUtil.insertVideoToMediaStore2(OneKeyChangeVideoCoverActivity.this, str);
                                                                    throw th;
                                                                }
                                                                AlbumUtil.insertVideoToMediaStore2(OneKeyChangeVideoCoverActivity.this, str);
                                                            }
                                                        });
                                                    }

                                                    public void onProgress(final int i, long j) {
                                                        OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                            public void run() {
                                                                OneKeyChangeVideoCoverActivity oneKeyChangeVideoCoverActivity = OneKeyChangeVideoCoverActivity.this;
                                                                oneKeyChangeVideoCoverActivity.updateLoadingDialog("视频处理 " + i + " %");
                                                            }
                                                        });
                                                    }
                                                });
                                                System.out.println("BIG_VIDEO_PATH:" + ((String) OneKeyChangeVideoCoverActivity.this.selectVideos.get(0)));
                                                System.out.println("BIG_VIDEO_COVER_PATH:" + ((String) OneKeyChangeVideoCoverActivity.this.selectCovers.get(0)));
                                                System.out.println("BIG_VIDEO_OUT_PATH:" + str);
                                                convert.changeVideoCover((String) OneKeyChangeVideoCoverActivity.this.selectVideos.get(0), (String) OneKeyChangeVideoCoverActivity.this.selectCovers.get(0), str, quality);
                                            }
                                        }).start();
                                    }
                                }
                            });
                        }
                    });
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
