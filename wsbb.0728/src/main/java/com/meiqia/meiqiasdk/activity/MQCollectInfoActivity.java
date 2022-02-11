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

    private abstract class BaseItem {
        public String displayName;
        public String fieldName;
        public boolean ignoreReturnCustomer;
        public boolean optional;
        public View rootView;
        public TextView titleTv;
        public String type;

        public BaseItem() {
            this.optional = false;
            init();
        }

        BaseItem(String str, String str2, String str3, boolean z, boolean z2) {
            this.displayName = str;
            this.fieldName = str2;
            this.type = str3;
            this.optional = z;
            this.ignoreReturnCustomer = z2;
            init();
        }

        public boolean checkValid() {
            if (this.optional) {
                return true;
            }
            boolean isValid = isValid();
            if (!isValid) {
                invalidState();
                return isValid;
            }
            validState();
            return isValid;
        }

        /* access modifiers changed from: package-private */
        public abstract void findViews();

        public String getFileName() {
            return this.fieldName;
        }

        public abstract Object getValue();

        public View getView() {
            if (!this.ignoreReturnCustomer || !MQCollectInfoActivity.this.getInquireForm().isSubmitForm()) {
                return this.rootView;
            }
            return null;
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

        /* access modifiers changed from: protected */
        public void invalidState() {
            this.titleTv.setTextColor(MQCollectInfoActivity.this.getResources().getColor(R.color.mq_error));
        }

        public abstract boolean isValid();

        /* access modifiers changed from: protected */
        public void validState() {
            this.titleTv.setTextColor(MQCollectInfoActivity.this.getResources().getColor(R.color.mq_form_tip_textColor));
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

        public String getCaptcha_token() {
            return this.captcha_token;
        }

        public String getValue() {
            return this.authCodeEt.getText().toString();
        }

        public View getView() {
            return this.rootView;
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.authCodeEt.getText().toString());
        }

        public void refreshAuthCode() {
            this.authCodeIv.setClickable(false);
            this.authCodeIv.setImageBitmap((Bitmap) null);
            this.authCodeEt.setText("");
            new Thread(new Runnable() {
                public void run() {
                    MQCollectInfoActivity mQCollectInfoActivity;
                    AnonymousClass2 r0;
                    try {
                        JSONObject authCode = HttpUtils.getInstance().getAuthCode();
                        String unused = CodeAuthItem.this.captcha_image = authCode.optString("captcha_image_url");
                        String unused2 = CodeAuthItem.this.captcha_token = authCode.optString("captcha_token");
                        MQCollectInfoActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                if (!MQCollectInfoActivity.this.isDestroy) {
                                    try {
                                        MQImage.displayImage(MQCollectInfoActivity.this, CodeAuthItem.this.authCodeIv, CodeAuthItem.this.captcha_image, R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, CodeAuthItem.this.authCodeIv.getWidth(), CodeAuthItem.this.authCodeIv.getHeight(), (MQImageLoader.MQDisplayImageListener) null);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        });
                        mQCollectInfoActivity = MQCollectInfoActivity.this;
                        r0 = new Runnable() {
                            public void run() {
                                CodeAuthItem.this.authCodeIv.setClickable(true);
                            }
                        };
                    } catch (Exception e) {
                        e.printStackTrace();
                        mQCollectInfoActivity = MQCollectInfoActivity.this;
                        r0 = new Runnable() {
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
                    mQCollectInfoActivity.runOnUiThread(r0);
                }
            }).start();
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
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < jSONArray.length()) {
                        CheckBox checkBox = (CheckBox) MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_checkbox, (ViewGroup) null);
                        checkBox.setText(jSONArray.getString(i2));
                        checkBox.setOnCheckedChangeListener(this);
                        checkBox.setTag(jSONArray.get(i2));
                        MQUtils.tintCompoundButton(checkBox, R.drawable.mq_checkbox_uncheck, R.drawable.mq_checkbox_unchecked);
                        this.checkboxContainer.addView(checkBox, -1, MQUtils.dip2px(MQCollectInfoActivity.this, 48.0f));
                        this.checkBoxList.add(checkBox);
                        i = i2 + 1;
                    } else {
                        return;
                    }
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

        public Object getValue() {
            JSONArray jSONArray = new JSONArray();
            for (CheckBox next : this.checkBoxList) {
                if (next.isChecked()) {
                    jSONArray.put(next.getTag());
                }
            }
            return jSONArray;
        }

        public boolean isValid() {
            for (CheckBox isChecked : this.checkBoxList) {
                if (isChecked.isChecked()) {
                    return true;
                }
            }
            return false;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            checkValid();
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
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 < jSONArray.length()) {
                        RadioButton radioButton = (RadioButton) MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_radio_btn, (ViewGroup) null);
                        radioButton.setText(jSONArray.getString(i2));
                        radioButton.setTag(jSONArray.get(i2));
                        radioButton.setId(-1);
                        radioButton.setOnCheckedChangeListener(this);
                        MQUtils.tintCompoundButton(radioButton, R.drawable.mq_radio_btn_uncheck, R.drawable.mq_radio_btn_checked);
                        this.radioGroup.addView(radioButton, -1, MQUtils.dip2px(MQCollectInfoActivity.this, 48.0f));
                        i = i2 + 1;
                    } else {
                        return;
                    }
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

        public Object getValue() {
            for (int i = 0; i < this.radioGroup.getChildCount(); i++) {
                View childAt = this.radioGroup.getChildAt(i);
                if (this.radioGroup.getCheckedRadioButtonId() == childAt.getId()) {
                    return childAt.getTag();
                }
            }
            return null;
        }

        public boolean isValid() {
            return this.radioGroup.getCheckedRadioButtonId() != -1;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                validState();
            } else {
                checkValid();
            }
        }
    }

    private class TextItem extends BaseItem {
        EditText contentEt;

        TextItem(String str, String str2, String str3, boolean z, boolean z2) {
            super(str, str2, str3, z, z2);
            setListeners();
            setInputType();
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

        /* access modifiers changed from: package-private */
        public void findViews() {
            this.rootView = MQCollectInfoActivity.this.getLayoutInflater().inflate(R.layout.mq_item_form_type_text, (ViewGroup) null);
            this.titleTv = (TextView) this.rootView.findViewById(R.id.title_tv);
            this.contentEt = (EditText) this.rootView.findViewById(R.id.content_et);
        }

        public String getContent() {
            return this.contentEt.getText().toString();
        }

        public String getValue() {
            return this.contentEt.getText().toString();
        }

        public boolean isValid() {
            return !TextUtils.isEmpty(this.contentEt.getText().toString());
        }
    }

    static {
        StubApp.interface11(6066);
    }

    private boolean checkData() {
        if (this.mBaseItemList.size() <= 0) {
            return true;
        }
        boolean z = true;
        for (BaseItem checkValid : this.mBaseItemList) {
            if (!checkValid.checkValid()) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public MQInquireForm getInquireForm() {
        if (this.mInquireForm == null) {
            this.mInquireForm = MQManager.getInstance(this).getMQInquireForm();
        }
        return this.mInquireForm;
    }

    /* access modifiers changed from: private */
    public void goToChatActivity() {
        String str;
        String str2;
        Intent intent = new Intent(this, MQConversationActivity.class);
        if (getIntent() != null) {
            str = getIntent().getStringExtra(AGENT_ID);
            String stringExtra = getIntent().getStringExtra(GROUP_ID);
            intent.putExtras(getIntent());
            str2 = stringExtra;
        } else {
            str = null;
            str2 = null;
        }
        intent.putExtra(MQConversationActivity.PRE_SEND_TEXT, getIntent().getStringExtra(MQConversationActivity.PRE_SEND_TEXT));
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            MQManager.getInstance(this).setScheduledAgentOrGroupWithId(str, str2);
        }
        startActivity(intent);
        onBackPressed();
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
            } catch (Exception e) {
                return true;
            }
        }
        return true;
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
            stringExtra = !TextUtils.isEmpty(stringExtra2) ? stringExtra2 : MQManager.getInstance(this).getCurrentClientId();
        }
        if (getInquireForm().isCaptcha()) {
            submitState(true);
            MQManager.getInstance(this).submitInquireForm(stringExtra, hashMap2, hashMap, new SimpleCallback() {
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

                public void onSuccess() {
                    MQCollectInfoActivity.this.goToChatActivity();
                }
            });
            return;
        }
        MQManager.getInstance(this).submitInquireForm(stringExtra, hashMap2, hashMap, (SimpleCallback) null);
        goToChatActivity();
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

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.isDestroy = true;
        super.onDestroy();
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

    /* access modifiers changed from: protected */
    public void processLogic(Bundle bundle) {
        BaseItem textItem;
        setTitle(getInquireForm().getInputs().optString("title"));
        if (isSubmitAndAllReturnedCustomer()) {
            goToChatActivity();
            return;
        }
        try {
            JSONArray optJSONArray = getInquireForm().getInputs().optJSONArray(MQInquireForm.KEY_INPUTS_FIELDS);
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                String optString = jSONObject.optString("display_name");
                String optString2 = jSONObject.optString(MQInquireForm.KEY_INPUTS_FIELDS_FIELD_NAME);
                hookField(optString2, jSONObject);
                String optString3 = jSONObject.optString("type");
                String optString4 = jSONObject.optString(MQInquireForm.KEY_INPUTS_FIELDS_CHOICES);
                boolean optBoolean = jSONObject.optBoolean(MQInquireForm.KEY_INPUTS_FIELDS_OPTIONAL);
                boolean optBoolean2 = jSONObject.optBoolean(MQInquireForm.KEY_INPUTS_FIELDS_IGNORE_RETURNED_CUSTOMER);
                char c = 65535;
                int hashCode = optString3.hashCode();
                if (hashCode != 3556653) {
                    if (hashCode != 1669382832) {
                        if (hashCode == 1770845560 && optString3.equals(TYPE_SINGLE_CHOICE)) {
                            c = 1;
                        }
                    } else if (optString3.equals(TYPE_MULTIPLE_CHOICE)) {
                        c = 2;
                    }
                } else if (optString3.equals("text")) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        textItem = new TextItem(optString, optString2, optString3, optBoolean, optBoolean2);
                        break;
                    case 1:
                        textItem = new SingleChoiceItem(optString, optString2, optString3, optString4, optBoolean, optBoolean2);
                        break;
                    case 2:
                        textItem = new MultipleChoiceItem(optString, optString2, optString3, optString4, optBoolean, optBoolean2);
                        break;
                    default:
                        textItem = null;
                        break;
                }
                if (!(textItem == null || textItem.getView() == null)) {
                    this.mBaseItemList.add(textItem);
                    this.mContainerLl.addView(textItem.getView());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (getInquireForm().isCaptcha()) {
            CodeAuthItem codeAuthItem = new CodeAuthItem();
            this.mCodeAuthItem = codeAuthItem;
            this.mBaseItemList.add(this.mCodeAuthItem);
            this.mContainerLl.addView(codeAuthItem.getView());
            codeAuthItem.refreshAuthCode();
        }
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.mSubmitTv.setOnClickListener(this);
    }
}
