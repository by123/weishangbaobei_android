package com.meiqia.meiqiasdk.chatitem;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.AgentChangeMessage;
import com.meiqia.meiqiasdk.model.BaseMessage;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;

public class MQTipItem extends MQBaseCustomCompositeView {
    private TextView mContentTv;

    public MQTipItem(Context context) {
        super(context);
    }

    private void setDirectionMessageContent(String str) {
        if (str != null) {
            String format = String.format(getResources().getString(R.string.mq_direct_content), new Object[]{str});
            int indexOf = format.indexOf(str);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.mq_chat_direct_agent_nickname_textColor)), indexOf, str.length() + indexOf, 34);
            this.mContentTv.setText(spannableStringBuilder);
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_msg_tip;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mContentTv = (TextView) getViewById(R.id.content_tv);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    /* access modifiers changed from: protected */
    public void setListener() {
    }

    public void setMessage(BaseMessage baseMessage) {
        if (baseMessage instanceof AgentChangeMessage) {
            setDirectionMessageContent(baseMessage.getAgentNickname());
        } else {
            this.mContentTv.setText(baseMessage.getContent());
        }
    }
}
