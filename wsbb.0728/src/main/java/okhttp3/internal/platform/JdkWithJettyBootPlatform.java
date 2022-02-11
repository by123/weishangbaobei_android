package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import okhttp3.internal.Util;

class JdkWithJettyBootPlatform extends Platform {
    private final Class<?> clientProviderClass;
    private final Method getMethod;
    private final Method putMethod;
    private final Method removeMethod;
    private final Class<?> serverProviderClass;

    JdkWithJettyBootPlatform(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.putMethod = method;
        this.getMethod = method2;
        this.removeMethod = method3;
        this.clientProviderClass = cls;
        this.serverProviderClass = cls2;
    }

    public static Platform buildIfSupported() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN" + "$Provider");
            Class<?> cls3 = Class.forName("org.eclipse.jetty.alpn.ALPN" + "$ClientProvider");
            Class<?> cls4 = Class.forName("org.eclipse.jetty.alpn.ALPN" + "$ServerProvider");
            return new JdkWithJettyBootPlatform(cls.getMethod("put", new Class[]{SSLSocket.class, cls2}), cls.getMethod("get", new Class[]{SSLSocket.class}), cls.getMethod("remove", new Class[]{SSLSocket.class}), cls3, cls4);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            return null;
        }
    }

    public void afterHandshake(SSLSocket sSLSocket) {
        try {
            this.removeMethod.invoke((Object) null, new Object[]{sSLSocket});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw Util.assertionError("unable to remove alpn", e);
        }
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
        List<String> alpnProtocolNames = alpnProtocolNames(list);
        try {
            ClassLoader classLoader = Platform.class.getClassLoader();
            Class<?> cls = this.clientProviderClass;
            Class<?> cls2 = this.serverProviderClass;
            JettyNegoProvider jettyNegoProvider = new JettyNegoProvider(alpnProtocolNames);
            Object newProxyInstance = Proxy.newProxyInstance(classLoader, new Class[]{cls, cls2}, jettyNegoProvider);
            this.putMethod.invoke((Object) null, new Object[]{sSLSocket, newProxyInstance});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw Util.assertionError("unable to set alpn", e);
        }
    }

    @Nullable
    public String getSelectedProtocol(SSLSocket sSLSocket) {
        try {
            JettyNegoProvider invocationHandler = Proxy.getInvocationHandler(this.getMethod.invoke((Object) null, new Object[]{sSLSocket}));
            if (!invocationHandler.unsupported && invocationHandler.selected == null) {
                Platform.get().log(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
                return null;
            } else if (invocationHandler.unsupported) {
                return null;
            } else {
                return invocationHandler.selected;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw Util.assertionError("unable to get selected protocol", e);
        }
    }
}
