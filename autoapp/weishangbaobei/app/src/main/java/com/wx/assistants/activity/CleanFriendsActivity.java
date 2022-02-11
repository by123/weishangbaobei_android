package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.NumSettingOnlyLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CleanFriendsActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int cleanFriendType = 1;
    /* access modifiers changed from: private */
    public int defaultNumber = 300;
    @BindView(2131296619)
    EditText editNickName;
    @BindView(2131296873)
    TextView jumpLabel;
    @BindView(2131296877)
    LinearLayout jumpLabelLayout;
    @BindView(2131296895)
    LinearLayout layoutLabel;
    @BindView(2131296896)
    LinearLayout layoutNickname;
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
    @BindView(2131297078)
    NumSettingOnlyLayout numSettingOnlyLayout;
    @BindView(2131297183)
    RadioGroup radioGroup;
    @BindView(2131297210)
    RadioButton rdoBtnDeleteAll;
    @BindView(2131297211)
    RadioButton rdoBtnDeleteLabel;
    @BindView(2131297212)
    RadioButton rdoBtnDeleteNickname;
    @BindView(2131297213)
    RadioButton rdoBtnDeleteNickname2;
    @BindView(2131297214)
    RadioButton rdoBtnDeleteNickname3;
    @BindView(2131297425)
    Button startWx;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags1 = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags1Peoples = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags2 = new LinkedHashSet<>();
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags2Peoples = new LinkedHashSet<>();
    private String tagsName1 = "";
    private String tagsName2 = "";
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6713);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.CleanFriendsActivity] */
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
        this.layoutNickname.setVisibility(0);
        this.layoutLabel.setVisibility(8);
        this.cleanFriendType = 1;
        this.navTitle.setText("一键删除好友");
        this.navRightText.setText("删除记录");
        this.startWx.setText("启动微信删除好友");
        this.jumpLabel.setText("可设置不删除的好友，默认全部删除");
        this.editNickName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                if (obj != null && !"".equals(obj)) {
                    if ("A000非好友".equals(obj)) {
                        CleanFriendsActivity.this.rdoBtnDeleteNickname3.setChecked(true);
                        CleanFriendsActivity.this.rdoBtnDeleteNickname2.setChecked(false);
                    } else if ("A000被屏蔽".equals(obj)) {
                        CleanFriendsActivity.this.rdoBtnDeleteNickname2.setChecked(true);
                        CleanFriendsActivity.this.rdoBtnDeleteNickname3.setChecked(false);
                    } else {
                        CleanFriendsActivity.this.rdoBtnDeleteNickname3.setChecked(false);
                        CleanFriendsActivity.this.rdoBtnDeleteNickname2.setChecked(false);
                    }
                }
            }
        });
        this.numSettingOnlyLayout.setNum(this.defaultNumber, this.maxNumber, "设置删除数量");
        this.numSettingOnlyLayout.setOnResultListener(new NumSettingOnlyLayout.OnResultListener() {
            public void result(int i) {
                int unused = CleanFriendsActivity.this.defaultNumber = i;
            }
        });
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.CleanFriendsActivity] */
    @OnClick({2131297052, 2131297049, 2131296877, 2131297425, 2131297636, 2131297213, 2131297214, 2131297210, 2131297212, 2131297211})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296877) {
            Intent intent = new Intent(this, ObtainTagActivity.class);
            intent.putExtra("selects", this.jumpLabel.getText().toString());
            startActivity(intent);
        } else if (id == 2131297049) {
            back();
        } else if (id == 2131297052) {
            startActivity(ClearFriendRecoverActivity.class, false, true);
        } else if (id == 2131297425) {
            final String obj = this.editNickName.getText().toString();
            if (this.cleanFriendType == 0) {
                if (this.tags1 == null || this.tags1.size() == 0) {
                    this.jumpLabel.setText("可设置不删除的好友，默认全部删除");
                }
            } else if (this.cleanFriendType == 1) {
                if (obj == null || "".equals(obj)) {
                    ToastUtils.showToast(this, "请设置要删除的好友");
                    return;
                }
            } else if (this.cleanFriendType == 2 && (this.tags2 == null || this.tags2.size() == 0)) {
                this.jumpLabel.setText("点我设置您要删除好友所在的标签");
                ToastUtils.showToast(this, "请设置要删除的标签");
                return;
            }
            startCheck("21", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("21");
                    operationParameterModel.setMaxOperaNum(CleanFriendsActivity.this.defaultNumber);
                    operationParameterModel.setDeleteFriendsType(CleanFriendsActivity.this.cleanFriendType);
                    operationParameterModel.setFriendsNameStr(obj);
                    if (CleanFriendsActivity.this.cleanFriendType == 0) {
                        operationParameterModel.setTagListNames(CleanFriendsActivity.this.tags1);
                        operationParameterModel.setTagListPeoples(CleanFriendsActivity.this.tags1Peoples);
                    } else if (CleanFriendsActivity.this.cleanFriendType == 2) {
                        operationParameterModel.setTagListNames(CleanFriendsActivity.this.tags2);
                        operationParameterModel.setTagListPeoples(CleanFriendsActivity.this.tags2Peoples);
                    }
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    CleanFriendsActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id != 2131297636) {
            switch (id) {
                case 2131297210:
                    this.layoutNickname.setVisibility(8);
                    this.layoutLabel.setVisibility(0);
                    this.cleanFriendType = 0;
                    if (this.tags1 == null || this.tags1.size() == 0) {
                        this.jumpLabel.setText("可设置不删除的好友，默认全部删除");
                        return;
                    } else {
                        this.jumpLabel.setText(this.tagsName1);
                        return;
                    }
                case 2131297211:
                    this.layoutNickname.setVisibility(8);
                    this.layoutLabel.setVisibility(0);
                    this.cleanFriendType = 2;
                    if (this.tags2 == null || this.tags2.size() == 0) {
                        this.jumpLabel.setText("点我设置您要删除好友所在的标签");
                        return;
                    } else {
                        this.jumpLabel.setText(this.tagsName2);
                        return;
                    }
                case 2131297212:
                    this.layoutNickname.setVisibility(0);
                    this.layoutLabel.setVisibility(8);
                    this.cleanFriendType = 1;
                    return;
                case 2131297213:
                    this.editNickName.setText("A000被屏蔽");
                    return;
                case 2131297214:
                    this.editNickName.setText("A000非好友");
                    return;
                default:
                    return;
            }
        } else {
            WebViewActivity.startActivity(this, "一键删除好友视频教程", QBangApis.VIDEO_CLEAN_FRIENDS);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        LinkedHashSet<String> selectedPeopleList = wxTagEvent.getSelectedPeopleList();
        if (this.cleanFriendType == 0) {
            this.tags1 = new LinkedHashSet<>();
            this.tags1.addAll(selectedTagList);
            this.tags1Peoples.clear();
            this.tags1Peoples.addAll(selectedPeopleList);
        } else if (this.cleanFriendType == 2) {
            this.tags2 = new LinkedHashSet<>();
            this.tags2.addAll(selectedTagList);
            this.tags2Peoples.clear();
            this.tags2Peoples.addAll(selectedPeopleList);
        }
        if (selectedTagList != null && selectedTagList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedTagList.size(); i++) {
                sb.append(selectedTagList.get(i) + ";");
            }
            if (this.cleanFriendType == 0) {
                this.tagsName1 = sb.toString();
            } else if (this.cleanFriendType == 2) {
                this.tagsName2 = sb.toString();
            }
            this.jumpLabel.setText(sb.toString());
        } else if (this.cleanFriendType == 0) {
            this.jumpLabel.setText("可设置不删除的好友，默认全部删除");
        } else {
            this.jumpLabel.setText("点我设置您要删除好友所在的标签");
        }
    }
}
