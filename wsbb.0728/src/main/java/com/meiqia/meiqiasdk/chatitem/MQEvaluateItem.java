package com.meiqia.meiqiasdk.chatitem;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.EvaluateMessage;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;

public class MQEvaluateItem extends MQBaseCustomCompositeView {
    private TextView mContentTv;
    private View mLevelBg;
    private ImageView mLevelImg;
    private TextView mLevelTv;

    public MQEvaluateItem(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_msg_evaluate;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mLevelTv = (TextView) getViewById(R.id.tv_msg_evaluate_level);
        this.mLevelBg = getViewById(R.id.view_msg_evaluate_level);
        this.mLevelImg = (ImageView) getViewById(R.id.ic_msg_evaluate_level);
        this.mContentTv = (TextView) getViewById(R.id.tv_msg_evaluate_content);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    /* access modifiers changed from: protected */
    public void setListener() {
    }

    public void setMessage(EvaluateMessage evaluateMessage) {
        switch (evaluateMessage.getLevel()) {
            case 0:
                this.mLevelImg.setImageResource(R.drawable.mq_ic_angry_face);
                this.mLevelTv.setText(R.string.mq_evaluate_bad);
                this.mLevelBg.setBackgroundResource(R.drawable.mq_shape_evaluate_angry);
                break;
            case 1:
                this.mLevelImg.setImageResource(R.drawable.mq_ic_neutral_face);
                this.mLevelTv.setText(R.string.mq_evaluate_medium);
                this.mLevelBg.setBackgroundResource(R.drawable.mq_shape_evaluate_neutral);
                break;
            case 2:
                this.mLevelImg.setImageResource(R.drawable.mq_ic_smiling_face);
                this.mLevelTv.setText(R.string.mq_evaluate_good);
                this.mLevelBg.setBackgroundResource(R.drawable.mq_shape_evaluate_smiling);
                break;
        }
        String content = evaluateMessage.getContent();
        if (!TextUtils.isEmpty(content)) {
            this.mContentTv.setVisibility(0);
            this.mContentTv.setText(content);
            return;
        }
        this.mContentTv.setVisibility(8);
    }
}
