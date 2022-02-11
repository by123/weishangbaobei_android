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

public class ContactScanUnAddFragment_ViewBinding implements Unbinder {
    private ContactScanUnAddFragment target;
    private View view2131296324;
    private View view2131296579;

    /* JADX WARNING: type inference failed for: r1v3, types: [com.wx.assistants.fragment.ContactScanUnAddFragment_ViewBinding$1, android.view.View$OnClickListener] */
    /* JADX WARNING: type inference failed for: r1v7, types: [com.wx.assistants.fragment.ContactScanUnAddFragment_ViewBinding$2, android.view.View$OnClickListener] */
    @UiThread
    public ContactScanUnAddFragment_ViewBinding(final ContactScanUnAddFragment contactScanUnAddFragment, View view) {
        this.target = contactScanUnAddFragment;
        View findRequiredView = Utils.findRequiredView(view, 2131296579, "field 'deleteContact' and method 'onViewClicked'");
        contactScanUnAddFragment.deleteContact = (LinearLayout) Utils.castView(findRequiredView, 2131296579, "field 'deleteContact'", LinearLayout.class);
        this.view2131296579 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                contactScanUnAddFragment.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, 2131296324, "field 'add_contact' and method 'onViewClicked'");
        contactScanUnAddFragment.add_contact = (Button) Utils.castView(findRequiredView2, 2131296324, "field 'add_contact'", Button.class);
        this.view2131296324 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                contactScanUnAddFragment.onViewClicked(view);
            }
        });
        contactScanUnAddFragment.selectPeopleNum = (TextView) Utils.findRequiredViewAsType(view, 2131297314, "field 'selectPeopleNum'", TextView.class);
        contactScanUnAddFragment.contact_list = (ListView) Utils.findRequiredViewAsType(view, 2131296530, "field 'contact_list'", ListView.class);
        contactScanUnAddFragment.refreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, 2131297228, "field 'refreshLayout'", SmartRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        ContactScanUnAddFragment contactScanUnAddFragment = this.target;
        if (contactScanUnAddFragment != null) {
            this.target = null;
            contactScanUnAddFragment.deleteContact = null;
            contactScanUnAddFragment.add_contact = null;
            contactScanUnAddFragment.selectPeopleNum = null;
            contactScanUnAddFragment.contact_list = null;
            contactScanUnAddFragment.refreshLayout = null;
            this.view2131296579.setOnClickListener((View.OnClickListener) null);
            this.view2131296579 = null;
            this.view2131296324.setOnClickListener((View.OnClickListener) null);
            this.view2131296324 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
