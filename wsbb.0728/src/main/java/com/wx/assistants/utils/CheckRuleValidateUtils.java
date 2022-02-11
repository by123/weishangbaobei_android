package com.wx.assistants.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.umeng.socialize.common.SocializeConstants;
import com.wx.assistants.activity.MemberCenterListActivity;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.globle.ApiWrapper3;
import com.wx.assistants.utils.fileutil.ListUtils;

public class CheckRuleValidateUtils {
    private static CheckRuleValidateUtils instance;
    /* access modifiers changed from: private */
    public static Activity mActivity;
    /* access modifiers changed from: private */
    public CheckResultListener listener;

    public interface CheckResultListener {
        void changeDevice(Object obj);

        void nilPermitExecution(Object obj);

        void permitError(ConmdBean conmdBean);

        void permitExecution(Object obj);
    }

    public interface CheckResultListener2 {
        void permitExecution(Object obj);
    }

    public static CheckRuleValidateUtils getInstance(Activity activity) {
        if (instance == null) {
            synchronized (CheckRuleValidateUtils.class) {
                try {
                    if (instance == null) {
                        instance = new CheckRuleValidateUtils();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<CheckRuleValidateUtils> cls = CheckRuleValidateUtils.class;
                        throw th;
                    }
                }
            }
        }
        mActivity = activity;
        return instance;
    }

    public void checkRuleValidate(final String str, CheckResultListener checkResultListener) {
        if (checkResultListener != null) {
            setCheckResultListener(checkResultListener);
        }
        ApiWrapper3.getInstance().checkRuleValidate(MacAddressUtils.getMacAddress(MyApplication.mContext), new ApiWrapper3.CallbackListener<ConmdBean>() {
            public void connectException() {
                if (CheckRuleValidateUtils.this.listener != null) {
                    ConmdBean conmdBean = new ConmdBean();
                    conmdBean.setMessage("网络连接异常，请检测网络是否可用！");
                    CheckRuleValidateUtils.this.listener.permitError(conmdBean);
                }
            }

            public void onFailure(FailureModel failureModel) {
                Activity access$000 = CheckRuleValidateUtils.mActivity;
                ToastUtils.showToast(access$000, failureModel.getCode() + ListUtils.DEFAULT_JOIN_SEPARATOR + failureModel.getErrorMessage() + ListUtils.DEFAULT_JOIN_SEPARATOR + failureModel.getErrorBody());
            }

            public void onFinish(final ConmdBean conmdBean) {
                String str;
                if (conmdBean != null) {
                    try {
                        if (conmdBean.getCode() == 0) {
                            try {
                                str = ((Double) conmdBean.getData()).doubleValue() + "";
                            } catch (Exception e) {
                                try {
                                    str = ((Integer) conmdBean.getData()).intValue() + "";
                                } catch (Exception e2) {
                                    try {
                                        str = ((String) conmdBean.getData()) + "";
                                    } catch (Exception e3) {
                                        str = "0";
                                    }
                                }
                            }
                            LogUtils.log("WS_BABY_checkRuleValidate.data=" + str);
                            if ("0.0".equals(str) || "0".equals(str) || SocializeConstants.PROTOCOL_VERSON.equals(str) || "2".equals(str)) {
                                if (SocializeConstants.PROTOCOL_VERSON.equals(str) || "2".equals(str)) {
                                    ToastUtils.showToast(CheckRuleValidateUtils.mActivity, "会员已失效");
                                }
                                if (CheckRuleValidateUtils.this.listener != null) {
                                    CheckRuleValidateUtils.this.listener.nilPermitExecution(conmdBean);
                                }
                                DialogUIUtils.dialogOpenMember(CheckRuleValidateUtils.mActivity, (String) null, (View.OnClickListener) null, new View.OnClickListener() {
                                    public void onClick(View view) {
                                        CheckRuleValidateUtils.mActivity.startActivity(new Intent(CheckRuleValidateUtils.mActivity, MemberCenterListActivity.class));
                                    }
                                });
                            } else if (!"-1.0".equals(str) && !"-1".equals(str)) {
                                MyApplication myApplication = MyApplication.instance;
                                if (MyApplication.isNeedSign) {
                                    if (("9".equals(str) && MyApplication.isCommentSign) || "4".equals(str) || "5".equals(str) || "6".equals(str) || "7".equals(str) || "17".equals(str) || "10".equals(str) || "18".equals(str) || "19".equals(str) || "29".equals(str) || "30".equals(str) || "31".equals(str) || "32".equals(str)) {
                                        if (CheckRuleValidateUtils.this.listener != null) {
                                            CheckRuleValidateUtils.this.listener.nilPermitExecution(conmdBean);
                                        }
                                        DialogUIUtils.dialogOpenMemberMain(CheckRuleValidateUtils.mActivity, (String) null, (View.OnClickListener) null, new View.OnClickListener() {
                                            public void onClick(View view) {
                                                if (CheckRuleValidateUtils.this.listener != null) {
                                                    CheckRuleValidateUtils.this.listener.permitExecution(conmdBean);
                                                }
                                            }
                                        }, new View.OnClickListener() {
                                            public void onClick(View view) {
                                                CheckRuleValidateUtils.mActivity.startActivity(new Intent(CheckRuleValidateUtils.mActivity, MemberCenterListActivity.class));
                                            }
                                        });
                                    } else if (CheckRuleValidateUtils.this.listener != null) {
                                        CheckRuleValidateUtils.this.listener.permitExecution(conmdBean);
                                    }
                                } else if (CheckRuleValidateUtils.this.listener != null) {
                                    CheckRuleValidateUtils.this.listener.permitExecution(conmdBean);
                                }
                            } else if (CheckRuleValidateUtils.this.listener != null) {
                                CheckRuleValidateUtils.this.listener.changeDevice(conmdBean);
                            }
                        } else if (conmdBean.getCode() == 1 && CheckRuleValidateUtils.this.listener != null) {
                            CheckRuleValidateUtils.this.listener.permitError(conmdBean);
                        }
                    } catch (Exception e4) {
                        ToastUtils.showToast(MyApplication.getConText(), "此功能，权限获取异常");
                    }
                } else {
                    ToastUtils.showToast(MyApplication.getConText(), "此功能，权限服务出错");
                }
            }

            public void onSocketTimeout() {
                if (CheckRuleValidateUtils.this.listener != null) {
                    CheckRuleValidateUtils.this.listener.permitExecution((Object) null);
                }
            }
        });
    }

    public void setCheckResultListener(CheckResultListener checkResultListener) {
        this.listener = checkResultListener;
    }
}
