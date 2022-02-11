package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxGroupEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.webview.WebViewActivity;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import worker8.com.github.radiogroupplus.RadioGroupPlus;

public class CleanGroupsActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int cleanGroupType = 0;
    /* access modifiers changed from: private */
    public int defaultNumber = SocializeConstants.CANCLE_RESULTCODE;
    /* access modifiers changed from: private */
    public String groupNames = "";
    @BindView(2131296873)
    TextView jumpLabel;
    @BindView(2131296877)
    LinearLayout jumpLabelLayout;
    private int maxNumber = 5000;
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
    @BindView(2131297177)
    RadioButton radioBtn01;
    @BindView(2131297178)
    RadioButton radioBtn02;
    @BindView(2131297179)
    RadioButton radioBtn03;
    @BindView(2131297180)
    RadioButton radioBtn04;
    @BindView(2131297181)
    RadioButton radioBtn05;
    @BindView(2131297184)
    RadioGroupPlus radioGroupPlus;
    @BindView(2131297185)
    LinearLayout radioLayout1;
    @BindView(2131297186)
    LinearLayout radioLayout2;
    @BindView(2131297187)
    LinearLayout radioLayout3;
    @BindView(2131297188)
    LinearLayout radioLayout4;
    @BindView(2131297189)
    LinearLayout radioLayout5;
    @BindView(2131297425)
    Button startWx;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6714);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.CleanGroupsActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void initView() {
        this.navRightImg.setVisibility(0);
        this.jumpLabelLayout.setVisibility(8);
        this.navTitle.setText("微信群清理");
        this.startWx.setText("开始清理");
        this.cleanGroupType = 0;
        this.jumpLabel.setText("设置要删除的群聊");
        this.radioGroupPlus.check(2131297177);
        this.radioGroupPlus.setOnCheckedChangeListener(new RadioGroupPlus.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroupPlus radioGroupPlus, @IdRes int i) {
                CleanGroupsActivity.this.onOrderClicked(radioGroupPlus);
            }
        });
    }

    public void onOrderClicked(RadioGroupPlus radioGroupPlus2) {
        if (2131297177 == radioGroupPlus2.getCheckedRadioButtonId()) {
            this.cleanGroupType = 0;
            this.jumpLabelLayout.setVisibility(8);
        } else if (2131297178 == radioGroupPlus2.getCheckedRadioButtonId()) {
            this.cleanGroupType = 1;
            this.jumpLabelLayout.setVisibility(8);
        } else if (2131297179 == radioGroupPlus2.getCheckedRadioButtonId()) {
            this.cleanGroupType = 2;
            this.jumpLabelLayout.setVisibility(8);
        } else if (2131297180 == radioGroupPlus2.getCheckedRadioButtonId()) {
            this.cleanGroupType = 3;
            this.jumpLabelLayout.setVisibility(8);
        } else if (2131297181 == radioGroupPlus2.getCheckedRadioButtonId()) {
            this.cleanGroupType = 4;
            this.jumpLabelLayout.setVisibility(0);
            if (this.groupNames == null || "".equals(this.groupNames)) {
                this.jumpLabel.setText("设置要删除的群聊");
            } else {
                this.jumpLabel.setText(this.groupNames);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.CleanGroupsActivity] */
    @OnClick({2131297052, 2131297049, 2131296877, 2131297425, 2131297185, 2131297186, 2131297187, 2131297188, 2131297189, 2131297636})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296877) {
            Intent intent = new Intent(this, ObtainGroupAllActivity.class);
            intent.putExtra("selects", this.jumpLabel.getText().toString());
            startActivity(intent);
        } else if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.该功能自动批量删除不想要的微信群\n2.清理所有群：自动清理退出所有的微信群\n3.保留通讯录：不清理被保存到通讯录的群，其他的群全部清理退出\n4.保留自建群：不清理自己是群主的群，其他的群全部清理退出\n5.保留通讯录的群和自建群：不清理被保存到通讯录的群、自己是群主的群，其他的群全部清理退出\n6.退出指定条件下的群：系统只会清理您设置的群聊。");
        } else if (id != 2131297425) {
            if (id != 2131297636) {
                switch (id) {
                    case 2131297185:
                        this.radioGroupPlus.check(2131297177);
                        return;
                    case 2131297186:
                        this.radioGroupPlus.check(2131297178);
                        return;
                    case 2131297187:
                        this.radioGroupPlus.check(2131297179);
                        return;
                    case 2131297188:
                        this.radioGroupPlus.check(2131297180);
                        return;
                    case 2131297189:
                        this.radioGroupPlus.check(2131297181);
                        return;
                    default:
                        return;
                }
            } else {
                WebViewActivity.startActivity(this, "群清理视频教程", QBangApis.VIDEO_CLEAN_GROUPS);
            }
        } else if (this.cleanGroupType != 4 || (this.groupNames != null && !"".equals(this.groupNames))) {
            startCheck("55", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("55");
                    operationParameterModel.setMaxOperaNum(CleanGroupsActivity.this.defaultNumber);
                    operationParameterModel.setDeleteFriendsType(CleanGroupsActivity.this.cleanGroupType);
                    operationParameterModel.setGroupNames(CleanGroupsActivity.this.groupNames);
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    CleanGroupsActivity.this.startWX(operationParameterModel);
                }
            });
        } else {
            ToastUtils.showToast(this, "请设置要删除的群聊");
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(WxGroupEvent wxGroupEvent) {
        LinkedHashSet<String> selectedGroupList = wxGroupEvent.getSelectedGroupList();
        if (selectedGroupList == null || selectedGroupList.size() <= 0) {
            this.jumpLabel.setText("请设置要删除的群聊");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = selectedGroupList.iterator();
        while (it.hasNext()) {
            sb.append(((String) it.next()) + ";");
        }
        this.groupNames = sb.toString();
        this.jumpLabel.setText(this.groupNames);
    }
}
