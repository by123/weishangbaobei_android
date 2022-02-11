package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.adapter.BaseRecyclerAdapter;
import com.wx.assistants.adapter.SmartViewHolder;
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
import com.wx.assistants.view.RecyclerViewDivider;
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

    static /* synthetic */ int access$110(InviteRecordActivity inviteRecordActivity) {
        int i = inviteRecordActivity.page;
        inviteRecordActivity.page = i - 1;
        return i;
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() != 1 || conmdBean.getMessage() == null) {
                            if (conmdBean.getCode() == 0) {
                                String json = new Gson().toJson(conmdBean.getData());
                                try {
                                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_info", json);
                                } catch (Exception e) {
                                }
                                InviteRecordActivity.this.setUserInfo(json);
                            }
                        } else if (conmdBean.getMessage().contains("请重新登录")) {
                            InviteRecordActivity.this.setUserName.setText("未知用户");
                            InviteRecordActivity.this.setExpireTime.setText("尚未登录，请登录");
                            InviteRecordActivity.this.operationBtn.setVisibility(0);
                            InviteRecordActivity.this.operationBtn.setText("登录");
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    public void initData() {
        this.navTitle.setText("邀请记录");
        initRecordData(false, 1);
        initViewListener();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
    public void initRecordData(final boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().myMemberInvite(i, 20, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(this, "user_token", ""), (ApiWrapper.CallbackListener<ConmdList2Bean>) new ApiWrapper.CallbackListener<ConmdList2Bean>() {
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

            /* JADX WARNING: type inference failed for: r0v7, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r2v4, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r2v5, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
            public void onFinish(ConmdList2Bean conmdList2Bean) {
                try {
                    if (InviteRecordActivity.this.refreshLayout != null) {
                        InviteRecordActivity.this.refreshLayout.finishRefresh();
                        InviteRecordActivity.this.refreshLayout.finishLoadMore(true);
                        InviteRecordActivity.this.refreshLayout.setEnableLoadMore(true);
                    }
                    if (conmdList2Bean != null) {
                        try {
                            if (conmdList2Bean.getCode() == 0) {
                                Gson gson = new Gson();
                                ConmdList2Bean.DataBean dataBean = (ConmdList2Bean.DataBean) gson.fromJson(gson.toJson(conmdList2Bean.getData()), new TypeToken<ConmdList2Bean.DataBean>() {
                                }.getType());
                                ArrayList<ConmdList2Bean.ResultBean> list = dataBean.getList();
                                ConmdList2Bean.PagesBean pages = dataBean.getPages();
                                if (pages.getSize() > 0) {
                                    if (pages.getSize() < pages.getPageSize() && InviteRecordActivity.this.refreshLayout != null) {
                                        InviteRecordActivity.this.refreshLayout.setEnableLoadMore(false);
                                    }
                                    if (z) {
                                        InviteRecordActivity.this.items.addAll(list);
                                    } else {
                                        InviteRecordActivity.this.items.clear();
                                        ArrayList unused = InviteRecordActivity.this.items = list;
                                    }
                                    InviteRecordActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(InviteRecordActivity.this));
                                    InviteRecordActivity.this.recyclerView.addItemDecoration(new RecyclerViewDivider(InviteRecordActivity.this, 1, 0, ContextCompat.getColor(InviteRecordActivity.this, 2131099799)));
                                    InviteRecordActivity.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                                    InviteRecordActivity.this.recyclerView.setAdapter(new BaseRecyclerAdapter<ConmdList2Bean.ResultBean>(InviteRecordActivity.this.items, 2131427476, (AdapterView.OnItemClickListener) null) {
                                        /* access modifiers changed from: protected */
                                        public void onBindViewHolder(SmartViewHolder smartViewHolder, ConmdList2Bean.ResultBean resultBean, int i) {
                                            String phone = resultBean.getPhone();
                                            String createTime = resultBean.getCreateTime();
                                            String result = resultBean.getResult();
                                            if (phone != null && phone.length() == 11) {
                                                phone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
                                            }
                                            smartViewHolder.text(2131297126, (CharSequence) phone);
                                            smartViewHolder.text(2131296826, (CharSequence) createTime);
                                            if (result != null) {
                                                smartViewHolder.text(2131296825, (CharSequence) result);
                                                if (result.contains("未登录")) {
                                                    smartViewHolder.textColorId(2131296825, 2131099768);
                                                } else if (result.contains("未支付")) {
                                                    smartViewHolder.textColorId(2131296825, 2131099759);
                                                } else if (result.contains("奖励")) {
                                                    smartViewHolder.textColorId(2131296825, 2131099759);
                                                }
                                            }
                                        }
                                    });
                                } else if (InviteRecordActivity.this.refreshLayout != null) {
                                    InviteRecordActivity.this.refreshLayout.setEnableLoadMore(false);
                                }
                            } else {
                                ToastUtils.showToast(InviteRecordActivity.this, conmdList2Bean.getMessage());
                            }
                        } catch (Exception e) {
                            if (z) {
                                InviteRecordActivity.access$110(InviteRecordActivity.this);
                            }
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

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
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.InviteRecordActivity] */
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

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception e) {
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
}
