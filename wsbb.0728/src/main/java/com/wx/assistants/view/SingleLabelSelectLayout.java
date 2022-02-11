package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.activity.ObtainSingleTagActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.WxSingleTagEvent;
import com.wx.assistants.utils.LogUtils;
import java.util.LinkedHashSet;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SingleLabelSelectLayout extends LinearLayout implements View.OnClickListener {
    private TextView bigText;
    private OnSelectSingleLabelListener listener;
    private Context mContext;
    private LabelBean selectLabelBean;
    private LinearLayout selectSingleLabel;
    private String smallStr = "";
    private TextView smallText;

    public interface OnSelectSingleLabelListener {
        void selectLabel(LabelBean labelBean);

        void selectedPeopleList(LinkedHashSet<String> linkedHashSet);

        void selectedPeopleString(String str);
    }

    public SingleLabelSelectLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public SingleLabelSelectLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public SingleLabelSelectLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427776, this, true);
        EventBus.getDefault().register(this);
    }

    public void onClick(View view) {
        if (view.getId() == 2131297317) {
            Intent intent = new Intent(this.mContext, ObtainSingleTagActivity.class);
            intent.putExtra("selects", this.selectLabelBean);
            MyApplication.instance.getBaseActivity().startActivity(intent);
        }
    }

    @Subscribe
    public void onEventMainThread(WxSingleTagEvent wxSingleTagEvent) {
        LabelBean selectedLabelBean = wxSingleTagEvent.getSelectedLabelBean();
        LinkedHashSet<String> selectedPeopleList = wxSingleTagEvent.getSelectedPeopleList();
        String selectedPeoples = wxSingleTagEvent.getSelectedPeoples();
        LogUtils.log("WS_BABY_BABY-0");
        if (selectedLabelBean != null) {
            LogUtils.log("WS_BABY_BABY-1");
            this.selectLabelBean = selectedLabelBean;
            this.smallText.setText(this.selectLabelBean.getLabelName());
            if (this.listener != null) {
                LogUtils.log("WS_BABY_BABY-2");
                this.listener.selectLabel(selectedLabelBean);
                this.listener.selectedPeopleString(selectedPeoples);
                this.listener.selectedPeopleList(selectedPeopleList);
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
            this.listener.selectedPeopleList((LinkedHashSet<String>) null);
            this.listener.selectedPeopleString((String) null);
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
