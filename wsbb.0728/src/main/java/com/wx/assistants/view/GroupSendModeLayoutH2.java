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
import com.wx.assistants.activity.ObtainGroupActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.wx.assistants.utils.SPUtils;

public class GroupSendModeLayoutH2 extends LinearLayout {
    /* access modifiers changed from: private */
    public String allLabel = "";
    private RadioGroup circulateRG;
    private String dndStr = "";
    private String groupsMembers = "";
    /* access modifiers changed from: private */
    public String groupsTags = "";
    private boolean isSaveKey2All = false;
    private boolean isSaveKey2Part = false;
    private boolean isSaveKey2Shield = false;
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
    public String keyAll = "group_default_all";
    /* access modifiers changed from: private */
    public String keyAllLabel = "group_default_all_label";
    /* access modifiers changed from: private */
    public String keyPart = "group_default_part";
    /* access modifiers changed from: private */
    public String keyPartLabel = "group_default_part_label";
    /* access modifiers changed from: private */
    public String keyShield = "group_default_shield";
    /* access modifiers changed from: private */
    public String keyShieldLabel = "group_default_shield_label";
    /* access modifiers changed from: private */
    public GroupSendTypeListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public String partLabel = "";
    /* access modifiers changed from: private */
    public int sendTypeMode = 0;
    /* access modifiers changed from: private */
    public String shieldLabel = "";
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

    public interface GroupSendTypeListener {
        void sendMembers(String str);

        void sendStartIndex(int i);

        void sendType(int i);
    }

    public GroupSendModeLayoutH2(Context context) {
        super(context);
        this.mContext = context;
    }

    public GroupSendModeLayoutH2(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public GroupSendModeLayoutH2(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427757, this, true);
    }

    public void initSendIndexStrAll(boolean z, String str, int i) {
        this.isSaveKeyAll = z;
        this.keyAll = str;
        this.startIndexAll = i;
        if (this.sendTypeMode == 0) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个微信群开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个微信群开始");
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
                textView.setText("从第" + this.startIndex + "个微信群开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个微信群开始");
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
                textView.setText("从第" + this.startIndex + "个微信群开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个微信群开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            }
            if (this.listener != null) {
                this.listener.sendStartIndex(this.startIndex);
            }
        }
    }

    public void initSendLabelStrAll(boolean z, String str, String str2) {
        this.isSaveKey2All = z;
        this.keyAllLabel = str;
        this.allLabel = str2;
        if (this.sendTypeMode == 0) {
            this.groupsTags = str2;
            if (this.groupsTags == null || "".equals(this.groupsTags)) {
                this.jump_label.setText("点我设置要邀请的微信群");
            } else {
                this.jump_label.setText(this.groupsTags);
            }
        }
    }

    public void initSendLabelStrPart(boolean z, String str, String str2) {
        this.isSaveKey2Part = z;
        this.keyPartLabel = str;
        this.partLabel = str2;
        if (this.sendTypeMode == 1) {
            this.groupsTags = str2;
            if (this.groupsTags == null || "".equals(this.groupsTags)) {
                this.jump_label.setText("点我设置要邀请的微信群");
                return;
            }
            this.jump_label.setText(this.groupsTags);
            if (this.listener != null) {
                this.listener.sendMembers(this.groupsTags);
            }
        }
    }

    public void initSendLabelStrShield(boolean z, String str, String str2) {
        this.isSaveKey2Shield = z;
        this.keyShieldLabel = str;
        this.shieldLabel = str2;
        if (this.sendTypeMode == 2) {
            this.groupsTags = str2;
            if (this.groupsTags == null || "".equals(this.groupsTags)) {
                this.jump_label.setText("点我设置要屏蔽的微信群");
                return;
            }
            this.jump_label.setText(this.groupsTags);
            if (this.listener != null) {
                this.listener.sendMembers(this.groupsTags);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.start_pull_layout = (LinearLayout) findViewById(2131297422);
        this.jump_label_layout = (LinearLayout) findViewById(2131296877);
        this.start_pull = (TextView) findViewById(2131297419);
        this.start_pull.setText("从第1个微信群开始");
        FontUtils.changeFontColor(this.mContext, this.start_pull);
        this.jump_label = (TextView) findViewById(2131296873);
        this.jump_label.setText("点我可设置要邀请的微信群");
        this.start_pull_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = GroupSendModeLayoutH2.this.start_pull.getText().toString();
                    int unused = GroupSendModeLayoutH2.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception e) {
                }
                DialogUIUtils.dialogSetStartPoint(GroupSendModeLayoutH2.this.mContext, GroupSendModeLayoutH2.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = GroupSendModeLayoutH2.this.startIndex = i;
                        TextView access$000 = GroupSendModeLayoutH2.this.start_pull;
                        access$000.setText("从第" + i + "个微信群开始");
                        FontUtils.changeFontColor(GroupSendModeLayoutH2.this.mContext, GroupSendModeLayoutH2.this.start_pull);
                        if (GroupSendModeLayoutH2.this.sendTypeMode == 0) {
                            int unused2 = GroupSendModeLayoutH2.this.startIndexAll = GroupSendModeLayoutH2.this.startIndex;
                        } else if (GroupSendModeLayoutH2.this.sendTypeMode == 1) {
                            int unused3 = GroupSendModeLayoutH2.this.startIndexPart = GroupSendModeLayoutH2.this.startIndex;
                        } else if (GroupSendModeLayoutH2.this.sendTypeMode == 2) {
                            int unused4 = GroupSendModeLayoutH2.this.startIndexShield = GroupSendModeLayoutH2.this.startIndex;
                        }
                        if (GroupSendModeLayoutH2.this.isSaveKeyAll && GroupSendModeLayoutH2.this.sendTypeMode == 0) {
                            SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyAll, Integer.valueOf(GroupSendModeLayoutH2.this.startIndex));
                        } else if (GroupSendModeLayoutH2.this.isSaveKeyPart && GroupSendModeLayoutH2.this.sendTypeMode == 1) {
                            SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyPart, Integer.valueOf(GroupSendModeLayoutH2.this.startIndex));
                        } else if (GroupSendModeLayoutH2.this.isSaveKeyShield && GroupSendModeLayoutH2.this.sendTypeMode == 2) {
                            SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyShield, Integer.valueOf(GroupSendModeLayoutH2.this.startIndex));
                        }
                        if (GroupSendModeLayoutH2.this.listener != null) {
                            GroupSendModeLayoutH2.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.jump_label_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GroupSendModeLayoutH2.this.mContext, ObtainGroupActivity.class);
                intent.putExtra("selects", GroupSendModeLayoutH2.this.groupsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        GroupSendModeLayoutH2.this.start_pull_layout.setVisibility(0);
                        GroupSendModeLayoutH2.this.jump_label_layout.setVisibility(8);
                        try {
                            int unused = GroupSendModeLayoutH2.this.startIndexAll = ((Integer) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyAll, 1)).intValue();
                        } catch (Exception e) {
                            int unused2 = GroupSendModeLayoutH2.this.startIndexAll = 1;
                        }
                        TextView access$000 = GroupSendModeLayoutH2.this.start_pull;
                        access$000.setText("从第" + GroupSendModeLayoutH2.this.startIndexAll + "个微信群开始");
                        FontUtils.changeFontColor(GroupSendModeLayoutH2.this.mContext, GroupSendModeLayoutH2.this.start_pull);
                        String unused3 = GroupSendModeLayoutH2.this.allLabel = (String) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyAllLabel, "");
                        if (GroupSendModeLayoutH2.this.allLabel == null || "".equals(GroupSendModeLayoutH2.this.allLabel)) {
                            GroupSendModeLayoutH2.this.jump_label.setText("点我设置要邀请的微信群");
                            SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyAllLabel, "");
                        } else {
                            GroupSendModeLayoutH2.this.jump_label.setText(GroupSendModeLayoutH2.this.allLabel);
                        }
                        String unused4 = GroupSendModeLayoutH2.this.groupsTags = GroupSendModeLayoutH2.this.allLabel;
                        if (GroupSendModeLayoutH2.this.listener != null) {
                            int unused5 = GroupSendModeLayoutH2.this.sendTypeMode = 0;
                            GroupSendModeLayoutH2.this.listener.sendType(GroupSendModeLayoutH2.this.sendTypeMode);
                            GroupSendModeLayoutH2.this.listener.sendStartIndex(GroupSendModeLayoutH2.this.startIndexAll);
                            GroupSendModeLayoutH2.this.listener.sendMembers("");
                            return;
                        }
                        return;
                    case 2131296497:
                        GroupSendModeLayoutH2.this.start_pull_layout.setVisibility(0);
                        GroupSendModeLayoutH2.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused6 = GroupSendModeLayoutH2.this.startIndexPart = ((Integer) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyPart, 1)).intValue();
                        } catch (Exception e2) {
                            int unused7 = GroupSendModeLayoutH2.this.startIndexPart = 1;
                        }
                        TextView access$0002 = GroupSendModeLayoutH2.this.start_pull;
                        access$0002.setText("从第" + GroupSendModeLayoutH2.this.startIndexPart + "个微信群开始");
                        FontUtils.changeFontColor(GroupSendModeLayoutH2.this.mContext, GroupSendModeLayoutH2.this.start_pull);
                        String unused8 = GroupSendModeLayoutH2.this.partLabel = (String) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyPartLabel, "");
                        if (GroupSendModeLayoutH2.this.partLabel == null || "".equals(GroupSendModeLayoutH2.this.partLabel)) {
                            GroupSendModeLayoutH2.this.jump_label.setText("点我设置要邀请的微信群");
                            SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyPartLabel, "");
                            if (GroupSendModeLayoutH2.this.listener != null) {
                                GroupSendModeLayoutH2.this.listener.sendMembers("");
                            }
                        } else {
                            GroupSendModeLayoutH2.this.jump_label.setText(GroupSendModeLayoutH2.this.partLabel);
                            if (GroupSendModeLayoutH2.this.listener != null) {
                                GroupSendModeLayoutH2.this.listener.sendMembers(GroupSendModeLayoutH2.this.partLabel);
                            }
                        }
                        String unused9 = GroupSendModeLayoutH2.this.groupsTags = GroupSendModeLayoutH2.this.partLabel;
                        if (GroupSendModeLayoutH2.this.listener != null) {
                            int unused10 = GroupSendModeLayoutH2.this.sendTypeMode = 1;
                            GroupSendModeLayoutH2.this.listener.sendType(GroupSendModeLayoutH2.this.sendTypeMode);
                            GroupSendModeLayoutH2.this.listener.sendStartIndex(GroupSendModeLayoutH2.this.startIndexPart);
                            return;
                        }
                        return;
                    case 2131296498:
                        GroupSendModeLayoutH2.this.start_pull_layout.setVisibility(0);
                        GroupSendModeLayoutH2.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused11 = GroupSendModeLayoutH2.this.startIndexShield = ((Integer) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyShield, 1)).intValue();
                        } catch (Exception e3) {
                            int unused12 = GroupSendModeLayoutH2.this.startIndexShield = 1;
                        }
                        TextView access$0003 = GroupSendModeLayoutH2.this.start_pull;
                        access$0003.setText("从第" + GroupSendModeLayoutH2.this.startIndexShield + "个微信群开始");
                        FontUtils.changeFontColor(GroupSendModeLayoutH2.this.mContext, GroupSendModeLayoutH2.this.start_pull);
                        String unused13 = GroupSendModeLayoutH2.this.shieldLabel = (String) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyShieldLabel, "");
                        if (GroupSendModeLayoutH2.this.shieldLabel == null || "".equals(GroupSendModeLayoutH2.this.shieldLabel)) {
                            GroupSendModeLayoutH2.this.jump_label.setText("点我设置要屏蔽的微信群");
                            SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutH2.this.keyShieldLabel, "");
                            if (GroupSendModeLayoutH2.this.listener != null) {
                                GroupSendModeLayoutH2.this.listener.sendMembers("");
                            }
                        } else {
                            GroupSendModeLayoutH2.this.jump_label.setText(GroupSendModeLayoutH2.this.shieldLabel);
                            if (GroupSendModeLayoutH2.this.listener != null) {
                                GroupSendModeLayoutH2.this.listener.sendMembers(GroupSendModeLayoutH2.this.shieldLabel);
                            }
                        }
                        String unused14 = GroupSendModeLayoutH2.this.groupsTags = GroupSendModeLayoutH2.this.shieldLabel;
                        if (GroupSendModeLayoutH2.this.listener != null) {
                            int unused15 = GroupSendModeLayoutH2.this.sendTypeMode = 2;
                            GroupSendModeLayoutH2.this.listener.sendStartIndex(GroupSendModeLayoutH2.this.startIndexShield);
                            GroupSendModeLayoutH2.this.listener.sendType(GroupSendModeLayoutH2.this.sendTypeMode);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void setGroupSendTypeListener(GroupSendTypeListener groupSendTypeListener) {
        if (groupSendTypeListener != null) {
            this.listener = groupSendTypeListener;
        }
    }

    public void setSendStr(String str) {
        this.groupsTags = str;
        if (str == null || "".equals(str)) {
            this.jump_label.setText("点我设置微信群");
            if (this.sendTypeMode == 1) {
                SPUtils.put(MyApplication.getConText(), this.keyPartLabel, "");
                this.jump_label.setText("点我设置要邀请的微信群");
            } else if (this.sendTypeMode == 2) {
                SPUtils.put(MyApplication.getConText(), this.keyShieldLabel, "");
                this.jump_label.setText("点我设置要屏蔽的微信群");
            }
            if (this.listener != null) {
                this.listener.sendMembers("");
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
}
