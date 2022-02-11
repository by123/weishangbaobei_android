package com.wx.assistants.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import com.wx.assistants.adapter.TagRemarkAdapter;
import com.wx.assistants.view.TagCloudLayout;
import java.util.ArrayList;
import java.util.Arrays;

public class RemarkFriendLayout extends LinearLayout implements TextWatcher {
    /* access modifiers changed from: private */
    public EditText editTextAddName;
    /* access modifiers changed from: private */
    public boolean isRemark = false;
    /* access modifiers changed from: private */
    public OnRemarkListener listener;
    private Context mContext;
    /* access modifiers changed from: private */
    public ArrayList<String> mRemark;
    /* access modifiers changed from: private */
    public String recordRemarkName = "";
    private Switch remarkSwitchBtn;
    private TagCloudLayout remarkViewGroup;
    /* access modifiers changed from: private */
    public LinearLayout remarksLayout;
    private TagRemarkAdapter tagRemarkAdapter;

    public interface OnRemarkListener {
        void remark(boolean z, String str);
    }

    public RemarkFriendLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public RemarkFriendLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public RemarkFriendLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void addData(int i, String str) {
        if (this.mRemark != null && this.tagRemarkAdapter != null && this.remarkViewGroup != null) {
            this.mRemark.add(i, str);
            this.tagRemarkAdapter.notifyDataSetChanged();
        }
    }

    public void afterTextChanged(Editable editable) {
        if (editable != null) {
            this.recordRemarkName = editable.toString();
            if (this.listener != null) {
                this.listener.remark(this.isRemark, this.recordRemarkName);
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427688, this, true);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.remarkViewGroup = (TagCloudLayout) findViewById(2131297237);
        this.remarksLayout = (LinearLayout) findViewById(2131297238);
        this.editTextAddName = (EditText) findViewById(2131296620);
        this.editTextAddName.addTextChangedListener(this);
        this.mRemark = new ArrayList<>();
        this.mRemark.addAll(Arrays.asList(getResources().getStringArray(2130903048)));
        this.tagRemarkAdapter = new TagRemarkAdapter(this.mContext, this.mRemark);
        this.remarkViewGroup.setAdapter(this.tagRemarkAdapter);
        this.remarkViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            public void itemClick(int i) {
                RemarkFriendLayout.this.editTextAddName.setText((String) RemarkFriendLayout.this.mRemark.get(i));
            }
        });
        this.remarkSwitchBtn = (Switch) findViewById(2131297234);
        this.remarkSwitchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = RemarkFriendLayout.this.isRemark = true;
                    RemarkFriendLayout.this.remarksLayout.setVisibility(0);
                    RemarkFriendLayout.this.listener.remark(RemarkFriendLayout.this.isRemark, RemarkFriendLayout.this.recordRemarkName);
                    return;
                }
                boolean unused2 = RemarkFriendLayout.this.isRemark = false;
                RemarkFriendLayout.this.editTextAddName.setText("");
                RemarkFriendLayout.this.remarksLayout.setVisibility(8);
                RemarkFriendLayout.this.listener.remark(RemarkFriendLayout.this.isRemark, "");
            }
        });
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void setOnRemarkListener(OnRemarkListener onRemarkListener) {
        if (onRemarkListener != null) {
            this.listener = onRemarkListener;
        }
    }
}
