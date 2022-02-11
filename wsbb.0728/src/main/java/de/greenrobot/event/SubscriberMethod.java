package de.greenrobot.event;

import java.lang.reflect.Method;

final class SubscriberMethod {
    final Class<?> eventType;
    final Method method;
    String methodString;
    final ThreadMode threadMode;

    SubscriberMethod(Method method2, ThreadMode threadMode2, Class<?> cls) {
        this.method = method2;
        this.threadMode = threadMode2;
        this.eventType = cls;
    }

    private void checkMethodString() {
        synchronized (this) {
            if (this.methodString == null) {
                StringBuilder sb = new StringBuilder(64);
                sb.append(this.method.getDeclaringClass().getName());
                sb.append('#');
                sb.append(this.method.getName());
                sb.append('(');
                sb.append(this.eventType.getName());
                this.methodString = sb.toString();
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SubscriberMethod)) {
            return false;
        }
        checkMethodString();
        SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
        subscriberMethod.checkMethodString();
        return this.methodString.equals(subscriberMethod.methodString);
    }

    public int hashCode() {
        return this.method.hashCode();
    }
}
