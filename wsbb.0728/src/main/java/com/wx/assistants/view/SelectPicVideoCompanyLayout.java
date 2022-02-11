package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.wx.assistants.activity.PlusImageActivity;
import com.wx.assistants.adapter.PublicProductGridAdapter;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.utils.AppManager;
import com.wx.assistants.utils.SPUtils;
import com.wx.assistants.view.CustomRadioSwitchLayout;
import com.wx.assistants.view.CustomRadioTwoLayout;
import com.wx.assistants.view.SendOrderLayout;
import java.util.ArrayList;
import java.util.List;

public class SelectPicVideoCompanyLayout extends LinearLayout {
    private BaseActivity activity;
    @BindView(2131296569)
    CustomRadioSwitchLayout customRadioSwitchLayout;
    /* access modifiers changed from: private */
    public boolean isFromFriend = true;
    /* access modifiers changed from: private */
    public int isOriginPic = 1;
    /* access modifiers changed from: private */
    public OnPicVideoSelectListener listener;
    private Context mContext;
    private PublicProductGridAdapter mGridViewAdapter;
    /* access modifiers changed from: private */
    public ArrayList<String> mPicList = new ArrayList<>();
    @BindView(2131297132)
    CustomRadioTwoLayout picVideoRadioLayout;
    @BindView(2131297160)
    CustomGridView productGridView;
    /* access modifiers changed from: private */
    public int selectPicVideoType = 0;
    /* access modifiers changed from: private */
    public int sendOrder = 0;
    @BindView(2131297335)
    SendOrderLayout sendOrderLayout;

    public interface OnPicVideoSelectListener {
        void mode(int i, int i2, int i3, ArrayList<String> arrayList);
    }

    public SelectPicVideoCompanyLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public SelectPicVideoCompanyLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public SelectPicVideoCompanyLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    private void initGridView() {
        this.mGridViewAdapter = new PublicProductGridAdapter(this.mContext, this.mPicList, this.selectPicVideoType == 0 ? 6 : 1, this.selectPicVideoType);
        this.productGridView.setAdapter(this.mGridViewAdapter);
        this.productGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != adapterView.getChildCount() - 1) {
                    SelectPicVideoCompanyLayout.this.viewPluImg(i);
                } else if (SelectPicVideoCompanyLayout.this.mPicList.size() == 6) {
                    SelectPicVideoCompanyLayout.this.viewPluImg(i);
                } else {
                    SelectPicVideoCompanyLayout.this.selectPic(6 - SelectPicVideoCompanyLayout.this.mPicList.size());
                }
            }
        });
        this.mGridViewAdapter.setDeleteUrlListener(new PublicProductGridAdapter.OnDeleteUrlListener() {
            public void delete(String str) {
                SelectPicVideoCompanyLayout.this.mPicList.remove(str);
                SelectPicVideoCompanyLayout.this.updateGrid();
            }
        });
        this.mGridViewAdapter.setDeleteListener(new PublicProductGridAdapter.OnDeleteListener() {
            public void delete(int i) {
                SelectPicVideoCompanyLayout.this.mPicList.remove(i);
                SelectPicVideoCompanyLayout.this.updateGrid();
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshAdapter(List<String> list) {
        for (String add : list) {
            this.mPicList.add(add);
            this.mGridViewAdapter.notifyDataSetChanged();
        }
        this.listener.mode(this.isOriginPic, this.sendOrder, this.selectPicVideoType, this.mPicList);
        StringBuffer stringBuffer = new StringBuffer();
        if (this.mPicList != null && this.mPicList.size() > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.mPicList.size()) {
                    break;
                }
                stringBuffer.append(this.mPicList.get(i2));
                if (i2 != this.mPicList.size() - 1) {
                    stringBuffer.append(";");
                }
                i = i2 + 1;
            }
            if (this.isFromFriend) {
                Context conText = MyApplication.getConText();
                SPUtils.put(conText, "company_text_img_friend_path" + this.selectPicVideoType, stringBuffer);
                return;
            }
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "company_text_img_group_path" + this.selectPicVideoType, stringBuffer);
        } else if (this.isFromFriend) {
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "company_text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            Context conText4 = MyApplication.getConText();
            SPUtils.put(conText4, "company_text_img_group_path" + this.selectPicVideoType, "");
        }
    }

    /* access modifiers changed from: private */
    public void selectPic(int i) {
        if (this.activity == null) {
            this.activity = (BaseActivity) AppManager.getAppManager().currentActivity();
        }
        if (this.selectPicVideoType == 0) {
            this.activity.selectMultiPicAlbum(i, new BaseActivity.OnAlbumResultListener() {
                public void result(ArrayList<String> arrayList) {
                    SelectPicVideoCompanyLayout.this.refreshAdapter(arrayList);
                }
            });
        } else if (this.selectPicVideoType == 1) {
            this.activity.selectSingleVideoAlbum(new BaseActivity.OnAlbumResultListener() {
                public void result(ArrayList<String> arrayList) {
                    SelectPicVideoCompanyLayout.this.refreshAdapter(arrayList);
                }
            });
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.wx.assistants.base.BaseActivity, android.content.Context] */
    /* access modifiers changed from: private */
    public void viewPluImg(int i) {
        if (this.activity == null) {
            this.activity = (BaseActivity) AppManager.getAppManager().currentActivity();
        }
        Intent intent = new Intent(this.activity, PlusImageActivity.class);
        intent.putStringArrayListExtra("img_list", this.mPicList);
        intent.putExtra("position", i);
        this.activity.startActivityForResult(intent, 10);
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427771, this, true);
        ButterKnife.bind(this);
    }

    public void initGridData() {
        String str;
        if (this.isFromFriend) {
            str = (String) SPUtils.get(MyApplication.getConText(), "company_text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            str = (String) SPUtils.get(MyApplication.getConText(), "company_text_img_group_path" + this.selectPicVideoType, "");
        }
        if (str == null || "".equals(str)) {
            this.mPicList.clear();
            initGridView();
            return;
        }
        this.mPicList.clear();
        if (str.contains(";")) {
            String[] split = str.split(";");
            for (String add : split) {
                this.mPicList.add(add);
            }
        } else {
            this.mPicList.add(str);
        }
        initGridView();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.customRadioSwitchLayout.setText("发送原图片");
        this.customRadioSwitchLayout.setDefaultSelect(1);
        this.customRadioSwitchLayout.setOnModelSelectListener(new CustomRadioSwitchLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = SelectPicVideoCompanyLayout.this.isOriginPic = i;
                if (SelectPicVideoCompanyLayout.this.listener != null) {
                    SelectPicVideoCompanyLayout.this.listener.mode(SelectPicVideoCompanyLayout.this.isOriginPic, SelectPicVideoCompanyLayout.this.sendOrder, SelectPicVideoCompanyLayout.this.selectPicVideoType, SelectPicVideoCompanyLayout.this.mPicList);
                }
            }
        });
        this.sendOrderLayout.setLeftText("先发送图片");
        this.sendOrderLayout.setOnSendOrderListener(new SendOrderLayout.OnSendOrderListener() {
            public void sendOrder(int i) {
                int unused = SelectPicVideoCompanyLayout.this.sendOrder = i;
                if (SelectPicVideoCompanyLayout.this.listener != null) {
                    SelectPicVideoCompanyLayout.this.listener.mode(SelectPicVideoCompanyLayout.this.isOriginPic, SelectPicVideoCompanyLayout.this.sendOrder, SelectPicVideoCompanyLayout.this.selectPicVideoType, SelectPicVideoCompanyLayout.this.mPicList);
                }
                if (SelectPicVideoCompanyLayout.this.isFromFriend) {
                    SPUtils.put(MyApplication.getConText(), "company_group_send_text_img_friend_order", Integer.valueOf(SelectPicVideoCompanyLayout.this.sendOrder));
                } else {
                    SPUtils.put(MyApplication.getConText(), "company_group_send_text_img_group_order", Integer.valueOf(SelectPicVideoCompanyLayout.this.sendOrder));
                }
            }
        });
        this.picVideoRadioLayout.setDefaultSelect(0);
        this.picVideoRadioLayout.setText("图片视频", "图片", 35, "视频", 35);
        this.picVideoRadioLayout.setOnModelSelectListener(new CustomRadioTwoLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = SelectPicVideoCompanyLayout.this.selectPicVideoType = i;
                SelectPicVideoCompanyLayout.this.listener.mode(SelectPicVideoCompanyLayout.this.isOriginPic, SelectPicVideoCompanyLayout.this.sendOrder, SelectPicVideoCompanyLayout.this.selectPicVideoType, SelectPicVideoCompanyLayout.this.mPicList);
                if (SelectPicVideoCompanyLayout.this.selectPicVideoType == 0) {
                    SelectPicVideoCompanyLayout.this.customRadioSwitchLayout.setText("发送原图片");
                    SelectPicVideoCompanyLayout.this.sendOrderLayout.setLeftText("先发送图片");
                } else if (SelectPicVideoCompanyLayout.this.selectPicVideoType == 1) {
                    SelectPicVideoCompanyLayout.this.customRadioSwitchLayout.setText("发送原视频");
                    SelectPicVideoCompanyLayout.this.sendOrderLayout.setLeftText("先发送视频");
                }
                SelectPicVideoCompanyLayout.this.initGridData();
            }
        });
    }

    public void setFromFriend(BaseActivity baseActivity, boolean z) {
        this.activity = baseActivity;
        this.isFromFriend = z;
        if (z) {
            this.sendOrder = ((Integer) SPUtils.get(MyApplication.getConText(), "company_group_send_text_img_friend_order", 0)).intValue();
            this.sendOrderLayout.setSendOrder(this.sendOrder);
        } else {
            this.sendOrder = ((Integer) SPUtils.get(MyApplication.getConText(), "company_group_send_text_img_group_order", 0)).intValue();
            this.sendOrderLayout.setSendOrder(this.sendOrder);
        }
        initGridData();
    }

    public void setOnActivityResult(Intent intent) {
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("img_list");
        this.mPicList.clear();
        this.mPicList.addAll(stringArrayListExtra);
        this.mGridViewAdapter.notifyDataSetChanged();
        this.listener.mode(this.isOriginPic, this.sendOrder, this.selectPicVideoType, this.mPicList);
        StringBuffer stringBuffer = new StringBuffer();
        if (this.mPicList != null && this.mPicList.size() > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.mPicList.size()) {
                    break;
                }
                stringBuffer.append(this.mPicList.get(i2));
                if (i2 != this.mPicList.size() - 1) {
                    stringBuffer.append(";");
                }
                i = i2 + 1;
            }
            if (this.isFromFriend) {
                Context conText = MyApplication.getConText();
                SPUtils.put(conText, "company_text_img_friend_path" + this.selectPicVideoType, stringBuffer);
                return;
            }
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "company_text_img_group_path" + this.selectPicVideoType, stringBuffer);
        } else if (this.isFromFriend) {
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "company_text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            Context conText4 = MyApplication.getConText();
            SPUtils.put(conText4, "company_text_img_group_path" + this.selectPicVideoType, "");
        }
    }

    public void setOnPicVideoSelectListener(OnPicVideoSelectListener onPicVideoSelectListener) {
        if (onPicVideoSelectListener != null) {
            this.listener = onPicVideoSelectListener;
        }
        if (this.listener != null) {
            this.listener.mode(this.isOriginPic, this.sendOrder, this.selectPicVideoType, this.mPicList);
        }
    }

    public void setSendMsgType(BaseActivity baseActivity, int i) {
        this.activity = baseActivity;
        if (i == 0) {
            this.sendOrderLayout.setVisibility(0);
        } else if (i == 1) {
            this.sendOrderLayout.setVisibility(8);
        } else if (i == 2) {
            this.sendOrderLayout.setVisibility(0);
        }
    }

    public void updateGrid() {
        this.mGridViewAdapter.notifyDataSetChanged();
        this.listener.mode(this.isOriginPic, this.sendOrder, this.selectPicVideoType, this.mPicList);
        StringBuffer stringBuffer = new StringBuffer();
        if (this.mPicList != null && this.mPicList.size() > 0) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.mPicList.size()) {
                    break;
                }
                stringBuffer.append(this.mPicList.get(i2));
                if (i2 != this.mPicList.size() - 1) {
                    stringBuffer.append(";");
                }
                i = i2 + 1;
            }
            if (this.isFromFriend) {
                Context conText = MyApplication.getConText();
                SPUtils.put(conText, "company_text_img_friend_path" + this.selectPicVideoType, stringBuffer);
                return;
            }
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "company_text_img_group_path" + this.selectPicVideoType, stringBuffer);
        } else if (this.isFromFriend) {
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "company_text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            Context conText4 = MyApplication.getConText();
            SPUtils.put(conText4, "company_text_img_group_path" + this.selectPicVideoType, "");
        }
    }
}
