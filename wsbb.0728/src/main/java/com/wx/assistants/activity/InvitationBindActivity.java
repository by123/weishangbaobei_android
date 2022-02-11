package com.wx.assistants.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.stub.StubApp;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.BindInvitationModel;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.MacAddressUtils;
import com.wx.assistants.utils.ToastUtils;
import top.limuyang2.shadowlayoutlib.ShadowLinearLayout;

public class InvitationBindActivity extends BaseActivity {
    @BindView(2131296526)
    Button confirmBtn;
    @BindView(2131297049)
    LinearLayout navBack;
    @BindView(2131297052)
    LinearLayout navRightLayout;
    @BindView(2131297053)
    TextView navRightText;
    @BindView(2131297054)
    TextView navTitle;
    @BindView(2131297125)
    EditText phoneNum;
    @BindView(2131297363)
    ShadowLinearLayout shadowLinearLayout;

    static {
        StubApp.interface11(8892);
    }

    public void invitationBind(String str) {
        MacAddressUtils.getMacAddress(MyApplication.mContext);
        showLoadingDialog("正在操作");
        ApiWrapper.getInstance().invitationBind(new BindInvitationModel(str), new ApiWrapper.CallbackListener<ConmdBean>() {
            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.InvitationBindActivity] */
            public void onFailure(FailureModel failureModel) {
                InvitationBindActivity.this.dismissLoadingDialog();
                ToastUtils.showToast(InvitationBindActivity.this, failureModel.getErrorMessage());
            }

            /* JADX WARNING: type inference failed for: r0v1, types: [android.content.Context, com.wx.assistants.activity.InvitationBindActivity] */
            /* JADX WARNING: type inference failed for: r0v3, types: [android.content.Context, com.wx.assistants.activity.InvitationBindActivity] */
            /* JADX WARNING: type inference failed for: r0v5, types: [android.content.Context, com.wx.assistants.activity.InvitationBindActivity] */
            public void onFinish(ConmdBean conmdBean) {
                InvitationBindActivity.this.dismissLoadingDialog();
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0) {
                            ToastUtils.showToast(InvitationBindActivity.this, "绑定成功");
                            InvitationBindActivity.this.finish();
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtils.showToast(InvitationBindActivity.this, "绑定失败");
                        return;
                    }
                }
                ToastUtils.showToast(InvitationBindActivity.this, conmdBean.getMessage());
            }
        });
    }

    /* access modifiers changed from: protected */
    public native void onCreate(Bundle bundle);

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.wx.assistants.activity.InvitationBindActivity] */
    @OnClick({2131297049, 2131296526})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == 2131296526) {
            String obj = this.phoneNum.getText().toString();
            if (obj == null || "".equals(obj)) {
                ToastUtils.showToast(this, "请输入被邀请人的手机号");
            } else {
                invitationBind(obj);
            }
        } else if (id == 2131297049) {
            back();
        }
    }
}
