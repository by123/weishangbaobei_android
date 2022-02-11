package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.sina.params.ShareRequestParam;
import com.wx.assistants.adapter.PassCardAdapter;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdListBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.PassiveCardBean;
import com.wx.assistants.bean.PassiveCardEvent;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class PassiveActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public PassCardAdapter adapter;
    /* access modifiers changed from: private */
    public List<PassiveCardBean> beans = new ArrayList();
    @BindView(2131296523)
    RecyclerView commonRV;
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
    @BindView(2131297079)
    TextView numText;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6757);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.PassiveActivity] */
    private void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("坐等被加");
        this.startWx.setText("添加被加号");
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                PassiveActivity.this.initData();
            }
        });
        this.beans = new ArrayList();
        this.adapter = new PassCardAdapter(this.beans, this);
        this.commonRV.setAdapter(this.adapter);
        this.commonRV.setLayoutManager(new LinearLayoutManager(this));
        this.commonRV.setNestedScrollingEnabled(false);
        initData();
    }

    public void initData() {
        ApiWrapper.getInstance().getMyCardList(1, SocializeConstants.CANCLE_RESULTCODE, new ApiWrapper.CallbackListener<ConmdListBean>() {
            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.PassiveActivity] */
            public void onFailure(FailureModel failureModel) {
                if (PassiveActivity.this.refreshLayout != null) {
                    PassiveActivity.this.refreshLayout.finishRefresh(true);
                }
                ? r0 = PassiveActivity.this;
                ToastUtils.showToast(r0, failureModel.getCode() + ":" + failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.PassiveActivity] */
            /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.PassiveActivity] */
            public void onFinish(ConmdListBean conmdListBean) {
                if (PassiveActivity.this.refreshLayout != null) {
                    PassiveActivity.this.refreshLayout.finishRefresh(true);
                }
                if (conmdListBean == null || conmdListBean.getCode() != 0) {
                    ? r0 = PassiveActivity.this;
                    ToastUtils.showToast(r0, conmdListBean.getCode() + ":" + conmdListBean.getMessage());
                    return;
                }
                try {
                    Gson gson = new Gson();
                    ArrayList arrayList = (ArrayList) gson.fromJson(gson.toJson(conmdListBean.getData().getList()), new TypeToken<ArrayList<PassiveCardBean>>() {
                    }.getType());
                    List unused = PassiveActivity.this.beans = arrayList;
                    if (PassiveActivity.this.adapter == null) {
                        PassCardAdapter unused2 = PassiveActivity.this.adapter = new PassCardAdapter(PassiveActivity.this.beans, PassiveActivity.this);
                        PassiveActivity.this.commonRV.setAdapter(PassiveActivity.this.adapter);
                        return;
                    }
                    PassiveActivity.this.adapter.updateData(arrayList);
                    PassiveActivity.this.adapter.notifyDataSetChanged();
                } catch (Exception e) {
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(PassiveCardEvent passiveCardEvent) {
        initData();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.PassiveActivity] */
    @OnClick({2131297425, 2131297049, 2131297636, 2131297052})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.添加的被加号，要确认能收到该好友。\n\n2.被加号可以是您绑定微信的手机号、微信号、QQ号\n\n3.大量被加需关闭好友验证：「设置-隐私-加我为朋友时需要验证」关闭该项。\n\n4.开启被加后，将会有大量的好友加你，加你的好友会出现在消息列表页，只需通过即可。\n");
        } else if (id == 2131297425) {
            Intent intent = new Intent(this, PassiveAddCardActivity.class);
            intent.putExtra("id", -1);
            intent.putExtra(ShareRequestParam.RESP_UPLOAD_PIC_PARAM_CODE, "");
            startActivity(intent);
        } else if (id == 2131297636) {
            WebViewActivity.startActivity(this, "坐等被加", QBangApis.VIDEO_PASSIVE_CARD);
        }
    }
}
