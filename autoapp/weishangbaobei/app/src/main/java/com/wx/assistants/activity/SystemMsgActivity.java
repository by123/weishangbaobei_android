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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

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
                    /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.SystemMsgActivity] */
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void initRecordData(final boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().myNotice(i, 10, "-1", new ApiWrapper.CallbackListener<ConmdListBean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.SystemMsgActivity] */
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b1, code lost:
                if (r4 != false) goto L_0x00b3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b3, code lost:
                com.wx.assistants.activity.SystemMsgActivity.access$210(r3.this$0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                return;
             */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00af */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdListBean r4) {
                /*
                    r3 = this;
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00b9 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00b9 }
                    if (r0 == 0) goto L_0x001c
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00b9 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00b9 }
                    r0.finishRefresh()     // Catch:{ Exception -> 0x00b9 }
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00b9 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00b9 }
                    r1 = 1
                    r0.finishLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00b9 }
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00b9 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00b9 }
                    r0.setEnableLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00b9 }
                L_0x001c:
                    if (r4 == 0) goto L_0x00bd
                    int r0 = r4.getCode()     // Catch:{ Exception -> 0x00af }
                    if (r0 != 0) goto L_0x00a5
                    com.wx.assistants.bean.ConmdListBean$LimitPageBean r4 = r4.getData()     // Catch:{ Exception -> 0x00af }
                    int r0 = r4.getSize()     // Catch:{ Exception -> 0x00af }
                    r1 = 0
                    if (r0 <= 0) goto L_0x0097
                    int r0 = r4.getSize()     // Catch:{ Exception -> 0x00af }
                    int r2 = r4.getPageSize()     // Catch:{ Exception -> 0x00af }
                    if (r0 >= r2) goto L_0x0046
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00af }
                    if (r0 == 0) goto L_0x0046
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00af }
                    r0.setEnableLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00af }
                L_0x0046:
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x00af }
                    r0.<init>()     // Catch:{ Exception -> 0x00af }
                    java.lang.Object r4 = r4.getList()     // Catch:{ Exception -> 0x00af }
                    java.lang.String r4 = r0.toJson(r4)     // Catch:{ Exception -> 0x00af }
                    com.wx.assistants.activity.SystemMsgActivity$2$1 r1 = new com.wx.assistants.activity.SystemMsgActivity$2$1     // Catch:{ Exception -> 0x00af }
                    r1.<init>()     // Catch:{ Exception -> 0x00af }
                    java.lang.reflect.Type r1 = r1.getType()     // Catch:{ Exception -> 0x00af }
                    java.lang.Object r4 = r0.fromJson(r4, r1)     // Catch:{ Exception -> 0x00af }
                    java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ Exception -> 0x00af }
                    boolean r0 = r4     // Catch:{ Exception -> 0x00af }
                    if (r0 == 0) goto L_0x0070
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    java.util.ArrayList r0 = r0.items     // Catch:{ Exception -> 0x00af }
                    r0.addAll(r4)     // Catch:{ Exception -> 0x00af }
                    goto L_0x007e
                L_0x0070:
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    java.util.ArrayList r0 = r0.items     // Catch:{ Exception -> 0x00af }
                    r0.clear()     // Catch:{ Exception -> 0x00af }
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    java.util.ArrayList unused = r0.items = r4     // Catch:{ Exception -> 0x00af }
                L_0x007e:
                    com.wx.assistants.activity.SystemMsgActivity r4 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    com.wx.assistants.adapter.BaseRecyclerAdapter r4 = r4.adpter     // Catch:{ Exception -> 0x00af }
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    java.util.ArrayList r0 = r0.items     // Catch:{ Exception -> 0x00af }
                    r4.setData(r0)     // Catch:{ Exception -> 0x00af }
                    com.wx.assistants.activity.SystemMsgActivity r4 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    com.wx.assistants.adapter.BaseRecyclerAdapter r4 = r4.adpter     // Catch:{ Exception -> 0x00af }
                    r4.notifyDataSetChanged()     // Catch:{ Exception -> 0x00af }
                    goto L_0x00bd
                L_0x0097:
                    com.wx.assistants.activity.SystemMsgActivity r4 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = r4.refreshLayout     // Catch:{ Exception -> 0x00af }
                    if (r4 == 0) goto L_0x00bd
                    com.wx.assistants.activity.SystemMsgActivity r4 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = r4.refreshLayout     // Catch:{ Exception -> 0x00af }
                    r4.setEnableLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00af }
                    goto L_0x00bd
                L_0x00a5:
                    com.wx.assistants.activity.SystemMsgActivity r0 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00af }
                    java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x00af }
                    com.wx.assistants.utils.ToastUtils.showToast(r0, r4)     // Catch:{ Exception -> 0x00af }
                    goto L_0x00bd
                L_0x00af:
                    boolean r4 = r4     // Catch:{ Exception -> 0x00b9 }
                    if (r4 == 0) goto L_0x00bd
                    com.wx.assistants.activity.SystemMsgActivity r4 = com.wx.assistants.activity.SystemMsgActivity.this     // Catch:{ Exception -> 0x00b9 }
                    com.wx.assistants.activity.SystemMsgActivity.access$210(r4)     // Catch:{ Exception -> 0x00b9 }
                    goto L_0x00bd
                L_0x00b9:
                    r4 = move-exception
                    r4.printStackTrace()
                L_0x00bd:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.activity.SystemMsgActivity.AnonymousClass2.onFinish(com.wx.assistants.bean.ConmdListBean):void");
            }

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

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }
}
