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
import com.wx.assistants.adapter.CopyLocalFileAdapter;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AccessibilityOpenTagEvent;
import com.wx.assistants.bean.FileBeanCopyEvent;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.FileBean;
import com.wx.assistants.utils.fileutil.FileUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ObtainCopyFileActivity extends BaseActivity {
    @BindView(2131296529)
    Button confirmSelect;
    /* access modifiers changed from: private */
    public List<FileBean> labList = new ArrayList();
    /* access modifiers changed from: private */
    public ListView label_list;
    /* access modifiers changed from: private */
    public CopyLocalFileAdapter myListAdapter;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public FileBean selectLabelBean;
    public Map<Integer, Boolean> selectMap = new HashMap();
    private String selects = "";

    static {
        StubApp.interface11(6738);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.ObtainCopyFileActivity] */
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
        this.navTitle.setText("选择备份的文件");
        this.label_list = (ListView) findViewById(2131296886);
        try {
            this.selectLabelBean = (FileBean) getIntent().getParcelableExtra("selects");
        } catch (Exception unused) {
        }
        initViewData();
    }

    public void initViewData() {
        new Thread(new Runnable() {
            public void run() {
                List unused = ObtainCopyFileActivity.this.labList = FileUtils.getCopyFiles();
                if (ObtainCopyFileActivity.this.labList != null && ObtainCopyFileActivity.this.labList.size() > 0) {
                    ObtainCopyFileActivity.this.runOnUiThread(new Runnable() {
                        /* JADX WARNING: type inference failed for: r3v1, types: [android.content.Context, com.wx.assistants.activity.ObtainCopyFileActivity] */
                        public void run() {
                            CopyLocalFileAdapter unused = ObtainCopyFileActivity.this.myListAdapter = new CopyLocalFileAdapter(ObtainCopyFileActivity.this.labList, ObtainCopyFileActivity.this);
                            ObtainCopyFileActivity.this.label_list.setAdapter(ObtainCopyFileActivity.this.myListAdapter);
                            ObtainCopyFileActivity.this.label_list.setVisibility(0);
                            ObtainCopyFileActivity.this.myListAdapter.setOnSelectLabelListener(new CopyLocalFileAdapter.OnSelectLabelListener() {
                                public void select(FileBean fileBean) {
                                    FileBean unused = ObtainCopyFileActivity.this.selectLabelBean = fileBean;
                                }

                                public void nuSelect(FileBean fileBean) {
                                    FileBean unused = ObtainCopyFileActivity.this.selectLabelBean = null;
                                }
                            });
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.ObtainCopyFileActivity] */
    @OnClick({2131297049, 2131296529})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296529) {
            try {
                FileBeanCopyEvent fileBeanCopyEvent = new FileBeanCopyEvent();
                if (this.myListAdapter == null) {
                    ToastUtils.showToast(this, "您还没有备份过文件！");
                } else if (this.selectLabelBean != null) {
                    fileBeanCopyEvent.setSelectedBean(this.selectLabelBean);
                    EventBus.getDefault().post(fileBeanCopyEvent);
                    finish();
                } else {
                    ToastUtils.showToast(this, "请选择文件");
                }
            } catch (Exception unused) {
            }
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
