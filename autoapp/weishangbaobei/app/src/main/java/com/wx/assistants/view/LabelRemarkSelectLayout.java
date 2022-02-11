package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.activity.ObtainTagActivity;
import com.wx.assistants.adapter.LabelRemarkSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.view.TagCloudLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class LabelRemarkSelectLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public String allLabel = "";
    private String dndStr = "";
    private TagCloudLayout flowViewGroup;
    private String friendsMembers = "";
    /* access modifiers changed from: private */
    public String friendsTags = "";
    /* access modifiers changed from: private */
    public TextView jump_label;
    /* access modifiers changed from: private */
    public LinearLayout jump_label_layout;
    /* access modifiers changed from: private */
    public String keyAll = "default";
    /* access modifiers changed from: private */
    public String keyAllLabel = "label";
    /* access modifiers changed from: private */
    public String keyPart = "default";
    /* access modifiers changed from: private */
    public String keyPartLabel = "default";
    /* access modifiers changed from: private */
    public String keyPartLabelMember = "default";
    /* access modifiers changed from: private */
    public String keyShield = "default";
    /* access modifiers changed from: private */
    public String keyShieldLabel = "default";
    /* access modifiers changed from: private */
    public String keyShieldLabelMember = "default";
    /* access modifiers changed from: private */
    public String keyUnLabel = "default";
    /* access modifiers changed from: private */
    public String keyUnLabelLabel = "label";
    /* access modifiers changed from: private */
    public String keyUnRemark = "default";
    /* access modifiers changed from: private */
    public String keyUnRemarkLabel = "label";
    /* access modifiers changed from: private */
    public FriendSendTypeListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public String partLabel = "";
    /* access modifiers changed from: private */
    public String partLabelMember = "";
    /* access modifiers changed from: private */
    public int sendTypeMode = 0;
    /* access modifiers changed from: private */
    public String shieldLabel = "";
    /* access modifiers changed from: private */
    public String shieldLabelMember = "";
    /* access modifiers changed from: private */
    public int startIndex = 1;
    /* access modifiers changed from: private */
    public int startIndexAll = 1;
    /* access modifiers changed from: private */
    public int startIndexPart = 1;
    /* access modifiers changed from: private */
    public int startIndexShield = 1;
    /* access modifiers changed from: private */
    public int startIndexUnLabel = 1;
    /* access modifiers changed from: private */
    public int startIndexUnRemark = 1;
    /* access modifiers changed from: private */
    public TextView start_pull;
    /* access modifiers changed from: private */
    public LinearLayout start_pull_layout;
    private LabelRemarkSelectAdapter tagSelectAdapter;
    /* access modifiers changed from: private */
    public String unLabelLabel = "";
    /* access modifiers changed from: private */
    public String unRemarkLabel = "";

    public interface FriendSendTypeListener {
        void sendLabels(String str);

        void sendMembers(LinkedHashSet<String> linkedHashSet);

        void sendStartIndex(int i);

        void sendType(int i);
    }

    public void setFriendSendTypeListener(FriendSendTypeListener friendSendTypeListener) {
        if (friendSendTypeListener != null) {
            this.listener = friendSendTypeListener;
        }
    }

    public void setSendTypeMode(int i) {
        this.sendTypeMode = i;
    }

    public LabelRemarkSelectLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public LabelRemarkSelectLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public LabelRemarkSelectLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public String getPartLabString() {
        return this.partLabel;
    }

    public void setSendMember(LinkedHashSet<String> linkedHashSet) {
        String str;
        if (linkedHashSet == null || linkedHashSet.size() <= 0) {
            str = "";
        } else {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                i++;
                if (i != linkedHashSet.size()) {
                    sb.append(str2 + ";");
                } else {
                    sb.append(str2);
                }
            }
            str = sb.toString();
        }
        if (this.sendTypeMode == 3) {
            this.partLabelMember = str;
        } else if (this.sendTypeMode == 4) {
            this.shieldLabelMember = str;
        }
        if (this.sendTypeMode == 3) {
            SPUtils.put(MyApplication.getConText(), this.keyPartLabelMember, str);
        } else if (this.sendTypeMode == 4) {
            SPUtils.put(MyApplication.getConText(), this.keyShieldLabelMember, str);
        }
    }

    public void setSendStr(String str) {
        this.friendsTags = str;
        if (str == null || "".equals(str)) {
            this.jump_label.setText("点我设置好友标签");
            if (this.sendTypeMode == 3) {
                SPUtils.put(MyApplication.getConText(), this.keyPartLabel, "");
                SPUtils.put(MyApplication.getConText(), this.keyPartLabelMember, "");
            } else if (this.sendTypeMode == 4) {
                SPUtils.put(MyApplication.getConText(), this.keyShieldLabel, "");
                SPUtils.put(MyApplication.getConText(), this.keyShieldLabelMember, "");
            }
            if (this.listener != null) {
                LogUtils.log("WS_FRIEND_MODE.0");
                this.listener.sendMembers(new LinkedHashSet());
            }
        } else {
            this.jump_label.setText(str);
        }
        if (this.sendTypeMode == 0) {
            this.allLabel = str;
        } else if (this.sendTypeMode == 1) {
            this.unLabelLabel = str;
        } else if (this.sendTypeMode == 2) {
            this.unRemarkLabel = str;
        } else if (this.sendTypeMode == 3) {
            this.partLabel = str;
        } else if (this.sendTypeMode == 4) {
            this.shieldLabel = str;
        }
        if (this.sendTypeMode == 0) {
            SPUtils.put(MyApplication.getConText(), this.keyAllLabel, str);
        } else if (this.sendTypeMode == 1) {
            SPUtils.put(MyApplication.getConText(), this.keyUnLabelLabel, str);
        } else if (this.sendTypeMode == 2) {
            SPUtils.put(MyApplication.getConText(), this.keyUnRemarkLabel, str);
        } else if (this.sendTypeMode == 3) {
            SPUtils.put(MyApplication.getConText(), this.keyPartLabel, str);
        } else if (this.sendTypeMode == 4) {
            SPUtils.put(MyApplication.getConText(), this.keyShieldLabel, str);
        }
    }

    public void initSendIndexStrAll(String str, int i) {
        this.keyAll = str;
        this.startIndexAll = i;
        if (this.sendTypeMode == 0) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendIndexStrUnLabel(String str, int i) {
        this.keyUnLabel = str;
        this.startIndexUnLabel = i;
        if (this.sendTypeMode == 1) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendIndexStrUnRemark(String str, int i) {
        this.keyUnRemark = str;
        this.startIndexUnRemark = i;
        if (this.sendTypeMode == 2) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendIndexStrPart(String str, int i) {
        this.keyPart = str;
        this.startIndexPart = i;
        if (this.sendTypeMode == 3) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendIndexStrShield(String str, int i) {
        this.keyShield = str;
        this.startIndexShield = i;
        if (this.sendTypeMode == 4) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendLabelStrAll(String str, String str2) {
        this.keyAllLabel = str;
        this.allLabel = str2;
        if (this.sendTypeMode == 0) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    public void initSendLabelStrUnLabel(String str, String str2) {
        this.keyUnLabelLabel = str;
        this.unLabelLabel = str2;
        if (this.sendTypeMode == 1) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    public void initSendLabelStrUnRemark(String str, String str2) {
        this.keyUnRemarkLabel = str;
        this.unRemarkLabel = str2;
        if (this.sendTypeMode == 2) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    public void initSendLabelStrPart(String str, String str2) {
        this.keyPartLabel = str;
        this.partLabel = str2;
        if (this.sendTypeMode == 3) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    public void initSendLabelStrShield(String str, String str2) {
        this.keyShieldLabel = str;
        this.shieldLabel = str2;
        if (this.sendTypeMode == 4) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    public void initSendLabelMemberPart(String str, String str2) {
        this.keyPartLabelMember = str;
        this.partLabelMember = str2;
        if (this.sendTypeMode == 3) {
            this.friendsMembers = str2;
            if (this.friendsMembers != null && !"".equals(this.friendsMembers)) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                if (this.friendsMembers.contains(";")) {
                    String[] split = this.friendsMembers.split(";");
                    for (String add : split) {
                        linkedHashSet.add(add);
                    }
                } else {
                    linkedHashSet.add(this.friendsMembers);
                }
                if (this.listener != null) {
                    LogUtils.log("WS_FRIEND_MODE.1");
                    this.listener.sendMembers(linkedHashSet);
                }
            }
        }
    }

    public void initSendLabelMemberShield(String str, String str2) {
        this.keyShieldLabelMember = str;
        this.shieldLabelMember = str2;
        if (this.sendTypeMode == 4) {
            this.friendsMembers = str2;
            if (this.friendsMembers != null && !"".equals(this.friendsMembers)) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                if (this.friendsMembers.contains(";")) {
                    String[] split = this.friendsMembers.split(";");
                    for (String add : split) {
                        linkedHashSet.add(add);
                    }
                } else {
                    linkedHashSet.add(this.friendsMembers);
                }
                if (this.listener != null) {
                    LogUtils.log("WS_FRIEND_MODE.2");
                    this.listener.sendMembers(linkedHashSet);
                }
            }
        }
    }

    private void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427763, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.flowViewGroup = (TagCloudLayout) findViewById(2131296681);
        ArrayList arrayList = new ArrayList();
        arrayList.add("全部好友");
        arrayList.add("没标签的好友");
        arrayList.add("没备注的好友");
        arrayList.add("部分好友");
        arrayList.add("屏蔽部分好友以外的好友");
        this.tagSelectAdapter = new LabelRemarkSelectAdapter(this.mContext, arrayList);
        this.flowViewGroup.setAdapter(this.tagSelectAdapter, 0);
        this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                int i2 = 0;
                if (i == 0) {
                    LabelRemarkSelectLayout.this.start_pull_layout.setVisibility(0);
                    LabelRemarkSelectLayout.this.jump_label_layout.setVisibility(8);
                    try {
                        int unused = LabelRemarkSelectLayout.this.startIndexAll = ((Integer) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyAll, 1)).intValue();
                    } catch (Exception unused2) {
                        int unused3 = LabelRemarkSelectLayout.this.startIndexAll = 1;
                    }
                    TextView access$400 = LabelRemarkSelectLayout.this.start_pull;
                    access$400.setText("从第" + LabelRemarkSelectLayout.this.startIndexAll + "个好友开始");
                    FontUtils.changeFontColor(LabelRemarkSelectLayout.this.mContext, LabelRemarkSelectLayout.this.start_pull);
                    String unused4 = LabelRemarkSelectLayout.this.allLabel = (String) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyAllLabel, "");
                    if (LabelRemarkSelectLayout.this.allLabel == null || "".equals(LabelRemarkSelectLayout.this.allLabel)) {
                        LabelRemarkSelectLayout.this.jump_label.setText("点我设置好友标签");
                        SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyAllLabel, "");
                    } else {
                        LabelRemarkSelectLayout.this.jump_label.setText(LabelRemarkSelectLayout.this.allLabel);
                    }
                    String unused5 = LabelRemarkSelectLayout.this.friendsTags = LabelRemarkSelectLayout.this.allLabel;
                    if (LabelRemarkSelectLayout.this.listener != null) {
                        int unused6 = LabelRemarkSelectLayout.this.sendTypeMode = 0;
                        LabelRemarkSelectLayout.this.listener.sendType(LabelRemarkSelectLayout.this.sendTypeMode);
                        LabelRemarkSelectLayout.this.listener.sendStartIndex(LabelRemarkSelectLayout.this.startIndexAll);
                        LogUtils.log("WS_FRIEND_MODE.3");
                        LabelRemarkSelectLayout.this.listener.sendMembers(new LinkedHashSet());
                        LabelRemarkSelectLayout.this.listener.sendLabels("");
                    }
                } else if (i == 1) {
                    LabelRemarkSelectLayout.this.start_pull_layout.setVisibility(0);
                    LabelRemarkSelectLayout.this.jump_label_layout.setVisibility(8);
                    try {
                        int unused7 = LabelRemarkSelectLayout.this.startIndexUnLabel = ((Integer) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnLabel, 1)).intValue();
                    } catch (Exception unused8) {
                        int unused9 = LabelRemarkSelectLayout.this.startIndexUnLabel = 1;
                    }
                    TextView access$4002 = LabelRemarkSelectLayout.this.start_pull;
                    access$4002.setText("从第" + LabelRemarkSelectLayout.this.startIndexUnLabel + "个好友开始");
                    FontUtils.changeFontColor(LabelRemarkSelectLayout.this.mContext, LabelRemarkSelectLayout.this.start_pull);
                    String unused10 = LabelRemarkSelectLayout.this.unLabelLabel = (String) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnLabelLabel, "");
                    if (LabelRemarkSelectLayout.this.unLabelLabel == null || "".equals(LabelRemarkSelectLayout.this.unLabelLabel)) {
                        LabelRemarkSelectLayout.this.jump_label.setText("点我设置好友标签");
                        SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnLabelLabel, "");
                    } else {
                        LabelRemarkSelectLayout.this.jump_label.setText(LabelRemarkSelectLayout.this.unLabelLabel);
                    }
                    String unused11 = LabelRemarkSelectLayout.this.friendsTags = LabelRemarkSelectLayout.this.unLabelLabel;
                    if (LabelRemarkSelectLayout.this.listener != null) {
                        int unused12 = LabelRemarkSelectLayout.this.sendTypeMode = 1;
                        LabelRemarkSelectLayout.this.listener.sendType(LabelRemarkSelectLayout.this.sendTypeMode);
                        LabelRemarkSelectLayout.this.listener.sendStartIndex(LabelRemarkSelectLayout.this.startIndexUnLabel);
                        LogUtils.log("WS_FRIEND_MODE.3");
                        LabelRemarkSelectLayout.this.listener.sendMembers(new LinkedHashSet());
                        LabelRemarkSelectLayout.this.listener.sendLabels("");
                    }
                } else if (i == 2) {
                    LabelRemarkSelectLayout.this.start_pull_layout.setVisibility(0);
                    LabelRemarkSelectLayout.this.jump_label_layout.setVisibility(8);
                    try {
                        int unused13 = LabelRemarkSelectLayout.this.startIndexUnRemark = ((Integer) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnRemark, 1)).intValue();
                    } catch (Exception unused14) {
                        int unused15 = LabelRemarkSelectLayout.this.startIndexUnRemark = 1;
                    }
                    TextView access$4003 = LabelRemarkSelectLayout.this.start_pull;
                    access$4003.setText("从第" + LabelRemarkSelectLayout.this.startIndexUnRemark + "个好友开始");
                    FontUtils.changeFontColor(LabelRemarkSelectLayout.this.mContext, LabelRemarkSelectLayout.this.start_pull);
                    String unused16 = LabelRemarkSelectLayout.this.unRemarkLabel = (String) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnRemarkLabel, "");
                    if (LabelRemarkSelectLayout.this.unRemarkLabel == null || "".equals(LabelRemarkSelectLayout.this.unRemarkLabel)) {
                        LabelRemarkSelectLayout.this.jump_label.setText("点我设置好友标签");
                        SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnRemarkLabel, "");
                    } else {
                        LabelRemarkSelectLayout.this.jump_label.setText(LabelRemarkSelectLayout.this.unRemarkLabel);
                    }
                    String unused17 = LabelRemarkSelectLayout.this.friendsTags = LabelRemarkSelectLayout.this.unRemarkLabel;
                    if (LabelRemarkSelectLayout.this.listener != null) {
                        int unused18 = LabelRemarkSelectLayout.this.sendTypeMode = 2;
                        LabelRemarkSelectLayout.this.listener.sendType(LabelRemarkSelectLayout.this.sendTypeMode);
                        LabelRemarkSelectLayout.this.listener.sendStartIndex(LabelRemarkSelectLayout.this.startIndexUnRemark);
                        LogUtils.log("WS_FRIEND_MODE.3");
                        LabelRemarkSelectLayout.this.listener.sendMembers(new LinkedHashSet());
                        LabelRemarkSelectLayout.this.listener.sendLabels("");
                    }
                } else if (i == 3) {
                    LabelRemarkSelectLayout.this.start_pull_layout.setVisibility(8);
                    LabelRemarkSelectLayout.this.jump_label_layout.setVisibility(0);
                    try {
                        int unused19 = LabelRemarkSelectLayout.this.startIndexPart = ((Integer) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyPart, 1)).intValue();
                    } catch (Exception unused20) {
                        int unused21 = LabelRemarkSelectLayout.this.startIndexPart = 1;
                    }
                    TextView access$4004 = LabelRemarkSelectLayout.this.start_pull;
                    access$4004.setText("从第" + LabelRemarkSelectLayout.this.startIndexPart + "个好友开始");
                    FontUtils.changeFontColor(LabelRemarkSelectLayout.this.mContext, LabelRemarkSelectLayout.this.start_pull);
                    String unused22 = LabelRemarkSelectLayout.this.partLabel = (String) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyPartLabel, "");
                    if (LabelRemarkSelectLayout.this.partLabel == null || "".equals(LabelRemarkSelectLayout.this.partLabel)) {
                        LabelRemarkSelectLayout.this.jump_label.setText("点我设置好友标签");
                        SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyPartLabel, "");
                        SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyPartLabelMember, "");
                    } else {
                        LabelRemarkSelectLayout.this.jump_label.setText(LabelRemarkSelectLayout.this.partLabel);
                    }
                    String unused23 = LabelRemarkSelectLayout.this.friendsTags = LabelRemarkSelectLayout.this.partLabel;
                    if (LabelRemarkSelectLayout.this.listener != null) {
                        int unused24 = LabelRemarkSelectLayout.this.sendTypeMode = 3;
                        LabelRemarkSelectLayout.this.listener.sendType(LabelRemarkSelectLayout.this.sendTypeMode);
                        LabelRemarkSelectLayout.this.listener.sendStartIndex(LabelRemarkSelectLayout.this.startIndexPart);
                        LabelRemarkSelectLayout.this.listener.sendLabels(LabelRemarkSelectLayout.this.partLabel);
                    }
                    String unused25 = LabelRemarkSelectLayout.this.partLabelMember = (String) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyPartLabelMember, "");
                    if (LabelRemarkSelectLayout.this.partLabelMember != null && !"".equals(LabelRemarkSelectLayout.this.partLabelMember)) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        if (LabelRemarkSelectLayout.this.partLabelMember.contains(";")) {
                            String[] split = LabelRemarkSelectLayout.this.partLabelMember.split(";");
                            while (i2 < split.length) {
                                linkedHashSet.add(split[i2]);
                                i2++;
                            }
                        } else {
                            linkedHashSet.add(LabelRemarkSelectLayout.this.partLabelMember);
                        }
                        if (LabelRemarkSelectLayout.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.4");
                            LabelRemarkSelectLayout.this.listener.sendMembers(linkedHashSet);
                        }
                    } else if (LabelRemarkSelectLayout.this.listener != null) {
                        LogUtils.log("WS_FRIEND_MODE.5");
                        LabelRemarkSelectLayout.this.listener.sendMembers(new LinkedHashSet());
                    }
                } else if (i == 4) {
                    LabelRemarkSelectLayout.this.start_pull_layout.setVisibility(0);
                    LabelRemarkSelectLayout.this.jump_label_layout.setVisibility(0);
                    try {
                        int unused26 = LabelRemarkSelectLayout.this.startIndexShield = ((Integer) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyShield, 1)).intValue();
                    } catch (Exception unused27) {
                        int unused28 = LabelRemarkSelectLayout.this.startIndexShield = 1;
                    }
                    TextView access$4005 = LabelRemarkSelectLayout.this.start_pull;
                    access$4005.setText("从第" + LabelRemarkSelectLayout.this.startIndexShield + "个好友开始");
                    FontUtils.changeFontColor(LabelRemarkSelectLayout.this.mContext, LabelRemarkSelectLayout.this.start_pull);
                    String unused29 = LabelRemarkSelectLayout.this.shieldLabel = (String) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyShieldLabel, "");
                    if (LabelRemarkSelectLayout.this.shieldLabel == null || "".equals(LabelRemarkSelectLayout.this.shieldLabel)) {
                        LabelRemarkSelectLayout.this.jump_label.setText("点我设置好友标签");
                        SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyShieldLabel, "");
                        SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyShieldLabelMember, "");
                    } else {
                        LabelRemarkSelectLayout.this.jump_label.setText(LabelRemarkSelectLayout.this.shieldLabel);
                    }
                    String unused30 = LabelRemarkSelectLayout.this.friendsTags = LabelRemarkSelectLayout.this.shieldLabel;
                    if (LabelRemarkSelectLayout.this.listener != null) {
                        int unused31 = LabelRemarkSelectLayout.this.sendTypeMode = 4;
                        LabelRemarkSelectLayout.this.listener.sendStartIndex(LabelRemarkSelectLayout.this.startIndexShield);
                        LabelRemarkSelectLayout.this.listener.sendType(LabelRemarkSelectLayout.this.sendTypeMode);
                        LabelRemarkSelectLayout.this.listener.sendLabels(LabelRemarkSelectLayout.this.shieldLabel);
                    }
                    String unused32 = LabelRemarkSelectLayout.this.shieldLabelMember = (String) SPUtils.get(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyShieldLabelMember, "");
                    if (LabelRemarkSelectLayout.this.shieldLabelMember != null && !"".equals(LabelRemarkSelectLayout.this.shieldLabelMember)) {
                        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                        if (LabelRemarkSelectLayout.this.shieldLabelMember.contains(";")) {
                            String[] split2 = LabelRemarkSelectLayout.this.shieldLabelMember.split(";");
                            while (i2 < split2.length) {
                                linkedHashSet2.add(split2[i2]);
                                i2++;
                            }
                        } else {
                            linkedHashSet2.add(LabelRemarkSelectLayout.this.shieldLabelMember);
                        }
                        if (LabelRemarkSelectLayout.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.6");
                            LabelRemarkSelectLayout.this.listener.sendMembers(linkedHashSet2);
                        }
                    } else if (LabelRemarkSelectLayout.this.listener != null) {
                        LogUtils.log("WS_FRIEND_MODE.7");
                        LabelRemarkSelectLayout.this.listener.sendMembers(new LinkedHashSet());
                    }
                }
            }
        });
        this.start_pull_layout = (LinearLayout) findViewById(2131297422);
        this.jump_label_layout = (LinearLayout) findViewById(2131296877);
        this.start_pull = (TextView) findViewById(2131297419);
        this.start_pull.setText("从第1个好友开始");
        FontUtils.changeFontColor(this.mContext, this.start_pull);
        this.jump_label = (TextView) findViewById(2131296873);
        this.jump_label.setText("点我可设置好友标签");
        this.start_pull_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = LabelRemarkSelectLayout.this.start_pull.getText().toString();
                    int unused = LabelRemarkSelectLayout.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception unused2) {
                }
                DialogUIUtils.dialogSetStartPoint(LabelRemarkSelectLayout.this.mContext, LabelRemarkSelectLayout.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = LabelRemarkSelectLayout.this.startIndex = i;
                        TextView access$400 = LabelRemarkSelectLayout.this.start_pull;
                        access$400.setText("从第" + i + "个好友开始");
                        FontUtils.changeFontColor(LabelRemarkSelectLayout.this.mContext, LabelRemarkSelectLayout.this.start_pull);
                        if (LabelRemarkSelectLayout.this.sendTypeMode == 0) {
                            int unused2 = LabelRemarkSelectLayout.this.startIndexAll = LabelRemarkSelectLayout.this.startIndex;
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 1) {
                            int unused3 = LabelRemarkSelectLayout.this.startIndexUnLabel = LabelRemarkSelectLayout.this.startIndex;
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 2) {
                            int unused4 = LabelRemarkSelectLayout.this.startIndexUnRemark = LabelRemarkSelectLayout.this.startIndex;
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 3) {
                            int unused5 = LabelRemarkSelectLayout.this.startIndexPart = LabelRemarkSelectLayout.this.startIndex;
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 4) {
                            int unused6 = LabelRemarkSelectLayout.this.startIndexShield = LabelRemarkSelectLayout.this.startIndex;
                        }
                        if (LabelRemarkSelectLayout.this.sendTypeMode == 0) {
                            SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyAll, Integer.valueOf(LabelRemarkSelectLayout.this.startIndex));
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 1) {
                            SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnLabel, Integer.valueOf(LabelRemarkSelectLayout.this.startIndex));
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 2) {
                            SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyUnRemark, Integer.valueOf(LabelRemarkSelectLayout.this.startIndex));
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 3) {
                            SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyPart, Integer.valueOf(LabelRemarkSelectLayout.this.startIndex));
                        } else if (LabelRemarkSelectLayout.this.sendTypeMode == 4) {
                            SPUtils.put(MyApplication.getConText(), LabelRemarkSelectLayout.this.keyShield, Integer.valueOf(LabelRemarkSelectLayout.this.startIndex));
                        }
                        if (LabelRemarkSelectLayout.this.listener != null) {
                            LabelRemarkSelectLayout.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.jump_label_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LabelRemarkSelectLayout.this.mContext, ObtainTagActivity.class);
                intent.putExtra("selects", LabelRemarkSelectLayout.this.friendsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
    }
}
