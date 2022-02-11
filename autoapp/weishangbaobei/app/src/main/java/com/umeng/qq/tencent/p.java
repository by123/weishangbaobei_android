package com.umeng.qq.tencent;

import android.content.Intent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class p {
    private static final String a = "openSDK_LOG.UIListenerManager";
    private static p b;
    private Map c = Collections.synchronizedMap(new HashMap());

    private p() {
        if (this.c == null) {
            this.c = Collections.synchronizedMap(new HashMap());
        }
    }

    public static p a() {
        if (b == null) {
            b = new p();
        }
        return b;
    }

    private j b(int i, j jVar) {
        return jVar;
    }

    public j a(int i) {
        String a2 = s.a(i);
        if (a2 == null) {
            return null;
        }
        return a(a2);
    }

    public j a(String str) {
        q qVar;
        if (str == null) {
            return null;
        }
        Map map = this.c;
        synchronized (this.c) {
            qVar = (q) this.c.get(str);
            this.c.remove(str);
        }
        if (qVar == null) {
            return null;
        }
        return qVar.b;
    }

    public Object a(int i, j jVar) {
        q qVar;
        String a2 = s.a(i);
        if (a2 == null) {
            return null;
        }
        Map map = this.c;
        synchronized (this.c) {
            qVar = (q) this.c.put(a2, new q(this, i, jVar));
        }
        if (qVar == null) {
            return null;
        }
        return qVar.b;
    }

    public Object a(String str, j jVar) {
        q qVar;
        int a2 = s.a(str);
        if (a2 == -1) {
            return null;
        }
        Map map = this.c;
        synchronized (this.c) {
            qVar = (q) this.c.put(str, new q(this, a2, jVar));
        }
        if (qVar == null) {
            return null;
        }
        return qVar.b;
    }

    public void a(Intent intent, j jVar) {
        r rVar;
        if (intent != null) {
            String stringExtra = intent.getStringExtra("key_action");
            if ("action_login".equals(stringExtra)) {
                int intExtra = intent.getIntExtra("key_error_code", 0);
                if (intExtra == 0) {
                    String stringExtra2 = intent.getStringExtra("key_response");
                    if (stringExtra2 != null) {
                        try {
                            jVar.a((Object) l.b(stringExtra2));
                            return;
                        } catch (JSONException unused) {
                            rVar = new r(-4, "服务器返回数据格式有误!", stringExtra2);
                        }
                    } else {
                        jVar.a((Object) new JSONObject());
                        return;
                    }
                } else {
                    jVar.a(new r(intExtra, intent.getStringExtra("key_error_msg"), intent.getStringExtra("key_error_detail")));
                    return;
                }
            } else if ("action_share".equals(stringExtra)) {
                String stringExtra3 = intent.getStringExtra("result");
                String stringExtra4 = intent.getStringExtra("response");
                if (!"cancel".equals(stringExtra3)) {
                    if ("error".equals(stringExtra3)) {
                        rVar = new r(-6, "unknown error", stringExtra4 + "");
                        jVar.a(rVar);
                        return;
                    } else if ("complete".equals(stringExtra3)) {
                        try {
                            jVar.a((Object) new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4));
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            rVar = new r(-4, "json error", stringExtra4 + "");
                        }
                    } else {
                        return;
                    }
                }
            } else {
                return;
            }
        }
        jVar.onCancel();
    }

    public boolean a(int i, int i2, Intent intent, j jVar) {
        r rVar;
        r rVar2;
        JSONObject jSONObject;
        j a2 = a(i);
        if (a2 == null) {
            if (jVar == null) {
                return false;
            }
            a2 = b(i, jVar);
        }
        if (i2 == -1) {
            if (intent == null) {
                a2.a(new r(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null."));
                return true;
            }
            String stringExtra = intent.getStringExtra("key_action");
            if ("action_login".equals(stringExtra)) {
                int intExtra = intent.getIntExtra("key_error_code", 0);
                if (intExtra == 0) {
                    String stringExtra2 = intent.getStringExtra("key_response");
                    if (stringExtra2 != null) {
                        try {
                            a2.a((Object) l.b(stringExtra2));
                        } catch (JSONException unused) {
                            rVar = new r(-4, "服务器返回数据格式有误!", stringExtra2);
                        }
                        return true;
                    }
                    jSONObject = new JSONObject();
                } else {
                    rVar2 = new r(intExtra, intent.getStringExtra("key_error_msg"), intent.getStringExtra("key_error_detail"));
                    a2.a(rVar2);
                    return true;
                }
            } else if ("action_share".equals(stringExtra)) {
                String stringExtra3 = intent.getStringExtra("result");
                String stringExtra4 = intent.getStringExtra("response");
                if (!"cancel".equals(stringExtra3)) {
                    if ("error".equals(stringExtra3)) {
                        rVar = new r(-6, "unknown error", stringExtra4 + "");
                        a2.a(rVar);
                        return true;
                    }
                    if ("complete".equals(stringExtra3)) {
                        try {
                            a2.a((Object) new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            a2.a(new r(-4, "json error", stringExtra4 + ""));
                        }
                    }
                    return true;
                }
            } else {
                int intExtra2 = intent.getIntExtra("key_error_code", 0);
                if (intExtra2 == 0) {
                    String stringExtra5 = intent.getStringExtra("key_response");
                    if (stringExtra5 != null) {
                        try {
                            a2.a((Object) l.b(stringExtra5));
                        } catch (JSONException unused2) {
                            rVar = new r(-4, "服务器返回数据格式有误!", stringExtra5);
                        }
                        return true;
                    }
                    jSONObject = new JSONObject();
                } else {
                    rVar2 = new r(intExtra2, intent.getStringExtra("key_error_msg"), intent.getStringExtra("key_error_detail"));
                    a2.a(rVar2);
                    return true;
                }
            }
            a2.a((Object) jSONObject);
            return true;
        }
        a2.onCancel();
        return true;
    }
}
