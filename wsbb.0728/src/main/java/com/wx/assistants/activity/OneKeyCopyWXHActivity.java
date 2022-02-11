package com.wx.assistants.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.view.FriendsCopyTagSetLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class OneKeyCopyWXHActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public int deleteMode = 1;
    @BindView(2131296585)
    TextView descText;
    @BindView(2131296720)
    FriendsCopyTagSetLayout friendsCopyTagSetLayout;
    @BindView(2131296873)
    TextView jumpLabel;
    @BindView(2131296877)
    LinearLayout jumpLabelLayout;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
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
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297419)
    TextView startPull;
    @BindView(2131297422)
    LinearLayout startPullLayout;
    @BindView(2131297425)
    Button startWx;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tagList = new LinkedHashSet<>();
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6748);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyCopyWXHActivity] */
    private void initView() {
        this.navTitle.setText("好友迁移");
        this.navRightText.setText("备份记录");
        this.startWx.setText("启动微信开始备份");
        this.jumpLabel.setText("点我设置要备份的好友标签");
        this.descText.setText("1.您若有多个微信号，利用本功能可以提前把微信好友进行备份，然后去右上角 备份记录 迁移到的其他微信号\n2.如果发现当前账号被封，可使用此功能，将原有微信号好友先进行备份，然后在迁移到新微信号，防止好友丢失。\n3.为了防止迁移好友频繁，建议您选择比较重要的好友优先迁移。并且一次最多30人。每迁移一次时间间隔2小时以上。\n");
        this.startPull.setText("默认从第1个微信好友开始备份");
        FontUtils.changeFontColor(this, this.startPull);
        this.friendsCopyTagSetLayout.setOnFriendsCopyTagSetListener(new FriendsCopyTagSetLayout.OnFriendsCopyTagSetListener() {
            public void circulateMode(int i) {
                int unused = OneKeyCopyWXHActivity.this.deleteMode = i;
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyCopyWXHActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
        this.tagList.clear();
        if (selectedTagList == null || selectedTagList.size() <= 0) {
            this.jumpLabel.setText("点我设置要备份的好友标签");
        } else {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= selectedTagList.size()) {
                    break;
                }
                this.tagList.add(selectedTagList.get(i2));
                sb.append(selectedTagList.get(i2) + ";");
                i = i2 + 1;
            }
            this.jumpLabel.setText(sb.toString());
        }
        if (wxTagEvent.getSelectedPeopleList() != null) {
            this.jumpPersons = new LinkedHashSet<>();
            this.jumpPersons.addAll(wxTagEvent.getSelectedPeopleList());
            return;
        }
        this.jumpPersons = new LinkedHashSet<>();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.OneKeyCopyWXHActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        PermissionUtils.checkReadAndWriteExternalStorage(this, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
            }
        });
        this.startIndex = ((Integer) SPUtils.get(MyApplication.getConText(), "copy_friend_index", 1)).intValue();
        TextView textView = this.startPull;
        textView.setText("默认从第" + this.startIndex + "个好友备份");
        FontUtils.changeFontColor(this, this.startPull);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.OneKeyCopyWXHActivity] */
    @OnClick({2131297049, 2131296877, 2131297425, 2131297636, 2131297052, 2131297422})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296877:
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.jumpLabel.getText().toString());
                startActivity(intent);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                startActivity(CopyFriendRecoverActivity.class, true, true);
                return;
            case 2131297422:
                try {
                    String charSequence = this.startPull.getText().toString();
                    this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception e) {
                }
                try {
                    DialogUIUtils.dialogSetStartPoint(this, "设置备份起点", this.startIndex, 5000, new AlertEditDialog.OnEditTextListener() {
                        /* JADX WARNING: type inference failed for: r0v2, types: [android.content.Context, com.wx.assistants.activity.OneKeyCopyWXHActivity] */
                        public void result(int i) {
                            TextView textView = OneKeyCopyWXHActivity.this.startPull;
                            textView.setText("默认从第" + i + "个微信好友开始备份");
                            FontUtils.changeFontColor(OneKeyCopyWXHActivity.this, OneKeyCopyWXHActivity.this.startPull);
                            int unused = OneKeyCopyWXHActivity.this.startIndex = i;
                            SPUtils.put(MyApplication.getConText(), "copy_friend_index", Integer.valueOf(i));
                        }
                    });
                    return;
                } catch (Exception e2) {
                    return;
                }
            case 2131297425:
                startCheck("44", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setStartIndex(OneKeyCopyWXHActivity.this.startIndex);
                        operationParameterModel.setMaxOperaNum(5000);
                        if (OneKeyCopyWXHActivity.this.jumpLabel.getText().toString().contains("点我设置")) {
                            operationParameterModel.setTaskNum("44");
                        } else {
                            operationParameterModel.setTaskNum("46");
                            if (OneKeyCopyWXHActivity.this.deleteMode == 0) {
                                operationParameterModel.setTagListPeoples(OneKeyCopyWXHActivity.this.jumpPersons);
                                operationParameterModel.setDeleteFriendsType(1);
                            } else {
                                operationParameterModel.setTagListNames(OneKeyCopyWXHActivity.this.tagList);
                                operationParameterModel.setDeleteFriendsType(2);
                            }
                        }
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        OneKeyCopyWXHActivity.this.startWX(operationParameterModel);
                    }
                });
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "好友迁移视频教程", QBangApis.VIDEO_COPY_FRIENDS);
                return;
            default:
                return;
        }
    }
}
