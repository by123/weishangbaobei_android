package com.wx.assistants.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.Enity.RecordBean;
import com.wx.assistants.adapter.SelectVoiceCollectionRecordAdapter;
import com.wx.assistants.bean.VoiceEvent;
import com.wx.assistants.utils.SQLiteUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SelectMyVoiceCollectionFragment extends Fragment {
    private SelectVoiceCollectionRecordAdapter adapter;
    @BindView(2131296952)
    ListView listView;
    private List<RecordBean> mRecords = new ArrayList();
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    public Unbinder unbinder;

    public static SelectMyVoiceCollectionFragment newInstance() {
        Bundle bundle = new Bundle();
        SelectMyVoiceCollectionFragment selectMyVoiceCollectionFragment = new SelectMyVoiceCollectionFragment();
        selectMyVoiceCollectionFragment.setArguments(bundle);
        return selectMyVoiceCollectionFragment;
    }

    public void initData() {
        this.mRecords = new ArrayList();
        this.adapter = new SelectVoiceCollectionRecordAdapter(getContext(), this.mRecords);
        this.listView.setAdapter(this.adapter);
        List allCollectionRecord = SQLiteUtils.getInstance().getAllCollectionRecord();
        if (allCollectionRecord != null && !allCollectionRecord.isEmpty()) {
            this.mRecords.addAll(allCollectionRecord);
            this.adapter.notifyDataSetChanged();
            this.listView.setSelection(0);
            if (this.refreshLayout != null) {
                this.refreshLayout.finishRefresh();
            }
        }
    }

    public void initView() {
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                SelectMyVoiceCollectionFragment.this.initData();
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427535, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        EventBus.getDefault().register(this);
        initView();
        initData();
        return inflate;
    }

    public void onDestroy() {
        SelectMyVoiceCollectionFragment.super.onDestroy();
        if (this.adapter != null) {
            this.adapter.onDestroy();
        }
    }

    public void onDestroyView() {
        SelectMyVoiceCollectionFragment.super.onDestroyView();
        EventBus.getDefault().unregister(this);
        this.unbinder.unbind();
    }

    @Subscribe
    public void onEventMainThread(VoiceEvent voiceEvent) {
        if (voiceEvent.getVoiceFragmentTag() == 2) {
            initData();
        }
    }
}
