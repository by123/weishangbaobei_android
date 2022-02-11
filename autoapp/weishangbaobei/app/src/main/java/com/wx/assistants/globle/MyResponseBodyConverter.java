package com.wx.assistants.globle;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class MyResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    MyResponseBodyConverter(Gson gson2, TypeAdapter<T> typeAdapter) {
        this.gson = gson2;
        this.adapter = typeAdapter;
    }

    public T convert(ResponseBody responseBody) throws IOException {
        try {
            T read = this.adapter.read(this.gson.newJsonReader(responseBody.charStream()));
            LogUtils.netJson(this.gson.toJson(read));
            return read;
        } finally {
            responseBody.close();
        }
    }
}
