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
import com.luck.picture.lib.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.adapter.BaseRecyclerAdapter;
import com.wx.assistants.adapter.SmartViewHolder;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.IndentBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;

public class MyIndentFragment extends Fragment {
    /* access modifiers changed from: private */
    public ArrayList<IndentBean> items = new ArrayList<>();
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    private Unbinder unbinder;

    public void initData(boolean z, int i) {
        this.page = i;
        ApiWrapper.getInstance().queryMemberAllOrders(MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(getActivity(), "user_token", ""), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
                if (MyIndentFragment.this.refreshLayout != null) {
                    MyIndentFragment.this.refreshLayout.finishRefresh();
                }
                if (failureModel != null) {
                    ToastUtils.showToast(MyIndentFragment.this.getActivity(), failureModel.getErrorMessage());
                }
            }

            public void onFinish(ConmdBean conmdBean) {
                if (MyIndentFragment.this.refreshLayout != null) {
                    MyIndentFragment.this.refreshLayout.finishRefresh();
                }
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0) {
                            Gson gson = new Gson();
                            MyIndentFragment.this.items.clear();
                            ArrayList unused = MyIndentFragment.this.items = (ArrayList) gson.fromJson(gson.toJson(conmdBean.getData()), new TypeToken<ArrayList<IndentBean>>() {
                            }.getType());
                            MyIndentFragment.this.recyclerView.setLayoutManager(new LinearLayoutManager(MyIndentFragment.this.getActivity()));
                            MyIndentFragment.this.recyclerView.addItemDecoration(new RecyclerViewDivider(MyIndentFragment.this.getActivity(), 1, 0, ContextCompat.getColor(MyIndentFragment.this.getActivity(), 2131099799)));
                            MyIndentFragment.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                            MyIndentFragment.this.recyclerView.setAdapter(new BaseRecyclerAdapter<IndentBean>(MyIndentFragment.this.items, 2131427477, (AdapterView.OnItemClickListener) null) {
                                /* access modifiers changed from: protected */
                                public void onBindViewHolder(SmartViewHolder smartViewHolder, IndentBean indentBean, int i) {
                                    smartViewHolder.text(2131297468, (CharSequence) indentBean.getOid());
                                    smartViewHolder.text(2131297463, (CharSequence) indentBean.getCreateTime());
                                    String member = indentBean.getMember();
                                    if ("1".equals(member) || "1.0".equals(member)) {
                                        smartViewHolder.text(2131297465, (CharSequence) "月度会员（30天）");
                                        smartViewHolder.text(2131296777, (CharSequence) "月");
                                    } else if ("2".equals(member) || SocializeConstants.PROTOCOL_VERSON.equals(member)) {
                                        smartViewHolder.text(2131297465, (CharSequence) "年度会员（365天）");
                                        smartViewHolder.text(2131296777, (CharSequence) "年");
                                    } else if ("3".equals(member) || "3.0".equals(member)) {
                                        smartViewHolder.text(2131297465, (CharSequence) "三年会员（1095天）");
                                        smartViewHolder.text(2131296777, (CharSequence) "三");
                                    } else if ("4".equals(member) || "4.0".equals(member)) {
                                        smartViewHolder.text(2131297465, (CharSequence) "半年会员（180天）");
                                        smartViewHolder.text(2131296777, (CharSequence) "半");
                                    } else if ("5".equals(member) || "5.0".equals(member)) {
                                        smartViewHolder.text(2131297465, (CharSequence) "年度会员（365天）");
                                        smartViewHolder.text(2131296777, (CharSequence) "年");
                                    } else if ("10".equals(member) || "10.0".equals(member)) {
                                        smartViewHolder.text(2131297465, (CharSequence) "永久会员");
                                        smartViewHolder.text(2131296777, (CharSequence) "永");
                                    } else if ("11".equals(member) || "11.0".equals(member)) {
                                        smartViewHolder.text(2131297465, (CharSequence) "永久会员");
                                        smartViewHolder.text(2131296777, (CharSequence) "永");
                                    } else {
                                        smartViewHolder.text(2131297465, (CharSequence) "体验会员");
                                        smartViewHolder.text(2131296777, (CharSequence) "体");
                                    }
                                    smartViewHolder.text(2131297466, (CharSequence) "￥" + indentBean.getPrice());
                                    smartViewHolder.text(2131297467, (CharSequence) "￥" + indentBean.getPrice());
                                    String state = indentBean.getState();
                                    if ("0".equals(state) || "0.0".equals(state)) {
                                        smartViewHolder.text(2131297469, (CharSequence) "未支付");
                                        smartViewHolder.textColorId(2131297469, R.color.main_color);
                                    } else if ("1".equals(state) || "1.0".equals(state)) {
                                        smartViewHolder.text(2131297469, (CharSequence) "已支付");
                                        smartViewHolder.textColorId(2131297469, 2131099740);
                                    }
                                }
                            });
                            return;
                        }
                        ToastUtils.showToast(MyIndentFragment.this.getActivity(), conmdBean.getMessage());
                    } catch (Exception e) {
                        LogUtils.log("WS_BABY_Catch_42");
                    }
                }
            }
        });
    }

    public void initViewListener() {
        if (this.refreshLayout != null) {
            this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
                public void onRefresh(RefreshLayout refreshLayout) {
                    MyIndentFragment.this.initData(false, 1);
                }
            });
            this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
                public void onLoadMore(RefreshLayout refreshLayout) {
                    int unused = MyIndentFragment.this.page = MyIndentFragment.this.page + 1;
                    MyIndentFragment.this.initData(true, MyIndentFragment.this.page);
                }
            });
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427536, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        initData(false, 1);
        initViewListener();
        return inflate;
    }

    public void onDestroyView() {
        MyIndentFragment.super.onDestroyView();
        this.unbinder.unbind();
    }
}
