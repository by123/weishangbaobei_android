package com.wx.assistants.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.gson.internal.LinkedTreeMap;
import com.simple.transformslibrary.CardSlideTransformer;
import com.simple.transformslibrary.TransformUtil;
import com.stub.StubApp;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.dialog.AlertActionSheetDialog;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.listener.MyUMShareListener;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.QRCodeUtil;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class InviteFriendFragment extends Fragment {
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
    private View mCacheView;
    @BindView(2131297365)
    LinearLayout sharedQQLayout;
    @BindView(2131297366)
    LinearLayout sharedQzoneLayout;
    @BindView(2131297367)
    LinearLayout sharedWechatCircleLayout;
    @BindView(2131297368)
    LinearLayout sharedWechatLayout;
    /* access modifiers changed from: private */
    public List<View> viewList = new ArrayList();

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.mCacheView == null) {
            this.mCacheView = layoutInflater.inflate(2131427533, (ViewGroup) null);
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mCacheView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCacheView);
        }
        ButterKnife.bind(this, this.mCacheView);
        initInviteData();
        return this.mCacheView;
    }

    public void initInviteData() {
        LogUtils.log("WS_BABY_InviteFriendFragment.initInviteData");
        ApiWrapper.getInstance().getInviteData(new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean == null) {
                    return;
                }
                if (conmdBean.getCode() == 0) {
                    try {
                        String unused = InviteFriendFragment.this.inviteUrl = (String) ((LinkedTreeMap) conmdBean.getData()).get("inviteUrl");
                        if (InviteFriendFragment.this.inviteUrl != null && !InviteFriendFragment.this.inviteUrl.equals("")) {
                            MyApplication.inviteUrlStr = InviteFriendFragment.this.inviteUrl;
                            String str = (String) SPUtils.get(InviteFriendFragment.this.getActivity(), "inviteUrl", "");
                            int intValue = ((Integer) SPUtils.get(InviteFriendFragment.this.getActivity(), "inviteUrlCode", 0)).intValue();
                            if (!"".equals(str) && InviteFriendFragment.this.inviteUrl.equals(str)) {
                                if (intValue == 5) {
                                    ArrayList unused2 = InviteFriendFragment.this.imagesList = new ArrayList();
                                    String str2 = (String) SPUtils.get(InviteFriendFragment.this.getActivity(), "inviteBase64#1", "");
                                    String str3 = (String) SPUtils.get(InviteFriendFragment.this.getActivity(), "inviteBase64#2", "");
                                    String str4 = (String) SPUtils.get(InviteFriendFragment.this.getActivity(), "inviteBase64#3", "");
                                    String str5 = (String) SPUtils.get(InviteFriendFragment.this.getActivity(), "inviteBase64#4", "");
                                    if (!"".equals(str2)) {
                                        InviteFriendFragment.this.imagesList.add(str2);
                                        LogUtils.log("WS_BABY_InviteFriendFragment.initInviteData.00");
                                    }
                                    "".equals(str3);
                                    "".equals(str4);
                                    "".equals(str5);
                                    InviteFriendFragment.this.handler.sendEmptyMessage(0);
                                    return;
                                }
                            }
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    InviteFriendFragment.this.getActivity().showLoadingDialog("初始化数据");
                                }
                            }, 200);
                            ArrayList unused3 = InviteFriendFragment.this.imagesList = new ArrayList();
                            new Thread(new Runnable() {
                                public void run() {
                                    Bitmap decodeResource = BitmapFactory.decodeResource(InviteFriendFragment.this.getResources(), 2131230828);
                                    LogUtils.log("bitmap-src1=" + decodeResource.getByteCount());
                                    Rect transparentBounds = QRCodeUtil.getTransparentBounds(decodeResource);
                                    Bitmap createQRCode = QRCodeUtil.createQRCode(InviteFriendFragment.this.inviteUrl, transparentBounds.right - transparentBounds.left);
                                    LogUtils.log("bitmap-qrBitmap=" + createQRCode.getByteCount());
                                    Bitmap createBitmap = QRCodeUtil.createBitmap(decodeResource, createQRCode);
                                    LogUtils.log("bitmap-newBitmap1=" + createBitmap.getByteCount());
                                    String bitmapToBase64 = QRCodeUtil.bitmapToBase64(createBitmap);
                                    InviteFriendFragment.this.imagesList.add(bitmapToBase64);
                                    SPUtils.put(InviteFriendFragment.this.getActivity(), "inviteBase64#1", bitmapToBase64);
                                    InviteFriendFragment.this.handler.sendEmptyMessage(0);
                                    LogUtils.log("WS_BABY_InviteFriendFragment.initInviteData.0");
                                }
                            }).start();
                        }
                    } catch (Exception unused4) {
                        LogUtils.log("WS_BABY_Catch_36");
                        InviteFriendFragment.this.getActivity().dismissLoadingDialog();
                    }
                } else {
                    InviteFriendFragment.this.getActivity().dismissLoadingDialog();
                    ToastUtils.showToast(InviteFriendFragment.this.getActivity(), conmdBean.getMessage());
                }
            }

            public void onFailure(FailureModel failureModel) {
                if (failureModel != null) {
                    InviteFriendFragment.this.getActivity().dismissLoadingDialog();
                    ToastUtils.showToast(InviteFriendFragment.this.getActivity(), failureModel.getErrorMessage());
                }
            }
        });
    }

    class MyViewAdapter extends PagerAdapter {
        private List<View> datas;

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public MyViewAdapter(List<View> list) {
            this.datas = list;
        }

        public int getCount() {
            return this.datas.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = this.datas.get(i);
            viewGroup.addView(view);
            return view;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(this.datas.get(i));
        }
    }

    private class OnMyPageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        private OnMyPageChangeListener() {
        }

        public void onPageSelected(int i) {
            int unused = InviteFriendFragment.this.currentIndex = i;
            byte[] unused2 = InviteFriendFragment.this.current_decode = Base64.decode((String) InviteFriendFragment.this.imagesList.get(i), 0);
        }
    }

    class MyHandler extends Handler {
        MyHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0 && InviteFriendFragment.this.imagesList.size() == 1) {
                LogUtils.log("WS_BABY_InviteFriendFragment.initInviteData.end");
                SPUtils.put(InviteFriendFragment.this.getActivity(), "inviteUrl", InviteFriendFragment.this.inviteUrl);
                SPUtils.put(InviteFriendFragment.this.getActivity(), "inviteUrlCode", 5);
                InviteFriendFragment.this.getActivity().dismissLoadingDialog();
                byte[] unused = InviteFriendFragment.this.current_decode = Base64.decode((String) InviteFriendFragment.this.imagesList.get(0), 0);
                View inflate = InviteFriendFragment.this.getLayoutInflater().inflate(2131427779, (ViewGroup) null);
                ImageView imageView = (ImageView) inflate.findViewById(2131297640);
                final byte[] decode = Base64.decode((String) InviteFriendFragment.this.imagesList.get(0), 0);
                if (Util.isOnMainThread() && !InviteFriendFragment.this.getActivity().isDestroyed()) {
                    Glide.with(MyApplication.getConText()).load(decode).into(imageView);
                }
                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        DialogUIUtils.actionSheetSavePhotos(InviteFriendFragment.this.getActivity(), new AlertActionSheetDialog.OnSheetItemClickListener() {
                            public void onClick(int i) {
                                if (i == 1) {
                                    new FileUtil().saveSyn((Context) InviteFriendFragment.this.getActivity(), decode, (FileUtil.OnSaveCompletedListener) new FileUtil.OnSaveCompletedListener() {
                                        public void saveCompleted() {
                                            ToastUtils.showToast(InviteFriendFragment.this.getActivity(), "图片已保存到相册");
                                        }
                                    });
                                }
                            }
                        });
                        return false;
                    }
                });
                InviteFriendFragment.this.viewList.add(inflate);
                MyViewAdapter unused2 = InviteFriendFragment.this.adapter = new MyViewAdapter(InviteFriendFragment.this.viewList);
                InviteFriendFragment.this.banner.setAdapter(InviteFriendFragment.this.adapter);
                InviteFriendFragment.this.banner.addOnPageChangeListener(new OnMyPageChangeListener());
                InviteFriendFragment.this.banner.setOffscreenPageLimit(1);
                TransformUtil.reverse(InviteFriendFragment.this.banner, new CardSlideTransformer());
            }
        }
    }

    @OnClick({2131297368, 2131297367, 2131297365, 2131297366})
    public void onViewClicked(View view) {
        if (this.current_decode != null) {
            switch (view.getId()) {
                case 2131297365:
                    new ShareAction(getActivity()).withMedia(new UMImage((Context) getActivity(), this.current_decode)).setPlatform(SHARE_MEDIA.QQ.toSnsPlatform().mPlatform).setCallback(new MyUMShareListener()).share();
                    return;
                case 2131297366:
                    new ShareAction(getActivity()).withMedia(new UMImage((Context) getActivity(), this.current_decode)).setPlatform(SHARE_MEDIA.QZONE.toSnsPlatform().mPlatform).setCallback(new MyUMShareListener()).share();
                    return;
                case 2131297367:
                    new ShareAction(getActivity()).withMedia(new UMImage((Context) getActivity(), this.current_decode)).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform).setCallback(new MyUMShareListener()).share();
                    return;
                case 2131297368:
                    new ShareAction(getActivity()).withMedia(new UMImage((Context) getActivity(), this.current_decode)).setPlatform(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform).setCallback(new MyUMShareListener()).share();
                    return;
                default:
                    return;
            }
        }
    }

    public void onDestroy() {
        InviteFriendFragment.super.onDestroy();
        if (Util.isOnMainThread()) {
            Glide.with(StubApp.getOrigApplicationContext(getActivity().getApplicationContext())).pauseRequests();
        }
    }
}
