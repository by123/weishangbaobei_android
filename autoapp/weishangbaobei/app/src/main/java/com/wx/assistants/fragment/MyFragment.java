package com.wx.assistants.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.activity.ActivationActivity;
import com.wx.assistants.activity.LoginActivity;
import com.wx.assistants.activity.MemberCenterListActivity;
import com.wx.assistants.activity.MyIndentActivity;
import com.wx.assistants.activity.OneKeyForwardMaterialActivity;
import com.wx.assistants.activity.SettingActivity;
import com.wx.assistants.activity.SuggestServerActivity;
import com.wx.assistants.activity.SystemMsgActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.base.BaseFragment;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UpdateMemberEvent;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.ApkDownUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.PackageUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.webview.WebViewActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MyFragment extends BaseFragment {
    @BindView(2131296399)
    LinearLayout bottomLayout;
    @BindView(2131296407)
    LinearLayout bottomWhiteLayout;
    @BindView(2131296408)
    LinearLayout bottomWhiteTopLayout;
    @BindView(2131296454)
    LinearLayout buttonService;
    @BindView(2131296562)
    TextView currentVersionText;
    @BindView(2131296658)
    LinearLayout faqLayout;
    @BindView(2131296741)
    FrameLayout headOperationLayout;
    @BindView(2131296818)
    LinearLayout inviteFriend;
    /* access modifiers changed from: private */
    public boolean isLogin = true;
    @BindView(2131296840)
    LinearLayout ivMyIndent;
    @BindView(2131296963)
    LinearLayout ll_check_update;
    private LinearLayout ll_edit;
    @BindView(2131296968)
    LinearLayout ll_member_center;
    @BindView(2131296980)
    Button loginBtn;
    private View mCacheView;
    @BindView(2131297002)
    LinearLayout meFriendCircle;
    @BindView(2131297004)
    TextView memberLever;
    @BindView(2131297005)
    LinearLayout memberLeverLayout;
    @BindView(2131297047)
    LinearLayout myActivation;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297349)
    TextView setExpireTime;
    @BindView(2131297353)
    ImageView setUserHead;
    @BindView(2131297354)
    TextView setUserName;
    @BindView(2131297451)
    LinearLayout systemMsgLayout;
    Unbinder unbinder;
    private String versionName = "";

    @OnClick({2131297002})
    public void onViewClicked() {
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.mCacheView == null) {
            Log.i("AAAA", "Weather onCreateView");
            this.mCacheView = layoutInflater.inflate(2131427534, (ViewGroup) null);
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mCacheView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCacheView);
        }
        this.unbinder = ButterKnife.bind(this, this.mCacheView);
        initViewListener();
        EventBus.getDefault().register(this);
        return this.mCacheView;
    }

    @Subscribe
    public void onEventMainThread(UpdateMemberEvent updateMemberEvent) {
        LogUtils.log("WS_BABY_.MyFragment.onEventMainThread");
        if (updateMemberEvent.getTag() == 0) {
            getUser();
        }
    }

    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(getActivity(), "ws_baby_user_info", "");
            if (str == null || "".equals(str)) {
                this.setUserName.setText("未知用户");
                this.setExpireTime.setText("尚未登录，请登录");
                this.memberLeverLayout.setVisibility(8);
                this.setUserHead.setBackgroundResource(2131231064);
                this.bottomLayout.setVisibility(0);
                this.isLogin = false;
                return;
            }
            this.isLogin = true;
            setUserInfo(str);
        } catch (Exception unused) {
        }
    }

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            this.bottomLayout.setVisibility(8);
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception unused) {
            }
            String memberStatus = userBean.getMemberStatus();
            if ("1".equals(memberStatus) || "1.0".equals(memberStatus)) {
                this.setUserHead.setBackgroundResource(2131231063);
                String phone_number = userBean.getPhone_number();
                userBean.getLevelName();
                String codeEndTime = userBean.getCodeEndTime();
                if (codeEndTime == null) {
                    codeEndTime = "";
                } else if (codeEndTime.contains(" ")) {
                    codeEndTime = codeEndTime.split(" ")[0];
                }
                this.setUserName.setText(phone_number);
                TextView textView = this.setExpireTime;
                textView.setText("会员到期时间：" + codeEndTime);
                this.memberLeverLayout.setVisibility(0);
                String level = userBean.getLevel();
                if ("1".equals(level) || "1.0".equals(level)) {
                    this.memberLever.setText("月");
                } else if ("2".equals(level) || SocializeConstants..equals(level)) {
                    this.memberLever.setText("年");
                } else if ("3".equals(level) || "3.0".equals(level)) {
                    this.memberLever.setText("三");
                } else if ("4".equals(level) || "4.0".equals(level)) {
                    this.memberLever.setText("半");
                } else if ("5".equals(level) || "5.0".equals(level)) {
                    this.memberLever.setText("年");
                } else if ("10".equals(level) || "10.0".equals(level)) {
                    this.memberLever.setText("永");
                } else if ("11".equals(level) || "11.0".equals(level)) {
                    this.memberLever.setText("永");
                } else {
                    this.memberLever.setText("体");
                }
            } else {
                this.setUserHead.setBackgroundResource(2131231064);
                this.setUserName.setText(userBean.getPhone_number());
                this.setExpireTime.setText("开通会员，尊享更多功能特权");
                this.memberLeverLayout.setVisibility(8);
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            getUser();
        }
    }

    public void onResume() {
        super.onResume();
        LogUtils.log("WS_BABY_.MyFragment.onResume");
        getUser();
        try {
            this.versionName = PackageUtils.getWSBabyVersion(MyApplication.getConText())[1];
        } catch (Exception unused) {
            this.versionName = "3.5.0.10";
        }
        TextView textView = this.currentVersionText;
        textView.setText("Ver:" + this.versionName.substring(0, this.versionName.lastIndexOf(".")));
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                MyFragment.this.getUser();
            }
        });
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: Can't wrap try/catch for region: R(6:14|15|16|17|18|26) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0077 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r4) {
                /*
                    r3 = this;
                    com.wx.assistants.fragment.MyFragment r0 = com.wx.assistants.fragment.MyFragment.this
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout
                    if (r0 == 0) goto L_0x000d
                    com.wx.assistants.fragment.MyFragment r0 = com.wx.assistants.fragment.MyFragment.this
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout
                    r0.finishRefresh()
                L_0x000d:
                    if (r4 == 0) goto L_0x008b
                    int r0 = r4.getCode()     // Catch:{ Exception -> 0x0082 }
                    r1 = 1
                    if (r0 != r1) goto L_0x005b
                    java.lang.String r0 = r4.getMessage()     // Catch:{ Exception -> 0x0082 }
                    if (r0 == 0) goto L_0x005b
                    java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x0082 }
                    java.lang.String r0 = "请重新登录"
                    boolean r4 = r4.contains(r0)     // Catch:{ Exception -> 0x0082 }
                    if (r4 == 0) goto L_0x008b
                    com.wx.assistants.fragment.MyFragment r4 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    android.widget.TextView r4 = r4.setUserName     // Catch:{ Exception -> 0x0082 }
                    java.lang.String r0 = "未知用户"
                    r4.setText(r0)     // Catch:{ Exception -> 0x0082 }
                    com.wx.assistants.fragment.MyFragment r4 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    android.widget.TextView r4 = r4.setExpireTime     // Catch:{ Exception -> 0x0082 }
                    java.lang.String r0 = "尚未登录，请登录"
                    r4.setText(r0)     // Catch:{ Exception -> 0x0082 }
                    com.wx.assistants.fragment.MyFragment r4 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    android.widget.LinearLayout r4 = r4.memberLeverLayout     // Catch:{ Exception -> 0x0082 }
                    r0 = 8
                    r4.setVisibility(r0)     // Catch:{ Exception -> 0x0082 }
                    com.wx.assistants.fragment.MyFragment r4 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    android.widget.ImageView r4 = r4.setUserHead     // Catch:{ Exception -> 0x0082 }
                    r0 = 2131231064(0x7f080158, float:1.8078198E38)
                    r4.setBackgroundResource(r0)     // Catch:{ Exception -> 0x0082 }
                    com.wx.assistants.fragment.MyFragment r4 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    android.widget.LinearLayout r4 = r4.bottomLayout     // Catch:{ Exception -> 0x0082 }
                    r0 = 0
                    r4.setVisibility(r0)     // Catch:{ Exception -> 0x0082 }
                    com.wx.assistants.fragment.MyFragment r4 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    boolean unused = r4.isLogin = r0     // Catch:{ Exception -> 0x0082 }
                    goto L_0x008b
                L_0x005b:
                    int r0 = r4.getCode()     // Catch:{ Exception -> 0x0082 }
                    if (r0 != 0) goto L_0x008b
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0082 }
                    r0.<init>()     // Catch:{ Exception -> 0x0082 }
                    java.lang.Object r4 = r4.getData()     // Catch:{ Exception -> 0x0082 }
                    java.lang.String r4 = r0.toJson(r4)     // Catch:{ Exception -> 0x0082 }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0077 }
                    java.lang.String r2 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r2, r4)     // Catch:{ Exception -> 0x0077 }
                L_0x0077:
                    com.wx.assistants.fragment.MyFragment r0 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    boolean unused = r0.isLogin = r1     // Catch:{ Exception -> 0x0082 }
                    com.wx.assistants.fragment.MyFragment r0 = com.wx.assistants.fragment.MyFragment.this     // Catch:{ Exception -> 0x0082 }
                    r0.setUserInfo(r4)     // Catch:{ Exception -> 0x0082 }
                    goto L_0x008b
                L_0x0082:
                    r4 = move-exception
                    java.lang.String r0 = "WS_BABY_Catch_46"
                    com.wx.assistants.utils.LogUtils.log(r0)
                    r4.printStackTrace()
                L_0x008b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.fragment.MyFragment.AnonymousClass2.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            public void onFailure(FailureModel failureModel) {
                MyFragment.this.refreshLayout.finishRefresh(false);
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    /* JADX WARNING: type inference failed for: r4v12, types: [com.wx.assistants.base.BaseActivity, android.app.Activity] */
    @OnClick({2131296454, 2131297451, 2131297047, 2131296840, 2131296818, 2131296968, 2131296963, 2131297052, 2131296658, 2131297002, 2131296408, 2131296980, 2131296407, 2131296399, 2131296741})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296399:
                closeBottomLayout();
                return;
            case 2131296407:
                closeBottomLayout();
                return;
            case 2131296408:
                closeBottomLayout();
                return;
            case 2131296454:
                startActivity(new Intent(getActivity(), SuggestServerActivity.class));
                return;
            case 2131296658:
                WebViewActivity.startActivity(getActivity(), "使用帮助", QBangApis.FAQ_URL, false);
                return;
            case 2131296741:
                if (!this.isLogin) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.putExtra("isNeedBack", true);
                    startActivity(intent);
                    return;
                }
                return;
            case 2131296818:
                getActivity().startActivity(OneKeyForwardMaterialActivity.class, true, true);
                return;
            case 2131296840:
                getActivity().startActivity(MyIndentActivity.class, true, true);
                return;
            case 2131296963:
                showLoadingDialog("正在检测");
                ApkDownUtils.getInstance((BaseActivity) getActivity()).queryVersion(true, new ApkDownUtils.OnDownLoadListener() {
                    public void listenerCompleted() {
                        MyFragment.this.dismissLoadingDialog();
                    }
                });
                return;
            case 2131296968:
                getActivity().startActivity(MemberCenterListActivity.class, true, true);
                return;
            case 2131296980:
                closeBottomLayout();
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                intent2.putExtra("isNeedBack", true);
                startActivity(intent2);
                return;
            case 2131297002:
                getActivity().startActivity(OneKeyForwardMaterialActivity.class, true, true);
                return;
            case 2131297047:
                getActivity().startActivity(ActivationActivity.class, true, true);
                return;
            case 2131297052:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                return;
            case 2131297451:
                getActivity().startActivity(SystemMsgActivity.class, true, true);
                return;
            default:
                return;
        }
    }

    public void closeBottomLayout() {
        this.bottomLayout.setVisibility(8);
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static class ScreenUtils {
        public static int getScreenHeight(Context context) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }

        public static int getScreenWidth(Context context) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
    }
}
