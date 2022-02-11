package com.yongchun.library.utils.luban;

import android.support.annotation.Nullable;

final class Preconditions {
    Preconditions() {
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static <T> T checkNotNull(T t, @Nullable Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
