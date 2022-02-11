package com.wx.assistants.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> FragmentList;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.FragmentList = list;
    }

    public Fragment getItem(int i) {
        return this.FragmentList.get(i);
    }

    public int getCount() {
        return this.FragmentList.size();
    }
}
