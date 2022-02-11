package com.scwang.smartrefresh.layout.api;

import android.support.annotation.RestrictTo;

public interface RefreshFooter extends RefreshInternal {
    @RestrictTo({RestrictTo.Scope.LIBRARY, RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.SUBCLASSES})
    boolean setNoMoreData(boolean z);
}
