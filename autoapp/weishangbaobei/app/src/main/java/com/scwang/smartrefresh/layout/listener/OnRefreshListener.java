package com.scwang.smartrefresh.layout.listener;

import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
