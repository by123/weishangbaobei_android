package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.activity.ObtainSingleTagCompanyActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.WxSingleTagCompanyEvent;
import com.wx.assistants.utils.LogUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SingleLabelSelectLayoutCompany extends LinearLayout implements View.OnClickListener {
    private TextView bigText;
    private OnSelectSingleLabelListener listener;
    private Context mContext;
    private LabelBean selectLabelBean;
    private LinearLayout selectSingleLabel;
    private String smallStr = "";
    private TextView smallText;

    public interface OnSelectSingleLabelListener {
        void selectLabel(LabelBean labelBean);
    }

    public SingleLabelSelectLayoutCompany(Context context) {
        super(context);
        this.mContext = context;
    }

    public SingleLabelSelectLayoutCompany(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public SingleLabelSelectLayoutCompany(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427776, this, true);
        EventBus.getDefault().register(this);
    }

    public void onClick(View view) {
        if (view.getId() == 2131297317) {
            Intent intent = new Intent(this.mContext, ObtainSingleTagCompanyActivity.class);
            intent.putExtra("selects", this.selectLabelBean);
            MyApplication.instance.getBaseActivity().startActivity(intent);
        }
    }

    @Subscribe
    public void onEventMainThread(WxSingleTagCompanyEvent wxSingleTagCompanyEvent) {
        LabelBean selectedLabelBean = wxSingleTagCompanyEvent.getSelectedLabelBean();
        LogUtils.log("WS_BABY_BABY-0");
        if (selectedLabelBean != null) {
            LogUtils.log("WS_BABY_BABY-1");
            this.selectLabelBean = selectedLabelBean;
            this.smallText.setText(this.selectLabelBean.getLabelName());
            if (this.listener != null) {
                LogUtils.log("WS_BABY_BABY-2");
                this.listener.selectLabel(selectedLabelBean);
                return;
            }
            return;
        }
        LogUtils.log("WS_BABY_BABY-3");
        this.selectLabelBean = null;
        this.smallText.setText(this.smallStr);
        if (this.listener != null) {
            LogUtils.log("WS_BABY_BABY-4");
            this.listener.selectLabel((LabelBean) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.selectSingleLabel = (LinearLayout) findViewById(2131297317);
        this.bigText = (TextView) findViewById(2131296395);
        this.smallText = (TextView) findViewById(2131297391);
        this.selectSingleLabel.setOnClickListener(this);
    }

    public void setBigText(String str) {
        if (this.bigText != null && str != null) {
            this.bigText.setText(str);
        }
    }

    public void setOnSelectSingleLabelListener(OnSelectSingleLabelListener onSelectSingleLabelListener) {
        if (onSelectSingleLabelListener != null) {
            this.listener = onSelectSingleLabelListener;
        }
    }

    public void setSmallText(String str) {
        if (this.smallText != null && str != null) {
            this.smallStr = str;
            this.smallText.setText(str);
        }
    }
}
