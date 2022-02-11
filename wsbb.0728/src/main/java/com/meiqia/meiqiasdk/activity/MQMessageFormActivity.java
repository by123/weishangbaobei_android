package com.meiqia.meiqiasdk.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQEnterpriseConfig;
import com.meiqia.core.bean.MQMessage;
import com.meiqia.core.callback.OnMessageSendCallback;
import com.meiqia.core.callback.OnTicketCategoriesCallback;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.callback.SimpleCallback;
import com.meiqia.meiqiasdk.dialog.MQListDialog;
import com.meiqia.meiqiasdk.dialog.MQLoadingDialog;
import com.meiqia.meiqiasdk.model.MessageFormInputModel;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQMessageFormInputLayout;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class MQMessageFormActivity extends Activity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public boolean isPause = false;
    private ImageView mBackIv;
    private RelativeLayout mBackRl;
    private TextView mBackTv;
    /* access modifiers changed from: private */
    public MQListDialog mCategoryDialog;
    /* access modifiers changed from: private */
    public String mCurrentCategoryId;
    /* access modifiers changed from: private */
    public List<Map<String, String>> mDataList = new ArrayList();
    private LinearLayout mInputContainerLl;
    private MQLoadingDialog mLoadingDialog;
    private ArrayList<MQMessageFormInputLayout> mMessageFormInputLayouts = new ArrayList<>();
    private ArrayList<MessageFormInputModel> mMessageFormInputModels = new ArrayList<>();
    private TextView mMessageTipTv;
    private TextView mSubmitTv;
    private RelativeLayout mTitleRl;
    private TextView mTitleTv;

    static {
        StubApp.interface11(6100);
    }

    private void applyCustomUIConfig() {
        if (-1 != MQConfig.ui.backArrowIconResId) {
            this.mBackIv.setImageResource(MQConfig.ui.backArrowIconResId);
        }
        MQUtils.applyCustomUITintDrawable(this.mTitleRl, 17170443, R.color.mq_activity_title_bg, MQConfig.ui.titleBackgroundResId);
        MQUtils.applyCustomUITextAndImageColor(R.color.mq_activity_title_textColor, MQConfig.ui.titleTextColorResId, this.mBackIv, this.mBackTv, this.mTitleTv, this.mSubmitTv);
        MQUtils.applyCustomUITitleGravity(this.mBackTv, this.mTitleTv);
    }

    private MQEnterpriseConfig getEnterpriseConfig() {
        return MQManager.getInstance(this).getEnterpriseConfig();
    }

    /* access modifiers changed from: private */
    public void handleFormInputLayouts() {
        this.mInputContainerLl.removeAllViews();
        this.mMessageFormInputModels.clear();
        this.mMessageFormInputLayouts.clear();
        MessageFormInputModel messageFormInputModel = new MessageFormInputModel();
        messageFormInputModel.tip = getString(R.string.mq_leave_msg);
        messageFormInputModel.key = "content";
        messageFormInputModel.required = true;
        messageFormInputModel.hint = getString(R.string.mq_leave_msg_hint);
        messageFormInputModel.inputType = 1;
        messageFormInputModel.singleLine = false;
        this.mMessageFormInputModels.add(messageFormInputModel);
        if (!TextUtils.isEmpty(getEnterpriseConfig().ticketConfig.getQq())) {
            if ("open".equals(getEnterpriseConfig().ticketConfig.getName())) {
                MessageFormInputModel messageFormInputModel2 = new MessageFormInputModel();
                messageFormInputModel2.tip = getString(R.string.mq_name);
                messageFormInputModel2.key = "name";
                messageFormInputModel2.required = false;
                messageFormInputModel2.hint = getString(R.string.mq_name_hint);
                messageFormInputModel2.inputType = 1;
                this.mMessageFormInputModels.add(messageFormInputModel2);
            }
            if ("open".equals(getEnterpriseConfig().ticketConfig.getTel())) {
                MessageFormInputModel messageFormInputModel3 = new MessageFormInputModel();
                messageFormInputModel3.tip = getString(R.string.mq_phone);
                messageFormInputModel3.key = "tel";
                messageFormInputModel3.required = false;
                messageFormInputModel3.hint = getString(R.string.mq_phone_hint);
                messageFormInputModel3.inputType = 3;
                this.mMessageFormInputModels.add(messageFormInputModel3);
            }
            if ("open".equals(getEnterpriseConfig().ticketConfig.getEmail())) {
                MessageFormInputModel messageFormInputModel4 = new MessageFormInputModel();
                messageFormInputModel4.tip = getString(R.string.mq_email);
                messageFormInputModel4.key = "email";
                messageFormInputModel4.required = false;
                messageFormInputModel4.hint = getString(R.string.mq_email_hint);
                messageFormInputModel4.inputType = 32;
                this.mMessageFormInputModels.add(messageFormInputModel4);
            }
            if ("open".equals(getEnterpriseConfig().ticketConfig.getWechat())) {
                MessageFormInputModel messageFormInputModel5 = new MessageFormInputModel();
                messageFormInputModel5.tip = getString(R.string.mq_wechat);
                messageFormInputModel5.key = "weixin";
                messageFormInputModel5.required = false;
                messageFormInputModel5.hint = getString(R.string.mq_wechat_hint);
                messageFormInputModel5.inputType = 1;
                this.mMessageFormInputModels.add(messageFormInputModel5);
            }
            if ("open".equals(getEnterpriseConfig().ticketConfig.getQq())) {
                MessageFormInputModel messageFormInputModel6 = new MessageFormInputModel();
                messageFormInputModel6.tip = getString(R.string.mq_qq);
                messageFormInputModel6.key = "qq";
                messageFormInputModel6.required = false;
                messageFormInputModel6.hint = getString(R.string.mq_qq_hint);
                messageFormInputModel6.inputType = 2;
                this.mMessageFormInputModels.add(messageFormInputModel6);
            }
        }
        Iterator<MessageFormInputModel> it = this.mMessageFormInputModels.iterator();
        while (it.hasNext()) {
            MQMessageFormInputLayout mQMessageFormInputLayout = new MQMessageFormInputLayout(this, it.next());
            this.mInputContainerLl.addView(mQMessageFormInputLayout);
            this.mMessageFormInputLayouts.add(mQMessageFormInputLayout);
        }
    }

    private void handleLeaveMessageIntro() {
        refreshLeaveMessageIntro();
    }

    private void initListener() {
        this.mBackRl.setOnClickListener(this);
        this.mSubmitTv.setOnClickListener(this);
    }

    private void initView() {
        setContentView(R.layout.mq_activity_message_form);
        this.mTitleRl = (RelativeLayout) findViewById(R.id.title_rl);
        this.mBackRl = (RelativeLayout) findViewById(R.id.back_rl);
        this.mBackTv = (TextView) findViewById(R.id.back_tv);
        this.mBackIv = (ImageView) findViewById(R.id.back_iv);
        this.mTitleTv = (TextView) findViewById(R.id.title_tv);
        this.mSubmitTv = (TextView) findViewById(R.id.submit_tv);
        this.mMessageTipTv = (TextView) findViewById(R.id.message_tip_tv);
        this.mInputContainerLl = (LinearLayout) findViewById(R.id.input_container_ll);
    }

    private void popTicketCategoriesChooseDialog() {
        MQManager.getInstance(this).getTicketCategories(new OnTicketCategoriesCallback() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(JSONArray jSONArray) {
                if (jSONArray != null && !MQMessageFormActivity.this.isPause) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            String optString = optJSONObject.optString("id");
                            String optString2 = optJSONObject.optString("name");
                            HashMap hashMap = new HashMap();
                            hashMap.put("name", optString2);
                            hashMap.put("id", optString);
                            MQMessageFormActivity.this.mDataList.add(hashMap);
                        }
                    }
                    if (MQMessageFormActivity.this.mDataList.size() != 0) {
                        MQListDialog unused = MQMessageFormActivity.this.mCategoryDialog = new MQListDialog((Activity) MQMessageFormActivity.this, R.string.mq_choose_ticket_category, (List<Map<String, String>>) MQMessageFormActivity.this.mDataList, (AdapterView.OnItemClickListener) new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                                String unused = MQMessageFormActivity.this.mCurrentCategoryId = (String) ((Map) MQMessageFormActivity.this.mDataList.get(i)).get("id");
                            }
                        });
                        try {
                            MQMessageFormActivity.this.mCategoryDialog.show();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });
    }

    private void processLogic(Bundle bundle) {
        applyCustomUIConfig();
        handleFormInputLayouts();
        handleLeaveMessageIntro();
        refreshEnterpriseConfigAndContent();
        popTicketCategoriesChooseDialog();
    }

    private void refreshEnterpriseConfigAndContent() {
        MQConfig.getController(this).refreshEnterpriseConfig(new SimpleCallback() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess() {
                MQMessageFormActivity.this.handleFormInputLayouts();
                MQMessageFormActivity.this.refreshLeaveMessageIntro();
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshLeaveMessageIntro() {
        String intro = MQConfig.getController(this).getEnterpriseConfig().ticketConfig.getIntro();
        if (TextUtils.isEmpty(intro)) {
            this.mMessageTipTv.setVisibility(8);
            return;
        }
        this.mMessageTipTv.setText(intro);
        this.mMessageTipTv.setVisibility(0);
    }

    private void showLoadingDialog() {
        if (this.mLoadingDialog == null) {
            this.mLoadingDialog = new MQLoadingDialog(this);
            this.mLoadingDialog.setCancelable(false);
        }
        this.mLoadingDialog.show();
    }

    private void submit() {
        String text = this.mMessageFormInputLayouts.get(0).getText();
        if (TextUtils.isEmpty(text)) {
            MQUtils.show((Context) this, (CharSequence) getString(R.string.mq_param_not_allow_empty, new Object[]{getString(R.string.mq_leave_msg)}));
            return;
        }
        boolean z = !MQEnterpriseConfig.SINGLE.equals(getEnterpriseConfig().ticketConfig.getContactRule());
        HashMap hashMap = new HashMap();
        int size = this.mMessageFormInputModels.size();
        boolean z2 = true;
        int i = 1;
        while (i < size) {
            MessageFormInputModel messageFormInputModel = this.mMessageFormInputModels.get(i);
            String text2 = this.mMessageFormInputLayouts.get(i).getText();
            boolean z3 = !TextUtils.isEmpty(text2) ? false : z2;
            if (!TextUtils.isEmpty(text2) || !z) {
                if (!TextUtils.isEmpty(text2)) {
                    hashMap.put(messageFormInputModel.key, text2);
                }
                z2 = z3;
                i++;
            } else {
                MQUtils.show((Context) this, (CharSequence) getString(R.string.mq_param_not_allow_empty, new Object[]{messageFormInputModel.tip}));
                return;
            }
        }
        if (z || !z2) {
            final long currentTimeMillis = System.currentTimeMillis();
            showLoadingDialog();
            MQMessage mQMessage = new MQMessage();
            mQMessage.setContent_type("text");
            mQMessage.setContent(text);
            MQManager.getInstance(this).submitTickets(mQMessage, this.mCurrentCategoryId, hashMap, new OnMessageSendCallback() {
                public void onFailure(MQMessage mQMessage, final int i, final String str) {
                    if (System.currentTimeMillis() - currentTimeMillis < 1500) {
                        MQUtils.runInUIThread(new Runnable() {
                            public void run() {
                                MQMessageFormActivity.this.dismissLoadingDialog();
                                if (20004 == i) {
                                    MQUtils.show(StubApp.getOrigApplicationContext(MQMessageFormActivity.this.getApplicationContext()), R.string.mq_submit_leave_msg_success);
                                    MQMessageFormActivity.this.finish();
                                    return;
                                }
                                MQUtils.show(StubApp.getOrigApplicationContext(MQMessageFormActivity.this.getApplicationContext()), (CharSequence) str);
                            }
                        }, System.currentTimeMillis() - currentTimeMillis);
                        return;
                    }
                    MQMessageFormActivity.this.dismissLoadingDialog();
                    MQUtils.show(StubApp.getOrigApplicationContext(MQMessageFormActivity.this.getApplicationContext()), (CharSequence) str);
                }

                public void onSuccess(MQMessage mQMessage, int i) {
                    if (System.currentTimeMillis() - currentTimeMillis < 1500) {
                        MQUtils.runInUIThread(new Runnable() {
                            public void run() {
                                MQMessageFormActivity.this.dismissLoadingDialog();
                                MQUtils.show(StubApp.getOrigApplicationContext(MQMessageFormActivity.this.getApplicationContext()), R.string.mq_submit_leave_msg_success);
                                MQMessageFormActivity.this.finish();
                            }
                        }, System.currentTimeMillis() - currentTimeMillis);
                        return;
                    }
                    MQMessageFormActivity.this.dismissLoadingDialog();
                    MQUtils.show(StubApp.getOrigApplicationContext(MQMessageFormActivity.this.getApplicationContext()), R.string.mq_submit_leave_msg_success);
                    MQMessageFormActivity.this.finish();
                }
            });
            return;
        }
        MQUtils.show((Context) this, (CharSequence) getString(R.string.mq_at_least_one_contact));
    }

    public void dismissLoadingDialog() {
        if (this.mLoadingDialog != null && this.mLoadingDialog.isShowing()) {
            this.mLoadingDialog.dismiss();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.back_rl) {
            finish();
        } else if (view.getId() == R.id.submit_tv) {
            submit();
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.isPause = true;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.mCategoryDialog != null && this.mCategoryDialog.isShowing()) {
            this.mCategoryDialog.dismiss();
        }
    }
}
