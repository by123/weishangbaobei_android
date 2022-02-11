package com.wx.assistants.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.ilike.voicerecorder.utils.PathUtil;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.Enity.RecordBean;
import com.wx.assistants.adapter.SelectVoiceRecordAdapter;
import com.wx.assistants.bean.VoiceEvent;
import com.wx.assistants.globle.LogUtils;
import com.wx.assistants.utils.FileUtil;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SQLiteUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import permission.PermissionListener;

public class SelectMyVoiceFragment extends Fragment {
    /* access modifiers changed from: private */
    public SelectVoiceRecordAdapter adapter;
    @BindView(2131296952)
    ListView listView;
    /* access modifiers changed from: private */
    public List<RecordBean> mRecords = new ArrayList();
    @BindView(2131297156)
    TextView pressLayout;
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    private Unbinder unbinder;
    @BindView(2131297661)
    VoiceRecorderView voiceRecorder;

    public static SelectMyVoiceFragment newInstance() {
        Bundle bundle = new Bundle();
        SelectMyVoiceFragment selectMyVoiceFragment = new SelectMyVoiceFragment();
        selectMyVoiceFragment.setArguments(bundle);
        return selectMyVoiceFragment;
    }

    public void initData() {
        this.mRecords = new ArrayList();
        this.adapter = new SelectVoiceRecordAdapter(getContext(), this.mRecords);
        this.listView.setAdapter(this.adapter);
        List allMyVoiceRecord = SQLiteUtils.getInstance().getAllMyVoiceRecord();
        if (allMyVoiceRecord != null && !allMyVoiceRecord.isEmpty()) {
            this.mRecords.addAll(allMyVoiceRecord);
            this.adapter.notifyDataSetChanged();
            this.listView.setSelection(0);
            if (this.refreshLayout != null) {
                this.refreshLayout.finishRefresh();
            }
        }
    }

    public void initView() {
        PathUtil.getInstance().createDirs("chat", "voice", getActivity());
        PermissionUtils.checkRecord(getContext(), new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
                SelectMyVoiceFragment.this.pressLayout.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return SelectMyVoiceFragment.this.voiceRecorder.onPressToSpeakBtnTouch(view, motionEvent, new VoiceRecorderView.EaseVoiceRecorderCallback() {
                            public void onVoiceRecordComplete(final String str, final int i) {
                                LogUtils.e("voiceFilePath=", str + "  time = " + i);
                                FileUtil.amrConvertShelfMp3(SelectMyVoiceFragment.this.getActivity(), str, new FileUtil.OnConvertMp3Listener() {
                                    public void convertFail() {
                                        LogUtils.d("WS_BABY", "convertFail");
                                        RecordBean recordBean = new RecordBean();
                                        recordBean.setSecond(i);
                                        recordBean.setPath(str);
                                        recordBean.setMp3LocPath("");
                                        recordBean.setIsPlayed(false);
                                        recordBean.setIsCollection(false);
                                        recordBean.setIsFromWx(false);
                                        recordBean.setVoiceTag(0);
                                        recordBean.setCreateTimeLong(System.currentTimeMillis());
                                        SQLiteUtils.getInstance().addRecord(recordBean);
                                        SelectMyVoiceFragment.this.mRecords.add(0, recordBean);
                                        SelectMyVoiceFragment.this.adapter.notifyDataSetChanged();
                                        SelectMyVoiceFragment.this.listView.setSelection(0);
                                    }

                                    public void convertSuccess(String str) {
                                        LogUtils.d("WS_BABY", str);
                                        RecordBean recordBean = new RecordBean();
                                        recordBean.setSecond(i);
                                        recordBean.setPath(str);
                                        recordBean.setMp3LocPath(str);
                                        recordBean.setIsPlayed(false);
                                        recordBean.setIsCollection(false);
                                        recordBean.setIsFromWx(false);
                                        recordBean.setVoiceTag(0);
                                        recordBean.setCreateTimeLong(System.currentTimeMillis());
                                        SQLiteUtils.getInstance().addRecord(recordBean);
                                        SelectMyVoiceFragment.this.mRecords.add(0, recordBean);
                                        SelectMyVoiceFragment.this.adapter.notifyDataSetChanged();
                                        SelectMyVoiceFragment.this.listView.setSelection(0);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                SelectMyVoiceFragment.this.initData();
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427539, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        EventBus.getDefault().register(this);
        initView();
        initData();
        return inflate;
    }

    public void onDestroyView() {
        SelectMyVoiceFragment.super.onDestroyView();
        EventBus.getDefault().unregister(this);
        this.unbinder.unbind();
        if (this.adapter != null) {
            this.adapter.onDestroy();
        }
    }

    @Subscribe
    public void onEventMainThread(VoiceEvent voiceEvent) {
        if (voiceEvent.getVoiceFragmentTag() == 0) {
            initData();
        }
    }
}
