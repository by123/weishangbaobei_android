package com.meiqia.meiqiasdk.chatitem;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.activity.MQPhotoPreviewActivity;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.model.RobotMessage;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.util.RichText;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;
import com.meiqia.meiqiasdk.widget.MQImageView;
import org.json.JSONArray;
import org.json.JSONObject;

public class MQRobotItem extends MQBaseCustomCompositeView implements RichText.OnImageClickListener {
    private TextView mAlreadyFeedbackTv;
    private MQImageView mAvatarIv;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private LinearLayout mContainerLl;
    private LinearLayout mContentLl;
    private LinearLayout mEvaluateLl;
    private TextView mMenuTipTv;
    private int mPadding;
    private RobotMessage mRobotMessage;
    private TextView mRobotRichTextFl;
    private int mTextSize;
    private int mTextTipSize;
    private TextView mUsefulTv;
    private TextView mUselessTv;

    public interface Callback {
        void onClickRobotMenuItem(String str);

        void onEvaluateRobotAnswer(RobotMessage robotMessage, int i);
    }

    public MQRobotItem(Context context, Callback callback) {
        super(context);
        this.mCallback = callback;
    }

    private void addMenuItem(JSONObject jSONObject) {
        final String optString = jSONObject.optString("text");
        if (!TextUtils.isEmpty(optString)) {
            TextView textView = (TextView) View.inflate(getContext(), R.layout.mq_item_robot_menu, (ViewGroup) null);
            MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_robot_menu_item_textColor, MQConfig.ui.robotMenuItemTextColorResId, (ImageView) null, textView);
            textView.setText(optString);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MQRobotItem.this.mCallback == null) {
                        return;
                    }
                    if (optString.indexOf(".") != 1 || optString.length() <= 2) {
                        MQRobotItem.this.mCallback.onClickRobotMenuItem(optString);
                    } else {
                        MQRobotItem.this.mCallback.onClickRobotMenuItem(optString.substring(2));
                    }
                }
            });
            this.mContentLl.addView(textView);
        }
    }

    private void addMenuList(JSONArray jSONArray) {
        this.mContainerLl.setVisibility(0);
        this.mContentLl.setVisibility(0);
        this.mMenuTipTv.setVisibility(0);
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                addMenuItem(jSONArray.optJSONObject(i));
            }
        }
    }

    private void addMenuList(JSONArray jSONArray, String str) {
        if (!TextUtils.isEmpty(str)) {
            TextView textView = new TextView(getContext());
            textView.setText(str);
            textView.setTextSize(0, (float) this.mTextTipSize);
            textView.setTextColor(getResources().getColor(R.color.mq_chat_robot_menu_tip_textColor));
            textView.setPadding(this.mPadding, this.mPadding, this.mPadding, this.mPadding);
            MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_robot_menu_tip_textColor, MQConfig.ui.robotMenuTipTextColorResId, (ImageView) null, textView);
            this.mContentLl.addView(textView);
        }
        addMenuList(jSONArray);
    }

    private void addNormalTextView(String str) {
        this.mContainerLl.setVisibility(0);
        this.mContentLl.setVisibility(0);
        if (!TextUtils.isEmpty(str)) {
            TextView textView = new TextView(getContext());
            textView.setText(str);
            textView.setTextSize(0, (float) this.mTextSize);
            textView.setTextColor(getResources().getColor(R.color.mq_chat_left_textColor));
            textView.setPadding(this.mPadding, this.mPadding, this.mPadding, this.mPadding);
            MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_left_textColor, MQConfig.ui.leftChatTextColorResId, (ImageView) null, textView);
            this.mContentLl.addView(textView);
            new RichText().fromHtml(str).setOnImageClickListener(this).into(textView);
        }
    }

    private void addRichText(String str) {
        this.mContainerLl.setVisibility(0);
        this.mRobotRichTextFl.setVisibility(0);
        new RichText().fromHtml(str).setOnImageClickListener(this).into(this.mRobotRichTextFl);
    }

    private void fillContentLl() {
        try {
            if ("unknown".equals(this.mRobotMessage.getSubType())) {
                addNormalTextView(getResources().getString(R.string.mq_unknown_msg_tip));
                return;
            }
            JSONArray jSONArray = new JSONArray(this.mRobotMessage.getContentRobot());
            boolean isRelated = isRelated(jSONArray);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                String optString = optJSONObject.optString("rich_text");
                if (isRelated) {
                    if (TextUtils.equals("text", optJSONObject.optString("type"))) {
                        addNormalTextView(optJSONObject.optString(TextUtils.isEmpty(optJSONObject.optString("rich_text")) ? "text" : "rich_text"));
                    } else if (TextUtils.equals("related", optJSONObject.optString("type"))) {
                        addMenuList(optJSONObject.optJSONArray("items"), optJSONObject.optString("text_before"));
                    }
                } else if (isRichText(optJSONObject, optString)) {
                    addRichText(optString);
                } else if (TextUtils.equals("text", optJSONObject.optString("type"))) {
                    if (TextUtils.isEmpty(optString)) {
                        addNormalTextView(optJSONObject.optString("text"));
                    } else {
                        addRichText(optString);
                    }
                } else if (TextUtils.equals("menu", optJSONObject.optString("type"))) {
                    addMenuList(optJSONObject.optJSONArray("items"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleEvaluateStatus() {
        if (TextUtils.equals("evaluate", this.mRobotMessage.getSubType())) {
            this.mEvaluateLl.setVisibility(0);
            if (this.mRobotMessage.isAlreadyFeedback()) {
                this.mUselessTv.setVisibility(8);
                this.mUsefulTv.setVisibility(8);
                this.mAlreadyFeedbackTv.setVisibility(0);
                return;
            }
            this.mUselessTv.setVisibility(0);
            this.mUsefulTv.setVisibility(0);
            this.mAlreadyFeedbackTv.setVisibility(8);
        }
    }

    private boolean isRelated(JSONArray jSONArray) {
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                if (TextUtils.equals("related", jSONArray.getJSONObject(i).optString("type"))) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean isRichText(JSONObject jSONObject, String str) {
        return TextUtils.equals("text", jSONObject.optString("type")) && !TextUtils.isEmpty(str) && (TextUtils.equals("evaluate", this.mRobotMessage.getSubType()) || TextUtils.equals("menu", this.mRobotMessage.getSubType()));
    }

    private void reset() {
        this.mContentLl.removeAllViews();
        this.mContainerLl.setVisibility(8);
        this.mContentLl.setVisibility(8);
        this.mEvaluateLl.setVisibility(8);
        this.mMenuTipTv.setVisibility(8);
        this.mRobotRichTextFl.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_robot;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mAvatarIv = (MQImageView) getViewById(R.id.iv_robot_avatar);
        this.mContainerLl = (LinearLayout) getViewById(R.id.ll_robot_container);
        this.mRobotRichTextFl = (TextView) getViewById(R.id.mq_robot_rich_text_container);
        this.mContentLl = (LinearLayout) getViewById(R.id.ll_robot_content);
        this.mEvaluateLl = (LinearLayout) getViewById(R.id.ll_robot_evaluate);
        this.mUsefulTv = (TextView) getViewById(R.id.tv_robot_useful);
        this.mUselessTv = (TextView) getViewById(R.id.tv_robot_useless);
        this.mMenuTipTv = (TextView) getViewById(R.id.tv_robot_menu_tip);
        this.mAlreadyFeedbackTv = (TextView) getViewById(R.id.tv_robot_already_feedback);
    }

    public void onClick(View view) {
        if (this.mCallback == null) {
            return;
        }
        if (view.getId() == R.id.tv_robot_useful) {
            this.mCallback.onEvaluateRobotAnswer(this.mRobotMessage, 1);
        } else if (view.getId() == R.id.tv_robot_useless) {
            this.mCallback.onEvaluateRobotAnswer(this.mRobotMessage, 0);
        }
    }

    public void onImageClicked(String str) {
        getContext().startActivity(MQPhotoPreviewActivity.newIntent(getContext(), MQUtils.getImageDir(getContext()), str));
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        MQUtils.applyCustomUITintDrawable(this.mContainerLl, R.color.mq_chat_left_bubble_final, R.color.mq_chat_left_bubble, MQConfig.ui.leftChatBubbleColorResId);
        MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_robot_menu_tip_textColor, MQConfig.ui.robotMenuTipTextColorResId, (ImageView) null, this.mMenuTipTv);
        MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_robot_evaluate_textColor, MQConfig.ui.robotEvaluateTextColorResId, (ImageView) null, this.mUsefulTv, this.mUselessTv);
        this.mPadding = getResources().getDimensionPixelSize(R.dimen.mq_size_level2);
        this.mTextSize = getResources().getDimensionPixelSize(R.dimen.mq_textSize_level2);
        this.mTextTipSize = getResources().getDimensionPixelSize(R.dimen.mq_textSize_level1);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        this.mUsefulTv.setOnClickListener(this);
        this.mUselessTv.setOnClickListener(this);
    }

    public void setMessage(RobotMessage robotMessage, Activity activity) {
        reset();
        this.mRobotMessage = robotMessage;
        MQImage.displayImage(activity, this.mAvatarIv, this.mRobotMessage.getAvatar(), R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, 100, 100, (MQImageLoader.MQDisplayImageListener) null);
        handleEvaluateStatus();
        fillContentLl();
    }
}
