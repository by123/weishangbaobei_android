package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.CircleSettingEvent;
import com.wx.assistants.bean.WxGroupEvent;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyCircleSettingActivity extends BaseActivity {
    @BindView(2131296338)
    RelativeLayout allLayout;
    @BindView(2131296399)
    LinearLayout bottomLayout;
    @BindView(2131296723)
    LinearLayout fromGroupSelect;
    @BindView(2131296724)
    LinearLayout fromTagSelect;
    @BindView(2131296775)
    ImageView imgAll;
    @BindView(2131296778)
    ImageView imgPart;
    @BindView(2131296779)
    ImageView imgSelf;
    @BindView(2131296780)
    ImageView imgShield;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297115)
    RelativeLayout partLayout;
    @BindView(2131297309)
    TextView selectGroupTv;
    private String selectGroups = "";
    private String selectModel = "公开";
    @BindView(2131297318)
    TextView selectTagTv;
    private String selectTags = "";
    @BindView(2131297326)
    RelativeLayout selfLayout;
    @BindView(2131297328)
    TextView sendAllSmallTV;
    @BindView(2131297329)
    TextView sendAllTV;
    @BindView(2131297337)
    TextView sendPartSmallTV;
    @BindView(2131297338)
    TextView sendPartTV;
    @BindView(2131297339)
    TextView sendSelfSmallTV;
    @BindView(2131297340)
    TextView sendShelfTV;
    @BindView(2131297341)
    TextView sendShieldSmallTV;
    @BindView(2131297342)
    TextView sendShieldTV;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;
    @BindView(2131297370)
    RelativeLayout shieldLayout;
    @BindView(2131297425)
    Button startWx;

    static {
        StubApp.interface11(6746);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    private void initView() {
        this.navTitle.setText("谁可以看");
        this.startWx.setText("我选好了");
        this.selectModel = getIntent().getStringExtra("selectModel");
        this.selectTags = getIntent().getStringExtra("selectTags");
        this.selectGroups = getIntent().getStringExtra("selectGroups");
        setSelectSendModel(this.selectModel);
        if (this.selectTags == null || "".equals(this.selectTags)) {
            this.selectTagTv.setText("");
            this.selectTags = "";
        } else {
            this.selectTagTv.setText(this.selectTags);
        }
        if (this.selectGroups == null || "".equals(this.selectGroups)) {
            this.selectGroupTv.setText("");
            this.selectGroups = "";
            return;
        }
        this.selectGroupTv.setText(this.selectGroups);
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.selectTagTv.setText("");
            this.selectTags = "";
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < selectedTagList.size(); i++) {
            sb.append(selectedTagList.get(i) + ";");
        }
        this.selectTagTv.setText(sb.toString());
        this.selectTags = sb.toString();
    }

    @Subscribe
    public void onEventMainThread(WxGroupEvent wxGroupEvent) {
        LinkedHashSet<String> selectedGroupList = wxGroupEvent.getSelectedGroupList();
        if (selectedGroupList == null || selectedGroupList.size() <= 0) {
            this.selectGroupTv.setText("");
            this.selectGroups = "";
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = selectedGroupList.iterator();
        while (it.hasNext()) {
            sb.append(((String) it.next()) + ";");
        }
        String sb2 = sb.toString();
        this.selectGroupTv.setText(sb2);
        this.selectGroups = sb2;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyCircleSettingActivity] */
    @OnClick({2131297049, 2131297425, 2131296724, 2131296723, 2131296338, 2131297326, 2131297115, 2131297370})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296338:
                setSelectSendModel("公开");
                return;
            case 2131296723:
                Intent intent = new Intent(this, ObtainGroupActivity.class);
                intent.putExtra("selects", this.selectGroupTv.getText().toString());
                startActivity(intent);
                return;
            case 2131296724:
                Intent intent2 = new Intent(this, ObtainTagActivity.class);
                intent2.putExtra("selects", this.selectTagTv.getText().toString());
                startActivity(intent2);
                return;
            case 2131297049:
                back();
                return;
            case 2131297115:
                setSelectSendModel("部分可见");
                return;
            case 2131297326:
                setSelectSendModel("私密");
                return;
            case 2131297370:
                setSelectSendModel("不给谁看");
                return;
            case 2131297425:
                if ("公开".equals(this.selectModel)) {
                    this.selectModel = "公开";
                    this.selectTags = "";
                    this.selectGroups = "";
                } else if ("私密".equals(this.selectModel)) {
                    this.selectModel = "私密";
                    this.selectTags = "";
                    this.selectGroups = "";
                } else if ("部分可见".equals(this.selectModel)) {
                    this.selectModel = "部分可见";
                    if ("".equals(this.selectTags) && "".equals(this.selectGroups)) {
                        ToastUtils.showToast(this, "请选择标签或群");
                        return;
                    }
                } else if ("不给谁看".equals(this.selectModel)) {
                    this.selectModel = "不给谁看";
                    if ("".equals(this.selectTags) && "".equals(this.selectGroups)) {
                        ToastUtils.showToast(this, "请选择标签或群");
                        return;
                    }
                }
                EventBus.getDefault().post(new CircleSettingEvent(this.selectModel, this.selectTags, this.selectGroups));
                back();
                return;
            default:
                return;
        }
    }

    public void setSelectSendModel(String str) {
        if ("公开".equals(str)) {
            this.selectModel = "公开";
            this.imgAll.setBackground(getResources().getDrawable(2131231280));
            this.imgSelf.setBackground(getResources().getDrawable(2131231279));
            this.imgPart.setBackground(getResources().getDrawable(2131231279));
            this.imgShield.setBackground(getResources().getDrawable(2131231279));
            this.bottomLayout.setVisibility(8);
        } else if ("私密".equals(str)) {
            this.selectModel = "私密";
            this.imgAll.setBackground(getResources().getDrawable(2131231279));
            this.imgSelf.setBackground(getResources().getDrawable(2131231280));
            this.imgPart.setBackground(getResources().getDrawable(2131231279));
            this.imgShield.setBackground(getResources().getDrawable(2131231279));
            this.bottomLayout.setVisibility(8);
        } else if ("部分可见".equals(str)) {
            this.selectModel = "部分可见";
            this.imgAll.setBackground(getResources().getDrawable(2131231279));
            this.imgSelf.setBackground(getResources().getDrawable(2131231279));
            this.imgPart.setBackground(getResources().getDrawable(2131231280));
            this.imgShield.setBackground(getResources().getDrawable(2131231279));
            this.bottomLayout.setVisibility(0);
        } else if ("不给谁看".equals(str)) {
            this.selectModel = "不给谁看";
            this.imgAll.setBackground(getResources().getDrawable(2131231279));
            this.imgSelf.setBackground(getResources().getDrawable(2131231279));
            this.imgPart.setBackground(getResources().getDrawable(2131231279));
            this.imgShield.setBackground(getResources().getDrawable(2131231280));
            this.bottomLayout.setVisibility(0);
        }
    }
}
