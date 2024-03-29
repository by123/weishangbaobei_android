package com.luck.picture.lib.compress;

import com.luck.picture.lib.entity.LocalMedia;
import java.util.List;

public interface OnCompressListener {
    void onError(Throwable th);

    void onStart();

    void onSuccess(List<LocalMedia> list);
}
