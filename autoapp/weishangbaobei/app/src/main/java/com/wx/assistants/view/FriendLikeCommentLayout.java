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

public class FriendLikeCommentLayout extends LinearLayout {
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

    public interface FriendSendTypeListener {
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

    public FriendLikeCommentLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendLikeCommentLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public FriendLikeCommentLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
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
            this.jump_label.setText("点我设置好友标签");
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

    public void initSendIndexStrAll(boolean z, String str, int i) {
        this.isSaveKeyAll = z;
        this.keyAll = str;
        this.startIndexAll = i;
        if (this.sendTypeMode == 0) {
            this.startIndex = i;
            if (this.startIndex != 1) {
                TextView textView = this.start_pull;
                textView.setText("从第" + this.startIndex + "个微信好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个微信好友开始");
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
                textView.setText("从第" + this.startIndex + "个微信好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个微信好友开始");
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
                textView.setText("从第" + this.startIndex + "个微信好友开始");
                FontUtils.changeFontColor(this.mContext, this.start_pull);
            } else {
                this.start_pull.setText("从第1个微信好友开始");
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

    private void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427752, this, true);
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
        this.jump_label.setText("点我可设置好友标签");
        this.start_pull_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String charSequence = FriendLikeCommentLayout.this.start_pull.getText().toString();
                    int unused = FriendLikeCommentLayout.this.startIndex = Integer.parseInt(charSequence.substring(charSequence.indexOf("第") + 1, charSequence.indexOf("个")));
                } catch (Exception unused2) {
                }
                DialogUIUtils.dialogSetStartPoint(FriendLikeCommentLayout.this.mContext, FriendLikeCommentLayout.this.startIndex, new AlertEditDialog.OnEditTextListener() {
                    public void result(int i) {
                        int unused = FriendLikeCommentLayout.this.startIndex = i;
                        TextView access$000 = FriendLikeCommentLayout.this.start_pull;
                        access$000.setText("从第" + i + "个微信好友开始");
                        FontUtils.changeFontColor(FriendLikeCommentLayout.this.mContext, FriendLikeCommentLayout.this.start_pull);
                        if (FriendLikeCommentLayout.this.sendTypeMode == 0) {
                            int unused2 = FriendLikeCommentLayout.this.startIndexAll = FriendLikeCommentLayout.this.startIndex;
                        } else if (FriendLikeCommentLayout.this.sendTypeMode == 1) {
                            int unused3 = FriendLikeCommentLayout.this.startIndexPart = FriendLikeCommentLayout.this.startIndex;
                        } else if (FriendLikeCommentLayout.this.sendTypeMode == 2) {
                            int unused4 = FriendLikeCommentLayout.this.startIndexShield = FriendLikeCommentLayout.this.startIndex;
                        }
                        if (FriendLikeCommentLayout.this.isSaveKeyAll && FriendLikeCommentLayout.this.sendTypeMode == 0) {
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyAll, Integer.valueOf(FriendLikeCommentLayout.this.startIndex));
                        } else if (FriendLikeCommentLayout.this.isSaveKeyPart && FriendLikeCommentLayout.this.sendTypeMode == 1) {
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyPart, Integer.valueOf(FriendLikeCommentLayout.this.startIndex));
                        } else if (FriendLikeCommentLayout.this.isSaveKeyShield && FriendLikeCommentLayout.this.sendTypeMode == 2) {
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyShield, Integer.valueOf(FriendLikeCommentLayout.this.startIndex));
                        }
                        if (FriendLikeCommentLayout.this.listener != null) {
                            FriendLikeCommentLayout.this.listener.sendStartIndex(i);
                        }
                    }
                });
            }
        });
        this.jump_label_layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FriendLikeCommentLayout.this.mContext, ObtainTagActivity.class);
                intent.putExtra("selects", FriendLikeCommentLayout.this.friendsTags);
                MyApplication.instance.getBaseActivity().startActivity(intent);
            }
        });
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int i2 = 0;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        FriendLikeCommentLayout.this.start_pull_layout.setVisibility(0);
                        FriendLikeCommentLayout.this.jump_label_layout.setVisibility(8);
                        try {
                            int unused = FriendLikeCommentLayout.this.startIndexAll = ((Integer) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyAll, 1)).intValue();
                        } catch (Exception unused2) {
                            int unused3 = FriendLikeCommentLayout.this.startIndexAll = 1;
                        }
                        TextView access$000 = FriendLikeCommentLayout.this.start_pull;
                        access$000.setText("从第" + FriendLikeCommentLayout.this.startIndexAll + "个微信好友开始");
                        FontUtils.changeFontColor(FriendLikeCommentLayout.this.mContext, FriendLikeCommentLayout.this.start_pull);
                        String unused4 = FriendLikeCommentLayout.this.allLabel = (String) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyAllLabel, "");
                        if (FriendLikeCommentLayout.this.allLabel == null || "".equals(FriendLikeCommentLayout.this.allLabel)) {
                            FriendLikeCommentLayout.this.jump_label.setText("点我设置好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyAllLabel, "");
                        } else {
                            FriendLikeCommentLayout.this.jump_label.setText(FriendLikeCommentLayout.this.allLabel);
                        }
                        String unused5 = FriendLikeCommentLayout.this.friendsTags = FriendLikeCommentLayout.this.allLabel;
                        if (FriendLikeCommentLayout.this.listener != null) {
                            int unused6 = FriendLikeCommentLayout.this.sendTypeMode = 0;
                            FriendLikeCommentLayout.this.listener.sendType(FriendLikeCommentLayout.this.sendTypeMode);
                            FriendLikeCommentLayout.this.listener.sendStartIndex(FriendLikeCommentLayout.this.startIndexAll);
                            LogUtils.log("WS_FRIEND_MODE.3");
                            FriendLikeCommentLayout.this.listener.sendMembers(new LinkedHashSet());
                            return;
                        }
                        return;
                    case 2131296497:
                        FriendLikeCommentLayout.this.start_pull_layout.setVisibility(0);
                        FriendLikeCommentLayout.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused7 = FriendLikeCommentLayout.this.startIndexPart = ((Integer) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyPart, 1)).intValue();
                        } catch (Exception unused8) {
                            int unused9 = FriendLikeCommentLayout.this.startIndexPart = 1;
                        }
                        TextView access$0002 = FriendLikeCommentLayout.this.start_pull;
                        access$0002.setText("从第" + FriendLikeCommentLayout.this.startIndexPart + "个微信好友开始");
                        FontUtils.changeFontColor(FriendLikeCommentLayout.this.mContext, FriendLikeCommentLayout.this.start_pull);
                        String unused10 = FriendLikeCommentLayout.this.partLabel = (String) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyPartLabel, "");
                        if (FriendLikeCommentLayout.this.partLabel == null || "".equals(FriendLikeCommentLayout.this.partLabel)) {
                            FriendLikeCommentLayout.this.jump_label.setText("点我设置好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyPartLabel, "");
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyPartLabelMember, "");
                        } else {
                            FriendLikeCommentLayout.this.jump_label.setText(FriendLikeCommentLayout.this.partLabel);
                        }
                        String unused11 = FriendLikeCommentLayout.this.friendsTags = FriendLikeCommentLayout.this.partLabel;
                        if (FriendLikeCommentLayout.this.listener != null) {
                            int unused12 = FriendLikeCommentLayout.this.sendTypeMode = 1;
                            FriendLikeCommentLayout.this.listener.sendType(FriendLikeCommentLayout.this.sendTypeMode);
                            FriendLikeCommentLayout.this.listener.sendStartIndex(FriendLikeCommentLayout.this.startIndexPart);
                        }
                        String unused13 = FriendLikeCommentLayout.this.partLabelMember = (String) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyPartLabelMember, "");
                        if (FriendLikeCommentLayout.this.partLabelMember != null && !"".equals(FriendLikeCommentLayout.this.partLabelMember)) {
                            LinkedHashSet linkedHashSet = new LinkedHashSet();
                            if (FriendLikeCommentLayout.this.partLabelMember.contains(";")) {
                                String[] split = FriendLikeCommentLayout.this.partLabelMember.split(";");
                                while (i2 < split.length) {
                                    linkedHashSet.add(split[i2]);
                                    i2++;
                                }
                            } else {
                                linkedHashSet.add(FriendLikeCommentLayout.this.partLabelMember);
                            }
                            if (FriendLikeCommentLayout.this.listener != null) {
                                LogUtils.log("WS_FRIEND_MODE.4");
                                FriendLikeCommentLayout.this.listener.sendMembers(linkedHashSet);
                                return;
                            }
                            return;
                        } else if (FriendLikeCommentLayout.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.5");
                            FriendLikeCommentLayout.this.listener.sendMembers(new LinkedHashSet());
                            return;
                        } else {
                            return;
                        }
                    case 2131296498:
                        FriendLikeCommentLayout.this.start_pull_layout.setVisibility(0);
                        FriendLikeCommentLayout.this.jump_label_layout.setVisibility(0);
                        try {
                            int unused14 = FriendLikeCommentLayout.this.startIndexShield = ((Integer) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyShield, 1)).intValue();
                        } catch (Exception unused15) {
                            int unused16 = FriendLikeCommentLayout.this.startIndexShield = 1;
                        }
                        TextView access$0003 = FriendLikeCommentLayout.this.start_pull;
                        access$0003.setText("从第" + FriendLikeCommentLayout.this.startIndexShield + "个微信好友开始");
                        FontUtils.changeFontColor(FriendLikeCommentLayout.this.mContext, FriendLikeCommentLayout.this.start_pull);
                        String unused17 = FriendLikeCommentLayout.this.shieldLabel = (String) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyShieldLabel, "");
                        if (FriendLikeCommentLayout.this.shieldLabel == null || "".equals(FriendLikeCommentLayout.this.shieldLabel)) {
                            FriendLikeCommentLayout.this.jump_label.setText("点我设置好友标签");
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyShieldLabel, "");
                            SPUtils.put(MyApplication.getConText(), FriendLikeCommentLayout.this.keyShieldLabelMember, "");
                        } else {
                            FriendLikeCommentLayout.this.jump_label.setText(FriendLikeCommentLayout.this.shieldLabel);
                        }
                        String unused18 = FriendLikeCommentLayout.this.friendsTags = FriendLikeCommentLayout.this.shieldLabel;
                        if (FriendLikeCommentLayout.this.listener != null) {
                            int unused19 = FriendLikeCommentLayout.this.sendTypeMode = 2;
                            FriendLikeCommentLayout.this.listener.sendStartIndex(FriendLikeCommentLayout.this.startIndexShield);
                            FriendLikeCommentLayout.this.listener.sendType(FriendLikeCommentLayout.this.sendTypeMode);
                        }
                        String unused20 = FriendLikeCommentLayout.this.shieldLabelMember = (String) SPUtils.get(MyApplication.getConText(), FriendLikeCommentLayout.this.keyShieldLabelMember, "");
                        if (FriendLikeCommentLayout.this.shieldLabelMember != null && !"".equals(FriendLikeCommentLayout.this.shieldLabelMember)) {
                            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                            if (FriendLikeCommentLayout.this.shieldLabelMember.contains(";")) {
                                String[] split2 = FriendLikeCommentLayout.this.shieldLabelMember.split(";");
                                while (i2 < split2.length) {
                                    linkedHashSet2.add(split2[i2]);
                                    i2++;
                                }
                            } else {
                                linkedHashSet2.add(FriendLikeCommentLayout.this.shieldLabelMember);
                            }
                            if (FriendLikeCommentLayout.this.listener != null) {
                                LogUtils.log("WS_FRIEND_MODE.6");
                                FriendLikeCommentLayout.this.listener.sendMembers(linkedHashSet2);
                                return;
                            }
                            return;
                        } else if (FriendLikeCommentLayout.this.listener != null) {
                            LogUtils.log("WS_FRIEND_MODE.7");
                            FriendLikeCommentLayout.this.listener.sendMembers(new LinkedHashSet());
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
}
