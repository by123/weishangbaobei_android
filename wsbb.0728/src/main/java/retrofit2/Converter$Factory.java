package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public abstract class Converter$Factory {
    protected static Type getParameterUpperBound(int i, ParameterizedType parameterizedType) {
        return Utils.getParameterUpperBound(i, parameterizedType);
    }

    protected static Class<?> getRawType(Type type) {
        return Utils.getRawType(type);
    }

    @Nullable
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return null;
    }

    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return null;
    }

    @Nullable
    public Converter<?, String> stringConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return null;
    }
}
