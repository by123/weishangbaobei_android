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
import com.wx.assistants.activity.ObtainTagCompanyActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.dialog.AlertEditDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.FontUtils;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SPUtils;
import java.util.LinkedHashSet;

public class FriendSendModeLayoutCompany extends LinearLayout {
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
    public String keyShield = "default";
    /* access modifiers changed from: private */
    public String keyShieldLabel = "default";
    /* access modifiers changed from: private */
    public FriendSendTypeListener listener;
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

    public interface FriendSendTypeListener {
        void sendStartIndex(int i);

        void sendTags(LinkedHashSet<String> linkedHashSet);

        void sendType(int i);
    }

    public FriendSendModeLayoutCompany(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendSendModeLayoutCompany(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public FriendSendModeLayoutCompany(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427755, this, true);
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
                textView.setText("从第" + this.startIndex + "个开始群发");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个开始群发");
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
                textView.setText("从第" + this.startIndex + "个开始群发");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个开始群发");
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
                textView.setText("从第" + this.startIndex + "个开始群发");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个开始群发");
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
            this.friendsTags = str2;
            if (this.friendsTags == null || "".equals(this.friendsTags)) {
                this.jump_label.setText("点我设置好友标签");
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
                this.jump_label.setText("点我设置好友标签");
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
                this.jump_label.setText("点我设置好友标签");
            } else {
                this.jump_label.setText(this.friendsTags);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.start_pull_layout = (LinearLayout) findViewById(2131297422);
        this.jump_label_layout = (LinearLayout) findViewById(2131296877);
        this.start_pull = (TextView) findViewById(2131297419);
        this.start_pull.setText("从第1个开始");
        FontUtils.changeFontColor(this.mContext, this.start_pull);
        this.jump_label = (TextView) findViewById(2131296873);
        this.jump_label.setText("点我可设置好友标签");
        this.start_pull_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = FriendSendModeLayoutCompany.this.start_pull.getText().toString();
                    int unused = FriendSendModeLayoutCompany.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception e) {
                }
                DialogUIUtils.dialogSetStartPoint(FriendSendModeLayoutCompany.this.mContext, FriendSendModeLayoutCompany.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = FriendSendModeLayoutCompany.this.startIndex = i;
                        TextView access$000 = FriendSendModeLayoutCompany.this.start_pull;
                        access$000.setText("从第" + i + "个开始");
                        FontUtils.changeFontColor(FriendSendModeLayoutCompany.this.mContext, FriendSendModeLayoutCompany.this.start_pull);
                        if (FriendSendModeLayoutCompany.this.sendTypeMode == 0) {
                            int unused2 = FriendSendModeLayoutCompany.this.startIndexAll = FriendSendModeLayoutCompany.this.startIndex;
                        } else if (FriendSendModeLayoutCompany.this.sendTypeMode == 1) {
                            int unused3 = FriendSendModeLayoutCompany.this.startIndexPart = FriendSendModeLayoutCompany.this.startIndex;
                        } else if (FriendSendModeLayoutCompany.this.sendTypeMode == 2) {
                            int unused4 = FriendSendModeLayoutCompany.this.startIndexShield = FriendSendModeLayoutCompany.this.startIndex;
                        }
                        if (FriendSendModeLayoutCompany.this.isSaveKeyAll && FriendSendModeLayoutCompany.this.sendTypeMode == 0) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyAll, Integer.valueOf(FriendSendModeLayoutCompany.this.startIndex));
                        } else if (FriendSendModeLayoutCompany.this.isSaveKeyPart && FriendSendModeLayoutCompany.this.sendTypeMode == 1) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyPart, Integer.valueOf(FriendSendModeLayoutCompany.this.startIndex));
                        } else if (FriendSendModeLayoutCompany.this.isSaveKeyShield && FriendSendModeLayoutCompany.this.sendTypeMode == 2) {
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyShield, Integer.valueOf(FriendSendModeLayoutCompany.this.startIndex));
                        }
                        if (FriendSendModeLayoutCompany.this.listener != null) {
                            FriendSendModeLayoutCompany.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.jump_label_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FriendSendModeLayoutCompany.this.mContext, ObtainTagCompanyActivity.class);
                intent.putExtra("selects", FriendSendModeLayoutCompany.this.friendsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LinkedHashSet linkedHashSet;
                LinkedHashSet linkedHashSet2 = null;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        FriendSendModeLayoutCompany.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayoutCompany.this.jump_label_layout.setVisibility(8);
                        try {
                            int unused = FriendSendModeLayoutCompany.this.startIndexAll = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyAll, 1)).intValue();
                        } catch (Exception e) {
                            int unused2 = FriendSendModeLayoutCompany.this.startIndexAll = 1;
                        }
                        FriendSendModeLayoutCompany.this.start_pull.setText("从第" + FriendSendModeLayoutCompany.this.startIndexAll + "个开始");
                        FontUtils.changeFontColor(FriendSendModeLayoutCompany.this.mContext, FriendSendModeLayoutCompany.this.start_pull);
                        String unused3 = FriendSendModeLayoutCompany.this.allLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyAllLabel, "");
                        if (FriendSendModeLayoutCompany.this.allLabel == null || "".equals(FriendSendModeLayoutCompany.this.allLabel)) {
                            FriendSendModeLayoutCompany.this.jump_label.setText("点我设置好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyAllLabel, "");
                        } else {
                            FriendSendModeLayoutCompany.this.jump_label.setText(FriendSendModeLayoutCompany.this.allLabel);
                        }
                        String unused4 = FriendSendModeLayoutCompany.this.friendsTags = FriendSendModeLayoutCompany.this.allLabel;
                        if (FriendSendModeLayoutCompany.this.listener != null) {
                            int unused5 = FriendSendModeLayoutCompany.this.sendTypeMode = 0;
                            FriendSendModeLayoutCompany.this.listener.sendType(FriendSendModeLayoutCompany.this.sendTypeMode);
                            FriendSendModeLayoutCompany.this.listener.sendStartIndex(FriendSendModeLayoutCompany.this.startIndexAll);
                            FriendSendModeLayoutCompany.this.listener.sendTags((LinkedHashSet<String>) null);
                            LogUtils.log("WS_FRIEND_MODE.3");
                            return;
                        }
                        return;
                    case 2131296497:
                        FriendSendModeLayoutCompany.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayoutCompany.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused6 = FriendSendModeLayoutCompany.this.startIndexPart = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyPart, 1)).intValue();
                        } catch (Exception e2) {
                            int unused7 = FriendSendModeLayoutCompany.this.startIndexPart = 1;
                        }
                        FriendSendModeLayoutCompany.this.start_pull.setText("从第" + FriendSendModeLayoutCompany.this.startIndexPart + "个开始");
                        FontUtils.changeFontColor(FriendSendModeLayoutCompany.this.mContext, FriendSendModeLayoutCompany.this.start_pull);
                        String unused8 = FriendSendModeLayoutCompany.this.partLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyPartLabel, "");
                        if (FriendSendModeLayoutCompany.this.partLabel == null || "".equals(FriendSendModeLayoutCompany.this.partLabel)) {
                            FriendSendModeLayoutCompany.this.jump_label.setText("点我设置好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyPartLabel, "");
                        } else {
                            FriendSendModeLayoutCompany.this.jump_label.setText(FriendSendModeLayoutCompany.this.partLabel);
                        }
                        String unused9 = FriendSendModeLayoutCompany.this.friendsTags = FriendSendModeLayoutCompany.this.partLabel;
                        if (FriendSendModeLayoutCompany.this.listener != null) {
                            int unused10 = FriendSendModeLayoutCompany.this.sendTypeMode = 1;
                            FriendSendModeLayoutCompany.this.listener.sendType(FriendSendModeLayoutCompany.this.sendTypeMode);
                            FriendSendModeLayoutCompany.this.listener.sendStartIndex(FriendSendModeLayoutCompany.this.startIndexPart);
                            if (FriendSendModeLayoutCompany.this.friendsTags == null || "".equals(FriendSendModeLayoutCompany.this.friendsTags)) {
                                linkedHashSet = null;
                            } else {
                                linkedHashSet = new LinkedHashSet();
                                if (FriendSendModeLayoutCompany.this.friendsTags.contains(";")) {
                                    String[] split = FriendSendModeLayoutCompany.this.friendsTags.split(";");
                                    for (String add : split) {
                                        linkedHashSet.add(add);
                                    }
                                } else {
                                    linkedHashSet.add(FriendSendModeLayoutCompany.this.friendsTags);
                                }
                            }
                            if (linkedHashSet != null) {
                                FriendSendModeLayoutCompany.this.listener.sendTags(linkedHashSet);
                                return;
                            }
                            return;
                        }
                        return;
                    case 2131296498:
                        FriendSendModeLayoutCompany.this.start_pull_layout.setVisibility(0);
                        FriendSendModeLayoutCompany.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused11 = FriendSendModeLayoutCompany.this.startIndexShield = ((Integer) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyShield, 1)).intValue();
                        } catch (Exception e3) {
                            int unused12 = FriendSendModeLayoutCompany.this.startIndexShield = 1;
                        }
                        FriendSendModeLayoutCompany.this.start_pull.setText("从第" + FriendSendModeLayoutCompany.this.startIndexShield + "个开始");
                        FontUtils.changeFontColor(FriendSendModeLayoutCompany.this.mContext, FriendSendModeLayoutCompany.this.start_pull);
                        String unused13 = FriendSendModeLayoutCompany.this.shieldLabel = (String) SPUtils.get(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyShieldLabel, "");
                        if (FriendSendModeLayoutCompany.this.shieldLabel == null || "".equals(FriendSendModeLayoutCompany.this.shieldLabel)) {
                            FriendSendModeLayoutCompany.this.jump_label.setText("点我设置好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendSendModeLayoutCompany.this.keyShieldLabel, "");
                        } else {
                            FriendSendModeLayoutCompany.this.jump_label.setText(FriendSendModeLayoutCompany.this.shieldLabel);
                        }
                        String unused14 = FriendSendModeLayoutCompany.this.friendsTags = FriendSendModeLayoutCompany.this.shieldLabel;
                        if (FriendSendModeLayoutCompany.this.listener != null) {
                            int unused15 = FriendSendModeLayoutCompany.this.sendTypeMode = 2;
                            FriendSendModeLayoutCompany.this.listener.sendStartIndex(FriendSendModeLayoutCompany.this.startIndexShield);
                            FriendSendModeLayoutCompany.this.listener.sendType(FriendSendModeLayoutCompany.this.sendTypeMode);
                            if (FriendSendModeLayoutCompany.this.friendsTags != null && !"".equals(FriendSendModeLayoutCompany.this.friendsTags)) {
                                linkedHashSet2 = new LinkedHashSet();
                                if (FriendSendModeLayoutCompany.this.friendsTags.contains(";")) {
                                    String[] split2 = FriendSendModeLayoutCompany.this.friendsTags.split(";");
                                    for (String add2 : split2) {
                                        linkedHashSet2.add(add2);
                                    }
                                } else {
                                    linkedHashSet2.add(FriendSendModeLayoutCompany.this.friendsTags);
                                }
                            }
                            if (linkedHashSet2 != null) {
                                FriendSendModeLayoutCompany.this.listener.sendTags(linkedHashSet2);
                                return;
                            }
                            return;
                        }
                        return;
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

    public void setSendStr(String str) {
        this.friendsTags = str;
        if (str == null || "".equals(str)) {
            this.jump_label.setText("点我设置好友标签");
            if (this.sendTypeMode == 1) {
                SPUtils.put(MyApplication.getConText(), this.keyPartLabel, "");
            } else if (this.sendTypeMode == 2) {
                SPUtils.put(MyApplication.getConText(), this.keyShieldLabel, "");
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
