package com.meiqia.meiqiasdk.chatitem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meiqia.core.bean.MQInquireForm;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.activity.MQPhotoPreviewActivity;
import com.meiqia.meiqiasdk.chatitem.MQRobotItem;
import com.meiqia.meiqiasdk.imageloader.MQImage;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.meiqia.meiqiasdk.model.HybridMessage;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQUtils;
import com.meiqia.meiqiasdk.util.RichText;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;
import com.meiqia.meiqiasdk.widget.MQImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MQHybridItem extends MQBaseCustomCompositeView implements RichText.OnImageClickListener {
    private MQImageView mAvatarIv;
    /* access modifiers changed from: private */
    public MQRobotItem.Callback mCallback;
    private LinearLayout mContainerLl;
    private HybridMessage mHybridMessage;
    private int mPadding;
    private int mTextSize;

    public MQHybridItem(Context context, MQRobotItem.Callback callback) {
        super(context);
        this.mCallback = callback;
    }

    private void addChoices(String str) throws JSONException {
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            final String optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                TextView textView = (TextView) View.inflate(getContext(), R.layout.mq_item_robot_menu, (ViewGroup) null);
                MQUtils.applyCustomUITextAndImageColor(R.color.mq_chat_robot_menu_item_textColor, MQConfig.ui.robotMenuItemTextColorResId, (ImageView) null, textView);
                textView.setText(optString);
                textView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MQHybridItem.this.mCallback == null) {
                            return;
                        }
                        if (optString.indexOf(".") != 1 || optString.length() <= 2) {
                            MQHybridItem.this.mCallback.onClickRobotMenuItem(optString);
                        } else {
                            MQHybridItem.this.mCallback.onClickRobotMenuItem(optString.substring(2));
                        }
                    }
                });
                this.mContainerLl.addView(textView);
            }
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
            new RichText().fromHtml(str).setOnImageClickListener(this).into(textView);
        }
    }

    private void addPhotoCardView(JSONObject jSONObject) {
        int screenWidth = ((MQUtils.getScreenWidth(getContext()) / 3) * 2) - MQUtils.dip2px(getContext(), 16.0f);
        int dip2px = MQUtils.dip2px(getContext(), 12.0f);
        ViewGroup.LayoutParams layoutParams = this.mContainerLl.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = screenWidth;
        this.mContainerLl.setLayoutParams(layoutParams);
        this.mContainerLl.setBackgroundResource(R.drawable.mq_bg_card);
        String optString = jSONObject.optString("title");
        String optString2 = jSONObject.optString(MQInquireForm.KEY_MENUS_ASSIGNMENTS_DESCRIPTION);
        final String optString3 = jSONObject.optString("target_url");
        String optString4 = jSONObject.optString("pic_url");
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setAdjustViewBounds(true);
        MQImage.displayImage((Activity) getContext(), imageView, optString4, R.drawable.mq_ic_holder_light, R.drawable.mq_ic_holder_light, screenWidth, screenWidth, new MQImageLoader.MQDisplayImageListener() {
            public void onSuccess(View view, String str) {
            }
        });
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.leftMargin = dip2px;
        layoutParams2.rightMargin = dip2px;
        this.mContainerLl.addView(imageView, layoutParams2);
        this.mContainerLl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    MQHybridItem.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(optString3)));
                } catch (Exception e) {
                    MQUtils.show(MQHybridItem.this.getContext(), R.string.mq_title_unknown_error);
                }
            }
        });
        if (!TextUtils.isEmpty(optString)) {
            TextView textView = new TextView(getContext());
            textView.setText(optString);
            textView.setMaxLines(1);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextSize(0, (float) getResources().getDimensionPixelSize(R.dimen.mq_textSize_level3));
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams3.topMargin = MQUtils.dip2px(getContext(), 2.0f);
            layoutParams3.bottomMargin = MQUtils.dip2px(getContext(), 2.0f);
            layoutParams3.leftMargin = dip2px;
            layoutParams3.rightMargin = dip2px;
            this.mContainerLl.addView(textView, layoutParams3);
        }
        if (!TextUtils.isEmpty(optString2)) {
            TextView textView2 = new TextView(getContext());
            textView2.setText(optString2);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams4.topMargin = MQUtils.dip2px(getContext(), 2.0f);
            layoutParams4.bottomMargin = MQUtils.dip2px(getContext(), 2.0f);
            layoutParams4.leftMargin = dip2px;
            layoutParams4.rightMargin = dip2px;
            this.mContainerLl.addView(textView2, layoutParams4);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    private void fillContentLl(String str) {
        char c;
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("type");
                switch (string.hashCode()) {
                    case -842613072:
                        if (string.equals("rich_text")) {
                            c = 0;
                            break;
                        }
                    case -508099331:
                        if (string.equals("photo_card")) {
                            c = 5;
                            break;
                        }
                    case 3322014:
                        if (string.equals("list")) {
                            c = 3;
                            break;
                        }
                    case 3556653:
                        if (string.equals("text")) {
                            c = 1;
                            break;
                        }
                    case 3641717:
                        if (string.equals("wait")) {
                            c = 4;
                            break;
                        }
                    case 751720178:
                        if (string.equals(MQInquireForm.KEY_INPUTS_FIELDS_CHOICES)) {
                            c = 2;
                            break;
                        }
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                    case 1:
                        addNormalOrRichTextView(jSONObject.getString("body"));
                        break;
                    case 2:
                        addChoices(jSONObject.optJSONObject("body").optString(MQInquireForm.KEY_INPUTS_FIELDS_CHOICES));
                        break;
                    case 3:
                        fillContentLl(jSONObject.getString("body"));
                        break;
                    case 4:
                        break;
                    case 5:
                        addPhotoCardView(jSONObject.optJSONObject("body"));
                        break;
                    default:
                        addNormalOrRichTextView(getContext().getString(R.string.mq_unknown_msg_tip));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_hybrid;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mAvatarIv = (MQImageView) getViewById(R.id.iv_robot_avatar);
        this.mContainerLl = (LinearLayout) getViewById(R.id.ll_robot_container);
    }

    public void onImageClicked(String str) {
        getContext().startActivity(MQPhotoPreviewActivity.newIntent(getContext(), MQUtils.getImageDir(getContext()), str));
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

    public void setMessage(HybridMessage hybridMessage, Activity activity) {
        this.mContainerLl.removeAllViews();
        this.mHybridMessage = hybridMessage;
        MQImage.displayImage(activity, this.mAvatarIv, this.mHybridMessage.getAvatar(), R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, 100, 100, (MQImageLoader.MQDisplayImageListener) null);
        fillContentLl(this.mHybridMessage.getContent());
    }
}
