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
import com.bluefox.moduel.weixinanyvideo.util.common.Util;
import com.fb.jjyyzjy.watermark.utils.AlbumUtil;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
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
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.webview.WebViewActivity;
import com.yongchun.library.adapter.ImageListAdapter;
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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
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
        } catch (Exception unused) {
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

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
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
                if (!PerformClickUtils.isFastClick()) {
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
                                                /* JADX WARNING: type inference failed for: r0v7, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                public void run() {
                                                    String str = FileUtils.getSDPath() + "/tencent/MicroMsg/WeiXin/" + System.currentTimeMillis() + PictureFileUtils.POST_VIDEO;
                                                    new Thread(new Runnable() {
                                                        public void run() {
                                                            OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                                /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                                public void run() {
                                                                    try {
                                                                        String str = (String) SPUtils.get(OneKeyChangeVideoCoverActivity.this, "last_video_add_cover_path", "");
                                                                        if (str != null && !"".equals(str)) {
                                                                            Util.delFile(str);
                                                                        }
                                                                    } catch (Exception unused) {
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }).start();
                                                    double quality = OneKeyChangeVideoCoverActivity.this.getQuality();
                                                    Convert convert = new Convert(OneKeyChangeVideoCoverActivity.this);
                                                    convert.setConvertListener(new ConvertListener() {
                                                        public void onError(String str) {
                                                            OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                                public void run() {
                                                                    OneKeyChangeVideoCoverActivity.this.dismissLoadingDialog();
                                                                }
                                                            });
                                                            PrintStream printStream = System.out;
                                                            printStream.println("BIG_VIDEO_progress:" + str);
                                                        }

                                                        public void invalid(int i) {
                                                            PrintStream printStream = System.out;
                                                            printStream.println("BIG_VIDEO_progress:" + i);
                                                        }

                                                        public void onProgress(final int i, long j) {
                                                            OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                                public void run() {
                                                                    OneKeyChangeVideoCoverActivity oneKeyChangeVideoCoverActivity = OneKeyChangeVideoCoverActivity.this;
                                                                    oneKeyChangeVideoCoverActivity.updateLoadingDialog("视频处理 " + i + " %");
                                                                }
                                                            });
                                                        }

                                                        public void onFinish(final String str) {
                                                            OneKeyChangeVideoCoverActivity.this.runOnUiThread(new Runnable() {
                                                                /* JADX WARNING: type inference failed for: r0v9, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                                /* JADX WARNING: type inference failed for: r1v16, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                                /* JADX WARNING: type inference failed for: r0v20, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                                /* JADX WARNING: type inference failed for: r0v28, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                                /* JADX WARNING: type inference failed for: r0v33, types: [android.content.Context, com.wx.assistants.activity.OneKeyChangeVideoCoverActivity] */
                                                                public void run() {
                                                                    try {
                                                                        if (str != null && !"".equals(str)) {
                                                                            SPUtils.put(OneKeyChangeVideoCoverActivity.this, "last_video_add_cover_path", str);
                                                                            ? r0 = OneKeyChangeVideoCoverActivity.this;
                                                                            ToastUtils.showToast(r0, "已保存到:" + str);
                                                                        }
                                                                    } catch (Exception unused) {
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
                                                                    } catch (InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    } catch (Throwable th) {
                                                                        AlbumUtil.insertVideoToMediaStore2(OneKeyChangeVideoCoverActivity.this, str);
                                                                        throw th;
                                                                    }
                                                                    AlbumUtil.insertVideoToMediaStore2(OneKeyChangeVideoCoverActivity.this, str);
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

    /* JADX WARNING: Can't wrap try/catch for region: R(10:1|2|(4:4|5|6|7)(2:8|(4:10|11|12|13)(2:14|(4:16|17|18|19)(2:20|(4:22|23|24|25)(2:26|(4:28|29|30|31)(2:32|(4:34|35|36|37)(2:38|(4:40|41|42|43)(2:44|(4:46|47|48|49)(2:50|(4:52|53|54|55)(2:56|(4:58|59|60|61)(2:62|(4:64|65|66|67)(2:68|(4:70|71|72|73)(2:74|(4:76|77|78|79)(2:80|(4:82|83|84|85)(2:86|(4:88|89|90|91)(2:92|(4:94|95|96|97)(2:98|(4:100|101|102|103)(2:104|(4:106|107|108|109)(2:110|(4:112|113|114|115)(2:116|(4:118|119|120|121)(2:122|(4:124|125|126|127)(2:128|(4:130|131|132|133)(2:134|(4:136|137|138|139)(2:140|(4:142|143|144|145)(2:146|(4:148|149|150|151)(2:152|(4:154|155|156|157)(2:158|(4:160|161|162|163)(2:164|(4:166|167|168|169)(2:170|(4:172|173|174|175)))))))))))))))))))))))))))))|176|177|178|179|180|181|182) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:179:0x02b6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double getQuality() {
        /*
            r11 = this;
            r0 = 4584304132692975288(0x3f9eb851eb851eb8, double:0.03)
            io.microshow.rxffmpeg.RxFFmpegInvoke r2 = io.microshow.rxffmpeg.RxFFmpegInvoke.getInstance()     // Catch:{ Exception -> 0x02dd }
            r3 = 0
            r2.setDebug(r3)     // Catch:{ Exception -> 0x02dd }
            io.microshow.rxffmpeg.RxFFmpegInvoke r2 = io.microshow.rxffmpeg.RxFFmpegInvoke.getInstance()     // Catch:{ Exception -> 0x02dd }
            java.util.ArrayList<java.lang.String> r4 = r11.selectVideos     // Catch:{ Exception -> 0x02dd }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ Exception -> 0x02dd }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x02dd }
            java.lang.String r2 = r2.getMediaInfo(r4)     // Catch:{ Exception -> 0x02dd }
            com.bluefox.moduel.weixinanyvideo.convert.MediaInfo r2 = com.bluefox.moduel.weixinanyvideo.convert.MediaInfo.parse(r2)     // Catch:{ Exception -> 0x02dd }
            long r4 = r2.duration     // Catch:{ Exception -> 0x02dd }
            r6 = 240000(0x3a980, double:1.18576E-318)
            r8 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x003e
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x003e:
            r6 = 230000(0x38270, double:1.13635E-318)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0054
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0054:
            r6 = 220000(0x35b60, double:1.086944E-318)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x006a
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x006a:
            r6 = 210000(0x33450, double:1.03754E-318)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0080
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0080:
            r6 = 200000(0x30d40, double:9.8813E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0096
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0096:
            r6 = 190000(0x2e630, double:9.38725E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x00ac
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x00ac:
            r6 = 180000(0x2bf20, double:8.8932E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x00c2
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x00c2:
            r6 = 170000(0x29810, double:8.3991E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x00d8
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x00d8:
            r6 = 160000(0x27100, double:7.90505E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x00ee
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x00ee:
            r6 = 140000(0x222e0, double:6.9169E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0104
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0104:
            r6 = 130000(0x1fbd0, double:6.42285E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x011a
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x011a:
            r6 = 125000(0x1e848, double:6.1758E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0130
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0130:
            r6 = 120000(0x1d4c0, double:5.9288E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0146
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0146:
            r6 = 115000(0x1c138, double:5.68175E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x015c
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x015c:
            r6 = 110000(0x1adb0, double:5.4347E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0172
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0172:
            r6 = 105000(0x19a28, double:5.1877E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0188
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0188:
            r6 = 100000(0x186a0, double:4.94066E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x019e
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x019e:
            r6 = 95000(0x17318, double:4.6936E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x01b4
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x01b4:
            r6 = 90000(0x15f90, double:4.4466E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x01ca
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x01ca:
            r6 = 85000(0x14c08, double:4.19956E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x01e0
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x01e0:
            r6 = 80000(0x13880, double:3.95253E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x01f6
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x01f6:
            r6 = 75000(0x124f8, double:3.7055E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x020c
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x020c:
            r6 = 70000(0x11170, double:3.45846E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0222
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0222:
            r6 = 65000(0xfde8, double:3.21143E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0237
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0237:
            r6 = 60000(0xea60, double:2.9644E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x024c
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x024c:
            r6 = 50000(0xc350, double:2.47033E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0261
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0261:
            r6 = 40000(0x9c40, double:1.97626E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x0276
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x0276:
            r6 = 30000(0x7530, double:1.4822E-319)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x028a
            int r4 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r4 = (double) r4
            java.lang.Double.isNaN(r4)
            double r4 = r4 * r8
            r6 = 0
            double r4 = r0 - r4
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
            goto L_0x029e
        L_0x028a:
            r6 = 15000(0x3a98, double:7.411E-320)
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x029e
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r6 = r11.executeCount     // Catch:{ Exception -> 0x02dd }
            double r6 = (double) r6
            java.lang.Double.isNaN(r6)
            double r6 = r6 * r8
            r8 = 0
            double r4 = r4 - r6
            r11.quality = r4     // Catch:{ Exception -> 0x02dd }
        L_0x029e:
            java.lang.String r4 = "%.3f"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x02b6 }
            double r6 = r11.quality     // Catch:{ Exception -> 0x02b6 }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ Exception -> 0x02b6 }
            r5[r3] = r6     // Catch:{ Exception -> 0x02b6 }
            java.lang.String r3 = java.lang.String.format(r4, r5)     // Catch:{ Exception -> 0x02b6 }
            double r3 = java.lang.Double.parseDouble(r3)     // Catch:{ Exception -> 0x02b6 }
            r11.quality = r3     // Catch:{ Exception -> 0x02b6 }
            goto L_0x02b8
        L_0x02b6:
            r11.quality = r0     // Catch:{ Exception -> 0x02dd }
        L_0x02b8:
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ Exception -> 0x02dd }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02dd }
            r4.<init>()     // Catch:{ Exception -> 0x02dd }
            java.lang.String r5 = "BIG_VIDEO_duration:"
            r4.append(r5)     // Catch:{ Exception -> 0x02dd }
            long r5 = r2.duration     // Catch:{ Exception -> 0x02dd }
            r4.append(r5)     // Catch:{ Exception -> 0x02dd }
            java.lang.String r2 = ","
            r4.append(r2)     // Catch:{ Exception -> 0x02dd }
            double r5 = r11.quality     // Catch:{ Exception -> 0x02dd }
            r4.append(r5)     // Catch:{ Exception -> 0x02dd }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x02dd }
            r3.println(r2)     // Catch:{ Exception -> 0x02dd }
            double r2 = r11.quality     // Catch:{ Exception -> 0x02dd }
            return r2
        L_0x02dd:
            r11.quality = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.OneKeyChangeVideoCoverActivity.getQuality():double");
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

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ImageListAdapter.max_duration = 300;
        EventBus.getDefault().unregister(this);
    }
}
