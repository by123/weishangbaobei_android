package com.luck.picture.lib.widget.longimage;

import android.support.annotation.NonNull;

public class CompatDecoderFactory<T> implements DecoderFactory<T> {
    private Class<? extends T> clazz;

    public CompatDecoderFactory(@NonNull Class<? extends T> cls) {
        this.clazz = cls;
    }

    public T make() throws IllegalAccessException, InstantiationException {
        return this.clazz.newInstance();
    }
}
