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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdList2Bean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.LogUtils;
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

    public class MyInviteRecordListAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private List<ConmdList2Bean.ResultBean> list;

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

        public MyInviteRecordListAdapter(List<ConmdList2Bean.ResultBean> list2, Context context2) {
            this.list = list2;
            this.context = context2;
        }

        public int getItemCount() {
            return this.list.size();
        }

        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            String str;
            ConmdList2Bean.ResultBean resultBean = this.list.get(i);
            String phone = resultBean.getPhone();
            String createTime = resultBean.getCreateTime();
            String result = resultBean.getResult();
            if (phone == null || phone.length() != 11) {
                str = phone;
            } else {
                str = phone.substring(0, 3) + "****" + phone.substring(7, 11);
            }
            viewHolder.phoneNumText.setText(str);
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

        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(this.context).inflate(2131427476, (ViewGroup) null));
        }

        public void setData(List<ConmdList2Bean.ResultBean> list2) {
            this.list = list2;
        }
    }

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

    public void initData() {
        initRecordData(false, 1);
        initViewListener();
    }

    public void initRecordData(final boolean z, int i) {
        this.page = i;
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        this.type = getArguments().getInt("type");
        ApiWrapper.getInstance().myMemberInvite(i, 20, macAddress, this.type, (ApiWrapper.CallbackListener<ConmdList2Bean>) new ApiWrapper.CallbackListener<ConmdList2Bean>() {
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

            public void onFinish(ConmdList2Bean conmdList2Bean) {
                try {
                    if (InviteRecordOneFragment.this.refreshLayout != null) {
                        InviteRecordOneFragment.this.refreshLayout.finishRefresh();
                        InviteRecordOneFragment.this.refreshLayout.finishLoadMore(true);
                        InviteRecordOneFragment.this.refreshLayout.setEnableLoadMore(true);
                    }
                    if (conmdList2Bean != null) {
                        try {
                            if (conmdList2Bean.getCode() == 0) {
                                Gson gson = new Gson();
                                String json = gson.toJson(conmdList2Bean.getData());
                                LogUtils.log("WS_BABY_X" + json);
                                ConmdList2Bean.DataBean dataBean = (ConmdList2Bean.DataBean) gson.fromJson(json, new TypeToken<ConmdList2Bean.DataBean>() {
                                }.getType());
                                ArrayList<ConmdList2Bean.ResultBean> list = dataBean.getList();
                                ConmdList2Bean.PagesBean pages = dataBean.getPages();
                                if (pages.getSize() > 0) {
                                    if (pages.getSize() < pages.getPageSize() && InviteRecordOneFragment.this.refreshLayout != null) {
                                        InviteRecordOneFragment.this.refreshLayout.setEnableLoadMore(false);
                                    }
                                    if (z) {
                                        InviteRecordOneFragment.this.items.addAll(list);
                                    } else {
                                        InviteRecordOneFragment.this.items.clear();
                                        ArrayList unused = InviteRecordOneFragment.this.items = list;
                                    }
                                    InviteRecordOneFragment.this.adapter.setData(InviteRecordOneFragment.this.items);
                                    InviteRecordOneFragment.this.adapter.notifyDataSetChanged();
                                } else if (InviteRecordOneFragment.this.refreshLayout != null) {
                                    InviteRecordOneFragment.this.refreshLayout.setEnableLoadMore(false);
                                }
                            } else {
                                ToastUtils.showToast(InviteRecordOneFragment.this.getActivity(), conmdList2Bean.getMessage());
                            }
                        } catch (Exception e) {
                            if (z) {
                                InviteRecordOneFragment.access$210(InviteRecordOneFragment.this);
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
}
