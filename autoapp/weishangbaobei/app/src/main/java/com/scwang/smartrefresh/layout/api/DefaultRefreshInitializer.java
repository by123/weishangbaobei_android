package com.scwang.smartrefresh.layout.api;

import android.content.Context;
import android.support.annotation.NonNull;

public interface DefaultRefreshInitializer {
    void initialize(@NonNull Context context, @NonNull RefreshLayout refreshLayout);
}
