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

public class ContactScanAddedFragment extends Fragment {
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
    public ArrayList<ContactScanBean> phones = new ArrayList<>();
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
            return ContactScanAddedFragment.this.scanData.size();
        }

        public Object getItem(int i) {
            return ContactScanAddedFragment.this.scanData.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        @SuppressLint({"NewApi"})
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHold viewHold;
            ContactScanBean contactScanBean = (ContactScanBean) ContactScanAddedFragment.this.scanData.get(i);
            ViewHold viewHold2 = new ViewHold();
            if (view == null) {
                view = View.inflate(ContactScanAddedFragment.this.getActivity(), 2131427497, (ViewGroup) null);
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
                String unused5 = ContactScanAddedFragment.this.phoneNumber = contactScanBean.getNumber().replace("+86", "").trim();
            } else {
                String unused6 = ContactScanAddedFragment.this.phoneNumber = contactScanBean.getNumber().trim();
            }
            viewHold.number.setText(ContactScanAddedFragment.this.phoneNumber);
            try {
                viewHold.nickName.setText(DateUtils.convertDate2String(new Date(contactScanBean.getCreateTime()), "MM/dd hh:mm"));
            } catch (Exception e) {
            }
            viewHold.ck.setChecked(contactScanBean.checked);
            viewHold.extra.setText(contactScanBean.getExtra());
            return view;
        }
    }

    public static ContactScanAddedFragment newInstance() {
        Bundle bundle = new Bundle();
        ContactScanAddedFragment contactScanAddedFragment = new ContactScanAddedFragment();
        contactScanAddedFragment.setArguments(bundle);
        return contactScanAddedFragment;
    }

    public void initData() {
        try {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ContactScanAddedFragment.this.scanData.clear();
                    ContactScanAddedFragment.this.scanData.addAll(SQLiteUtils.getInstance().getAllContactScanFromAdded());
                    if (!(ContactScanAddedFragment.this.scanData == null || ContactScanAddedFragment.this.scanData.size() <= 0 || ContactScanAddedFragment.this.myAdapter == null)) {
                        ContactScanAddedFragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                ContactScanAddedFragment.this.myAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    if (ContactScanAddedFragment.this.refreshLayout != null) {
                        ContactScanAddedFragment.this.refreshLayout.finishRefresh();
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
                if (((ContactScanBean) ContactScanAddedFragment.this.scanData.get(i)).checked) {
                    ((ContactScanBean) ContactScanAddedFragment.this.scanData.get(i)).checked = false;
                    ContactScanAddedFragment.this.phones.remove(ContactScanAddedFragment.this.scanData.get(i));
                } else if (ContactScanAddedFragment.this.phones.size() >= 20) {
                    ToastUtils.showToast(ContactScanAddedFragment.this.getActivity(), "最多可以选择20人");
                } else {
                    ((ContactScanBean) ContactScanAddedFragment.this.scanData.get(i)).checked = true;
                    ContactScanAddedFragment.this.phones.add(ContactScanAddedFragment.this.scanData.get(i));
                }
                ContactScanAddedFragment.this.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        TextView textView = ContactScanAddedFragment.this.selectPeopleNum;
                        textView.setText(ContactScanAddedFragment.this.phones.size() + "");
                        ContactScanAddedFragment.this.myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        this.refreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                ContactScanAddedFragment.this.initData();
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
        ContactScanAddedFragment.super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onDestroyView() {
        ContactScanAddedFragment.super.onDestroyView();
        this.unbinder.unbind();
    }

    @Subscribe
    public void onEventMainThread(CameraContactEvent cameraContactEvent) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                ContactScanAddedFragment.this.phones.clear();
                ContactScanAddedFragment.this.selectPeopleNum.setText("0");
                ContactScanAddedFragment.this.initData();
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
                            if (ContactScanAddedFragment.this.phones != null && ContactScanAddedFragment.this.phones.size() > 0) {
                                int i = 0;
                                while (true) {
                                    int i2 = i;
                                    if (i2 < ContactScanAddedFragment.this.phones.size()) {
                                        ContactScanBean contactScanBean = (ContactScanBean) ContactScanAddedFragment.this.phones.get(i2);
                                        ContactScanAddedFragment.this.scanData.remove(contactScanBean);
                                        SQLiteUtils.getInstance().deleteContactScan(contactScanBean);
                                        i = i2 + 1;
                                    } else {
                                        ContactScanAddedFragment.this.getActivity().runOnUiThread(new Runnable() {
                                            public void run() {
                                                ContactScanAddedFragment.this.myAdapter.notifyDataSetChanged();
                                            }
                                        });
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
