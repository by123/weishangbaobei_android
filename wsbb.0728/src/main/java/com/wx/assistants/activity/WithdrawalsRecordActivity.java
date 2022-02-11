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
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.ExtractRecordBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;
import java.util.Date;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class WithdrawalsRecordActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public BaseRecyclerAdapter adapter;
    /* access modifiers changed from: private */
    public ArrayList<ExtractRecordBean> items = new ArrayList<>();
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
    @BindView(2131297531)
    LinearLayout topLayout;

    static {
        StubApp.interface11(6760);
    }

    static /* synthetic */ int access$210(WithdrawalsRecordActivity withdrawalsRecordActivity) {
        int i = withdrawalsRecordActivity.page;
        withdrawalsRecordActivity.page = i - 1;
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
                                WithdrawalsRecordActivity.this.topLayout.setVisibility(8);
                            }
                        } else if (conmdBean.getMessage().contains("重新登录")) {
                            WithdrawalsRecordActivity.this.topLayout.setVisibility(0);
                            WithdrawalsRecordActivity.this.setUserName.setText("未知用户");
                            WithdrawalsRecordActivity.this.setExpireTime.setText("尚未登录，请登录");
                            WithdrawalsRecordActivity.this.operationBtn.setVisibility(0);
                            WithdrawalsRecordActivity.this.operationBtn.setText("登录");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.WithdrawalsRecordActivity] */
    public void initData() {
        this.navTitle.setText("提现记录");
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.addItemDecoration(new RecyclerViewDivider(this, 1, 0, ContextCompat.getColor(this, 2131099799)));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.adapter = new BaseRecyclerAdapter<ExtractRecordBean>(this.items, 2131427475, (AdapterView.OnItemClickListener) null) {
            /* access modifiers changed from: protected */
            public void onBindViewHolder(SmartViewHolder smartViewHolder, ExtractRecordBean extractRecordBean, int i) {
                Date createTime = extractRecordBean.getCreateTime();
                String withdrawAmount = extractRecordBean.getWithdrawAmount();
                String state = extractRecordBean.getState();
                String result = extractRecordBean.getResult();
                String convertDate2String = DateUtils.convertDate2String(createTime, "yyyy-MM-dd");
                String convertDate2String2 = DateUtils.convertDate2String(createTime, "HH:mm:ss");
                smartViewHolder.text(2131296574, (CharSequence) convertDate2String);
                smartViewHolder.text(2131297508, (CharSequence) convertDate2String2);
                smartViewHolder.text(2131296654, (CharSequence) "提现" + withdrawAmount + "元");
                if ("1.0".equals(state) || "1".equals(state)) {
                    smartViewHolder.text(2131296655, (CharSequence) "申请中");
                    smartViewHolder.textColorId(2131296655, 2131099768);
                } else if (SocializeConstants.PROTOCOL_VERSON.equals(state) || "2".equals(state)) {
                    smartViewHolder.text(2131296655, (CharSequence) "取消");
                    smartViewHolder.textColorId(2131296655, 2131099759);
                } else if ("3.0".equals(state) || "3".equals(state)) {
                    smartViewHolder.text(2131296655, (CharSequence) "已完成");
                    smartViewHolder.textColorId(2131296655, 2131099740);
                } else if ("4.0".equals(state) || "4".equals(state)) {
                    if (result == null) {
                        result = "支付失败";
                    }
                    smartViewHolder.text(2131296655, (CharSequence) result);
                    smartViewHolder.textColorId(2131296655, 2131099759);
                }
            }
        };
        this.recyclerView.setAdapter(this.adapter);
        initRecordData(false, 1);
        initViewListener();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.WithdrawalsRecordActivity] */
    public void initRecordData(final boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().myExtractList(i, 10, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(this, "user_token", ""), new ApiWrapper.CallbackListener<ConmdListBean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.WithdrawalsRecordActivity] */
            public void onFailure(FailureModel failureModel) {
                if (z) {
                    WithdrawalsRecordActivity.access$210(WithdrawalsRecordActivity.this);
                }
                if (WithdrawalsRecordActivity.this.refreshLayout != null) {
                    WithdrawalsRecordActivity.this.refreshLayout.finishRefresh();
                    WithdrawalsRecordActivity.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(WithdrawalsRecordActivity.this, failureModel.getErrorMessage());
                }
            }

            /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context, com.wx.assistants.activity.WithdrawalsRecordActivity] */
            public void onFinish(ConmdListBean conmdListBean) {
                if (WithdrawalsRecordActivity.this.refreshLayout != null) {
                    WithdrawalsRecordActivity.this.refreshLayout.finishRefresh();
                    WithdrawalsRecordActivity.this.refreshLayout.finishLoadMore(true);
                    WithdrawalsRecordActivity.this.refreshLayout.setEnableLoadMore(true);
                }
                if (conmdListBean != null) {
                    try {
                        if (conmdListBean.getCode() == 0) {
                            ConmdListBean.LimitPageBean data = conmdListBean.getData();
                            if (data.getSize() > 0) {
                                if (data.getSize() < data.getPageSize()) {
                                    WithdrawalsRecordActivity.this.refreshLayout.setEnableLoadMore(false);
                                }
                                Gson gson = new Gson();
                                ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(data.getList()), new TypeToken<ArrayList<ExtractRecordBean>>() {
                                }.getType());
                                if (z) {
                                    WithdrawalsRecordActivity.this.items.addAll(arrayList);
                                } else {
                                    WithdrawalsRecordActivity.this.items.clear();
                                    ArrayList unused = WithdrawalsRecordActivity.this.items = arrayList;
                                }
                                WithdrawalsRecordActivity.this.adapter.setData(WithdrawalsRecordActivity.this.items);
                                WithdrawalsRecordActivity.this.adapter.notifyDataSetChanged();
                            } else if (WithdrawalsRecordActivity.this.refreshLayout != null) {
                                WithdrawalsRecordActivity.this.refreshLayout.setEnableLoadMore(false);
                            }
                        } else {
                            ToastUtils.showToast(WithdrawalsRecordActivity.this, conmdListBean.getMessage());
                        }
                    } catch (Exception e) {
                        if (z) {
                            WithdrawalsRecordActivity.access$210(WithdrawalsRecordActivity.this);
                        }
                    }
                }
            }
        });
    }

    public void initViewListener() {
        if (this.refreshLayout != null) {
            this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
                public void onRefresh(RefreshLayout refreshLayout) {
                    WithdrawalsRecordActivity.this.initRecordData(false, 1);
                }
            });
            this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
                public void onLoadMore(RefreshLayout refreshLayout) {
                    int unused = WithdrawalsRecordActivity.this.page = WithdrawalsRecordActivity.this.page + 1;
                    WithdrawalsRecordActivity.this.initRecordData(true, WithdrawalsRecordActivity.this.page);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.WithdrawalsRecordActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.WithdrawalsRecordActivity] */
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
