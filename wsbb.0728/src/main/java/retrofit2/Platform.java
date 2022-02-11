package retrofit2;

import android.os.Build;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

class Platform {
    private static final Platform PLATFORM = findPlatform();

    static class Android extends Platform {
        Android() {
        }

        /* access modifiers changed from: package-private */
        public CallAdapter$Factory defaultCallAdapterFactory(@Nullable Executor executor) {
            if (executor != null) {
                return new ExecutorCallAdapterFactory(executor);
            }
            throw new AssertionError();
        }

        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }
    }

    @IgnoreJRERequirement
    static class Java8 extends Platform {
        Java8() {
        }

        /* access modifiers changed from: package-private */
        public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
            Constructor<MethodHandles.Lookup> declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(new Object[]{cls, -1}).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
        }

        /* access modifiers changed from: package-private */
        public boolean isDefaultMethod(Method method) {
            return method.isDefault();
        }
    }

    Platform() {
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (Build.VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException e2) {
            return new Platform();
        }
    }

    static Platform get() {
        return PLATFORM;
    }

    /* access modifiers changed from: package-private */
    public CallAdapter$Factory defaultCallAdapterFactory(@Nullable Executor executor) {
        return executor != null ? new ExecutorCallAdapterFactory(executor) : DefaultCallAdapterFactory.INSTANCE;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Executor defaultCallbackExecutor() {
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, @Nullable Object... objArr) throws Throwable {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public boolean isDefaultMethod(Method method) {
        return false;
    }
}
