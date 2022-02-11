package com.wx.assistants.utils;

import android.app.Activity;
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

    public void setCheckResultListener(CheckResultListener checkResultListener) {
        this.listener = checkResultListener;
    }

    public static CheckRuleValidateUtils getInstance(Activity activity) {
        if (instance == null) {
            synchronized (CheckRuleValidateUtils.class) {
                if (instance == null) {
                    instance = new CheckRuleValidateUtils();
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
            /* JADX WARNING: Can't wrap try/catch for region: R(2:7|8) */
            /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
                r1 = ((java.lang.String) r5.getData()) + "";
             */
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x005a, code lost:
                r1 = "0";
             */
            /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
                r1 = ((java.lang.Integer) r5.getData()).intValue() + "";
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0026 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0042 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFinish(final com.wx.assistants.bean.ConmdBean r5) {
                /*
                    r4 = this;
                    if (r5 == 0) goto L_0x01dd
                    int r0 = r5.getCode()     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x01c4
                    java.lang.String r0 = "0"
                    java.lang.Object r1 = r5.getData()     // Catch:{ Exception -> 0x0026 }
                    java.lang.Double r1 = (java.lang.Double) r1     // Catch:{ Exception -> 0x0026 }
                    double r1 = r1.doubleValue()     // Catch:{ Exception -> 0x0026 }
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0026 }
                    r3.<init>()     // Catch:{ Exception -> 0x0026 }
                    r3.append(r1)     // Catch:{ Exception -> 0x0026 }
                    java.lang.String r1 = ""
                    r3.append(r1)     // Catch:{ Exception -> 0x0026 }
                    java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x0026 }
                    goto L_0x005b
                L_0x0026:
                    java.lang.Object r1 = r5.getData()     // Catch:{ Exception -> 0x0042 }
                    java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Exception -> 0x0042 }
                    int r1 = r1.intValue()     // Catch:{ Exception -> 0x0042 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0042 }
                    r2.<init>()     // Catch:{ Exception -> 0x0042 }
                    r2.append(r1)     // Catch:{ Exception -> 0x0042 }
                    java.lang.String r1 = ""
                    r2.append(r1)     // Catch:{ Exception -> 0x0042 }
                    java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0042 }
                    goto L_0x005b
                L_0x0042:
                    java.lang.Object r1 = r5.getData()     // Catch:{ Exception -> 0x005a }
                    java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x005a }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005a }
                    r2.<init>()     // Catch:{ Exception -> 0x005a }
                    r2.append(r1)     // Catch:{ Exception -> 0x005a }
                    java.lang.String r1 = ""
                    r2.append(r1)     // Catch:{ Exception -> 0x005a }
                    java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x005a }
                    goto L_0x005b
                L_0x005a:
                    r1 = r0
                L_0x005b:
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e7 }
                    r0.<init>()     // Catch:{ Exception -> 0x01e7 }
                    java.lang.String r2 = "WS_BABY_checkRuleValidate.data="
                    r0.append(r2)     // Catch:{ Exception -> 0x01e7 }
                    r0.append(r1)     // Catch:{ Exception -> 0x01e7 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.LogUtils.log(r0)     // Catch:{ Exception -> 0x01e7 }
                    java.lang.String r0 = "0.0"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    r2 = 0
                    if (r0 != 0) goto L_0x018d
                    java.lang.String r0 = "0"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x018d
                    java.lang.String r0 = "2.0"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x018d
                    java.lang.String r0 = "2"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x0092
                    goto L_0x018d
                L_0x0092:
                    java.lang.String r0 = "-1.0"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x017b
                    java.lang.String r0 = "-1"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x00a4
                    goto L_0x017b
                L_0x00a4:
                    com.wx.assistants.application.MyApplication r0 = com.wx.assistants.application.MyApplication.instance     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = com.wx.assistants.application.MyApplication.isNeedSign     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x0168
                    java.lang.String r0 = "9"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x00b8
                    boolean r0 = com.wx.assistants.application.MyApplication.isCommentSign     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                L_0x00b8:
                    java.lang.String r0 = "4"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "5"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "6"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "7"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "17"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "10"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "18"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "19"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "29"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "30"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "31"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x0144
                    java.lang.String r0 = "32"
                    java.lang.String r1 = r3     // Catch:{ Exception -> 0x01e7 }
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x0131
                    goto L_0x0144
                L_0x0131:
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x01f0
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    r0.permitExecution(r5)     // Catch:{ Exception -> 0x01e7 }
                    goto L_0x01f0
                L_0x0144:
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x0155
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    r0.nilPermitExecution(r5)     // Catch:{ Exception -> 0x01e7 }
                L_0x0155:
                    android.app.Activity r0 = com.wx.assistants.utils.CheckRuleValidateUtils.mActivity     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$1$2 r1 = new com.wx.assistants.utils.CheckRuleValidateUtils$1$2     // Catch:{ Exception -> 0x01e7 }
                    r1.<init>(r5)     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$1$3 r5 = new com.wx.assistants.utils.CheckRuleValidateUtils$1$3     // Catch:{ Exception -> 0x01e7 }
                    r5.<init>()     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.DialogUIUtils.dialogOpenMemberMain(r0, r2, r2, r1, r5)     // Catch:{ Exception -> 0x01e7 }
                    goto L_0x01f0
                L_0x0168:
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x01f0
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    r0.permitExecution(r5)     // Catch:{ Exception -> 0x01e7 }
                    goto L_0x01f0
                L_0x017b:
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x01f0
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    r0.changeDevice(r5)     // Catch:{ Exception -> 0x01e7 }
                    goto L_0x01f0
                L_0x018d:
                    java.lang.String r0 = "2.0"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 != 0) goto L_0x019d
                    java.lang.String r0 = "2"
                    boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x01a6
                L_0x019d:
                    android.app.Activity r0 = com.wx.assistants.utils.CheckRuleValidateUtils.mActivity     // Catch:{ Exception -> 0x01e7 }
                    java.lang.String r1 = "会员已失效"
                    com.wx.assistants.utils.ToastUtils.showToast(r0, r1)     // Catch:{ Exception -> 0x01e7 }
                L_0x01a6:
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x01b7
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    r0.nilPermitExecution(r5)     // Catch:{ Exception -> 0x01e7 }
                L_0x01b7:
                    android.app.Activity r5 = com.wx.assistants.utils.CheckRuleValidateUtils.mActivity     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$1$1 r0 = new com.wx.assistants.utils.CheckRuleValidateUtils$1$1     // Catch:{ Exception -> 0x01e7 }
                    r0.<init>()     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.DialogUIUtils.dialogOpenMember(r5, r2, r2, r0)     // Catch:{ Exception -> 0x01e7 }
                    goto L_0x01f0
                L_0x01c4:
                    int r0 = r5.getCode()     // Catch:{ Exception -> 0x01e7 }
                    r1 = 1
                    if (r0 != r1) goto L_0x01f0
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    if (r0 == 0) goto L_0x01f0
                    com.wx.assistants.utils.CheckRuleValidateUtils r0 = com.wx.assistants.utils.CheckRuleValidateUtils.this     // Catch:{ Exception -> 0x01e7 }
                    com.wx.assistants.utils.CheckRuleValidateUtils$CheckResultListener r0 = r0.listener     // Catch:{ Exception -> 0x01e7 }
                    r0.permitError(r5)     // Catch:{ Exception -> 0x01e7 }
                    goto L_0x01f0
                L_0x01dd:
                    android.content.Context r5 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x01e7 }
                    java.lang.String r0 = "此功能，权限服务出错"
                    com.wx.assistants.utils.ToastUtils.showToast(r5, r0)     // Catch:{ Exception -> 0x01e7 }
                    goto L_0x01f0
                L_0x01e7:
                    android.content.Context r5 = com.wx.assistants.application.MyApplication.getConText()
                    java.lang.String r0 = "此功能，权限获取异常"
                    com.wx.assistants.utils.ToastUtils.showToast(r5, r0)
                L_0x01f0:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.CheckRuleValidateUtils.AnonymousClass1.onFinish(com.wx.assistants.bean.ConmdBean):void");
            }

            public void onFailure(FailureModel failureModel) {
                Activity access$000 = CheckRuleValidateUtils.mActivity;
                ToastUtils.showToast(access$000, failureModel.getCode() + ListUtils.DEFAULT_JOIN_SEPARATOR + failureModel.getErrorMessage() + ListUtils.DEFAULT_JOIN_SEPARATOR + failureModel.getErrorBody());
            }

            public void onSocketTimeout() {
                if (CheckRuleValidateUtils.this.listener != null) {
                    CheckRuleValidateUtils.this.listener.permitExecution((Object) null);
                }
            }

            public void connectException() {
                if (CheckRuleValidateUtils.this.listener != null) {
                    ConmdBean conmdBean = new ConmdBean();
                    conmdBean.setMessage("网络连接异常，请检测网络是否可用！");
                    CheckRuleValidateUtils.this.listener.permitError(conmdBean);
                }
            }
        });
    }
}
