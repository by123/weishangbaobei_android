package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
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
import com.wx.assistants.adapter.BaseRecyclerAdapter;
import com.wx.assistants.adapter.SmartViewHolder;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdList2Bean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;

public class MyInviteRecordActivity extends BaseActivity {
    @BindView(2131296366)
    LinearLayout backLayout;
    @BindView(2131296820)
    TextView invitePeopleNum;
    @BindView(2131296822)
    TextView inviteReward;
    /* access modifiers changed from: private */
    public ArrayList<ConmdList2Bean.ResultBean> items = new ArrayList<>();
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297680)
    TextView withdrawal;
    @BindView(2131297681)
    TextView withdrawalAmount;

    static {
        StubApp.interface11(9046);
    }

    static /* synthetic */ int access$110(MyInviteRecordActivity myInviteRecordActivity) {
        int i = myInviteRecordActivity.page;
        myInviteRecordActivity.page = i - 1;
        return i;
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
                if (MyInviteRecordActivity.this.refreshLayout != null) {
                    MyInviteRecordActivity.this.refreshLayout.finishRefresh(false);
                }
            }

            public void onFinish(ConmdBean conmdBean) {
                if (MyInviteRecordActivity.this.refreshLayout != null) {
                    MyInviteRecordActivity.this.refreshLayout.finishRefresh();
                }
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 1 && conmdBean.getMessage() != null) {
                            conmdBean.getMessage().contains("请重新登录");
                        } else if (conmdBean.getCode() == 0) {
                            String json = new Gson().toJson(conmdBean.getData());
                            try {
                                SPUtils.put(MyApplication.getConText(), "ws_baby_user_info", json);
                            } catch (Exception e) {
                            }
                            MyInviteRecordActivity.this.setUserInfo(json);
                        }
                    } catch (Exception e2) {
                        LogUtils.log("WS_BABY_Catch_35");
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

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.MyInviteRecordActivity] */
    public void initRecordData(final boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().myMemberInvite(i, 20, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(this, "user_token", ""), (ApiWrapper.CallbackListener<ConmdList2Bean>) new ApiWrapper.CallbackListener<ConmdList2Bean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.MyInviteRecordActivity] */
            public void onFailure(FailureModel failureModel) {
                if (z) {
                    MyInviteRecordActivity.access$110(MyInviteRecordActivity.this);
                }
                if (MyInviteRecordActivity.this.refreshLayout != null) {
                    MyInviteRecordActivity.this.refreshLayout.finishRefresh();
                    MyInviteRecordActivity.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(MyInviteRecordActivity.this, failureModel.getErrorMessage());
                }
            }

            /* JADX WARNING: type inference failed for: r0v7, types: [android.content.Context, com.wx.assistants.activity.MyInviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r2v4, types: [android.content.Context, com.wx.assistants.activity.MyInviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r2v5, types: [android.content.Context, com.wx.assistants.activity.MyInviteRecordActivity] */
            /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.MyInviteRecordActivity] */
            public void onFinish(ConmdList2Bean conmdList2Bean) {
                try {
                    if (MyInviteRecordActivity.this.refreshLayout != null) {
                        MyInviteRecordActivity.this.refreshLayout.finishRefresh();
                        MyInviteRecordActivity.this.refreshLayout.finishLoadMore(true);
                        MyInviteRecordActivity.this.refreshLayout.setEnableLoadMore(true);
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
                                    if (pages.getSize() < pages.getPageSize() && MyInviteRecordActivity.this.refreshLayout != null) {
                                        MyInviteRecordActivity.this.refreshLayout.setEnableLoadMore(false);
                                    }
                                    if (z) {
                                        MyInviteRecordActivity.this.items.addAll(list);
                                    } else {
                                        MyInviteRecordActivity.this.items.clear();
                                        ArrayList unused = MyInviteRecordActivity.this.items = list;
                                    }
                                    MyInviteRecordActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(MyInviteRecordActivity.this));
                                    MyInviteRecordActivity.this.recyclerView.addItemDecoration(new RecyclerViewDivider(MyInviteRecordActivity.this, 1, 0, ContextCompat.getColor(MyInviteRecordActivity.this, 2131099799)));
                                    MyInviteRecordActivity.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                                    MyInviteRecordActivity.this.recyclerView.setAdapter(new BaseRecyclerAdapter<ConmdList2Bean.ResultBean>(MyInviteRecordActivity.this.items, 2131427476, (AdapterView.OnItemClickListener) null) {
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
                                } else if (MyInviteRecordActivity.this.refreshLayout != null) {
                                    MyInviteRecordActivity.this.refreshLayout.setEnableLoadMore(false);
                                }
                            } else {
                                ToastUtils.showToast(MyInviteRecordActivity.this, conmdList2Bean.getMessage());
                            }
                        } catch (Exception e) {
                            if (z) {
                                MyInviteRecordActivity.access$110(MyInviteRecordActivity.this);
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
                MyInviteRecordActivity.this.initRecordData(false, 1);
            }
        });
        this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                int unused = MyInviteRecordActivity.this.page = MyInviteRecordActivity.this.page + 1;
                MyInviteRecordActivity.this.initRecordData(true, MyInviteRecordActivity.this.page);
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

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.MyInviteRecordActivity] */
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

    @OnClick({2131297049, 2131297680, 2131296366})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296366) {
            back();
        } else if (id == 2131297049) {
            back();
        } else if (id == 2131297680) {
            startActivity(AlipayWithdrawalsActivity.class, true, true);
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
                TextView textView = this.withdrawalAmount;
                textView.setText(userBean.getWithdrawBalance() + "");
                TextView textView2 = this.invitePeopleNum;
                textView2.setText(userBean.getInviteNum() + "");
                TextView textView3 = this.inviteReward;
                textView3.setText(userBean.getWithdrawSum() + "");
            }
        }
    }
}
