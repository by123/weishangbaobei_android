package com.wx.assistants.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.wx.assistants.server.request.CheckCodeRequest;
import com.wx.assistants.server.request.UserActivationCodeRequest;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.RecyclerViewDivider;
import java.util.ArrayList;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class ActivationActivity extends BaseActivity {
    @BindView(2131296312)
    EditText activateEd;
    /* access modifiers changed from: private */
    public ArrayList<ActivationCodeBean> items = new ArrayList<>();
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    private int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297349)
    TextView setExpireTime;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297426)
    Button startWx2;
    @BindView(2131297531)
    LinearLayout topLayout;

    static {
        StubApp.interface11(8224);
    }

    private void initView() {
        this.navTitle.setText("会员卡激活");
        this.startWx.setText("激活");
    }

    public void activationDevice(String str) {
        String trim = this.activateEd.getText().toString().trim();
        String macAddress = MacAddressUtils.getMacAddress(MyApplication.mContext);
        showLoadingDialog("正在激活");
        ApiWrapper.getInstance().userActivationCode(new UserActivationCodeRequest(str, trim, macAddress), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            public void onFailure(FailureModel failureModel) {
                ActivationActivity.this.dismissLoadingDialog();
                ToastUtils.showToast(ActivationActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            public void onFinish(ConmdBean conmdBean) {
                ActivationActivity.this.dismissLoadingDialog();
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0) {
                            ToastUtils.showToast(ActivationActivity.this, "激活成功");
                            ActivationActivity.this.startActivity(new Intent(ActivationActivity.this, MainActivity.class));
                            ActivationActivity.this.finish();
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showToast(ActivationActivity.this, "激活失败");
                        return;
                    }
                }
                ToastUtils.showToast(ActivationActivity.this, conmdBean.getMessage());
            }
        });
    }

    public void checkCode() {
        String trim = this.activateEd.getText().toString().trim();
        showLoadingDialog("正在查询");
        ApiWrapper.getInstance().checkCode(new CheckCodeRequest(trim), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            public void onFailure(FailureModel failureModel) {
                ActivationActivity.this.dismissLoadingDialog();
                ToastUtils.showToast(ActivationActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v8, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v9, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v10, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v11, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v12, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r0v13, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            public void onFinish(ConmdBean conmdBean) {
                ActivationActivity.this.dismissLoadingDialog();
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 1) {
                            ? r0 = ActivationActivity.this;
                            ToastUtils.showToast(r0, conmdBean.getMessage() + "-激活码未使用".toString());
                        } else if (conmdBean.getCode() == 2) {
                            ? r02 = ActivationActivity.this;
                            ToastUtils.showToast(r02, conmdBean.getMessage() + "-激活码未使用".toString());
                        } else if (conmdBean.getCode() == 3) {
                            ? r03 = ActivationActivity.this;
                            ToastUtils.showToast(r03, conmdBean.getMessage() + "-激活码已使用".toString());
                        } else if (conmdBean.getCode() == 4) {
                            ? r04 = ActivationActivity.this;
                            ToastUtils.showToast(r04, conmdBean.getMessage() + "-激活码已过期".toString());
                        } else if (conmdBean.getCode() == 5) {
                            ToastUtils.showToast(ActivationActivity.this, "激活码不存在！");
                        } else {
                            ? r05 = ActivationActivity.this;
                            ToastUtils.showToast(r05, conmdBean.getCode() + "" + conmdBean.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showToast(ActivationActivity.this, conmdBean.getMessage());
                    }
                }
            }
        });
    }

    public void initData() {
        initOneDayActivationCodeData();
        initViewListener();
    }

    public void initOneDayActivationCodeData() {
        ApiWrapper.getInstance().myActivationCode(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            public void onFailure(FailureModel failureModel) {
                if (ActivationActivity.this.refreshLayout != null) {
                    ActivationActivity.this.refreshLayout.finishRefresh();
                }
                if (failureModel != null) {
                    ToastUtils.showToast(ActivationActivity.this, failureModel.getErrorMessage());
                }
            }

            /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r2v2, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r2v3, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
            public void onFinish(ConmdBean conmdBean) {
                if (ActivationActivity.this.refreshLayout != null) {
                    ActivationActivity.this.refreshLayout.finishRefresh();
                }
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0) {
                            Gson gson = new Gson();
                            ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(conmdBean.getData()), new TypeToken<ArrayList<ActivationCodeBean>>() {
                            }.getType());
                            if (arrayList == null || arrayList.size() <= 0) {
                                ActivationActivity.this.refreshLayout.setEnableLoadMore(false);
                                return;
                            }
                            ActivationActivity.this.items.clear();
                            ArrayList unused = ActivationActivity.this.items = arrayList;
                            ActivationActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(ActivationActivity.this));
                            ActivationActivity.this.recyclerView.addItemDecoration(new RecyclerViewDivider(ActivationActivity.this, 1, 0, ContextCompat.getColor(ActivationActivity.this, 2131099799)));
                            ActivationActivity.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                            ActivationActivity.this.recyclerView.setAdapter(new BaseRecyclerAdapter<ActivationCodeBean>(ActivationActivity.this.items, 2131427479, (AdapterView.OnItemClickListener) null) {
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
                                        /* JADX WARNING: type inference failed for: r0v7, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
                                        public void onClick(View view) {
                                            ((ClipboardManager) ActivationActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence) null, activationCodeBean.getActivationCode()));
                                            ToastUtils.showToast(ActivationActivity.this, "复制成功");
                                        }
                                    });
                                }
                            });
                            return;
                        }
                        ToastUtils.showToast(ActivationActivity.this, conmdBean.getMessage());
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    public void initViewListener() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                ActivationActivity.this.initOneDayActivationCodeData();
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.ActivationActivity] */
    @OnClick({2131297425, 2131297049, 2131297426})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != 2131297049) {
            switch (id) {
                case 2131297425:
                    String trim = this.activateEd.getText().toString().trim();
                    if (trim == null || "".equals(trim)) {
                        ToastUtils.showToast(this, "请填写激活码");
                        return;
                    }
                    String str = (String) SPUtils.get(this, "ws_baby_phone", "");
                    if (!"".equals(str)) {
                        activationDevice(str);
                        return;
                    }
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("isNeedBack", true);
                    startActivity(intent);
                    return;
                case 2131297426:
                    String trim2 = this.activateEd.getText().toString().trim();
                    if (trim2 == null || "".equals(trim2)) {
                        ToastUtils.showToast(this, "请输入激活码");
                        return;
                    } else if (!"".equals((String) SPUtils.get(this, "ws_baby_phone", ""))) {
                        checkCode();
                        return;
                    } else {
                        Intent intent2 = new Intent(this, LoginActivity.class);
                        intent2.putExtra("isNeedBack", true);
                        startActivity(intent2);
                        return;
                    }
                default:
                    return;
            }
        } else {
            back();
        }
    }
}
