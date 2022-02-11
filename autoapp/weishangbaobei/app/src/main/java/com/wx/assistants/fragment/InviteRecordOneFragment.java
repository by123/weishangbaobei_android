package com.wx.assistants.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdList2Bean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;
import java.util.List;

public class InviteRecordOneFragment extends Fragment {
    /* access modifiers changed from: private */
    public MyInviteRecordListAdapter adapter;
    /* access modifiers changed from: private */
    public ArrayList<ConmdList2Bean.ResultBean> items = new ArrayList<>();
    private View mCacheView;
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    private int type = 1;

    static /* synthetic */ int access$210(InviteRecordOneFragment inviteRecordOneFragment) {
        int i = inviteRecordOneFragment.page;
        inviteRecordOneFragment.page = i - 1;
        return i;
    }

    public static InviteRecordOneFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        InviteRecordOneFragment inviteRecordOneFragment = new InviteRecordOneFragment();
        inviteRecordOneFragment.setArguments(bundle);
        return inviteRecordOneFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.mCacheView == null) {
            this.mCacheView = layoutInflater.inflate(2131427532, (ViewGroup) null);
        }
        ViewGroup viewGroup2 = (ViewGroup) this.mCacheView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCacheView);
        }
        ButterKnife.bind(this, this.mCacheView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), 1, 0, ContextCompat.getColor(getActivity(), 2131099799)));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.adapter = new MyInviteRecordListAdapter(this.items, getActivity());
        this.recyclerView.setAdapter(this.adapter);
        initData();
        return this.mCacheView;
    }

    public void initData() {
        initRecordData(false, 1);
        initViewListener();
    }

    public void initRecordData(final boolean z, int i) {
        this.page = i;
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        this.type = getArguments().getInt("type");
        ApiWrapper.getInstance().myMemberInvite(i, 20, macAddress, this.type, (ApiWrapper.CallbackListener<ConmdList2Bean>) new ApiWrapper.CallbackListener<ConmdList2Bean>() {
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x00cd, code lost:
                if (r8 != false) goto L_0x00cf;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:27:0x00cf, code lost:
                com.wx.assistants.fragment.InviteRecordOneFragment.access$210(r3.this$0);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
                return;
             */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x00cb */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(com.wx.assistants.bean.ConmdList2Bean r4) {
                /*
                    r3 = this;
                    com.wx.assistants.fragment.InviteRecordOneFragment r0 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00d5 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00d5 }
                    if (r0 == 0) goto L_0x001c
                    com.wx.assistants.fragment.InviteRecordOneFragment r0 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00d5 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00d5 }
                    r0.finishRefresh()     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.fragment.InviteRecordOneFragment r0 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00d5 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00d5 }
                    r1 = 1
                    r0.finishLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.fragment.InviteRecordOneFragment r0 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00d5 }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r0 = r0.refreshLayout     // Catch:{ Exception -> 0x00d5 }
                    r0.setEnableLoadMore((boolean) r1)     // Catch:{ Exception -> 0x00d5 }
                L_0x001c:
                    if (r4 == 0) goto L_0x00d9
                    int r0 = r4.getCode()     // Catch:{ Exception -> 0x00cb }
                    if (r0 != 0) goto L_0x00bd
                    com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x00cb }
                    r0.<init>()     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.bean.ConmdList2Bean$DataBean r4 = r4.getData()     // Catch:{ Exception -> 0x00cb }
                    java.lang.String r4 = r0.toJson(r4)     // Catch:{ Exception -> 0x00cb }
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00cb }
                    r1.<init>()     // Catch:{ Exception -> 0x00cb }
                    java.lang.String r2 = "WS_BABY_X"
                    r1.append(r2)     // Catch:{ Exception -> 0x00cb }
                    r1.append(r4)     // Catch:{ Exception -> 0x00cb }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.utils.LogUtils.log(r1)     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.fragment.InviteRecordOneFragment$1$1 r1 = new com.wx.assistants.fragment.InviteRecordOneFragment$1$1     // Catch:{ Exception -> 0x00cb }
                    r1.<init>()     // Catch:{ Exception -> 0x00cb }
                    java.lang.reflect.Type r1 = r1.getType()     // Catch:{ Exception -> 0x00cb }
                    java.lang.Object r4 = r0.fromJson(r4, r1)     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.bean.ConmdList2Bean$DataBean r4 = (com.wx.assistants.bean.ConmdList2Bean.DataBean) r4     // Catch:{ Exception -> 0x00cb }
                    java.util.ArrayList r0 = r4.getList()     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.bean.ConmdList2Bean$PagesBean r4 = r4.getPages()     // Catch:{ Exception -> 0x00cb }
                    int r1 = r4.getSize()     // Catch:{ Exception -> 0x00cb }
                    r2 = 0
                    if (r1 <= 0) goto L_0x00af
                    int r1 = r4.getSize()     // Catch:{ Exception -> 0x00cb }
                    int r4 = r4.getPageSize()     // Catch:{ Exception -> 0x00cb }
                    if (r1 >= r4) goto L_0x007a
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = r4.refreshLayout     // Catch:{ Exception -> 0x00cb }
                    if (r4 == 0) goto L_0x007a
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = r4.refreshLayout     // Catch:{ Exception -> 0x00cb }
                    r4.setEnableLoadMore((boolean) r2)     // Catch:{ Exception -> 0x00cb }
                L_0x007a:
                    boolean r4 = r8     // Catch:{ Exception -> 0x00cb }
                    if (r4 == 0) goto L_0x0088
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    java.util.ArrayList r4 = r4.items     // Catch:{ Exception -> 0x00cb }
                    r4.addAll(r0)     // Catch:{ Exception -> 0x00cb }
                    goto L_0x0096
                L_0x0088:
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    java.util.ArrayList r4 = r4.items     // Catch:{ Exception -> 0x00cb }
                    r4.clear()     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    java.util.ArrayList unused = r4.items = r0     // Catch:{ Exception -> 0x00cb }
                L_0x0096:
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.fragment.InviteRecordOneFragment$MyInviteRecordListAdapter r4 = r4.adapter     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.fragment.InviteRecordOneFragment r0 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    java.util.ArrayList r0 = r0.items     // Catch:{ Exception -> 0x00cb }
                    r4.setData(r0)     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.fragment.InviteRecordOneFragment$MyInviteRecordListAdapter r4 = r4.adapter     // Catch:{ Exception -> 0x00cb }
                    r4.notifyDataSetChanged()     // Catch:{ Exception -> 0x00cb }
                    goto L_0x00d9
                L_0x00af:
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = r4.refreshLayout     // Catch:{ Exception -> 0x00cb }
                    if (r4 == 0) goto L_0x00d9
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = r4.refreshLayout     // Catch:{ Exception -> 0x00cb }
                    r4.setEnableLoadMore((boolean) r2)     // Catch:{ Exception -> 0x00cb }
                    goto L_0x00d9
                L_0x00bd:
                    com.wx.assistants.fragment.InviteRecordOneFragment r0 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00cb }
                    android.support.v4.app.FragmentActivity r0 = r0.getActivity()     // Catch:{ Exception -> 0x00cb }
                    java.lang.String r4 = r4.getMessage()     // Catch:{ Exception -> 0x00cb }
                    com.wx.assistants.utils.ToastUtils.showToast(r0, r4)     // Catch:{ Exception -> 0x00cb }
                    goto L_0x00d9
                L_0x00cb:
                    boolean r4 = r8     // Catch:{ Exception -> 0x00d5 }
                    if (r4 == 0) goto L_0x00d9
                    com.wx.assistants.fragment.InviteRecordOneFragment r4 = com.wx.assistants.fragment.InviteRecordOneFragment.this     // Catch:{ Exception -> 0x00d5 }
                    com.wx.assistants.fragment.InviteRecordOneFragment.access$210(r4)     // Catch:{ Exception -> 0x00d5 }
                    goto L_0x00d9
                L_0x00d5:
                    r4 = move-exception
                    r4.printStackTrace()
                L_0x00d9:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.fragment.InviteRecordOneFragment.AnonymousClass1.onFinish(com.wx.assistants.bean.ConmdList2Bean):void");
            }

            public void onFailure(FailureModel failureModel) {
                if (z) {
                    InviteRecordOneFragment.access$210(InviteRecordOneFragment.this);
                }
                if (InviteRecordOneFragment.this.refreshLayout != null) {
                    InviteRecordOneFragment.this.refreshLayout.finishRefresh();
                    InviteRecordOneFragment.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(InviteRecordOneFragment.this.getActivity(), failureModel.getErrorMessage());
                }
            }
        });
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                InviteRecordOneFragment.this.initRecordData(false, 1);
            }
        });
        this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                int unused = InviteRecordOneFragment.this.page = InviteRecordOneFragment.this.page + 1;
                InviteRecordOneFragment.this.initRecordData(true, InviteRecordOneFragment.this.page);
            }
        });
    }

    public class MyInviteRecordListAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private List<ConmdList2Bean.ResultBean> list;

        public void setData(List<ConmdList2Bean.ResultBean> list2) {
            this.list = list2;
        }

        public MyInviteRecordListAdapter(List<ConmdList2Bean.ResultBean> list2, Context context2) {
            this.list = list2;
            this.context = context2;
        }

        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427476, (ViewGroup) null));
        }

        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            ConmdList2Bean.ResultBean resultBean = this.list.get(i);
            String phone = resultBean.getPhone();
            String createTime = resultBean.getCreateTime();
            String result = resultBean.getResult();
            if (phone != null && phone.length() == 11) {
                phone = phone.substring(0, 3) + "****" + phone.substring(7, 11);
            }
            viewHolder.phoneNumText.setText(phone);
            viewHolder.inviteTimeText.setText(createTime);
            if (result != null) {
                viewHolder.inviteStatusText.setText(result);
                if (result.contains("未登录")) {
                    viewHolder.inviteStatusText.setTextColor(InviteRecordOneFragment.this.getResources().getColor(2131099768));
                } else if (result.contains("未支付")) {
                    viewHolder.inviteStatusText.setTextColor(InviteRecordOneFragment.this.getResources().getColor(2131099759));
                } else if (result.contains("奖励")) {
                    viewHolder.inviteStatusText.setTextColor(InviteRecordOneFragment.this.getResources().getColor(2131099759));
                } else {
                    viewHolder.inviteStatusText.setTextColor(InviteRecordOneFragment.this.getResources().getColor(2131099740));
                }
            }
        }

        public int getItemCount() {
            return this.list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView inviteStatusText;
            TextView inviteTimeText;
            LinearLayout itemLayout;
            TextView phoneNumText;

            public ViewHolder(View view) {
                super(view);
                this.itemLayout = (LinearLayout) view.findViewById(2131296833);
                this.phoneNumText = (TextView) view.findViewById(2131297126);
                this.inviteTimeText = (TextView) view.findViewById(2131296826);
                this.inviteStatusText = (TextView) view.findViewById(2131296825);
            }
        }
    }
}
