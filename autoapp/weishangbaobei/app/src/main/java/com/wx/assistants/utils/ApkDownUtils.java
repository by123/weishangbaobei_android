package com.wx.assistants.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Process;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.maning.updatelibrary.InstallUtils;
import com.wx.assistants.base.BaseActivity;
import com.wx.assistants.bean.AppVersionModel;
import com.wx.assistants.bean.ConmdBean;
import com.wx.assistants.bean.FailureModel;
import com.wx.assistants.dialog.ProtocolDialog;
import com.wx.assistants.globle.ApiWrapper;
import com.wx.assistants.utils.fileutil.FileUtils;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;
import java.io.File;
import java.io.PrintStream;
import permission.PermissionListener;

public class ApkDownUtils {
    private static ApkDownUtils instance;
    /* access modifiers changed from: private */
    public static Activity mContext;
    /* access modifiers changed from: private */
    public String APK_SAVE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/WSBaby/update.apk");
    /* access modifiers changed from: private */
    public String apkDownloadPath;
    /* access modifiers changed from: private */
    public InstallUtils.DownloadCallBack downloadCallBack;
    /* access modifiers changed from: private */
    public InstallUtils.DownloadCallBack downloadCallBack2;
    /* access modifiers changed from: private */
    public String fileName = "";
    /* access modifiers changed from: private */
    public String filePath = "";
    /* access modifiers changed from: private */
    public boolean isToast = false;
    private LoadingDialog loadingDialog;
    /* access modifiers changed from: private */
    public OnDownLoadListener onDownLoadListener;

    public interface OnDownLoadListener {
        void listenerCompleted();
    }

    private ApkDownUtils() {
    }

    public static ApkDownUtils getInstance(Activity activity) {
        if (instance == null) {
            synchronized (ApkDownUtils.class) {
                if (instance == null) {
                    instance = new ApkDownUtils();
                }
            }
        }
        mContext = activity;
        return instance;
    }

    public void downVideoWaterMark(final String str) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_DOWN = " + str);
        PermissionUtils.checkReadAndWriteExternalStorage(mContext, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
                ApkDownUtils.this.initWaterMarkCallBack();
                try {
                    File file = new File(FileUtils.getSDPath() + "/DCIM/Camera/");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    String unused = ApkDownUtils.this.fileName = new File(str).getName();
                    ApkDownUtils apkDownUtils = ApkDownUtils.this;
                    String unused2 = apkDownUtils.filePath = FileUtils.getSDPath() + "/DCIM/Camera/" + ApkDownUtils.this.fileName;
                    InstallUtils.with(ApkDownUtils.mContext).setApkUrl(str).setApkPath(ApkDownUtils.this.filePath).setCallBack(ApkDownUtils.this.downloadCallBack2).startDownload();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void checkReadAndWriteExternalStorage() {
        PermissionUtils.checkReadAndWriteExternalStorage(mContext, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
                ApiWrapper.getInstance().getVersion(new ApiWrapper.CallbackListener<ConmdBean<AppVersionModel>>() {
                    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
                    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
                    /* JADX WARNING: Removed duplicated region for block: B:14:0x0068 A[Catch:{ Exception -> 0x0096 }] */
                    /* JADX WARNING: Removed duplicated region for block: B:19:0x0082 A[Catch:{ Exception -> 0x0096 }] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onFinish(com.wx.assistants.bean.ConmdBean<com.wx.assistants.bean.AppVersionModel> r10) {
                        /*
                            r9 = this;
                            java.lang.Object r10 = r10.getData()     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.bean.AppVersionModel r10 = (com.wx.assistants.bean.AppVersionModel) r10     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.utils.ApkDownUtils$2 r0 = com.wx.assistants.utils.ApkDownUtils.AnonymousClass2.this     // Catch:{ Exception -> 0x0036 }
                            com.wx.assistants.utils.ApkDownUtils r0 = com.wx.assistants.utils.ApkDownUtils.this     // Catch:{ Exception -> 0x0036 }
                            com.wx.assistants.utils.ApkDownUtils$OnDownLoadListener r0 = r0.onDownLoadListener     // Catch:{ Exception -> 0x0036 }
                            if (r0 == 0) goto L_0x001b
                            com.wx.assistants.utils.ApkDownUtils$2 r0 = com.wx.assistants.utils.ApkDownUtils.AnonymousClass2.this     // Catch:{ Exception -> 0x0036 }
                            com.wx.assistants.utils.ApkDownUtils r0 = com.wx.assistants.utils.ApkDownUtils.this     // Catch:{ Exception -> 0x0036 }
                            com.wx.assistants.utils.ApkDownUtils$OnDownLoadListener r0 = r0.onDownLoadListener     // Catch:{ Exception -> 0x0036 }
                            r0.listenerCompleted()     // Catch:{ Exception -> 0x0036 }
                        L_0x001b:
                            java.lang.String r0 = r10.getShareUrl()     // Catch:{ Exception -> 0x0036 }
                            if (r0 == 0) goto L_0x0036
                            java.lang.String r1 = ""
                            boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x0036 }
                            if (r1 != 0) goto L_0x0036
                            com.wx.assistants.application.MyApplication.SHARE_DOWN_URL = r0     // Catch:{ Exception -> 0x0036 }
                            android.app.Activity r0 = com.wx.assistants.utils.ApkDownUtils.mContext     // Catch:{ Exception -> 0x0036 }
                            java.lang.String r1 = "user_inviteUrl"
                            java.lang.String r2 = com.wx.assistants.application.MyApplication.SHARE_DOWN_URL     // Catch:{ Exception -> 0x0036 }
                            com.wx.assistants.utils.SPUtils.put(r0, r1, r2)     // Catch:{ Exception -> 0x0036 }
                        L_0x0036:
                            android.app.Activity r0 = com.wx.assistants.utils.ApkDownUtils.mContext     // Catch:{ Exception -> 0x0096 }
                            int r0 = com.wx.assistants.utils.PackageUtils.getVersionCode(r0)     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.bean.AppVersionModel$BaseVersion r1 = r10.getVersion()     // Catch:{ Exception -> 0x0096 }
                            int r1 = r1.getVersionCode()     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.bean.AppVersionModel$BaseVersion r2 = r10.getVersion()     // Catch:{ Exception -> 0x0096 }
                            java.lang.String r5 = r2.getVersionName()     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.bean.AppVersionModel$BaseVersion r2 = r10.getVersion()     // Catch:{ Exception -> 0x0096 }
                            java.lang.String r6 = r2.getVersionInstructions()     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.bean.AppVersionModel$BaseVersion r2 = r10.getVersion()     // Catch:{ Exception -> 0x0096 }
                            java.lang.String r2 = r2.getDownloadUrl()     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.bean.AppVersionModel$BaseVersion r10 = r10.getVersion()     // Catch:{ Exception -> 0x0096 }
                            int r10 = r10.getIsForce()     // Catch:{ Exception -> 0x0096 }
                            if (r0 >= r1) goto L_0x0082
                            r1 = 326(0x146, float:4.57E-43)
                            if (r0 >= r1) goto L_0x006f
                            r10 = 1
                            r4 = 1
                            goto L_0x0070
                        L_0x006f:
                            r4 = r10
                        L_0x0070:
                            android.app.Activity r3 = com.wx.assistants.utils.ApkDownUtils.mContext     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.utils.ApkDownUtils$2$1$1 r7 = new com.wx.assistants.utils.ApkDownUtils$2$1$1     // Catch:{ Exception -> 0x0096 }
                            r7.<init>()     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.utils.ApkDownUtils$2$1$2 r8 = new com.wx.assistants.utils.ApkDownUtils$2$1$2     // Catch:{ Exception -> 0x0096 }
                            r8.<init>(r2)     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.utils.DialogUIUtils.dialogAppVersion(r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0096 }
                            goto L_0x009a
                        L_0x0082:
                            com.wx.assistants.utils.ApkDownUtils$2 r10 = com.wx.assistants.utils.ApkDownUtils.AnonymousClass2.this     // Catch:{ Exception -> 0x0096 }
                            com.wx.assistants.utils.ApkDownUtils r10 = com.wx.assistants.utils.ApkDownUtils.this     // Catch:{ Exception -> 0x0096 }
                            boolean r10 = r10.isToast     // Catch:{ Exception -> 0x0096 }
                            if (r10 == 0) goto L_0x009a
                            android.app.Activity r10 = com.wx.assistants.utils.ApkDownUtils.mContext     // Catch:{ Exception -> 0x0096 }
                            java.lang.String r0 = "当前已是最新版本"
                            com.wx.assistants.utils.ToastUtils.showToast(r10, r0)     // Catch:{ Exception -> 0x0096 }
                            goto L_0x009a
                        L_0x0096:
                            r10 = move-exception
                            r10.printStackTrace()
                        L_0x009a:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.utils.ApkDownUtils.AnonymousClass2.AnonymousClass1.onFinish(com.wx.assistants.bean.ConmdBean):void");
                    }

                    public void onFailure(FailureModel failureModel) {
                        LogUtils.log("#######" + failureModel.toString());
                    }
                });
            }
        });
    }

    public void queryVersion() {
        PermissionUtils.checkReadAndWriteExternalStorage(mContext, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
                ApiWrapper.getInstance().queryVersion(new ApiWrapper.CallbackListener<ConmdBean>() {
                    public void onFinish(ConmdBean conmdBean) {
                        if (ApkDownUtils.this.onDownLoadListener != null) {
                            ApkDownUtils.this.onDownLoadListener.listenerCompleted();
                        }
                        try {
                            AppVersionModel appVersionModel = (AppVersionModel) new Gson().fromJson(conmdBean.getData().toString(), AppVersionModel.class);
                            int versionCode = PackageUtils.getVersionCode(ApkDownUtils.mContext);
                            int versionCode2 = appVersionModel.getVersion().getVersionCode();
                            String versionInstructions = appVersionModel.getVersion().getVersionInstructions();
                            final String downloadUrl = appVersionModel.getVersion().getDownloadUrl();
                            String versionName = appVersionModel.getVersion().getVersionName();
                            int isForce = appVersionModel.getVersion().getIsForce();
                            if (versionCode < versionCode2) {
                                DialogUIUtils.dialogAppVersion(ApkDownUtils.mContext, versionCode < 326 ? 1 : isForce, versionName, versionInstructions, new View.OnClickListener() {
                                    public void onClick(View view) {
                                    }
                                }, new View.OnClickListener() {
                                    public void onClick(View view) {
                                        InstallUtils.with(ApkDownUtils.mContext).setApkUrl(downloadUrl).setApkPath(ApkDownUtils.this.APK_SAVE_PATH).setCallBack(ApkDownUtils.this.downloadCallBack).startDownload();
                                    }
                                });
                            } else if (ApkDownUtils.this.isToast) {
                                ToastUtils.showToast(ApkDownUtils.mContext, "当前已是最新版本");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onFailure(FailureModel failureModel) {
                        LogUtils.log("#######" + failureModel.toString());
                    }
                });
            }
        });
    }

    public void initProtocol() {
        if (!((Boolean) SPUtils.get(mContext, "ws_baby_protocol_new", false)).booleanValue()) {
            new ProtocolDialog(mContext).builder().setPositiveButton(new View.OnClickListener() {
                public void onClick(View view) {
                    SPUtils.put(ApkDownUtils.mContext, "ws_baby_protocol_new", true);
                    PermissionUtils.checkAllPermission(ApkDownUtils.mContext, new PermissionListener() {
                        public void permissionDenied(@NonNull String[] strArr) {
                        }

                        public void permissionGranted(@NonNull String[] strArr) {
                        }
                    });
                }
            }).setNilAgree(new View.OnClickListener() {
                public void onClick(View view) {
                    AppManager.getAppManager().finishAllActivity();
                    System.exit(0);
                    Process.killProcess(Process.myPid());
                }
            }).setCancelable(false).show();
        }
    }

    public void checkVersion(boolean z) {
        if (((Boolean) SPUtils.get(mContext, "ws_baby_protocol_new", false)).booleanValue()) {
            this.isToast = z;
            initCallBack();
            checkReadAndWriteExternalStorage();
        }
    }

    public void queryVersion(boolean z, OnDownLoadListener onDownLoadListener2) {
        this.isToast = z;
        this.onDownLoadListener = onDownLoadListener2;
        initCallBack();
        checkReadAndWriteExternalStorage();
    }

    public void initWaterMarkCallBack() {
        this.downloadCallBack2 = new InstallUtils.DownloadCallBack() {
            public void onStart() {
                try {
                    ApkDownUtils.this.showLoadingDialog("正在保存中");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onComplete(String str) {
                try {
                    ApkDownUtils.this.dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogUtils.log("WS_BABY_DOWN_END" + str);
                String str2 = FileUtils.getSDPath() + "/DCIM/Camera/" + (System.currentTimeMillis() + PictureFileUtils.POST_VIDEO);
                ToastUtils.showToast(ApkDownUtils.mContext, "已保存到" + str2);
                new File(str).renameTo(new File(str2));
                File file = new File(str2);
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(file));
                ApkDownUtils.mContext.sendBroadcast(intent);
            }

            public void onLoading(long j, long j2) {
                Log.i(BaseActivity.TAG, "InstallUtils----onLoading:-----total:" + j + ",current:" + j2);
                LogUtils.log("WS_BABY_DOWN_LOAD" + j + ListUtils.DEFAULT_JOIN_SEPARATOR + j2);
                if (j2 > 0 && j > 0 && ApkDownUtils.mContext != null) {
                    final int i = (int) ((j2 * 100) / j);
                    ApkDownUtils.mContext.runOnUiThread(new Runnable() {
                        public void run() {
                            ApkDownUtils apkDownUtils = ApkDownUtils.this;
                            apkDownUtils.updateLoadingDialog("已保存" + i + "%");
                        }
                    });
                }
            }

            public void onFail(Exception exc) {
                try {
                    ApkDownUtils.this.dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void cancle() {
                Log.i(BaseActivity.TAG, "InstallUtils---cancle");
            }
        };
    }

    public static void insertIntoMediaStore(Context context, boolean z, File file, long j) {
        ContentResolver contentResolver = context.getContentResolver();
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", file.getName());
        contentValues.put("_display_name", file.getName());
        contentValues.put("datetaken", Long.valueOf(j));
        contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
        if (!z) {
            contentValues.put("orientation", 0);
        }
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("_size", Long.valueOf(file.length()));
        contentValues.put("mime_type", z ? getVideoMimeType(file.getAbsolutePath()) : "image/jpeg");
        contentResolver.insert(z ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    private static String getVideoMimeType(String str) {
        String lowerCase = str.toLowerCase();
        return (lowerCase.endsWith("mp4") || lowerCase.endsWith("mpeg4") || !lowerCase.endsWith("3gp")) ? "video/mp4" : "video/3gp";
    }

    public void initCallBack() {
        this.downloadCallBack = new InstallUtils.DownloadCallBack() {
            public void onStart() {
                try {
                    ApkDownUtils.this.showLoadingDialog("正在下载");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onComplete(String str) {
                try {
                    ApkDownUtils.this.dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogUtils.log("WS_BABY_DOWN_END" + str);
                Log.i(BaseActivity.TAG, "InstallUtils---onComplete:" + str);
                String unused = ApkDownUtils.this.apkDownloadPath = str;
                InstallUtils.checkInstallPermission(ApkDownUtils.mContext, new InstallUtils.InstallPermissionCallBack() {
                    public void onGranted() {
                        ApkDownUtils.this.dismissLoadingDialog();
                        ApkDownUtils.this.installApk(ApkDownUtils.this.apkDownloadPath);
                    }

                    public void onDenied() {
                        ApkDownUtils.this.dismissLoadingDialog();
                        DialogUIUtils.authorizationDialog(ApkDownUtils.mContext, (View.OnClickListener) null, new View.OnClickListener() {
                            public void onClick(View view) {
                                InstallUtils.openInstallPermissionSetting(ApkDownUtils.mContext, new InstallUtils.InstallPermissionCallBack() {
                                    public void onDenied() {
                                    }

                                    public void onGranted() {
                                        ApkDownUtils.this.installApk(ApkDownUtils.this.apkDownloadPath);
                                    }
                                });
                            }
                        });
                    }
                });
            }

            public void onLoading(long j, long j2) {
                Log.i(BaseActivity.TAG, "InstallUtils----onLoading:-----total:" + j + ",current:" + j2);
                LogUtils.log("WS_BABY_DOWN_LOAD" + j + ListUtils.DEFAULT_JOIN_SEPARATOR + j2);
                if (j > 0 && j2 > 0 && ApkDownUtils.mContext != null) {
                    final int i = (int) ((j2 * 100) / j);
                    ApkDownUtils.mContext.runOnUiThread(new Runnable() {
                        public void run() {
                            ApkDownUtils apkDownUtils = ApkDownUtils.this;
                            apkDownUtils.updateLoadingDialog("已下载" + i + "%");
                        }
                    });
                }
            }

            public void onFail(Exception exc) {
                try {
                    ApkDownUtils.this.dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void cancle() {
                Log.i(BaseActivity.TAG, "InstallUtils---cancle");
                ApkDownUtils.mContext.runOnUiThread(new Runnable() {
                    public void run() {
                    }
                });
            }
        };
    }

    /* access modifiers changed from: private */
    public void installApk(String str) {
        InstallUtils.installAPK(mContext, str, new InstallUtils.InstallCallBack() {
            public void onFail(Exception exc) {
            }

            public void onSuccess() {
            }
        });
    }

    public void showLoadingDialog(String str) {
        try {
            this.loadingDialog = new LoadingDialog(mContext);
            this.loadingDialog.setInterceptBack(false);
            this.loadingDialog.setLoadingText(str).show();
        } catch (Exception unused) {
        }
    }

    public void updateLoadingDialog(String str) {
        try {
            if (this.loadingDialog != null) {
                this.loadingDialog.setLoadingText(str);
            }
        } catch (Exception unused) {
        }
    }

    public void dismissLoadingDialog() {
        try {
            if (this.loadingDialog != null) {
                this.loadingDialog.close();
            }
        } catch (Exception unused) {
        }
    }
}
