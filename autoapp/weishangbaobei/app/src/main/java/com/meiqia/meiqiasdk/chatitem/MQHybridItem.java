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

    /* access modifiers changed from: protected */
    public void setListener() {
    }

    public MQHybridItem(Context context, MQRobotItem.Callback callback) {
        super(context);
        this.mCallback = callback;
    }

    public void onImageClicked(String str) {
        getContext().startActivity(MQPhotoPreviewActivity.newIntent(getContext(), MQUtils.getImageDir(getContext()), str));
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

    /* access modifiers changed from: protected */
    public void processLogic() {
        MQUtils.applyCustomUITintDrawable(this.mContainerLl, R.color.mq_chat_left_bubble_final, R.color.mq_chat_left_bubble, MQConfig.ui.leftChatBubbleColorResId);
        this.mPadding = getResources().getDimensionPixelSize(R.dimen.mq_size_level2);
        this.mTextSize = getResources().getDimensionPixelSize(R.dimen.mq_textSize_level2);
    }

    public void setMessage(HybridMessage hybridMessage, Activity activity) {
        this.mContainerLl.removeAllViews();
        this.mHybridMessage = hybridMessage;
        MQImage.displayImage(activity, this.mAvatarIv, this.mHybridMessage.getAvatar(), R.drawable.mq_ic_holder_avatar, R.drawable.mq_ic_holder_avatar, 100, 100, (MQImageLoader.MQDisplayImageListener) null);
        fillContentLl(this.mHybridMessage.getContent());
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fillContentLl(java.lang.String r7) {
        /*
            r6 = this;
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ Exception -> 0x00a1 }
            r0.<init>(r7)     // Catch:{ Exception -> 0x00a1 }
            r7 = 0
            r1 = 0
        L_0x0007:
            int r2 = r0.length()     // Catch:{ Exception -> 0x00a1 }
            if (r1 >= r2) goto L_0x00a5
            org.json.JSONObject r2 = r0.getJSONObject(r1)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = "type"
            java.lang.String r3 = r2.getString(r3)     // Catch:{ Exception -> 0x00a1 }
            r4 = -1
            int r5 = r3.hashCode()     // Catch:{ Exception -> 0x00a1 }
            switch(r5) {
                case -842613072: goto L_0x0053;
                case -508099331: goto L_0x0049;
                case 3322014: goto L_0x003f;
                case 3556653: goto L_0x0035;
                case 3641717: goto L_0x002a;
                case 751720178: goto L_0x0020;
                default: goto L_0x001f;
            }     // Catch:{ Exception -> 0x00a1 }
        L_0x001f:
            goto L_0x005d
        L_0x0020:
            java.lang.String r5 = "choices"
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x005d
            r3 = 2
            goto L_0x005e
        L_0x002a:
            java.lang.String r5 = "wait"
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x005d
            r3 = 4
            goto L_0x005e
        L_0x0035:
            java.lang.String r5 = "text"
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x005d
            r3 = 1
            goto L_0x005e
        L_0x003f:
            java.lang.String r5 = "list"
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x005d
            r3 = 3
            goto L_0x005e
        L_0x0049:
            java.lang.String r5 = "photo_card"
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x005d
            r3 = 5
            goto L_0x005e
        L_0x0053:
            java.lang.String r5 = "rich_text"
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x00a1 }
            if (r3 == 0) goto L_0x005d
            r3 = 0
            goto L_0x005e
        L_0x005d:
            r3 = -1
        L_0x005e:
            switch(r3) {
                case 0: goto L_0x008a;
                case 1: goto L_0x008a;
                case 2: goto L_0x007a;
                case 3: goto L_0x0070;
                case 4: goto L_0x009d;
                case 5: goto L_0x0066;
                default: goto L_0x0061;
            }     // Catch:{ Exception -> 0x00a1 }
        L_0x0061:
            android.content.Context r2 = r6.getContext()     // Catch:{ Exception -> 0x00a1 }
            goto L_0x0094
        L_0x0066:
            java.lang.String r3 = "body"
            org.json.JSONObject r2 = r2.optJSONObject(r3)     // Catch:{ Exception -> 0x00a1 }
            r6.addPhotoCardView(r2)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x009d
        L_0x0070:
            java.lang.String r3 = "body"
            java.lang.String r2 = r2.getString(r3)     // Catch:{ Exception -> 0x00a1 }
            r6.fillContentLl(r2)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x009d
        L_0x007a:
            java.lang.String r3 = "body"
            org.json.JSONObject r2 = r2.optJSONObject(r3)     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r3 = "choices"
            java.lang.String r2 = r2.optString(r3)     // Catch:{ Exception -> 0x00a1 }
            r6.addChoices(r2)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x009d
        L_0x008a:
            java.lang.String r3 = "body"
            java.lang.String r2 = r2.getString(r3)     // Catch:{ Exception -> 0x00a1 }
            r6.addNormalOrRichTextView(r2)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x009d
        L_0x0094:
            int r3 = com.meiqia.meiqiasdk.R.string.mq_unknown_msg_tip     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r2 = r2.getString(r3)     // Catch:{ Exception -> 0x00a1 }
            r6.addNormalOrRichTextView(r2)     // Catch:{ Exception -> 0x00a1 }
        L_0x009d:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x00a1:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00a5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.chatitem.MQHybridItem.fillContentLl(java.lang.String):void");
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
                } catch (Exception unused) {
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
}
