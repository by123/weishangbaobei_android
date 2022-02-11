package com.wx.assistants.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.Enity.RecordBean;
import com.wx.assistants.adapter.VoiceWxRecordAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.VoiceEvent;
import com.wx.assistants.service.WxLocVoiceService;
import com.wx.assistants.utils.LogUtils;
import com.wx.assistants.utils.SQLiteUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class WxVoiceFragment extends Fragment {
    /* access modifiers changed from: private */
    public VoiceWxRecordAdapter adapter;
    /* access modifiers changed from: private */
    public boolean isCanRefreshData = true;
    @BindView(2131296952)
    ListView listView;
    private List<RecordBean> mRecords = new ArrayList();
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    @BindView(2131297375)
    TextView showTith;
    public Unbinder unbinder;

    public static WxVoiceFragment newInstance() {
        Bundle bundle = new Bundle();
        WxVoiceFragment wxVoiceFragment = new WxVoiceFragment();
        wxVoiceFragment.setArguments(bundle);
        return wxVoiceFragment;
    }

    public void initData() {
        this.mRecords = new ArrayList();
        this.adapter = new VoiceWxRecordAdapter(getContext(), this.mRecords);
        this.listView.setAdapter(this.adapter);
        List allWXRecord = SQLiteUtils.getInstance().getAllWXRecord();
        if (allWXRecord == null || allWXRecord.isEmpty()) {
            if (this.showTith != null) {
                this.showTith.setVisibility(0);
            }
            if (this.refreshLayout != null) {
                this.refreshLayout.finishRefresh();
                return;
            }
            return;
        }
        if (this.showTith != null && this.showTith.getVisibility() == 0) {
            this.showTith.setVisibility(8);
        }
        this.mRecords.addAll(allWXRecord);
        this.adapter.notifyDataSetChanged();
        this.listView.setSelection(0);
        if (this.refreshLayout != null) {
            this.refreshLayout.finishRefresh();
        }
    }

    public void initView() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                if (refreshLayout != null) {
                    refreshLayout.finishRefresh();
                }
                WxVoiceFragment.this.refreshData();
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427543, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        EventBus.getDefault().register(this);
        this.isCanRefreshData = true;
        initView();
        initData();
        refreshData();
        return inflate;
    }

    public void onDestroyView() {
        WxVoiceFragment.super.onDestroyView();
        EventBus.getDefault().unregister(this);
        this.unbinder.unbind();
        if (this.adapter != null) {
            this.adapter.onDestroy();
        }
    }

    @Subscribe
    public void onEventMainThread(VoiceEvent voiceEvent) {
        if (voiceEvent.getVoiceFragmentTag() == 1) {
            initData();
        }
    }

    public void onResume() {
        WxVoiceFragment.super.onResume();
        this.isCanRefreshData = true;
        LogUtils.log("WS_BABY_onResume");
    }

    public void refreshData() {
        if (this.isCanRefreshData) {
            WxLocVoiceService.startWith(MyApplication.getConText(), new WxLocVoiceService.OnProgressResultListener() {
                public void isCanRefreshData(boolean z) {
                    boolean unused = WxVoiceFragment.this.isCanRefreshData = z;
                    LogUtils.log("WS_BABY_isCanRefreshData" + WxVoiceFragment.this.isCanRefreshData);
                }

                public void showPageEnd() {
                    if (WxVoiceFragment.this.showTith != null && WxVoiceFragment.this.showTith.getVisibility() == 0) {
                        WxVoiceFragment.this.showTith.setVisibility(8);
                    }
                }

                public void updateAdapterData(List<RecordBean> list) {
                    if (WxVoiceFragment.this.adapter != null && WxVoiceFragment.this.listView != null) {
                        WxVoiceFragment.this.adapter.setData(list);
                        WxVoiceFragment.this.adapter.notifyDataSetChanged();
                        WxVoiceFragment.this.listView.setSelection(0);
                        if (WxVoiceFragment.this.refreshLayout != null) {
                            WxVoiceFragment.this.refreshLayout.finishRefresh();
                        }
                    }
                }
            });
        }
    }
}
