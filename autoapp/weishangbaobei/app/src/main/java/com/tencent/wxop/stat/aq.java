package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.StatLogger;
import com.tencent.wxop.stat.common.l;
import com.tencent.wxop.stat.common.q;
import com.tencent.wxop.stat.event.e;
import com.umeng.socialize.common.SocializeConstants;

class aq {
    private static volatile long f;
    /* access modifiers changed from: private */
    public e a;
    private StatReportStrategy b = null;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public Context d = null;
    private long e = System.currentTimeMillis();

    public aq(e eVar) {
        this.a = eVar;
        this.b = StatConfig.getStatSendStrategy();
        this.c = eVar.f();
        this.d = eVar.e();
    }

    private void a(h hVar) {
        i.b(StatServiceImpl.t).a(this.a, hVar);
    }

    private void b() {
        if (this.a.d() != null && this.a.d().isSendImmediately()) {
            this.b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.j && a.a(StatServiceImpl.t).e()) {
            this.b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.isDebugEnable()) {
            StatLogger f2 = StatServiceImpl.q;
            f2.i("strategy=" + this.b.name());
        }
        switch (ag.a[this.b.ordinal()]) {
            case 1:
                c();
                return;
            case 2:
                au.a(this.d).a(this.a, (h) null, this.c, false);
                if (StatConfig.isDebugEnable()) {
                    StatLogger f3 = StatServiceImpl.q;
                    f3.i("PERIOD currTime=" + this.e + ",nextPeriodSendTs=" + StatServiceImpl.c + ",difftime=" + (StatServiceImpl.c - this.e));
                }
                if (StatServiceImpl.c == 0) {
                    StatServiceImpl.c = q.a(this.d, "last_period_ts", 0);
                    if (this.e > StatServiceImpl.c) {
                        StatServiceImpl.e(this.d);
                    }
                    long sendPeriodMinutes = this.e + ((long) (StatConfig.getSendPeriodMinutes() * 60 * SocializeConstants.CANCLE_RESULTCODE));
                    if (StatServiceImpl.c > sendPeriodMinutes) {
                        StatServiceImpl.c = sendPeriodMinutes;
                    }
                    d.a(this.d).a();
                }
                if (StatConfig.isDebugEnable()) {
                    StatLogger f4 = StatServiceImpl.q;
                    f4.i("PERIOD currTime=" + this.e + ",nextPeriodSendTs=" + StatServiceImpl.c + ",difftime=" + (StatServiceImpl.c - this.e));
                }
                if (this.e > StatServiceImpl.c) {
                    StatServiceImpl.e(this.d);
                    return;
                }
                return;
            case 3:
            case 4:
                au.a(this.d).a(this.a, (h) null, this.c, false);
                return;
            case 5:
                au.a(this.d).a(this.a, (h) new ar(this), this.c, true);
                return;
            case 6:
                if (a.a(StatServiceImpl.t).c() == 1) {
                    c();
                    return;
                } else {
                    au.a(this.d).a(this.a, (h) null, this.c, false);
                    return;
                }
            case 7:
                if (l.e(this.d)) {
                    a((h) new as(this));
                    return;
                }
                return;
            default:
                StatLogger f5 = StatServiceImpl.q;
                f5.error((Object) "Invalid stat strategy:" + StatConfig.getStatSendStrategy());
                return;
        }
    }

    private void c() {
        if (au.b().a <= 0 || !StatConfig.l) {
            a((h) new at(this));
            return;
        }
        au.b().a(this.a, (h) null, this.c, true);
        au.b().a(-1);
    }

    private boolean d() {
        if (StatConfig.h <= 0) {
            return false;
        }
        if (this.e > StatServiceImpl.h) {
            StatServiceImpl.g.clear();
            long unused = StatServiceImpl.h = this.e + StatConfig.i;
            if (StatConfig.isDebugEnable()) {
                StatLogger f2 = StatServiceImpl.q;
                f2.i("clear methodsCalledLimitMap, nextLimitCallClearTime=" + StatServiceImpl.h);
            }
        }
        Integer valueOf = Integer.valueOf(this.a.a().a());
        Integer num = (Integer) StatServiceImpl.g.get(valueOf);
        if (num != null) {
            StatServiceImpl.g.put(valueOf, Integer.valueOf(num.intValue() + 1));
            if (num.intValue() <= StatConfig.h) {
                return false;
            }
            if (StatConfig.isDebugEnable()) {
                StatLogger f3 = StatServiceImpl.q;
                f3.e((Object) "event " + this.a.g() + " was discard, cause of called limit, current:" + num + ", limit:" + StatConfig.h + ", period:" + StatConfig.i + " ms");
            }
            return true;
        }
        StatServiceImpl.g.put(valueOf, 1);
        return false;
    }

    public void a() {
        if (!d()) {
            if (StatConfig.m > 0 && this.e >= f) {
                StatServiceImpl.flushDataToDB(this.d);
                f = this.e + StatConfig.n;
                if (StatConfig.isDebugEnable()) {
                    StatLogger f2 = StatServiceImpl.q;
                    f2.i("nextFlushTime=" + f);
                }
            }
            if (a.a(this.d).f()) {
                if (StatConfig.isDebugEnable()) {
                    StatLogger f3 = StatServiceImpl.q;
                    f3.i("sendFailedCount=" + StatServiceImpl.a);
                }
                if (!StatServiceImpl.a()) {
                    b();
                    return;
                }
                au.a(this.d).a(this.a, (h) null, this.c, false);
                if (this.e - StatServiceImpl.b > 1800000) {
                    StatServiceImpl.d(this.d);
                    return;
                }
                return;
            }
            au.a(this.d).a(this.a, (h) null, this.c, false);
        }
    }
}
