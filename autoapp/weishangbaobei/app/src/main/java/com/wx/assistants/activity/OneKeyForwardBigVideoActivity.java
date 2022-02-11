package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.webview.WebViewActivity;
import com.yongchun.library.adapter.ImageListAdapter;
import com.zhouyou.view.seekbar.SignSeekBar;
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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static /* synthetic */ int access$008(OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity) {
        int i = oneKeyForwardBigVideoActivity.executeCount;
        oneKeyForwardBigVideoActivity.executeCount = i + 1;
        return i;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
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
        } catch (Exception unused) {
        }
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
            public void getProgressOnFinally(SignSeekBar signSeekBar, int i, float f, boolean z) {
            }

            public void onProgressChanged(SignSeekBar signSeekBar, int i, float f, boolean z) {
            }

            public void getProgressOnActionUp(SignSeekBar signSeekBar, int i, float f) {
                int unused = OneKeyForwardBigVideoActivity.this.codeRate = i;
            }
        });
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardBigVideoActivity] */
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
                                    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                                    /* JADX WARNING: Missing exception handler attribute for start block: B:201:0x051d */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void checkEnd() {
                                        /*
                                            r15 = this;
                                            java.lang.String r0 = "WS_BABY_checkend"
                                            com.wx.assistants.utils.LogUtils.log(r0)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this
                                            java.util.ArrayList r0 = r0.selectImgs
                                            if (r0 == 0) goto L_0x056d
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this
                                            java.util.ArrayList r0 = r0.selectImgs
                                            int r0 = r0.size()
                                            if (r0 <= 0) goto L_0x056d
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this
                                            java.util.ArrayList r0 = r0.selectImgs
                                            r1 = 0
                                            java.lang.Object r0 = r0.get(r1)
                                            java.lang.String r0 = (java.lang.String) r0
                                            java.lang.String r2 = ".mp4"
                                            boolean r0 = r0.endsWith(r2)
                                            if (r0 == 0) goto L_0x056d
                                            r0 = 1
                                            io.microshow.rxffmpeg.RxFFmpegInvoke r2 = io.microshow.rxffmpeg.RxFFmpegInvoke.getInstance()     // Catch:{ Exception -> 0x054c }
                                            r2.setDebug(r1)     // Catch:{ Exception -> 0x054c }
                                            io.microshow.rxffmpeg.RxFFmpegInvoke r2 = io.microshow.rxffmpeg.RxFFmpegInvoke.getInstance()     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            java.util.ArrayList r3 = r3.selectImgs     // Catch:{ Exception -> 0x054c }
                                            java.lang.Object r3 = r3.get(r1)     // Catch:{ Exception -> 0x054c }
                                            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x054c }
                                            java.lang.String r2 = r2.getMediaInfo(r3)     // Catch:{ Exception -> 0x054c }
                                            com.bluefox.moduel.weixinanyvideo.convert.MediaInfo r2 = com.bluefox.moduel.weixinanyvideo.convert.MediaInfo.parse(r2)     // Catch:{ Exception -> 0x054c }
                                            long r3 = r2.duration     // Catch:{ Exception -> 0x054c }
                                            r5 = 240000(0x3a980, double:1.18576E-318)
                                            r7 = 15000(0x3a98, double:7.411E-320)
                                            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
                                            r11 = 4581421828931458171(0x3f947ae147ae147b, double:0.02)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x0088
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4588807732320345784(0x3faeb851eb851eb8, double:0.06)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0088:
                                            r5 = 230000(0x38270, double:1.13635E-318)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x00ac
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4589708452245819884(0x3fb1eb851eb851ec, double:0.07)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x00ac:
                                            r5 = 220000(0x35b60, double:1.086944E-318)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x00d0
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4590429028186199163(0x3fb47ae147ae147b, double:0.08)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x00d0:
                                            r5 = 210000(0x33450, double:1.03754E-318)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x00f4
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4591149604126578442(0x3fb70a3d70a3d70a, double:0.09)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x00f4:
                                            r5 = 200000(0x30d40, double:9.8813E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x0118
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4591870180066957722(0x3fb999999999999a, double:0.1)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0118:
                                            r5 = 190000(0x2e630, double:9.38725E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x013c
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4592590756007337001(0x3fbc28f5c28f5c29, double:0.11)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x013c:
                                            r5 = 180000(0x2bf20, double:8.8932E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x0160
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4593311331947716280(0x3fbeb851eb851eb8, double:0.12)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0160:
                                            r5 = 170000(0x29810, double:8.3991E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x0184
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4593851763903000740(0x3fc0a3d70a3d70a4, double:0.13)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0184:
                                            r5 = 160000(0x27100, double:7.90505E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x01a8
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4594212051873190380(0x3fc1eb851eb851ec, double:0.14)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x01a8:
                                            r5 = 140000(0x222e0, double:6.9169E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x01cc
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4594572339843380019(0x3fc3333333333333, double:0.15)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x01cc:
                                            r5 = 130000(0x1fbd0, double:6.42285E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x01f0
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4594932627813569659(0x3fc47ae147ae147b, double:0.16)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x01f0:
                                            r5 = 125000(0x1e848, double:6.1758E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x0214
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4595292915783759299(0x3fc5c28f5c28f5c3, double:0.17)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0214:
                                            r5 = 120000(0x1d4c0, double:5.9288E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x0238
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4595653203753948938(0x3fc70a3d70a3d70a, double:0.18)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0238:
                                            r5 = 115000(0x1c138, double:5.68175E-319)
                                            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r9 <= 0) goto L_0x025c
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4596013491724138578(0x3fc851eb851eb852, double:0.19)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r9 = (double) r6
                                            java.lang.Double.isNaN(r9)
                                            double r9 = r9 * r11
                                            r6 = 0
                                            double r4 = r4 - r9
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x025c:
                                            r5 = 110000(0x1adb0, double:5.4347E-319)
                                            r9 = 4584304132692975288(0x3f9eb851eb851eb8, double:0.03)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x0285
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4596373779694328218(0x3fc999999999999a, double:0.2)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0285:
                                            r5 = 105000(0x19a28, double:5.1877E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x02a9
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4596734067664517857(0x3fcae147ae147ae1, double:0.21)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x02a9:
                                            r5 = 100000(0x186a0, double:4.94066E-319)
                                            r9 = 4585925428558828667(0x3fa47ae147ae147b, double:0.04)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x02d2
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4597094355634707497(0x3fcc28f5c28f5c29, double:0.22)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x02d2:
                                            r5 = 95000(0x17318, double:4.6936E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x02f6
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4597454643604897137(0x3fcd70a3d70a3d71, double:0.23)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x02f6:
                                            r5 = 90000(0x15f90, double:4.4466E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x031a
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4597814931575086776(0x3fceb851eb851eb8, double:0.24)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x031a:
                                            r5 = 85000(0x14c08, double:4.19956E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x033b
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4598175219545276416(0x3fd0000000000000, double:0.25)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x033b:
                                            r5 = 80000(0x13880, double:3.95253E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x035f
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4598355363530371236(0x3fd0a3d70a3d70a4, double:0.26)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x035f:
                                            r5 = 75000(0x124f8, double:3.7055E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x0383
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4598535507515466056(0x3fd147ae147ae148, double:0.27)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0383:
                                            r5 = 70000(0x11170, double:3.45846E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x03a7
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4598715651500560876(0x3fd1eb851eb851ec, double:0.28)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x03a7:
                                            r5 = 65000(0xfde8, double:3.21143E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x03cb
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4598895795485655695(0x3fd28f5c28f5c28f, double:0.29)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x03cb:
                                            r5 = 60000(0xea60, double:2.9644E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x03ef
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4599075939470750515(0x3fd3333333333333, double:0.3)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x03ef:
                                            r5 = 50000(0xc350, double:2.47033E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x0412
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4599976659396224614(0x3fd6666666666666, double:0.35)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x0412:
                                            r5 = 40000(0x9c40, double:1.97626E-319)
                                            r9 = 4587366580439587226(0x3fa999999999999a, double:0.05)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x043a
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4600877379321698714(0x3fd999999999999a, double:0.4)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x043a:
                                            r5 = 30000(0x7530, double:1.4822E-319)
                                            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                                            if (r13 <= 0) goto L_0x045c
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4601778099247172813(0x3fdccccccccccccd, double:0.45)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                            goto L_0x047b
                                        L_0x045c:
                                            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
                                            if (r5 <= 0) goto L_0x047b
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r3 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            r4 = 4603129179135383962(0x3fe199999999999a, double:0.55)
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054c }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054c }
                                            int r6 = r6.executeCount     // Catch:{ Exception -> 0x054c }
                                            double r13 = (double) r6
                                            java.lang.Double.isNaN(r13)
                                            double r13 = r13 * r9
                                            r6 = 0
                                            double r4 = r4 - r13
                                            r3.quality = r4     // Catch:{ Exception -> 0x054c }
                                        L_0x047b:
                                            long r3 = r2.duration     // Catch:{ Exception -> 0x054c }
                                            int r5 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
                                            if (r5 > 0) goto L_0x0483
                                            r3 = 0
                                            goto L_0x0484
                                        L_0x0483:
                                            r3 = 1
                                        L_0x0484:
                                            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ Exception -> 0x054d }
                                            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x054d }
                                            r5.<init>()     // Catch:{ Exception -> 0x054d }
                                            java.lang.String r6 = "BIG_VIDEO_rate_1_"
                                            r5.append(r6)     // Catch:{ Exception -> 0x054d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054d }
                                            double r6 = r6.quality     // Catch:{ Exception -> 0x054d }
                                            r5.append(r6)     // Catch:{ Exception -> 0x054d }
                                            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x054d }
                                            r4.println(r5)     // Catch:{ Exception -> 0x054d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r4 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r4 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            int r4 = r4.codeRate     // Catch:{ Exception -> 0x051d }
                                            r5 = 20
                                            if (r4 >= r5) goto L_0x051d
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r4 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r4 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            int r4 = r4.codeRate     // Catch:{ Exception -> 0x051d }
                                            int r5 = r5 - r4
                                            double r4 = (double) r5
                                            r6 = 4636737291354636288(0x4059000000000000, double:100.0)
                                            java.lang.Double.isNaN(r4)
                                            double r4 = r4 / r6
                                            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ Exception -> 0x051d }
                                            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x051d }
                                            r7.<init>()     // Catch:{ Exception -> 0x051d }
                                            java.lang.String r8 = "BIG_VIDEO_rate_2_"
                                            r7.append(r8)     // Catch:{ Exception -> 0x051d }
                                            r7.append(r4)     // Catch:{ Exception -> 0x051d }
                                            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x051d }
                                            r6.println(r7)     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            double r6 = r6.quality     // Catch:{ Exception -> 0x051d }
                                            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                                            if (r8 <= 0) goto L_0x0517
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            double r6 = r6.quality     // Catch:{ Exception -> 0x051d }
                                            r8 = 0
                                            double r6 = r6 - r4
                                            r8 = 0
                                            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                                            if (r10 <= 0) goto L_0x0517
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r6 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r7 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r7 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            double r7 = r7.quality     // Catch:{ Exception -> 0x051d }
                                            r9 = 0
                                            double r7 = r7 - r4
                                            r6.quality = r7     // Catch:{ Exception -> 0x051d }
                                            java.lang.String r4 = "%.2f"
                                            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r5 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r5 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            double r5 = r5.quality     // Catch:{ Exception -> 0x051d }
                                            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ Exception -> 0x051d }
                                            r0[r1] = r5     // Catch:{ Exception -> 0x051d }
                                            java.lang.String r0 = java.lang.String.format(r4, r0)     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r4 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r4 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            double r5 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x051d }
                                            r4.quality = r5     // Catch:{ Exception -> 0x051d }
                                            goto L_0x051d
                                        L_0x0517:
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x051d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x051d }
                                            r0.quality = r11     // Catch:{ Exception -> 0x051d }
                                        L_0x051d:
                                            java.io.PrintStream r0 = java.lang.System.out     // Catch:{ Exception -> 0x054d }
                                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x054d }
                                            r4.<init>()     // Catch:{ Exception -> 0x054d }
                                            java.lang.String r5 = "BIG_VIDEO_duration:"
                                            r4.append(r5)     // Catch:{ Exception -> 0x054d }
                                            long r5 = r2.duration     // Catch:{ Exception -> 0x054d }
                                            r4.append(r5)     // Catch:{ Exception -> 0x054d }
                                            java.lang.String r2 = ","
                                            r4.append(r2)     // Catch:{ Exception -> 0x054d }
                                            r4.append(r3)     // Catch:{ Exception -> 0x054d }
                                            java.lang.String r2 = ","
                                            r4.append(r2)     // Catch:{ Exception -> 0x054d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r2 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this     // Catch:{ Exception -> 0x054d }
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r2 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this     // Catch:{ Exception -> 0x054d }
                                            double r5 = r2.quality     // Catch:{ Exception -> 0x054d }
                                            r4.append(r5)     // Catch:{ Exception -> 0x054d }
                                            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x054d }
                                            r0.println(r2)     // Catch:{ Exception -> 0x054d }
                                            goto L_0x054d
                                        L_0x054c:
                                            r3 = 1
                                        L_0x054d:
                                            if (r3 == 0) goto L_0x0566
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this
                                            java.lang.String r2 = "正在处理视频"
                                            r0.showLoadingDialog(r2, r1)
                                            java.lang.Thread r0 = new java.lang.Thread
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8$1$1 r1 = new com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8$1$1
                                            r1.<init>()
                                            r0.<init>(r1)
                                            r0.start()
                                            goto L_0x056d
                                        L_0x0566:
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity$8 r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.this
                                            com.wx.assistants.activity.OneKeyForwardBigVideoActivity r0 = com.wx.assistants.activity.OneKeyForwardBigVideoActivity.this
                                            r0.compressSaveVideo()
                                        L_0x056d:
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.OneKeyForwardBigVideoActivity.AnonymousClass8.AnonymousClass1.checkEnd():void");
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

    public void compressSaveVideo() {
        showLoadingDialog("正在处理视频", false);
        compressSaveVideo((List<String>) this.selectImgs, (FileUtil.OnConvertMp4Listener) new FileUtil.OnConvertMp4Listener() {
            public void convertSuccess(String str) {
                OneKeyForwardBigVideoActivity.this.dismissLoadingDialog();
                OneKeyForwardBigVideoActivity.this.executeStartWx();
            }

            public void convertProgress(int i) {
                OneKeyForwardBigVideoActivity oneKeyForwardBigVideoActivity = OneKeyForwardBigVideoActivity.this;
                oneKeyForwardBigVideoActivity.updateLoadingDialog("视频处理 " + i + " %");
            }

            public void convertFail() {
                OneKeyForwardBigVideoActivity.this.dismissLoadingDialog();
            }
        });
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

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ImageListAdapter.max_duration = 300;
        EventBus.getDefault().unregister(this);
    }
}
