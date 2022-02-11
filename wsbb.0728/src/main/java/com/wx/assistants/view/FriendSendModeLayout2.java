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
import com.wx.assistants.activity.ObtainSingleTagActivity;
import com.wx.assistants.activity.ObtainTagActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class FriendSendModeLayout2 extends LinearLayout {
    /* access modifiers changed from: private */
    public String allLabel = "";
    private RadioGroup circulateRG;
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

    public interface FriendSendTypeListener {
        void sendMembers(LinkedHashSet<String> linkedHashSet);

        void sendStartIndex(int i);

        void sendTag(LinkedHashSet<String> linkedHashSet);

        void sendType(int i);
    }

    public FriendSendModeLayout2(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendSendModeLayout2(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public FriendSendModeLayout2(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427754, this, true);
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
                textView.setText("从第" + this.startIndex + "个好友开始拉人进群");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始拉人进群");
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
                textView.setText("从第" + this.startIndex + "个好友开始拉人进群");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始拉人进群");
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
                textView.setText("从第" + this.startIndex + "个好友开始拉人进群");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个好友开始拉人进群");
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
                this.jump_label.setText("点我设置要拉的好友标签");
                return;
            }
            this.jump_label.setText(this.friendsTags);
            if (this.listener != null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                linkedHashSet.add(this.friendsTags);
                this.listener.sendTag(linkedHashSet);
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
                this.jump_label.setText("点我设置要拉的好友标签");
                return;
            }
            this.jump_label.setText(this.friendsTags);
            if (this.listener != null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                linkedHashSet.add(this.friendsTags);
                this.listener.sendTag(linkedHashSet);
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
                return;
            }
            this.jump_label.setText(this.friendsTags);
            if (this.listener != null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                if (this.friendsTags.contains(";")) {
                    String[] split = this.friendsTags.split(";");
                    for (String add : split) {
                        linkedHashSet.add(add);
                    }
                } else {
                    linkedHashSet.add(this.friendsTags);
                }
                this.listener.sendTag(linkedHashSet);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.start_pull_layout = (LinearLayout) findViewById(2131297422);
        this.jump_label_layout = (LinearLayout) findViewById(2131296877);
        this.start_pull = (TextView) findViewById(2131297419);
        this.start_pull.setText("从第1个微信好友开始");
        FontUtils.changeFontColor(this.mContext, this.start_pull);
        this.jump_label = (TextView) findViewById(2131296873);
        this.jump_label.setText("点我可设置要拉的好友标签");
        this.start_pull_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = FriendSendModeLayout2.this.start_pull.getText().toString();
                    int unused = FriendSendModeLayout2.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception e) {
                }
                DialogUIUtils.dialogSetStartPoint(FriendSendModeLayout2.this.mContext, FriendSendModeLayout2.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = FriendSendModeLayout2.this.startIndex = i;
                        TextView access$000 = FriendSendModeLayout2.this.start_pull;
                        access$000.setText("从第" + i + "个好友开始拉人进群");
                        FontUtils.changeFontColor(FriendSendModeLayout2.this.mContext, FriendSendModeLayout2.this.start_pull);
                        if (FriendSendModeLayout2.this.sendTypeMode == 0) {
                            int unused2 = FriendSendModeLayout2.this.startIndexAll = FriendSendModeLayout2.this.startIndex;
                        } else if (FriendSendModeLayout2.this.sendTypeMode == 1) {
                            int unused3 = FriendSendModeLayout2.this.startIndexPart = FriendSendModeLayout2.this.startIndex;
                        } else if (FriendSendModeLayout2.this.sendTypeMode == 2) {
                            int unused4 = FriendSendModeLayout2.this.startIndexShield = FriendSendModeLayout2.this.startIndex;
                        }
                        if (FriendSendModeLayout2.this.isSaveKeyAll && FriendSendModeLayout2.this.sendTypeMode == 0) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyAll, Integer.valueOf(FriendSendModeLayout2.this.startIndex));
                        } else if (FriendSendModeLayout2.this.isSaveKeyPart && FriendSendModeLayout2.this.sendTypeMode == 1) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyPart, Integer.valueOf(FriendSendModeLayout2.this.startIndex));
                        } else if (FriendSendModeLayout2.this.isSaveKeyShield && FriendSendModeLayout2.this.sendTypeMode == 2) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyShield, Integer.valueOf(FriendSendModeLayout2.this.startIndex));
                        }
                        if (FriendSendModeLayout2.this.listener != null) {
                            FriendSendModeLayout2.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.jump_label_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FriendSendModeLayout2.this.sendTypeMode != 2) {
                    Intent intent = new Intent(FriendSendModeLayout2.this.mContext, ObtainSingleTagActivity.class);
                    LabelBean labelBean = new LabelBean();
                    labelBean.setLabelName(FriendSendModeLayout2.this.friendsTags);
                    intent.putExtra("selects", labelBean);
                    MyApplication.instance.getBaseActivity().startActivity(intent);
                    return;
                }
                Intent intent2 = new Intent(FriendSendModeLayout2.this.mContext, ObtainTagActivity.class);
                intent2.putExtra("selects", FriendSendModeLayout2.this.friendsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent2);
            }
        });
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int i2 = 0;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        FriendSendModeLayout2.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayout2.this.jump_label_layout.setVisibility(8);
                        try {
                            int unused = FriendSendModeLayout2.this.startIndexAll = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyAll, 1)).intValue();
                        } catch (Exception e) {
                            int unused2 = FriendSendModeLayout2.this.startIndexAll = 1;
                        }
                        FriendSendModeLayout2.this.start_pull.setText("从第" + FriendSendModeLayout2.this.startIndexAll + "个好友开始拉人进群");
                        FontUtils.changeFontColor(FriendSendModeLayout2.this.mContext, FriendSendModeLayout2.this.start_pull);
                        String unused3 = FriendSendModeLayout2.this.allLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyAllLabel, "");
                        if (FriendSendModeLayout2.this.allLabel == null || "".equals(FriendSendModeLayout2.this.allLabel)) {
                            FriendSendModeLayout2.this.jump_label.setText("点我设置要拉的好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyAllLabel, "");
                        } else {
                            FriendSendModeLayout2.this.jump_label.setText(FriendSendModeLayout2.this.allLabel);
                            if (FriendSendModeLayout2.this.listener != null) {
                                LinkedHashSet linkedHashSet = new LinkedHashSet();
                                linkedHashSet.add(FriendSendModeLayout2.this.allLabel);
                                FriendSendModeLayout2.this.listener.sendTag(linkedHashSet);
                            }
                        }
                        String unused4 = FriendSendModeLayout2.this.friendsTags = FriendSendModeLayout2.this.allLabel;
                        if (FriendSendModeLayout2.this.listener != null) {
                            int unused5 = FriendSendModeLayout2.this.sendTypeMode = 0;
                            FriendSendModeLayout2.this.listener.sendType(FriendSendModeLayout2.this.sendTypeMode);
                            FriendSendModeLayout2.this.listener.sendStartIndex(FriendSendModeLayout2.this.startIndexAll);
                            LogUtils.log("WS_FRIEND_MODE.3");
                            FriendSendModeLayout2.this.listener.sendMembers(new LinkedHashSet());
                            return;
                        }
                        return;
                    case 2131296497:
                        FriendSendModeLayout2.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayout2.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused6 = FriendSendModeLayout2.this.startIndexPart = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyPart, 1)).intValue();
                        } catch (Exception e2) {
                            int unused7 = FriendSendModeLayout2.this.startIndexPart = 1;
                        }
                        FriendSendModeLayout2.this.start_pull.setText("从第" + FriendSendModeLayout2.this.startIndexPart + "个好友开始拉人进群");
                        FontUtils.changeFontColor(FriendSendModeLayout2.this.mContext, FriendSendModeLayout2.this.start_pull);
                        String unused8 = FriendSendModeLayout2.this.partLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyPartLabel, "");
                        if (FriendSendModeLayout2.this.partLabel == null || "".equals(FriendSendModeLayout2.this.partLabel)) {
                            FriendSendModeLayout2.this.jump_label.setText("点我设置要拉的好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyPartLabel, "");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyPartLabelMember, "");
                        } else {
                            FriendSendModeLayout2.this.jump_label.setText(FriendSendModeLayout2.this.partLabel);
                            if (FriendSendModeLayout2.this.listener != null) {
                                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                                linkedHashSet2.add(FriendSendModeLayout2.this.partLabel);
                                FriendSendModeLayout2.this.listener.sendTag(linkedHashSet2);
                            }
                        }
                        String unused9 = FriendSendModeLayout2.this.friendsTags = FriendSendModeLayout2.this.partLabel;
                        if (FriendSendModeLayout2.this.listener != null) {
                            int unused10 = FriendSendModeLayout2.this.sendTypeMode = 1;
                            FriendSendModeLayout2.this.listener.sendType(FriendSendModeLayout2.this.sendTypeMode);
                            FriendSendModeLayout2.this.listener.sendStartIndex(FriendSendModeLayout2.this.startIndexPart);
                        }
                        String unused11 = FriendSendModeLayout2.this.partLabelMember = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyPartLabelMember, "");
                        if (FriendSendModeLayout2.this.partLabelMember != null && !"".equals(FriendSendModeLayout2.this.partLabelMember)) {
                            LinkedHashSet linkedHashSet3 = new LinkedHashSet();
                            if (FriendSendModeLayout2.this.partLabelMember.contains(";")) {
                                String[] split = FriendSendModeLayout2.this.partLabelMember.split(";");
                                while (i2 < split.length) {
                                    linkedHashSet3.add(split[i2]);
                                    i2++;
                                }
                            } else {
                                linkedHashSet3.add(FriendSendModeLayout2.this.partLabelMember);
                            }
                            if (FriendSendModeLayout2.this.listener != null) {
                                LogUtils.log("WS_FRIEND_MODE.4");
                                FriendSendModeLayout2.this.listener.sendMembers(linkedHashSet3);
                                return;
                            }
                            return;
                        } else if (FriendSendModeLayout2.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.5");
                            FriendSendModeLayout2.this.listener.sendMembers(new LinkedHashSet());
                            return;
                        } else {
                            return;
                        }
                    case 2131296498:
                        FriendSendModeLayout2.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayout2.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused12 = FriendSendModeLayout2.this.startIndexShield = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyShield, 1)).intValue();
                        } catch (Exception e3) {
                            int unused13 = FriendSendModeLayout2.this.startIndexShield = 1;
                        }
                        FriendSendModeLayout2.this.start_pull.setText("从第" + FriendSendModeLayout2.this.startIndexShield + "个好友开始拉人进群");
                        FontUtils.changeFontColor(FriendSendModeLayout2.this.mContext, FriendSendModeLayout2.this.start_pull);
                        String unused14 = FriendSendModeLayout2.this.shieldLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyShieldLabel, "");
                        if (FriendSendModeLayout2.this.shieldLabel == null || "".equals(FriendSendModeLayout2.this.shieldLabel)) {
                            FriendSendModeLayout2.this.jump_label.setText("点我设置要屏蔽的好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyShieldLabel, "");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayout2.this.keyShieldLabelMember, "");
                        } else {
                            FriendSendModeLayout2.this.jump_label.setText(FriendSendModeLayout2.this.shieldLabel);
                            if (FriendSendModeLayout2.this.listener != null) {
                                LinkedHashSet linkedHashSet4 = new LinkedHashSet();
                                if (FriendSendModeLayout2.this.shieldLabel.contains(";")) {
                                    String[] split2 = FriendSendModeLayout2.this.shieldLabel.split(";");
                                    for (String add : split2) {
                                        linkedHashSet4.add(add);
                                    }
                                } else {
                                    linkedHashSet4.add(FriendSendModeLayout2.this.shieldLabel);
                                }
                                FriendSendModeLayout2.this.listener.sendTag(linkedHashSet4);
                            }
                        }
                        String unused15 = FriendSendModeLayout2.this.friendsTags = FriendSendModeLayout2.this.shieldLabel;
                        if (FriendSendModeLayout2.this.listener != null) {
                            int unused16 = FriendSendModeLayout2.this.sendTypeMode = 2;
                            FriendSendModeLayout2.this.listener.sendStartIndex(FriendSendModeLayout2.this.startIndexShield);
                            FriendSendModeLayout2.this.listener.sendType(FriendSendModeLayout2.this.sendTypeMode);
                        }
                        String unused17 = FriendSendModeLayout2.this.shieldLabelMember = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayout2.this.keyShieldLabelMember, "");
                        if (FriendSendModeLayout2.this.shieldLabelMember != null && !"".equals(FriendSendModeLayout2.this.shieldLabelMember)) {
                            LinkedHashSet linkedHashSet5 = new LinkedHashSet();
                            if (FriendSendModeLayout2.this.shieldLabelMember.contains(";")) {
                                String[] split3 = FriendSendModeLayout2.this.shieldLabelMember.split(";");
                                while (i2 < split3.length) {
                                    linkedHashSet5.add(split3[i2]);
                                    i2++;
                                }
                            } else {
                                linkedHashSet5.add(FriendSendModeLayout2.this.shieldLabelMember);
                            }
                            if (FriendSendModeLayout2.this.listener != null) {
                                LogUtils.log("WS_FRIEND_MODE.6");
                                FriendSendModeLayout2.this.listener.sendMembers(linkedHashSet5);
                                return;
                            }
                            return;
                        } else if (FriendSendModeLayout2.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.7");
                            FriendSendModeLayout2.this.listener.sendMembers(new LinkedHashSet());
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
            this.jump_label.setText("点我设置要拉的好友标签");
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
