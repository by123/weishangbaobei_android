package com.meiqia.meiqiasdk.chatitem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.meiqia.core.MQManager;
import com.meiqia.core.callback.SuccessCallback;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.chatitem.MQBaseBubbleItem;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.model.ClueCardMessage;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQSimpleTextWatcher;
import com.meiqia.meiqiasdk.util.MQTimeUtils;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.widget.MQImageView;
import java.util.Calendar;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MQClueCardItem extends MQBaseBubbleItem {
    private MQImageView mAvatarIv;
    /* access modifiers changed from: private */
    public ClueCardMessage mClueCardMessage;
    /* access modifiers changed from: private */
    public LinearLayout mContainerLl;
    private int mPadding;
    private TextView mSendButton;
    private int mTextSize;

    /* access modifiers changed from: protected */
    public void setListener() {
    }

    public MQClueCardItem(Context context, MQBaseBubbleItem.Callback callback) {
        super(context, callback);
    }

    public void setMessage(ClueCardMessage clueCardMessage, Activity activity) {
        this.mContainerLl.removeAllViews();
        this.mClueCardMessage = clueCardMessage;
        MQImage.displayImage(activity, this.mAvatarIv, clueCardMessage.getAvatar(), R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, 100, 100, (MQImageLoader.MQDisplayImageListener) null);
        fillContentLl(this.mClueCardMessage.getContent());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fillContentLl(java.lang.String r10) {
        /*
            r9 = this;
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x00ab }
            r0.<init>(r10)     // Catch:{ Exception -> 0x00ab }
            r10 = 0
            r1 = 0
            r2 = 0
        L_0x0008:
            int r3 = r0.length()     // Catch:{ Exception -> 0x00ab }
            r4 = 1
            if (r1 >= r3) goto L_0x009e
            org.json.JSONObject r3 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x00ab }
            java.lang.String r5 = "type"
            java.lang.String r5 = r3.getString(r5)     // Catch:{ Exception -> 0x00ab }
            r6 = -1
            int r7 = r5.hashCode()     // Catch:{ Exception -> 0x00ab }
            r8 = 2
            switch(r7) {
                case -1034364087: goto L_0x0069;
                case -891985903: goto L_0x005f;
                case 3076014: goto L_0x0055;
                case 3556653: goto L_0x004b;
                case 94627080: goto L_0x0041;
                case 108270587: goto L_0x0037;
                case 1536891843: goto L_0x002d;
                case 1793702779: goto L_0x0023;
                default: goto L_0x0022;
            }     // Catch:{ Exception -> 0x00ab }
        L_0x0022:
            goto L_0x0073
        L_0x0023:
            java.lang.String r7 = "datetime"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 7
            goto L_0x0074
        L_0x002d:
            java.lang.String r7 = "checkbox"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 5
            goto L_0x0074
        L_0x0037:
            java.lang.String r7 = "radio"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 3
            goto L_0x0074
        L_0x0041:
            java.lang.String r7 = "check"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 4
            goto L_0x0074
        L_0x004b:
            java.lang.String r7 = "text"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 1
            goto L_0x0074
        L_0x0055:
            java.lang.String r7 = "date"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 6
            goto L_0x0074
        L_0x005f:
            java.lang.String r7 = "string"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 0
            goto L_0x0074
        L_0x0069:
            java.lang.String r7 = "number"
            boolean r5 = r5.equals(r7)     // Catch:{ Exception -> 0x00ab }
            if (r5 == 0) goto L_0x0073
            r5 = 2
            goto L_0x0074
        L_0x0073:
            r5 = -1
        L_0x0074:
            switch(r5) {
                case 0: goto L_0x008c;
                case 1: goto L_0x008c;
                case 2: goto L_0x0088;
                case 3: goto L_0x0084;
                case 4: goto L_0x0080;
                case 5: goto L_0x0080;
                case 6: goto L_0x007c;
                case 7: goto L_0x007c;
                default: goto L_0x0077;
            }     // Catch:{ Exception -> 0x00ab }
        L_0x0077:
            android.content.Context r2 = r9.getContext()     // Catch:{ Exception -> 0x00ab }
            goto L_0x0090
        L_0x007c:
            r9.addDatePick(r3)     // Catch:{ Exception -> 0x00ab }
            goto L_0x009a
        L_0x0080:
            r9.addCheckBox(r3)     // Catch:{ Exception -> 0x00ab }
            goto L_0x009a
        L_0x0084:
            r9.addRadioGroup(r3)     // Catch:{ Exception -> 0x00ab }
            goto L_0x009a
        L_0x0088:
            r9.addInputEdit(r3, r8)     // Catch:{ Exception -> 0x00ab }
            goto L_0x009a
        L_0x008c:
            r9.addInputEdit(r3, r4)     // Catch:{ Exception -> 0x00ab }
            goto L_0x009a
        L_0x0090:
            int r3 = com.meiqia.meiqiasdk.R.string.mq_unknown_msg_tip     // Catch:{ Exception -> 0x00ab }
            java.lang.String r2 = r2.getString(r3)     // Catch:{ Exception -> 0x00ab }
            r9.addNormalOrRichTextView(r2)     // Catch:{ Exception -> 0x00ab }
            r2 = 1
        L_0x009a:
            int r1 = r1 + 1
            goto L_0x0008
        L_0x009e:
            if (r2 == 0) goto L_0x00a7
            int r10 = r0.length()     // Catch:{ Exception -> 0x00ab }
            if (r10 != r4) goto L_0x00a7
            return
        L_0x00a7:
            r9.addSendButton()     // Catch:{ Exception -> 0x00ab }
            goto L_0x00af
        L_0x00ab:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.chatitem.MQClueCardItem.fillContentLl(java.lang.String):void");
    }

    private void addSendButton() {
        this.mSendButton = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.mq_item_clue_card_send, (ViewGroup) null);
        setSendButtonEnableState(this.mClueCardMessage.isAllEnable());
        this.mContainerLl.addView(this.mSendButton, -2, -2);
        this.mSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MQClueCardItem.this.mClueCardMessage.isAllEnable()) {
                    boolean z = true;
                    Iterator<String> keys = MQClueCardItem.this.mClueCardMessage.getAttrs().keys();
                    while (true) {
                        if (!keys.hasNext()) {
                            break;
                        }
                        String next = keys.next();
                        String optString = MQClueCardItem.this.mClueCardMessage.getAttrs().optString(next);
                        if (!TextUtils.equals(next, "qq")) {
                            if (!TextUtils.equals(next, "tel")) {
                                if (TextUtils.equals(next, "email") && !MQUtils.isEmailValid(optString)) {
                                    MQClueCardItem.this.mClueCardMessage.getAttrs().remove(next);
                                    break;
                                }
                            } else if (!MQUtils.isPhone(optString)) {
                                MQClueCardItem.this.mClueCardMessage.getAttrs().remove(next);
                                break;
                            }
                        } else if (!MQUtils.isQQ(optString)) {
                            MQClueCardItem.this.mClueCardMessage.getAttrs().remove(next);
                            break;
                        }
                    }
                    z = false;
                    if (!z) {
                        MQClueCardItem.this.mContainerLl.removeAllViews();
                        MQClueCardItem.this.fillContentLl(MQClueCardItem.this.mClueCardMessage.getContent());
                        return;
                    }
                    MQClueCardItem.this.setSendButtonEnableState(false);
                    MQManager.getInstance(MQClueCardItem.this.getContext()).replyClueCard(MQClueCardItem.this.mClueCardMessage.getAttrs(), new SuccessCallback() {
                        public void onSuccess() {
                            MQClueCardItem.this.mCallback.onClueCardMessageSendSuccess(MQClueCardItem.this.mClueCardMessage);
                        }

                        public void onFailure(int i, String str) {
                            MQClueCardItem.this.setSendButtonEnableState(MQClueCardItem.this.mClueCardMessage.isAllEnable());
                            Toast.makeText(MQClueCardItem.this.getContext(), str, 0).show();
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void setSendButtonEnableState(boolean z) {
        if (this.mSendButton != null) {
            this.mSendButton.setEnabled(z);
            this.mSendButton.setAlpha(z ? 1.0f : 0.3f);
        }
    }

    private void addInputEdit(JSONObject jSONObject, int i) {
        if (jSONObject != null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.mq_item_clue_card_input_edit, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(R.id.mq_title_tv);
            EditText editText = (EditText) inflate.findViewById(R.id.mq_input_et);
            final String optString = jSONObject.optString("name");
            textView.setText(String.format(getResources().getString(R.string.mq_item_clue_card_input), new Object[]{getName(optString)}));
            editText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Toast.makeText(MQClueCardItem.this.getContext(), "suc", 0).show();
                }
            });
            editText.setInputType(i);
            editText.setText(this.mClueCardMessage.getAttrs().optString(optString, ""));
            editText.setSelection(editText.getText().length());
            this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(this.mClueCardMessage.getAttrs().optString(optString, "")));
            setSendButtonEnableState(this.mClueCardMessage.isAllEnable());
            this.mContainerLl.addView(inflate, -2, -2);
            textView.setTextColor(getResources().getColor(this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
            editText.addTextChangedListener(new MQSimpleTextWatcher() {
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    try {
                        MQClueCardItem.this.mClueCardMessage.getAttrs().put(optString, charSequence);
                        MQClueCardItem.this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(charSequence));
                        textView.setTextColor(MQClueCardItem.this.getResources().getColor(MQClueCardItem.this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                        MQClueCardItem.this.setSendButtonEnableState(MQClueCardItem.this.mClueCardMessage.isAllEnable());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getName(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1249512767: goto L_0x0073;
                case -1147692044: goto L_0x0068;
                case -791575966: goto L_0x005d;
                case 3616: goto L_0x0053;
                case 96511: goto L_0x0049;
                case 114715: goto L_0x003f;
                case 3373707: goto L_0x0035;
                case 96619420: goto L_0x002a;
                case 113011944: goto L_0x001f;
                case 950398559: goto L_0x0014;
                case 951526432: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x007d
        L_0x0009:
            java.lang.String r0 = "contact"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 1
            goto L_0x007e
        L_0x0014:
            java.lang.String r0 = "comment"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 10
            goto L_0x007e
        L_0x001f:
            java.lang.String r0 = "weibo"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 7
            goto L_0x007e
        L_0x002a:
            java.lang.String r0 = "email"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 9
            goto L_0x007e
        L_0x0035:
            java.lang.String r0 = "name"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 0
            goto L_0x007e
        L_0x003f:
            java.lang.String r0 = "tel"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 4
            goto L_0x007e
        L_0x0049:
            java.lang.String r0 = "age"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 3
            goto L_0x007e
        L_0x0053:
            java.lang.String r0 = "qq"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 5
            goto L_0x007e
        L_0x005d:
            java.lang.String r0 = "weixin"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 6
            goto L_0x007e
        L_0x0068:
            java.lang.String r0 = "address"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 8
            goto L_0x007e
        L_0x0073:
            java.lang.String r0 = "gender"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x007d
            r0 = 2
            goto L_0x007e
        L_0x007d:
            r0 = -1
        L_0x007e:
            switch(r0) {
                case 0: goto L_0x011c;
                case 1: goto L_0x010d;
                case 2: goto L_0x00fe;
                case 3: goto L_0x00ef;
                case 4: goto L_0x00e0;
                case 5: goto L_0x00d1;
                case 6: goto L_0x00c2;
                case 7: goto L_0x00b3;
                case 8: goto L_0x00a3;
                case 9: goto L_0x0093;
                case 10: goto L_0x0083;
                default: goto L_0x0081;
            }
        L_0x0081:
            goto L_0x012a
        L_0x0083:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_comment
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x0093:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_email
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x00a3:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_address
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x00b3:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_weibo
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x00c2:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_wechat
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x00d1:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_qq
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x00e0:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_phone
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x00ef:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_age
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x00fe:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_gender
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x010d:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_contact
            java.lang.String r2 = r2.getString(r0)
            goto L_0x012a
        L_0x011c:
            android.content.Context r2 = r1.getContext()
            android.content.res.Resources r2 = r2.getResources()
            int r0 = com.meiqia.meiqiasdk.R.string.mq_name
            java.lang.String r2 = r2.getString(r0)
        L_0x012a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.chatitem.MQClueCardItem.getName(java.lang.String):java.lang.String");
    }

    private void addRadioGroup(final JSONObject jSONObject) {
        if (jSONObject != null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.mq_item_clue_card_radio, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(R.id.mq_title_tv);
            RadioGroup radioGroup = (RadioGroup) inflate.findViewById(R.id.radio_group);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(i);
                    if (radioButton != null) {
                        String str = (String) radioButton.getTag();
                        try {
                            String optString = jSONObject.optString("name");
                            MQClueCardItem.this.mClueCardMessage.getAttrs().put(optString, str);
                            MQClueCardItem.this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(MQClueCardItem.this.mClueCardMessage.getAttrs().optString(optString, "")));
                            textView.setTextColor(MQClueCardItem.this.getResources().getColor(MQClueCardItem.this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                            MQClueCardItem.this.setSendButtonEnableState(MQClueCardItem.this.mClueCardMessage.isAllEnable());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MQClueCardItem.this.mCallback.notifyDataSetChanged();
                    }
                }
            });
            try {
                String optString = jSONObject.optString("name");
                textView.setText(String.format(getResources().getString(R.string.mq_item_clue_card_select), new Object[]{getName(optString)}));
                radioGroup.clearCheck();
                JSONArray optJSONArray = jSONObject.optJSONArray("metainfo");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    RadioButton radioButton = (RadioButton) LayoutInflater.from(getContext()).inflate(R.layout.mq_item_form_radio_btn_left, (ViewGroup) null);
                    radioButton.setText(optJSONArray.optJSONObject(i).optString("name"));
                    String optString2 = optJSONArray.optJSONObject(i).optString("value");
                    if (TextUtils.equals(optString2, this.mClueCardMessage.getAttrs().optString(optString, ""))) {
                        radioButton.setChecked(true);
                    } else {
                        radioButton.setChecked(false);
                    }
                    radioButton.setTag(optString2);
                    radioButton.setId(-1);
                    MQUtils.tintCompoundButton(radioButton, R.drawable.mq_radio_btn_uncheck, R.drawable.mq_radio_btn_checked);
                    radioGroup.addView(radioButton, -1, MQUtils.dip2px(getContext(), 32.0f));
                }
                this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(this.mClueCardMessage.getAttrs().optString(optString, "")));
                textView.setTextColor(getResources().getColor(this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                setSendButtonEnableState(this.mClueCardMessage.isAllEnable());
                this.mContainerLl.addView(inflate, -2, -2);
            } catch (Exception unused) {
            }
        }
    }

    private void addCheckBox(JSONObject jSONObject) {
        if (jSONObject != null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.mq_item_clue_card_checkbox, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(R.id.mq_title_tv);
            final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.checkbox_container);
            try {
                final String optString = jSONObject.optString("name");
                textView.setText(String.format(getResources().getString(R.string.mq_item_clue_card_select), new Object[]{getName(optString)}));
                JSONArray optJSONArray = jSONObject.optJSONArray("metainfo");
                AnonymousClass5 r6 = new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        try {
                            JSONArray jSONArray = new JSONArray();
                            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                                CompoundButton compoundButton2 = (CompoundButton) linearLayout.getChildAt(i);
                                if (compoundButton2.isChecked()) {
                                    jSONArray.put(compoundButton2.getTag());
                                }
                            }
                            MQClueCardItem.this.mClueCardMessage.getAttrs().put(optString, jSONArray);
                            MQClueCardItem.this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(MQClueCardItem.this.mClueCardMessage.getAttrs().optString(optString, "")));
                            textView.setTextColor(MQClueCardItem.this.getResources().getColor(MQClueCardItem.this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                            MQClueCardItem.this.setSendButtonEnableState(MQClueCardItem.this.mClueCardMessage.isAllEnable());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                JSONArray optJSONArray2 = this.mClueCardMessage.getAttrs().optJSONArray(jSONObject.optString("name"));
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString2 = optJSONArray.optJSONObject(i).optString("value");
                    CheckBox checkBox = (CheckBox) LayoutInflater.from(getContext()).inflate(R.layout.mq_item_form_checkbox, (ViewGroup) null);
                    checkBox.setChecked(false);
                    checkBox.setText(optJSONArray.optJSONObject(i).optString("name"));
                    checkBox.setSingleLine();
                    if (optJSONArray2 != null) {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= optJSONArray2.length()) {
                                break;
                            } else if (TextUtils.equals(optJSONArray2.getString(i2), optString2)) {
                                checkBox.setChecked(true);
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                    checkBox.setOnCheckedChangeListener(r6);
                    checkBox.setTag(optString2);
                    MQUtils.tintCompoundButton(checkBox, R.drawable.mq_checkbox_uncheck, R.drawable.mq_checkbox_unchecked);
                    linearLayout.addView(checkBox, -1, MQUtils.dip2px(getContext(), 32.0f));
                }
                this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(this.mClueCardMessage.getAttrs().optString(optString, "")));
                textView.setTextColor(getResources().getColor(this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                setSendButtonEnableState(this.mClueCardMessage.isAllEnable());
                this.mContainerLl.addView(inflate, -2, -2);
            } catch (Exception unused) {
            }
        }
    }

    private void addDatePick(JSONObject jSONObject) {
        if (jSONObject != null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.mq_item_clue_card_time_picker, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(R.id.mq_title_tv);
            final TextView textView2 = (TextView) inflate.findViewById(R.id.mq_input_tv);
            final String optString = jSONObject.optString("name");
            textView.setText(String.format(getResources().getString(R.string.mq_item_clue_card_input), new Object[]{getName(optString)}));
            this.mContainerLl.addView(inflate, -2, -2);
            String optString2 = this.mClueCardMessage.getAttrs().optString(optString, "");
            if (!TextUtils.isEmpty(optString2)) {
                textView2.setText(MQTimeUtils.partLongToTime(MQTimeUtils.parseTimeToLong(optString2)));
            }
            this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(this.mClueCardMessage.getAttrs().optString(optString, "")));
            textView.setTextColor(getResources().getColor(this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
            setSendButtonEnableState(this.mClueCardMessage.isAllEnable());
            textView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Calendar instance = Calendar.getInstance();
                    new DatePickerDialog(MQClueCardItem.this.getContext(), new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker datePicker, final int i, final int i2, final int i3) {
                            Calendar instance = Calendar.getInstance();
                            instance.set(1, i);
                            instance.set(2, i2);
                            instance.set(5, i3);
                            String partLongToTime = MQTimeUtils.partLongToTime(instance.getTimeInMillis());
                            try {
                                MQClueCardItem.this.mClueCardMessage.getAttrs().put(optString, MQTimeUtils.partLongToTime(instance.getTimeInMillis()));
                                MQClueCardItem.this.mClueCardMessage.setEnable(optString, true ^ TextUtils.isEmpty(MQClueCardItem.this.mClueCardMessage.getAttrs().optString(optString, "")));
                                textView.setTextColor(MQClueCardItem.this.getResources().getColor(MQClueCardItem.this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                                MQClueCardItem.this.setSendButtonEnableState(MQClueCardItem.this.mClueCardMessage.isAllEnable());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            textView2.setText(partLongToTime);
                            new TimePickerDialog(MQClueCardItem.this.getContext(), new TimePickerDialog.OnTimeSetListener() {
                                public void onTimeSet(TimePicker timePicker, int i, int i2) {
                                    Calendar instance = Calendar.getInstance();
                                    instance.set(1, i);
                                    instance.set(2, i2);
                                    instance.set(5, i3);
                                    instance.set(11, i);
                                    instance.set(12, i2);
                                    textView2.setText(MQTimeUtils.partLongToTime(instance.getTimeInMillis()));
                                    try {
                                        MQClueCardItem.this.mClueCardMessage.getAttrs().put(optString, MQTimeUtils.partLongToTime(instance.getTimeInMillis()));
                                        MQClueCardItem.this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(MQClueCardItem.this.mClueCardMessage.getAttrs().optString(optString, "")));
                                        textView.setTextColor(MQClueCardItem.this.getResources().getColor(MQClueCardItem.this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                                        MQClueCardItem.this.setSendButtonEnableState(MQClueCardItem.this.mClueCardMessage.isAllEnable());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, instance.get(11), instance.get(12), true).show();
                        }
                    }, instance.get(1), instance.get(2), instance.get(5)).show();
                }
            });
        }
    }

    private void addNormalOrRichTextView(String str) {
        if (!TextUtils.isEmpty(str)) {
            TextView textView = new TextView(getContext());
            textView.setText(str);
            textView.setTextSize(0, (float) this.mTextSize);
            textView.setTextColor(getResources().getColor(R.color.mq_chat_left_textColor));
            textView.setPadding(this.mPadding, this.mPadding, this.mPadding, this.mPadding);
            MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_left_textColor, MQConfig.ui.leftChatTextColorResId, (ImageView) null, textView);
            this.mContainerLl.addView(textView);
        }
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mAvatarIv = (MQImageView) getViewById(R.id.iv_robot_avatar);
        this.mContainerLl = (LinearLayout) getViewById(R.id.ll_container);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        MQUtils.applyCustomUITintDrawable(this.mContainerLl, R.color.mq_chat_left_bubble_final, R.color.mq_chat_left_bubble, MQConfig.ui.leftChatBubbleColorResId);
        this.mPadding = getResources().getDimensionPixelSize(R.dimen.mq_size_level2);
        this.mTextSize = getResources().getDimensionPixelSize(R.dimen.mq_textSize_level2);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_clue_card;
    }
}
