package com.tencent.mm.opensdk.diffdev.a;

import com.yalantis.ucrop.view.CropImageView;

public enum g {
    UUID_EXPIRED(402),
    UUID_CANCELED(403),
    UUID_SCANED(404),
    UUID_CONFIRM(405),
    UUID_KEEP_CONNECT(408),
    UUID_ERROR(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
    
    private int code;

    private g(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    public final String toString() {
        return "UUIDStatusCode:" + this.code;
    }
}
