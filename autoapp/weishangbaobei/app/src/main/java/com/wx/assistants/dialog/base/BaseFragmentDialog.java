package com.wx.assistants.dialog.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public abstract class BaseFragmentDialog extends DialogFragment {
    private boolean isShowing = false;

    /* access modifiers changed from: protected */
    public abstract int initAnimations();

    public float initBackgroundAlpha() {
        return 0.6f;
    }

    public void initDialog() {
    }

    /* access modifiers changed from: protected */
    public abstract View initView(LayoutInflater layoutInflater);

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setSoftInputMode(16);
            getDialog().getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getContext(), 17170445));
            int initAnimations = initAnimations();
            if (initAnimations != 0) {
                getDialog().getWindow().setWindowAnimations(initAnimations);
            }
        }
        setStyle(2131755193, 16973834);
        getDialog().requestWindowFeature(1);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4;
            }
        });
        initDialog();
        return initView(layoutInflater);
    }

    public void dismiss() {
        this.isShowing = false;
        BaseFragmentDialog.super.dismiss();
    }

    public void dismissAllowingStateLoss() {
        BaseFragmentDialog.super.dismissAllowingStateLoss();
        this.isShowing = false;
    }

    public void showDialog(FragmentActivity fragmentActivity) {
        try {
            if (!isShowing() && fragmentActivity != null && fragmentActivity.getSupportFragmentManager() != null) {
                FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
                supportFragmentManager.beginTransaction().remove(this).commit();
                show(supportFragmentManager, fragmentActivity.getLocalClassName());
            }
        } catch (Exception e) {
            Log.e("-------", e.toString());
        }
    }

    public void show(FragmentManager fragmentManager, String str) {
        this.isShowing = true;
        BaseFragmentDialog.super.show(fragmentManager, str);
    }

    public boolean isShowing() {
        if (!this.isShowing) {
            return getDialog() != null && getDialog().isShowing();
        }
        return true;
    }

    public void onStart() {
        BaseFragmentDialog.super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = initBackgroundAlpha();
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    public void onDestroyView() {
        BaseFragmentDialog.super.onDestroyView();
        this.isShowing = false;
    }
}
