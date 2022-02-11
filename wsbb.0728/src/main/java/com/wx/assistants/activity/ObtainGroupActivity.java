package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.Enity.GroupBean;
import com.wx.assistants.Enity.GroupMemberBean;
import com.wx.assistants.adapter.MyListAdapter;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxGroupEvent;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.wx.assistants.view.TagCloudLayout;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ObtainGroupActivity extends BaseActivity {
    @BindView(2131296529)
    Button confirmSelect;
    /* access modifiers changed from: private */
    public String currentWxNum = "0";
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    @BindView(2131296728)
    Button getLable;
    /* access modifiers changed from: private */
    public List<String> groupList = new ArrayList();
    private List<LabelBean> labList = new ArrayList();
    @BindView(2131296886)
    ListView labelList;
    /* access modifiers changed from: private */
    public MyListAdapter myListAdapter;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297182)
    CheckBox radioBtnAll;
    private int selectDefaultGroupIndex = 0;
    private List<String> selectGroupList = new ArrayList();
    public Map<Integer, Boolean> selectMap = new HashMap();
    @BindView(2131297322)
    LinearLayout selectWXLayout;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> selectedList = new LinkedHashSet<>();
    private String selects = "";

    static {
        StubApp.interface11(6739);
    }

    private void initView() {
        this.navTitle.setText("选择群组");
        this.radioBtnAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (ObtainGroupActivity.this.groupList != null && ObtainGroupActivity.this.groupList.size() > 0) {
                    if (z) {
                        ObtainGroupActivity.this.selectMap = new HashMap();
                        for (int i = 0; i < ObtainGroupActivity.this.groupList.size(); i++) {
                            ObtainGroupActivity.this.selectMap.put(Integer.valueOf(i), true);
                        }
                    } else {
                        ObtainGroupActivity.this.selectMap = new HashMap();
                        for (int i2 = 0; i2 < ObtainGroupActivity.this.groupList.size(); i2++) {
                            ObtainGroupActivity.this.selectMap.put(Integer.valueOf(i2), false);
                        }
                    }
                    ObtainGroupActivity.this.myListAdapter.setSelectMap(ObtainGroupActivity.this.selectMap);
                    ObtainGroupActivity.this.myListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupActivity] */
    public void initListViewData() {
        this.groupList = new ArrayList();
        this.labList = new ArrayList();
        List groupMemberBeans = SQLiteUtils.getInstance().getGroupMemberBeans(this.currentWxNum);
        if (groupMemberBeans != null && groupMemberBeans.size() != 0) {
            String wxGroups = ((GroupMemberBean) groupMemberBeans.get(0)).getWxGroups();
            if (!wxGroups.equals("")) {
                if (wxGroups.contains(";")) {
                    this.groupList.addAll(Arrays.asList(wxGroups.split(";")));
                } else {
                    this.groupList.add(wxGroups);
                }
                if (this.groupList != null && !this.groupList.isEmpty()) {
                    try {
                        this.selects = getIntent().getStringExtra("selects");
                        if (this.selects != null && !"".equals(this.selects)) {
                            this.selectMap.clear();
                            this.selectGroupList.clear();
                            if (this.selects.contains(";")) {
                                String[] split = this.selects.split(";");
                                for (String add : split) {
                                    this.selectGroupList.add(add);
                                }
                            } else {
                                this.selectGroupList.add(this.selects);
                            }
                        }
                    } catch (Exception e) {
                    }
                    for (int i = 0; i < this.groupList.size(); i++) {
                        LabelBean labelBean = new LabelBean();
                        labelBean.setLabelName(this.groupList.get(i));
                        if (this.selectGroupList == null || this.selectGroupList.size() <= 0) {
                            this.selectMap.put(Integer.valueOf(i), false);
                        } else if (this.selectGroupList.contains(this.groupList.get(i))) {
                            this.selectMap.put(Integer.valueOf(i), true);
                        } else {
                            this.selectMap.put(Integer.valueOf(i), false);
                        }
                        this.labList.add(labelBean);
                    }
                    if (this.labList != null && this.labList.size() > 0) {
                        this.myListAdapter = new MyListAdapter(this.labList, this.selectMap, this);
                        this.labelList.setAdapter(this.myListAdapter);
                        this.labelList.setVisibility(0);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.ObtainGroupActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        final List allGroupBean = SQLiteUtils.getInstance().getAllGroupBean();
        if (allGroupBean != null && allGroupBean.size() > 0) {
            try {
                this.selectDefaultGroupIndex = ((Integer) SPUtils.get(this, "selectDefaultGroupIndex", 0)).intValue();
            } catch (Exception e) {
            }
            if (this.selectDefaultGroupIndex >= allGroupBean.size()) {
                SPUtils.put(this, "selectDefaultGroupIndex", Integer.valueOf(allGroupBean.size() - 1));
                this.selectDefaultGroupIndex = allGroupBean.size() - 1;
            }
            this.currentWxNum = ((GroupBean) allGroupBean.get(this.selectDefaultGroupIndex)).getWxNum();
            PrintStream printStream = System.out;
            printStream.println("WS_BABY_GROUPS_" + allGroupBean.size() + ListUtils.DEFAULT_JOIN_SEPARATOR + allGroupBean.toString());
        }
        if (allGroupBean == null || allGroupBean.size() <= 0) {
            this.selectWXLayout.setVisibility(8);
        } else {
            this.selectWXLayout.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < allGroupBean.size(); i++) {
                GroupBean groupBean = (GroupBean) allGroupBean.get(i);
                arrayList.add(groupBean.getWxName() + "_" + groupBean.getWxNum());
            }
            TagSelectAdapter tagSelectAdapter = new TagSelectAdapter(this, arrayList);
            this.flowViewGroup.setNilAdapter();
            this.flowViewGroup.setAdapter(tagSelectAdapter, this.selectDefaultGroupIndex);
            this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupActivity] */
                public void itemClick(int i) {
                    SPUtils.put(ObtainGroupActivity.this, "selectDefaultGroupIndex", Integer.valueOf(i));
                    String unused = ObtainGroupActivity.this.currentWxNum = ((GroupBean) allGroupBean.get(i)).getWxNum();
                    ObtainGroupActivity.this.initListViewData();
                }
            });
        }
        initListViewData();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupActivity] */
    @OnClick({2131297049, 2131296728, 2131296529})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296529) {
            try {
                final WxGroupEvent wxGroupEvent = new WxGroupEvent();
                if (this.myListAdapter == null || this.myListAdapter.map == null) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择群组 , 请选择群组 , 若放弃选择 之前选择的群组将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxGroupEvent.setSelectedGroupList(ObtainGroupActivity.this.selectedList);
                            EventBus.getDefault().post(wxGroupEvent);
                            ObtainGroupActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                    return;
                }
                Map<Integer, Boolean> map = this.myListAdapter.map;
                if (map == null || map.size() <= 0) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择群组 , 请选择群组 , 若放弃选择 之前选择的群组将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxGroupEvent.setSelectedGroupList(ObtainGroupActivity.this.selectedList);
                            EventBus.getDefault().post(wxGroupEvent);
                            ObtainGroupActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                    return;
                }
                this.selectedList = new LinkedHashSet<>();
                for (int i = 0; i < this.groupList.size(); i++) {
                    if (map.get(Integer.valueOf(i)).booleanValue()) {
                        this.selectedList.add(this.groupList.get(i));
                    }
                }
                if (this.selectedList == null || this.selectedList.size() <= 0) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择群组 , 请选择群组 , 若放弃选择 之前选择的群组将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxGroupEvent.setSelectedGroupList(ObtainGroupActivity.this.selectedList);
                            EventBus.getDefault().post(wxGroupEvent);
                            ObtainGroupActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                    return;
                }
                wxGroupEvent.setSelectedGroupList(this.selectedList);
                EventBus.getDefault().post(wxGroupEvent);
                finish();
            } catch (Exception e) {
            }
        } else if (id == 2131296728) {
            startCheck("14", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("14");
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    ObtainGroupActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297049) {
            back();
        }
    }
}
