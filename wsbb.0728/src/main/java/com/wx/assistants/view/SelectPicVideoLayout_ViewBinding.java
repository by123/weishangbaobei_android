package com.wx.assistants.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SelectPicVideoLayout_ViewBinding implements Unbinder {
    private SelectPicVideoLayout target;

    @UiThread
    public SelectPicVideoLayout_ViewBinding(SelectPicVideoLayout selectPicVideoLayout) {
        this(selectPicVideoLayout, selectPicVideoLayout);
    }

    @UiThread
    public SelectPicVideoLayout_ViewBinding(SelectPicVideoLayout selectPicVideoLayout, View view) {
        this.target = selectPicVideoLayout;
        selectPicVideoLayout.customRadioSwitchLayout = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296569, "field 'customRadioSwitchLayout'", CustomRadioSwitchLayout.class);
        selectPicVideoLayout.sendOrderLayout = (SendOrderLayout) Utils.findRequiredViewAsType(view, 2131297335, "field 'sendOrderLayout'", SendOrderLayout.class);
        selectPicVideoLayout.productGridView = (CustomGridView) Utils.findRequiredViewAsType(view, 2131297160, "field 'productGridView'", CustomGridView.class);
        selectPicVideoLayout.picVideoRadioLayout = (CustomRadioThreeLayout) Utils.findRequiredViewAsType(view, 2131297132, "field 'picVideoRadioLayout'", CustomRadioThreeLayout.class);
    }

    @CallSuper
    public void unbind() {
        SelectPicVideoLayout selectPicVideoLayout = this.target;
        if (selectPicVideoLayout != null) {
            this.target = null;
            selectPicVideoLayout.customRadioSwitchLayout = null;
            selectPicVideoLayout.sendOrderLayout = null;
            selectPicVideoLayout.productGridView = null;
            selectPicVideoLayout.picVideoRadioLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
