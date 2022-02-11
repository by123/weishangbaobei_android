package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.SPUtils;

public class CleanZombieDNDLayout extends LinearLayout {
    private RadioGroup circulateRG;
    /* access modifiers changed from: private */
    public int dndMode = 0;
    /* access modifiers changed from: private */
    public String dndStr = "";
    /* access modifiers changed from: private */
    public EditText editLeavingMessage;
    /* access modifiers changed from: private */
    public TextView extra;
    /* access modifiers changed from: private */
    public DNDModelListener listener;
    private String sayContent;

    public interface DNDModelListener {
        void dndMode(int i);

        void dndModeString(String str);
    }

    public void setDNDModelListener(DNDModelListener dNDModelListener) {
        if (dNDModelListener != null) {
            this.listener = dNDModelListener;
        }
    }

    public CleanZombieDNDLayout(Context context) {
        super(context);
    }

    public CleanZombieDNDLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initCirculateNumView(context);
    }

    public CleanZombieDNDLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427739, this, true);
    }

    public String getContent() {
        return this.editLeavingMessage.getText().toString();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.circulateRG = (RadioGroup) findViewById(2131296500);
        this.extra = (TextView) findViewById(2131296742);
        this.editLeavingMessage = (EditText) findViewById(2131296616);
        String str = (String) SPUtils.get(MyApplication.getConText(), "clean_zombie_fan_ndn_content", "");
        if (str != null && !"".equals(str)) {
            this.editLeavingMessage.setText(str);
        }
        this.extra.setText(getResources().getString(2131689537));
        this.circulateRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case 2131296496:
                        CleanZombieDNDLayout.this.extra.setText(CleanZombieDNDLayout.this.getResources().getString(2131689537));
                        CleanZombieDNDLayout.this.editLeavingMessage.setHint("请输入要发送的文字，系统将自动发送您编辑的文字来检测死粉");
                        CleanZombieDNDLayout.this.editLeavingMessage.setVisibility(0);
                        if (CleanZombieDNDLayout.this.listener != null) {
                            int unused = CleanZombieDNDLayout.this.dndMode = 0;
                            CleanZombieDNDLayout.this.listener.dndMode(CleanZombieDNDLayout.this.dndMode);
                            CleanZombieDNDLayout.this.listener.dndModeString(CleanZombieDNDLayout.this.dndStr);
                            return;
                        }
                        return;
                    case 2131296497:
                        CleanZombieDNDLayout.this.extra.setText(CleanZombieDNDLayout.this.getResources().getString(2131689538));
                        CleanZombieDNDLayout.this.editLeavingMessage.setHint("请输入要发送的文字，系统将自动发送您编辑的文字来检测死粉");
                        CleanZombieDNDLayout.this.editLeavingMessage.setVisibility(0);
                        if (CleanZombieDNDLayout.this.listener != null) {
                            int unused2 = CleanZombieDNDLayout.this.dndMode = 1;
                            CleanZombieDNDLayout.this.listener.dndMode(CleanZombieDNDLayout.this.dndMode);
                            CleanZombieDNDLayout.this.listener.dndModeString(CleanZombieDNDLayout.this.dndStr);
                            return;
                        }
                        return;
                    case 2131296498:
                        CleanZombieDNDLayout.this.extra.setText(CleanZombieDNDLayout.this.getResources().getString(2131689539));
                        CleanZombieDNDLayout.this.editLeavingMessage.setHint("请输入要发送的文字，系统将自动发送您编辑的文字来检测死粉");
                        CleanZombieDNDLayout.this.editLeavingMessage.setVisibility(8);
                        if (CleanZombieDNDLayout.this.listener != null) {
                            int unused3 = CleanZombieDNDLayout.this.dndMode = 2;
                            CleanZombieDNDLayout.this.listener.dndMode(CleanZombieDNDLayout.this.dndMode);
                            CleanZombieDNDLayout.this.listener.dndModeString("");
                            return;
                        }
                        return;
                    case 2131296499:
                        CleanZombieDNDLayout.this.extra.setText(CleanZombieDNDLayout.this.getResources().getString(2131689540));
                        CleanZombieDNDLayout.this.editLeavingMessage.setHint("发送收藏后，可附带一条留言信息。");
                        CleanZombieDNDLayout.this.editLeavingMessage.setVisibility(0);
                        if (CleanZombieDNDLayout.this.listener != null) {
                            int unused4 = CleanZombieDNDLayout.this.dndMode = 3;
                            CleanZombieDNDLayout.this.listener.dndMode(CleanZombieDNDLayout.this.dndMode);
                            CleanZombieDNDLayout.this.listener.dndModeString(CleanZombieDNDLayout.this.dndStr);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
        this.editLeavingMessage.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                String unused = CleanZombieDNDLayout.this.dndStr = editable.toString();
                if (CleanZombieDNDLayout.this.dndStr == null || "".equals(CleanZombieDNDLayout.this.dndStr)) {
                    SPUtils.put(MyApplication.getConText(), "clean_zombie_fan_ndn_content", editable.toString());
                } else {
                    SPUtils.put(MyApplication.getConText(), "clean_zombie_fan_ndn_content", editable.toString());
                }
                if (CleanZombieDNDLayout.this.listener != null) {
                    CleanZombieDNDLayout.this.listener.dndMode(CleanZombieDNDLayout.this.dndMode);
                    CleanZombieDNDLayout.this.listener.dndModeString(CleanZombieDNDLayout.this.dndStr);
                }
            }
        });
    }
}
