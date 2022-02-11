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

    public MQClueCardItem(Context context, MQBaseBubbleItem.Callback callback) {
        super(context, callback);
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
                AnonymousClass5 r9 = new CompoundButton.OnCheckedChangeListener() {
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
                    checkBox.setOnCheckedChangeListener(r9);
                    checkBox.setTag(optString2);
                    MQUtils.tintCompoundButton(checkBox, R.drawable.mq_checkbox_uncheck, R.drawable.mq_checkbox_unchecked);
                    linearLayout.addView(checkBox, -1, MQUtils.dip2px(getContext(), 32.0f));
                }
                this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(this.mClueCardMessage.getAttrs().optString(optString, "")));
                textView.setTextColor(getResources().getColor(this.mClueCardMessage.isEnable(optString) ? R.color.mq_chat_event_gray : R.color.mq_error));
                setSendButtonEnableState(this.mClueCardMessage.isAllEnable());
                this.mContainerLl.addView(inflate, -2, -2);
            } catch (Exception e) {
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
                                MQClueCardItem.this.mClueCardMessage.setEnable(optString, !TextUtils.isEmpty(MQClueCardItem.this.mClueCardMessage.getAttrs().optString(optString, "")));
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
            } catch (Exception e) {
            }
        }
    }

    private void addSendButton() {
        this.mSendButton = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.mq_item_clue_card_send, (ViewGroup) null);
        setSendButtonEnableState(this.mClueCardMessage.isAllEnable());
        this.mContainerLl.addView(this.mSendButton, -2, -2);
        this.mSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z;
                if (MQClueCardItem.this.mClueCardMessage.isAllEnable()) {
                    Iterator<String> keys = MQClueCardItem.this.mClueCardMessage.getAttrs().keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        String optString = MQClueCardItem.this.mClueCardMessage.getAttrs().optString(next);
                        if (TextUtils.equals(next, "qq")) {
                            if (!MQUtils.isQQ(optString)) {
                                MQClueCardItem.this.mClueCardMessage.getAttrs().remove(next);
                            }
                        } else if (TextUtils.equals(next, "tel")) {
                            if (!MQUtils.isPhone(optString)) {
                                MQClueCardItem.this.mClueCardMessage.getAttrs().remove(next);
                            }
                        } else if (TextUtils.equals(next, "email") && !MQUtils.isEmailValid(optString)) {
                            MQClueCardItem.this.mClueCardMessage.getAttrs().remove(next);
                        }
                        z = false;
                    }
                    z = true;
                    if (!z) {
                        MQClueCardItem.this.mContainerLl.removeAllViews();
                        MQClueCardItem.this.fillContentLl(MQClueCardItem.this.mClueCardMessage.getContent());
                        return;
                    }
                    MQClueCardItem.this.setSendButtonEnableState(false);
                    MQManager.getInstance(MQClueCardItem.this.getContext()).replyClueCard(MQClueCardItem.this.mClueCardMessage.getAttrs(), new SuccessCallback() {
                        public void onFailure(int i, String str) {
                            MQClueCardItem.this.setSendButtonEnableState(MQClueCardItem.this.mClueCardMessage.isAllEnable());
                            Toast.makeText(MQClueCardItem.this.getContext(), str, 0).show();
                        }

                        public void onSuccess() {
                            MQClueCardItem.this.mCallback.onClueCardMessageSendSuccess(MQClueCardItem.this.mClueCardMessage);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    public void fillContentLl(String str) {
        char c;
        try {
            JSONArray jSONArray = new JSONArray(str);
            boolean z = false;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("type");
                switch (string.hashCode()) {
                    case -1034364087:
                        if (string.equals("number")) {
                            c = 2;
                            break;
                        }
                    case -891985903:
                        if (string.equals("string")) {
                            c = 0;
                            break;
                        }
                    case 3076014:
                        if (string.equals("date")) {
                            c = 6;
                            break;
                        }
                    case 3556653:
                        if (string.equals("text")) {
                            c = 1;
                            break;
                        }
                    case 94627080:
                        if (string.equals("check")) {
                            c = 4;
                            break;
                        }
                    case 108270587:
                        if (string.equals("radio")) {
                            c = 3;
                            break;
                        }
                    case 1536891843:
                        if (string.equals("checkbox")) {
                            c = 5;
                            break;
                        }
                    case 1793702779:
                        if (string.equals("datetime")) {
                            c = 7;
                            break;
                        }
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                    case 1:
                        addInputEdit(jSONObject, 1);
                        break;
                    case 2:
                        addInputEdit(jSONObject, 2);
                        break;
                    case 3:
                        addRadioGroup(jSONObject);
                        break;
                    case 4:
                    case 5:
                        addCheckBox(jSONObject);
                        break;
                    case 6:
                    case 7:
                        addDatePick(jSONObject);
                        break;
                    default:
                        addNormalOrRichTextView(getContext().getString(R.string.mq_unknown_msg_tip));
                        z = true;
                        break;
                }
            }
            if (!z || jSONArray.length() != 1) {
                addSendButton();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    private String getName(String str) {
        char c;
        switch (str.hashCode()) {
            case -1249512767:
                if (str.equals("gender")) {
                    c = 2;
                    break;
                }
            case -1147692044:
                if (str.equals("address")) {
                    c = 8;
                    break;
                }
            case -791575966:
                if (str.equals("weixin")) {
                    c = 6;
                    break;
                }
            case 3616:
                if (str.equals("qq")) {
                    c = 5;
                    break;
                }
            case 96511:
                if (str.equals("age")) {
                    c = 3;
                    break;
                }
            case 114715:
                if (str.equals("tel")) {
                    c = 4;
                    break;
                }
            case 3373707:
                if (str.equals("name")) {
                    c = 0;
                    break;
                }
            case 96619420:
                if (str.equals("email")) {
                    c = 9;
                    break;
                }
            case 113011944:
                if (str.equals("weibo")) {
                    c = 7;
                    break;
                }
            case 950398559:
                if (str.equals("comment")) {
                    c = 10;
                    break;
                }
            case 951526432:
                if (str.equals("contact")) {
                    c = 1;
                    break;
                }
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return getContext().getResources().getString(R.string.mq_name);
            case 1:
                return getContext().getResources().getString(R.string.mq_contact);
            case 2:
                return getContext().getResources().getString(R.string.mq_gender);
            case 3:
                return getContext().getResources().getString(R.string.mq_age);
            case 4:
                return getContext().getResources().getString(R.string.mq_phone);
            case 5:
                return getContext().getResources().getString(R.string.mq_qq);
            case 6:
                return getContext().getResources().getString(R.string.mq_wechat);
            case 7:
                return getContext().getResources().getString(R.string.mq_weibo);
            case 8:
                return getContext().getResources().getString(R.string.mq_address);
            case 9:
                return getContext().getResources().getString(R.string.mq_email);
            case 10:
                return getContext().getResources().getString(R.string.mq_comment);
            default:
                return str;
        }
    }

    /* access modifiers changed from: private */
    public void setSendButtonEnableState(boolean z) {
        if (this.mSendButton != null) {
            this.mSendButton.setEnabled(z);
            this.mSendButton.setAlpha(z ? 1.0f : 0.3f);
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_clue_card;
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
    public void setListener() {
    }

    public void setMessage(ClueCardMessage clueCardMessage, Activity activity) {
        this.mContainerLl.removeAllViews();
        this.mClueCardMessage = clueCardMessage;
        MQImage.displayImage(activity, this.mAvatarIv, clueCardMessage.getAvatar(), R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, 100, 100, (MQImageLoader.MQDisplayImageListener) null);
        fillContentLl(this.mClueCardMessage.getContent());
    }
}
