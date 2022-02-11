package com.wx.assistants.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.adapter.BaseRecyclerAdapter;
import com.wx.assistants.adapter.SmartViewHolder;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.MemberCardBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;
import java.util.Date;

public class MyMemberCardFragment extends Fragment {
    /* access modifiers changed from: private */
    public ArrayList<MemberCardBean> items = new ArrayList<>();
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    private Unbinder unbinder;

    static /* synthetic */ int access$110(MyMemberCardFragment myMemberCardFragment) {
        int i = myMemberCardFragment.page;
        myMemberCardFragment.page = i - 1;
        return i;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427538, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        initData(false, 1);
        initViewListener();
        return inflate;
    }

    public void initData(final boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().myMemberCard(i, 20, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(getActivity(), "user_token", ""), new ApiWrapper.CallbackListener<ConmdListBean>() {
            public void onFinish(ConmdListBean conmdListBean) {
                if (MyMemberCardFragment.this.refreshLayout != null) {
                    MyMemberCardFragment.this.refreshLayout.finishRefresh();
                    MyMemberCardFragment.this.refreshLayout.finishLoadMore(true);
                    MyMemberCardFragment.this.refreshLayout.setEnableLoadMore(true);
                }
                if (conmdListBean != null) {
                    try {
                        if (conmdListBean.getCode() == 0) {
                            ConmdListBean.LimitPageBean data = conmdListBean.getData();
                            if (data.getSize() > 0) {
                                if (data.getSize() < data.getPageSize() && MyMemberCardFragment.this.refreshLayout != null) {
                                    MyMemberCardFragment.this.refreshLayout.setEnableLoadMore(false);
                                }
                                Gson gson = new Gson();
                                ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(data.getList()), new TypeToken<ArrayList<MemberCardBean>>() {
                                }.getType());
                                if (z) {
                                    MyMemberCardFragment.this.items.addAll(arrayList);
                                } else {
                                    MyMemberCardFragment.this.items.clear();
                                    ArrayList unused = MyMemberCardFragment.this.items = arrayList;
                                }
                                MyMemberCardFragment.this.recyclerView.setLayoutManager(new LinearLayoutManager(MyMemberCardFragment.this.getActivity()));
                                MyMemberCardFragment.this.recyclerView.addItemDecoration(new RecyclerViewDivider(MyMemberCardFragment.this.getActivity(), 1, 0, ContextCompat.getColor(MyMemberCardFragment.this.getActivity(), 2131099799)));
                                MyMemberCardFragment.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                                MyMemberCardFragment.this.recyclerView.setAdapter(new BaseRecyclerAdapter<MemberCardBean>(MyMemberCardFragment.this.items, 2131427478, (AdapterView.OnItemClickListener) null) {
                                    /* access modifiers changed from: protected */
                                    public void onBindViewHolder(SmartViewHolder smartViewHolder, MemberCardBean memberCardBean, int i) {
                                        smartViewHolder.text(2131297492, (CharSequence) memberCardBean.getTypeName());
                                        smartViewHolder.text(2131297488, (CharSequence) memberCardBean.getActivationCode());
                                        Date activationTime = memberCardBean.getActivationTime();
                                        String convertDate2String = DateUtils.convertDate2String(activationTime, "yyyy-MM-dd");
                                        smartViewHolder.text(2131297493, (CharSequence) DateUtils.convertDate2String(activationTime, "HH:mm:ss"));
                                        smartViewHolder.text(2131297490, (CharSequence) convertDate2String);
                                    }
                                });
                            } else if (MyMemberCardFragment.this.refreshLayout != null) {
                                MyMemberCardFragment.this.refreshLayout.setEnableLoadMore(false);
                            }
                        } else {
                            ToastUtils.showToast(MyMemberCardFragment.this.getActivity(), conmdListBean.getMessage());
                        }
                    } catch (Exception unused2) {
                        LogUtils.log("WS_BABY_Catch_48");
                        if (z) {
                            MyMemberCardFragment.access$110(MyMemberCardFragment.this);
                        }
                    }
                }
            }

            public void onFailure(FailureModel failureModel) {
                if (z) {
                    MyMemberCardFragment.access$110(MyMemberCardFragment.this);
                }
                if (MyMemberCardFragment.this.refreshLayout != null) {
                    MyMemberCardFragment.this.refreshLayout.finishRefresh();
                    MyMemberCardFragment.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(MyMemberCardFragment.this.getActivity(), failureModel.getErrorMessage());
                }
            }
        });
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                MyMemberCardFragment.this.initData(false, 1);
            }
        });
        this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                int unused = MyMemberCardFragment.this.page = MyMemberCardFragment.this.page + 1;
                MyMemberCardFragment.this.initData(true, MyMemberCardFragment.this.page);
            }
        });
    }

    public void onDestroyView() {
        MyMemberCardFragment.super.onDestroyView();
        this.unbinder.unbind();
    }
}
