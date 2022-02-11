package com.meiqia.meiqiasdk.chatitem;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.chatitem.MQBaseBubbleItem;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.model.TextMessage;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQUtils;

public class MQClientItem extends MQBaseBubbleItem {
    private ImageView sendState;
    private ProgressBar sendingProgressBar;
    private TextView sensitiveWordTipTv;

    private class FailedMessageOnClickListener implements View.OnClickListener {
        private BaseMessage mFailedMessage;

        public FailedMessageOnClickListener(BaseMessage baseMessage) {
            this.mFailedMessage = baseMessage;
        }

        public void onClick(View view) {
            if (!MQUtils.isFastClick()) {
                MQClientItem.this.mCallback.resendFailedMessage(this.mFailedMessage);
            }
        }
    }

    public MQClientItem(Context context, MQBaseBubbleItem.Callback callback) {
        super(context, callback);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_chat_right;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        super.initView();
        this.sendingProgressBar = (ProgressBar) getViewById(R.id.progress_bar);
        this.sendState = (ImageView) getViewById(R.id.send_state);
        this.sensitiveWordTipTv = (TextView) getViewById(R.id.sensitive_words_tip_tv);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        super.processLogic();
        applyConfig(false);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
    }

    public void setMessage(BaseMessage baseMessage, int i, Activity activity) {
        super.setMessage(baseMessage, i, activity);
        if (!MQConfig.isShowClientAvatar) {
            this.usAvatar.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.chatBox.getLayoutParams();
            layoutParams.addRule(11);
            this.chatBox.setLayoutParams(layoutParams);
        }
        this.sensitiveWordTipTv.setVisibility(8);
        if (this.sendingProgressBar != null) {
            String status = baseMessage.getStatus();
            char c = 65535;
            int hashCode = status.hashCode();
            if (hashCode != -1281977283) {
                if (hashCode != -734206867) {
                    if (hashCode == 1979923290 && status.equals("sending")) {
                        c = 0;
                    }
                } else if (status.equals("arrived")) {
                    c = 1;
                }
            } else if (status.equals("failed")) {
                c = 2;
            }
            switch (c) {
                case 0:
                    this.sendingProgressBar.setVisibility(0);
                    this.sendState.setVisibility(8);
                    return;
                case 1:
                    this.sendingProgressBar.setVisibility(8);
                    this.sendState.setVisibility(8);
                    if (baseMessage instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) baseMessage;
                        if (textMessage.isContainsSensitiveWords()) {
                            this.sendingProgressBar.setVisibility(8);
                            this.sendState.setVisibility(8);
                            this.sensitiveWordTipTv.setVisibility(0);
                            if (!TextUtils.isEmpty(textMessage.getReplaceContent())) {
                                this.contentText.setText(textMessage.getReplaceContent());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 2:
                    this.sendingProgressBar.setVisibility(8);
                    this.sendState.setVisibility(0);
                    this.sendState.setBackgroundResource(R.drawable.mq_ic_msg_failed);
                    this.sendState.setOnClickListener(new FailedMessageOnClickListener(baseMessage));
                    this.sendState.setTag(Long.valueOf(baseMessage.getId()));
                    return;
                default:
                    return;
            }
        }
    }
}
