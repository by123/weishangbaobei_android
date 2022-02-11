package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    public static final String a = "lng";
    public static final String b = "lat";
    public static final String c = "ts";
    public static final long d = 30000;
    public static final int e = 200;
    private static final String f = "UMSysLocationCache";
    /* access modifiers changed from: private */
    public static boolean g = true;

    public static void a(final Context context) {
        ULog.i(f, "begin location");
        if (context != null) {
            try {
                new Thread(new Runnable() {
                    public void run() {
                        while (c.g) {
                            try {
                                JSONArray b = c.b(context);
                                if (b == null || b.length() < 200) {
                                    ULog.i(c.f, "location status is ok, time is " + System.currentTimeMillis());
                                    final b bVar = new b(context);
                                    bVar.a(new d() {
                                        public void a(Location location) {
                                            if (location != null) {
                                                double longitude = location.getLongitude();
                                                double latitude = location.getLatitude();
                                                float accuracy = location.getAccuracy();
                                                double altitude = location.getAltitude();
                                                ULog.i(c.f, "lon is " + longitude + ", lat is " + latitude + ", acc is " + accuracy + ", alt is " + altitude);
                                                if (!(longitude == 0.0d || latitude == 0.0d)) {
                                                    long time = location.getTime();
                                                    JSONObject jSONObject = new JSONObject();
                                                    try {
                                                        jSONObject.put("lng", longitude);
                                                        jSONObject.put("lat", latitude);
                                                        jSONObject.put("ts", time);
                                                        jSONObject.put("acc", (double) accuracy);
                                                        jSONObject.put("alt", altitude);
                                                    } catch (JSONException e) {
                                                        ULog.i(c.f, "e is " + e);
                                                    } catch (Throwable th) {
                                                        ULog.i(c.f, "" + th.getMessage());
                                                    }
                                                    ULog.i(c.f, "locationJSONObject is " + jSONObject.toString());
                                                    SharedPreferences sharedPreferences = context.getSharedPreferences(a.p, 0);
                                                    if (sharedPreferences != null) {
                                                        String string = sharedPreferences.getString(a.r, "");
                                                        String string2 = sharedPreferences.getString(a.s, "");
                                                        ULog.i(c.f, "--->>> get lon is " + string + ", lat is " + string2);
                                                        if (TextUtils.isEmpty(string) || Double.parseDouble(string) != longitude || TextUtils.isEmpty(string2) || Double.parseDouble(string2) != latitude) {
                                                            JSONArray b2 = c.b(context);
                                                            if (b2 == null) {
                                                                b2 = new JSONArray();
                                                            }
                                                            b2.put(jSONObject);
                                                            SharedPreferences.Editor edit = sharedPreferences.edit();
                                                            edit.putString(a.r, String.valueOf(longitude));
                                                            edit.putString(a.s, String.valueOf(latitude));
                                                            edit.putString(a.q, b2.toString());
                                                            edit.commit();
                                                            ULog.i(c.f, "location put is ok~~");
                                                        } else {
                                                            ULog.i(c.f, "location same");
                                                        }
                                                    }
                                                }
                                            }
                                            bVar.a();
                                        }
                                    });
                                    try {
                                        Thread.sleep(c.d);
                                    } catch (Exception e) {
                                    }
                                } else {
                                    boolean unused = c.g = false;
                                    return;
                                }
                            } catch (Throwable th) {
                                return;
                            }
                        }
                    }
                }).start();
            } catch (Exception e2) {
            }
        }
    }

    public static JSONArray b(Context context) {
        JSONArray jSONArray = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences(a.p, 0);
        if (sharedPreferences != null) {
            try {
                String string = sharedPreferences.getString(a.q, "");
                if (!TextUtils.isEmpty(string)) {
                    jSONArray = new JSONArray(string);
                }
            } catch (JSONException e2) {
                ULog.i(f, "e is " + e2);
            } catch (Throwable th) {
                ULog.i(f, "e is " + th);
            }
            if (jSONArray != null) {
                ULog.i(f, "get json str is " + jSONArray.toString());
            }
        }
        return jSONArray;
    }

    public static void c(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(a.p, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(a.q, "");
                edit.commit();
                ULog.i(f, "delete is ok~~");
            }
        } catch (Throwable th) {
        }
    }
}
