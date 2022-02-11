package com.luck.picture.lib.permissions;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.stub.StubApp;
import io.reactivex.subjects.PublishSubject;
import java.util.HashMap;
import java.util.Map;

public class RxPermissionsFragment extends Fragment {
    private static final int PERMISSIONS_REQUEST_CODE = 42;
    private boolean mLogging;
    private Map<String, PublishSubject<Permission>> mSubjects = new HashMap();

    public boolean containsByPermission(@NonNull String str) {
        return this.mSubjects.containsKey(str);
    }

    public PublishSubject<Permission> getSubjectByPermission(@NonNull String str) {
        return (PublishSubject) this.mSubjects.get(str);
    }

    /* access modifiers changed from: package-private */
    @TargetApi(23)
    public boolean isGranted(String str) {
        return getActivity().checkSelfPermission(str) == 0;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(23)
    public boolean isRevoked(String str) {
        return getActivity().getPackageManager().isPermissionRevokedByPolicy(str, getActivity().getPackageName());
    }

    /* access modifiers changed from: package-private */
    public void log(String str) {
        if (this.mLogging) {
            Log.d(RxPermissions.TAG, str);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        StubApp.interface22(i, strArr, iArr);
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 42) {
            boolean[] zArr = new boolean[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                zArr[i2] = shouldShowRequestPermissionRationale(strArr[i2]);
            }
            onRequestPermissionsResult(strArr, iArr, zArr);
        }
    }

    /* access modifiers changed from: package-private */
    public void onRequestPermissionsResult(String[] strArr, int[] iArr, boolean[] zArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            log("onRequestPermissionsResult  " + strArr[i]);
            PublishSubject publishSubject = (PublishSubject) this.mSubjects.get(strArr[i]);
            if (publishSubject == null) {
                Log.e(RxPermissions.TAG, "RxPermissions.onRequestPermissionsResult invoked but didn't find the corresponding permission request.");
                return;
            }
            this.mSubjects.remove(strArr[i]);
            publishSubject.onNext(new Permission(strArr[i], iArr[i] == 0, zArr[i]));
            publishSubject.onComplete();
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(23)
    public void requestPermissions(@NonNull String[] strArr) {
        requestPermissions(strArr, 42);
    }

    public void setLogging(boolean z) {
        this.mLogging = z;
    }

    public PublishSubject<Permission> setSubjectForPermission(@NonNull String str, @NonNull PublishSubject<Permission> publishSubject) {
        return (PublishSubject) this.mSubjects.put(str, publishSubject);
    }
}
