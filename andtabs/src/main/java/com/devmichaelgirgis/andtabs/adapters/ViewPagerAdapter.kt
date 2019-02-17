package com.devmichaelgirgis.andtabs.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.devmichaelgirgis.andtabs.items.AdapterItem


class ViewPagerAdapter(FragmentManger: FragmentManager, private val AdapterList: AdapterItem) : FragmentStatePagerAdapter(FragmentManger) {

    override fun getItem(position: Int): Fragment {
        return AdapterList.fragmentList!![position]
    }

    override fun getCount(): Int {

        return AdapterList.fragmentList!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (AdapterList.isTitle) {
            null
        } else {
            AdapterList.titleList!![position]
        }
    }

}
