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
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.NoticeBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.Date;

public class SystemMsgActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public BaseRecyclerAdapter adpter;
    /* access modifiers changed from: private */
    public ArrayList<NoticeBean> items = new ArrayList<>();
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

    static {
        StubApp.interface11(9393);
    }

    static /* synthetic */ int access$210(SystemMsgActivity systemMsgActivity) {
        int i = systemMsgActivity.page;
        systemMsgActivity.page = i - 1;
        return i;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.SystemMsgActivity] */
    public void initData() {
        this.navTitle.setText("系统消息");
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.addItemDecoration(new RecyclerViewDivider(this, 1, 0, ContextCompat.getColor(this, 2131099799)));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.adpter = new BaseRecyclerAdapter<NoticeBean>(this.items, 2131427480, (AdapterView.OnItemClickListener) null) {
            /* access modifiers changed from: protected */
            public void onBindViewHolder(SmartViewHolder smartViewHolder, final NoticeBean noticeBean, int i) {
                Date createTime = noticeBean.getCreateTime();
                String noticeTitle = noticeBean.getNoticeTitle();
                String noticeDesc = noticeBean.getNoticeDesc();
                String convertDate2String = DateUtils.convertDate2String(createTime, "yyyy-MM-dd");
                String convertDate2String2 = DateUtils.convertDate2String(createTime, "HH:mm:ss");
                smartViewHolder.text(2131296826, (CharSequence) convertDate2String + " " + convertDate2String2);
                smartViewHolder.text(2131297072, (CharSequence) noticeTitle);
                smartViewHolder.text(2131297071, (CharSequence) noticeDesc);
                smartViewHolder.click(2131296833, new View.OnClickListener() {
                    /* JADX WARNING: type inference failed for: r1v5, types: [android.content.Context, com.wx.assistants.activity.SystemMsgActivity] */
                    public void onClick(View view) {
                        String noticeUrl = noticeBean.getNoticeUrl();
                        if (noticeUrl != null && !"".equals(noticeUrl) && noticeUrl.startsWith("http")) {
                            WebViewActivity.startActivity(SystemMsgActivity.this, "", noticeUrl);
                        }
                    }
                });
            }
        };
        this.recyclerView.setAdapter(this.adpter);
        initRecordData(false, 1);
        initViewListener();
    }

    public void initRecordData(final boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().myNotice(i, 10, "-1", new ApiWrapper.CallbackListener<ConmdListBean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.SystemMsgActivity] */
            public void onFailure(FailureModel failureModel) {
                if (z) {
                    SystemMsgActivity.access$210(SystemMsgActivity.this);
                }
                if (SystemMsgActivity.this.refreshLayout != null) {
                    SystemMsgActivity.this.refreshLayout.finishRefresh();
                    SystemMsgActivity.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(SystemMsgActivity.this, failureModel.getErrorMessage());
                }
            }

            /* JADX WARNING: type inference failed for: r0v7, types: [android.content.Context, com.wx.assistants.activity.SystemMsgActivity] */
            public void onFinish(ConmdListBean conmdListBean) {
                try {
                    if (SystemMsgActivity.this.refreshLayout != null) {
                        SystemMsgActivity.this.refreshLayout.finishRefresh();
                        SystemMsgActivity.this.refreshLayout.finishLoadMore(true);
                        SystemMsgActivity.this.refreshLayout.setEnableLoadMore(true);
                    }
                    if (conmdListBean != null) {
                        try {
                            if (conmdListBean.getCode() == 0) {
                                ConmdListBean.LimitPageBean data = conmdListBean.getData();
                                if (data.getSize() > 0) {
                                    if (data.getSize() < data.getPageSize() && SystemMsgActivity.this.refreshLayout != null) {
                                        SystemMsgActivity.this.refreshLayout.setEnableLoadMore(false);
                                    }
                                    Gson gson = new Gson();
                                    ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(data.getList()), new TypeToken<ArrayList<NoticeBean>>() {
                                    }.getType());
                                    if (z) {
                                        SystemMsgActivity.this.items.addAll(arrayList);
                                    } else {
                                        SystemMsgActivity.this.items.clear();
                                        ArrayList unused = SystemMsgActivity.this.items = arrayList;
                                    }
                                    SystemMsgActivity.this.adpter.setData(SystemMsgActivity.this.items);
                                    SystemMsgActivity.this.adpter.notifyDataSetChanged();
                                } else if (SystemMsgActivity.this.refreshLayout != null) {
                                    SystemMsgActivity.this.refreshLayout.setEnableLoadMore(false);
                                }
                            } else {
                                ToastUtils.showToast(SystemMsgActivity.this, conmdListBean.getMessage());
                            }
                        } catch (Exception e) {
                            if (z) {
                                SystemMsgActivity.access$210(SystemMsgActivity.this);
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
                SystemMsgActivity.this.initRecordData(false, 1);
            }
        });
        this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                int unused = SystemMsgActivity.this.page = SystemMsgActivity.this.page + 1;
                SystemMsgActivity.this.initRecordData(true, SystemMsgActivity.this.page);
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }
}
