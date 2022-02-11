package com.wx.assistants.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

public class ActionManager {
    private static final int REQUEST_BASE_CODE = 10201;
    private static ActionManager manager;
    private Activity activity;
    private Map<Integer, CallResult> callResults;
    private int mapKey = REQUEST_BASE_CODE;

    public interface CallResult {
        void onResult(Intent intent);
    }

    private ActionManager() {
    }

    public static ActionManager getInstance() {
        if (manager == null) {
            synchronized (ActionManager.class) {
                try {
                    if (manager == null) {
                        manager = new ActionManager();
                        manager.callResults = new HashMap();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<ActionManager> cls = ActionManager.class;
                        throw th;
                    }
                }
            }
        }
        return manager;
    }

    public ActionManager bindActivity(Activity activity2) {
        this.activity = activity2;
        return manager;
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (this.callResults.get(Integer.valueOf(i)) == null) {
            return false;
        }
        if (i2 == -1) {
            this.callResults.get(Integer.valueOf(i)).onResult(intent);
        } else {
            this.callResults.get(Integer.valueOf(i)).onResult((Intent) null);
        }
        this.callResults.remove(Integer.valueOf(i));
        return true;
    }

    public boolean startActivity(String str) {
        return startActivity(str, (CallResult) null);
    }

    public boolean startActivity(String str, Bundle bundle, CallResult callResult) {
        if (callResult == null) {
            try {
                this.activity.startActivity(new Intent(str));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            Intent intent = new Intent(str);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            Map<Integer, CallResult> map = this.callResults;
            int i = this.mapKey + 1;
            this.mapKey = i;
            map.put(Integer.valueOf(i), callResult);
            this.activity.startActivityForResult(intent, this.mapKey);
            return true;
        }
    }

    public boolean startActivity(String str, CallResult callResult) {
        return startActivity(str, (Bundle) null, callResult);
    }

    public void unbindActivity() {
        this.callResults.clear();
        this.callResults = null;
        this.activity = null;
        manager = null;
    }
}
