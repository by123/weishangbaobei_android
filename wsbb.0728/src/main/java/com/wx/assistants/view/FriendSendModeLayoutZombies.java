package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wx.assistants.activity.ObtainTagActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class FriendSendModeLayoutZombies extends LinearLayout {
    /* access modifiers changed from: private */
    public String allLabel = "";
    private RadioGroup circulateRG;
    private String dndStr = "";
    private String friendsMembers = "";
    /* access modifiers changed from: private */
    public String friendsTags = "";
    private boolean isSaveKey2All = false;
    private boolean isSaveKey2Part = false;
    private boolean isSaveKey2Shield = false;
    private boolean isSaveKey3Part = false;
    private boolean isSaveKey3Shield = false;
    /* access modifiers changed from: private */
    public boolean isSaveKeyAll = false;
    /* access modifiers changed from: private */
    public boolean isSaveKeyPart = false;
    /* access modifiers changed from: private */
    public boolean isSaveKeyShield = false;
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
    public TextView start_pull;
    /* access modifiers changed from: private */
    public LinearLayout start_pull_layout;
    private TextView title;

    public interface FriendSendTypeListener {
        void sendMembers(LinkedHashSet<String> linkedHashSet);

        void sendStartIndex(int i);

        void sendType(int i);
    }

    public FriendSendModeLayoutZombies(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendSendModeLayoutZombies(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public FriendSendModeLayoutZombies(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427753, this, true);
    }

    public String getPartLabString() {
        return this.partLabel;
    }

    public void initSendIndexStrAll(boolean z, String str, int i) {
        this.isSaveKeyAll = z;
        this.keyAll = str;
        this.startIndexAll = i;
        if (this.sendTypeMode == 0) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始检测");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始检测");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendIndexStrPart(boolean z, String str, int i) {
        this.isSaveKeyPart = z;
        this.keyPart = str;
        this.startIndexPart = i;
        if (this.sendTypeMode == 1) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始检测");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始检测");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendIndexStrShield(boolean z, String str, int i) {
        this.isSaveKeyShield = z;
        this.keyShield = str;
        this.startIndexShield = i;
        if (this.sendTypeMode == 2) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个好友开始检测");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始检测");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendLabelMemberPart(boolean z, String str, String str2) {
        this.isSaveKey3Part = z;
        this.keyPartLabelMember = str;
        this.partLabelMember = str2;
        if (this.sendTypeMode == 1) {
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

    public void initSendLabelMemberShield(boolean z, String str, String str2) {
        this.isSaveKey3Shield = z;
        this.keyShieldLabelMember = str;
        this.shieldLabelMember = str2;
        if (this.sendTypeMode == 2) {
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

    public void initSendLabelStrAll(boolean z, String str, String str2) {
        this.isSaveKey2All = z;
        this.keyAllLabel = str;
        this.allLabel = str2;
        if (this.sendTypeMode == 0) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置要检测的好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    public void initSendLabelStrPart(boolean z, String str, String str2) {
        this.isSaveKey2Part = z;
        this.keyPartLabel = str;
        this.partLabel = str2;
        if (this.sendTypeMode == 1) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置要检测的好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    public void initSendLabelStrShield(boolean z, String str, String str2) {
        this.isSaveKey2Shield = z;
        this.keyShieldLabel = str;
        this.shieldLabel = str2;
        if (this.sendTypeMode == 2) {
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置要屏蔽的好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.title = (TextView) findViewById(2131297514);
        this.title.setText("检测哪些人");
        this.start_pull_layout = (LinearLayout) findViewById(2131297422);
        this.jump_label_layout = (LinearLayout) findViewById(2131296877);
        this.start_pull = (TextView) findViewById(2131297419);
        this.start_pull.setText("从第1个好友开始检测");
        FontUtils.changeFontColor(this.mContext, this.start_pull);
        this.jump_label = (TextView) findViewById(2131296873);
        this.jump_label.setText("点我设置要检测的好友标签");
        this.start_pull_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = FriendSendModeLayoutZombies.this.start_pull.getText().toString();
                    int unused = FriendSendModeLayoutZombies.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception e) {
                }
                DialogUIUtils.dialogSetStartPoint(FriendSendModeLayoutZombies.this.mContext, FriendSendModeLayoutZombies.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = FriendSendModeLayoutZombies.this.startIndex = i;
                        TextView access$000 = FriendSendModeLayoutZombies.this.start_pull;
                        access$000.setText("从第" + i + "个好友开始检测");
                        FontUtils.changeFontColor(FriendSendModeLayoutZombies.this.mContext, FriendSendModeLayoutZombies.this.start_pull);
                        if (FriendSendModeLayoutZombies.this.sendTypeMode == 0) {
                            int unused2 = FriendSendModeLayoutZombies.this.startIndexAll = FriendSendModeLayoutZombies.this.startIndex;
                        } else if (FriendSendModeLayoutZombies.this.sendTypeMode == 1) {
                            int unused3 = FriendSendModeLayoutZombies.this.startIndexPart = FriendSendModeLayoutZombies.this.startIndex;
                        } else if (FriendSendModeLayoutZombies.this.sendTypeMode == 2) {
                            int unused4 = FriendSendModeLayoutZombies.this.startIndexShield = FriendSendModeLayoutZombies.this.startIndex;
                        }
                        if (FriendSendModeLayoutZombies.this.isSaveKeyAll && FriendSendModeLayoutZombies.this.sendTypeMode == 0) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyAll, Integer.valueOf(FriendSendModeLayoutZombies.this.startIndex));
                        } else if (FriendSendModeLayoutZombies.this.isSaveKeyPart && FriendSendModeLayoutZombies.this.sendTypeMode == 1) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyPart, Integer.valueOf(FriendSendModeLayoutZombies.this.startIndex));
                        } else if (FriendSendModeLayoutZombies.this.isSaveKeyShield && FriendSendModeLayoutZombies.this.sendTypeMode == 2) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyShield, Integer.valueOf(FriendSendModeLayoutZombies.this.startIndex));
                        }
                        if (FriendSendModeLayoutZombies.this.listener != null) {
                            FriendSendModeLayoutZombies.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.jump_label_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FriendSendModeLayoutZombies.this.mContext, ObtainTagActivity.class);
                intent.putExtra("selects", FriendSendModeLayoutZombies.this.friendsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        FriendSendModeLayoutZombies.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayoutZombies.this.jump_label_layout.setVisibility(8);
                        try {
                            int unused = FriendSendModeLayoutZombies.this.startIndexAll = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyAll, 1)).intValue();
                        } catch (Exception e) {
                            int unused2 = FriendSendModeLayoutZombies.this.startIndexAll = 1;
                        }
                        FriendSendModeLayoutZombies.this.start_pull.setText("从第" + FriendSendModeLayoutZombies.this.startIndexAll + "个好友开始检测");
                        FontUtils.changeFontColor(FriendSendModeLayoutZombies.this.mContext, FriendSendModeLayoutZombies.this.start_pull);
                        String unused3 = FriendSendModeLayoutZombies.this.allLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyAllLabel, "");
                        if (FriendSendModeLayoutZombies.this.allLabel == null || "".equals(FriendSendModeLayoutZombies.this.allLabel)) {
                            FriendSendModeLayoutZombies.this.jump_label.setText("点我设置要检测的好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyAllLabel, "");
                        } else {
                            FriendSendModeLayoutZombies.this.jump_label.setText(FriendSendModeLayoutZombies.this.allLabel);
                        }
                        String unused4 = FriendSendModeLayoutZombies.this.friendsTags = FriendSendModeLayoutZombies.this.allLabel;
                        if (FriendSendModeLayoutZombies.this.listener != null) {
                            int unused5 = FriendSendModeLayoutZombies.this.sendTypeMode = 0;
                            FriendSendModeLayoutZombies.this.listener.sendType(FriendSendModeLayoutZombies.this.sendTypeMode);
                            FriendSendModeLayoutZombies.this.listener.sendStartIndex(FriendSendModeLayoutZombies.this.startIndexAll);
                            LogUtils.log("WS_FRIEND_MODE.3");
                            FriendSendModeLayoutZombies.this.listener.sendMembers(new LinkedHashSet());
                            return;
                        }
                        return;
                    case 2131296497:
                        FriendSendModeLayoutZombies.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayoutZombies.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused6 = FriendSendModeLayoutZombies.this.startIndexPart = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyPart, 1)).intValue();
                        } catch (Exception e2) {
                            int unused7 = FriendSendModeLayoutZombies.this.startIndexPart = 1;
                        }
                        FriendSendModeLayoutZombies.this.start_pull.setText("从第" + FriendSendModeLayoutZombies.this.startIndexPart + "个好友开始检测");
                        FontUtils.changeFontColor(FriendSendModeLayoutZombies.this.mContext, FriendSendModeLayoutZombies.this.start_pull);
                        String unused8 = FriendSendModeLayoutZombies.this.partLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyPartLabel, "");
                        if (FriendSendModeLayoutZombies.this.partLabel == null || "".equals(FriendSendModeLayoutZombies.this.partLabel)) {
                            FriendSendModeLayoutZombies.this.jump_label.setText("点我设置要检测的好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyPartLabel, "");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyPartLabelMember, "");
                        } else {
                            FriendSendModeLayoutZombies.this.jump_label.setText(FriendSendModeLayoutZombies.this.partLabel);
                        }
                        String unused9 = FriendSendModeLayoutZombies.this.friendsTags = FriendSendModeLayoutZombies.this.partLabel;
                        if (FriendSendModeLayoutZombies.this.listener != null) {
                            int unused10 = FriendSendModeLayoutZombies.this.sendTypeMode = 1;
                            FriendSendModeLayoutZombies.this.listener.sendType(FriendSendModeLayoutZombies.this.sendTypeMode);
                            FriendSendModeLayoutZombies.this.listener.sendStartIndex(FriendSendModeLayoutZombies.this.startIndexPart);
                        }
                        String unused11 = FriendSendModeLayoutZombies.this.partLabelMember = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyPartLabelMember, "");
                        if (FriendSendModeLayoutZombies.this.partLabelMember != null && !"".equals(FriendSendModeLayoutZombies.this.partLabelMember)) {
                            LinkedHashSet linkedHashSet = new LinkedHashSet();
                            if (FriendSendModeLayoutZombies.this.partLabelMember.contains(";")) {
                                String[] split = FriendSendModeLayoutZombies.this.partLabelMember.split(";");
                                for (String add : split) {
                                    linkedHashSet.add(add);
                                }
                            } else {
                                linkedHashSet.add(FriendSendModeLayoutZombies.this.partLabelMember);
                            }
                            if (FriendSendModeLayoutZombies.this.listener != null) {
                                LogUtils.log("WS_FRIEND_MODE.4");
                                FriendSendModeLayoutZombies.this.listener.sendMembers(linkedHashSet);
                                return;
                            }
                            return;
                        } else if (FriendSendModeLayoutZombies.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.5");
                            FriendSendModeLayoutZombies.this.listener.sendMembers(new LinkedHashSet());
                            return;
                        } else {
                            return;
                        }
                    case 2131296498:
                        FriendSendModeLayoutZombies.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayoutZombies.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused12 = FriendSendModeLayoutZombies.this.startIndexShield = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyShield, 1)).intValue();
                        } catch (Exception e3) {
                            int unused13 = FriendSendModeLayoutZombies.this.startIndexShield = 1;
                        }
                        FriendSendModeLayoutZombies.this.start_pull.setText("从第" + FriendSendModeLayoutZombies.this.startIndexShield + "个好友开始检测");
                        FontUtils.changeFontColor(FriendSendModeLayoutZombies.this.mContext, FriendSendModeLayoutZombies.this.start_pull);
                        String unused14 = FriendSendModeLayoutZombies.this.shieldLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyShieldLabel, "");
                        if (FriendSendModeLayoutZombies.this.shieldLabel == null || "".equals(FriendSendModeLayoutZombies.this.shieldLabel)) {
                            FriendSendModeLayoutZombies.this.jump_label.setText("点我设置要屏蔽的好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyShieldLabel, "");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyShieldLabelMember, "");
                        } else {
                            FriendSendModeLayoutZombies.this.jump_label.setText(FriendSendModeLayoutZombies.this.shieldLabel);
                        }
                        String unused15 = FriendSendModeLayoutZombies.this.friendsTags = FriendSendModeLayoutZombies.this.shieldLabel;
                        if (FriendSendModeLayoutZombies.this.listener != null) {
                            int unused16 = FriendSendModeLayoutZombies.this.sendTypeMode = 2;
                            FriendSendModeLayoutZombies.this.listener.sendStartIndex(FriendSendModeLayoutZombies.this.startIndexShield);
                            FriendSendModeLayoutZombies.this.listener.sendType(FriendSendModeLayoutZombies.this.sendTypeMode);
                        }
                        String unused17 = FriendSendModeLayoutZombies.this.shieldLabelMember = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutZombies.this.keyShieldLabelMember, "");
                        if (FriendSendModeLayoutZombies.this.shieldLabelMember != null && !"".equals(FriendSendModeLayoutZombies.this.shieldLabelMember)) {
                            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                            if (FriendSendModeLayoutZombies.this.shieldLabelMember.contains(";")) {
                                String[] split2 = FriendSendModeLayoutZombies.this.shieldLabelMember.split(";");
                                for (String add2 : split2) {
                                    linkedHashSet2.add(add2);
                                }
                            } else {
                                linkedHashSet2.add(FriendSendModeLayoutZombies.this.shieldLabelMember);
                            }
                            if (FriendSendModeLayoutZombies.this.listener != null) {
                                LogUtils.log("WS_FRIEND_MODE.6");
                                FriendSendModeLayoutZombies.this.listener.sendMembers(linkedHashSet2);
                                return;
                            }
                            return;
                        } else if (FriendSendModeLayoutZombies.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.7");
                            FriendSendModeLayoutZombies.this.listener.sendMembers(new LinkedHashSet());
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        });
    }

    public void setFriendSendTypeListener(FriendSendTypeListener friendSendTypeListener) {
        if (friendSendTypeListener != null) {
            this.listener = friendSendTypeListener;
        }
    }

    public void setSendMember(LinkedHashSet<String> linkedHashSet) {
        String str;
        if (linkedHashSet == null || linkedHashSet.size() <= 0) {
            str = "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator it = linkedHashSet.iterator();
            int i = 0;
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
        if (this.sendTypeMode == 1) {
            this.partLabelMember = str;
        } else if (this.sendTypeMode == 2) {
            this.shieldLabelMember = str;
        }
        if (this.isSaveKey3Part && this.sendTypeMode == 1) {
            SPUtils.put(MyApplication.getConText(), this.keyPartLabelMember, str);
        } else if (this.isSaveKey3Shield && this.sendTypeMode == 2) {
            SPUtils.put(MyApplication.getConText(), this.keyShieldLabelMember, str);
        }
    }

    public void setSendStr(String str) {
        this.friendsTags = str;
        if (str == null || "".equals(str)) {
            this.jump_label.setText("点我设置要检测的好友标签");
            if (this.sendTypeMode == 1) {
                SPUtils.put(MyApplication.getConText(), this.keyPartLabel, "");
                SPUtils.put(MyApplication.getConText(), this.keyPartLabelMember, "");
            } else if (this.sendTypeMode == 2) {
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
            this.partLabel = str;
        } else if (this.sendTypeMode == 2) {
            this.shieldLabel = str;
        }
        if (this.isSaveKey2All && this.sendTypeMode == 0) {
            SPUtils.put(MyApplication.getConText(), this.keyAllLabel, str);
        } else if (this.isSaveKey2Part && this.sendTypeMode == 1) {
            SPUtils.put(MyApplication.getConText(), this.keyPartLabel, str);
        } else if (this.isSaveKey2Shield && this.sendTypeMode == 2) {
            SPUtils.put(MyApplication.getConText(), this.keyShieldLabel, str);
        }
    }

    public void setSendTypeMode(int i) {
        this.sendTypeMode = i;
    }
}
