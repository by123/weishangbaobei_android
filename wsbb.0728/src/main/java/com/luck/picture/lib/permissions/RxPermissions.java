package com.luck.picture.lib.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;

public class RxPermissions {
    public static final String TAG = "RxPermissions";
    public static final Object TRIGGER = new Object();
    public RxPermissionsFragment mRxPermissionsFragment;

    public RxPermissions(@NonNull Activity activity) {
        this.mRxPermissionsFragment = getRxPermissionsFragment(activity);
    }

    private RxPermissionsFragment findRxPermissionsFragment(Activity activity) {
        return (RxPermissionsFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }

    private RxPermissionsFragment getRxPermissionsFragment(Activity activity) {
        RxPermissionsFragment rxPermissionsFragment;
        RxPermissionsFragment rxPermissionsFragment2;
        try {
            rxPermissionsFragment2 = findRxPermissionsFragment(activity);
            if (rxPermissionsFragment2 == null) {
                try {
                    rxPermissionsFragment = new RxPermissionsFragment();
                    try {
                        FragmentManager fragmentManager = activity.getFragmentManager();
                        fragmentManager.beginTransaction().add(rxPermissionsFragment, TAG).commitAllowingStateLoss();
                        fragmentManager.executePendingTransactions();
                        return rxPermissionsFragment;
                    } catch (Exception e) {
                        e = e;
                    }
                } catch (Exception e2) {
                    e = e2;
                    rxPermissionsFragment = rxPermissionsFragment2;
                    e.printStackTrace();
                    rxPermissionsFragment2 = rxPermissionsFragment;
                    return rxPermissionsFragment2;
                }
            }
        } catch (Exception e3) {
            e = e3;
            rxPermissionsFragment = null;
        }
        return rxPermissionsFragment2;
    }

    private Observable<?> oneOf(Observable<?> observable, Observable<?> observable2) {
        return observable == null ? Observable.just(TRIGGER) : Observable.merge(observable, observable2);
    }

    private Observable<?> pending(String... strArr) {
        for (String containsByPermission : strArr) {
            if (!this.mRxPermissionsFragment.containsByPermission(containsByPermission)) {
                return Observable.empty();
            }
        }
        return Observable.just(TRIGGER);
    }

    /* access modifiers changed from: private */
    public Observable<Permission> request(Observable<?> observable, final String... strArr) {
        if (strArr != null && strArr.length != 0) {
            return oneOf(observable, pending(strArr)).flatMap(new Function<Object, Observable<Permission>>() {
                public Observable<Permission> apply(Object obj) throws Exception {
                    return RxPermissions.this.requestImplementation(strArr);
                }
            });
        }
        throw new IllegalArgumentException("RxPermissions.request/requestEach requires at least one input permission");
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    public Observable<Permission> requestImplementation(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        ArrayList arrayList2 = new ArrayList();
        for (String str : strArr) {
            this.mRxPermissionsFragment.log("Requesting permission " + str);
            if (isGranted(str)) {
                arrayList.add(Observable.just(new Permission(str, true, false)));
            } else if (isRevoked(str)) {
                arrayList.add(Observable.just(new Permission(str, false, false)));
            } else {
                PublishSubject<Permission> subjectByPermission = this.mRxPermissionsFragment.getSubjectByPermission(str);
                if (subjectByPermission == null) {
                    arrayList2.add(str);
                    subjectByPermission = PublishSubject.create();
                    this.mRxPermissionsFragment.setSubjectForPermission(str, subjectByPermission);
                }
                arrayList.add(subjectByPermission);
            }
        }
        if (!arrayList2.isEmpty()) {
            requestPermissionsFromFragment((String[]) arrayList2.toArray(new String[arrayList2.size()]));
        }
        return Observable.concat(Observable.fromIterable(arrayList));
    }

    @TargetApi(23)
    private boolean shouldShowRequestPermissionRationaleImplementation(Activity activity, String... strArr) {
        for (String str : strArr) {
            if (!isGranted(str) && !activity.shouldShowRequestPermissionRationale(str)) {
                return false;
            }
        }
        return true;
    }

    public <T> ObservableTransformer<T, Boolean> ensure(final String... strArr) {
        return new ObservableTransformer<T, Boolean>() {
            public ObservableSource<Boolean> apply(Observable<T> observable) {
                return RxPermissions.this.request(observable, strArr).buffer(strArr.length).flatMap(new Function<List<Permission>, ObservableSource<Boolean>>() {
                    public ObservableSource<Boolean> apply(List<Permission> list) throws Exception {
                        if (list.isEmpty()) {
                            return Observable.empty();
                        }
                        for (Permission permission : list) {
                            if (!permission.granted) {
                                return Observable.just(false);
                            }
                        }
                        return Observable.just(true);
                    }
                });
            }
        };
    }

    public <T> ObservableTransformer<T, Permission> ensureEach(final String... strArr) {
        return new ObservableTransformer<T, Permission>() {
            public ObservableSource<Permission> apply(Observable<T> observable) {
                return RxPermissions.this.request(observable, strArr);
            }
        };
    }

    public boolean isGranted(String str) {
        return !isMarshmallow() || this.mRxPermissionsFragment.isGranted(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public boolean isRevoked(String str) {
        return isMarshmallow() && this.mRxPermissionsFragment.isRevoked(str);
    }

    /* access modifiers changed from: package-private */
    public void onRequestPermissionsResult(String[] strArr, int[] iArr) {
        this.mRxPermissionsFragment.onRequestPermissionsResult(strArr, iArr, new boolean[strArr.length]);
    }

    public Observable<Boolean> request(String... strArr) {
        return Observable.just(TRIGGER).compose(ensure(strArr));
    }

    public Observable<Permission> requestEach(String... strArr) {
        return Observable.just(TRIGGER).compose(ensureEach(strArr));
    }

    /* access modifiers changed from: package-private */
    @TargetApi(23)
    public void requestPermissionsFromFragment(String[] strArr) {
        RxPermissionsFragment rxPermissionsFragment = this.mRxPermissionsFragment;
        rxPermissionsFragment.log("requestPermissionsFromFragment " + TextUtils.join(", ", strArr));
        this.mRxPermissionsFragment.requestPermissions(strArr);
    }

    public void setLogging(boolean z) {
        this.mRxPermissionsFragment.setLogging(z);
    }

    public Observable<Boolean> shouldShowRequestPermissionRationale(Activity activity, String... strArr) {
        return !isMarshmallow() ? Observable.just(false) : Observable.just(Boolean.valueOf(shouldShowRequestPermissionRationaleImplementation(activity, strArr)));
    }
}
