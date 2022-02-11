package com.wx.assistants.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.google.gson.internal.LinkedTreeMap;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.QRCodeUtil;
import com.wx.assistants.utils.SPUtils;
import java.util.ArrayList;

public class CompositionService extends Service {
    /* access modifiers changed from: private */
    public MyHandler handler = new MyHandler();
    /* access modifiers changed from: private */
    public ArrayList<String> imagesList;
    /* access modifiers changed from: private */
    public String inviteUrl = "";

    class MyHandler extends Handler {
        MyHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0 && CompositionService.this.imagesList.size() == 1) {
                LogUtils.log("CompositionService.end");
                if (CompositionService.this.inviteUrl != null) {
                    SPUtils.put(CompositionService.this, "inviteUrlCode", 5);
                    SPUtils.put(CompositionService.this, "inviteUrl", CompositionService.this.inviteUrl);
                }
            }
        }
    }

    public void initInviteData() {
        try {
            LogUtils.log("CompositionService.initInviteData");
            String str = (String) SPUtils.get(this, "ws_baby_phone", "");
            if (str != null && !"".equals(str)) {
                ApiWrapper.getInstance().getInviteData(new ApiWrapper.CallbackListener<ConmdBean>() {
                    public void onFailure(FailureModel failureModel) {
                    }

                    public void onFinish(ConmdBean conmdBean) {
                        if (conmdBean != null && conmdBean.getCode() == 0) {
                            try {
                                String unused = CompositionService.this.inviteUrl = (String) ((LinkedTreeMap) conmdBean.getData()).get("inviteUrl");
                                if (CompositionService.this.inviteUrl != null && !CompositionService.this.inviteUrl.equals("")) {
                                    MyApplication.inviteUrlStr = CompositionService.this.inviteUrl;
                                    String str = (String) SPUtils.get(CompositionService.this, "inviteUrl", "");
                                    int intValue = ((Integer) SPUtils.get(CompositionService.this, "inviteUrlCode", 0)).intValue();
                                    if ("".equals(str) || !CompositionService.this.inviteUrl.equals(str) || intValue != 5) {
                                        ArrayList unused2 = CompositionService.this.imagesList = new ArrayList();
                                        new Thread(new Runnable() {
                                            public void run() {
                                                try {
                                                    Bitmap decodeResource = BitmapFactory.decodeResource(CompositionService.this.getResources(), 2131230828);
                                                    LogUtils.log("bitmap-src1=" + decodeResource.getByteCount());
                                                    Rect transparentBounds = QRCodeUtil.getTransparentBounds(decodeResource);
                                                    Bitmap createQRCode = QRCodeUtil.createQRCode(CompositionService.this.inviteUrl, transparentBounds.right - transparentBounds.left);
                                                    LogUtils.log("bitmap-qrBitmap=" + createQRCode.getByteCount());
                                                    Bitmap createBitmap = QRCodeUtil.createBitmap(decodeResource, createQRCode);
                                                    LogUtils.log("bitmap-newBitmap1=" + createBitmap.getByteCount());
                                                    String bitmapToBase64 = QRCodeUtil.bitmapToBase64(createBitmap);
                                                    CompositionService.this.imagesList.add(bitmapToBase64);
                                                    SPUtils.put(CompositionService.this, "inviteBase64#1", bitmapToBase64);
                                                    CompositionService.this.handler.sendEmptyMessage(0);
                                                    LogUtils.log("CompositionService.1");
                                                } catch (Exception e) {
                                                }
                                            }
                                        }).start();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    }
                });
            }
        } catch (Exception e) {
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.log("CompositionService.onStartCommand");
        initInviteData();
        return super.onStartCommand(intent, i, i2);
    }
}
