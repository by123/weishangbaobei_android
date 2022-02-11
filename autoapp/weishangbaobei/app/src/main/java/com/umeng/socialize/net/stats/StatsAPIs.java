package com.umeng.socialize.net.stats;

import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.stats.AuthStatsRequest;
import com.umeng.socialize.net.stats.ShareStatsRequest;
import com.umeng.socialize.net.stats.UserInfoStatsRequest;
import com.umeng.socialize.net.stats.cache.UMCacheListener;
import com.umeng.socialize.net.stats.cache.b;
import com.umeng.socialize.net.stats.cache.c;
import com.umeng.socialize.utils.Log;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.json.JSONArray;

public class StatsAPIs {
    /* access modifiers changed from: private */
    public static SocializeClient mClient = new SocializeClient();

    public static SocializeReseponse shareboardStats(StatsRequest statsRequest) {
        return mClient.execute(statsRequest);
    }

    public static SocializeReseponse shareStatsStart(ShareStatsRequest shareStatsRequest) {
        shareStatsRequest.setRequestPath(ShareStatsRequest.ShareLifecycle.START);
        SocializeReseponse execute = mClient.execute(shareStatsRequest);
        handleResponseForCache(false, shareStatsRequest, execute);
        return execute;
    }

    public static SocializeReseponse shareStatsEnd(ShareStatsRequest shareStatsRequest) {
        shareStatsRequest.setRequestPath(ShareStatsRequest.ShareLifecycle.END);
        SocializeReseponse execute = mClient.execute(shareStatsRequest);
        handleResponseForCache(false, shareStatsRequest, execute);
        return execute;
    }

    public static SocializeReseponse authStatsStart(AuthStatsRequest authStatsRequest) {
        authStatsRequest.setRequestPath(AuthStatsRequest.AuthLifecycle.START);
        SocializeReseponse execute = mClient.execute(authStatsRequest);
        handleResponseForCache(false, authStatsRequest, execute);
        return execute;
    }

    public static SocializeReseponse authStatsEnd(AuthStatsRequest authStatsRequest) {
        authStatsRequest.setRequestPath(AuthStatsRequest.AuthLifecycle.END);
        SocializeReseponse execute = mClient.execute(authStatsRequest);
        handleResponseForCache(false, authStatsRequest, execute);
        return execute;
    }

    public static SocializeReseponse userInfoStatsStart(UserInfoStatsRequest userInfoStatsRequest) {
        userInfoStatsRequest.setRequestPath(UserInfoStatsRequest.GetUserInfoLifecycle.START);
        SocializeReseponse execute = mClient.execute(userInfoStatsRequest);
        handleResponseForCache(false, userInfoStatsRequest, execute);
        return execute;
    }

    public static SocializeReseponse userInfoStatsEnd(UserInfoStatsRequest userInfoStatsRequest) {
        userInfoStatsRequest.setRequestPath(UserInfoStatsRequest.GetUserInfoLifecycle.END);
        SocializeReseponse execute = mClient.execute(userInfoStatsRequest);
        handleResponseForCache(false, userInfoStatsRequest, execute);
        return execute;
    }

    public static SocializeReseponse dauStats(DauStatsRequest dauStatsRequest) {
        SocializeReseponse execute = mClient.execute(dauStatsRequest);
        handleResponseForCache(true, dauStatsRequest, execute);
        return execute;
    }

    private static void handleResponseForCache(boolean z, SocializeRequest socializeRequest, SocializeReseponse socializeReseponse) {
        if (socializeReseponse == null || !socializeReseponse.isHttpOK()) {
            try {
                String query = new URL(socializeRequest.toGetUrl()).getQuery();
                Log.d("StatsAPIs", "save stats log");
                b.a().a(query, (UMCacheListener) null);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (z) {
            Log.d("StatsAPIs", "read stats log");
            b.a().a((UMCacheListener) new UMCacheListener() {
                public void onResult(boolean z, c.a aVar) {
                    if (aVar != null) {
                        JSONArray jSONArray = new JSONArray();
                        List<String> b = aVar.b();
                        for (int i = 0; i < b.size(); i++) {
                            jSONArray.put(b.get(i));
                        }
                        StatsAPIs.sendStatsCache(aVar.a(), jSONArray);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void sendStatsCache(final String str, JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() > 0) {
            Log.d("StatsAPIs", "send stats log:" + jSONArray.toString());
            final StatsLogRequest statsLogRequest = new StatsLogRequest(SocializeReseponse.class);
            statsLogRequest.addStringParams("data", jSONArray.toString());
            QueuedWork.runInBack(new Runnable() {
                public void run() {
                    SocializeReseponse execute = StatsAPIs.mClient.execute(statsLogRequest);
                    if (execute != null && execute.isHttpOK()) {
                        b.a().b(str, (UMCacheListener) null);
                        Log.d("StatsAPIs", "delete stats log" + str);
                    }
                }
            }, true);
        }
    }
}
