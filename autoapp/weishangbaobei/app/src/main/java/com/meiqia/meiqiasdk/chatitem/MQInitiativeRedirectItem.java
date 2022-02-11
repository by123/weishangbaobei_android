package com.meiqia.meiqiasdk.chatitem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.InitiativeRedirectMessage;
import com.meiqia.meiqiasdk.widget.MQBaseCustomCompositeView;

public class MQInitiativeRedirectItem extends MQBaseCustomCompositeView {
    private Callback mCallback;
    private TextView mTipTv;

    public interface Callback {
        void onClickForceRedirectHuman();
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    public MQInitiativeRedirectItem(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_item_useless_redirect;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mTipTv = (TextView) getViewById(R.id.tv_item_redirect_tip);
    }

    /* access modifiers changed from: protected */
    public void setListener() {
        getViewById(R.id.tv_useless_redirect_redirect_human).setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.mCallback != null) {
            this.mCallback.onClickForceRedirectHuman();
        }
    }

    public void setMessage(InitiativeRedirectMessage initiativeRedirectMessage, Callback callback) {
        this.mCallback = callback;
        this.mTipTv.setText(initiativeRedirectMessage.getTipResId());
    }
}
