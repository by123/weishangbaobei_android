package com.meiqia.core;

import android.content.Context;
import android.content.Intent;
import com.meiqia.core.a.f;
import com.meiqia.core.a.i;
import com.meiqia.core.a.k;
import com.meiqia.core.bean.MQMessage;
import com.stub.StubApp;
import java.util.ArrayList;
import java.util.List;

class c {
    private static c a;
    private final i b;
    private final f c;
    private final MQMessageManager d;
    private Context e;
    private List<String> f = new ArrayList();

    private c(Context context) {
        this.e = context;
        this.b = new i(context);
        this.c = f.a(context);
        this.d = MQMessageManager.getInstance(context);
    }

    public static c a(Context context) {
        if (a == null) {
            synchronized (c.class) {
                try {
                    if (a == null) {
                        a = new c(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<c> cls = c.class;
                        throw th;
                    }
                }
            }
        }
        return a;
    }

    private void b(MQMessage mQMessage) {
        this.c.a(mQMessage);
        this.b.b(d.a, mQMessage.getCreated_on());
    }

    private boolean c(MQMessage mQMessage) {
        return mQMessage != null && !this.c.b(mQMessage) && !"client".equals(mQMessage.getFrom_type()) && !d(mQMessage);
    }

    private boolean d(MQMessage mQMessage) {
        String valueOf = String.valueOf(mQMessage.getId());
        if (this.f.contains(valueOf)) {
            return true;
        }
        this.f.add(valueOf);
        if (this.f.size() > 5) {
            this.f.remove(this.f.size() - 1);
        }
        return false;
    }

    private void e(MQMessage mQMessage) {
        this.d.addMQMessage(mQMessage);
        Intent intent = new Intent("new_msg_received_action");
        intent.putExtra("msgId", String.valueOf(mQMessage.getId()));
        k.a(this.e, intent);
        f.b("newMsg received : type = " + mQMessage.getContent_type() + "  content = " + mQMessage.getContent());
    }

    public void a(MQMessage mQMessage) {
        if (c(mQMessage)) {
            b(mQMessage);
            e(mQMessage);
        }
    }
}
