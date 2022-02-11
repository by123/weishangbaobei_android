package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.Enity.GroupBeanAll;
import com.wx.assistants.Enity.GroupMemberBeanAll;
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

public class ObtainGroupAllActivity extends BaseActivity {
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
    @BindView(2131297051)
    ImageView navRightImg;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297182)
    CheckBox radioBtnAll;
    private int selectDefaultGroupAllIndex = 0;
    private List<String> selectGroupList = new ArrayList();
    public Map<Integer, Boolean> selectMap = new HashMap();
    @BindView(2131297322)
    LinearLayout selectWXLayout;
    /* access modifiers changed from: private */
    public LinkedHashSet<String> selectedList = new LinkedHashSet<>();
    private String selects = "";
    @BindView(2131297363)
    LinearLayout shadowLinearLayout;

    static {
        StubApp.interface11(6740);
    }

    private void initView() {
        this.navTitle.setText("选择群组");
        this.radioBtnAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (ObtainGroupAllActivity.this.groupList != null && ObtainGroupAllActivity.this.groupList.size() > 0) {
                    if (z) {
                        ObtainGroupAllActivity.this.selectMap = new HashMap();
                        for (int i = 0; i < ObtainGroupAllActivity.this.groupList.size(); i++) {
                            ObtainGroupAllActivity.this.selectMap.put(Integer.valueOf(i), true);
                        }
                    } else {
                        ObtainGroupAllActivity.this.selectMap = new HashMap();
                        for (int i2 = 0; i2 < ObtainGroupAllActivity.this.groupList.size(); i2++) {
                            ObtainGroupAllActivity.this.selectMap.put(Integer.valueOf(i2), false);
                        }
                    }
                    ObtainGroupAllActivity.this.myListAdapter.setSelectMap(ObtainGroupAllActivity.this.selectMap);
                    ObtainGroupAllActivity.this.myListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupAllActivity] */
    public void initListViewData() {
        this.groupList = new ArrayList();
        this.labList = new ArrayList();
        List groupMemberBeansAll = SQLiteUtils.getInstance().getGroupMemberBeansAll(this.currentWxNum);
        if (groupMemberBeansAll != null && groupMemberBeansAll.size() != 0) {
            String wxGroups = ((GroupMemberBeanAll) groupMemberBeansAll.get(0)).getWxGroups();
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupAllActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.ObtainGroupAllActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        final List allGroupBeanAll = SQLiteUtils.getInstance().getAllGroupBeanAll();
        if (allGroupBeanAll != null && allGroupBeanAll.size() > 0) {
            try {
                this.selectDefaultGroupAllIndex = ((Integer) SPUtils.get(this, "selectDefaultGroupAllIndex", 0)).intValue();
            } catch (Exception e) {
            }
            if (this.selectDefaultGroupAllIndex >= allGroupBeanAll.size()) {
                SPUtils.put(this, "selectDefaultGroupAllIndex", Integer.valueOf(allGroupBeanAll.size() - 1));
                this.selectDefaultGroupAllIndex = allGroupBeanAll.size() - 1;
            }
            this.currentWxNum = ((GroupBeanAll) allGroupBeanAll.get(this.selectDefaultGroupAllIndex)).getWxNum();
            PrintStream printStream = System.out;
            printStream.println("WS_BABY_GROUPS_" + allGroupBeanAll.size() + ListUtils.DEFAULT_JOIN_SEPARATOR + allGroupBeanAll.toString());
        }
        if (allGroupBeanAll == null || allGroupBeanAll.size() <= 0) {
            this.selectWXLayout.setVisibility(8);
        } else {
            this.selectWXLayout.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < allGroupBeanAll.size(); i++) {
                GroupBeanAll groupBeanAll = (GroupBeanAll) allGroupBeanAll.get(i);
                arrayList.add(groupBeanAll.getWxName() + "_" + groupBeanAll.getWxNum());
            }
            TagSelectAdapter tagSelectAdapter = new TagSelectAdapter(this, arrayList);
            this.flowViewGroup.setNilAdapter();
            this.flowViewGroup.setAdapter(tagSelectAdapter, this.selectDefaultGroupAllIndex);
            this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupAllActivity] */
                public void itemClick(int i) {
                    SPUtils.put(ObtainGroupAllActivity.this, "selectDefaultGroupAllIndex", Integer.valueOf(i));
                    String unused = ObtainGroupAllActivity.this.currentWxNum = ((GroupBeanAll) allGroupBeanAll.get(i)).getWxNum();
                    ObtainGroupAllActivity.this.initListViewData();
                }
            });
        }
        initListViewData();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.ObtainGroupAllActivity] */
    @OnClick({2131297049, 2131296728, 2131296529})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296529) {
            try {
                final WxGroupEvent wxGroupEvent = new WxGroupEvent();
                if (this.myListAdapter == null || this.myListAdapter.map == null) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择群组 , 请选择群组 , 若放弃选择 之前选择的群组将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxGroupEvent.setSelectedGroupList(ObtainGroupAllActivity.this.selectedList);
                            EventBus.getDefault().post(wxGroupEvent);
                            ObtainGroupAllActivity.this.finish();
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
                            wxGroupEvent.setSelectedGroupList(ObtainGroupAllActivity.this.selectedList);
                            EventBus.getDefault().post(wxGroupEvent);
                            ObtainGroupAllActivity.this.finish();
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
                            wxGroupEvent.setSelectedGroupList(ObtainGroupAllActivity.this.selectedList);
                            EventBus.getDefault().post(wxGroupEvent);
                            ObtainGroupAllActivity.this.finish();
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
            startCheck("140", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("140");
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    ObtainGroupAllActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297049) {
            back();
        }
    }
}
