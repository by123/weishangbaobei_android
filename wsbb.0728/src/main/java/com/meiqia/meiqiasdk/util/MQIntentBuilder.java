package com.meiqia.meiqiasdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.meiqia.core.MQManager;
import com.meiqia.core.MQScheduleRule;
import com.meiqia.meiqiasdk.activity.MQCollectInfoActivity;
import com.meiqia.meiqiasdk.activity.MQConversationActivity;
import com.meiqia.meiqiasdk.activity.MQInquiryFormActivity;
import java.io.File;
import java.util.HashMap;

public class MQIntentBuilder {
    private String mAgentId;
    private Context mContext;
    private String mGroupId;
    private Intent mIntent;
    private MQScheduleRule mScheduleRule = MQScheduleRule.REDIRECT_ENTERPRISE;

    public MQIntentBuilder(Context context) {
        this.mContext = context;
        this.mIntent = getIntent(context, MQConversationActivity.class);
    }

    public MQIntentBuilder(Context context, Class<? extends MQConversationActivity> cls) {
        this.mContext = context;
        this.mIntent = getIntent(context, cls);
    }

    private void checkClient(String str) {
        if (!TextUtils.equals(MQUtils.getString(this.mContext, MQInquiryFormActivity.CURRENT_CLIENT, (String) null), str)) {
            MQManager.getInstance(this.mContext).getEnterpriseConfig().survey.setHas_submitted_form(false);
        }
        MQUtils.putString(this.mContext, MQInquiryFormActivity.CURRENT_CLIENT, str);
    }

    private Intent getIntent(Context context, Class<? extends MQConversationActivity> cls) {
        if (MQManager.getInstance(context).getCurrentAgent() != null) {
            this.mIntent = new Intent(context, cls);
            return this.mIntent;
        }
        boolean isMenusOpen = MQManager.getInstance(context).getMQInquireForm().isMenusOpen();
        boolean isInputsOpen = MQManager.getInstance(context).getMQInquireForm().isInputsOpen();
        if (isMenusOpen) {
            this.mIntent = new Intent(context, MQInquiryFormActivity.class);
        } else if (isInputsOpen) {
            this.mIntent = new Intent(context, MQCollectInfoActivity.class);
        } else {
            this.mIntent = new Intent(context, cls);
        }
        return this.mIntent;
    }

    public Intent build() {
        MQManager.getInstance(this.mContext).setScheduledAgentOrGroupWithId(this.mAgentId, this.mGroupId, this.mScheduleRule);
        if (!(this.mContext instanceof Activity)) {
            this.mIntent.addFlags(268435456);
        }
        return this.mIntent;
    }

    public MQIntentBuilder setClientId(String str) {
        this.mIntent.putExtra(MQConversationActivity.CLIENT_ID, str);
        checkClient(str);
        return this;
    }

    public MQIntentBuilder setClientInfo(HashMap<String, String> hashMap) {
        this.mIntent.putExtra(MQConversationActivity.CLIENT_INFO, hashMap);
        return this;
    }

    public MQIntentBuilder setCustomizedId(String str) {
        this.mIntent.putExtra(MQConversationActivity.CUSTOMIZED_ID, str);
        checkClient(str);
        return this;
    }

    public MQIntentBuilder setPreSendImageMessage(File file) {
        if (file != null && file.exists()) {
            this.mIntent.putExtra(MQConversationActivity.PRE_SEND_IMAGE_PATH, file.getAbsolutePath());
        }
        return this;
    }

    public MQIntentBuilder setPreSendTextMessage(String str) {
        this.mIntent.putExtra(MQConversationActivity.PRE_SEND_TEXT, str);
        return this;
    }

    public MQIntentBuilder setScheduleRule(MQScheduleRule mQScheduleRule) {
        this.mScheduleRule = mQScheduleRule;
        return this;
    }

    public MQIntentBuilder setScheduledAgent(String str) {
        this.mAgentId = str;
        return this;
    }

    public MQIntentBuilder setScheduledGroup(String str) {
        this.mGroupId = str;
        return this;
    }

    public MQIntentBuilder updateClientInfo(HashMap<String, String> hashMap) {
        this.mIntent.putExtra(MQConversationActivity.UPDATE_CLIENT_INFO, hashMap);
        return this;
    }
}
