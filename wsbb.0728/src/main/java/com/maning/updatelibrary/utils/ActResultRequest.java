package com.maning.updatelibrary.utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;

public class ActResultRequest {
    private OnActResultEventDispatcherFragment fragment;

    public ActResultRequest(Activity activity) {
        this.fragment = getEventDispatchFragment(activity);
    }

    private OnActResultEventDispatcherFragment findEventDispatchFragment(FragmentManager fragmentManager) {
        return (OnActResultEventDispatcherFragment) fragmentManager.findFragmentByTag(OnActResultEventDispatcherFragment.TAG);
    }

    private OnActResultEventDispatcherFragment getEventDispatchFragment(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        OnActResultEventDispatcherFragment findEventDispatchFragment = findEventDispatchFragment(fragmentManager);
        if (findEventDispatchFragment != null) {
            return findEventDispatchFragment;
        }
        OnActResultEventDispatcherFragment onActResultEventDispatcherFragment = new OnActResultEventDispatcherFragment();
        fragmentManager.beginTransaction().add(onActResultEventDispatcherFragment, OnActResultEventDispatcherFragment.TAG).commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
        return onActResultEventDispatcherFragment;
    }

    public void startForResult(Intent intent, ActForResultCallback actForResultCallback) {
        this.fragment.startForResult(intent, actForResultCallback);
    }
}
