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

    public FragmentSelectVoiceAdapter(FragmentManager fragmentManager, ArrayList<String> arrayList) {
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
        return i == 0 ? SelectMyVoiceFragment.newInstance() : i == 1 ? SelectWxVoiceFragment.newInstance() : i == 2 ? SelectMyVoiceCollectionFragment.newInstance() : MyVoiceFragment.newInstance();
    }

    public String getTitle(int i) {
        return this.listTitles.get(i);
    }

    public boolean isEnableBadge(int i) {
        return i == 0;
    }
}
