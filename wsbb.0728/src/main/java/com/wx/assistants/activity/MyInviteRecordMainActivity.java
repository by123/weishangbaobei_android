package com.wx.assistants.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.athbk.ultimatetablayout.OnClickTabListener;
import com.athbk.ultimatetablayout.UltimateTabLayout;
import com.google.gson.Gson;
import com.stub.StubApp;
import com.wx.assistants.adapter.MyInviteRecordMainAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.bean.UserInfoBean;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.SPUtils;
import java.util.ArrayList;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class MyInviteRecordMainActivity extends BaseActivity {
    private MyInviteRecordMainAdapter adapter;
    @BindView(2131296731)
    LinearLayout graphicExplanationLayout;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297452)
    UltimateTabLayout tabLayout;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;
    @BindView(2131297639)
    ViewPager viewPager;

    static {
        StubApp.interface11(9054);
    }

    public void defaultSetting() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("直推用户");
        arrayList.add("间推用户");
        this.adapter = new MyInviteRecordMainAdapter(getSupportFragmentManager(), arrayList);
        this.viewPager.setAdapter(this.adapter);
        this.tabLayout.setOnClickTabListener(new OnClickTabListener() {
            public void onClickTab(int i) {
                MyInviteRecordMainActivity.this.viewPager.setCurrentItem(i);
                if (i == 1) {
                    MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 0);
                } else {
                    MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 1);
                }
            }
        });
        this.tabLayout.setViewPager(this.viewPager, this.adapter);
        this.viewPager.setOffscreenPageLimit(1);
    }

    public void getUser() {
        ApiWrapper.getInstance().getUser(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper.CallbackListener<ConmdBean>() {
            public void onFailure(FailureModel failureModel) {
                MyInviteRecordMainActivity.this.defaultSetting();
            }

            public void onFinish(ConmdBean conmdBean) {
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() != 1 || conmdBean.getMessage() == null) {
                            if (conmdBean.getCode() == 0) {
                                String json = new Gson().toJson(conmdBean.getData());
                                try {
                                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_info", json);
                                } catch (Exception e) {
                                }
                                MyInviteRecordMainActivity.this.setUserInfo(json);
                            } else if (conmdBean.getCode() == 1) {
                                MyInviteRecordMainActivity.this.defaultSetting();
                            }
                        } else if (conmdBean.getMessage().contains("请重新登录")) {
                            MyInviteRecordMainActivity.this.defaultSetting();
                        }
                    } catch (Exception e2) {
                        MyInviteRecordMainActivity.this.defaultSetting();
                    }
                }
            }
        });
    }

    public void initView() {
        this.navTitle.setText("邀请记录");
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getUser();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.MyInviteRecordMainActivity] */
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            String str = (String) SPUtils.get(this, "ws_baby_user_info", "");
            if (str != null && !"".equals(str)) {
                setUserInfo(str);
            }
        } catch (Exception e) {
        }
    }

    @OnClick({2131297049})
    public void onViewClicked(View view) {
        if (view.getId() == 2131297049) {
            back();
        }
    }

    public void setUserInfo(String str) {
        UserInfoBean.UserBean userBean = (UserInfoBean.UserBean) new Gson().fromJson(str, UserInfoBean.UserBean.class);
        if (userBean != null) {
            try {
                String userId = userBean.getUserId();
                if (userId != null && !"".equals(userId)) {
                    SPUtils.put(MyApplication.getConText(), "ws_baby_user_id", userId);
                }
            } catch (Exception e) {
            }
            String str2 = userBean.getInviteNum() + "";
            ArrayList arrayList = new ArrayList();
            arrayList.add("直推用户(" + str2 + ")");
            arrayList.add("间推用户(" + (userBean.getInviteTwoNum() + "") + ")");
            this.adapter = new MyInviteRecordMainAdapter(getSupportFragmentManager(), arrayList);
            this.viewPager.setAdapter(this.adapter);
            this.tabLayout.setOnClickTabListener(new OnClickTabListener() {
                public void onClickTab(int i) {
                    MyInviteRecordMainActivity.this.viewPager.setCurrentItem(i);
                    if (i == 1) {
                        MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 0);
                    } else {
                        MyInviteRecordMainActivity.this.tabLayout.setNumberBadge(i, 1);
                    }
                }
            });
            this.tabLayout.setViewPager(this.viewPager, this.adapter);
            this.viewPager.setOffscreenPageLimit(1);
        }
    }
}
