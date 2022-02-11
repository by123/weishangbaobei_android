package com.luck.picture.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DoubleUtils;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public final class PictureSelector {
    private final WeakReference<Activity> mActivity;
    private final WeakReference<Fragment> mFragment;

    private PictureSelector(Activity activity) {
        this(activity, (Fragment) null);
    }

    private PictureSelector(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private PictureSelector(Activity activity, Fragment fragment) {
        this.mActivity = new WeakReference<>(activity);
        this.mFragment = new WeakReference<>(fragment);
    }

    public static PictureSelector create(Activity activity) {
        return new PictureSelector(activity);
    }

    public static PictureSelector create(Fragment fragment) {
        return new PictureSelector(fragment);
    }

    public PictureSelectionModel openGallery(int i) {
        return new PictureSelectionModel(this, i);
    }

    public PictureSelectionModel openCamera(int i) {
        return new PictureSelectionModel(this, i, true);
    }

    public PictureSelectionModel themeStyle(int i) {
        return new PictureSelectionModel(this, PictureMimeType.ofImage()).theme(i);
    }

    public static List<LocalMedia> obtainMultipleResult(Intent intent) {
        ArrayList arrayList = new ArrayList();
        if (intent == null) {
            return arrayList;
        }
        List<LocalMedia> list = (List) intent.getSerializableExtra(PictureConfig.EXTRA_RESULT_SELECTION);
        return list == null ? new ArrayList() : list;
    }

    public static Intent putIntentResult(List<LocalMedia> list) {
        return new Intent().putExtra(PictureConfig.EXTRA_RESULT_SELECTION, (Serializable) list);
    }

    public static List<LocalMedia> obtainSelectorList(Bundle bundle) {
        if (bundle != null) {
            return (List) bundle.getSerializable(PictureConfig.EXTRA_SELECT_LIST);
        }
        return new ArrayList();
    }

    public static void saveSelectorList(Bundle bundle, List<LocalMedia> list) {
        bundle.putSerializable(PictureConfig.EXTRA_SELECT_LIST, (Serializable) list);
    }

    public void externalPicturePreview(int i, List<LocalMedia> list) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(getActivity(), PictureExternalPreviewActivity.class);
            intent.putExtra("previewSelectList", (Serializable) list);
            intent.putExtra("position", i);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.a5, 0);
        }
    }

    public void externalPicturePreview(int i, String str, List<LocalMedia> list) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(getActivity(), PictureExternalPreviewActivity.class);
            intent.putExtra("previewSelectList", (Serializable) list);
            intent.putExtra("position", i);
            intent.putExtra(PictureConfig.DIRECTORY_PATH, str);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.a5, 0);
        }
    }

    public void externalPictureVideo(String str) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(getActivity(), PictureVideoPlayActivity.class);
            intent.putExtra("video_path", str);
            getActivity().startActivity(intent);
        }
    }

    public void externalPictureAudio(String str) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent(getActivity(), PicturePlayAudioActivity.class);
            intent.putExtra("audio_path", str);
            getActivity().startActivity(intent);
            getActivity().overridePendingTransition(R.anim.a5, 0);
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Activity getActivity() {
        return (Activity) this.mActivity.get();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Fragment getFragment() {
        if (this.mFragment != null) {
            return (Fragment) this.mFragment.get();
        }
        return null;
    }
}
