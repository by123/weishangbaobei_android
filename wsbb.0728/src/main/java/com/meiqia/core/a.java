package com.meiqia.core;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.meiqia.core.callback.AppLifecycleListener;
import com.stub.StubApp;

@TargetApi(14)
public class a implements Application.ActivityLifecycleCallbacks {
    private int a = 0;
    private AppLifecycleListener b;

    public a(Context context, AppLifecycleListener appLifecycleListener) {
        this.b = appLifecycleListener;
        a(context);
    }

    private void a(Context context) {
        ((Application) StubApp.getOrigApplicationContext(context.getApplicationContext())).registerActivityLifecycleCallbacks(this);
    }

    public boolean a() {
        return this.a == 0;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (a()) {
            this.b.foreground();
        }
        this.a++;
    }

    public void onActivityStopped(Activity activity) {
        this.a--;
        if (a()) {
            this.b.background();
        }
    }
}
