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
import com.wx.assistants.view.CustomRadioThreeLayout;
import com.wx.assistants.view.SendOrderLayout;
import java.util.ArrayList;
import java.util.List;

public class SelectPicVideoLayout extends LinearLayout {
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
    CustomRadioThreeLayout picVideoRadioLayout;
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

    public SelectPicVideoLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public SelectPicVideoLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public SelectPicVideoLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    private void initGridView() {
        this.mGridViewAdapter = new PublicProductGridAdapter(this.mContext, this.mPicList, 6, this.selectPicVideoType);
        this.productGridView.setAdapter(this.mGridViewAdapter);
        this.productGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != adapterView.getChildCount() - 1) {
                    SelectPicVideoLayout.this.viewPluImg(i);
                } else if (SelectPicVideoLayout.this.mPicList.size() == 6) {
                    SelectPicVideoLayout.this.viewPluImg(i);
                } else {
                    SelectPicVideoLayout.this.selectPic(6 - SelectPicVideoLayout.this.mPicList.size());
                }
            }
        });
        this.mGridViewAdapter.setDeleteUrlListener(new PublicProductGridAdapter.OnDeleteUrlListener() {
            public void delete(String str) {
                if (SelectPicVideoLayout.this.mPicList != null) {
                    SelectPicVideoLayout.this.mPicList.remove(str);
                    SelectPicVideoLayout.this.updateGrid();
                }
            }
        });
        this.mGridViewAdapter.setDeleteListener(new PublicProductGridAdapter.OnDeleteListener() {
            public void delete(int i) {
                if (SelectPicVideoLayout.this.mPicList != null && SelectPicVideoLayout.this.mPicList.size() > 0 && SelectPicVideoLayout.this.mPicList.size() > i) {
                    SelectPicVideoLayout.this.mPicList.remove(i);
                    SelectPicVideoLayout.this.updateGrid();
                }
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
                SPUtils.put(conText, "text_img_friend_path" + this.selectPicVideoType, stringBuffer);
                return;
            }
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "text_img_group_path" + this.selectPicVideoType, stringBuffer);
        } else if (this.isFromFriend) {
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            Context conText4 = MyApplication.getConText();
            SPUtils.put(conText4, "text_img_group_path" + this.selectPicVideoType, "");
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
                    SelectPicVideoLayout.this.refreshAdapter(arrayList);
                }
            });
        } else if (this.selectPicVideoType == 1) {
            this.activity.selectMultiVideoAlbum(i, new BaseActivity.OnAlbumResultListener() {
                public void result(ArrayList<String> arrayList) {
                    SelectPicVideoLayout.this.refreshAdapter(arrayList);
                }
            });
        } else {
            this.activity.selectMultiAlbum(i, new BaseActivity.OnAlbumResultListener() {
                public void result(ArrayList<String> arrayList) {
                    SelectPicVideoLayout.this.refreshAdapter(arrayList);
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
        if (this.activity != null) {
            Intent intent = new Intent(this.activity, PlusImageActivity.class);
            intent.putStringArrayListExtra("img_list", this.mPicList);
            intent.putExtra("position", i);
            this.activity.startActivityForResult(intent, 10);
        }
    }

    public void initCirculateNumView(Context context) {
        LayoutInflater.from(context).inflate(2131427772, this, true);
        ButterKnife.bind(this);
    }

    public void initGridData() {
        String str;
        if (this.isFromFriend) {
            str = (String) SPUtils.get(MyApplication.getConText(), "text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            str = (String) SPUtils.get(MyApplication.getConText(), "text_img_group_path" + this.selectPicVideoType, "");
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
                int unused = SelectPicVideoLayout.this.isOriginPic = i;
                if (SelectPicVideoLayout.this.listener != null) {
                    SelectPicVideoLayout.this.listener.mode(SelectPicVideoLayout.this.isOriginPic, SelectPicVideoLayout.this.sendOrder, SelectPicVideoLayout.this.selectPicVideoType, SelectPicVideoLayout.this.mPicList);
                }
            }
        });
        this.sendOrderLayout.setLeftText("先发送图片");
        this.sendOrderLayout.setOnSendOrderListener(new SendOrderLayout.OnSendOrderListener() {
            public void sendOrder(int i) {
                int unused = SelectPicVideoLayout.this.sendOrder = i;
                if (SelectPicVideoLayout.this.listener != null) {
                    SelectPicVideoLayout.this.listener.mode(SelectPicVideoLayout.this.isOriginPic, SelectPicVideoLayout.this.sendOrder, SelectPicVideoLayout.this.selectPicVideoType, SelectPicVideoLayout.this.mPicList);
                }
                if (SelectPicVideoLayout.this.isFromFriend) {
                    SPUtils.put(MyApplication.getConText(), "group_send_text_img_friend_order", Integer.valueOf(SelectPicVideoLayout.this.sendOrder));
                } else {
                    SPUtils.put(MyApplication.getConText(), "group_send_text_img_group_order", Integer.valueOf(SelectPicVideoLayout.this.sendOrder));
                }
            }
        });
        this.picVideoRadioLayout.setDefaultSelect(0);
        this.picVideoRadioLayout.setText("图片视频", "图片", 35, "视频", 35, "图视频并发", 75);
        this.picVideoRadioLayout.setOnModelSelectListener(new CustomRadioThreeLayout.OnModelSelectListener() {
            public void mode(int i) {
                int unused = SelectPicVideoLayout.this.selectPicVideoType = i;
                SelectPicVideoLayout.this.listener.mode(SelectPicVideoLayout.this.isOriginPic, SelectPicVideoLayout.this.sendOrder, SelectPicVideoLayout.this.selectPicVideoType, SelectPicVideoLayout.this.mPicList);
                if (SelectPicVideoLayout.this.selectPicVideoType == 0) {
                    SelectPicVideoLayout.this.customRadioSwitchLayout.setText("发送原图片");
                    SelectPicVideoLayout.this.sendOrderLayout.setLeftText("先发送图片");
                } else if (SelectPicVideoLayout.this.selectPicVideoType == 1) {
                    SelectPicVideoLayout.this.customRadioSwitchLayout.setText("发送原视频");
                    SelectPicVideoLayout.this.sendOrderLayout.setLeftText("先发送视频");
                } else {
                    SelectPicVideoLayout.this.customRadioSwitchLayout.setText("发送原图片/视频");
                    SelectPicVideoLayout.this.sendOrderLayout.setLeftText("先发送图片/视频");
                }
                SelectPicVideoLayout.this.initGridData();
            }
        });
    }

    public void setFromFriend(BaseActivity baseActivity, boolean z) {
        this.activity = baseActivity;
        this.isFromFriend = z;
        if (z) {
            this.sendOrder = ((Integer) SPUtils.get(MyApplication.getConText(), "group_send_text_img_friend_order", 0)).intValue();
            this.sendOrderLayout.setSendOrder(this.sendOrder);
        } else {
            this.sendOrder = ((Integer) SPUtils.get(MyApplication.getConText(), "group_send_text_img_group_order", 0)).intValue();
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
                SPUtils.put(conText, "text_img_friend_path" + this.selectPicVideoType, stringBuffer);
                return;
            }
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "text_img_group_path" + this.selectPicVideoType, stringBuffer);
        } else if (this.isFromFriend) {
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            Context conText4 = MyApplication.getConText();
            SPUtils.put(conText4, "text_img_group_path" + this.selectPicVideoType, "");
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
                SPUtils.put(conText, "text_img_friend_path" + this.selectPicVideoType, stringBuffer);
                return;
            }
            Context conText2 = MyApplication.getConText();
            SPUtils.put(conText2, "text_img_group_path" + this.selectPicVideoType, stringBuffer);
        } else if (this.isFromFriend) {
            Context conText3 = MyApplication.getConText();
            SPUtils.put(conText3, "text_img_friend_path" + this.selectPicVideoType, "");
        } else {
            Context conText4 = MyApplication.getConText();
            SPUtils.put(conText4, "text_img_group_path" + this.selectPicVideoType, "");
        }
    }
}
