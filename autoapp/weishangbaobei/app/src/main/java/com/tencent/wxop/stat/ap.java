package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.event.i;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;

class ap implements Runnable {
    private Context a = null;
    private Map<String, Integer> b = null;
    private StatSpecifyReportedInfo c = null;

    public ap(Context context, Map<String, Integer> map, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.a = context;
        this.c = statSpecifyReportedInfo;
        if (map != null) {
            this.b = map;
        }
    }

    private NetworkMonitor a(String str, int i) {
        int i2;
        NetworkMonitor networkMonitor = new NetworkMonitor();
        Socket socket = new Socket();
        try {
            networkMonitor.setDomain(str);
            networkMonitor.setPort(i);
            long currentTimeMillis = System.currentTimeMillis();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(str, i);
            socket.connect(inetSocketAddress, BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
            networkMonitor.setMillisecondsConsume(System.currentTimeMillis() - currentTimeMillis);
            networkMonitor.setRemoteIp(inetSocketAddress.getAddress().getHostAddress());
            socket.close();
            try {
                socket.close();
            } catch (Throwable th) {
                StatServiceImpl.q.e(th);
            }
            i2 = 0;
        } catch (IOException e) {
            try {
                StatServiceImpl.q.e((Throwable) e);
                socket.close();
            } catch (Throwable th2) {
                StatServiceImpl.q.e(th2);
            }
        } catch (Throwable th3) {
            StatServiceImpl.q.e(th3);
        }
        networkMonitor.setStatusCode(i2);
        return networkMonitor;
        i2 = -1;
        networkMonitor.setStatusCode(i2);
        return networkMonitor;
        throw th;
    }

    private Map<String, Integer> a() {
        String str;
        HashMap hashMap = new HashMap();
        String a2 = StatConfig.a("__MTA_TEST_SPEED__", (String) null);
        if (!(a2 == null || a2.trim().length() == 0)) {
            for (String split : a2.split(";")) {
                String[] split2 = split.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                if (!(split2 == null || split2.length != 2 || (str = split2[0]) == null || str.trim().length() == 0)) {
                    try {
                        hashMap.put(str, Integer.valueOf(Integer.valueOf(split2[1]).intValue()));
                    } catch (NumberFormatException e) {
                        StatServiceImpl.q.e((Throwable) e);
                    }
                }
            }
        }
        return hashMap;
    }

    public void run() {
        StatLogger f;
        String str;
        try {
            if (this.b == null) {
                this.b = a();
            }
            if (this.b != null) {
                if (this.b.size() != 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (Map.Entry next : this.b.entrySet()) {
                        String str2 = (String) next.getKey();
                        if (str2 != null) {
                            if (str2.length() != 0) {
                                if (((Integer) next.getValue()) == null) {
                                    f = StatServiceImpl.q;
                                    str = "port is null for " + str2;
                                    f.w(str);
                                } else {
                                    jSONArray.put(a((String) next.getKey(), ((Integer) next.getValue()).intValue()).toJSONObject());
                                }
                            }
                        }
                        f = StatServiceImpl.q;
                        str = "empty domain name.";
                        f.w(str);
                    }
                    if (jSONArray.length() != 0) {
                        i iVar = new i(this.a, StatServiceImpl.a(this.a, false, this.c), this.c);
                        iVar.a(jSONArray.toString());
                        new aq(iVar).a();
                        return;
                    }
                    return;
                }
            }
            StatServiceImpl.q.i("empty domain list.");
        } catch (Throwable th) {
            StatServiceImpl.q.e(th);
        }
    }
}
