package com.wx.assistants.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class ContactScanAddedFragment_ViewBinding implements Unbinder {
    private ContactScanAddedFragment target;
    private View view2131296324;
    private View view2131296579;

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.ContactScanAddedFragment_ViewBinding$1] */
    /* JADX WARNING: type inference failed for: r1v7, types: [android.view.View$OnClickListener, com.wx.assistants.fragment.ContactScanAddedFragment_ViewBinding$2] */
    @UiThread
    public ContactScanAddedFragment_ViewBinding(final ContactScanAddedFragment contactScanAddedFragment, View view) {
        this.target = contactScanAddedFragment;
        View findRequiredView = Utils.findRequiredView(view, 2131296579, "field 'deleteContact' and method 'onViewClicked'");
        contactScanAddedFragment.deleteContact = (LinearLayout) Utils.castView(findRequiredView, 2131296579, "field 'deleteContact'", LinearLayout.class);
        this.view2131296579 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                contactScanAddedFragment.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296324, "field 'add_contact' and method 'onViewClicked'");
        contactScanAddedFragment.add_contact = (Button) Utils.castView(findRequiredView2, 2131296324, "field 'add_contact'", Button.class);
        this.view2131296324 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                contactScanAddedFragment.onViewClicked(view);
            }
        });
        contactScanAddedFragment.selectPeopleNum = (TextView) Utils.findRequiredViewAsType(view, 2131297314, "field 'selectPeopleNum'", TextView.class);
        contactScanAddedFragment.contact_list = (ListView) Utils.findRequiredViewAsType(view, 2131296530, "field 'contact_list'", ListView.class);
        contactScanAddedFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        ContactScanAddedFragment contactScanAddedFragment = this.target;
        if (contactScanAddedFragment != null) {
            this.target = null;
            contactScanAddedFragment.deleteContact = null;
            contactScanAddedFragment.add_contact = null;
            contactScanAddedFragment.selectPeopleNum = null;
            contactScanAddedFragment.contact_list = null;
            contactScanAddedFragment.refreshLayout = null;
            this.view2131296579.setOnClickListener((View.OnClickListener) null);
            this.view2131296579 = null;
            this.view2131296324.setOnClickListener((View.OnClickListener) null);
            this.view2131296324 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
