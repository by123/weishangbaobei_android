package com.wx.assistants.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.gson.internal.LinkedTreeMap;
import com.simple.transformslibrary.CardSlideTransformer;
import com.simple.transformslibrary.TransformUtil;
import com.stub.StubApp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.dialog.AlertActionSheetDialog;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.QRCodeUtil;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class InviteFriendActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public MyViewAdapter adapter;
    @BindView(2131296373)
    ViewPager banner;
    /* access modifiers changed from: private */
    public int currentIndex = 0;
    /* access modifiers changed from: private */
    public byte[] current_decode;
    /* access modifiers changed from: private */
    public MyHandler handler = new MyHandler();
    /* access modifiers changed from: private */
    public ArrayList<String> imagesList;
    /* access modifiers changed from: private */
    public String inviteUrl = "";
    UMShareListener listener = new UMShareListener() {
        public void onCancel(SHARE_MEDIA share_media) {
        }

        public void onError(SHARE_MEDIA share_media, Throwable th) {
        }

        public void onResult(SHARE_MEDIA share_media) {
        }

        public void onStart(SHARE_MEDIA share_media) {
        }
    };
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297365)
    LinearLayout sharedQQLayout;
    @BindView(2131297366)
    LinearLayout sharedQzoneLayout;
    @BindView(2131297367)
    LinearLayout sharedWechatCircleLayout;
    @BindView(2131297368)
    LinearLayout sharedWechatLayout;
    List<View> viewList = new ArrayList();

    class MyHandler extends Handler {
        MyHandler() {
        }

        /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
        /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0 && InviteFriendActivity.this.imagesList.size() == 1) {
                SPUtils.put(InviteFriendActivity.this, "inviteUrl", InviteFriendActivity.this.inviteUrl);
                SPUtils.put(InviteFriendActivity.this, "inviteUrlCode", 5);
                LogUtils.log("WS_BABY_InviteFriendActivity.initInviteData.end");
                InviteFriendActivity.this.dismissLoadingDialog();
                byte[] unused = InviteFriendActivity.this.current_decode = Base64.decode((String) InviteFriendActivity.this.imagesList.get(0), 0);
                View inflate = InviteFriendActivity.this.getLayoutInflater().inflate(2131427779, (ViewGroup) null);
                ImageView imageView = (ImageView) inflate.findViewById(2131297640);
                final byte[] decode = Base64.decode((String) InviteFriendActivity.this.imagesList.get(0), 0);
                if (Util.isOnMainThread() && !InviteFriendActivity.this.isDestroyed()) {
                    Glide.with(MyApplication.getConText()).load(decode).into(imageView);
                }
                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
                    public boolean onLongClick(View view) {
                        DialogUIUtils.actionSheetSavePhotos(InviteFriendActivity.this, new AlertActionSheetDialog.OnSheetItemClickListener() {
                            /* JADX WARNING: type inference failed for: r1v2, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
                            public void onClick(int i) {
                                if (i == 1) {
                                    new FileUtil().saveSyn((Context) InviteFriendActivity.this, decode, (FileUtil.OnSaveCompletedListener) new FileUtil.OnSaveCompletedListener() {
                                        /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
                                        public void saveCompleted() {
                                            ToastUtils.showToast(InviteFriendActivity.this, "图片已保存到相册");
                                        }
                                    });
                                }
                            }
                        });
                        return false;
                    }
                });
                InviteFriendActivity.this.viewList.add(inflate);
                MyViewAdapter unused2 = InviteFriendActivity.this.adapter = new MyViewAdapter(InviteFriendActivity.this.viewList);
                InviteFriendActivity.this.banner.setAdapter(InviteFriendActivity.this.adapter);
                InviteFriendActivity.this.banner.addOnPageChangeListener(new OnMyPageChangeListener());
                InviteFriendActivity.this.banner.setOffscreenPageLimit(1);
                TransformUtil.reverse(InviteFriendActivity.this.banner, new CardSlideTransformer());
            }
        }
    }

    class MyViewAdapter extends PagerAdapter {
        private List<View> datas;

        public MyViewAdapter(List<View> list) {
            this.datas = list;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(this.datas.get(i));
        }

        public int getCount() {
            return this.datas.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = this.datas.get(i);
            viewGroup.addView(view);
            return view;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    private class OnMyPageChangeListener implements ViewPager.OnPageChangeListener {
        private OnMyPageChangeListener() {
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            int i2 = i % 1;
            LogUtils.log("WS_BABY_" + i2);
            int unused = InviteFriendActivity.this.currentIndex = i2;
            byte[] unused2 = InviteFriendActivity.this.current_decode = Base64.decode((String) InviteFriendActivity.this.imagesList.get(i2), 0);
        }
    }

    static {
        StubApp.interface11(8911);
    }

    public void initInviteData() {
        LogUtils.log("WS_BABY_InviteFriendActivity.initInviteData");
        ApiWrapper.getInstance().getInviteData(new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            public void onFailure(FailureModel failureModel) {
                if (failureModel != null) {
                    InviteFriendActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(InviteFriendActivity.this, failureModel.getErrorMessage());
                }
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            /* JADX WARNING: type inference failed for: r0v16, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            /* JADX WARNING: type inference failed for: r1v4, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            /* JADX WARNING: type inference failed for: r0v25, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            /* JADX WARNING: type inference failed for: r1v13, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            /* JADX WARNING: type inference failed for: r2v10, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            /* JADX WARNING: type inference failed for: r3v4, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean == null) {
                    return;
                }
                if (conmdBean.getCode() == 0) {
                    try {
                        String unused = InviteFriendActivity.this.inviteUrl = (String) ((LinkedTreeMap) conmdBean.getData()).get("inviteUrl");
                        if (InviteFriendActivity.this.inviteUrl != null && !InviteFriendActivity.this.inviteUrl.equals("")) {
                            MyApplication.inviteUrlStr = InviteFriendActivity.this.inviteUrl;
                            String str = (String) SPUtils.get(InviteFriendActivity.this, "inviteUrl", "");
                            int intValue = ((Integer) SPUtils.get(InviteFriendActivity.this, "inviteUrlCode", 0)).intValue();
                            if ("".equals(str) || !InviteFriendActivity.this.inviteUrl.equals(str) || intValue != 5) {
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        InviteFriendActivity.this.showLoadingDialog("初始化数据");
                                    }
                                }, 200);
                                ArrayList unused2 = InviteFriendActivity.this.imagesList = new ArrayList();
                                new Thread(new Runnable() {
                                    /* JADX WARNING: type inference failed for: r1v13, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity] */
                                    public void run() {
                                        Bitmap decodeResource = BitmapFactory.decodeResource(InviteFriendActivity.this.getResources(), 2131230828);
                                        LogUtils.log("bitmap-src1=" + decodeResource.getByteCount());
                                        Rect transparentBounds = QRCodeUtil.getTransparentBounds(decodeResource);
                                        Bitmap createQRCode = QRCodeUtil.createQRCode(InviteFriendActivity.this.inviteUrl, transparentBounds.right - transparentBounds.left);
                                        LogUtils.log("bitmap-qrBitmap=" + createQRCode.getByteCount());
                                        Bitmap createBitmap = QRCodeUtil.createBitmap(decodeResource, createQRCode);
                                        LogUtils.log("bitmap-newBitmap1=" + createBitmap.getByteCount());
                                        String bitmapToBase64 = QRCodeUtil.bitmapToBase64(createBitmap);
                                        InviteFriendActivity.this.imagesList.add(bitmapToBase64);
                                        SPUtils.put(InviteFriendActivity.this, "inviteBase64#1", bitmapToBase64);
                                        InviteFriendActivity.this.handler.sendEmptyMessage(0);
                                        LogUtils.log("WS_BABY_InviteFriendActivity.initInviteData.0");
                                    }
                                }).start();
                                return;
                            }
                            ArrayList unused3 = InviteFriendActivity.this.imagesList = new ArrayList();
                            String str2 = (String) SPUtils.get(InviteFriendActivity.this, "inviteBase64#1", "");
                            String str3 = (String) SPUtils.get(InviteFriendActivity.this, "inviteBase64#2", "");
                            String str4 = (String) SPUtils.get(InviteFriendActivity.this, "inviteBase64#3", "");
                            String str5 = (String) SPUtils.get(InviteFriendActivity.this, "inviteBase64#4", "");
                            if (!"".equals(str2)) {
                                InviteFriendActivity.this.imagesList.add(str2);
                                LogUtils.log("WS_BABY_InviteFriendActivity.initInviteData.00");
                            }
                            "".equals(str3);
                            "".equals(str4);
                            "".equals(str5);
                            InviteFriendActivity.this.handler.sendEmptyMessage(0);
                        }
                    } catch (Exception e) {
                        InviteFriendActivity.this.dismissLoadingDialog();
                    }
                } else {
                    InviteFriendActivity.this.dismissLoadingDialog();
                    ToastUtils.showToast(InviteFriendActivity.this, conmdBean.getMessage());
                }
            }
        });
    }

    public void initView() {
        this.navTitle.setText("邀请朋友");
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (Util.isOnMainThread()) {
            Glide.with(StubApp.getOrigApplicationContext(getApplicationContext())).pauseRequests();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.InviteFriendActivity, android.app.Activity] */
    @OnClick({2131297049, 2131297368, 2131297367, 2131297365, 2131297366})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != 2131297049) {
            switch (id) {
                case 2131297365:
                    if (this.current_decode != null) {
                        new ShareAction(this).withMedia(new UMImage((Context) this, this.current_decode)).setPlatform(SHARE_MEDIA.QQ.toSnsPlatform().mPlatform).setCallback(this.listener).share();
                        return;
                    }
                    return;
                case 2131297366:
                    if (this.current_decode != null) {
                        new ShareAction(this).withMedia(new UMImage((Context) this, this.current_decode)).setPlatform(SHARE_MEDIA.QZONE.toSnsPlatform().mPlatform).setCallback(this.listener).share();
                        return;
                    }
                    return;
                case 2131297367:
                    if (this.current_decode != null) {
                        new ShareAction(this).withMedia(new UMImage((Context) this, this.current_decode)).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform).setCallback(this.listener).share();
                        return;
                    }
                    return;
                case 2131297368:
                    if (this.current_decode != null) {
                        new ShareAction(this).withMedia(new UMImage((Context) this, this.current_decode)).setPlatform(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform).setCallback(this.listener).share();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            back();
        }
    }
}
