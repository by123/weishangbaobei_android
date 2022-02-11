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
import com.wx.assistants.adapter.MySingleListAdapter;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxSingleTagEvent;
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

public class ObtainSingleTagActivity extends BaseActivity {
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
    private MySingleListAdapter myListAdapter;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    private int selectDefaultSingleTagIndex = 0;
    private List<String> selectLabList = new ArrayList();
    /* access modifiers changed from: private */
    public LabelBean selectLabelBean;
    public Map<Integer, Boolean> selectMap = new HashMap();
    @BindView(2131297322)
    LinearLayout selectWXLayout;
    private ArrayList<String> selectedList = new ArrayList<>();
    /* access modifiers changed from: private */
    public LinkedHashSet<String> selectedPeopleList = new LinkedHashSet<>();
    private String selects = "";

    static {
        StubApp.interface11(6741);
    }

    private void initView() {
        this.navTitle.setText("选择标签");
        try {
            this.selectLabelBean = (LabelBean) getIntent().getParcelableExtra("selects");
        } catch (Exception e) {
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.ObtainSingleTagActivity] */
    public void initListViewData() {
        this.labels.clear();
        this.labList.clear();
        List tagMemberBeans = SQLiteUtils.getInstance().getTagMemberBeans(this.currentWxNum);
        if (tagMemberBeans != null && tagMemberBeans.size() != 0) {
            String wxTags = ((TagMemberBean) tagMemberBeans.get(0)).getWxTags();
            String wxName = ((TagMemberBean) tagMemberBeans.get(0)).getWxName();
            if (!wxTags.equals("")) {
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
                    } catch (Exception e) {
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
                        this.myListAdapter = new MySingleListAdapter(this.labList, this.selectLabelBean, this);
                        this.labelList.setAdapter(this.myListAdapter);
                        this.labelList.setVisibility(0);
                        this.myListAdapter.setOnSelectLabelListener(new MySingleListAdapter.OnSelectLabelListener() {
                            public void nuSelect(LabelBean labelBean) {
                                LabelBean unused = ObtainSingleTagActivity.this.selectLabelBean = null;
                            }

                            public void select(LabelBean labelBean) {
                                LabelBean unused = ObtainSingleTagActivity.this.selectLabelBean = labelBean;
                            }
                        });
                        this.myListAdapter.setOnClickTagNameListener(new MySingleListAdapter.OnClickTagNameListener() {
                            /* JADX WARNING: type inference failed for: r1v2, types: [android.content.Context, com.wx.assistants.activity.ObtainSingleTagActivity] */
                            public void click(int i) {
                                List tagPeoplesBean = SQLiteUtils.getInstance().getTagPeoplesBean(((LabelBean) ObtainSingleTagActivity.this.labList.get(i)).getLabelName(), ObtainSingleTagActivity.this.currentWxNum);
                                if (tagPeoplesBean != null && tagPeoplesBean.size() > 0) {
                                    DialogUIUtils.dialogTagMembers(ObtainSingleTagActivity.this, tagPeoplesBean);
                                }
                            }
                        });
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.ObtainSingleTagActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.ObtainSingleTagActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        final List allTagBean = SQLiteUtils.getInstance().getAllTagBean();
        if (allTagBean != null && allTagBean.size() > 0) {
            try {
                this.selectDefaultSingleTagIndex = ((Integer) SPUtils.get(this, "selectDefaultSingleTagIndex", 0)).intValue();
            } catch (Exception e) {
            }
            if (this.selectDefaultSingleTagIndex >= allTagBean.size()) {
                SPUtils.put(this, "selectDefaultSingleTagIndex", Integer.valueOf(allTagBean.size() - 1));
                this.selectDefaultSingleTagIndex = allTagBean.size() - 1;
            }
            this.currentWxNum = ((TagBean) allTagBean.get(this.selectDefaultSingleTagIndex)).getWxNum();
            this.currentWxName = ((TagBean) allTagBean.get(this.selectDefaultSingleTagIndex)).getWxName();
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
            this.flowViewGroup.setAdapter(tagSelectAdapter, this.selectDefaultSingleTagIndex);
            this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.ObtainSingleTagActivity] */
                public void itemClick(int i) {
                    SPUtils.put(ObtainSingleTagActivity.this, "selectDefaultSingleTagIndex", Integer.valueOf(i));
                    String unused = ObtainSingleTagActivity.this.currentWxNum = ((TagBean) allTagBean.get(i)).getWxNum();
                    String unused2 = ObtainSingleTagActivity.this.currentWxName = ((TagBean) allTagBean.get(i)).getWxName();
                    ObtainSingleTagActivity.this.initListViewData();
                }
            });
        }
        initListViewData();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.ObtainSingleTagActivity] */
    @OnClick({2131297049, 2131296728, 2131296529})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296529) {
            try {
                final StringBuilder sb = new StringBuilder();
                final WxSingleTagEvent wxSingleTagEvent = new WxSingleTagEvent();
                if (this.myListAdapter == null) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxSingleTagEvent.setSelectedPeopleList(ObtainSingleTagActivity.this.selectedPeopleList);
                            wxSingleTagEvent.setSelectedPeoples(sb.toString());
                            EventBus.getDefault().post(wxSingleTagEvent);
                            ObtainSingleTagActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                            wxSingleTagEvent.setSelectedLabelBean((LabelBean) null);
                            wxSingleTagEvent.setSelectedPeopleList((LinkedHashSet<String>) null);
                            wxSingleTagEvent.setSelectedPeoples((String) null);
                            EventBus.getDefault().post(wxSingleTagEvent);
                        }
                    });
                } else if (this.selectLabelBean != null) {
                    wxSingleTagEvent.setSelectedLabelBean(this.selectLabelBean);
                    this.selectedList = new ArrayList<>();
                    this.selectedList.add(this.selectLabelBean.getLabelName());
                    if (this.selectedList == null || this.selectedList.size() <= 0) {
                        DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                            public void onClick(View view) {
                                wxSingleTagEvent.setSelectedPeopleList(ObtainSingleTagActivity.this.selectedPeopleList);
                                wxSingleTagEvent.setSelectedPeoples(sb.toString());
                                EventBus.getDefault().post(wxSingleTagEvent);
                                ObtainSingleTagActivity.this.finish();
                            }
                        }, new View.OnClickListener() {
                            public void onClick(View view) {
                                wxSingleTagEvent.setSelectedLabelBean((LabelBean) null);
                                wxSingleTagEvent.setSelectedPeopleList((LinkedHashSet<String>) null);
                                wxSingleTagEvent.setSelectedPeoples((String) null);
                                EventBus.getDefault().post(wxSingleTagEvent);
                            }
                        });
                        return;
                    }
                    for (int i = 0; i < this.selectedList.size(); i++) {
                        List tagPeoplesBean = SQLiteUtils.getInstance().getTagPeoplesBean(this.selectedList.get(i), this.currentWxNum);
                        String str = "";
                        if (tagPeoplesBean != null) {
                            if (tagPeoplesBean.size() > 0) {
                                str = ((TagPeoplesBean) tagPeoplesBean.get(0)).getWxPeoples();
                            }
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
                    wxSingleTagEvent.setSelectedPeopleList(this.selectedPeopleList);
                    wxSingleTagEvent.setSelectedPeoples(sb.toString());
                    EventBus.getDefault().post(wxSingleTagEvent);
                    finish();
                } else {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            wxSingleTagEvent.setSelectedPeopleList(ObtainSingleTagActivity.this.selectedPeopleList);
                            wxSingleTagEvent.setSelectedPeoples(sb.toString());
                            EventBus.getDefault().post(wxSingleTagEvent);
                            ObtainSingleTagActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                            wxSingleTagEvent.setSelectedLabelBean((LabelBean) null);
                            wxSingleTagEvent.setSelectedPeopleList((LinkedHashSet<String>) null);
                            wxSingleTagEvent.setSelectedPeoples((String) null);
                            EventBus.getDefault().post(wxSingleTagEvent);
                        }
                    });
                }
            } catch (Exception e) {
            }
        } else if (id == 2131296728) {
            startCheck("11", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("11");
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    ObtainSingleTagActivity.this.startWX(operationParameterModel);
                }
            });
        } else if (id == 2131297049) {
            back();
        }
    }
}
