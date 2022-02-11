package com.wx.assistants.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.athbk.ultimatetablayout.IFTabAdapter;
import com.wx.assistants.fragment.MyVoiceFragment;
import com.wx.assistants.fragment.SelectMyVoiceCollectionFragment;
import com.wx.assistants.fragment.SelectMyVoiceFragment;
import com.wx.assistants.fragment.SelectWxVoiceFragment;
import java.util.ArrayList;

public class FragmentSelectVoiceAdapter extends FragmentPagerAdapter implements IFTabAdapter {
    ArrayList<String> listTitles = new ArrayList<>();

    public int getIcon(int i) {
        return 0;
    }

    public boolean isEnableBadge(int i) {
        return i == 0;
    }

    public FragmentSelectVoiceAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList) {
        super(fragmentManager);
        this.listTitles = arrayList;
    }

    public Fragment getItem(int i) {
        if (i == 0) {
            return SelectMyVoiceFragment.newInstance();
        }
        if (i == 1) {
            return SelectWxVoiceFragment.newInstance();
        }
        if (i == 2) {
            return SelectMyVoiceCollectionFragment.newInstance();
        }
        return MyVoiceFragment.newInstance();
    }

    public int getCount() {
        return this.listTitles.size();
    }

    public String getTitle(int i) {
        return this.listTitles.get(i);
    }
}
