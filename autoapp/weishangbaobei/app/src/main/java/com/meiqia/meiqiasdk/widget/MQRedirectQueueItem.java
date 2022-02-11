package com.meiqia.meiqiasdk.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.callback.LeaveMessageCallback;
import com.meiqia.meiqiasdk.model.RedirectQueueMessage;
import com.meiqia.meiqiasdk.util.MQConfig;

public class MQRedirectQueueItem extends MQBaseCustomCompositeView {
    private LeaveMessageCallback mCallback;
    private TextView mInfoTv;
    private ImageView mQueueAnimIv;
    private TextView mTipTv;

    public MQRedirectQueueItem(Context context, LeaveMessageCallback leaveMessageCallback) {
        super(context);
        this.mCallback = leaveMessageCallback;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_redirect_queue;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mQueueAnimIv = (ImageView) getViewById(R.id.iv_redirect_queue_anim);
        this.mTipTv = (TextView) getViewById(R.id.tv_redirect_queue_tip);
        this.mInfoTv = (TextView) getViewById(R.id.tv_queue_info_tv);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        getViewById(R.id.tv_redirect_queue_leave_msg).setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
        this.mInfoTv.setText(MQConfig.getController(getContext()).getEnterpriseConfig().queueingSetting.getIntro());
    }

    public void onClick(View view) {
        if (this.mCallback != null) {
            this.mCallback.onClickLeaveMessage();
        }
    }

    public void setMessage(RedirectQueueMessage redirectQueueMessage) {
        this.mTipTv.setText(getResources().getString(R.string.mq_queue_leave_msg, new Object[]{Integer.valueOf(redirectQueueMessage.getQueueSize())}));
        ((AnimationDrawable) this.mQueueAnimIv.getDrawable()).start();
    }
}
