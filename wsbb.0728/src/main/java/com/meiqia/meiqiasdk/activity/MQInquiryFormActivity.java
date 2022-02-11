package com.meiqia.meiqiasdk.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.meiqiasdk.R;
import com.stub.StubApp;
import org.json.JSONArray;
import org.json.JSONObject;

public class MQInquiryFormActivity extends MQBaseActivity {
    public static final String CURRENT_CLIENT = "CURRENT_CLIENT";
    private LinearLayout mContainer;
    private MQInquireForm mInquireForm;
    private TextView mQuestionTitleTv;

    private class FormItem implements View.OnClickListener {
        private TextView contentTb = ((TextView) this.rootView.findViewById(R.id.content_tv));
        private View rootView;
        private String target;
        private String target_kind;

        public FormItem(Context context, String str, String str2) {
            this.target_kind = str;
            this.target = str2;
            this.rootView = LayoutInflater.from(context).inflate(R.layout.mq_item_form_inquiry, (ViewGroup) null);
            this.rootView.setOnClickListener(this);
        }

        private String getContent() {
            return this.contentTb.getText().toString();
        }

        public View getItem() {
            return this.rootView;
        }

        public void onClick(View view) {
            String str;
            String str2;
            if (!TextUtils.isEmpty(this.target_kind)) {
                str2 = "group".equals(this.target_kind) ? this.target : null;
                str = "agent".equals(this.target_kind) ? this.target : null;
            } else {
                str = null;
                str2 = null;
            }
            JSONArray optJSONArray = MQInquiryFormActivity.this.getInquireForm().getInputs().optJSONArray(MQInquireForm.KEY_INPUTS_FIELDS);
            if (!MQInquiryFormActivity.this.getInquireForm().isInputsOpen() || MQInquiryFormActivity.this.isSubmitAndAllReturnedCustomer() || optJSONArray.length() <= 0) {
                Intent intent = new Intent(MQInquiryFormActivity.this, MQConversationActivity.class);
                if (MQInquiryFormActivity.this.getIntent() != null) {
                    intent.putExtras(MQInquiryFormActivity.this.getIntent());
                }
                intent.putExtra(MQConversationActivity.PRE_SEND_TEXT, getContent());
                if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                    MQManager.getInstance(MQInquiryFormActivity.this).setScheduledAgentOrGroupWithId(str, str2);
                }
                MQInquiryFormActivity.this.startActivity(intent);
            } else {
                Intent intent2 = new Intent(MQInquiryFormActivity.this, MQCollectInfoActivity.class);
                if (MQInquiryFormActivity.this.getIntent() != null) {
                    intent2.putExtras(MQInquiryFormActivity.this.getIntent());
                }
                if (!TextUtils.isEmpty(str2)) {
                    intent2.putExtra(MQCollectInfoActivity.GROUP_ID, str2);
                }
                if (!TextUtils.isEmpty(str)) {
                    intent2.putExtra(MQCollectInfoActivity.AGENT_ID, str);
                }
                intent2.putExtra(MQConversationActivity.PRE_SEND_TEXT, getContent());
                MQInquiryFormActivity.this.startActivity(intent2);
            }
            MQInquiryFormActivity.this.onBackPressed();
        }

        public void setContent(String str) {
            this.contentTb.setText(str);
        }
    }

    static {
        StubApp.interface11(6093);
    }

    /* access modifiers changed from: private */
    public MQInquireForm getInquireForm() {
        if (this.mInquireForm == null) {
            this.mInquireForm = MQManager.getInstance(this).getMQInquireForm();
        }
        return this.mInquireForm;
    }

    /* access modifiers changed from: private */
    public boolean isSubmitAndAllReturnedCustomer() {
        if (!getInquireForm().isSubmitForm()) {
            return false;
        }
        JSONArray optJSONArray = getInquireForm().getInputs().optJSONArray(MQInquireForm.KEY_INPUTS_FIELDS);
        int i = 0;
        while (i < optJSONArray.length()) {
            try {
                if (!optJSONArray.getJSONObject(i).optBoolean(MQInquireForm.KEY_INPUTS_FIELDS_IGNORE_RETURNED_CUSTOMER)) {
                    return false;
                }
                i++;
            } catch (Exception e) {
                return true;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int getLayoutRes() {
        return R.layout.mq_activity_inquiry_form;
    }

    /* access modifiers changed from: protected */
    public void initView(Bundle bundle) {
        this.mQuestionTitleTv = (TextView) findViewById(R.id.question_title);
        this.mContainer = (LinearLayout) findViewById(R.id.container_ll);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void processLogic(Bundle bundle) {
        try {
            JSONObject menus = getInquireForm().getMenus();
            this.mQuestionTitleTv.setText(menus.optString("title"));
            JSONArray optJSONArray = menus.optJSONArray(MQInquireForm.KEY_MENUS_ASSIGNMENTS);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject = optJSONArray.getJSONObject(i);
                    String optString = jSONObject.optString(MQInquireForm.KEY_MENUS_ASSIGNMENTS_TARGET_KIND);
                    String optString2 = jSONObject.optString(MQInquireForm.KEY_MENUS_ASSIGNMENTS_TARGET);
                    String optString3 = jSONObject.optString(MQInquireForm.KEY_MENUS_ASSIGNMENTS_DESCRIPTION);
                    FormItem formItem = new FormItem(this, optString, optString2);
                    formItem.setContent(optString3);
                    this.mContainer.addView(formItem.getItem());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void setListener() {
    }
}
