package com.wx.assistants.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.wx.assistants.Enity.ContactScanBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.service.RecognizeService;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import gdut.bsx.share2.ShareContentType;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONObject;
import permission.PermissionListener;

public class AutoAddContactCameraActivity extends BaseActivity {
    private static final int REQUEST_CODE_GENERAL_BASIC = 106;
    private static final int REQUEST_CODE_PICK_IMAGE = 100;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297079)
    TextView numText;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297427)
    Button start_wx_tag;
    /* access modifiers changed from: private */
    public int type = 0;

    static {
        StubApp.interface11(6697);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    @OnClick({2131297052})
    public void onViewClicked() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 106 && i2 == -1) {
            System.out.println("来自拍照.成功");
            recognize(FileUtil.getSaveFile(StubApp.getOrigApplicationContext(getApplicationContext())).getAbsolutePath());
        }
        if (i != 100) {
            return;
        }
        if (i2 == -1) {
            System.out.println("来自相册.成功");
            recognize(getRealPathFromURI(intent.getData()));
            return;
        }
        System.out.println("来自相册.失败");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
    public void recognize(String str) {
        try {
            showLoadingDialog("正在识别");
            RecognizeService.recGeneralBasic(this, str, new RecognizeService.ServiceListener() {
                /* JADX WARNING: type inference failed for: r1v6, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
                /* JADX WARNING: type inference failed for: r4v1, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
                public void onResult(String str) {
                    String string;
                    List<String> extractNumbers;
                    AutoAddContactCameraActivity.this.dismissLoadingDialog();
                    PrintStream printStream = System.out;
                    printStream.println("Result = " + str);
                    if (str != null) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            HashSet<String> hashSet = new HashSet<>();
                            JSONArray jSONArray = jSONObject.getJSONArray("words_result");
                            if (jSONArray == null || jSONArray.length() <= 0) {
                                AutoAddContactCameraActivity.this.resultFail();
                                return;
                            }
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                                if (!(jSONObject2 == null || (string = jSONObject2.getString("words")) == null || "".equals(string) || (extractNumbers = AutoAddContactCameraActivity.this.extractNumbers(string)) == null || extractNumbers.size() <= 0)) {
                                    hashSet.addAll(extractNumbers);
                                }
                            }
                            if (hashSet.size() > 0) {
                                if (hashSet.size() > 0) {
                                    for (String number : hashSet) {
                                        ContactScanBean contactScanBean = new ContactScanBean();
                                        contactScanBean.checked = false;
                                        contactScanBean.setCreateTime(System.currentTimeMillis());
                                        contactScanBean.setNumber(number);
                                        SQLiteUtils.getInstance().addContactScan(contactScanBean);
                                    }
                                }
                                ? r4 = AutoAddContactCameraActivity.this;
                                DialogUIUtils.dialogDefault(r4, "识别结果", "系统识别到了" + hashSet.size() + "个手机号，已帮您保存到通讯录。", "", "确定", (View.OnClickListener) null, new View.OnClickListener() {
                                    /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
                                    public void onClick(View view) {
                                        AutoAddContactCameraActivity.this.startActivity(new Intent(AutoAddContactCameraActivity.this, AutoAddContactCameraAddressActivity.class));
                                    }
                                });
                            }
                            if (hashSet.size() == 0) {
                                DialogUIUtils.dialogDefault(AutoAddContactCameraActivity.this, "识别结果", AutoAddContactCameraActivity.this.type == 0 ? "系统未识别到手机号码！是否重新拍照" : "系统未识别到手机号码！是否重新选择", "取消", AutoAddContactCameraActivity.this.type == 0 ? "重新拍照" : "重新选择", (View.OnClickListener) null, new View.OnClickListener() {
                                    public void onClick(View view) {
                                        if (AutoAddContactCameraActivity.this.type == 0) {
                                            AutoAddContactCameraActivity.this.goCamera();
                                        } else {
                                            AutoAddContactCameraActivity.this.goLocalAlbum();
                                        }
                                    }
                                });
                            }
                            PrintStream printStream2 = System.out;
                            printStream2.println("Result = " + hashSet + ",size = " + hashSet.size());
                        } catch (Exception unused) {
                            AutoAddContactCameraActivity.this.resultFail();
                        }
                    } else {
                        AutoAddContactCameraActivity.this.resultFail();
                    }
                }
            });
        } catch (Exception unused) {
            dismissLoadingDialog();
        }
    }

    /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resultFail() {
        /*
            r7 = this;
            java.lang.String r1 = "识别结果"
            int r0 = r7.type
            if (r0 != 0) goto L_0x000c
            java.lang.String r0 = "系统未识别到手机号码！是否重新拍照"
        L_0x000a:
            r2 = r0
            goto L_0x0010
        L_0x000c:
            java.lang.String r0 = "系统未识别到手机号码！是否重新选择"
            goto L_0x000a
        L_0x0010:
            java.lang.String r3 = "取消"
            int r0 = r7.type
            if (r0 != 0) goto L_0x001c
            java.lang.String r0 = "重新拍照"
        L_0x001a:
            r4 = r0
            goto L_0x0020
        L_0x001c:
            java.lang.String r0 = "重新选择"
            goto L_0x001a
        L_0x0020:
            r5 = 0
            com.wx.assistants.activity.AutoAddContactCameraActivity$2 r6 = new com.wx.assistants.activity.AutoAddContactCameraActivity$2
            r6.<init>()
            r0 = r7
            com.wx.assistants.utils.DialogUIUtils.dialogDefault(r0, r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.AutoAddContactCameraActivity.resultFail():void");
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor;
        try {
            cursor = getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        } catch (Throwable th) {
            th.printStackTrace();
            cursor = null;
        }
        if (cursor == null) {
            return uri.getPath();
        }
        cursor.moveToFirst();
        String string = cursor.getString(cursor.getColumnIndex("_data"));
        cursor.close();
        return string;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    public void initView() {
        this.navRightText.setText("通讯录");
        this.navTitle.setText("拍照加好友");
        this.start_wx_tag.setText("从相册选择");
        this.startWx.setText("开启相机");
    }

    private boolean checkTokenStatus() {
        if (!MyApplication.hasBDGotToken) {
            Toast.makeText(StubApp.getOrigApplicationContext(getApplicationContext()), "识别初始化失败，请退出当前页，在进入重试", 1).show();
        }
        return MyApplication.hasBDGotToken;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
    private void initAccessTokenWithAkSk() {
        System.out.println("WS_BABY.initAccessTokenWithAkSk.0  ");
        try {
            showLoadingDialog("正在初始化");
            OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
                public void onResult(AccessToken accessToken) {
                    AutoAddContactCameraActivity.this.runOnUiThread(new Runnable() {
                        /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
                        public void run() {
                            AutoAddContactCameraActivity.this.dismissLoadingDialog();
                            ToastUtils.showToast(AutoAddContactCameraActivity.this, "初始化成功！");
                        }
                    });
                    String accessToken2 = accessToken.getAccessToken();
                    PrintStream printStream = System.out;
                    printStream.println("WS_BABY.initAccessTokenWithAkSk.token = " + accessToken2);
                    MyApplication.hasBDGotToken = true;
                }

                public void onError(final OCRError oCRError) {
                    oCRError.printStackTrace();
                    AutoAddContactCameraActivity.this.runOnUiThread(new Runnable() {
                        /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
                        public void run() {
                            AutoAddContactCameraActivity.this.dismissLoadingDialog();
                            ? r0 = AutoAddContactCameraActivity.this;
                            ToastUtils.showToast(r0, "" + oCRError);
                        }
                    });
                    PrintStream printStream = System.out;
                    printStream.println("WS_BABY.initAccessTokenWithAkSk.error = " + oCRError);
                }
            }, StubApp.getOrigApplicationContext(getApplicationContext()), MyApplication.BD_OCR_KEY, MyApplication.BD_OCR_SK);
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
    @OnClick({2131297049, 2131297425, 2131297427, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            startActivity(new Intent(this, AutoAddContactCameraAddressActivity.class));
        } else if (id == 2131297425) {
            goCamera();
        } else if (id == 2131297427) {
            goLocalAlbum();
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
    public void goCamera() {
        if (checkTokenStatus()) {
            this.type = 0;
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtra("outputFilePath", FileUtil.getSaveFile(getApplication()).getAbsolutePath());
            intent.putExtra("contentType", "general");
            startActivityForResult(intent, 106);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.AutoAddContactCameraActivity] */
    public void goLocalAlbum() {
        if (checkTokenStatus()) {
            this.type = 1;
            PermissionUtils.checkReadAndWriteExternalStorage(this, new PermissionListener() {
                public void permissionDenied(@NonNull String[] strArr) {
                }

                public void permissionGranted(@NonNull String[] strArr) {
                    Intent intent = new Intent("android.intent.action.PICK");
                    intent.setType(ShareContentType.IMAGE);
                    AutoAddContactCameraActivity.this.startActivityForResult(intent, 100);
                }
            });
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, java.lang.Object, com.wx.assistants.activity.AutoAddContactCameraActivity] */
    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            if (OCR.getInstance(this) != null) {
                OCR.getInstance(this).release();
            }
        } catch (Exception unused) {
        }
        EventBus.getDefault().unregister(this);
    }

    public List<String> extractNumbers(String str) {
        String[] split;
        ArrayList arrayList = new ArrayList();
        String trim = Pattern.compile("[^0-9]").matcher(str).replaceAll("@").trim();
        if (trim != null && !"".equals(trim) && (split = trim.split("@")) != null && split.length > 0) {
            for (String str2 : split) {
                if (str2 != null && !"".equals(str2) && str2.length() > 11) {
                    str2 = str2.substring(0, 11);
                }
                if (str2 != null && !"".equals(str2) && str2.length() == 11 && !str2.startsWith("0")) {
                    arrayList.add(str2);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        int cameraNum = DateUtils.getCameraNum();
        TextView textView = this.numText;
        textView.setText("注：建议每天添加20位好友，您今日已添加" + cameraNum + "位。");
    }
}
