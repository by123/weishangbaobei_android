package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Call$Factory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.BuiltInConverters;
import retrofit2.ServiceMethod;

public final class Retrofit {
    final HttpUrl baseUrl;
    final List<CallAdapter$Factory> callAdapterFactories;
    final Call$Factory callFactory;
    @Nullable
    final Executor callbackExecutor;
    final List<Converter$Factory> converterFactories;
    private final Map<Method, ServiceMethod<?, ?>> serviceMethodCache = new ConcurrentHashMap();
    final boolean validateEagerly;

    Retrofit(Call$Factory call$Factory, HttpUrl httpUrl, List<Converter$Factory> list, List<CallAdapter$Factory> list2, @Nullable Executor executor, boolean z) {
        this.callFactory = call$Factory;
        this.baseUrl = httpUrl;
        this.converterFactories = list;
        this.callAdapterFactories = list2;
        this.callbackExecutor = executor;
        this.validateEagerly = z;
    }

    public <T> T create(final Class<T> cls) {
        Utils.validateServiceInterface(cls);
        if (this.validateEagerly) {
            eagerlyValidateMethods(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            private final Platform platform = Platform.get();

            public Object invoke(Object obj, Method method, @Nullable Object[] objArr) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, cls, obj, objArr);
                }
                ServiceMethod<?, ?> loadServiceMethod = Retrofit.this.loadServiceMethod(method);
                return loadServiceMethod.adapt(new OkHttpCall(loadServiceMethod, objArr));
            }
        });
    }

    private void eagerlyValidateMethods(Class<?> cls) {
        Platform platform = Platform.get();
        for (Method method : cls.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ServiceMethod<?, ?> loadServiceMethod(Method method) {
        ServiceMethod<?, ?> serviceMethod;
        ServiceMethod<?, ?> serviceMethod2 = this.serviceMethodCache.get(method);
        if (serviceMethod2 != null) {
            return serviceMethod2;
        }
        synchronized (this.serviceMethodCache) {
            serviceMethod = this.serviceMethodCache.get(method);
            if (serviceMethod == null) {
                serviceMethod = new ServiceMethod.Builder(this, method).build();
                this.serviceMethodCache.put(method, serviceMethod);
            }
        }
        return serviceMethod;
    }

    public Call$Factory callFactory() {
        return this.callFactory;
    }

    public HttpUrl baseUrl() {
        return this.baseUrl;
    }

    public List<CallAdapter$Factory> callAdapterFactories() {
        return this.callAdapterFactories;
    }

    public CallAdapter<?, ?> callAdapter(Type type, Annotation[] annotationArr) {
        return nextCallAdapter((CallAdapter$Factory) null, type, annotationArr);
    }

    public CallAdapter<?, ?> nextCallAdapter(@Nullable CallAdapter$Factory callAdapter$Factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "returnType == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.callAdapterFactories.indexOf(callAdapter$Factory) + 1;
        int size = this.callAdapterFactories.size();
        for (int i = indexOf; i < size; i++) {
            CallAdapter<?, ?> callAdapter = this.callAdapterFactories.get(i).get(type, annotationArr, this);
            if (callAdapter != null) {
                return callAdapter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
        sb.append(type);
        sb.append(".\n");
        if (callAdapter$Factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.callAdapterFactories.get(i2).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.callAdapterFactories.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.callAdapterFactories.get(indexOf).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public List<Converter$Factory> converterFactories() {
        return this.converterFactories;
    }

    public <T> Converter<T, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return nextRequestBodyConverter((Converter$Factory) null, type, annotationArr, annotationArr2);
    }

    public <T> Converter<T, RequestBody> nextRequestBodyConverter(@Nullable Converter$Factory converter$Factory, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "parameterAnnotations == null");
        Utils.checkNotNull(annotationArr2, "methodAnnotations == null");
        int indexOf = this.converterFactories.indexOf(converter$Factory) + 1;
        int size = this.converterFactories.size();
        for (int i = indexOf; i < size; i++) {
            Converter<?, RequestBody> requestBodyConverter = this.converterFactories.get(i).requestBodyConverter(type, annotationArr, annotationArr2, this);
            if (requestBodyConverter != null) {
                return requestBodyConverter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate RequestBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (converter$Factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.converterFactories.get(i2).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.converterFactories.get(indexOf).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotationArr) {
        return nextResponseBodyConverter((Converter$Factory) null, type, annotationArr);
    }

    public <T> Converter<ResponseBody, T> nextResponseBodyConverter(@Nullable Converter$Factory converter$Factory, Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int indexOf = this.converterFactories.indexOf(converter$Factory) + 1;
        int size = this.converterFactories.size();
        for (int i = indexOf; i < size; i++) {
            Converter<ResponseBody, ?> responseBodyConverter = this.converterFactories.get(i).responseBodyConverter(type, annotationArr, this);
            if (responseBodyConverter != null) {
                return responseBodyConverter;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate ResponseBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (converter$Factory != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < indexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.converterFactories.get(i2).getClass().getName());
            }
            sb.append(10);
        }
        sb.append("  Tried:");
        int size2 = this.converterFactories.size();
        while (indexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.converterFactories.get(indexOf).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> Converter<T, String> stringConverter(Type type, Annotation[] annotationArr) {
        Utils.checkNotNull(type, "type == null");
        Utils.checkNotNull(annotationArr, "annotations == null");
        int size = this.converterFactories.size();
        for (int i = 0; i < size; i++) {
            Converter<?, String> stringConverter = this.converterFactories.get(i).stringConverter(type, annotationArr, this);
            if (stringConverter != null) {
                return stringConverter;
            }
        }
        return BuiltInConverters.ToStringConverter.INSTANCE;
    }

    @Nullable
    public Executor callbackExecutor() {
        return this.callbackExecutor;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        private HttpUrl baseUrl;
        private final List<CallAdapter$Factory> callAdapterFactories;
        @Nullable
        private Call$Factory callFactory;
        @Nullable
        private Executor callbackExecutor;
        private final List<Converter$Factory> converterFactories;
        private final Platform platform;
        private boolean validateEagerly;

        Builder(Platform platform2) {
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            this.platform = platform2;
        }

        public Builder() {
            this(Platform.get());
        }

        Builder(Retrofit retrofit) {
            this.converterFactories = new ArrayList();
            this.callAdapterFactories = new ArrayList();
            this.platform = Platform.get();
            this.callFactory = retrofit.callFactory;
            this.baseUrl = retrofit.baseUrl;
            this.converterFactories.addAll(retrofit.converterFactories);
            this.converterFactories.remove(0);
            this.callAdapterFactories.addAll(retrofit.callAdapterFactories);
            this.callAdapterFactories.remove(this.callAdapterFactories.size() - 1);
            this.callbackExecutor = retrofit.callbackExecutor;
            this.validateEagerly = retrofit.validateEagerly;
        }

        public Builder client(OkHttpClient okHttpClient) {
            return callFactory((Call$Factory) Utils.checkNotNull(okHttpClient, "client == null"));
        }

        public Builder callFactory(Call$Factory call$Factory) {
            this.callFactory = (Call$Factory) Utils.checkNotNull(call$Factory, "factory == null");
            return this;
        }

        public Builder baseUrl(String str) {
            Utils.checkNotNull(str, "baseUrl == null");
            HttpUrl parse = HttpUrl.parse(str);
            if (parse != null) {
                return baseUrl(parse);
            }
            throw new IllegalArgumentException("Illegal URL: " + str);
        }

        public Builder baseUrl(HttpUrl httpUrl) {
            Utils.checkNotNull(httpUrl, "baseUrl == null");
            List<String> pathSegments = httpUrl.pathSegments();
            if ("".equals(pathSegments.get(pathSegments.size() - 1))) {
                this.baseUrl = httpUrl;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + httpUrl);
        }

        public Builder addConverterFactory(Converter$Factory converter$Factory) {
            this.converterFactories.add(Utils.checkNotNull(converter$Factory, "factory == null"));
            return this;
        }

        public Builder addCallAdapterFactory(CallAdapter$Factory callAdapter$Factory) {
            this.callAdapterFactories.add(Utils.checkNotNull(callAdapter$Factory, "factory == null"));
            return this;
        }

        public Builder callbackExecutor(Executor executor) {
            this.callbackExecutor = (Executor) Utils.checkNotNull(executor, "executor == null");
            return this;
        }

        public List<CallAdapter$Factory> callAdapterFactories() {
            return this.callAdapterFactories;
        }

        public List<Converter$Factory> converterFactories() {
            return this.converterFactories;
        }

        public Builder validateEagerly(boolean z) {
            this.validateEagerly = z;
            return this;
        }

        public Retrofit build() {
            if (this.baseUrl != null) {
                Call$Factory call$Factory = this.callFactory;
                if (call$Factory == null) {
                    call$Factory = new OkHttpClient();
                }
                Call$Factory call$Factory2 = call$Factory;
                Executor executor = this.callbackExecutor;
                if (executor == null) {
                    executor = this.platform.defaultCallbackExecutor();
                }
                Executor executor2 = executor;
                ArrayList arrayList = new ArrayList(this.callAdapterFactories);
                arrayList.add(this.platform.defaultCallAdapterFactory(executor2));
                ArrayList arrayList2 = new ArrayList(this.converterFactories.size() + 1);
                arrayList2.add(new BuiltInConverters());
                arrayList2.addAll(this.converterFactories);
                return new Retrofit(call$Factory2, this.baseUrl, Collections.unmodifiableList(arrayList2), Collections.unmodifiableList(arrayList), executor2, this.validateEagerly);
            }
            throw new IllegalStateException("Base URL required.");
        }
    }
}
