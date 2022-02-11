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
import com.wx.assistants.Enity.TagBeanCompay;
import com.wx.assistants.Enity.TagMemberBeanCompany;
import com.wx.assistants.adapter.MyListAdapter;
import com.wx.assistants.adapter.TagSelectAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.LabelBean;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.bean.WxTagCompanyEvent;
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
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ObtainTagCompanyActivity extends BaseActivity {
    @BindView(2131296529)
    Button confirmSelect;
    /* access modifiers changed from: private */
    public String currentWxNum = "0";
    @BindView(2131296681)
    TagCloudLayout flowViewGroup;
    @BindView(2131296728)
    Button getLable;
    private List<LabelBean> labList = new ArrayList();
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
    private int selectDefaultTagCompanyIndex = 0;
    private List<String> selectLabList = new ArrayList();
    public Map<Integer, Boolean> selectMap = new HashMap();
    @BindView(2131297322)
    LinearLayout selectWXLayout;
    private ArrayList<String> selectedList = new ArrayList<>();
    private String selects = "";

    static {
        StubApp.interface11(6744);
    }

    private void initView() {
        this.navTitle.setText("选择标签");
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagCompanyActivity] */
    public void initListViewData() {
        this.labels.clear();
        this.labList.clear();
        List tagMemberBeansCompany = SQLiteUtils.getInstance().getTagMemberBeansCompany(this.currentWxNum);
        if (tagMemberBeansCompany != null && tagMemberBeansCompany.size() != 0) {
            String wxTags = ((TagMemberBeanCompany) tagMemberBeansCompany.get(0)).getWxTags();
            String wxCompany = ((TagMemberBeanCompany) tagMemberBeansCompany.get(0)).getWxCompany();
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
                    labelBean.setLabelNameText(wxCompany);
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

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagCompanyActivity] */
    @Subscribe
    public void onEventMainThread(AccessibilityOpenTagEvent accessibilityOpenTagEvent) {
        int tag = accessibilityOpenTagEvent.getTag();
        if (tag == 0) {
            ToastUtils.showToast(this, "辅助服务已开启");
        } else if (tag == 1) {
            ToastUtils.showToast(this, "悬浮窗已开启");
        }
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context, com.wx.assistants.activity.ObtainTagCompanyActivity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        final List tagBeanCompany = SQLiteUtils.getInstance().getTagBeanCompany();
        if (tagBeanCompany != null && tagBeanCompany.size() > 0) {
            try {
                this.selectDefaultTagCompanyIndex = ((Integer) SPUtils.get(this, "selectDefaultTagCompanyIndex", 0)).intValue();
            } catch (Exception e) {
            }
            if (this.selectDefaultTagCompanyIndex >= tagBeanCompany.size()) {
                SPUtils.put(this, "selectDefaultTagCompanyIndex", Integer.valueOf(tagBeanCompany.size() - 1));
                this.selectDefaultTagCompanyIndex = tagBeanCompany.size() - 1;
            }
            this.currentWxNum = ((TagBeanCompay) tagBeanCompany.get(this.selectDefaultTagCompanyIndex)).getWxCompany();
            PrintStream printStream = System.out;
            printStream.println("WS_BABY_TAGS_" + tagBeanCompany.size() + ListUtils.DEFAULT_JOIN_SEPARATOR + tagBeanCompany.toString());
        }
        if (tagBeanCompany == null || tagBeanCompany.size() <= 0) {
            this.selectWXLayout.setVisibility(8);
        } else {
            this.selectWXLayout.setVisibility(0);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < tagBeanCompany.size(); i++) {
                arrayList.add(((TagBeanCompay) tagBeanCompany.get(i)).getWxCompany());
            }
            TagSelectAdapter tagSelectAdapter = new TagSelectAdapter(this, arrayList);
            this.flowViewGroup.setNilAdapter();
            this.flowViewGroup.setAdapter(tagSelectAdapter, this.selectDefaultTagCompanyIndex);
            this.flowViewGroup.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagCompanyActivity] */
                public void itemClick(int i) {
                    SPUtils.put(ObtainTagCompanyActivity.this, "selectDefaultTagCompanyIndex", Integer.valueOf(i));
                    String unused = ObtainTagCompanyActivity.this.currentWxNum = ((TagBeanCompay) tagBeanCompany.get(i)).getWxCompany();
                    ObtainTagCompanyActivity.this.initListViewData();
                }
            });
        }
        initListViewData();
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.wx.assistants.activity.ObtainTagCompanyActivity] */
    @OnClick({2131297049, 2131296728, 2131296529})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296529) {
            try {
                new StringBuilder();
                final WxTagCompanyEvent wxTagCompanyEvent = new WxTagCompanyEvent();
                if (this.myListAdapter == null || this.myListAdapter.map == null) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            EventBus.getDefault().post(wxTagCompanyEvent);
                            ObtainTagCompanyActivity.this.finish();
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
                            EventBus.getDefault().post(wxTagCompanyEvent);
                            ObtainTagCompanyActivity.this.finish();
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
                    wxTagCompanyEvent.setSelectedTagList(this.selectedList);
                }
                if (this.selectedList == null || this.selectedList.size() <= 0) {
                    DialogUIUtils.GroupTabSelectDialog(this, "您没有选择标签 , 请选择标签 , 若放弃选择 之前选择的标签将会清空！", new View.OnClickListener() {
                        public void onClick(View view) {
                            EventBus.getDefault().post(wxTagCompanyEvent);
                            ObtainTagCompanyActivity.this.finish();
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                    return;
                }
                EventBus.getDefault().post(wxTagCompanyEvent);
                finish();
            } catch (Exception e) {
            }
        } else if (id != 2131296728) {
            if (id == 2131297049) {
                back();
            }
        } else if (!checkCompanyWxApp(this)) {
            ToastUtils.showToast(this, "请先安装企业微信！");
        } else {
            startCheck("10011", true, new BaseActivity.OnStartCheckListener() {
                public void checkEnd() {
                    OperationParameterModel operationParameterModel = new OperationParameterModel();
                    operationParameterModel.setTaskNum("10011");
                    MyApplication.instance.setOperationParameterModel(operationParameterModel);
                    ObtainTagCompanyActivity.this.startWXCompany(operationParameterModel);
                }
            });
        }
    }
}
