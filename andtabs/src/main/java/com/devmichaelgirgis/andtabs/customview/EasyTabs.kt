package com.devmichaelgirgis.andtabs.customview


import android.content.Context
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

import com.devmichaelgirgis.andtabs.R



class EasyTabs : RelativeLayout {
    var tabLayout: TabLayout? = null
        private set
    var viewPager: ViewPager? = null
        private set
    private var mRootView: View? = null

    constructor(context: Context) : super(context) {
        mRootView = View.inflate(context, R.layout.easytabs, this)
        tabLayout = mRootView!!.findViewById(R.id.tabs)
        viewPager = mRootView!!.findViewById(R.id.pager)


    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mRootView = View.inflate(context, R.layout.easytabs, this)
        tabLayout = mRootView!!.findViewById(R.id.tabs)
        viewPager = mRootView!!.findViewById(R.id.pager)

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        mRootView = View.inflate(context, R.layout.easytabs, this)
        tabLayout = mRootView!!.findViewById(R.id.tabs)
        viewPager = mRootView!!.findViewById(R.id.pager)
    }
}
