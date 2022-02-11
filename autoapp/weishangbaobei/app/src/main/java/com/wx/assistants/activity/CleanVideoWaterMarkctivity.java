package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.WaterMarkResultModel;
import com.wx.assistants.globle.ApiWrapper2;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.ApkDownUtils;
import com.wx.assistants.utils.CheckRuleValidateUtils;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.webview.WebViewActivity;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import java.io.PrintStream;
import java.util.Date;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class CleanVideoWaterMarkctivity extends BaseActivity {
    @BindView(2131296616)
    EditText editLeavingMessage;
    @BindView(2131296727)
    Button getBtn;
    /* access modifiers changed from: private */
    public boolean isCanClicked = true;
    @BindView(2131296867)
    JCVideoPlayer jcVideoPlayer;
    private int maxUseNum = 30;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297274)
    Button saveVideoBtn;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297377)
    LinearLayout showVideo;
    /* access modifiers changed from: private */
    public String videoUrl = "";

    static {
        StubApp.interface11(8523);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void initViewListener() {
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                boolean unused = CleanVideoWaterMarkctivity.this.isCanClicked = true;
                String unused2 = CleanVideoWaterMarkctivity.this.videoUrl = "";
            }
        });
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity, android.app.Activity] */
    @OnClick({2131297049, 2131296727, 2131297274, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296727) {
            checkUserRule();
        } else if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            WebViewActivity.startActivity(this, "视频去水印教程", QBangApis.VIDEO_CLEAN_VIDEO_WATER_MARK);
        } else if (id == 2131297274 && !"".equals(this.videoUrl)) {
            ApkDownUtils.getInstance(this).downVideoWaterMark(this.videoUrl);
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
    public void getVideo() {
        if (this.isCanClicked) {
            try {
                if (!DateUtils.convertDate2String(new Date()).equals((String) SPUtils.get(this, "clean_video_mark_date", "1986-03-17"))) {
                    SPUtils.put(this, "clean_video_mark_times", 0);
                    SPUtils.put(this, "clean_video_mark_date", DateUtils.convertDate2String(new Date()));
                }
                int intValue = ((Integer) SPUtils.get(this, "clean_video_mark_times", 0)).intValue();
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_video_" + intValue);
                if (intValue >= this.maxUseNum) {
                    ToastUtils.showToast(this, "每天最多使用 " + this.maxUseNum + " 次");
                    SPUtils.put(this, "clean_video_mark_date", DateUtils.convertDate2String(new Date()));
                    return;
                }
            } catch (Exception unused) {
            }
            String trim = this.editLeavingMessage.getText().toString().trim();
            if (trim == null || "".equals(trim)) {
                this.videoUrl = "";
                ToastUtils.showToast(this, "提取的视频地址不能为空");
                return;
            }
            showLoadingDialog("正在提取");
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            String str = new String(Hex.encodeHex(DigestUtils.md5(trim + valueOf + "a590dd763d16f9cc375469a48b5de583")));
            ApiWrapper2 instance = ApiWrapper2.getInstance();
            instance.getWaterMark(trim, valueOf + "", str, "944cd0718a62665e", new ApiWrapper2.CallbackListener2<WaterMarkResultModel>() {
                /* JADX WARNING: type inference failed for: r1v7, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
                /* JADX WARNING: type inference failed for: r2v2, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
                public void onFinish(final WaterMarkResultModel waterMarkResultModel) {
                    CleanVideoWaterMarkctivity.this.dismissLoadingDialog();
                    if (waterMarkResultModel.isSucc()) {
                        try {
                            SPUtils.put(CleanVideoWaterMarkctivity.this, "clean_video_mark_times", Integer.valueOf(((Integer) SPUtils.get(CleanVideoWaterMarkctivity.this, "clean_video_mark_times", 0)).intValue() + 1));
                        } catch (Exception unused) {
                        }
                        CleanVideoWaterMarkctivity.this.showVideo.setVisibility(0);
                        WaterMarkResultModel.WaterMarkResultBean data = waterMarkResultModel.getData();
                        if (data != null) {
                            boolean unused2 = CleanVideoWaterMarkctivity.this.isCanClicked = false;
                            String unused3 = CleanVideoWaterMarkctivity.this.videoUrl = data.getVideo();
                            CleanVideoWaterMarkctivity.this.jcVideoPlayer.setUp(CleanVideoWaterMarkctivity.this.videoUrl, data.getCover(), "");
                            return;
                        }
                        return;
                    }
                    CleanVideoWaterMarkctivity.this.runOnUiThread(new Runnable() {
                        /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
                        public void run() {
                            String unused = CleanVideoWaterMarkctivity.this.videoUrl = "";
                            ToastUtils.showToast(CleanVideoWaterMarkctivity.this, waterMarkResultModel.getRetDesc());
                        }
                    });
                }

                public void onFailure(FailureModel failureModel) {
                    CleanVideoWaterMarkctivity.this.dismissLoadingDialog();
                    LogUtils.log("ws_baby" + failureModel);
                    CleanVideoWaterMarkctivity.this.runOnUiThread(new Runnable() {
                        /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
                        public void run() {
                            String unused = CleanVideoWaterMarkctivity.this.videoUrl = "";
                            ToastUtils.showToast(CleanVideoWaterMarkctivity.this, "视频提取失败");
                        }
                    });
                }
            });
            return;
        }
        ToastUtils.showToast(this, "请不要重复获取奥");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity, android.app.Activity] */
    public void checkUserRule() {
        if (!"".equals((String) SPUtils.get(this, "ws_baby_phone", ""))) {
            showLoadingDialog("请耐心等待");
            CheckRuleValidateUtils.getInstance(this).checkRuleValidate("41", new CheckRuleValidateUtils.CheckResultListener() {
                public void permitExecution(Object obj) {
                    CleanVideoWaterMarkctivity.this.dismissLoadingDialog();
                    CleanVideoWaterMarkctivity.this.getVideo();
                }

                public void nilPermitExecution(Object obj) {
                    CleanVideoWaterMarkctivity.this.dismissLoadingDialog();
                }

                public void permitError(ConmdBean conmdBean) {
                    CleanVideoWaterMarkctivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(MyApplication.getConText(), conmdBean.getMessage());
                }

                /* JADX WARNING: type inference failed for: r4v2, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
                public void changeDevice(Object obj) {
                    CleanVideoWaterMarkctivity.this.dismissLoadingDialog();
                    DialogUIUtils.dialogChangerDevice(CleanVideoWaterMarkctivity.this, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r1v1, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
                        public void onClick(View view) {
                            CleanVideoWaterMarkctivity.this.startActivity(new Intent(CleanVideoWaterMarkctivity.this, MemberCenterListActivity.class));
                        }
                    }, new View.OnClickListener() {
                        /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.CleanVideoWaterMarkctivity] */
                        public void onClick(View view) {
                            CleanVideoWaterMarkctivity.this.startActivity(new Intent(CleanVideoWaterMarkctivity.this, DeviceChangeActivity.class));
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }
            });
            return;
        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("isNeedBack", true);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog();
    }
}
