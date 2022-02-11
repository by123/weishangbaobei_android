package com.meiqia.core.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.meiqia.core.bean.MQClient;

public class i {
    private SharedPreferences a;
    private SharedPreferences.Editor b;

    public i(Context context) {
        this.a = context.getSharedPreferences("Meiqia", 0);
    }

    private void a(String str, long j) {
        this.b = this.a.edit();
        this.b.putLong(str, j);
        this.b.apply();
    }

    private void a(String str, String str2) {
        this.b = this.a.edit();
        this.b.putString(str, str2);
        this.b.apply();
    }

    private void a(String str, boolean z) {
        this.b = this.a.edit();
        this.b.putBoolean(str, z);
        this.b.apply();
    }

    private long b(String str, long j) {
        return this.a.getLong(str, j);
    }

    private String b(String str, String str2) {
        return this.a.getString(str, str2);
    }

    private boolean b(String str, boolean z) {
        return this.a.getBoolean(str, z);
    }

    private String h(MQClient mQClient, String str) {
        String str2;
        if (mQClient != null) {
            str2 = str + mQClient.getAppKey() + mQClient.getTrackId();
        } else {
            str2 = str + a();
        }
        return d.a(str2);
    }

    private String i(MQClient mQClient, String str) {
        String str2;
        if (mQClient != null) {
            str2 = str + mQClient.getAppKey();
        } else {
            str2 = str + a();
        }
        return d.a(str2);
    }

    public long a(MQClient mQClient) {
        return this.a.getLong(h(mQClient, "mq_last_conversation"), -1);
    }

    public String a() {
        return this.a.getString("meiqia_appkey", "");
    }

    public void a(MQClient mQClient, long j) {
        this.b = this.a.edit();
        this.b.putLong(h(mQClient, "mq_last_conversation"), j);
        this.b.apply();
    }

    public void a(MQClient mQClient, String str) {
        a(h(mQClient, "mq_queueing_robot_agent_id"), str);
    }

    public void a(MQClient mQClient, String str, boolean z) {
        a(h(mQClient, str), z);
    }

    public void a(MQClient mQClient, boolean z) {
        a(h(mQClient, "mq_is_schedulered"), z);
    }

    public void a(String str) {
        this.b = this.a.edit();
        this.b.putString("meiqia_appkey", str);
        this.b.apply();
    }

    public long b(MQClient mQClient) {
        return this.a.getLong(h(mQClient, "mq_last_msg_update_time"), System.currentTimeMillis());
    }

    public String b() {
        SharedPreferences sharedPreferences = this.a;
        return sharedPreferences.getString("meiqia_current_track_id_" + a(), (String) null);
    }

    public void b(MQClient mQClient, long j) {
        this.b = this.a.edit();
        this.b.putLong(h(mQClient, "mq_last_msg_update_time"), j);
        this.b.apply();
    }

    public void b(MQClient mQClient, String str) {
        this.b = this.a.edit();
        this.b.putString(h(mQClient, "mq_dev_infos"), str);
        this.b.apply();
    }

    public void b(MQClient mQClient, boolean z) {
        a(h(mQClient, "mq_isRegisterDeviceTokenSuc"), z);
    }

    public void b(String str) {
        this.b = this.a.edit();
        SharedPreferences.Editor editor = this.b;
        editor.putString("meiqia_current_track_id_" + a(), str);
        this.b.apply();
    }

    public boolean b(MQClient mQClient, String str, boolean z) {
        return b(h(mQClient, str), z);
    }

    public long c(MQClient mQClient) {
        return this.a.getLong(h(mQClient, "mq_last_ticket_msg_update_time"), System.currentTimeMillis());
    }

    @Deprecated
    public String c() {
        return b(d("mq_track_id"), (String) null);
    }

    public void c(MQClient mQClient, long j) {
        this.b = this.a.edit();
        this.b.putLong(h(mQClient, "mq_last_ticket_msg_update_time"), j);
        this.b.apply();
    }

    public void c(MQClient mQClient, String str) {
        this.b = this.a.edit();
        this.b.putString(h(mQClient, "mq_client_infos"), str);
        this.b.apply();
    }

    public void c(MQClient mQClient, boolean z) {
        a(i(mQClient, "mq_isFirstRefreshMQInquireForm"), z);
    }

    @Deprecated
    public void c(String str) {
        a("mq_user_id", str);
    }

    @Deprecated
    public String d() {
        return b(d("mq_visit_id"), (String) null);
    }

    @Deprecated
    public String d(String str) {
        return d.a(str + a() + i());
    }

    public void d(MQClient mQClient, long j) {
        a(h(mQClient, "mq_current_ticket_id"), j);
    }

    public void d(MQClient mQClient, String str) {
        this.b = this.a.edit();
        this.b.putString(h(mQClient, "mq_client_update_infos"), str);
        this.b.apply();
    }

    public boolean d(MQClient mQClient) {
        try {
            return b(h(mQClient, "mq_is_schedulered"), false);
        } catch (Exception unused) {
            return true;
        }
    }

    @Deprecated
    public String e() {
        return b(d("mq_visit_page_id"), (String) null);
    }

    public String e(MQClient mQClient) {
        return b(h(mQClient, "mq_queueing_robot_agent_id"), (String) null);
    }

    public void e(MQClient mQClient, long j) {
        this.b = this.a.edit();
        this.b.putLong(h(mQClient, "mq_conversation_onstop_time"), j);
        this.b.apply();
    }

    public void e(MQClient mQClient, String str) {
        a(h(mQClient, "mq_client_avatar_url"), str);
    }

    public long f(MQClient mQClient) {
        return b(h(mQClient, "mq_current_ticket_id"), -1);
    }

    @Deprecated
    public String f() {
        return b(d("mq_browser_id"), (String) null);
    }

    public void f(MQClient mQClient, long j) {
        a(h(mQClient, "mq_conversation_last_msg_time"), j);
    }

    public void f(MQClient mQClient, String str) {
        a(h(mQClient, "mq_register_device_token"), str);
    }

    @Deprecated
    public String g() {
        return b(d("mq_enterprise_id"), "1");
    }

    public String g(MQClient mQClient) {
        return this.a.getString(h(mQClient, "mq_dev_infos"), (String) null);
    }

    public void g(MQClient mQClient, long j) {
        a(h(mQClient, "mq_lastRefreshTime"), j);
    }

    public void g(MQClient mQClient, String str) {
        a(i(mQClient, "mq_enterprise_config"), str);
    }

    @Deprecated
    public String h() {
        return this.a.getString(d("mq_aes_key"), (String) null);
    }

    public String h(MQClient mQClient) {
        return this.a.getString(h(mQClient, "mq_client_infos"), "");
    }

    public void h(MQClient mQClient, long j) {
        a(i(mQClient, "last_refresh_ent_config"), j);
    }

    @Deprecated
    public String i() {
        return b("mq_user_id", "");
    }

    public String i(MQClient mQClient) {
        return this.a.getString(h(mQClient, "mq_client_update_infos"), "");
    }

    public String j(MQClient mQClient) {
        return b(h(mQClient, "mq_client_avatar_url"), "");
    }

    public long k(MQClient mQClient) {
        return this.a.getLong(h(mQClient, "mq_conversation_onstop_time"), System.currentTimeMillis());
    }

    public long l(MQClient mQClient) {
        return this.a.getLong(h(mQClient, "mq_conversation_last_msg_time"), k(mQClient));
    }

    public String m(MQClient mQClient) {
        return b(h(mQClient, "mq_register_device_token"), "");
    }

    public boolean n(MQClient mQClient) {
        return b(h(mQClient, "mq_isRegisterDeviceTokenSuc"), false);
    }

    public long o(MQClient mQClient) {
        return b(h(mQClient, "mq_lastRefreshTime"), 0);
    }

    public long p(MQClient mQClient) {
        return b(i(mQClient, "last_refresh_ent_config"), 0);
    }

    public String q(MQClient mQClient) {
        return b(i(mQClient, "mq_enterprise_config"), "");
    }

    public boolean r(MQClient mQClient) {
        return b(i(mQClient, "mq_isFirstRefreshMQInquireForm"), true);
    }
}
