package com.wx.assistants.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.wx.assistants.Enity.CopyFriendBean;
import com.wx.assistants.activity.ObtainCopyFileActivity;
import com.wx.assistants.dialog.view.AlertEditTextDialog;
import com.wx.assistants.utils.DialogUIUtils;
import com.wx.assistants.utils.PermissionUtils;
import com.wx.assistants.utils.SQLiteUtils;
import com.wx.assistants.utils.ToastUtils;
import com.wx.assistants.utils.fileutil.FileUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import permission.PermissionListener;

public class FriendsMoveBottomLayout extends LinearLayout {
    @BindView(2131296361)
    Button autoCopy;
    @BindView(2131296503)
    Button cleanAddedBtn;
    @BindView(2131296504)
    Button cleanAllBtn;
    /* access modifiers changed from: private */
    public String jsonData;
    /* access modifiers changed from: private */
    public OnFriendsMoveListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    @BindView(2131297218)
    Button readFile;
    private List<CopyFriendBean> recoverFriendBeanList;

    public interface OnFriendsMoveListener {
        void cleanAddedSuccess();

        void cleanAllSuccess();
    }

    public FriendsMoveBottomLayout(Context context) {
        super(context);
        this.mContext = context;
    }

    public FriendsMoveBottomLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initCirculateNumView(context);
    }

    public FriendsMoveBottomLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void initCirculateNumView(Context context) {
        ButterKnife.bind(LayoutInflater.from(context).inflate(2131427545, this, true));
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    @OnClick({2131296504, 2131296503, 2131296361, 2131297218})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case 2131296361:
                PermissionUtils.checkReadAndWriteExternalStorage(this.mContext, new PermissionListener() {
                    public void permissionDenied(@NonNull String[] strArr) {
                    }

                    public void permissionGranted(@NonNull String[] strArr) {
                        List allCopyFriends = SQLiteUtils.getInstance().getAllCopyFriends();
                        if (allCopyFriends == null || allCopyFriends.size() <= 0) {
                            ToastUtils.showToast(FriendsMoveBottomLayout.this.mContext, "没发现备份的好友！");
                        } else {
                            DialogUIUtils.setEditText(FriendsMoveBottomLayout.this.mContext, "请设置文件名", "", new AlertEditTextDialog.OnEditTextListener() {
                                public void result(String str) {
                                    try {
                                        Context access$000 = FriendsMoveBottomLayout.this.mContext;
                                        FileUtils.saveToSDCard(access$000, "WsBaby#_#" + str + "#_#" + System.currentTimeMillis() + ".txt", FriendsMoveBottomLayout.this.jsonData);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (Throwable th) {
                                        ToastUtils.showToast(FriendsMoveBottomLayout.this.mContext, "保存成功");
                                        throw th;
                                    }
                                    ToastUtils.showToast(FriendsMoveBottomLayout.this.mContext, "保存成功");
                                }
                            });
                        }
                    }
                });
                return;
            case 2131296503:
                final List allAddedCopyFriends = SQLiteUtils.getInstance().getAllAddedCopyFriends();
                if (allAddedCopyFriends == null || allAddedCopyFriends.size() == 0) {
                    ToastUtils.showToast(this.mContext, "没有要清理的数据!");
                    return;
                } else {
                    DialogUIUtils.dialogDefault(this.mContext, "清空已经添加的", "您确定要清除已添加的数据吗？", "取消", "确定", (View.OnClickListener) null, new View.OnClickListener() {
                        public void onClick(View view) {
                            SQLiteUtils.getInstance().deleteAddedCopyFriends(allAddedCopyFriends);
                            ToastUtils.showToast(FriendsMoveBottomLayout.this.mContext, "已清除");
                            if (FriendsMoveBottomLayout.this.listener != null) {
                                FriendsMoveBottomLayout.this.listener.cleanAddedSuccess();
                            }
                        }
                    });
                    return;
                }
            case 2131296504:
                List allCopyFriends = SQLiteUtils.getInstance().getAllCopyFriends();
                if (allCopyFriends == null || allCopyFriends.size() <= 0) {
                    ToastUtils.showToast(this.mContext, "没有要清理的数据!");
                    return;
                } else {
                    DialogUIUtils.dialogDefault(this.mContext, "清空全部备份", "您确定要全部清除吗？", "取消", "确定", (View.OnClickListener) null, new View.OnClickListener() {
                        public void onClick(View view) {
                            SQLiteUtils.getInstance().deleteAllCopyFriends();
                            ToastUtils.showToast(FriendsMoveBottomLayout.this.mContext, "已全部清除");
                            if (FriendsMoveBottomLayout.this.listener != null) {
                                FriendsMoveBottomLayout.this.listener.cleanAllSuccess();
                            }
                        }
                    });
                    return;
                }
            case 2131297218:
                PermissionUtils.checkReadAndWriteExternalStorage(this.mContext, new PermissionListener() {
                    public void permissionDenied(@NonNull String[] strArr) {
                    }

                    public void permissionGranted(@NonNull String[] strArr) {
                        FriendsMoveBottomLayout.this.mContext.startActivity(new Intent(FriendsMoveBottomLayout.this.mContext, ObtainCopyFileActivity.class));
                    }
                });
                return;
            default:
                return;
        }
    }

    public void setData(List<CopyFriendBean> list) {
        this.recoverFriendBeanList = list;
        if (this.recoverFriendBeanList != null && this.recoverFriendBeanList.size() > 0) {
            try {
                this.jsonData = new Gson().toJson(this.recoverFriendBeanList);
                PrintStream printStream = System.out;
                printStream.println("WS_BABY_" + this.jsonData);
            } catch (Exception e) {
            }
        }
    }

    public void setOnFriendsMoveListener(OnFriendsMoveListener onFriendsMoveListener) {
        if (onFriendsMoveListener != null) {
            this.listener = onFriendsMoveListener;
        }
    }
}
