package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.wx.assistants.adapter.MaterialNormalAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.MaterialBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;

public class OneKeyForwardMaterialActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public ArrayList<MaterialBean> items = new ArrayList<>();
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public MaterialNormalAdapter normalAdapter;
    /* access modifiers changed from: private */
    public int page = 1;
    @BindView(2131297225)
    RecyclerView recyclerView;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297287)
    TextView scrollTextView;

    static {
        StubApp.interface11(6751);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static /* synthetic */ int access$210(OneKeyForwardMaterialActivity oneKeyForwardMaterialActivity) {
        int i = oneKeyForwardMaterialActivity.page;
        oneKeyForwardMaterialActivity.page = i - 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.OneKeyForwardMaterialActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        PermissionUtils.checkReadAndWriteExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
            }
        });
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardMaterialActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardMaterialActivity] */
    public void initViewData(final boolean z, int i) {
        showLoadingDialog("正在加载素材");
        this.page = i;
        ApiWrapper.getInstance().materialList(i, 5, MacAddressUtils.getMacAddress(MyApplication.mContext), (String) SPUtils.get(this, "user_token", ""), new ApiWrapper.CallbackListener<ConmdListBean>() {
            /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardMaterialActivity] */
            public void onFinish(ConmdListBean conmdListBean) {
                OneKeyForwardMaterialActivity.this.dismissLoadingDialog();
                if (OneKeyForwardMaterialActivity.this.refreshLayout != null) {
                    OneKeyForwardMaterialActivity.this.refreshLayout.finishRefresh(true);
                    OneKeyForwardMaterialActivity.this.refreshLayout.finishLoadMore(true);
                    OneKeyForwardMaterialActivity.this.refreshLayout.setEnableLoadMore(true);
                }
                if (conmdListBean != null) {
                    try {
                        if (conmdListBean.getCode() == 0) {
                            ConmdListBean.LimitPageBean data = conmdListBean.getData();
                            if (data.getSize() > 0) {
                                if (data.getSize() < data.getPageSize() && OneKeyForwardMaterialActivity.this.refreshLayout != null) {
                                    OneKeyForwardMaterialActivity.this.refreshLayout.setEnableLoadMore(false);
                                }
                                Gson gson = new Gson();
                                ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(data.getList()), new TypeToken<ArrayList<MaterialBean>>() {
                                }.getType());
                                if (z) {
                                    OneKeyForwardMaterialActivity.this.items.addAll(arrayList);
                                } else {
                                    OneKeyForwardMaterialActivity.this.items.clear();
                                    ArrayList unused = OneKeyForwardMaterialActivity.this.items = arrayList;
                                }
                                OneKeyForwardMaterialActivity.this.normalAdapter.setData(OneKeyForwardMaterialActivity.this.items);
                                OneKeyForwardMaterialActivity.this.normalAdapter.notifyDataSetChanged();
                            } else if (OneKeyForwardMaterialActivity.this.refreshLayout != null) {
                                OneKeyForwardMaterialActivity.this.refreshLayout.setEnableLoadMore(false);
                            }
                        } else {
                            ToastUtils.showToast(OneKeyForwardMaterialActivity.this, conmdListBean.getMessage());
                        }
                    } catch (Exception unused2) {
                        if (z) {
                            OneKeyForwardMaterialActivity.access$210(OneKeyForwardMaterialActivity.this);
                        }
                    }
                }
            }

            /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.OneKeyForwardMaterialActivity] */
            public void onFailure(FailureModel failureModel) {
                OneKeyForwardMaterialActivity.this.dismissLoadingDialog();
                if (z) {
                    OneKeyForwardMaterialActivity.access$210(OneKeyForwardMaterialActivity.this);
                }
                if (OneKeyForwardMaterialActivity.this.refreshLayout != null) {
                    OneKeyForwardMaterialActivity.this.refreshLayout.finishRefresh();
                    OneKeyForwardMaterialActivity.this.refreshLayout.finishLoadMore(false);
                }
                if (failureModel != null) {
                    ToastUtils.showToast(OneKeyForwardMaterialActivity.this, failureModel.getErrorMessage());
                }
            }
        });
    }

    public void initViewListener() {
        if (this.refreshLayout != null) {
            this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
                public void onRefresh(RefreshLayout refreshLayout) {
                    OneKeyForwardMaterialActivity.this.initViewData(false, 1);
                }
            });
            this.refreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
                public void onLoadMore(RefreshLayout refreshLayout) {
                    int unused = OneKeyForwardMaterialActivity.this.page = OneKeyForwardMaterialActivity.this.page + 1;
                    OneKeyForwardMaterialActivity.this.initViewData(true, OneKeyForwardMaterialActivity.this.page);
                }
            });
        }
    }

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
