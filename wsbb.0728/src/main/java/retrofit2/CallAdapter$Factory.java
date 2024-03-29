package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

public abstract class CallAdapter$Factory {
    protected static Type getParameterUpperBound(int i, ParameterizedType parameterizedType) {
        return Utils.getParameterUpperBound(i, parameterizedType);
    }

    protected static Class<?> getRawType(Type type) {
        return Utils.getRawType(type);
    }

    @Nullable
    public abstract CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit);
}
