package com.wx.assistants.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
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
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stub.StubApp;
import com.wx.assistants.adapter.BaseRecyclerAdapter;
import com.wx.assistants.adapter.SmartViewHolder;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ActivationCodeBean;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;

public class OneDayActivationCodeActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public ArrayList<ActivationCodeBean> items = new ArrayList<>();
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    private int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297531)
    LinearLayout topLayout;

    static {
        StubApp.interface11(9146);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    public void initData() {
        this.navTitle.setText("领取一天激活码");
        initOneDayActivationCodeData();
        initViewListener();
    }

    public void initOneDayActivationCodeData() {
        ApiWrapper.getInstance().myActivationCode(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.OneDayActivationCodeActivity] */
            /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneDayActivationCodeActivity] */
            /* JADX WARNING: type inference failed for: r2v1, types: [android.content.Context, com.wx.assistants.activity.OneDayActivationCodeActivity] */
            /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.OneDayActivationCodeActivity] */
            public void onFinish(ConmdBean conmdBean) {
                if (OneDayActivationCodeActivity.this.refreshLayout != null) {
                    OneDayActivationCodeActivity.this.refreshLayout.finishRefresh();
                }
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0) {
                            Gson gson = new Gson();
                            ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(conmdBean.getData()), new TypeToken<ArrayList<ActivationCodeBean>>() {
                            }.getType());
                            if (arrayList == null || arrayList.size() <= 0) {
                                OneDayActivationCodeActivity.this.refreshLayout.setEnableLoadMore(false);
                                return;
                            }
                            OneDayActivationCodeActivity.this.items.clear();
                            ArrayList unused = OneDayActivationCodeActivity.this.items = arrayList;
                            OneDayActivationCodeActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(OneDayActivationCodeActivity.this));
                            OneDayActivationCodeActivity.this.recyclerView.addItemDecoration(new RecyclerViewDivider(OneDayActivationCodeActivity.this, 1, 0, ContextCompat.getColor(OneDayActivationCodeActivity.this, 2131099799)));
                            OneDayActivationCodeActivity.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                            OneDayActivationCodeActivity.this.recyclerView.setAdapter(new BaseRecyclerAdapter<ActivationCodeBean>(OneDayActivationCodeActivity.this.items, 2131427479, (AdapterView.OnItemClickListener) null) {
                                /* access modifiers changed from: protected */
                                public void onBindViewHolder(SmartViewHolder smartViewHolder, final ActivationCodeBean activationCodeBean, int i) {
                                    smartViewHolder.text(2131296315, (CharSequence) activationCodeBean.getActivationCode());
                                    if (activationCodeBean.getState() != 0) {
                                        smartViewHolder.text(2131296314, (CharSequence) "已使用");
                                        smartViewHolder.enable(2131296313, false);
                                    } else {
                                        smartViewHolder.text(2131296314, (CharSequence) "未使用");
                                        smartViewHolder.enable(2131296313, true);
                                    }
                                    smartViewHolder.click(2131296313, new View.OnClickListener() {
                                        /* JADX WARNING: type inference failed for: r3v8, types: [android.content.Context, com.wx.assistants.activity.OneDayActivationCodeActivity] */
                                        public void onClick(View view) {
                                            ((ClipboardManager) OneDayActivationCodeActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, activationCodeBean.getActivationCode()));
                                            ToastUtils.showToast(OneDayActivationCodeActivity.this, "复制成功");
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        ToastUtils.showToast(OneDayActivationCodeActivity.this, conmdBean.getMessage());
                    } catch (Exception unused2) {
                        LogUtils.log("WS_BABY_Catch_33");
                    }
                }
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.OneDayActivationCodeActivity] */
            public void onFailure(FailureModel failureModel) {
                if (OneDayActivationCodeActivity.this.refreshLayout != null) {
                    OneDayActivationCodeActivity.this.refreshLayout.finishRefresh();
                }
                if (failureModel != null) {
                    ToastUtils.showToast(OneDayActivationCodeActivity.this, failureModel.getErrorMessage());
                }
            }
        });
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                OneDayActivationCodeActivity.this.initOneDayActivationCodeData();
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
