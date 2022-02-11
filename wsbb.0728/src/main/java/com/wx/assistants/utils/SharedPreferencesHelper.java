package com.wx.assistants.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.WeakHashMap;

public class SharedPreferencesHelper {
    private static WeakHashMap<String, SharedPreferencesHelper> spMap = new WeakHashMap<>();
    private SharedPreferences.Editor editor = this.sp.edit();
    private SharedPreferences sp;

    public SharedPreferencesHelper(Context context, String str) {
        this.sp = context.getSharedPreferences(str, 0);
    }

    public static SharedPreferencesHelper getInstance(Context context, String str) {
        SharedPreferencesHelper sharedPreferencesHelper = spMap.get(str);
        if (sharedPreferencesHelper != null) {
            return sharedPreferencesHelper;
        }
        SharedPreferencesHelper sharedPreferencesHelper2 = new SharedPreferencesHelper(context, str);
        spMap.put(str, sharedPreferencesHelper2);
        return sharedPreferencesHelper2;
    }

    public boolean contains(String str) {
        return this.sp.contains(str);
    }

    public boolean getBoolean(String str) {
        return this.sp.getBoolean(str, false);
    }

    public int getInt(String str) {
        return this.sp.getInt(str, 0);
    }

    public long getLong(String str) {
        return this.sp.getLong(str, 0);
    }

    public String getValue(String str) {
        return this.sp.getString(str, (String) null);
    }

    public void putBoolean(String str, boolean z) {
        this.editor = this.sp.edit();
        this.editor.putBoolean(str, z);
        this.editor.commit();
    }

    public void putInt(String str, int i) {
        this.editor = this.sp.edit();
        this.editor.putInt(str, i);
        this.editor.commit();
    }

    public void putLong(String str, long j) {
        this.editor = this.sp.edit();
        this.editor.putLong(str, j);
        this.editor.commit();
    }

    public void putValue(String str, String str2) {
        this.editor = this.sp.edit();
        this.editor.putString(str, str2);
        this.editor.commit();
    }

    public boolean remove(String str) {
        this.editor = this.sp.edit();
        this.editor.remove(str);
        return this.editor.commit();
    }
}
