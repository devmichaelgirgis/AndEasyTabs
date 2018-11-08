package com.devmichaelgirgis.andtabs.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.devmichaelgirgis.andtabs.Items.AdapterItem;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private AdapterItem AdapterList;

    public ViewPagerAdapter(FragmentManager FragmentManger, AdapterItem AdapterList) {
        super(FragmentManger);
        this.AdapterList = AdapterList;

    }

    @Override
    public Fragment getItem(int position) {
        return AdapterList.getFragmentList().get(position);
    }

    @Override
    public int getCount() {

        return AdapterList.getFragmentList().size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (AdapterList.isTitle()) {
            return null;
        } else {
            return AdapterList.getTitleList().get(position);
        }
    }

}
