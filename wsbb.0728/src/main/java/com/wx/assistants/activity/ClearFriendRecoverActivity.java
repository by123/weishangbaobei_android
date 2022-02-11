package com.wx.assistants.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.stub.StubApp;
import com.wx.assistants.Enity.RecoverFriendBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ClearFriendRecoverActivity extends BaseActivity implements View.OnClickListener {
    private ListView contact_list;
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
    public List<RecoverFriendBean> recoverFriendBeanList;
    /* access modifiers changed from: private */
    public ArrayList<RecoverFriendBean> recoverFriendBeans = new ArrayList<>();
    /* access modifiers changed from: private */
    public TextView selectPeopleNum;

    private class MyAdapter extends BaseAdapter {

        class ViewHold {
            /* access modifiers changed from: private */
            public CheckBox ck;
            /* access modifiers changed from: private */
            public TextView deleteTime;
            /* access modifiers changed from: private */
            public TextView isAdded;
            /* access modifiers changed from: private */
            public TextView wxNickName;
            /* access modifiers changed from: private */
            public TextView wxNum;

            ViewHold() {
            }
        }

        private MyAdapter() {
        }

        public int getCount() {
            return ClearFriendRecoverActivity.this.recoverFriendBeanList.size();
        }

        public Object getItem(int i) {
            return ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        /* JADX WARNING: type inference failed for: r1v12, types: [android.content.Context, com.wx.assistants.activity.ClearFriendRecoverActivity] */
        @SuppressLint({"NewApi"})
        public View getView(int i, View view, ViewGroup viewGroup) {
            RecoverFriendBean recoverFriendBean = (RecoverFriendBean) ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i);
            ViewHold viewHold = new ViewHold();
            if (view == null) {
                view = View.inflate(ClearFriendRecoverActivity.this, 2131427687, (ViewGroup) null);
                TextView unused = viewHold.wxNum = (TextView) view.findViewById(2131297691);
                TextView unused2 = viewHold.wxNickName = (TextView) view.findViewById(2131297690);
                TextView unused3 = viewHold.deleteTime = (TextView) view.findViewById(2131296582);
                TextView unused4 = viewHold.isAdded = (TextView) view.findViewById(2131296828);
                CheckBox unused5 = viewHold.ck = (CheckBox) view.findViewById(2131296501);
                view.setTag(viewHold);
            } else {
                viewHold = (ViewHold) view.getTag();
            }
            viewHold.wxNum.setText(recoverFriendBean.getWxNum());
            viewHold.wxNickName.setText(recoverFriendBean.getWxNickName());
            viewHold.deleteTime.setText(recoverFriendBean.getDeleteTime());
            viewHold.ck.setChecked(((RecoverFriendBean) ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked);
            if (recoverFriendBean.isAdded()) {
                viewHold.isAdded.setVisibility(0);
            } else {
                viewHold.isAdded.setVisibility(8);
            }
            return view;
        }
    }

    static {
        StubApp.interface11(8568);
    }

    private void initList() {
        this.recoverFriendBeanList = new ArrayList();
        List allFriends = SQLiteUtils.getInstance().getAllFriends();
        if (allFriends != null && allFriends.size() > 0) {
            for (int i = 0; i < allFriends.size(); i++) {
                ((RecoverFriendBean) allFriends.get(i)).setChecked(false);
            }
            this.recoverFriendBeanList.addAll(allFriends);
            this.myAdapter = new MyAdapter();
            this.contact_list.setAdapter(this.myAdapter);
        }
    }

    private void initView() {
        this.navTitle.setText("恢复删除好友");
        this.navRightLayout.setVisibility(0);
        this.navRightText.setText("确认恢复");
        this.contact_list = (ListView) findViewById(2131296530);
        this.selectPeopleNum = (TextView) findViewById(2131297314);
        initList();
        this.navRightLayout.setOnClickListener(this);
        this.contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r0v24, types: [android.content.Context, com.wx.assistants.activity.ClearFriendRecoverActivity] */
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (((RecoverFriendBean) ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked) {
                    ((RecoverFriendBean) ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked = false;
                    ClearFriendRecoverActivity.this.recoverFriendBeans.remove(ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i));
                } else if (ClearFriendRecoverActivity.this.recoverFriendBeans.size() >= 20) {
                    ToastUtils.showToast(ClearFriendRecoverActivity.this, "最多可以选择20人");
                } else {
                    ((RecoverFriendBean) ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i)).checked = true;
                    ClearFriendRecoverActivity.this.recoverFriendBeans.add(ClearFriendRecoverActivity.this.recoverFriendBeanList.get(i));
                }
                TextView access$200 = ClearFriendRecoverActivity.this.selectPeopleNum;
                access$200.setText(ClearFriendRecoverActivity.this.recoverFriendBeans.size() + "");
                ClearFriendRecoverActivity.this.myAdapter.notifyDataSetChanged();
            }
        });
        this.navBack.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131297049) {
            back();
        } else if (id == 2131297052 && this.recoverFriendBeanList != null && this.recoverFriendBeanList.size() > 0) {
            final LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.recoverFriendBeanList.size()) {
                    break;
                }
                RecoverFriendBean recoverFriendBean = this.recoverFriendBeanList.get(i2);
                if (recoverFriendBean.checked) {
                    linkedHashSet.add(recoverFriendBean);
                }
                i = i2 + 1;
            }
            if (linkedHashSet.size() > 0) {
                startCheck("34", true, new BaseActivity.OnStartCheckListener() {
                    public void checkEnd() {
                        OperationParameterModel operationParameterModel = new OperationParameterModel();
                        operationParameterModel.setTaskNum("34");
                        operationParameterModel.setRecoverFriends(linkedHashSet);
                        operationParameterModel.setSayContent("");
                        operationParameterModel.setStartIndex(1);
                        operationParameterModel.setMaxOperaNum(100);
                        operationParameterModel.setBreakAdd(0);
                        operationParameterModel.setAutoRemarkContactsName(true);
                        operationParameterModel.setSpaceTime(0);
                        MyApplication.instance.setOperationParameterModel(operationParameterModel);
                        ClearFriendRecoverActivity.this.startWX(operationParameterModel);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
