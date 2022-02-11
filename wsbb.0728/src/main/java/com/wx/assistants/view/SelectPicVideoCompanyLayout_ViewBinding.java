package com.wx.assistants.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SelectPicVideoCompanyLayout_ViewBinding implements Unbinder {
    private SelectPicVideoCompanyLayout target;

    @UiThread
    public SelectPicVideoCompanyLayout_ViewBinding(SelectPicVideoCompanyLayout selectPicVideoCompanyLayout) {
        this(selectPicVideoCompanyLayout, selectPicVideoCompanyLayout);
    }

    @UiThread
    public SelectPicVideoCompanyLayout_ViewBinding(SelectPicVideoCompanyLayout selectPicVideoCompanyLayout, View view) {
        this.target = selectPicVideoCompanyLayout;
        selectPicVideoCompanyLayout.customRadioSwitchLayout = (CustomRadioSwitchLayout) Utils.findRequiredViewAsType(view, 2131296569, "field 'customRadioSwitchLayout'", CustomRadioSwitchLayout.class);
        selectPicVideoCompanyLayout.sendOrderLayout = (SendOrderLayout) Utils.findRequiredViewAsType(view, 2131297335, "field 'sendOrderLayout'", SendOrderLayout.class);
        selectPicVideoCompanyLayout.productGridView = (CustomGridView) Utils.findRequiredViewAsType(view, 2131297160, "field 'productGridView'", CustomGridView.class);
        selectPicVideoCompanyLayout.picVideoRadioLayout = (CustomRadioTwoLayout) Utils.findRequiredViewAsType(view, 2131297132, "field 'picVideoRadioLayout'", CustomRadioTwoLayout.class);
    }

    @CallSuper
    public void unbind() {
        SelectPicVideoCompanyLayout selectPicVideoCompanyLayout = this.target;
        if (selectPicVideoCompanyLayout != null) {
            this.target = null;
            selectPicVideoCompanyLayout.customRadioSwitchLayout = null;
            selectPicVideoCompanyLayout.sendOrderLayout = null;
            selectPicVideoCompanyLayout.productGridView = null;
            selectPicVideoCompanyLayout.picVideoRadioLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
