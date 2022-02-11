package com.wx.assistants.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.Enity.ContactBean;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.OperationParameterModel;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.PhoneUtil;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends BaseActivity implements View.OnClickListener {
    public static final String LauncherUI = "com.tencent.mm.ui.LauncherUI";
    private Button add_contact;
    /* access modifiers changed from: private */
    public ListView contact_list;
    /* access modifiers changed from: private */
    public MyHandler handler = new MyHandler();
    /* access modifiers changed from: private */
    public MyAdapter myAdapter;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297054)
    TextView navTitle;
    /* access modifiers changed from: private */
    public List<ContactBean> phoneList;
    /* access modifiers changed from: private */
    public String phoneNumber;
    /* access modifiers changed from: private */
    public ArrayList<ContactBean> phones = new ArrayList<>();
    private LinearLayout re_load;
    /* access modifiers changed from: private */
    public TextView selectPeopleNum;

    private class MyAdapter extends BaseAdapter {

        class ViewHold {
            /* access modifiers changed from: private */
            public CheckBox ck;
            /* access modifiers changed from: private */
            public TextView extra;
            /* access modifiers changed from: private */
            public TextView nickName;
            /* access modifiers changed from: private */
            public TextView number;

            ViewHold() {
            }
        }

        private MyAdapter() {
        }

        public int getCount() {
            return ContactActivity.this.phoneList.size();
        }

        public Object getItem(int i) {
            return ContactActivity.this.phoneList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        /* JADX WARNING: type inference failed for: r1v4, types: [android.content.Context, com.wx.assistants.activity.ContactActivity] */
        @SuppressLint({"NewApi"})
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHold viewHold;
            ContactBean contactBean = (ContactBean) ContactActivity.this.phoneList.get(i);
            ViewHold viewHold2 = new ViewHold();
            if (view == null) {
                view = View.inflate(ContactActivity.this, 2131427496, (ViewGroup) null);
                TextView unused = viewHold2.number = (TextView) view.findViewById(2131297080);
                TextView unused2 = viewHold2.nickName = (TextView) view.findViewById(2131297062);
                CheckBox unused3 = viewHold2.ck = (CheckBox) view.findViewById(2131296501);
                TextView unused4 = viewHold2.extra = (TextView) view.findViewById(2131296653);
                view.setTag(viewHold2);
                viewHold = viewHold2;
            } else {
                viewHold = (ViewHold) view.getTag();
            }
            if (contactBean.getNumber().contains("+86")) {
                String unused5 = ContactActivity.this.phoneNumber = contactBean.getNumber().replace("+86", "").trim();
            } else {
                String unused6 = ContactActivity.this.phoneNumber = contactBean.getNumber().trim();
            }
            viewHold.number.setText(ContactActivity.this.phoneNumber);
            viewHold.nickName.setText(contactBean.getNickName());
            viewHold.ck.setChecked(contactBean.checked);
            viewHold.extra.setText(contactBean.getExtra());
            return view;
        }
    }

    class MyHandler extends Handler {
        MyHandler() {
        }

        /* JADX WARNING: type inference failed for: r0v4, types: [android.content.Context, com.wx.assistants.activity.ContactActivity] */
        public void handleMessage(Message message) {
            super.handleMessage(message);
            MyAdapter unused = ContactActivity.this.myAdapter = new MyAdapter();
            ContactActivity.this.contact_list.setAdapter(ContactActivity.this.myAdapter);
            ContactActivity.this.dismissLoadingDialog();
            ToastUtils.showToast(ContactActivity.this, "导入成功");
        }
    }

    static {
        StubApp.interface11(8590);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.wx.assistants.activity.ContactActivity, android.app.Activity] */
    private void getPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            initView();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS") == 0) {
            initView();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_CONTACTS"}, 120);
        }
    }

    /* access modifiers changed from: private */
    public void initList() {
        showLoadingDialog("..正在导入..");
        new Thread(new Runnable() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.wx.assistants.activity.ContactActivity] */
            public void run() {
                List unused = ContactActivity.this.phoneList = new PhoneUtil(ContactActivity.this).getPhone();
                ContactActivity.this.handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void initView() {
        this.navTitle.setText("手机通讯录朋友");
        this.contact_list = (ListView) findViewById(2131296530);
        this.re_load = (LinearLayout) findViewById(2131297217);
        this.add_contact = (Button) findViewById(2131296324);
        this.selectPeopleNum = (TextView) findViewById(2131297314);
        initList();
        this.re_load.setOnClickListener(this);
        this.add_contact.setOnClickListener(this);
        this.contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r0v24, types: [android.content.Context, com.wx.assistants.activity.ContactActivity] */
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (((ContactBean) ContactActivity.this.phoneList.get(i)).checked) {
                    ((ContactBean) ContactActivity.this.phoneList.get(i)).checked = false;
                    ContactActivity.this.phones.remove(ContactActivity.this.phoneList.get(i));
                } else if (ContactActivity.this.phones.size() >= 20) {
                    ToastUtils.showToast(ContactActivity.this, "最多可以选择20人");
                } else {
                    ((ContactBean) ContactActivity.this.phoneList.get(i)).checked = true;
                    ContactActivity.this.phones.add(ContactActivity.this.phoneList.get(i));
                }
                TextView access$200 = ContactActivity.this.selectPeopleNum;
                access$200.setText(ContactActivity.this.phones.size() + "");
                ContactActivity.this.myAdapter.notifyDataSetChanged();
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context] */
    /* JADX WARNING: Multi-variable type inference failed */
    private void openDialog() {
        DialogUIUtils.dialogDefault(this, "提示", "确认要重新导入通讯录吗？", "取消", "确认", new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                ContactActivity.this.initList();
            }
        });
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, com.wx.assistants.activity.ContactActivity] */
    public void onClick(View view) {
        int id = view.getId();
        if (id != 2131296324) {
            if (id == 2131297217) {
                openDialog();
            }
        } else if (this.phoneList != null && this.phoneList.size() > 0) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.phoneList.size()) {
                    break;
                }
                ContactBean contactBean = this.phoneList.get(i2);
                if (contactBean.checked) {
                    arrayList.add(contactBean);
                }
                i = i2 + 1;
            }
            if (arrayList.size() > 0) {
                OperationParameterModel operationParameterModel = new OperationParameterModel();
                operationParameterModel.setTaskNum("26");
                operationParameterModel.setAddressBookPhones(arrayList);
                MyApplication.instance.setOperationParameterModel(operationParameterModel);
                startActivity(new Intent(this, AutoAddContacts2Activity.class));
            }
        }
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    @TargetApi(23)
    public native void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    @OnClick({2131297049})
    public void onViewClicked() {
        back();
    }
}
