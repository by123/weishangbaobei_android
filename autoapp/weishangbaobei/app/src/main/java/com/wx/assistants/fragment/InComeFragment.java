package com.wx.assistants.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.activity.AlipayWithdrawalsActivity;
import com.wx.assistants.activity.MemberCenterListActivity;
import com.wx.assistants.activity.MemberPrivilegeActivity;
import com.wx.assistants.activity.MyInviteRecordMainActivity;
import com.wx.assistants.activity.OneKeyForwardMaterialActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.dialog.AlertActionSheetSharedDialog;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.ClipboardUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;

public class InComeFragment extends Fragment {
    @BindView(2131296372)
    TextView balance;
    @BindView(2131296416)
    LinearLayout btGetMoney;
    @BindView(2131296549)
    Button copyBtn;
    @BindView(2131296816)
    LinearLayout invitationRecord;
    @BindView(2131296820)
    TextView invitePeopleNum;
    @BindView(2131296821)
    TextView invitePeopleNum2;
    @BindView(2131296822)
    TextView inviteReward;
    @BindView(2131296827)
    TextView inviteUrl;
    @BindView(2131296925)
    TextView levelNameText;
    private View mCacheView;
    @BindView(2131297000)
    LinearLayout materialCircleLayout;
    @BindView(2131297007)
    LinearLayout memberPrivilege;
    @BindView(2131297046)
    LinearLayout myQRCode;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297353)
    ImageView setUserHead;
    Unbinder unbinder;
    @BindView(2131297618)
    LinearLayout upgradeMember;
    @BindView(2131297621)
    TextView userPhone;

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.mCacheView == null) {
            Log.i("AAAA", "Weather onCreateView");
            this.mCacheView = layoutInflater.inflate(2131427530, (ViewGroup) null);
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mCacheView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCacheView);
        }
        this.unbinder = ButterKnife.bind(this, this.mCacheView);
        initView();
        initViewListener();
        return this.mCacheView;
    }

    public void initView() {
        String str = (String) SPUtils.get(getContext(), "ws_baby_phone", "");
        if (str == null || "".equals(str)) {
            this.userPhone.setText("未知用户");
        } else {
            this.userPhone.setText(str);
        }
        String str2 = (String) SPUtils.get(getActivity(), "ws_baby_user_info", "");
        if (str2 == null || "".equals(str2)) {
            this.setUserHead.setBackgroundResource(2131231064);
            return;
        }
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str2, UserInfoBean.UserBean.class);
        if (userBean != null) {
            String memberStatus = userBean.getMemberStatus();
            if ("1".equals(memberStatus) || "1.0".equals(memberStatus)) {
                this.setUserHead.setBackgroundResource(2131231063);
            } else {
                this.setUserHead.setBackgroundResource(2131231064);
            }
        }
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                InComeFragment.this.initData();
                InComeFragment.this.getUser();
            }
        });
    }

    public void initData() {
        LogUtils.log("WS_BABY_InComeFragment.initInviteData");
        ApiWrapper.getInstance().getInviteData(new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (InComeFragment.this.refreshLayout != null) {
                            InComeFragment.this.refreshLayout.finishRefresh();
                        }
                        String str = (String) ((LinkedTreeMap) conmdBean.getData()).get("inviteUrl");
                        MyApplication.inviteUrlStr = str;
                        InComeFragment.this.inviteUrl.setText(str);
                    } catch (Exception unused) {
                        LogUtils.log("WS_BABY_Catch_34");
                        if (InComeFragment.this.refreshLayout != null) {
                            InComeFragment.this.refreshLayout.finishRefresh(false);
                        }
                    }
                } else if (InComeFragment.this.refreshLayout != null) {
                    InComeFragment.this.refreshLayout.finishRefresh(false);
                }
            }

            public void onFailure(FailureModel failureModel) {
                if (InComeFragment.this.refreshLayout != null) {
                    InComeFragment.this.refreshLayout.finishRefresh(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(InComeFragment.this.getActivity(), failureModel.getErrorMessage());
                }
            }
        });
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: Can't wrap try/catch for region: R(6:12|13|14|15|16|23) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r3) {
                /*
                    r2 = this;
                    com.wx.assistants.fragment.InComeFragment r0 = com.wx.assistants.fragment.InComeFragment.this
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout
                    if (r0 == 0) goto L_0x000d
                    com.wx.assistants.fragment.InComeFragment r0 = com.wx.assistants.fragment.InComeFragment.this
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout
                    r0.finishRefresh()
                L_0x000d:
                    if (r3 == 0) goto L_0x0052
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0049 }
                    r1 = 1
                    if (r0 != r1) goto L_0x0027
                    java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x0049 }
                    if (r0 == 0) goto L_0x0027
                    java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x0049 }
                    java.lang.String r0 = "请重新登录"
                    boolean r3 = r3.contains(r0)     // Catch:{ Exception -> 0x0049 }
                    goto L_0x0052
                L_0x0027:
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0049 }
                    if (r0 != 0) goto L_0x0052
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0049 }
                    r0.<init>()     // Catch:{ Exception -> 0x0049 }
                    java.lang.Object r3 = r3.getData()     // Catch:{ Exception -> 0x0049 }
                    java.lang.String r3 = r0.toJson(r3)     // Catch:{ Exception -> 0x0049 }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0043 }
                    java.lang.String r1 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r3)     // Catch:{ Exception -> 0x0043 }
                L_0x0043:
                    com.wx.assistants.fragment.InComeFragment r0 = com.wx.assistants.fragment.InComeFragment.this     // Catch:{ Exception -> 0x0049 }
                    r0.setUserInfo(r3)     // Catch:{ Exception -> 0x0049 }
                    goto L_0x0052
                L_0x0049:
                    r3 = move-exception
                    java.lang.String r0 = "WS_BABY_Catch_35"
                    com.wx.assistants.utils.LogUtils.log(r0)
                    r3.printStackTrace()
                L_0x0052:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.fragment.InComeFragment.AnonymousClass3.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            public void onFailure(FailureModel failureModel) {
                if (InComeFragment.this.refreshLayout != null) {
                    InComeFragment.this.refreshLayout.finishRefresh(false);
                }
            }
        });
    }

    public void onStart() {
        InComeFragment.super.onStart();
        try {
            String str = (String) SPUtils.get(getActivity(), "ws_baby_user_info", "");
            if (str != null && !"".equals(str)) {
                setUserInfo(str);
            }
        } catch (Exception unused) {
        }
    }

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception unused) {
            }
            String memberStatus = userBean.getMemberStatus();
            if ("1".equals(memberStatus) || "1.0".equals(memberStatus)) {
                TextView textView = this.balance;
                textView.setText("¥" + userBean.getWithdrawBalance());
                TextView textView2 = this.invitePeopleNum;
                textView2.setText(userBean.getInviteNum() + "");
                TextView textView3 = this.inviteReward;
                textView3.setText("累计奖励:¥" + userBean.getWithdrawSum() + "元");
                userBean.getInviteNum();
                TextView textView4 = this.invitePeopleNum2;
                textView4.setText(userBean.getInviteTwoNum() + "");
                String level = userBean.getLevel();
                if ("1".equals(level) || "1.0".equals(level)) {
                    this.levelNameText.setText("月度会员");
                } else if ("2".equals(level) || SocializeConstants.PROTOCOL_VERSON.equals(level)) {
                    this.levelNameText.setText("一年会员");
                } else if ("3".equals(level) || "3.0".equals(level)) {
                    this.levelNameText.setText("三年会员");
                } else if ("4".equals(level) || "4.0".equals(level)) {
                    this.levelNameText.setText("半年会员");
                } else if ("5".equals(level) || "5.0".equals(level)) {
                    this.levelNameText.setText("一年会员");
                } else if ("10".equals(level) || "10.0".equals(level)) {
                    this.levelNameText.setText("永久会员");
                } else if ("11".equals(level) || "11.0".equals(level)) {
                    this.levelNameText.setText("永久会员");
                } else {
                    this.levelNameText.setText("体验会员");
                }
            } else {
                this.levelNameText.setText("升级会员");
                TextView textView5 = this.balance;
                textView5.setText("¥" + userBean.getWithdrawBalance());
                TextView textView6 = this.invitePeopleNum;
                textView6.setText(userBean.getInviteNum() + "");
                TextView textView7 = this.inviteReward;
                textView7.setText("累计奖励:¥" + userBean.getWithdrawSum() + "元");
                userBean.getInviteNum();
                TextView textView8 = this.invitePeopleNum2;
                textView8.setText(userBean.getInviteTwoNum() + "");
            }
        }
    }

    public void onResume() {
        InComeFragment.super.onResume();
        initData();
        getUser();
    }

    public void onDestroyView() {
        InComeFragment.super.onDestroyView();
        this.unbinder.unbind();
    }

    @OnClick({2131297007, 2131296416, 2131297618, 2131297046, 2131296549, 2131297000, 2131296816})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296416:
                getActivity().startActivity(AlipayWithdrawalsActivity.class, true, true);
                return;
            case 2131296549:
                ClipboardUtils.setClipboardText(getActivity(), this.inviteUrl.getText().toString(), true);
                return;
            case 2131296816:
                getActivity().startActivity(MyInviteRecordMainActivity.class, true, true);
                return;
            case 2131297000:
                getActivity().startActivity(OneKeyForwardMaterialActivity.class, true, true);
                return;
            case 2131297007:
                startActivity(new Intent(getActivity(), MemberPrivilegeActivity.class));
                return;
            case 2131297046:
                inviteFriend();
                return;
            case 2131297618:
                getActivity().startActivity(MemberCenterListActivity.class, true, true);
                return;
            default:
                return;
        }
    }

    public void inviteFriend() {
        if (MyApplication.inviteUrlStr == null || "".equals(MyApplication.inviteUrlStr)) {
            ToastUtils.showToast(getContext(), "正在获取要分享的数据，请稍后在试!");
            return;
        }
        if (MyApplication.inviteUrlStr.startsWith("http://")) {
            MyApplication.inviteUrlStr = MyApplication.inviteUrlStr.replace("http://", "");
        }
        new AlertActionSheetSharedDialog(getActivity(), new AlertActionSheetSharedDialog.OnSheetItemClickListener() {
            public void onClick(int i) {
                String str = "下载<<微商宝贝>>在“个人中心”（功能建议于客服）中领取激活码即可立享24小时免费VIP特权。让您体验快速找出删除你的好友、24小时群发群、群聊爆粉、一键点赞评论等超多酷炫功能。详戳【 " + MyApplication.inviteUrlStr + " 】";
                switch (i) {
                    case 0:
                        new ShareAction(InComeFragment.this.getActivity()).withText(str).setPlatform(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform).setCallback((UMShareListener) null).share();
                        return;
                    case 1:
                        new ShareAction(InComeFragment.this.getActivity()).withText(str).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform).setCallback((UMShareListener) null).share();
                        return;
                    default:
                        return;
                }
            }
        }).builder().show();
    }
}
