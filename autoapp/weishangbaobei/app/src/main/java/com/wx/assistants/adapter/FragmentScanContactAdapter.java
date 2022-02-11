package com.wx.assistants.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.athbk.ultimatetablayout.IFTabAdapter;
import com.wx.assistants.fragment.ContactScanAddedFragment;
import com.wx.assistants.fragment.ContactScanUnAddFragment;
import java.util.ArrayList;

public class FragmentScanContactAdapter extends FragmentPagerAdapter implements IFTabAdapter {
    ArrayList<String> listTitles = new ArrayList<>();

    public int getIcon(int i) {
        return 0;
    }

    public boolean isEnableBadge(int i) {
        return i == 0;
    }

    public FragmentScanContactAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList) {
        super(fragmentManager);
        this.listTitles = arrayList;
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            return ContactScanUnAddFragment.newInstance();
        }
        if (i == 1) {
            return ContactScanAddedFragment.newInstance();
        }
        return ContactScanUnAddFragment.newInstance();
    }

    public int getCount() {
        return this.listTitles.size();
    }

    public String getTitle(int i) {
        return this.listTitles.get(i);
    }
}
