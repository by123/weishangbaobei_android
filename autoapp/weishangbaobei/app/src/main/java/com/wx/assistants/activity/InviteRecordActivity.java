package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdList2Bean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;

public class InviteRecordActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public ArrayList<ConmdList2Bean.ResultBean> items = new ArrayList<>();
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297091)
    Button operationBtn;
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297349)
    TextView setExpireTime;
    @BindView(2131297354)
    TextView setUserName;

    static {
        StubApp.interface11(8924);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static /* synthetic */ int access$110(InviteRecordActivity inviteRecordActivity) {
        int i = inviteRecordActivity.page;
        inviteRecordActivity.page = i - 1;
        return i;
    }

    public void initData() {
        this.navTitle.setText("邀请记录");
        initRecordData(false, 1);
        initViewListener();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(this, "ws_baby_user_info", "");
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
                userBean.getLevelName() + "";
                this.setUserName.setText(userBean.getPhone_number() + "");
                this.operationBtn.setVisibility(8);
                String level = userBean.getLevel();
                if ("1".equals(level) || "1.0".equals(level)) {
                    this.setExpireTime.setText("您当前是月会员，最高可获得" + getResources().getString(2131689805) + "的邀请奖励");
                } else if ("2".equals(level) || SocializeConstants.PROTOCOL_VERSON.equals(level)) {
                    this.setExpireTime.setText("您当前是一年会员，最高可获得" + getResources().getString(2131689809) + "的邀请奖励");
                } else if ("3".equals(level) || "3.0".equals(level)) {
                    this.setExpireTime.setText("您当前是三年会员，最高可获得" + getResources().getString(2131689807) + "的邀请奖励");
                } else if ("4".equals(level) || "4.0".equals(level)) {
                    this.setExpireTime.setText("您当前是半年会员，最高可获得" + getResources().getString(2131689808) + "的邀请奖励");
                } else if ("5".equals(level) || "5.0".equals(level)) {
                    this.setExpireTime.setText("您当前是一年会员，最高可获得" + getResources().getString(2131689809) + "的邀请奖励");
                } else if ("10".equals(level) || "10.0".equals(level)) {
                    this.setExpireTime.setText("您当前是永久会员，最高可获得" + getResources().getString(2131689804) + "的邀请奖励");
                } else if ("11".equals(level) || "11.0".equals(level)) {
                    this.setExpireTime.setText("您当前是永久会员，最高可获得" + getResources().getString(2131689804) + "的邀请奖励");
                } else {
                    this.setExpireTime.setText("您当前是永久会员，最高可获得" + getResources().getString(2131689810) + "的邀请奖励");
                }
            } else {
                this.setUserName.setText(userBean.getPhone_number() + "");
                this.setExpireTime.setText("开通会员，最高可获得" + getResources().getString(2131689807) + "的邀请奖励");
                this.operationBtn.setVisibility(0);
                this.operationBtn.setText("去开通");
            }
        }
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(6:11|12|13|14|15|23) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x005b */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdBean r3) {
                /*
                    r2 = this;
                    if (r3 == 0) goto L_0x0065
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0061 }
                    r1 = 1
                    if (r0 != r1) goto L_0x003f
                    java.lang.String r0 = r3.getMessage()     // Catch:{ Exception -> 0x0061 }
                    if (r0 == 0) goto L_0x003f
                    java.lang.String r3 = r3.getMessage()     // Catch:{ Exception -> 0x0061 }
                    java.lang.String r0 = "请重新登录"
                    boolean r3 = r3.contains(r0)     // Catch:{ Exception -> 0x0061 }
                    if (r3 == 0) goto L_0x0065
                    com.wx.assistants.activity.InviteRecordActivity r3 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x0061 }
                    android.widget.TextView r3 = r3.setUserName     // Catch:{ Exception -> 0x0061 }
                    java.lang.String r0 = "未知用户"
                    r3.setText(r0)     // Catch:{ Exception -> 0x0061 }
                    com.wx.assistants.activity.InviteRecordActivity r3 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x0061 }
                    android.widget.TextView r3 = r3.setExpireTime     // Catch:{ Exception -> 0x0061 }
                    java.lang.String r0 = "尚未登录，请登录"
                    r3.setText(r0)     // Catch:{ Exception -> 0x0061 }
                    com.wx.assistants.activity.InviteRecordActivity r3 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x0061 }
                    android.widget.Button r3 = r3.operationBtn     // Catch:{ Exception -> 0x0061 }
                    r0 = 0
                    r3.setVisibility(r0)     // Catch:{ Exception -> 0x0061 }
                    com.wx.assistants.activity.InviteRecordActivity r3 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x0061 }
                    android.widget.Button r3 = r3.operationBtn     // Catch:{ Exception -> 0x0061 }
                    java.lang.String r0 = "登录"
                    r3.setText(r0)     // Catch:{ Exception -> 0x0061 }
                    goto L_0x0065
                L_0x003f:
                    int r0 = r3.getCode()     // Catch:{ Exception -> 0x0061 }
                    if (r0 != 0) goto L_0x0065
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x0061 }
                    r0.<init>()     // Catch:{ Exception -> 0x0061 }
                    java.lang.Object r3 = r3.getData()     // Catch:{ Exception -> 0x0061 }
                    java.lang.String r3 = r0.toJson(r3)     // Catch:{ Exception -> 0x0061 }
                    android.content.Context r0 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x005b }
                    java.lang.String r1 = "ws_baby_user_info"
                    com.wx.assistants.utils.SPUtils.put(r0, r1, r3)     // Catch:{ Exception -> 0x005b }
                L_0x005b:
                    com.wx.assistants.activity.InviteRecordActivity r0 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x0061 }
                    r0.setUserInfo(r3)     // Catch:{ Exception -> 0x0061 }
                    goto L_0x0065
                L_0x0061:
                    r3 = move-exception
                    r3.printStackTrace()
                L_0x0065:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.InviteRecordActivity.AnonymousClass1.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }
        });
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
    public void initRecordData(final boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().myMemberInvite(i, 20, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(this, "user_token", ""), (ApiWrapper.CallbackListener<ConmdList2Bean>) new ApiWrapper.CallbackListener<ConmdList2Bean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r2v4, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r2v5, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e4, code lost:
                if (r8 != false) goto L_0x00e6;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e6, code lost:
                com.wx.assistants.activity.InviteRecordActivity.access$110(r6.this$0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                return;
             */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00e2 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdList2Bean r7) {
                /*
                    r6 = this;
                    com.wx.assistants.activity.InviteRecordActivity r0 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00ec }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00ec }
                    r1 = 1
                    if (r0 == 0) goto L_0x001c
                    com.wx.assistants.activity.InviteRecordActivity r0 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00ec }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00ec }
                    r0.finishRefresh()     // Catch:{ Exception -> 0x00ec }
                    com.wx.assistants.activity.InviteRecordActivity r0 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00ec }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00ec }
                    r0.finishLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00ec }
                    com.wx.assistants.activity.InviteRecordActivity r0 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00ec }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00ec }
                    r0.setEnableLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00ec }
                L_0x001c:
                    if (r7 == 0) goto L_0x00f0
                    int r0 = r7.getCode()     // Catch:{ Exception -> 0x00e2 }
                    if (r0 != 0) goto L_0x00d8
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x00e2 }
                    r0.<init>()     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.bean.ConmdList2Bean$DataBean r7 = r7.getData()     // Catch:{ Exception -> 0x00e2 }
                    java.lang.String r7 = r0.toJson(r7)     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity$2$1 r2 = new com.wx.assistants.activity.InviteRecordActivity$2$1     // Catch:{ Exception -> 0x00e2 }
                    r2.<init>()     // Catch:{ Exception -> 0x00e2 }
                    java.lang.reflect.Type r2 = r2.getType()     // Catch:{ Exception -> 0x00e2 }
                    java.lang.Object r7 = r0.fromJson(r7, r2)     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.bean.ConmdList2Bean$DataBean r7 = (com.wx.assistants.bean.ConmdList2Bean.DataBean) r7     // Catch:{ Exception -> 0x00e2 }
                    java.util.ArrayList r0 = r7.getList()     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.bean.ConmdList2Bean$PagesBean r7 = r7.getPages()     // Catch:{ Exception -> 0x00e2 }
                    int r2 = r7.getSize()     // Catch:{ Exception -> 0x00e2 }
                    r3 = 0
                    if (r2 <= 0) goto L_0x00ca
                    int r2 = r7.getSize()     // Catch:{ Exception -> 0x00e2 }
                    int r7 = r7.getPageSize()     // Catch:{ Exception -> 0x00e2 }
                    if (r2 >= r7) goto L_0x0066
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r7 = r7.refreshLayout     // Catch:{ Exception -> 0x00e2 }
                    if (r7 == 0) goto L_0x0066
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r7 = r7.refreshLayout     // Catch:{ Exception -> 0x00e2 }
                    r7.setEnableLoadMore((boolean) r3)     // Catch:{ Exception -> 0x00e2 }
                L_0x0066:
                    boolean r7 = r8     // Catch:{ Exception -> 0x00e2 }
                    if (r7 == 0) goto L_0x0074
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    java.util.ArrayList r7 = r7.items     // Catch:{ Exception -> 0x00e2 }
                    r7.addAll(r0)     // Catch:{ Exception -> 0x00e2 }
                    goto L_0x0082
                L_0x0074:
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    java.util.ArrayList r7 = r7.items     // Catch:{ Exception -> 0x00e2 }
                    r7.clear()     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    java.util.ArrayList unused = r7.items = r0     // Catch:{ Exception -> 0x00e2 }
                L_0x0082:
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    android.support.v7.widget.RecyclerView r7 = r7.recyclerView     // Catch:{ Exception -> 0x00e2 }
                    android.support.v7.widget.LinearLayoutManager r0 = new android.support.v7.widget.LinearLayoutManager     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r2 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    r0.<init>(r2)     // Catch:{ Exception -> 0x00e2 }
                    r7.setLayoutManager(r0)     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    android.support.v7.widget.RecyclerView r7 = r7.recyclerView     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.view.RecyclerViewDivider r0 = new com.wx.assistants.view.RecyclerViewDivider     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r2 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r4 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    r5 = 2131099799(0x7f060097, float:1.7811961E38)
                    int r4 = android.support.v4.content.ContextCompat.getColor(r4, r5)     // Catch:{ Exception -> 0x00e2 }
                    r0.<init>(r2, r1, r3, r4)     // Catch:{ Exception -> 0x00e2 }
                    r7.addItemDecoration(r0)     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    android.support.v7.widget.RecyclerView r7 = r7.recyclerView     // Catch:{ Exception -> 0x00e2 }
                    android.support.v7.widget.DefaultItemAnimator r0 = new android.support.v7.widget.DefaultItemAnimator     // Catch:{ Exception -> 0x00e2 }
                    r0.<init>()     // Catch:{ Exception -> 0x00e2 }
                    r7.setItemAnimator(r0)     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    android.support.v7.widget.RecyclerView r7 = r7.recyclerView     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity$2$2 r0 = new com.wx.assistants.activity.InviteRecordActivity$2$2     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.activity.InviteRecordActivity r1 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    java.util.ArrayList r1 = r1.items     // Catch:{ Exception -> 0x00e2 }
                    r2 = 2131427476(0x7f0b0094, float:1.847657E38)
                    r3 = 0
                    r0.<init>(r1, r2, r3)     // Catch:{ Exception -> 0x00e2 }
                    r7.setAdapter(r0)     // Catch:{ Exception -> 0x00e2 }
                    goto L_0x00f0
                L_0x00ca:
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r7 = r7.refreshLayout     // Catch:{ Exception -> 0x00e2 }
                    if (r7 == 0) goto L_0x00f0
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r7 = r7.refreshLayout     // Catch:{ Exception -> 0x00e2 }
                    r7.setEnableLoadMore((boolean) r3)     // Catch:{ Exception -> 0x00e2 }
                    goto L_0x00f0
                L_0x00d8:
                    com.wx.assistants.activity.InviteRecordActivity r0 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00e2 }
                    java.lang.String r7 = r7.getMessage()     // Catch:{ Exception -> 0x00e2 }
                    com.wx.assistants.utils.ToastUtils.showToast(r0, r7)     // Catch:{ Exception -> 0x00e2 }
                    goto L_0x00f0
                L_0x00e2:
                    boolean r7 = r8     // Catch:{ Exception -> 0x00ec }
                    if (r7 == 0) goto L_0x00f0
                    com.wx.assistants.activity.InviteRecordActivity r7 = com.wx.assistants.activity.InviteRecordActivity.this     // Catch:{ Exception -> 0x00ec }
                    com.wx.assistants.activity.InviteRecordActivity.access$110(r7)     // Catch:{ Exception -> 0x00ec }
                    goto L_0x00f0
                L_0x00ec:
                    r7 = move-exception
                    r7.printStackTrace()
                L_0x00f0:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.InviteRecordActivity.AnonymousClass2.onFinish(com.wx.assistants.bean.ConmdList2Bean):void");
            }

            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            public void onFailure(FailureModel failureModel) {
                if (z) {
                    InviteRecordActivity.access$110(InviteRecordActivity.this);
                }
                if (InviteRecordActivity.this.refreshLayout != null) {
                    InviteRecordActivity.this.refreshLayout.finishRefresh();
                    InviteRecordActivity.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(InviteRecordActivity.this, failureModel.getErrorMessage());
                }
            }
        });
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                InviteRecordActivity.this.initRecordData(false, 1);
            }
        });
        this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                int unused = InviteRecordActivity.this.page = InviteRecordActivity.this.page + 1;
                InviteRecordActivity.this.initRecordData(true, InviteRecordActivity.this.page);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
    @OnClick({2131297049, 2131297091})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297091) {
            if ("登录".equals(this.operationBtn.getText())) {
                Intent intent = new Intent(this, LoginActivity.class);
                intent.putExtra("isNeedBack", true);
                startActivity(intent);
            } else if ("去开通".equals(this.operationBtn.getText())) {
                startActivity(new Intent(this, MemberCenterListActivity.class));
            }
        }
    }
}
