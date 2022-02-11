package com.wx.assistants.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.stub.StubApp;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.HotMaterialTextBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
import com.wx.assistants.globle.QBangApis;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.PerformClickUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.glide.GlideUtils;
import com.wx.assistants.view.CustomRadioLayout;
import com.wx.assistants.view.CustomSpeedLayout;
import com.wx.assistants.view.EditTextWithScrollView;
import com.wx.assistants.view.FriendSendModeLayout;
import com.wx.assistants.view.SendContentLayout;
import com.wx.assistants.view.SendOrderLayout;
import com.wx.assistants.view.TagCloudLayout;
import com.wx.assistants.webview.WebViewActivity;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GroupSendBatchToFriendActivity extends BaseActivity {
    @BindView(2131296320)
    TextView addPicVideoText;
    @BindView(2131296391)
    LinearLayout batchLayout;
    @BindView(2131296392)
    Switch batchSwitchBtn;
    @BindView(2131296507)
    LinearLayout cleanEditText;
    @BindView(2131296508)
    LinearLayout cleanImage;
    @BindView(2131296572)
    CustomSpeedLayout customSpeedLayout;
    @BindView(2131296616)
    EditTextWithScrollView editLeavingMessage;
    @BindView(2131296617)
    LinearLayout editLeavingMessage2;
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    @BindView(2131296717)
    FriendSendModeLayout friendSendModeLayout;
    /* access modifiers changed from: private */
    public boolean isAutoBatch = true;
    @BindView(2131296871)
    LinearLayout jumpFriendLayout;
    @BindView(2131296872)
    TextView jumpLabel;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> jumpPersons = new LinkedHashSet<>();
    @BindView(2131296950)
    LinearLayout linearLayoutTemplate;
    /* access modifiers changed from: private */
    public ArrayList<String> mList;
    /* access modifiers changed from: private */
    public int msgType = 2;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297054)
    TextView navTitle;
    private String noSendStr = "";
    @BindView(2131297131)
    LinearLayout picLayout;
    @BindView(2131297132)
    CustomRadioLayout picVideoRadioLayout;
    private String sayContent = "";
    /* access modifiers changed from: private */
    public int scrollSpeed = CustomSpeedLayout.DEFAULT_SPEED;
    /* access modifiers changed from: private */
    public ArrayList<String> selectImages = new ArrayList<>();
    @BindView(2131297311)
    ImageView selectImg;
    /* access modifiers changed from: private */
    public ArrayList<String> selectVideos = new ArrayList<>();
    @BindView(2131297333)
    SendContentLayout sendContentLayout;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    @BindView(2131297335)
    SendOrderLayout sendOrderLayout;
    /* access modifiers changed from: private */
    public int sendPicVideoCode = 0;
    /* access modifiers changed from: private */
    public int sendType = 0;
    /* access modifiers changed from: private */
    public int startBathIndex = 1;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    @BindView(2131297425)
    Button startWx;
    /* access modifiers changed from: private */
    public TagSelectAdapter tagSelectAdapter;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> tags = new LinkedHashSet<>();
    @BindView(2131297464)
    LinearLayout textLayout;
    @BindView(2131297636)
    LinearLayout videoIntroduceLayout;

    static {
        StubApp.interface11(6723);
    }

    public void executeStartWx(OperationParameterModel operationParameterModel) {
        if (this.isAutoBatch) {
            operationParameterModel.setTagListPeoples(this.jumpPersons);
            operationParameterModel.setTagListNames(this.tags);
            operationParameterModel.setSendOrder(this.sendOrder);
            operationParameterModel.setTagFriendType(this.sendType);
            operationParameterModel.setStartIndex(this.startIndex);
            operationParameterModel.setMessageSendType(this.msgType);
            operationParameterModel.setSayContent(this.sayContent);
            operationParameterModel.setLocalImgUrl("xxx.png");
            operationParameterModel.setSpaceTime(0);
            operationParameterModel.setIntimateMode(0);
            operationParameterModel.setScrollSpeed(this.scrollSpeed);
            MyApplication.instance.setOperationParameterModel(operationParameterModel);
            startWX(operationParameterModel);
            return;
        }
        operationParameterModel.setTaskNum("4");
        operationParameterModel.setSendOrder(this.sendOrder);
        operationParameterModel.setStartIndex(this.startBathIndex);
        operationParameterModel.setMessageSendType(this.msgType);
        operationParameterModel.setSayContent(this.sayContent);
        operationParameterModel.setTagListPeoples(this.jumpPersons);
        operationParameterModel.setLocalImgUrl("xx.png");
        operationParameterModel.setScrollSpeed(this.scrollSpeed);
        MyApplication.instance.setOperationParameterModel(operationParameterModel);
        startWX(operationParameterModel);
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity, android.content.Context] */
    public void initView() {
        this.navRightImg.setVisibility(0);
        this.navTitle.setText("批量发送给好友");
        this.startWx.setText("启动微信开始群发");
        this.batchSwitchBtn.setChecked(true);
        this.friendSendModeLayout.setVisibility(0);
        this.jumpLabel.setText("");
        this.jumpLabel.setVisibility(8);
        this.batchSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = GroupSendBatchToFriendActivity.this.isAutoBatch = z;
                if (GroupSendBatchToFriendActivity.this.isAutoBatch) {
                    GroupSendBatchToFriendActivity.this.friendSendModeLayout.setVisibility(0);
                    GroupSendBatchToFriendActivity.this.batchLayout.setVisibility(8);
                    return;
                }
                GroupSendBatchToFriendActivity.this.friendSendModeLayout.setVisibility(8);
                GroupSendBatchToFriendActivity.this.batchLayout.setVisibility(0);
            }
        });
        this.picVideoRadioLayout.setText("选择图片/视频", "图片", 60, "视频", 60);
        this.picVideoRadioLayout.setDefaultSelect(0);
        this.picVideoRadioLayout.setOnModelSelectListener(new CustomRadioLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = GroupSendBatchToFriendActivity.this.sendPicVideoCode = i;
                if (GroupSendBatchToFriendActivity.this.sendPicVideoCode == 0) {
                    SPUtils.put(MyApplication.getConText(), "batch_text_img_friend_code", 0);
                    GroupSendBatchToFriendActivity.this.addPicVideoText.setText("添加图片");
                    GroupSendBatchToFriendActivity.this.sendOrderLayout.setLeftText("先发图片");
                    GroupSendBatchToFriendActivity.this.setPicVideoData(0);
                    return;
                }
                SPUtils.put(MyApplication.getConText(), "batch_text_img_friend_code", 1);
                GroupSendBatchToFriendActivity.this.addPicVideoText.setText("添加视频");
                GroupSendBatchToFriendActivity.this.sendOrderLayout.setLeftText("先发视频");
                GroupSendBatchToFriendActivity.this.setPicVideoData(1);
            }
        });
        this.selectImg.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                GroupSendBatchToFriendActivity.this.imgLongClickPreview(GroupSendBatchToFriendActivity.this.sendPicVideoCode == 0 ? GroupSendBatchToFriendActivity.this.selectImages : GroupSendBatchToFriendActivity.this.selectVideos);
                return false;
            }
        });
        this.friendSendModeLayout.setFriendSendTypeListener(new FriendSendModeLayout.FriendSendTypeListener() {
            public void sendMembers(LinkedHashSet<String> linkedHashSet) {
                LinkedHashSet unused = GroupSendBatchToFriendActivity.this.jumpPersons = linkedHashSet;
            }

            public void sendStartIndex(int i) {
                int unused = GroupSendBatchToFriendActivity.this.startIndex = i;
            }

            public void sendType(int i) {
                int unused = GroupSendBatchToFriendActivity.this.sendType = i;
            }
        });
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                if (editable.toString() != null) {
                    SPUtils.put(MyApplication.getConText(), "batch_text_img_friend_content", editable.toString());
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.sendOrderLayout.setOnSendOrderListener(new SendOrderLayout.OnSendOrderListener() {
            public void sendOrder(int i) {
                int unused = GroupSendBatchToFriendActivity.this.sendOrder = i;
                SPUtils.put(MyApplication.getConText(), "group_send_batch_text_img_friend_order", Integer.valueOf(GroupSendBatchToFriendActivity.this.sendOrder));
            }
        });
        this.sendContentLayout.setCirculateModelListener(new SendContentLayout.CirculateModelListener() {
            public void circulateMode(int i) {
                int unused = GroupSendBatchToFriendActivity.this.msgType = i;
                GroupSendBatchToFriendActivity.this.updateLayout();
            }
        });
        this.mList = new ArrayList<>();
        this.mList.add("1-200");
        this.mList.add("201-400");
        this.mList.add("401-600");
        this.mList.add("601-800");
        this.mList.add("801-1000");
        this.mList.add("1001-1200");
        this.mList.add("1201-1400");
        this.mList.add("1401-1600");
        this.mList.add("1601-1800");
        this.mList.add("1801-2000");
        this.mList.add("2001-2200");
        this.mList.add("2201-2400");
        this.mList.add("2401-2600");
        this.mList.add("2601-2800");
        this.mList.add("2801-3000");
        this.mList.add("更多");
        this.tagSelectAdapter = new TagSelectAdapter(this, this.mList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                String str = (String) GroupSendBatchToFriendActivity.this.mList.get(i);
                if ("更多".equals(str)) {
                    if ("2801-3000".equals((String) GroupSendBatchToFriendActivity.this.mList.get(GroupSendBatchToFriendActivity.this.mList.size() - 2))) {
                        GroupSendBatchToFriendActivity.this.mList.remove(GroupSendBatchToFriendActivity.this.mList.size() - 1);
                        GroupSendBatchToFriendActivity.this.mList.add("3001-3200");
                        GroupSendBatchToFriendActivity.this.mList.add("3201-3400");
                        GroupSendBatchToFriendActivity.this.mList.add("3401-3600");
                        GroupSendBatchToFriendActivity.this.mList.add("3601-3800");
                        GroupSendBatchToFriendActivity.this.mList.add("3801-4000");
                        GroupSendBatchToFriendActivity.this.mList.add("4001-4200");
                        GroupSendBatchToFriendActivity.this.mList.add("4201-4400");
                        GroupSendBatchToFriendActivity.this.mList.add("4401-4600");
                        GroupSendBatchToFriendActivity.this.mList.add("4601-4800");
                        GroupSendBatchToFriendActivity.this.mList.add("4801-5000");
                    }
                    GroupSendBatchToFriendActivity.this.tagSelectAdapter.notifyDataSetChanged();
                    return;
                }
                try {
                    int unused = GroupSendBatchToFriendActivity.this.startBathIndex = Integer.parseInt(str.split("-")[0]);
                } catch (Exception e) {
                    LogUtils.log("WS_BABY_Catch_31");
                    e.printStackTrace();
                }
            }
        });
        this.customSpeedLayout.setFunction(CustomSpeedLayout.FUNCTION.GROUP_BATCH_AUTO);
        this.customSpeedLayout.setOnSignSeekBarListener(new CustomSpeedLayout.OnSignSeekBarListener() {
            public void progress(int i) {
                int unused = GroupSendBatchToFriendActivity.this.scrollSpeed = i;
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_PROGRESS_" + i);
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

    /* JADX WARNING: type inference failed for: r2v0, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity, android.content.Context] */
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
    public void onEventMainThread(final HotMaterialTextBean hotMaterialTextBean) {
        runOnUiThread(new Runnable() {
            public void run() {
                final String materialStr = hotMaterialTextBean.getMaterialStr();
                if (materialStr != null && !"".equals(materialStr)) {
                    GroupSendBatchToFriendActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            GroupSendBatchToFriendActivity.this.editLeavingMessage.setText(materialStr);
                            if (materialStr != null && !"".equals(materialStr)) {
                                SPUtils.put(MyApplication.getConText(), "batch_text_img_friend_content", materialStr);
                            }
                        }
                    });
                }
            }
        });
    }

    @Subscribe
    public void onEventMainThread(WxTagEvent wxTagEvent) {
        if (this.isAutoBatch) {
            ArrayList<String> selectedTagList = wxTagEvent.getSelectedTagList();
            this.tags = new LinkedHashSet<>();
            if (selectedTagList == null || selectedTagList.size() <= 0) {
                this.friendSendModeLayout.setSendStr("");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < selectedTagList.size(); i++) {
                    sb.append(selectedTagList.get(i) + ";");
                    this.tags.add(selectedTagList.get(i));
                }
                this.noSendStr = sb.toString();
                this.friendSendModeLayout.setSendStr(this.noSendStr);
            }
            if (wxTagEvent.getSelectedPeopleList() != null) {
                this.jumpPersons = new LinkedHashSet<>();
                this.jumpPersons.addAll(wxTagEvent.getSelectedPeopleList());
            } else {
                this.jumpPersons = new LinkedHashSet<>();
            }
            this.friendSendModeLayout.setSendMember(this.jumpPersons);
            return;
        }
        ArrayList<String> selectedTagList2 = wxTagEvent.getSelectedTagList();
        if (selectedTagList2 == null || selectedTagList2.size() <= 0) {
            this.jumpLabel.setText("");
            this.jumpLabel.setVisibility(8);
        } else {
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < selectedTagList2.size(); i2++) {
                sb2.append(selectedTagList2.get(i2) + ";");
            }
            this.jumpLabel.setText(sb2.toString());
            this.jumpLabel.setVisibility(0);
        }
        if (wxTagEvent.getSelectedPeopleList() != null) {
            this.jumpPersons = new LinkedHashSet<>();
            this.jumpPersons.addAll(wxTagEvent.getSelectedPeopleList());
            return;
        }
        this.jumpPersons = new LinkedHashSet<>();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            String str = (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_friend_content", "");
            String str2 = (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_friend_path", "");
            String str3 = (String) SPUtils.get(MyApplication.getConText(), "batch_text_video_friend_path", "");
            int intValue = ((Integer) SPUtils.get(MyApplication.getConText(), "batch_text_img_friend_code", 0)).intValue();
            this.picVideoRadioLayout.setDefaultSelect(intValue);
            if (str != null && !"".equals(str)) {
                this.editLeavingMessage.setText(str);
            }
            if ((str2 != null && !"".equals(str2)) || (str3 != null && !"".equals(str3))) {
                if (intValue == 0) {
                    this.selectImages = new ArrayList<>();
                    this.selectImages.add(str2);
                } else {
                    this.selectVideos = new ArrayList<>();
                    this.selectVideos.add(str3);
                }
                this.editLeavingMessage2.setVisibility(8);
                this.selectImg.setVisibility(0);
                this.cleanImage.setVisibility(0);
                Context conText = MyApplication.getConText();
                ImageView imageView = this.selectImg;
                if (intValue != 0) {
                    str2 = str3;
                }
                GlideUtils.showImageViewGone(conText, imageView, str2, new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            GroupSendBatchToFriendActivity.this.cleanImage.performClick();
                        }
                    }
                });
            }
            int intValue2 = ((Integer) SPUtils.get(MyApplication.getConText(), "batch_text_img_num_all", 1)).intValue();
            int intValue3 = ((Integer) SPUtils.get(MyApplication.getConText(), "batch_text_img_num_part", 1)).intValue();
            int intValue4 = ((Integer) SPUtils.get(MyApplication.getConText(), "batch_text_img_num_shield", 1)).intValue();
            this.friendSendModeLayout.initSendIndexStrAll(true, "batch_text_img_num_all", intValue2);
            this.friendSendModeLayout.initSendIndexStrPart(true, "batch_text_img_num_part", intValue3);
            this.friendSendModeLayout.initSendIndexStrShield(true, "batch_text_img_num_shield", intValue4);
            this.friendSendModeLayout.initSendLabelStrAll(true, "batch_text_img_label_all", (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_label_all", ""));
            this.friendSendModeLayout.initSendLabelStrPart(true, "batch_text_img_label_part", (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_label_part", ""));
            this.friendSendModeLayout.initSendLabelStrShield(true, "batch_text_img_label_shield", (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_label_shield", ""));
            this.friendSendModeLayout.initSendLabelMemberPart(true, "batch_text_img_label_member_part", (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_label_member_part", ""));
            this.friendSendModeLayout.initSendLabelMemberShield(true, "batch_text_img_label_member_shield", (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_label_member_shield", ""));
            this.sendOrder = ((Integer) SPUtils.get(MyApplication.getConText(), "group_send_batch_text_img_friend_order", 0)).intValue();
            this.sendOrderLayout.setSendOrder(this.sendOrder);
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.activity.GroupSendBatchToFriendActivity, android.content.Context] */
    @OnClick({2131296507, 2131296871, 2131296508, 2131297049, 2131297425, 2131297636, 2131297052, 2131296616, 2131296617, 2131296950, 2131297311})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296507:
                this.editLeavingMessage.setText("");
                return;
            case 2131296508:
                this.cleanImage.setVisibility(8);
                if (this.sendPicVideoCode == 0) {
                    this.selectImages = null;
                } else {
                    this.selectVideos = null;
                }
                this.editLeavingMessage2.setVisibility(0);
                this.selectImg.setVisibility(8);
                if (this.sendPicVideoCode == 0) {
                    SPUtils.put(MyApplication.getConText(), "batch_text_img_friend_path", "");
                } else {
                    SPUtils.put(MyApplication.getConText(), "batch_text_video_friend_path", "");
                }
                GlideUtils.showImageViewGone(MyApplication.getConText(), this.selectImg, "", new GlideUtils.OnLoadImgListener() {
                    public void loadResult(int i) {
                        PrintStream printStream = System.out;
                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                        if (i == 1) {
                            GroupSendBatchToFriendActivity.this.cleanImage.performClick();
                        }
                    }
                });
                return;
            case 2131296617:
                if (this.sendPicVideoCode == 0) {
                    selectSingleAlbum(new BaseActivity.OnAlbumResultListener() {
                        public void result(ArrayList<String> arrayList) {
                            if (arrayList.size() > 0) {
                                ArrayList unused = GroupSendBatchToFriendActivity.this.selectImages = arrayList;
                                GroupSendBatchToFriendActivity.this.editLeavingMessage2.setVisibility(8);
                                GroupSendBatchToFriendActivity.this.selectImg.setVisibility(0);
                                SPUtils.put(MyApplication.getConText(), "batch_text_img_friend_path", GroupSendBatchToFriendActivity.this.selectImages.get(0));
                                GroupSendBatchToFriendActivity.this.cleanImage.setVisibility(0);
                                GlideUtils.showImageViewGone(MyApplication.getConText(), GroupSendBatchToFriendActivity.this.selectImg, (String) GroupSendBatchToFriendActivity.this.selectImages.get(0), new GlideUtils.OnLoadImgListener() {
                                    public void loadResult(int i) {
                                        PrintStream printStream = System.out;
                                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                        if (i == 1) {
                                            GroupSendBatchToFriendActivity.this.cleanImage.performClick();
                                        }
                                    }
                                });
                            }
                        }
                    });
                    return;
                } else {
                    selectSingleVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                        public void result(ArrayList<String> arrayList) {
                            if (arrayList.size() > 0) {
                                ArrayList unused = GroupSendBatchToFriendActivity.this.selectVideos = arrayList;
                                GroupSendBatchToFriendActivity.this.editLeavingMessage2.setVisibility(8);
                                GroupSendBatchToFriendActivity.this.selectImg.setVisibility(0);
                                SPUtils.put(MyApplication.getConText(), "batch_text_video_friend_path", GroupSendBatchToFriendActivity.this.selectVideos.get(0));
                                GroupSendBatchToFriendActivity.this.cleanImage.setVisibility(0);
                                GlideUtils.showImageViewGone(MyApplication.getConText(), GroupSendBatchToFriendActivity.this.selectImg, (String) GroupSendBatchToFriendActivity.this.selectVideos.get(0), new GlideUtils.OnLoadImgListener() {
                                    public void loadResult(int i) {
                                        PrintStream printStream = System.out;
                                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                        if (i == 1) {
                                            GroupSendBatchToFriendActivity.this.cleanImage.performClick();
                                        }
                                    }
                                });
                            }
                        }
                    });
                    return;
                }
            case 2131296871:
                Intent intent = new Intent(this, ObtainTagActivity.class);
                intent.putExtra("selects", this.jumpLabel.getText().toString());
                startActivity(intent);
                return;
            case 2131296950:
                WebViewActivity.startActivity(this, "热门群发模版", QBangApis.HOT_MASTER_PLATE_URL, false);
                return;
            case 2131297049:
                back();
                return;
            case 2131297052:
                DialogUIUtils.dialogFunctionalSpecification(this, this.navTitle.getText().toString(), "1.批量群发好友：借助微信群发助手，每次最多只能勾选200人\n\n2.自动分批好友，支持文字、图片或视频同时发送，分批完成后，会自动进行下一批群发\n\n3.群发停止时，若群发的内容不改变，则默认记录上次群发的位置，再次群发时，将接着上次没有群发完的好友，继续群发，以保证不重复发送\n\n4.如果提示，操作太频繁，请稍后再试！这种情况下只需等待10分钟左右就可以正常群发消息。注：群发消息时间间隔不要太短，否则会提示操作太频繁");
                return;
            case 2131297065:
                Intent intent2 = new Intent(this, ObtainTagActivity.class);
                intent2.putExtra("selects", this.noSendStr);
                startActivity(intent2);
                return;
            case 2131297311:
                if (this.sendPicVideoCode == 0) {
                    selectSingleAlbum(new BaseActivity.OnAlbumResultListener() {
                        public void result(ArrayList<String> arrayList) {
                            if (arrayList.size() > 0) {
                                ArrayList unused = GroupSendBatchToFriendActivity.this.selectImages = arrayList;
                                GroupSendBatchToFriendActivity.this.editLeavingMessage2.setVisibility(8);
                                GroupSendBatchToFriendActivity.this.selectImg.setVisibility(0);
                                SPUtils.put(MyApplication.getConText(), "batch_text_img_friend_path", GroupSendBatchToFriendActivity.this.selectImages.get(0));
                                GroupSendBatchToFriendActivity.this.cleanImage.setVisibility(0);
                                GlideUtils.showImageViewGone(MyApplication.getConText(), GroupSendBatchToFriendActivity.this.selectImg, (String) GroupSendBatchToFriendActivity.this.selectImages.get(0), new GlideUtils.OnLoadImgListener() {
                                    public void loadResult(int i) {
                                        PrintStream printStream = System.out;
                                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                        if (i == 1) {
                                            GroupSendBatchToFriendActivity.this.cleanImage.performClick();
                                        }
                                    }
                                });
                            }
                        }
                    });
                    return;
                } else {
                    selectSingleVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                        public void result(ArrayList<String> arrayList) {
                            if (arrayList.size() > 0) {
                                ArrayList unused = GroupSendBatchToFriendActivity.this.selectVideos = arrayList;
                                GroupSendBatchToFriendActivity.this.editLeavingMessage2.setVisibility(8);
                                GroupSendBatchToFriendActivity.this.selectImg.setVisibility(0);
                                SPUtils.put(MyApplication.getConText(), "batch_text_video_friend_path", GroupSendBatchToFriendActivity.this.selectVideos.get(0));
                                GroupSendBatchToFriendActivity.this.cleanImage.setVisibility(0);
                                GlideUtils.showImageViewGone(MyApplication.getConText(), GroupSendBatchToFriendActivity.this.selectImg, (String) GroupSendBatchToFriendActivity.this.selectVideos.get(0), new GlideUtils.OnLoadImgListener() {
                                    public void loadResult(int i) {
                                        PrintStream printStream = System.out;
                                        printStream.println("WS_BABY_RESULT_TYPE =" + i);
                                        if (i == 1) {
                                            GroupSendBatchToFriendActivity.this.cleanImage.performClick();
                                        }
                                    }
                                });
                            }
                        }
                    });
                    return;
                }
            case 2131297425:
                if (!PerformClickUtils.isFastClick()) {
                    this.sayContent = this.editLeavingMessage.getText().toString();
                    if (this.msgType == 0) {
                        if (this.sayContent == null || "".equals(this.sayContent)) {
                            ToastUtils.showToast(this, "请输入文字内容！");
                            return;
                        }
                    } else if (this.msgType == 1) {
                        if (this.sendPicVideoCode == 0) {
                            if (this.selectImages == null || this.selectImages.size() == 0) {
                                ToastUtils.showToast(this, "请选择图片！");
                                return;
                            }
                        } else if (this.selectVideos == null || this.selectVideos.size() == 0) {
                            ToastUtils.showToast(this, "请选择视频！");
                            return;
                        }
                    } else if (this.msgType == 2) {
                        if (this.sayContent == null || "".equals(this.sayContent)) {
                            ToastUtils.showToast(this, "请输入文字内容！");
                            return;
                        } else if (this.sendPicVideoCode == 0) {
                            if (this.selectImages == null || this.selectImages.size() == 0) {
                                ToastUtils.showToast(this, "请选择图片！");
                                return;
                            }
                        } else if (this.selectVideos == null || this.selectVideos.size() == 0) {
                            ToastUtils.showToast(this, "请选择视频！");
                            return;
                        }
                    }
                    if (this.jumpPersons == null || this.jumpPersons.size() <= 0) {
                        if (this.sendType == 1 && this.isAutoBatch) {
                            ToastUtils.showToast(this, "请设置您要群发的标签");
                            return;
                        } else if (this.sendType == 2 && this.isAutoBatch) {
                            ToastUtils.showToast(this, "请设置您要屏蔽的标签");
                            return;
                        }
                    }
                    String str = "37";
                    if (this.sendType == 0) {
                        str = "37";
                    } else if (this.sendType == 1) {
                        str = "7";
                    } else if (this.sendType == 2) {
                        str = "17";
                    }
                    startCheck(str, true, new BaseActivity.OnStartCheckListener() {
                        public void checkEnd() {
                            final OperationParameterModel operationParameterModel = new OperationParameterModel();
                            if (GroupSendBatchToFriendActivity.this.sendType == 0) {
                                operationParameterModel.setTaskNum("37");
                            } else if (GroupSendBatchToFriendActivity.this.sendType == 1) {
                                operationParameterModel.setTaskNum("7");
                            } else if (GroupSendBatchToFriendActivity.this.sendType == 2) {
                                operationParameterModel.setTaskNum("17");
                            }
                            if (GroupSendBatchToFriendActivity.this.tags == null || GroupSendBatchToFriendActivity.this.tags.size() == 0) {
                                LinkedHashSet unused = GroupSendBatchToFriendActivity.this.tags = new LinkedHashSet();
                                String partLabString = GroupSendBatchToFriendActivity.this.friendSendModeLayout.getPartLabString();
                                if (partLabString != null && !"".equals(partLabString)) {
                                    if (partLabString.contains(";")) {
                                        String[] split = partLabString.split(";");
                                        for (int i = 0; i < split.length; i++) {
                                            if (split[i] != null && !"".equals(split[i])) {
                                                GroupSendBatchToFriendActivity.this.tags.add(split[i]);
                                            }
                                        }
                                    } else {
                                        GroupSendBatchToFriendActivity.this.tags.add(partLabString);
                                    }
                                }
                            }
                            if (GroupSendBatchToFriendActivity.this.msgType == 1 || GroupSendBatchToFriendActivity.this.msgType == 2) {
                                new ArrayList();
                                ArrayList access$200 = GroupSendBatchToFriendActivity.this.sendPicVideoCode == 0 ? GroupSendBatchToFriendActivity.this.selectImages : GroupSendBatchToFriendActivity.this.selectVideos;
                                if (((String) access$200.get(0)).endsWith(PictureFileUtils.POST_VIDEO)) {
                                    GroupSendBatchToFriendActivity.this.showLoadingDialog("正在处理视频", false);
                                    GroupSendBatchToFriendActivity.this.compressSaveVideo((List<String>) access$200, (FileUtil.OnConvertMp4Listener) new FileUtil.OnConvertMp4Listener() {
                                        public void convertFail() {
                                            GroupSendBatchToFriendActivity.this.dismissLoadingDialog();
                                            ToastUtils.showToast(MyApplication.getConText(), "视频处理失败");
                                        }

                                        public void convertProgress(int i) {
                                            GroupSendBatchToFriendActivity groupSendBatchToFriendActivity = GroupSendBatchToFriendActivity.this;
                                            groupSendBatchToFriendActivity.updateLoadingDialog("视频处理 " + i + "%");
                                        }

                                        public void convertSuccess(String str) {
                                            GroupSendBatchToFriendActivity.this.dismissLoadingDialog();
                                            GroupSendBatchToFriendActivity.this.executeStartWx(operationParameterModel);
                                        }
                                    });
                                    return;
                                }
                                GroupSendBatchToFriendActivity.this.compressSaveImg((List<String>) access$200, (BaseActivity.OnSaveImgListener) new BaseActivity.OnSaveImgListener() {
                                    public void saveFail() {
                                        GroupSendBatchToFriendActivity.this.dismissLoadingDialog();
                                        ToastUtils.showToast(MyApplication.getConText(), "图片下载失败");
                                    }

                                    public void saveSuccess() {
                                        GroupSendBatchToFriendActivity.this.executeStartWx(operationParameterModel);
                                    }
                                });
                                return;
                            }
                            GroupSendBatchToFriendActivity.this.executeStartWx(operationParameterModel);
                        }
                    });
                    return;
                }
                return;
            case 2131297636:
                WebViewActivity.startActivity(this, "批量群发好友", QBangApis.VIDEO_GROUP_SEND_BATCH_FRIEND);
                return;
            default:
                return;
        }
    }

    public void setPicVideoData(int i) {
        String str = (String) SPUtils.get(MyApplication.getConText(), "batch_text_img_friend_path", "");
        String str2 = (String) SPUtils.get(MyApplication.getConText(), "batch_text_video_friend_path", "");
        if ((str != null && !"".equals(str)) || (str2 != null && !"".equals(str2))) {
            if (i == 0) {
                this.selectImages = new ArrayList<>();
                this.selectImages.add(str);
            } else {
                this.selectVideos = new ArrayList<>();
                this.selectVideos.add(str2);
            }
            this.editLeavingMessage2.setVisibility(8);
            this.selectImg.setVisibility(0);
            this.cleanImage.setVisibility(0);
            Context conText = MyApplication.getConText();
            ImageView imageView = this.selectImg;
            if (i != 0) {
                str = str2;
            }
            GlideUtils.showImageViewGone(conText, imageView, str, new GlideUtils.OnLoadImgListener() {
                public void loadResult(int i) {
                    PrintStream printStream = System.out;
                    printStream.println("WS_BABY_RESULT_TYPE =" + i);
                    if (i == 1) {
                        GroupSendBatchToFriendActivity.this.cleanImage.performClick();
                    }
                }
            });
        }
    }

    public void updateLayout() {
        if (this.msgType == 0) {
            this.textLayout.setVisibility(0);
            this.picLayout.setVisibility(8);
            this.sendOrderLayout.setVisibility(8);
        } else if (this.msgType == 1) {
            this.textLayout.setVisibility(8);
            this.picLayout.setVisibility(0);
            this.sendOrderLayout.setVisibility(8);
        } else if (this.msgType == 2) {
            this.textLayout.setVisibility(0);
            this.picLayout.setVisibility(0);
            this.sendOrderLayout.setVisibility(0);
        }
    }
}
