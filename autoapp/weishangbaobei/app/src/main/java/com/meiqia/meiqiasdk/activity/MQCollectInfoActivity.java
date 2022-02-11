package com.meiqia.meiqiasdk.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.meiqia.core.MQManager;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.core.callback.SimpleCallback;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.util.HttpUtils;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.stub.StubApp;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MQCollectInfoActivity extends MQBaseActivity implements View.OnClickListener {
    public static final String AGENT_ID = "agent_id";
    private static final long AUTO_DISMISS_TOP_TIP_TIME = 2000;
    public static final String GROUP_ID = "group_id";
    private static final String TYPE_MULTIPLE_CHOICE = "multiple_choice";
    private static final String TYPE_SINGLE_CHOICE = "single_choice";
    private static final String TYPE_TEXT = "text";
    /* access modifiers changed from: private */
    public boolean isDestroy = false;
    private Runnable mAutoDismissTopTipRunnable;
    private List<BaseItem> mBaseItemList;
    private RelativeLayout mBodyRl;
    /* access modifiers changed from: private */
    public CodeAuthItem mCodeAuthItem;
    private LinearLayout mContainerLl;
    private Handler mHandler;
    private MQInquireForm mInquireForm;
    private ProgressBar mLoadingProgressBar;
    /* access modifiers changed from: private */
    public RelativeLayout mRootView;
    private View mScrollView;
    private TextView mSubmitTv;
    /* access modifiers changed from: private */
    public TextView mTopTipViewTv;

    static {
        StubApp.interface11(6066);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.isDestroy = true;
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public int getLayoutRes() {
        return R.layout.mq_activity_collect_info;
    }

    /* access modifiers changed from: protected */
    public void initView(Bundle bundle) {
        this.mLoadingProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        this.mSubmitTv = (TextView) findViewById(R.id.submit_tv);
        this.mContainerLl = (LinearLayout) findViewById(R.id.container_ll);
        this.mRootView = (RelativeLayout) findViewById(R.id.root);
        this.mBodyRl = (RelativeLayout) findViewById(R.id.body_rl);
        this.mScrollView = findViewById(R.id.content_sv);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.mSubmitTv.setOnClickListener(this);
    }

    /* JADX WARNING: type inference failed for: r12v0, types: [java.lang.Object, com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$BaseItem] */
    /* JADX WARNING: type inference failed for: r12v1 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r4v5, types: [com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$SingleChoiceItem] */
    /* JADX WARNING: type inference failed for: r4v6, types: [com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$MultipleChoiceItem] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processLogic(android.os.Bundle r14) {
        /*
            r13 = this;
            com.meiqia.core.bean.MQInquireForm r14 = r13.getInquireForm()
            org.json.JSONObject r14 = r14.getInputs()
            java.lang.String r0 = "title"
            java.lang.String r14 = r14.optString(r0)
            r13.setTitle(r14)
            boolean r14 = r13.isSubmitAndAllReturnedCustomer()
            if (r14 == 0) goto L_0x001b
            r13.goToChatActivity()
            return
        L_0x001b:
            com.meiqia.core.bean.MQInquireForm r14 = r13.getInquireForm()     // Catch:{ JSONException -> 0x00cc }
            org.json.JSONObject r14 = r14.getInputs()     // Catch:{ JSONException -> 0x00cc }
            java.lang.String r0 = "fields"
            org.json.JSONArray r14 = r14.optJSONArray(r0)     // Catch:{ JSONException -> 0x00cc }
            r0 = 0
            r1 = 0
        L_0x002b:
            int r2 = r14.length()     // Catch:{ JSONException -> 0x00cc }
            if (r1 >= r2) goto L_0x00d0
            org.json.JSONObject r2 = r14.getJSONObject(r1)     // Catch:{ JSONException -> 0x00cc }
            java.lang.String r3 = "display_name"
            java.lang.String r6 = r2.optString(r3)     // Catch:{ JSONException -> 0x00cc }
            java.lang.String r3 = "field_name"
            java.lang.String r7 = r2.optString(r3)     // Catch:{ JSONException -> 0x00cc }
            r13.hookField(r7, r2)     // Catch:{ JSONException -> 0x00cc }
            java.lang.String r3 = "type"
            java.lang.String r8 = r2.optString(r3)     // Catch:{ JSONException -> 0x00cc }
            java.lang.String r3 = "choices"
            java.lang.String r9 = r2.optString(r3)     // Catch:{ JSONException -> 0x00cc }
            java.lang.String r3 = "optional"
            boolean r3 = r2.optBoolean(r3)     // Catch:{ JSONException -> 0x00cc }
            java.lang.String r4 = "ignore_returned_customer"
            boolean r2 = r2.optBoolean(r4)     // Catch:{ JSONException -> 0x00cc }
            r4 = 0
            r5 = -1
            int r10 = r8.hashCode()     // Catch:{ JSONException -> 0x00cc }
            r11 = 3556653(0x36452d, float:4.983932E-39)
            if (r10 == r11) goto L_0x0086
            r11 = 1669382832(0x6380c2b0, float:4.750424E21)
            if (r10 == r11) goto L_0x007c
            r11 = 1770845560(0x698cf578, float:2.13011E25)
            if (r10 == r11) goto L_0x0072
            goto L_0x008f
        L_0x0072:
            java.lang.String r10 = "single_choice"
            boolean r10 = r8.equals(r10)     // Catch:{ JSONException -> 0x00cc }
            if (r10 == 0) goto L_0x008f
            r5 = 1
            goto L_0x008f
        L_0x007c:
            java.lang.String r10 = "multiple_choice"
            boolean r10 = r8.equals(r10)     // Catch:{ JSONException -> 0x00cc }
            if (r10 == 0) goto L_0x008f
            r5 = 2
            goto L_0x008f
        L_0x0086:
            java.lang.String r10 = "text"
            boolean r10 = r8.equals(r10)     // Catch:{ JSONException -> 0x00cc }
            if (r10 == 0) goto L_0x008f
            r5 = 0
        L_0x008f:
            switch(r5) {
                case 0: goto L_0x00a8;
                case 1: goto L_0x009e;
                case 2: goto L_0x0094;
                default: goto L_0x0092;
            }     // Catch:{ JSONException -> 0x00cc }
        L_0x0092:
            r12 = r4
            goto L_0x00b2
        L_0x0094:
            com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$MultipleChoiceItem r12 = new com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$MultipleChoiceItem     // Catch:{ JSONException -> 0x00cc }
            r4 = r12
            r5 = r13
            r10 = r3
            r11 = r2
            r4.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ JSONException -> 0x00cc }
            goto L_0x00b2
        L_0x009e:
            com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$SingleChoiceItem r12 = new com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$SingleChoiceItem     // Catch:{ JSONException -> 0x00cc }
            r4 = r12
            r5 = r13
            r10 = r3
            r11 = r2
            r4.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ JSONException -> 0x00cc }
            goto L_0x00b2
        L_0x00a8:
            com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$TextItem r11 = new com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$TextItem     // Catch:{ JSONException -> 0x00cc }
            r4 = r11
            r5 = r13
            r9 = r3
            r10 = r2
            r4.<init>(r6, r7, r8, r9, r10)     // Catch:{ JSONException -> 0x00cc }
            r12 = r11
        L_0x00b2:
            if (r12 == 0) goto L_0x00c8
            android.view.View r2 = r12.getView()     // Catch:{ JSONException -> 0x00cc }
            if (r2 == 0) goto L_0x00c8
            java.util.List<com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$BaseItem> r2 = r13.mBaseItemList     // Catch:{ JSONException -> 0x00cc }
            r2.add(r12)     // Catch:{ JSONException -> 0x00cc }
            android.widget.LinearLayout r2 = r13.mContainerLl     // Catch:{ JSONException -> 0x00cc }
            android.view.View r3 = r12.getView()     // Catch:{ JSONException -> 0x00cc }
            r2.addView(r3)     // Catch:{ JSONException -> 0x00cc }
        L_0x00c8:
            int r1 = r1 + 1
            goto L_0x002b
        L_0x00cc:
            r14 = move-exception
            r14.printStackTrace()
        L_0x00d0:
            com.meiqia.core.bean.MQInquireForm r14 = r13.getInquireForm()
            boolean r14 = r14.isCaptcha()
            if (r14 == 0) goto L_0x00f4
            com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$CodeAuthItem r14 = new com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$CodeAuthItem
            r14.<init>()
            r13.mCodeAuthItem = r14
            java.util.List<com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$BaseItem> r0 = r13.mBaseItemList
            com.meiqia.meiqiasdk.activity.MQCollectInfoActivity$CodeAuthItem r1 = r13.mCodeAuthItem
            r0.add(r1)
            android.widget.LinearLayout r0 = r13.mContainerLl
            android.view.View r1 = r14.getView()
            r0.addView(r1)
            r14.refreshAuthCode()
        L_0x00f4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.activity.MQCollectInfoActivity.processLogic(android.os.Bundle):void");
    }

    private void hookField(String str, JSONObject jSONObject) {
        if ("gender".equals(str)) {
            try {
                jSONObject.put("type", TYPE_SINGLE_CHOICE);
                jSONObject.put(MQInquireForm.KEY_INPUTS_FIELDS_CHOICES, getResources().getString(R.string.mq_inquire_gender_choice));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public MQInquireForm getInquireForm() {
        if (this.mInquireForm == null) {
            this.mInquireForm = MQManager.getInstance(this).getMQInquireForm();
        }
        return this.mInquireForm;
    }

    public void popInvalidTip() {
        if (this.mTopTipViewTv == null) {
            this.mTopTipViewTv = (TextView) getLayoutInflater().inflate(R.layout.mq_top_pop_tip, (ViewGroup) null);
            this.mTopTipViewTv.setText(R.string.mq_tip_required_before_submit);
            this.mTopTipViewTv.setBackgroundColor(getResources().getColor(R.color.mq_error));
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.mq_top_tip_height);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, dimensionPixelOffset);
            layoutParams.addRule(6, R.id.content_sv);
            this.mRootView.addView(this.mTopTipViewTv, 1, layoutParams);
            ViewCompat.setTranslationY(this.mTopTipViewTv, (float) (-dimensionPixelOffset));
            ViewCompat.animate(this.mTopTipViewTv).translationY(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(300).start();
            if (this.mAutoDismissTopTipRunnable == null) {
                this.mAutoDismissTopTipRunnable = new Runnable() {
                    public void run() {
                        MQCollectInfoActivity.this.popInvalidTip();
                    }
                };
            }
            this.mHandler.postDelayed(this.mAutoDismissTopTipRunnable, AUTO_DISMISS_TOP_TIP_TIME);
            return;
        }
        this.mHandler.removeCallbacks(this.mAutoDismissTopTipRunnable);
        ViewCompat.animate(this.mTopTipViewTv).translationY((float) (-this.mTopTipViewTv.getHeight())).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                MQCollectInfoActivity.this.mRootView.removeView(MQCollectInfoActivity.this.mTopTipViewTv);
                TextView unused = MQCollectInfoActivity.this.mTopTipViewTv = null;
            }
        }).setDuration(300).start();
    }

    public void onClick(View view) {
        if (view.getId() != R.id.submit_tv) {
            return;
        }
        if (checkData()) {
            submitData();
        } else {
            popInvalidTip();
        }
    }

    private boolean checkData() {
        boolean z = true;
        if (this.mBaseItemList.size() > 0) {
            for (BaseItem checkValid : this.mBaseItemList) {
                if (!checkValid.checkValid()) {
                    z = false;
                }
            }
        }
        return z;
    }

    private void submitData() {
        HashMap hashMap;
        Object value;
        HashMap hashMap2 = new HashMap();
        if (this.mBaseItemList.size() > 0) {
            for (BaseItem next : this.mBaseItemList) {
                if (!(next instanceof CodeAuthItem) && (value = next.getValue()) != null && !TextUtils.isEmpty(value.toString())) {
                    hashMap2.put(next.getFileName(), value);
                }
            }
        }
        if (this.mCodeAuthItem != null) {
            hashMap = new HashMap();
            hashMap.put("Captcha-Token", this.mCodeAuthItem.getCaptcha_token());
            hashMap.put("Captcha-Value", this.mCodeAuthItem.getValue());
        } else {
            hashMap = null;
        }
        if (getIntent() == null) {
            finish();
        }
        String stringExtra = getIntent().getStringExtra(MQConversationActivity.CLIENT_ID);
        String stringExtra2 = getIntent().getStringExtra(MQConversationActivity.CUSTOMIZED_ID);
        if (TextUtils.isEmpty(stringExtra)) {
            if (!TextUtils.isEmpty(stringExtra2)) {
                stringExtra = stringExtra2;
            } else {
                stringExtra = MQManager.getInstance(this).getCurrentClientId();
            }
        }
        if (getInquireForm().isCaptcha()) {
            submitState(true);
            MQManager.getInstance(this).submitInquireForm(stringExtra, hashMap2, hashMap, new SimpleCallback() {
                public void onSuccess() {
                    MQCollectInfoActivity.this.goToChatActivity();
                }

                public void onFailure(int i, String str) {
                    MQCollectInfoActivity.this.submitState(false);
                    if (i == 400) {
                        MQCollectInfoActivity.this.mCodeAuthItem.refreshAuthCode();
                        Toast.makeText(MQCollectInfoActivity.this, R.string.mq_error_auth_code_wrong, 0).show();
                    } else if (i == 19999) {
                        Toast.makeText(MQCollectInfoActivity.this, R.string.mq_title_net_not_work, 0).show();
                    } else {
                        Toast.makeText(MQCollectInfoActivity.this, R.string.mq_error_submit_form, 0).show();
                    }
                }
            });
            return;
        }
        MQManager.getInstance(this).submitInquireForm(stringExtra, hashMap2, hashMap, (SimpleCallback) null);
        goToChatActivity();
    }

    /* access modifiers changed from: private */
    public void goToChatActivity() {
        String str;
        Intent intent = new Intent(this, MQConversationActivity.class);
        String str2 = null;
        if (getIntent() != null) {
            str2 = getIntent().getStringExtra(AGENT_ID);
            str = getIntent().getStringExtra(GROUP_ID);
            intent.putExtras(getIntent());
        } else {
            str = null;
        }
        intent.putExtra(MQConversationActivity.PRE_SEND_TEXT, getIntent().getStringExtra(MQConversationActivity.PRE_SEND_TEXT));
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str)) {
            MQManager.getInstance(this).setScheduledAgentOrGroupWithId(str2, str);
        }
        startActivity(intent);
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public void submitState(boolean z) {
        if (z) {
            this.mLoadingProgressBar.setVisibility(0);
            this.mSubmitTv.setVisibility(8);
            this.mScrollView.setVisibility(8);
            return;
        }
        this.mLoadingProgressBar.setVisibility(8);
        this.mSubmitTv.setVisibility(0);
        this.mScrollView.setVisibility(0);
    }

    private boolean isSubmitAndAllReturnedCustomer() {
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
            } catch (Exception unused) {
                return true;
            }
        }
        return true;
    }

    private abstract class BaseItem {
        public String displayName;
        public String fieldName;
        public boolean ignoreReturnCustomer;
        public boolean optional;
        public View rootView;
        public TextView titleTv;
        public String type;

        /* access modifiers changed from: package-private */
        public abstract void findViews();

        public abstract Object getValue();

        public abstract boolean isValid();

        BaseItem(String str, String str2, String str3, boolean z, boolean z2) {
            this.displayName = str;
            this.fieldName = str2;
            this.type = str3;
            this.optional = z;
            this.ignoreReturnCustomer = z2;
            init();
        }

        public BaseItem() {
            this.optional = false;
            init();
        }

        /* access modifiers changed from: protected */
        public void init() {
            findViews();
            initTitle();
        }

        public void initTitle() {
            if (!TextUtils.isEmpty(this.displayName)) {
                this.titleTv.setText(this.displayName);
            }
            if (!this.optional) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.titleTv.getText() + " *");
                spannableStringBuilder.setSpan(new ForegroundColorSpan(MQCollectInfoActivity.this.getResources().getColor(R.color.mq_error)), this.titleTv.getText().length() + 1, spannableStringBuilder.length(), 33);
                this.titleTv.setText(spannableStringBuilder);
            }
        }

        public boolean checkValid() {
            if (this.optional) {
                return true;
            }
            boolean isValid = isValid();
            if (!isValid) {
                invalidState();
            } else {
                validState();
            }
            return isValid;
        }

        /* access modifiers changed from: protected */
        public void validState() {
            this.titleTv.setTextColor(MQCollectInfoActivity.this.getResources().getColor(R.color.mq_form_tip_textColor));
        }

        /* access modifiers changed from: protected */
        public void invalidState() {
            this.titleTv.setTextColor(MQCollectInfoActivity.this.getResources().getColor(R.color.mq_error));
        }

        public String getFileName() {
            return this.fieldName;
        }

        public View getView() {
            if (!this.ignoreReturnCustomer || !MQCollectInfoActivity.this.getInquireForm().isSubmitForm()) {
                return this.rootView;
            }
            return null;
        }
    }

    private class TextItem extends BaseItem {
        EditText contentEt;

        TextItem(String str, String str2, String str3, boolean z, boolean z2) {
            super(str, str2, str3, z, z2);
            setListeners();
            setInputType();
        }

        /* access modifiers changed from: package-private */
        public void findViews() {
            this.rootView = MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_type_text, (ViewGroup) null);
            this.titleTv = (TextView) this.rootView.findViewById(R.id.title_tv);
            this.contentEt = (EditText) this.rootView.findViewById(R.id.content_et);
        }

        private void setListeners() {
            this.contentEt.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    TextItem.this.checkValid();
                }
            });
        }

        private void setInputType() {
            if ("tel".equals(this.fieldName)) {
                this.contentEt.setInputType(3);
            } else if ("qq".equals(this.fieldName) || "age".equals(this.fieldName)) {
                this.contentEt.setInputType(2);
            } else if ("email".equals(this.fieldName)) {
                this.contentEt.setInputType(32);
            }
        }

        public String getContent() {
            return this.contentEt.getText().toString();
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.contentEt.getText().toString());
        }

        public String getValue() {
            return this.contentEt.getText().toString();
        }
    }

    private class SingleChoiceItem extends BaseItem implements CompoundButton.OnCheckedChangeListener {
        private String choices;
        RadioGroup radioGroup;

        SingleChoiceItem(String str, String str2, String str3, String str4, boolean z, boolean z2) {
            super(str, str2, str3, z, z2);
            this.choices = str4;
            initData();
        }

        private void initData() {
            try {
                JSONArray jSONArray = new JSONArray(this.choices);
                for (int i = 0; i < jSONArray.length(); i++) {
                    RadioButton radioButton = (RadioButton) MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_radio_btn, (ViewGroup) null);
                    radioButton.setText(jSONArray.getString(i));
                    radioButton.setTag(jSONArray.get(i));
                    radioButton.setId(-1);
                    radioButton.setOnCheckedChangeListener(this);
                    MQUtils.tintCompoundButton(radioButton, R.drawable.mq_radio_btn_uncheck, R.drawable.mq_radio_btn_checked);
                    this.radioGroup.addView(radioButton, -1, MQUtils.dip2px(MQCollectInfoActivity.this, 48.0f));
                }
            } catch (JSONException e) {
                this.rootView.setVisibility(8);
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: package-private */
        public void findViews() {
            this.rootView = MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_type_single_choice, (ViewGroup) null);
            this.titleTv = (TextView) this.rootView.findViewById(R.id.title_tv);
            this.radioGroup = (RadioGroup) this.rootView.findViewById(R.id.radio_group);
        }

        public boolean isValid() {
            return this.radioGroup.getCheckedRadioButtonId() != -1;
        }

        public Object getValue() {
            for (int i = 0; i < this.radioGroup.getChildCount(); i++) {
                View childAt = this.radioGroup.getChildAt(i);
                if (this.radioGroup.getCheckedRadioButtonId() == childAt.getId()) {
                    return childAt.getTag();
                }
            }
            return null;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                validState();
            } else {
                checkValid();
            }
        }
    }

    private class MultipleChoiceItem extends BaseItem implements CompoundButton.OnCheckedChangeListener {
        private List<CheckBox> checkBoxList = new ArrayList();
        private LinearLayout checkboxContainer;
        private String choices;

        MultipleChoiceItem(String str, String str2, String str3, String str4, boolean z, boolean z2) {
            super(str, str2, str3, z, z2);
            this.choices = str4;
            initData();
        }

        private void initData() {
            try {
                JSONArray jSONArray = new JSONArray(this.choices);
                for (int i = 0; i < jSONArray.length(); i++) {
                    CheckBox checkBox = (CheckBox) MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_checkbox, (ViewGroup) null);
                    checkBox.setText(jSONArray.getString(i));
                    checkBox.setOnCheckedChangeListener(this);
                    checkBox.setTag(jSONArray.get(i));
                    MQUtils.tintCompoundButton(checkBox, R.drawable.mq_checkbox_uncheck, R.drawable.mq_checkbox_unchecked);
                    this.checkboxContainer.addView(checkBox, -1, MQUtils.dip2px(MQCollectInfoActivity.this, 48.0f));
                    this.checkBoxList.add(checkBox);
                }
            } catch (JSONException e) {
                this.rootView.setVisibility(8);
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: package-private */
        public void findViews() {
            this.rootView = MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_type_multiple_choice, (ViewGroup) null);
            this.titleTv = (TextView) this.rootView.findViewById(R.id.title_tv);
            this.checkboxContainer = (LinearLayout) this.rootView.findViewById(R.id.checkbox_container);
        }

        public boolean isValid() {
            for (CheckBox isChecked : this.checkBoxList) {
                if (isChecked.isChecked()) {
                    return true;
                }
            }
            return false;
        }

        public Object getValue() {
            JSONArray jSONArray = new JSONArray();
            for (CheckBox next : this.checkBoxList) {
                if (next.isChecked()) {
                    jSONArray.put(next.getTag());
                }
            }
            return jSONArray;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            checkValid();
        }
    }

    private class CodeAuthItem extends BaseItem {
        private EditText authCodeEt;
        /* access modifiers changed from: private */
        public ImageView authCodeIv;
        /* access modifiers changed from: private */
        public String captcha_image;
        /* access modifiers changed from: private */
        public String captcha_token;

        public CodeAuthItem() {
            super();
            this.authCodeIv.setOnClickListener(new View.OnClickListener(MQCollectInfoActivity.this) {
                public void onClick(View view) {
                    CodeAuthItem.this.refreshAuthCode();
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void findViews() {
            this.rootView = MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_type_auth_code, (ViewGroup) null);
            this.titleTv = (TextView) this.rootView.findViewById(R.id.title_tv);
            this.authCodeEt = (EditText) this.rootView.findViewById(R.id.auth_code_et);
            this.authCodeIv = (ImageView) this.rootView.findViewById(R.id.auth_code_iv);
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.authCodeEt.getText().toString());
        }

        public String getValue() {
            return this.authCodeEt.getText().toString();
        }

        public String getCaptcha_token() {
            return this.captcha_token;
        }

        public View getView() {
            return this.rootView;
        }

        public void refreshAuthCode() {
            this.authCodeIv.setClickable(false);
            this.authCodeIv.setImageBitmap((Bitmap) null);
            this.authCodeEt.setText("");
            new Thread(new Runnable() {
                public void run() {
                    AnonymousClass2 r1;
                    MQCollectInfoActivity mQCollectInfoActivity;
                    try {
                        JSONObject authCode = HttpUtils.getInstance().getAuthCode();
                        String unused = CodeAuthItem.this.captcha_image = authCode.optString("captcha_image_url");
                        String unused2 = CodeAuthItem.this.captcha_token = authCode.optString("captcha_token");
                        MQCollectInfoActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (!MQCollectInfoActivity.this.isDestroy) {
                                    try {
                                        MQImage.displayImage(MQCollectInfoActivity.this, CodeAuthItem.this.authCodeIv, CodeAuthItem.this.captcha_image, R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, CodeAuthItem.this.authCodeIv.getWidth(), CodeAuthItem.this.authCodeIv.getHeight(), (MQImageLoader.MQDisplayImageListener) null);
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        });
                        mQCollectInfoActivity = MQCollectInfoActivity.this;
                        r1 = new Runnable() {
                            public void run() {
                                CodeAuthItem.this.authCodeIv.setClickable(true);
                            }
                        };
                    } catch (Exception e) {
                        e.printStackTrace();
                        mQCollectInfoActivity = MQCollectInfoActivity.this;
                        r1 = new Runnable() {
                            public void run() {
                                CodeAuthItem.this.authCodeIv.setClickable(true);
                            }
                        };
                    } catch (Throwable th) {
                        MQCollectInfoActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                CodeAuthItem.this.authCodeIv.setClickable(true);
                            }
                        });
                        throw th;
                    }
                    mQCollectInfoActivity.runOnUiThread(r1);
                }
            }).start();
        }
    }
}
