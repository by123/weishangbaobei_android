package com.wx.assistants.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.adapter.MaterialNormalAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseFragment;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.MaterialBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.ClipboardUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;

public class MaterialForwardFragment extends BaseFragment {
    TextView inviteSelfUrl;
    private boolean isNeedBar = false;
    /* access modifiers changed from: private */
    public ArrayList<MaterialBean> items = new ArrayList<>();
    private View mCacheView;
    LinearLayout navLayout;
    /* access modifiers changed from: private */
    public MaterialNormalAdapter normalAdapter;
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    TextView scrollTextView;
    ImageView setUserHead;
    TextView userPhone;

    static /* synthetic */ int access$210(MaterialForwardFragment materialForwardFragment) {
        int i = materialForwardFragment.page;
        materialForwardFragment.page = i - 1;
        return i;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (this.mCacheView == null) {
            this.mCacheView = layoutInflater.inflate(2131427527, (ViewGroup) null);
        }
        View inflate = LayoutInflater.from(getActivity()).inflate(2131427580, (ViewGroup) null, false);
        this.navLayout = (LinearLayout) inflate.findViewById(2131297050);
        this.scrollTextView = (TextView) inflate.findViewById(2131297287);
        this.inviteSelfUrl = (TextView) inflate.findViewById(2131296824);
        this.userPhone = (TextView) inflate.findViewById(2131297621);
        this.setUserHead = (ImageView) inflate.findViewById(2131297353);
        ViewGroup viewGroup2 = (ViewGroup) this.mCacheView.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCacheView);
        }
        ButterKnife.bind(this, this.mCacheView);
        initViewListener();
        try {
            this.isNeedBar = getArguments().getBoolean("isDeleteNavBar");
            if (this.isNeedBar) {
                this.navLayout.setVisibility(8);
            } else {
                this.navLayout.setVisibility(0);
            }
        } catch (Exception unused) {
            LogUtils.log("WS_BABY_Catch_50");
        }
        String str = (String) SPUtils.get(getContext(), "ws_baby_phone", "");
        if (str == null || "".equals(str)) {
            this.userPhone.setText("未知用户");
        } else {
            this.userPhone.setText(str);
        }
        TextView textView = this.inviteSelfUrl;
        textView.setText("专属链接：" + MyApplication.inviteUrlStr);
        this.inviteSelfUrl.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (MyApplication.inviteUrlStr == null || "".equals(MyApplication.inviteUrlStr)) {
                    return false;
                }
                ClipboardUtils.setClipboardText(MaterialForwardFragment.this.getActivity(), MyApplication.inviteUrlStr);
                return false;
            }
        });
        String str2 = (String) SPUtils.get(getActivity(), "ws_baby_user_info", "");
        if (str2 == null || "".equals(str2)) {
            this.setUserHead.setBackgroundResource(2131231064);
        } else {
            UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str2, UserInfoBean.UserBean.class);
            if (userBean != null) {
                String memberStatus = userBean.getMemberStatus();
                if ("1".equals(memberStatus) || "1.0".equals(memberStatus)) {
                    this.setUserHead.setBackgroundResource(2131231063);
                } else {
                    this.setUserHead.setBackgroundResource(2131231064);
                }
            }
        }
        this.recyclerView = this.mCacheView.findViewById(2131297225);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), 1, 0, ContextCompat.getColor(getActivity(), 2131099799)));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.normalAdapter = new MaterialNormalAdapter(getActivity(), this.items);
        this.normalAdapter.addHeaderView(inflate);
        this.recyclerView.setAdapter(this.normalAdapter);
        this.recyclerView.setItemViewCacheSize(100);
        this.normalAdapter.setOnStartWxListener(new MaterialNormalAdapter.OnStartWxListener() {
            public void materialPosition(int i) {
                if (MaterialForwardFragment.this.getActivity().isAccessibilitySettingsOn()) {
                    MaterialForwardFragment.this.getActivity().showLoadingDialog("素材保存中");
                    final MaterialBean materialBean = (MaterialBean) MaterialForwardFragment.this.items.get(i);
                    ClipboardUtils.setClipboardTextNoToast(MaterialForwardFragment.this.getActivity(), materialBean.getContent());
                    if (materialBean.getPicUrlJson() != null && !"".equals(materialBean.getPicUrlJson())) {
                        String[] strArr = (String[]) new Gson().fromJson(materialBean.getPicUrlJson(), new TypeToken<String[]>() {
                        }.getType());
                        final ArrayList arrayList = new ArrayList();
                        for (String str : strArr) {
                            arrayList.add(QBangApis.IMG_PREFIX_URL + str);
                        }
                        new FileUtil().saveSyn((Context) MaterialForwardFragment.this.getActivity(), (ArrayList<String>) arrayList, (FileUtil.OnSaveCompletedListener) new FileUtil.OnSaveCompletedListener() {
                            public void saveCompleted() {
                                MaterialForwardFragment.this.getActivity().dismissLoadingDialog();
                                ToastUtils.showToast(MaterialForwardFragment.this.getActivity(), "素材保存完成");
                                OperationParameterModel operationParameterModel = new OperationParameterModel();
                                operationParameterModel.setTaskNum("25");
                                operationParameterModel.setAutoSend(true);
                                operationParameterModel.setMaterialPicCount(arrayList.size());
                                operationParameterModel.setMaterialText(materialBean.getContent());
                                operationParameterModel.setMaterialForwardId(materialBean.getId() + "");
                                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                                MaterialForwardFragment.this.getActivity().runWx(operationParameterModel);
                            }
                        });
                        return;
                    }
                    return;
                }
                MaterialForwardFragment.this.getActivity().openAlertDialog();
            }

            public void materialImagesSave(int i) {
                MaterialForwardFragment.this.getActivity().showLoadingDialog("素材图片保存中");
                MaterialBean materialBean = (MaterialBean) MaterialForwardFragment.this.items.get(i);
                if (materialBean.getPicUrlJson() != null && !"".equals(materialBean.getPicUrlJson())) {
                    String[] strArr = (String[]) new Gson().fromJson(materialBean.getPicUrlJson(), new TypeToken<String[]>() {
                    }.getType());
                    ArrayList arrayList = new ArrayList();
                    for (String str : strArr) {
                        arrayList.add(QBangApis.IMG_PREFIX_URL + str);
                    }
                    new FileUtil().saveSyn((Context) MaterialForwardFragment.this.getActivity(), (ArrayList<String>) arrayList, (FileUtil.OnSaveCompletedListener) new FileUtil.OnSaveCompletedListener() {
                        public void saveCompleted() {
                            MaterialForwardFragment.this.getActivity().dismissLoadingDialog();
                            ToastUtils.showToast(MaterialForwardFragment.this.getActivity(), "素材图片保存完成");
                        }
                    });
                }
            }
        });
        if (getActivity().isLoginIng()) {
            initViewData(false, 1, true);
            initInviteSelfUrl();
        } else {
            getActivity().toastNilLogin();
        }
        return this.mCacheView;
    }

    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(getContext(), "ws_baby_phone", "");
            if (str == null || "".equals(str)) {
                this.userPhone.setText("未知用户");
            } else {
                this.userPhone.setText(str);
            }
            TextView textView = this.inviteSelfUrl;
            textView.setText("专属链接：" + MyApplication.inviteUrlStr);
        } catch (Exception unused) {
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        if (getActivity().isLoginIng()) {
            initViewData(false, 1, false);
            initInviteSelfUrl();
            return;
        }
        getActivity().toastNilLogin();
    }

    public void onResume() {
        super.onResume();
        if (getActivity().isLoginIng()) {
            initViewData(false, 1, false);
            initInviteSelfUrl();
        }
    }

    public void initViewData(final boolean z, int i, final boolean z2) {
        if (z2) {
            showLoadingDialog("正在加载素材");
        }
        this.page = i;
        ApiWrapper.getInstance().materialList(i, 5, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(getActivity(), "user_token", ""), new ApiWrapper.CallbackListener<ConmdListBean>() {
            public void onFinish(ConmdListBean conmdListBean) {
                if (z2) {
                    MaterialForwardFragment.this.dismissLoadingDialog();
                }
                if (MaterialForwardFragment.this.refreshLayout != null) {
                    MaterialForwardFragment.this.refreshLayout.finishRefresh(true);
                    MaterialForwardFragment.this.refreshLayout.finishLoadMore(true);
                    MaterialForwardFragment.this.refreshLayout.setEnableLoadMore(true);
                }
                if (conmdListBean != null) {
                    try {
                        if (conmdListBean.getCode() == 0) {
                            ConmdListBean.LimitPageBean data = conmdListBean.getData();
                            if (data.getSize() > 0) {
                                if (data.getSize() < data.getPageSize() && MaterialForwardFragment.this.refreshLayout != null) {
                                    MaterialForwardFragment.this.refreshLayout.setEnableLoadMore(false);
                                }
                                Gson gson = new Gson();
                                ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(data.getList()), new TypeToken<ArrayList<MaterialBean>>() {
                                }.getType());
                                if (z) {
                                    MaterialForwardFragment.this.items.addAll(arrayList);
                                } else {
                                    MaterialForwardFragment.this.items.clear();
                                    ArrayList unused = MaterialForwardFragment.this.items = arrayList;
                                }
                                MaterialForwardFragment.this.normalAdapter.setData(MaterialForwardFragment.this.items);
                                MaterialForwardFragment.this.normalAdapter.notifyDataSetChanged();
                            } else if (MaterialForwardFragment.this.refreshLayout != null) {
                                MaterialForwardFragment.this.refreshLayout.setEnableLoadMore(false);
                            }
                        } else {
                            ToastUtils.showToast(MaterialForwardFragment.this.getActivity(), conmdListBean.getMessage());
                        }
                    } catch (Exception unused2) {
                        LogUtils.log("WS_BABY_Catch_51");
                        if (z) {
                            MaterialForwardFragment.access$210(MaterialForwardFragment.this);
                        }
                    }
                }
            }

            public void onFailure(FailureModel failureModel) {
                if (z2) {
                    MaterialForwardFragment.this.dismissLoadingDialog();
                }
                if (z) {
                    MaterialForwardFragment.access$210(MaterialForwardFragment.this);
                }
                if (MaterialForwardFragment.this.refreshLayout != null) {
                    MaterialForwardFragment.this.refreshLayout.finishRefresh();
                    MaterialForwardFragment.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(MaterialForwardFragment.this.getActivity(), failureModel.getErrorMessage());
                }
            }
        });
    }

    public void initViewListener() {
        if (this.refreshLayout != null) {
            this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
                public void onRefresh(RefreshLayout refreshLayout) {
                    if (MaterialForwardFragment.this.getActivity().isLoginIng()) {
                        MaterialForwardFragment.this.initViewData(false, 1, false);
                        MaterialForwardFragment.this.initInviteSelfUrl();
                    } else if (MaterialForwardFragment.this.refreshLayout != null) {
                        MaterialForwardFragment.this.refreshLayout.finishRefresh(true);
                        MaterialForwardFragment.this.refreshLayout.finishLoadMore(true);
                        MaterialForwardFragment.this.refreshLayout.setEnableLoadMore(true);
                    }
                }
            });
            this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
                public void onLoadMore(RefreshLayout refreshLayout) {
                    int unused = MaterialForwardFragment.this.page = MaterialForwardFragment.this.page + 1;
                    MaterialForwardFragment.this.initViewData(true, MaterialForwardFragment.this.page, false);
                }
            });
        }
    }

    public void initInviteSelfUrl() {
        LogUtils.log("WS_BABY_InComeFragment.initInviteData");
        ApiWrapper.getInstance().getInviteData(new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
            }

            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        String str = (String) ((LinkedTreeMap) conmdBean.getData()).get("inviteUrl");
                        MyApplication.inviteUrlStr = str;
                        TextView textView = MaterialForwardFragment.this.inviteSelfUrl;
                        textView.setText("专属链接：" + str);
                    } catch (Exception unused) {
                        LogUtils.log("WS_BABY_Catch_34");
                    }
                }
            }
        });
    }
}
