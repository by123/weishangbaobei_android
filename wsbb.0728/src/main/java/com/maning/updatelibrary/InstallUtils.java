package com.maning.updatelibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.maning.updatelibrary.http.AbsFileProgressCallback;
import com.maning.updatelibrary.http.DownloadFileUtils;
import com.maning.updatelibrary.utils.ActForResultCallback;
import com.maning.updatelibrary.utils.ActResultRequest;
import com.maning.updatelibrary.utils.MNUtils;
import com.stub.StubApp;
import java.io.File;

public class InstallUtils {
    /* access modifiers changed from: private */
    public static final String TAG = "InstallUtils";
    /* access modifiers changed from: private */
    public static boolean isDownloading;
    private static Context mContext;
    /* access modifiers changed from: private */
    public static DownloadCallBack mDownloadCallBack;
    private static InstallUtils mInstance;
    /* access modifiers changed from: private */
    public String filePath;
    private String httpUrl;

    public interface DownloadCallBack {
        void cancle();

        void onComplete(String str);

        void onFail(Exception exc);

        void onLoading(long j, long j2);

        void onStart();
    }

    public interface InstallCallBack {
        void onFail(Exception exc);

        void onSuccess();
    }

    public interface InstallPermissionCallBack {
        void onDenied();

        void onGranted();
    }

    private InstallUtils() {
    }

    public static void cancleDownload() {
        DownloadFileUtils.cancle(InstallUtils.class);
    }

    public static void checkInstallPermission(Activity activity, InstallPermissionCallBack installPermissionCallBack) {
        if (!hasInstallPermission(activity)) {
            openInstallPermissionSetting(activity, installPermissionCallBack);
        } else if (installPermissionCallBack != null) {
            installPermissionCallBack.onGranted();
        }
    }

    public static boolean hasInstallPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    public static void installAPK(Activity activity, String str, final InstallCallBack installCallBack) {
        Uri fromFile;
        try {
            MNUtils.changeApkFileMode(new File(str));
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            File file = new File(str);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(1);
                fromFile = MNUpdateApkFileProvider.getUriForFile(activity, activity.getPackageName() + ".updateFileProvider", file);
            } else {
                fromFile = Uri.fromFile(file);
            }
            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
            new ActResultRequest(activity).startForResult(intent, new ActForResultCallback() {
                public void onActivityResult(int i, Intent intent) {
                    String access$300 = InstallUtils.TAG;
                    Log.i(access$300, "onActivityResult:" + i);
                    if (installCallBack != null) {
                        installCallBack.onSuccess();
                    }
                }
            });
        } catch (Exception e) {
            if (installCallBack != null) {
                installCallBack.onFail(e);
            }
        }
    }

    public static void installAPKWithBrower(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static boolean isDownloading() {
        return isDownloading;
    }

    public static void openInstallPermissionSetting(Activity activity, final InstallPermissionCallBack installPermissionCallBack) {
        if (Build.VERSION.SDK_INT >= 26) {
            new ActResultRequest(activity).startForResult(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + activity.getPackageName())), new ActForResultCallback() {
                public void onActivityResult(int i, Intent intent) {
                    String access$300 = InstallUtils.TAG;
                    Log.i(access$300, "onActivityResult:" + i);
                    if (i == -1) {
                        if (installPermissionCallBack != null) {
                            installPermissionCallBack.onGranted();
                        }
                    } else if (installPermissionCallBack != null) {
                        installPermissionCallBack.onDenied();
                    }
                }
            });
        } else if (installPermissionCallBack != null) {
            installPermissionCallBack.onGranted();
        }
    }

    public static void setDownloadCallBack(DownloadCallBack downloadCallBack) {
        if (isDownloading) {
            mDownloadCallBack = downloadCallBack;
        }
    }

    public static InstallUtils with(Context context) {
        mContext = StubApp.getOrigApplicationContext(context.getApplicationContext());
        if (mInstance == null) {
            mInstance = new InstallUtils();
        }
        return mInstance;
    }

    public InstallUtils setApkPath(String str) {
        this.filePath = str;
        return mInstance;
    }

    public InstallUtils setApkUrl(String str) {
        this.httpUrl = str;
        return mInstance;
    }

    public InstallUtils setCallBack(DownloadCallBack downloadCallBack) {
        mDownloadCallBack = downloadCallBack;
        return mInstance;
    }

    public void startDownload() {
        if (isDownloading) {
            cancleDownload();
        }
        if (TextUtils.isEmpty(this.filePath)) {
            this.filePath = MNUtils.getCachePath(mContext) + "/update.apk";
        }
        MNUtils.changeApkFileMode(new File(this.filePath));
        DownloadFileUtils.with().downloadPath(this.filePath).url(this.httpUrl).tag(InstallUtils.class).execute(new AbsFileProgressCallback() {
            int currentProgress = 0;

            public void onCancle() {
                boolean unused = InstallUtils.isDownloading = false;
                if (InstallUtils.mDownloadCallBack != null) {
                    InstallUtils.mDownloadCallBack.cancle();
                }
            }

            public void onFailed(String str) {
                boolean unused = InstallUtils.isDownloading = false;
                if (InstallUtils.mDownloadCallBack != null) {
                    InstallUtils.mDownloadCallBack.onFail(new Exception(str));
                }
            }

            public void onProgress(long j, long j2, boolean z) {
                boolean unused = InstallUtils.isDownloading = true;
                if (InstallUtils.mDownloadCallBack != null && j2 > 0) {
                    int i = (int) ((100 * j) / j2);
                    if (i - this.currentProgress >= 1) {
                        InstallUtils.mDownloadCallBack.onLoading(j2, j);
                    }
                    this.currentProgress = i;
                }
            }

            public void onStart() {
                boolean unused = InstallUtils.isDownloading = true;
                if (InstallUtils.mDownloadCallBack != null) {
                    InstallUtils.mDownloadCallBack.onStart();
                }
            }

            public void onSuccess(String str) {
                boolean unused = InstallUtils.isDownloading = false;
                if (InstallUtils.mDownloadCallBack != null) {
                    InstallUtils.mDownloadCallBack.onComplete(InstallUtils.this.filePath);
                }
            }
        });
    }
}
