package com.wx.assistants.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wx.assistants.Enity.ContactScanBean;
import com.wx.assistants.activity.AutoAddContactCameraAddressActivity;
import com.wx.assistants.activity.AutoAddContactCameraOperationActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.CameraContactEvent;
import com.wx.assistants.utils.DateUtils;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ContactScanUnAddFragment extends Fragment {
    @BindView(2131296324)
    Button add_contact;
    @BindView(2131296530)
    ListView contact_list;
    @BindView(2131296579)
    LinearLayout deleteContact;
    /* access modifiers changed from: private */
    public MyAdapter myAdapter;
    /* access modifiers changed from: private */
    public String phoneNumber;
    /* access modifiers changed from: private */
    public List<ContactScanBean> phones = new ArrayList();
    @BindView(2131297228)
    SmartRefreshLayout refreshLayout;
    /* access modifiers changed from: private */
    public List<ContactScanBean> scanData = new ArrayList();
    @BindView(2131297314)
    TextView selectPeopleNum;
    private Unbinder unbinder;

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
            return ContactScanUnAddFragment.this.scanData.size();
        }

        public Object getItem(int i) {
            return ContactScanUnAddFragment.this.scanData.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        @SuppressLint({"NewApi"})
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHold viewHold;
            ContactScanBean contactScanBean = (ContactScanBean) ContactScanUnAddFragment.this.scanData.get(i);
            ViewHold viewHold2 = new ViewHold();
            if (view == null) {
                view = View.inflate(ContactScanUnAddFragment.this.getActivity(), 2131427497, (ViewGroup) null);
                TextView unused = viewHold2.number = (TextView) view.findViewById(2131297080);
                TextView unused2 = viewHold2.nickName = (TextView) view.findViewById(2131297062);
                CheckBox unused3 = viewHold2.ck = (CheckBox) view.findViewById(2131296501);
                TextView unused4 = viewHold2.extra = (TextView) view.findViewById(2131296653);
                view.setTag(viewHold2);
                viewHold = viewHold2;
            } else {
                viewHold = (ViewHold) view.getTag();
            }
            if (contactScanBean.getNumber().contains("+86")) {
                String unused5 = ContactScanUnAddFragment.this.phoneNumber = contactScanBean.getNumber().replace("+86", "").trim();
            } else {
                String unused6 = ContactScanUnAddFragment.this.phoneNumber = contactScanBean.getNumber().trim();
            }
            viewHold.number.setText(ContactScanUnAddFragment.this.phoneNumber);
            try {
                viewHold.nickName.setText(DateUtils.convertDate2String(new Date(contactScanBean.getCreateTime()), "MM/dd hh:mm"));
            } catch (Exception e) {
            }
            viewHold.ck.setChecked(contactScanBean.checked);
            viewHold.extra.setText(contactScanBean.getExtra());
            return view;
        }
    }

    public static ContactScanUnAddFragment newInstance() {
        Bundle bundle = new Bundle();
        ContactScanUnAddFragment contactScanUnAddFragment = new ContactScanUnAddFragment();
        contactScanUnAddFragment.setArguments(bundle);
        return contactScanUnAddFragment;
    }

    public void initData() {
        try {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ContactScanUnAddFragment.this.scanData.clear();
                    ContactScanUnAddFragment.this.scanData.addAll(SQLiteUtils.getInstance().getAllContactScanFromUnAdd());
                    if (!(ContactScanUnAddFragment.this.scanData == null || ContactScanUnAddFragment.this.scanData.size() <= 0 || ContactScanUnAddFragment.this.myAdapter == null)) {
                        ContactScanUnAddFragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                ContactScanUnAddFragment.this.myAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    if (ContactScanUnAddFragment.this.refreshLayout != null) {
                        ContactScanUnAddFragment.this.refreshLayout.finishRefresh();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    public void initView() {
        this.myAdapter = new MyAdapter();
        this.contact_list.setAdapter(this.myAdapter);
        this.contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (((ContactScanBean) ContactScanUnAddFragment.this.scanData.get(i)).checked) {
                    ((ContactScanBean) ContactScanUnAddFragment.this.scanData.get(i)).checked = false;
                    ContactScanUnAddFragment.this.phones.remove(ContactScanUnAddFragment.this.scanData.get(i));
                } else if (ContactScanUnAddFragment.this.phones.size() >= 20) {
                    ToastUtils.showToast(ContactScanUnAddFragment.this.getActivity(), "最多可以选择20人");
                } else {
                    ((ContactScanBean) ContactScanUnAddFragment.this.scanData.get(i)).checked = true;
                    ContactScanUnAddFragment.this.phones.add(ContactScanUnAddFragment.this.scanData.get(i));
                }
                ContactScanUnAddFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        TextView textView = ContactScanUnAddFragment.this.selectPeopleNum;
                        textView.setText(ContactScanUnAddFragment.this.phones.size() + "");
                        ContactScanUnAddFragment.this.myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                ContactScanUnAddFragment.this.initData();
            }
        });
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427387, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        EventBus.getDefault().register(this);
        initView();
        initData();
        return inflate;
    }

    public void onDestroy() {
        ContactScanUnAddFragment.super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onDestroyView() {
        ContactScanUnAddFragment.super.onDestroyView();
        this.unbinder.unbind();
    }

    @Subscribe
    public void onEventMainThread(CameraContactEvent cameraContactEvent) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                ContactScanUnAddFragment.this.phones.clear();
                ContactScanUnAddFragment.this.selectPeopleNum.setText("0");
                ContactScanUnAddFragment.this.initData();
            }
        });
    }

    @OnClick({2131296324, 2131296579})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != 2131296324) {
            if (id == 2131296579) {
                if (this.phones == null || this.phones.size() == 0) {
                    ToastUtils.showToast(getActivity(), "请选择要删除的手机号");
                } else {
                    DialogUIUtils.dialogDefault(getActivity(), "删除确认", "您确定要删除吗？", "取消", "确定", new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    }, new View.OnClickListener() {
                        public void onClick(View view) {
                            if (ContactScanUnAddFragment.this.phones != null && ContactScanUnAddFragment.this.phones.size() > 0) {
                                int i = 0;
                                while (true) {
                                    int i2 = i;
                                    if (i2 < ContactScanUnAddFragment.this.phones.size()) {
                                        ContactScanBean contactScanBean = (ContactScanBean) ContactScanUnAddFragment.this.phones.get(i2);
                                        ContactScanUnAddFragment.this.scanData.remove(contactScanBean);
                                        SQLiteUtils.getInstance().deleteContactScan(contactScanBean);
                                        i = i2 + 1;
                                    } else {
                                        ContactScanUnAddFragment.this.myAdapter.notifyDataSetChanged();
                                        ToastUtils.showToast(MyApplication.getConText(), "删除成功");
                                        return;
                                    }
                                }
                            }
                        }
                    });
                }
            }
        } else if (this.phones == null || this.phones.size() == 0) {
            ToastUtils.showToast(getActivity(), "请选择要添加的手机号");
        } else if (this.phones.size() > 0) {
            AutoAddContactCameraAddressActivity.phones = this.phones;
            startActivity(new Intent(getContext(), AutoAddContactCameraOperationActivity.class));
        }
    }
}
