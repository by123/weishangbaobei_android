package com.wx.assistants.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.athbk.ultimatetablayout.IFTabAdapter;
import com.wx.assistants.fragment.InviteRecordOneFragment;
import java.util.ArrayList;

public class MyInviteRecordMainAdapter extends FragmentPagerAdapter implements IFTabAdapter {
    ArrayList<String> listTitles = new ArrayList<>();

    public MyInviteRecordMainAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList) {
        super(fragmentManager);
        this.listTitles = arrayList;
    }

    public int getCount() {
        return this.listTitles.size();
    }

    public int getIcon(int i) {
        return 0;
    }

    public Fragment getItem(int i) {
        return i == 0 ? InviteRecordOneFragment.newInstance(1) : i == 1 ? InviteRecordOneFragment.newInstance(2) : InviteRecordOneFragment.newInstance(1);
    }

    public String getTitle(int i) {
        return this.listTitles.get(i);
    }

    public boolean isEnableBadge(int i) {
        return i == 0;
    }

    public void updateData(ArrayList<String> arrayList) {
        this.listTitles.clear();
        this.listTitles = arrayList;
    }
}
