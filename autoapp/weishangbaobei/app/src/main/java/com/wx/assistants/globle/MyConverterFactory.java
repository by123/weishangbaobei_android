package com.wx.assistants.globle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter$Factory;
import retrofit2.Retrofit;

public final class MyConverterFactory extends Converter$Factory {
    private final Gson gson;

    public static MyConverterFactory create() {
        return create(new Gson());
    }

    public static MyConverterFactory create(Gson gson2) {
        return new MyConverterFactory(gson2);
    }

    private MyConverterFactory(Gson gson2) {
        if (gson2 != null) {
            this.gson = gson2;
            return;
        }
        throw new NullPointerException("gson == null");
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        return new MyResponseBodyConverter(this.gson, this.gson.getAdapter(TypeToken.get(type)));
    }

    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        return new MyRequestBodyConverter(this.gson, this.gson.getAdapter(TypeToken.get(type)));
    }
}
