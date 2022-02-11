package com.scwang.smartrefresh.layout.api;

import android.content.Context;
import android.support.annotation.NonNull;

public interface DefaultRefreshFooterCreator {
    @NonNull
    RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout refreshLayout);
}
