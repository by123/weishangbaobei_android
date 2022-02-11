package com.maning.updatelibrary.utils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

public class OnActResultEventDispatcherFragment extends Fragment {
    public static final String TAG = "on_act_result_event_dispatcher";
    private SparseArray<ActForResultCallback> mCallbacks = new SparseArray<>();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public void startForResult(Intent intent, ActForResultCallback actForResultCallback) {
        this.mCallbacks.put(actForResultCallback.hashCode(), actForResultCallback);
        startActivityForResult(intent, actForResultCallback.hashCode());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        ActForResultCallback actForResultCallback = this.mCallbacks.get(i);
        this.mCallbacks.remove(i);
        if (actForResultCallback != null) {
            actForResultCallback.onActivityResult(i2, intent);
        }
    }
}
