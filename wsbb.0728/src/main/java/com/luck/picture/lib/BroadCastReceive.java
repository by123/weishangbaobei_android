package com.luck.picture.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.entity.EventEntity;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.rxbus2.RxBus;
import java.util.List;

public class BroadCastReceive extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("wq", "action == " + action);
        if (TextUtils.equals("com.fb.jjyyzjy.videocomplex.PICTURE_CHANGED", action)) {
            List list = (List) new Gson().fromJson(intent.getStringExtra("picture_data"), new TypeToken<List<LocalMedia>>() {
            }.getType());
            Log.e("wq", "localMedialist == " + list);
            if (list != null) {
                Log.e("wq", "localMedialist == " + list.size());
                RxBus.getDefault().post(new EventEntity(1, (List<LocalMedia>) list));
            }
        }
    }
}
