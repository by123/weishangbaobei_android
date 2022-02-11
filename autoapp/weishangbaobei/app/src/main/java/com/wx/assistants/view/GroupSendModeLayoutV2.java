package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wx.assistants.activity.ObtainGroupActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.wx.assistants.utils.SPUtils;

public class GroupSendModeLayoutV2 extends LinearLayout {
    /* access modifiers changed from: private */
    public String allLabel = "";
    private RelativeLayout allLayout;
    private String dndStr = "";
    private String groupsMembers = "";
    /* access modifiers changed from: private */
    public String groupsTags = "";
    /* access modifiers changed from: private */
    public ImageView imgAll;
    /* access modifiers changed from: private */
    public ImageView imgPart;
    /* access modifiers changed from: private */
    public ImageView imgShield;
    private boolean isSaveKey2All = false;
    private boolean isSaveKey2Part = false;
    private boolean isSaveKey2Shield = false;
    private boolean isSaveKeyAll = false;
    private boolean isSaveKeyPart = false;
    private boolean isSaveKeyShield = false;
    /* access modifiers changed from: private */
    public TextView jump_label2;
    /* access modifiers changed from: private */
    public TextView jump_label3;
    /* access modifiers changed from: private */
    public LinearLayout jump_label_layout2;
    /* access modifiers changed from: private */
    public LinearLayout jump_label_layout3;
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
    private RelativeLayout partLayout;
    private TextView sendAllSmallTV;
    private TextView sendAllTV;
    private TextView sendPartSmallTV;
    private TextView sendPartTV;
    private TextView sendShieldSmallTV;
    private TextView sendShieldTV;
    /* access modifiers changed from: private */
    public int sendTypeMode = 0;
    /* access modifiers changed from: private */
    public String shieldLabel = "";
    private RelativeLayout shieldLayout;
    /* access modifiers changed from: private */
    public int startIndex = 1;
    /* access modifiers changed from: private */
    public int startIndexAll = 1;
    /* access modifiers changed from: private */
    public int startIndexPart = 1;
    /* access modifiers changed from: private */
    public int startIndexShield = 1;
    /* access modifiers changed from: private */
    public TextView start_pull1;
    /* access modifiers changed from: private */
    public TextView start_pull2;
    /* access modifiers changed from: private */
    public TextView start_pull3;
    /* access modifiers changed from: private */
    public LinearLayout start_pull_layout1;
    /* access modifiers changed from: private */
    public LinearLayout start_pull_layout2;
    /* access modifiers changed from: private */
    public LinearLayout start_pull_layout3;

    public interface GroupSendTypeListener {
        void sendMembers(String str);

        void sendStartIndex(int i);

        void sendType(int i);
    }

    public void setGroupSendTypeListener(GroupSendTypeListener groupSendTypeListener) {
        if (groupSendTypeListener != null) {
            this.listener = groupSendTypeListener;
        }
    }

    public GroupSendModeLayoutV2(Context context) {
        super(context);
        this.mContext = context;
    }

    public GroupSendModeLayoutV2(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public GroupSendModeLayoutV2(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setSendStr(String str) {
        this.groupsTags = str;
        if (str == null || "".equals(str)) {
            if (this.sendTypeMode == 1) {
                this.jump_label2.setText("点我设置微信群");
            } else {
                this.jump_label3.setText("点我设置微信群");
            }
            if (this.sendTypeMode == 1) {
                SPUtils.put(MyApplication.getConText(), this.keyPartLabel, "");
                this.jump_label2.setText("点我设置要邀请的微信群");
            } else if (this.sendTypeMode == 2) {
                SPUtils.put(MyApplication.getConText(), this.keyShieldLabel, "");
                this.jump_label3.setText("点我设置要屏蔽的微信群");
            }
            if (this.listener != null) {
                this.listener.sendMembers("");
            }
        } else if (this.sendTypeMode == 1) {
            this.jump_label2.setText(str);
        } else {
            this.jump_label3.setText(str);
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

    public void initSendIndexStrAll(boolean z, String str, int i) {
        this.isSaveKeyAll = z;
        this.keyAll = str;
        this.startIndexAll = i;
        if (this.sendTypeMode == 0) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull1;
                textView.setText("从第" + this.startIndex + "个微信群开始拉人");
                FontUtils.changeFontColor(this.mContext, this.start_pull1);
            } else {
                this.start_pull1.setText("从第1个微信群开始拉人");
                FontUtils.changeFontColor(this.mContext, this.start_pull1);
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
                TextView textView = this.start_pull2;
                textView.setText("从第" + this.startIndex + "个微信群开始拉人");
                FontUtils.changeFontColor(this.mContext, this.start_pull2);
            } else {
                this.start_pull2.setText("从第1个微信群开始拉人");
                FontUtils.changeFontColor(this.mContext, this.start_pull2);
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
                TextView textView = this.start_pull3;
                textView.setText("从第" + this.startIndex + "个微信群开始拉人");
                FontUtils.changeFontColor(this.mContext, this.start_pull3);
            } else {
                this.start_pull3.setText("从第1个微信群开始拉人");
                FontUtils.changeFontColor(this.mContext, this.start_pull3);
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
        }
    }

    public void initSendLabelStrPart(boolean z, String str, String str2) {
        this.isSaveKey2Part = z;
        this.keyPartLabel = str;
        this.partLabel = str2;
        if (this.sendTypeMode == 1) {
            this.groupsTags = str2;
            if (this.groupsTags == null || "".equals(this.groupsTags)) {
                this.jump_label2.setText("点我设置要邀请的微信群");
                return;
            }
            this.jump_label2.setText(this.groupsTags);
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
                this.jump_label3.setText("点我设置要屏蔽的微信群");
                return;
            }
            this.jump_label3.setText(this.groupsTags);
            if (this.listener != null) {
                this.listener.sendMembers(this.groupsTags);
            }
        }
    }

    private void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427759, this, true);
    }

    public void setAllTv(String str, String str2) {
        if (this.sendAllTV != null) {
            this.sendAllTV.setText(str);
        }
        if (this.sendAllSmallTV != null) {
            this.sendAllSmallTV.setText(str2);
        }
    }

    public void setPartTv(String str, String str2) {
        if (this.sendPartTV != null) {
            this.sendPartTV.setText(str);
        }
        if (this.sendPartSmallTV != null) {
            this.sendPartSmallTV.setText(str2);
        }
    }

    public void setShieldTv(String str, String str2) {
        if (this.sendShieldTV != null) {
            this.sendShieldTV.setText(str);
        }
        if (this.sendShieldSmallTV != null) {
            this.sendShieldSmallTV.setText(str2);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.allLayout = (RelativeLayout) findViewById(2131296338);
        this.partLayout = (RelativeLayout) findViewById(2131297115);
        this.shieldLayout = (RelativeLayout) findViewById(2131297370);
        this.imgAll = (ImageView) findViewById(2131296775);
        this.imgPart = (ImageView) findViewById(2131296778);
        this.imgShield = (ImageView) findViewById(2131296780);
        this.sendAllTV = (TextView) findViewById(2131297329);
        this.sendAllSmallTV = (TextView) findViewById(2131297328);
        this.sendPartTV = (TextView) findViewById(2131297338);
        this.sendPartSmallTV = (TextView) findViewById(2131297337);
        this.sendShieldTV = (TextView) findViewById(2131297342);
        this.sendShieldSmallTV = (TextView) findViewById(2131297341);
        this.start_pull_layout1 = (LinearLayout) findViewById(2131297422);
        this.start_pull_layout2 = (LinearLayout) findViewById(2131297423);
        this.start_pull_layout3 = (LinearLayout) findViewById(2131297424);
        this.jump_label_layout2 = (LinearLayout) findViewById(2131296879);
        this.jump_label_layout3 = (LinearLayout) findViewById(2131296880);
        this.start_pull1 = (TextView) findViewById(2131297419);
        this.start_pull2 = (TextView) findViewById(2131297420);
        this.start_pull3 = (TextView) findViewById(2131297421);
        this.jump_label2 = (TextView) findViewById(2131296875);
        this.jump_label3 = (TextView) findViewById(2131296876);
        this.start_pull_layout2.setVisibility(8);
        this.start_pull_layout3.setVisibility(8);
        this.jump_label_layout2.setVisibility(8);
        this.jump_label_layout3.setVisibility(8);
        this.imgAll.setBackground(getResources().getDrawable(2131231280));
        this.imgPart.setBackground(getResources().getDrawable(2131231279));
        this.imgShield.setBackground(getResources().getDrawable(2131231279));
        this.start_pull1.setText("从第1个微信群开始拉人");
        FontUtils.changeFontColor(this.mContext, this.start_pull1);
        this.start_pull2.setText("从第1个微信群开始拉人");
        FontUtils.changeFontColor(this.mContext, this.start_pull2);
        this.start_pull3.setText("从第1个微信群开始拉人");
        FontUtils.changeFontColor(this.mContext, this.start_pull3);
        this.jump_label2.setText("点我设置要邀请的微信群");
        this.jump_label3.setText("点我设置要屏蔽的微信群");
        this.start_pull_layout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = GroupSendModeLayoutV2.this.start_pull1.getText().toString();
                    int unused = GroupSendModeLayoutV2.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception unused2) {
                }
                DialogUIUtils.dialogSetStartPoint(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = GroupSendModeLayoutV2.this.startIndex = i;
                        TextView access$000 = GroupSendModeLayoutV2.this.start_pull1;
                        access$000.setText("从第" + i + "个微信群开始拉人");
                        FontUtils.changeFontColor(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.start_pull1);
                        int unused2 = GroupSendModeLayoutV2.this.sendTypeMode = 0;
                        int unused3 = GroupSendModeLayoutV2.this.startIndexAll = GroupSendModeLayoutV2.this.startIndex;
                        SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyAll, Integer.valueOf(GroupSendModeLayoutV2.this.startIndex));
                        if (GroupSendModeLayoutV2.this.listener != null) {
                            GroupSendModeLayoutV2.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.start_pull_layout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = GroupSendModeLayoutV2.this.start_pull2.getText().toString();
                    int unused = GroupSendModeLayoutV2.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception unused2) {
                }
                DialogUIUtils.dialogSetStartPoint(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = GroupSendModeLayoutV2.this.startIndex = i;
                        TextView access$700 = GroupSendModeLayoutV2.this.start_pull2;
                        access$700.setText("从第" + i + "个微信群开始拉人");
                        FontUtils.changeFontColor(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.start_pull2);
                        int unused2 = GroupSendModeLayoutV2.this.sendTypeMode = 1;
                        int unused3 = GroupSendModeLayoutV2.this.startIndexPart = GroupSendModeLayoutV2.this.startIndex;
                        SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyPart, Integer.valueOf(GroupSendModeLayoutV2.this.startIndex));
                        if (GroupSendModeLayoutV2.this.listener != null) {
                            GroupSendModeLayoutV2.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.start_pull_layout3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = GroupSendModeLayoutV2.this.start_pull3.getText().toString();
                    int unused = GroupSendModeLayoutV2.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception unused2) {
                }
                DialogUIUtils.dialogSetStartPoint(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = GroupSendModeLayoutV2.this.startIndex = i;
                        TextView access$1000 = GroupSendModeLayoutV2.this.start_pull3;
                        access$1000.setText("从第" + i + "个微信群开始拉人");
                        FontUtils.changeFontColor(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.start_pull3);
                        int unused2 = GroupSendModeLayoutV2.this.sendTypeMode = 2;
                        int unused3 = GroupSendModeLayoutV2.this.startIndexShield = GroupSendModeLayoutV2.this.startIndex;
                        SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyShield, Integer.valueOf(GroupSendModeLayoutV2.this.startIndex));
                        if (GroupSendModeLayoutV2.this.listener != null) {
                            GroupSendModeLayoutV2.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.jump_label_layout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GroupSendModeLayoutV2.this.mContext, ObtainGroupActivity.class);
                intent.putExtra("selects", GroupSendModeLayoutV2.this.groupsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        this.jump_label_layout3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GroupSendModeLayoutV2.this.mContext, ObtainGroupActivity.class);
                intent.putExtra("selects", GroupSendModeLayoutV2.this.groupsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        this.allLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = GroupSendModeLayoutV2.this.sendTypeMode = 0;
                try {
                    int unused2 = GroupSendModeLayoutV2.this.startIndexAll = ((Integer) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyAll, 1)).intValue();
                } catch (Exception unused3) {
                    int unused4 = GroupSendModeLayoutV2.this.startIndexAll = 1;
                }
                TextView access$000 = GroupSendModeLayoutV2.this.start_pull1;
                access$000.setText("从第" + GroupSendModeLayoutV2.this.startIndexAll + "个微信群开始拉人");
                FontUtils.changeFontColor(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.start_pull1);
                String unused5 = GroupSendModeLayoutV2.this.allLabel = (String) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyAllLabel, "");
                String unused6 = GroupSendModeLayoutV2.this.groupsTags = GroupSendModeLayoutV2.this.allLabel;
                if (GroupSendModeLayoutV2.this.listener != null) {
                    GroupSendModeLayoutV2.this.listener.sendType(GroupSendModeLayoutV2.this.sendTypeMode);
                    GroupSendModeLayoutV2.this.listener.sendStartIndex(GroupSendModeLayoutV2.this.startIndexAll);
                    GroupSendModeLayoutV2.this.listener.sendMembers("");
                }
                GroupSendModeLayoutV2.this.start_pull_layout1.setVisibility(0);
                GroupSendModeLayoutV2.this.start_pull_layout2.setVisibility(8);
                GroupSendModeLayoutV2.this.start_pull_layout3.setVisibility(8);
                GroupSendModeLayoutV2.this.jump_label_layout2.setVisibility(8);
                GroupSendModeLayoutV2.this.jump_label_layout3.setVisibility(8);
                GroupSendModeLayoutV2.this.imgAll.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231280));
                GroupSendModeLayoutV2.this.imgPart.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231279));
                GroupSendModeLayoutV2.this.imgShield.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231279));
            }
        });
        this.partLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = GroupSendModeLayoutV2.this.sendTypeMode = 1;
                try {
                    int unused2 = GroupSendModeLayoutV2.this.startIndexPart = ((Integer) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyPart, 1)).intValue();
                } catch (Exception unused3) {
                    int unused4 = GroupSendModeLayoutV2.this.startIndexPart = 1;
                }
                TextView access$700 = GroupSendModeLayoutV2.this.start_pull2;
                access$700.setText("从第" + GroupSendModeLayoutV2.this.startIndexPart + "个微信群开始拉人");
                FontUtils.changeFontColor(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.start_pull2);
                String unused5 = GroupSendModeLayoutV2.this.partLabel = (String) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyPartLabel, "");
                if (GroupSendModeLayoutV2.this.partLabel == null || "".equals(GroupSendModeLayoutV2.this.partLabel)) {
                    GroupSendModeLayoutV2.this.jump_label2.setText("点我设置要邀请的微信群");
                    SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyPartLabel, "");
                    if (GroupSendModeLayoutV2.this.listener != null) {
                        GroupSendModeLayoutV2.this.listener.sendMembers("");
                    }
                } else {
                    GroupSendModeLayoutV2.this.jump_label2.setText(GroupSendModeLayoutV2.this.partLabel);
                    if (GroupSendModeLayoutV2.this.listener != null) {
                        GroupSendModeLayoutV2.this.listener.sendMembers(GroupSendModeLayoutV2.this.partLabel);
                    }
                }
                String unused6 = GroupSendModeLayoutV2.this.groupsTags = GroupSendModeLayoutV2.this.partLabel;
                if (GroupSendModeLayoutV2.this.listener != null) {
                    GroupSendModeLayoutV2.this.listener.sendType(GroupSendModeLayoutV2.this.sendTypeMode);
                    GroupSendModeLayoutV2.this.listener.sendStartIndex(GroupSendModeLayoutV2.this.startIndexPart);
                }
                GroupSendModeLayoutV2.this.start_pull_layout1.setVisibility(8);
                GroupSendModeLayoutV2.this.start_pull_layout3.setVisibility(8);
                GroupSendModeLayoutV2.this.jump_label_layout3.setVisibility(8);
                GroupSendModeLayoutV2.this.start_pull_layout2.setVisibility(0);
                GroupSendModeLayoutV2.this.jump_label_layout2.setVisibility(0);
                GroupSendModeLayoutV2.this.imgAll.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231279));
                GroupSendModeLayoutV2.this.imgPart.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231280));
                GroupSendModeLayoutV2.this.imgShield.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231279));
            }
        });
        this.shieldLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = GroupSendModeLayoutV2.this.sendTypeMode = 2;
                try {
                    int unused2 = GroupSendModeLayoutV2.this.startIndexShield = ((Integer) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyShield, 1)).intValue();
                } catch (Exception unused3) {
                    int unused4 = GroupSendModeLayoutV2.this.startIndexShield = 1;
                }
                TextView access$1000 = GroupSendModeLayoutV2.this.start_pull3;
                access$1000.setText("从第" + GroupSendModeLayoutV2.this.startIndexShield + "个微信群开始拉人");
                FontUtils.changeFontColor(GroupSendModeLayoutV2.this.mContext, GroupSendModeLayoutV2.this.start_pull3);
                String unused5 = GroupSendModeLayoutV2.this.shieldLabel = (String) SPUtils.get(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyShieldLabel, "");
                if (GroupSendModeLayoutV2.this.shieldLabel == null || "".equals(GroupSendModeLayoutV2.this.shieldLabel)) {
                    GroupSendModeLayoutV2.this.jump_label3.setText("点我设置要屏蔽的微信群");
                    SPUtils.put(MyApplication.getConText(), GroupSendModeLayoutV2.this.keyShieldLabel, "");
                    if (GroupSendModeLayoutV2.this.listener != null) {
                        GroupSendModeLayoutV2.this.listener.sendMembers("");
                    }
                } else {
                    GroupSendModeLayoutV2.this.jump_label3.setText(GroupSendModeLayoutV2.this.shieldLabel);
                    if (GroupSendModeLayoutV2.this.listener != null) {
                        GroupSendModeLayoutV2.this.listener.sendMembers(GroupSendModeLayoutV2.this.shieldLabel);
                    }
                }
                String unused6 = GroupSendModeLayoutV2.this.groupsTags = GroupSendModeLayoutV2.this.shieldLabel;
                if (GroupSendModeLayoutV2.this.listener != null) {
                    GroupSendModeLayoutV2.this.listener.sendStartIndex(GroupSendModeLayoutV2.this.startIndexShield);
                    GroupSendModeLayoutV2.this.listener.sendType(GroupSendModeLayoutV2.this.sendTypeMode);
                }
                GroupSendModeLayoutV2.this.start_pull_layout1.setVisibility(8);
                GroupSendModeLayoutV2.this.start_pull_layout3.setVisibility(0);
                GroupSendModeLayoutV2.this.jump_label_layout3.setVisibility(0);
                GroupSendModeLayoutV2.this.start_pull_layout2.setVisibility(8);
                GroupSendModeLayoutV2.this.jump_label_layout2.setVisibility(8);
                GroupSendModeLayoutV2.this.imgAll.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231279));
                GroupSendModeLayoutV2.this.imgPart.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231279));
                GroupSendModeLayoutV2.this.imgShield.setBackground(GroupSendModeLayoutV2.this.getResources().getDrawable(2131231280));
            }
        });
    }
}
