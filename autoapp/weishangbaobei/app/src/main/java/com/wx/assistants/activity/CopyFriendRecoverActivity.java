package com.wx.assistants.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stub.StubApp;
import com.wx.assistants.Enity.CopyFriendBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.FileBeanCopyEvent;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.dialog.AlertEditDefaultDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.FileBean;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.wx.assistants.view.FriendsMoveBottomLayout;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CopyFriendRecoverActivity extends BaseActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public ListView contact_list;
    /* access modifiers changed from: private */
    public int currentPage = 0;
    @BindView(2131296721)
    FriendsMoveBottomLayout friendsMoveBottomLayout;
    /* access modifiers changed from: private */
    public MyAdapter myAdapter;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public int pageSize = 5000;
    /* access modifiers changed from: private */
    public List<CopyFriendBean> recoverFriendBeanList;
    /* access modifiers changed from: private */
    public ArrayList<CopyFriendBean> recoverFriendBeans = new ArrayList<>();
    /* access modifiers changed from: private */
    public TextView selectPeopleNum;

    static {
        StubApp.interface11(6721);
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    static /* synthetic */ int access$408(CopyFriendRecoverActivity copyFriendRecoverActivity) {
        int i = copyFriendRecoverActivity.currentPage;
        copyFriendRecoverActivity.currentPage = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void initView() {
        this.recoverFriendBeanList = new ArrayList();
        this.navTitle.setText("选择迁移的好友");
        this.navRightLayout.setVisibility(0);
        this.navRightText.setText("确认迁移");
        this.contact_list = (ListView) findViewById(2131296530);
        this.selectPeopleNum = (TextView) findViewById(2131297314);
        initList();
        this.navRightLayout.setOnClickListener(this);
        this.contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v25, types: [android.content.Context, com.wx.assistants.activity.CopyFriendRecoverActivity] */
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (((CopyFriendBean) CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked) {
                    ((CopyFriendBean) CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked = false;
                    CopyFriendRecoverActivity.this.recoverFriendBeans.remove(CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i));
                } else if (CopyFriendRecoverActivity.this.recoverFriendBeans.size() >= 30) {
                    ToastUtils.showToast(CopyFriendRecoverActivity.this, "最多可以选择30人");
                } else {
                    ((CopyFriendBean) CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked = true;
                    CopyFriendRecoverActivity.this.recoverFriendBeans.add(CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i));
                }
                TextView access$200 = CopyFriendRecoverActivity.this.selectPeopleNum;
                access$200.setText(CopyFriendRecoverActivity.this.recoverFriendBeans.size() + "");
                CopyFriendRecoverActivity.this.myAdapter.notifyDataSetChanged();
            }
        });
        this.navBack.setOnClickListener(this);
        this.contact_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i + i2 == i3) {
                    CopyFriendRecoverActivity.access$408(CopyFriendRecoverActivity.this);
                    List allCopyFriends = SQLiteUtils.getInstance().getAllCopyFriends(CopyFriendRecoverActivity.this.currentPage, CopyFriendRecoverActivity.this.pageSize);
                    if (allCopyFriends != null && allCopyFriends.size() > 0) {
                        for (int i4 = 0; i4 < allCopyFriends.size(); i4++) {
                            ((CopyFriendBean) allCopyFriends.get(i4)).setChecked(false);
                        }
                        CopyFriendRecoverActivity.this.recoverFriendBeanList.addAll(allCopyFriends);
                        CopyFriendRecoverActivity.this.myAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        this.friendsMoveBottomLayout.setOnFriendsMoveListener(new FriendsMoveBottomLayout.OnFriendsMoveListener() {
            public void cleanAllSuccess() {
                CopyFriendRecoverActivity.this.initList();
            }

            public void cleanAddedSuccess() {
                CopyFriendRecoverActivity.this.initList();
            }
        });
    }

    /* access modifiers changed from: private */
    public void initList() {
        this.recoverFriendBeanList.clear();
        List allCopyFriends = SQLiteUtils.getInstance().getAllCopyFriends(this.currentPage, this.pageSize);
        if (allCopyFriends == null || allCopyFriends.size() <= 0) {
            this.recoverFriendBeanList.addAll(allCopyFriends);
            this.myAdapter = new MyAdapter();
            this.contact_list.setAdapter(this.myAdapter);
        } else {
            for (int i = 0; i < allCopyFriends.size(); i++) {
                ((CopyFriendBean) allCopyFriends.get(i)).setChecked(false);
            }
            this.recoverFriendBeanList.addAll(allCopyFriends);
            this.myAdapter = new MyAdapter();
            this.contact_list.setAdapter(this.myAdapter);
        }
        this.friendsMoveBottomLayout.setData(this.recoverFriendBeanList);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052 && this.recoverFriendBeanList != null && this.recoverFriendBeanList.size() > 0) {
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i = 0; i < this.recoverFriendBeanList.size(); i++) {
                CopyFriendBean copyFriendBean = this.recoverFriendBeanList.get(i);
                if (copyFriendBean.checked) {
                    linkedHashSet.add(copyFriendBean);
                }
            }
            if (linkedHashSet.size() > 0) {
                startCheck("45", true, new BaseActivity.OnStartCheckListener() {
                    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.wx.assistants.activity.CopyFriendRecoverActivity] */
                    public void checkEnd() {
                        DialogUIUtils.dialogInput(CopyFriendRecoverActivity.this, "设置验证信息", new AlertEditDefaultDialog.OnEditTextListener() {
                            public void result(String str) {
                                OperationParameterModel operationParameterModel = new OperationParameterModel();
                                operationParameterModel.setTaskNum("45");
                                operationParameterModel.setCopyFriends(linkedHashSet);
                                operationParameterModel.setSayContent(str);
                                operationParameterModel.setStartIndex(1);
                                operationParameterModel.setMaxOperaNum(5000);
                                operationParameterModel.setBreakAdd(0);
                                operationParameterModel.setAutoRemarkContactsName(true);
                                operationParameterModel.setSpaceTime(0);
                                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                                CopyFriendRecoverActivity.this.startWX(operationParameterModel);
                            }
                        });
                    }
                });
            }
        }
    }

    private class MyAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        private MyAdapter() {
        }

        public int getCount() {
            return CopyFriendRecoverActivity.this.recoverFriendBeanList.size();
        }

        public Object getItem(int i) {
            return CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i);
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [android.content.Context, com.wx.assistants.activity.CopyFriendRecoverActivity] */
        @SuppressLint({"NewApi"})
        public View getView(int i, View view, ViewGroup viewGroup) {
            CopyFriendBean copyFriendBean = (CopyFriendBean) CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i);
            ViewHold viewHold = new ViewHold();
            if (view == null) {
                view = View.inflate(CopyFriendRecoverActivity.this, 2131427687, (ViewGroup) null);
                TextView unused = viewHold.wxNum = (TextView) view.findViewById(2131297691);
                TextView unused2 = viewHold.wxNickName = (TextView) view.findViewById(2131297690);
                TextView unused3 = viewHold.deleteTime = (TextView) view.findViewById(2131296582);
                TextView unused4 = viewHold.isAdded = (TextView) view.findViewById(2131296828);
                CheckBox unused5 = viewHold.ck = (CheckBox) view.findViewById(2131296501);
                TextView unused6 = viewHold.deleteTimeLeft = (TextView) view.findViewById(2131296583);
                view.setTag(viewHold);
            } else {
                viewHold = (ViewHold) view.getTag();
            }
            viewHold.wxNum.setText(copyFriendBean.getWxNum());
            viewHold.wxNickName.setText(copyFriendBean.getWxNickName());
            viewHold.deleteTimeLeft.setText("微信备注:");
            viewHold.deleteTime.setText(copyFriendBean.getWxRemarkName());
            viewHold.ck.setChecked(((CopyFriendBean) CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked);
            if (copyFriendBean.getIsAdded()) {
                viewHold.isAdded.setVisibility(0);
            } else {
                viewHold.isAdded.setVisibility(8);
            }
            return view;
        }

        class ViewHold {
            /* access modifiers changed from: private */
            public CheckBox ck;
            /* access modifiers changed from: private */
            public TextView deleteTime;
            /* access modifiers changed from: private */
            public TextView deleteTimeLeft;
            /* access modifiers changed from: private */
            public TextView isAdded;
            /* access modifiers changed from: private */
            public TextView wxNickName;
            /* access modifiers changed from: private */
            public TextView wxNum;

            ViewHold() {
            }
        }
    }

    @Subscribe
    public void onEventMainThread(final FileBeanCopyEvent fileBeanCopyEvent) {
        showLoadingDialog("加载数据");
        new Thread(new Runnable() {
            public void run() {
                FileBean selectedBean = fileBeanCopyEvent.getSelectedBean();
                if (selectedBean != null) {
                    String filePath = selectedBean.getFilePath();
                    PrintStream printStream = System.out;
                    printStream.println("WS_BABY_FILE_PATH." + filePath);
                    try {
                        ArrayList arrayList = (ArrayList) new Gson().fromJson(FileUtils.readRootFile(filePath), new TypeToken<ArrayList<CopyFriendBean>>() {
                        }.getType());
                        if (arrayList != null && arrayList.size() > 0) {
                            CopyFriendRecoverActivity.this.recoverFriendBeans.clear();
                            int unused = CopyFriendRecoverActivity.this.currentPage = 0;
                            CopyFriendRecoverActivity.this.recoverFriendBeanList.clear();
                            CopyFriendRecoverActivity.this.recoverFriendBeanList.addAll(arrayList);
                            SQLiteUtils.getInstance().deleteAllCopyFriends();
                            for (int i = 0; i < CopyFriendRecoverActivity.this.recoverFriendBeanList.size(); i++) {
                                SQLiteUtils.getInstance().addCopyFriend((CopyFriendBean) CopyFriendRecoverActivity.this.recoverFriendBeanList.get(i));
                            }
                            CopyFriendRecoverActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    CopyFriendRecoverActivity.this.selectPeopleNum.setText("0");
                                    MyAdapter unused = CopyFriendRecoverActivity.this.myAdapter = new MyAdapter();
                                    CopyFriendRecoverActivity.this.contact_list.setAdapter(CopyFriendRecoverActivity.this.myAdapter);
                                    CopyFriendRecoverActivity.this.friendsMoveBottomLayout.setData(CopyFriendRecoverActivity.this.recoverFriendBeanList);
                                    CopyFriendRecoverActivity.this.dismissLoadingDialog();
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        CopyFriendRecoverActivity.this.dismissLoadingDialog();
                    }
                }
            }
        }).start();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
