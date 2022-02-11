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
import com.wx.assistants.application.MyApplication;
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
                try {
                    if (instance == null) {
                        instance = new ApkDownUtils();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<ApkDownUtils> cls = ApkDownUtils.class;
                        throw th;
                    }
                }
            }
        }
        mContext = activity;
        return instance;
    }

    private static String getVideoMimeType(String str) {
        String lowerCase = str.toLowerCase();
        return (lowerCase.endsWith("mp4") || lowerCase.endsWith("mpeg4") || !lowerCase.endsWith("3gp")) ? "video/mp4" : "video/3gp";
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

    /* access modifiers changed from: private */
    public void installApk(String str) {
        InstallUtils.installAPK(mContext, str, new InstallUtils.InstallCallBack() {
            public void onFail(Exception exc) {
            }

            public void onSuccess() {
            }
        });
    }

    public void checkReadAndWriteExternalStorage() {
        PermissionUtils.checkReadAndWriteExternalStorage(mContext, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
                ApiWrapper.getInstance().getVersion(new ApiWrapper.CallbackListener<ConmdBean<AppVersionModel>>() {
                    public void onFailure(FailureModel failureModel) {
                        LogUtils.log("#######" + failureModel.toString());
                    }

                    public void onFinish(ConmdBean<AppVersionModel> conmdBean) {
                        try {
                            AppVersionModel data = conmdBean.getData();
                            try {
                                if (ApkDownUtils.this.onDownLoadListener != null) {
                                    ApkDownUtils.this.onDownLoadListener.listenerCompleted();
                                }
                                String shareUrl = data.getShareUrl();
                                if (shareUrl != null && !"".equals(shareUrl)) {
                                    MyApplication.SHARE_DOWN_URL = shareUrl;
                                    SPUtils.put(ApkDownUtils.mContext, "user_inviteUrl", MyApplication.SHARE_DOWN_URL);
                                }
                            } catch (Exception e) {
                            }
                            int versionCode = PackageUtils.getVersionCode(ApkDownUtils.mContext);
                            int versionCode2 = data.getVersion().getVersionCode();
                            String versionName = data.getVersion().getVersionName();
                            String versionInstructions = data.getVersion().getVersionInstructions();
                            final String downloadUrl = data.getVersion().getDownloadUrl();
                            int isForce = data.getVersion().getIsForce();
                            if (versionCode < versionCode2) {
                                if (versionCode < 326) {
                                    isForce = 1;
                                }
                                DialogUIUtils.dialogAppVersion(ApkDownUtils.mContext, isForce, versionName, versionInstructions, new View.OnClickListener() {
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
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void checkVersion(boolean z) {
        if (((Boolean) SPUtils.get(mContext, "ws_baby_protocol_new", false)).booleanValue()) {
            this.isToast = z;
            initCallBack();
            checkReadAndWriteExternalStorage();
        }
    }

    public void dismissLoadingDialog() {
        try {
            if (this.loadingDialog != null) {
                this.loadingDialog.close();
            }
        } catch (Exception e) {
        }
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

    public void initCallBack() {
        this.downloadCallBack = new InstallUtils.DownloadCallBack() {
            public void cancle() {
                Log.i(BaseActivity.TAG, "InstallUtils---cancle");
                ApkDownUtils.mContext.runOnUiThread(new Runnable() {
                    public void run() {
                    }
                });
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

                    public void onGranted() {
                        ApkDownUtils.this.dismissLoadingDialog();
                        ApkDownUtils.this.installApk(ApkDownUtils.this.apkDownloadPath);
                    }
                });
            }

            public void onFail(Exception exc) {
                try {
                    ApkDownUtils.this.dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onLoading(long j, long j2) {
                Log.i(BaseActivity.TAG, "InstallUtils----onLoading:-----total:" + j + ",current:" + j2);
                LogUtils.log("WS_BABY_DOWN_LOAD" + j + ListUtils.DEFAULT_JOIN_SEPARATOR + j2);
                if (j > 0 && j2 > 0 && ApkDownUtils.mContext != null) {
                    final int i = (int) ((100 * j2) / j);
                    ApkDownUtils.mContext.runOnUiThread(new Runnable() {
                        public void run() {
                            ApkDownUtils apkDownUtils = ApkDownUtils.this;
                            apkDownUtils.updateLoadingDialog("已下载" + i + "%");
                        }
                    });
                }
            }

            public void onStart() {
                try {
                    ApkDownUtils.this.showLoadingDialog("正在下载");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
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

    public void initWaterMarkCallBack() {
        this.downloadCallBack2 = new InstallUtils.DownloadCallBack() {
            public void cancle() {
                Log.i(BaseActivity.TAG, "InstallUtils---cancle");
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

            public void onFail(Exception exc) {
                try {
                    ApkDownUtils.this.dismissLoadingDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onLoading(long j, long j2) {
                Log.i(BaseActivity.TAG, "InstallUtils----onLoading:-----total:" + j + ",current:" + j2);
                LogUtils.log("WS_BABY_DOWN_LOAD" + j + ListUtils.DEFAULT_JOIN_SEPARATOR + j2);
                if (j2 > 0 && j > 0 && ApkDownUtils.mContext != null) {
                    final int i = (int) ((100 * j2) / j);
                    ApkDownUtils.mContext.runOnUiThread(new Runnable() {
                        public void run() {
                            ApkDownUtils apkDownUtils = ApkDownUtils.this;
                            apkDownUtils.updateLoadingDialog("已保存" + i + "%");
                        }
                    });
                }
            }

            public void onStart() {
                try {
                    ApkDownUtils.this.showLoadingDialog("正在保存中");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void queryVersion() {
        PermissionUtils.checkReadAndWriteExternalStorage(mContext, new PermissionListener() {
            public void permissionDenied(@NonNull String[] strArr) {
            }

            public void permissionGranted(@NonNull String[] strArr) {
                ApiWrapper.getInstance().queryVersion(new ApiWrapper.CallbackListener<ConmdBean>() {
                    public void onFailure(FailureModel failureModel) {
                        LogUtils.log("#######" + failureModel.toString());
                    }

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
                                if (versionCode < 326) {
                                    isForce = 1;
                                }
                                DialogUIUtils.dialogAppVersion(ApkDownUtils.mContext, isForce, versionName, versionInstructions, new View.OnClickListener() {
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
                });
            }
        });
    }

    public void queryVersion(boolean z, OnDownLoadListener onDownLoadListener2) {
        this.isToast = z;
        this.onDownLoadListener = onDownLoadListener2;
        initCallBack();
        checkReadAndWriteExternalStorage();
    }

    public void showLoadingDialog(String str) {
        try {
            this.loadingDialog = new LoadingDialog(mContext);
            this.loadingDialog.setInterceptBack(false);
            this.loadingDialog.setLoadingText(str).show();
        } catch (Exception e) {
        }
    }

    public void updateLoadingDialog(String str) {
        try {
            if (this.loadingDialog != null) {
                this.loadingDialog.setLoadingText(str);
            }
        } catch (Exception e) {
        }
    }
}
