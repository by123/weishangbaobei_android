package com.luck.picture.lib.widget.longimage;

public interface DecoderFactory<T> {
    T make() throws IllegalAccessException, InstantiationException;
}
