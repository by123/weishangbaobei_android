package com.wx.assistants.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

public class BaseFragment extends Fragment {
    private LoadingDialog loadingDialog;

    public void dismissLoadingDialog() {
        try {
            if (this.loadingDialog != null) {
                this.loadingDialog.close();
            }
        } catch (Exception e) {
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return BaseFragment.super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void showLoadingDialog(String str) {
        try {
            this.loadingDialog = new LoadingDialog(getContext());
            this.loadingDialog.setInterceptBack(false);
            this.loadingDialog.setLoadingText(str).show();
        } catch (Exception e) {
        }
    }

    public void updateLoadingDialog(String str) {
        try {
            if (this.loadingDialog != null) {
                this.loadingDialog.setLoadingText(str);
            }
        } catch (Exception e) {
        }
    }
}
