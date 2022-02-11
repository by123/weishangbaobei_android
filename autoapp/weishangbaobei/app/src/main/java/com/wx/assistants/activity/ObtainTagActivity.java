package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.Enity.TagBean;
import com.wx.assistants.Enity.TagMemberBean;
import com.wx.assistants.Enity.TagPeoplesBean;
import com.wx.assistants.adapter.MyListAdapter;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagEvent;
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

public class ObtainTagActivity extends BaseActivity {
    @BindView(2131296529)
    Button confirmSelect;
    /* access modifiers changed from: private */
    public String currentWxName = "0";
    /* access modifiers changed from: private */
    public String currentWxNum = "0";
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    @BindView(2131296728)
    Button getLable;
    /* access modifiers changed from: private */
    public List<LabelBean> labList = new ArrayList();
    @BindView(2131296886)
    ListView labelList;
    private List<String> labels = new ArrayList();
    private MyListAdapter myListAdapter;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    private int selectDefaultTagIndex = 0;
    private List<String> selectLabList = new ArrayList();
    public Map<Integer, Boolean> selectMap = new HashMap();
    @BindView(2131297322)
    LinearLayout selectWXLayout;
    private ArrayList<String> selectedList = new ArrayList<>();
    /* access modifiers changed from: private */
    public LinkedHashSet<String> selectedPeopleList = new LinkedHashSet<>();
    private String selects = "";

    static {
        StubApp.interface11(6743);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    private void initView() {
        this.navTitle.setText("选择标签");
        this.getLable.setText("启动微信获取标签");
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.ObtainTagActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        final List allTagBean = SQLiteUtils.getInstance().getAllTagBean();
        if (allTagBean != null && allTagBean.size() > 0) {
            try {
                this.selectDefaultTagIndex = ((Integer) SPUtils.get(this, "selectDefaultTagIndex", 0)).intValue();
            } catch (Exception unused) {
            }
            if (this.selectDefaultTagIndex >= allTagBean.size()) {
                SPUtils.put(this, "selectDefaultTagIndex", Integer.valueOf(allTagBean.size() - 1));
                this.selectDefaultTagIndex = allTagBean.size() - 1;
            }
            this.currentWxNum = ((TagBean) allTagBean.get(this.selectDefaultTagIndex)).getWxNum();
            this.currentWxName = ((TagBean) allTagBean.get(this.selectDefaultTagIndex)).getWxName();
            PrintStream printStream = System.out;
            printStream.println("WS_BABY_TAGS_" + allTagBean.size() + ListUtils.DEFAULT_JOIN_SEPARATOR + allTagBean.toString());
        }
        if (allTagBean == null || allTagBean.size() <= 0) {
            this.selectWXLayout.setVisibility(8);
        } else {
            this.selectWXLayout.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < allTagBean.size(); i++) {
                TagBean tagBean = (TagBean) allTagBean.get(i);
                arrayList.add(tagBean.getWxName() + "_" + tagBean.getWxNum());
            }
            TagSelectAdapter tagSelectAdapter = new TagSelectAdapter(this, arrayList);
            this.flowViewGroup.setNilAdapter();
            this.flowViewGroup.setAdapter(tagSelectAdapter, this.selectDefaultTagIndex);
            this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagActivity] */
                public void itemClick(int i) {
                    SPUtils.put(ObtainTagActivity.this, "selectDefaultTagIndex", Integer.valueOf(i));
                    String unused = ObtainTagActivity.this.currentWxNum = ((TagBean) allTagBean.get(i)).getWxNum();
                    String unused2 = ObtainTagActivity.this.currentWxName = ((TagBean) allTagBean.get(i)).getWxName();
                    ObtainTagActivity.this.initListViewData();
                }
            });
        }
        initListViewData();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagActivity] */
    public void initListViewData() {
        this.labels.clear();
        this.labList.clear();
        List tagMemberBeans = SQLiteUtils.getInstance().getTagMemberBeans(this.currentWxNum);
        if (tagMemberBeans != null && tagMemberBeans.size() != 0) {
            String wxTags = ((TagMemberBean) tagMemberBeans.get(0)).getWxTags();
            String wxName = ((TagMemberBean) tagMemberBeans.get(0)).getWxName();
            if (wxTags.contains(";")) {
                this.labels.addAll(Arrays.asList(wxTags.split(";")));
            } else {
                this.labels.add(wxTags);
            }
            if (this.labelList != null && !this.labels.isEmpty()) {
                try {
                    this.selects = getIntent().getStringExtra("selects");
                    if (this.selects != null && !"".equals(this.selects)) {
                        this.selectMap.clear();
                        this.selectLabList.clear();
                        if (this.selects.contains(";")) {
                            String[] split = this.selects.split(";");
                            for (String add : split) {
                                this.selectLabList.add(add);
                            }
                        } else {
                            this.selectLabList.add(this.selects);
                        }
                    }
                } catch (Exception unused) {
                }
                for (int i = 0; i < this.labels.size(); i++) {
                    LabelBean labelBean = new LabelBean();
                    labelBean.setLabelName(this.labels.get(i));
                    labelBean.setLabelNameText(wxName);
                    this.labList.add(labelBean);
                    if (this.selectLabList == null || this.selectLabList.size() <= 0) {
                        this.selectMap.put(Integer.valueOf(i), false);
                    } else if (this.selectLabList.contains(this.labels.get(i))) {
                        this.selectMap.put(Integer.valueOf(i), true);
                    } else {
                        this.selectMap.put(Integer.valueOf(i), false);
                    }
                }
                if (this.labList != null && this.labList.size() > 0) {
                    this.myListAdapter = new MyListAdapter(this.labList, this.selectMap, this);
                    this.labelList.setAdapter(this.myListAdapter);
                    this.labelList.setVisibility(0);
                    this.myListAdapter.setOnClickTagNameListener(new MyListAdapter.OnClickTagNameListener() {
                        /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.ObtainTagActivity] */
                        public void click(int i) {
                            List tagPeoplesBean = SQLiteUtils.getInstance().getTagPeoplesBean(((LabelBean) ObtainTagActivity.this.labList.get(i)).getLabelName(), ObtainTagActivity.this.currentWxNum);
                            if (tagPeoplesBean != null && tagPeoplesBean.size() > 0) {
                                DialogUIUtils.dialogTagMembers(ObtainTagActivity.this, tagPeoplesBean);
                            }
                        }
                    });
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagActivity] */
    @OnClick({2131297049, 2131296728, 2131296529})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296529) {
            try {
                final StringBuilder sb = new StringBuilder();
                final WxTagEvent wxTagEvent = new WxTagEvent();
                if (this.myListAdapter == null || this.myListAdapter.map == null) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxTagEvent.setSelectedPeopleList(ObtainTagActivity.this.selectedPeopleList);
                            wxTagEvent.setSelectedPeoples(sb.toString());
                            EventBus.getDefault().post(wxTagEvent);
                            ObtainTagActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                    return;
                }
                Map<Integer, Boolean> map = this.myListAdapter.map;
                if (map == null || map.size() <= 0) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxTagEvent.setSelectedPeopleList(ObtainTagActivity.this.selectedPeopleList);
                            wxTagEvent.setSelectedPeoples(sb.toString());
                            EventBus.getDefault().post(wxTagEvent);
                            ObtainTagActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                    return;
                }
                this.selectedList = new ArrayList<>();
                for (int i = 0; i < this.labels.size(); i++) {
                    if (map.get(Integer.valueOf(i)).booleanValue()) {
                        this.selectedList.add(this.labels.get(i));
                    }
                }
                if (this.selectedList != null && this.selectedList.size() > 0) {
                    wxTagEvent.setSelectedTagList(this.selectedList);
                }
                if (this.selectedList == null || this.selectedList.size() <= 0) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxTagEvent.setSelectedPeopleList(ObtainTagActivity.this.selectedPeopleList);
                            wxTagEvent.setSelectedPeoples(sb.toString());
                            EventBus.getDefault().post(wxTagEvent);
                            ObtainTagActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                    return;
                }
                for (int i2 = 0; i2 < this.selectedList.size(); i2++) {
                    List tagPeoplesBean = SQLiteUtils.getInstance().getTagPeoplesBean(this.selectedList.get(i2), this.currentWxNum);
                    String str = "";
                    if (tagPeoplesBean != null && tagPeoplesBean.size() > 0) {
                        str = ((TagPeoplesBean) tagPeoplesBean.get(0)).getWxPeoples();
                    }
                    sb.append(str);
                    if (str != null) {
                        if (str.contains(";")) {
                            this.selectedPeopleList.addAll(Arrays.asList(str.split(";")));
                        } else {
                            this.selectedPeopleList.add(str);
                        }
                    }
                }
                wxTagEvent.setSelectedPeopleList(this.selectedPeopleList);
                wxTagEvent.setSelectedPeoples(sb.toString());
                EventBus.getDefault().post(wxTagEvent);
                finish();
            } catch (Exception unused) {
            }
        } else if (id == 2131296728) {
            startCheck("11", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("11");
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    ObtainTagActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297049) {
            back();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
