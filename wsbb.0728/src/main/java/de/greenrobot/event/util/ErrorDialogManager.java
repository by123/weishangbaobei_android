package de.greenrobot.event.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import de.greenrobot.event.EventBus;

public class ErrorDialogManager {
    public static final String KEY_EVENT_TYPE_ON_CLOSE = "de.greenrobot.eventbus.errordialog.event_type_on_close";
    public static final String KEY_FINISH_AFTER_DIALOG = "de.greenrobot.eventbus.errordialog.finish_after_dialog";
    public static final String KEY_ICON_ID = "de.greenrobot.eventbus.errordialog.icon_id";
    public static final String KEY_MESSAGE = "de.greenrobot.eventbus.errordialog.message";
    public static final String KEY_TITLE = "de.greenrobot.eventbus.errordialog.title";
    protected static final String TAG_ERROR_DIALOG = "de.greenrobot.eventbus.error_dialog";
    protected static final String TAG_ERROR_DIALOG_MANAGER = "de.greenrobot.eventbus.error_dialog_manager";
    public static ErrorDialogFragmentFactory<?> factory;

    @TargetApi(11)
    public static class HoneycombManagerFragment extends Fragment {
        protected Bundle argumentsForErrorDialog;
        private EventBus eventBus;
        private Object executionScope;
        protected boolean finishAfterDialog;

        public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            HoneycombManagerFragment honeycombManagerFragment = (HoneycombManagerFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER);
            if (honeycombManagerFragment == null) {
                honeycombManagerFragment = new HoneycombManagerFragment();
                fragmentManager.beginTransaction().add(honeycombManagerFragment, ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER).commit();
                fragmentManager.executePendingTransactions();
            }
            honeycombManagerFragment.finishAfterDialog = z;
            honeycombManagerFragment.argumentsForErrorDialog = bundle;
            honeycombManagerFragment.executionScope = obj;
        }

        public void onEventMainThread(ThrowableFailureEvent throwableFailureEvent) {
            if (ErrorDialogManager.isInExecutionScope(this.executionScope, throwableFailureEvent)) {
                ErrorDialogManager.checkLogException(throwableFailureEvent);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG);
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                DialogFragment dialogFragment2 = (DialogFragment) ErrorDialogManager.factory.prepareErrorFragment(throwableFailureEvent, this.finishAfterDialog, this.argumentsForErrorDialog);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, ErrorDialogManager.TAG_ERROR_DIALOG);
                }
            }
        }

        public void onPause() {
            this.eventBus.unregister(this);
            super.onPause();
        }

        public void onResume() {
            super.onResume();
            this.eventBus = ErrorDialogManager.factory.config.getEventBus();
            this.eventBus.register(this);
        }
    }

    public static class SupportManagerFragment extends android.support.v4.app.Fragment {
        protected Bundle argumentsForErrorDialog;
        private EventBus eventBus;
        private Object executionScope;
        protected boolean finishAfterDialog;
        private boolean skipRegisterOnNextResume;

        public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
            android.support.v4.app.FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            SupportManagerFragment findFragmentByTag = supportFragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER);
            if (findFragmentByTag == null) {
                findFragmentByTag = new SupportManagerFragment();
                supportFragmentManager.beginTransaction().add(findFragmentByTag, ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER).commit();
                supportFragmentManager.executePendingTransactions();
            }
            findFragmentByTag.finishAfterDialog = z;
            findFragmentByTag.argumentsForErrorDialog = bundle;
            findFragmentByTag.executionScope = obj;
        }

        public void onCreate(Bundle bundle) {
            ErrorDialogManager.super.onCreate(bundle);
            this.eventBus = ErrorDialogManager.factory.config.getEventBus();
            this.eventBus.register(this);
            this.skipRegisterOnNextResume = true;
        }

        public void onEventMainThread(ThrowableFailureEvent throwableFailureEvent) {
            if (ErrorDialogManager.isInExecutionScope(this.executionScope, throwableFailureEvent)) {
                ErrorDialogManager.checkLogException(throwableFailureEvent);
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                android.support.v4.app.DialogFragment findFragmentByTag = fragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG);
                if (findFragmentByTag != null) {
                    findFragmentByTag.dismiss();
                }
                android.support.v4.app.DialogFragment prepareErrorFragment = ErrorDialogManager.factory.prepareErrorFragment(throwableFailureEvent, this.finishAfterDialog, this.argumentsForErrorDialog);
                if (prepareErrorFragment != null) {
                    prepareErrorFragment.show(fragmentManager, ErrorDialogManager.TAG_ERROR_DIALOG);
                }
            }
        }

        public void onPause() {
            this.eventBus.unregister(this);
            ErrorDialogManager.super.onPause();
        }

        public void onResume() {
            ErrorDialogManager.super.onResume();
            if (this.skipRegisterOnNextResume) {
                this.skipRegisterOnNextResume = false;
                return;
            }
            this.eventBus = ErrorDialogManager.factory.config.getEventBus();
            this.eventBus.register(this);
        }
    }

    public static void attachTo(Activity activity) {
        attachTo(activity, false, (Bundle) null);
    }

    public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
        if (factory == null) {
            throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
        } else if (isSupportActivity(activity)) {
            SupportManagerFragment.attachTo(activity, obj, z, bundle);
        } else {
            HoneycombManagerFragment.attachTo(activity, obj, z, bundle);
        }
    }

    public static void attachTo(Activity activity, boolean z) {
        attachTo(activity, z, (Bundle) null);
    }

    public static void attachTo(Activity activity, boolean z, Bundle bundle) {
        attachTo(activity, activity.getClass(), z, bundle);
    }

    protected static void checkLogException(ThrowableFailureEvent throwableFailureEvent) {
        if (factory.config.logExceptions) {
            String str = factory.config.tagForLoggingExceptions;
            if (str == null) {
                str = EventBus.TAG;
            }
            Log.i(str, "Error dialog manager received exception", throwableFailureEvent.throwable);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r2.getExecutionScope();
     */
    public static boolean isInExecutionScope(Object obj, ThrowableFailureEvent throwableFailureEvent) {
        Object executionScope;
        return throwableFailureEvent == null || executionScope == null || executionScope.equals(obj);
    }

    private static boolean isSupportActivity(Activity activity) {
        String name;
        Class cls = activity.getClass();
        do {
            cls = cls.getSuperclass();
            if (cls != null) {
                name = cls.getName();
                if (name.equals("android.support.v4.app.FragmentActivity")) {
                    return true;
                }
                if (name.startsWith("com.actionbarsherlock.app") && (name.endsWith(".SherlockActivity") || name.endsWith(".SherlockListActivity") || name.endsWith(".SherlockPreferenceActivity"))) {
                    throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + name);
                }
            } else {
                throw new RuntimeException("Illegal activity type: " + activity.getClass());
            }
        } while (!name.equals("android.app.Activity"));
        if (Build.VERSION.SDK_INT >= 11) {
            return false;
        }
        throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
    }
}
