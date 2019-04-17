package com.devmichaelgirgis.andtabs.builders

import android.annotation.TargetApi
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorInt
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

import com.devmichaelgirgis.andtabs.adapters.ViewPagerAdapter
import com.devmichaelgirgis.andtabs.`interface`.TabsListener
import com.devmichaelgirgis.andtabs.items.AdapterItem
import com.devmichaelgirgis.andtabs.items.TabItem
import com.devmichaelgirgis.andtabs.customview.EasyTabs

import android.R.attr.typeface
import java.util.*


class EasyTabsBuilder {
    private var StaticTabsLayout: TabLayout? = null
    private var StaticViewPager: ViewPager? = null
    private var StaticActivity: AppCompatActivity? = null
    private var easyTabs: EasyTabs? = null
    private var isHidden = false
    private var isFade = false
    private var iconsPosition = 0
    private var Icons: Array<Drawable>? = null
    private var ResIdIcons: IntArray? = null
    private val top = 0
    private val FragmentList = ArrayList<Fragment>()
    private val TitleList = ArrayList<String>()

    private  var selectedTypeFace: Typeface? =null
    private var  isAllCaps:Boolean = false

    fun setCustomTypeface(selected: Typeface,isAllCaps:Boolean): EasyTabsBuilder {
        this.selectedTypeFace=selected
        this.isAllCaps = isAllCaps

        val vg = StaticTabsLayout!!.getChildAt(0) as? ViewGroup
        if(vg!=null) {
            val tabsCount = vg.childCount
            for (j in 0 until tabsCount) {
                val vgTab = vg.getChildAt(j) as? ViewGroup
                if(vgTab!=null) {
                    val tabChildsCount = vgTab.childCount
                    for (i in 0 until tabChildsCount) {
                        val tabViewChild = vgTab.getChildAt(i)

                        if (tabViewChild is TextView) {
                            tabViewChild.typeface = selected
                            tabViewChild.isAllCaps = isAllCaps
                        }
                    }
                }
            }
        }
        return this
    }

    fun setTabCenterPosition(): EasyTabsBuilder {
        StaticTabsLayout!!.setTabMode(TabLayout.MODE_FIXED)
        return this
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setRTLPosition(state: Boolean?): EasyTabsBuilder {

        if (state!!) {
            StaticTabsLayout!!.layoutDirection = TabLayout.LAYOUT_DIRECTION_RTL
            StaticViewPager!!.currentItem = FragmentList.size - 1

        } else {
            StaticViewPager!!.currentItem = 0
        }
        return this
    }

    fun setTextColors(@ColorInt selectedColor: Int, @ColorInt unselectedColor: Int): EasyTabsBuilder {
        StaticTabsLayout!!.setTabTextColors(unselectedColor, selectedColor)
        return this
    }

    fun setTabsBackgroundColor(@ColorInt Color: Int): EasyTabsBuilder {
        StaticTabsLayout!!.setBackgroundColor(Color)
        return this
    }

    fun setIndicatorColor(@ColorInt Color: Int): EasyTabsBuilder {
        StaticTabsLayout!!.setSelectedTabIndicatorColor(Color)
        return this
    }

    fun withListener(tabsListener: TabsListener): EasyTabsBuilder {
        StaticTabsLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tabsListener.onScreenPosition(tab.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        return this
    }

    fun setIconFading(state: Boolean): EasyTabsBuilder {
        isFade = state
        if (Icons != null) {
            if (Icons!!.size == FragmentList.size) {
                if (state) {
                    StaticViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                            val alphaCurrent = (255 - 128 * Math.abs(positionOffset)).toInt()
                            val alphaNext = (128 + 128 * Math.abs(positionOffset)).toInt()

                            if (positionOffset != 0f) {

                                when (Icons!!.size) {
                                    0 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    1 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    2 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    3 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    4 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    5 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    6 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    7 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    8 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    9 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                    }

                                    10 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    11 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    12 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                        13 -> {
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    13 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                        13 -> {
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaNext
                                        }
                                        14 -> {
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(15)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    14 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                        13 -> {
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaNext
                                        }
                                        14 -> {
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(15)!!.icon!!.alpha = alphaNext
                                        }
                                        15 -> {
                                            StaticTabsLayout!!.getTabAt(15)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(16)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                }
                            }
                        }

                        override fun onPageSelected(position: Int) {

                        }

                        override fun onPageScrollStateChanged(state: Int) {

                        }

                    })
                }
            } else {
                val throwable = Throwable("Amount of icons must be equal to the number of StaticTabsLayout. ")
                try {
                    throw throwable
                } catch (throwable1: Throwable) {
                    throwable1.printStackTrace()
                }


            }
        } else {
            if (ResIdIcons!!.size == FragmentList.size) {
                if (state) {
                    StaticViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                            val alphaCurrent = (255 - 128 * Math.abs(positionOffset)).toInt()
                            val alphaNext = (128 + 128 * Math.abs(positionOffset)).toInt()

                            if (positionOffset != 0f) {
                                when (ResIdIcons!!.size) {
                                    0 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    1 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    2 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    3 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    4 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    5 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    6 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    7 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    8 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    9 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                    }

                                    10 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    11 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    12 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                        13 -> {
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    13 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                        13 -> {
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaNext
                                        }
                                        14 -> {
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(15)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                    14 -> when (position) {
                                        0 -> {
                                            StaticTabsLayout!!.getTabAt(0)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaNext
                                        }
                                        1 -> {
                                            StaticTabsLayout!!.getTabAt(1)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaNext
                                        }
                                        2 -> {
                                            StaticTabsLayout!!.getTabAt(2)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaNext
                                        }
                                        3 -> {
                                            StaticTabsLayout!!.getTabAt(3)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaNext
                                        }
                                        4 -> {
                                            StaticTabsLayout!!.getTabAt(4)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaNext
                                        }
                                        5 -> {
                                            StaticTabsLayout!!.getTabAt(5)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaNext
                                        }
                                        6 -> {
                                            StaticTabsLayout!!.getTabAt(6)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaNext
                                        }
                                        7 -> {
                                            StaticTabsLayout!!.getTabAt(7)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaNext
                                        }
                                        8 -> {
                                            StaticTabsLayout!!.getTabAt(8)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaNext
                                        }
                                        9 -> {
                                            StaticTabsLayout!!.getTabAt(9)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaNext
                                        }
                                        10 -> {
                                            StaticTabsLayout!!.getTabAt(10)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaNext
                                        }
                                        11 -> {
                                            StaticTabsLayout!!.getTabAt(11)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaNext
                                        }
                                        12 -> {
                                            StaticTabsLayout!!.getTabAt(12)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaNext
                                        }
                                        13 -> {
                                            StaticTabsLayout!!.getTabAt(13)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaNext
                                        }
                                        14 -> {
                                            StaticTabsLayout!!.getTabAt(14)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(15)!!.icon!!.alpha = alphaNext
                                        }
                                        15 -> {
                                            StaticTabsLayout!!.getTabAt(15)!!.icon!!.alpha = alphaCurrent
                                            StaticTabsLayout!!.getTabAt(16)!!.icon!!.alpha = alphaNext
                                        }
                                    }
                                }
                            }
                        }

                        override fun onPageSelected(position: Int) {

                        }

                        override fun onPageScrollStateChanged(state: Int) {

                        }

                    })
                }
            } else {
                val throwable = Throwable("Amount of icons must be equal to the number of the Tabs. ")
                try {
                    throw throwable
                } catch (throwable1: Throwable) {
                    throwable1.printStackTrace()
                }


            }
        }


        return this
    }

    fun setTabLayoutScrollable(state: Boolean?): EasyTabsBuilder {
        if (state!!) {
            StaticTabsLayout!!.tabMode = TabLayout.MODE_SCROLLABLE
        } else {
            StaticTabsLayout!!.tabMode = TabLayout.MODE_FIXED
        }
        if (FragmentList.size > 6) {
            StaticTabsLayout!!.tabMode = TabLayout.MODE_SCROLLABLE
        }
        return this
    }

    fun hideAllTitles(hide: Boolean): EasyTabsBuilder {
        this.isHidden = hide
        return this
    }

    fun addTabs(vararg tabs: TabItem): EasyTabsBuilder {
        for (item in tabs) {
            FragmentList.add(item.fragment)
            TitleList.add(item.title!!)
        }
        return this
    }

    fun addIcons(icons: Array<Drawable>): EasyTabsBuilder {
        this.Icons = icons
        return this
    }

    fun addIcons(vararg icons: Int): EasyTabsBuilder {
        this.ResIdIcons = icons
        return this
    }

    fun changeIconPosition(position: String): EasyTabsBuilder {
        val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        when (position) {
            "left" -> {
                lp.gravity = Gravity.RIGHT
                lp.setMargins(0, 0, 0, 2)
            }
            "right" -> {
                lp.gravity = Gravity.END
                lp.setMargins(0, 2, 0, 0)
            }
            "top" -> {
                lp.gravity = Gravity.TOP
                lp.setMargins(2, 0, 0, 0)
            }
            "bottom" -> {
                lp.gravity = Gravity.BOTTOM
                lp.setMargins(0, 0, 2, 0)
            }
            "center" -> {
                lp.gravity = Gravity.CENTER
                lp.setMargins(0, 0, 0, 0)
            }
            else -> {
            }
        }
        val vg = StaticTabsLayout!!.getChildAt(0) as? ViewGroup
        if(vg!=null) {
            val tabsCount = vg.childCount
            for (j in 0 until tabsCount) {
                val vgTab = vg.getChildAt(j) as? ViewGroup
                if(vgTab!=null) {
                    val tabChildsCount = vgTab.childCount
                    for (i in 0 until tabChildsCount) {
                        val tabViewChild = vgTab.getChildAt(i)

                        if (tabViewChild is ImageView) {
                            tabViewChild.setLayoutParams(lp)
                        }
                    }
                }
            }
        }
        return this
    }

    fun addTransformation(reverseDrawingOrder: Boolean, transform: ViewPager.PageTransformer): EasyTabsBuilder {
        StaticViewPager!!.setPageTransformer(reverseDrawingOrder, transform)
        return this
    }

    fun build() {
        val adapter = ViewPagerAdapter(StaticActivity!!.supportFragmentManager, AdapterItem(FragmentList, TitleList, isHidden))
        StaticViewPager!!.adapter = adapter
        StaticTabsLayout!!.setupWithViewPager(StaticViewPager)
        StaticTabsLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                StaticViewPager!!.currentItem = tab.position

                val vg = StaticViewPager!!.getChildAt(0) as? ViewGroup
                if(vg!=null)
                {
                val tabsCount = vg.childCount
                for (j in 0 until tabsCount) {
                    val vgTab = vg.getChildAt(j) as? ViewGroup
                    if(vgTab!=null) {
                        val tabChildCount = vgTab.childCount
                        if (j == tab.position) {
                            for (i in 0 until tabChildCount) {
                                val tabViewChild = vgTab.getChildAt(i)
                                if (tabViewChild is TextView) {
                                    {
                                        tabViewChild.typeface = selectedTypeFace
                                        tabViewChild.typeface = selectedTypeFace
                                    }
                                    tabViewChild.isAllCaps = isAllCaps
                                }
                            }
                        } else {
                            for (i in 0 until tabChildCount) {
                                val tabViewChild = vgTab.getChildAt(i)
                                if (tabViewChild is TextView) {
                                    if (selectedTypeFace != null) {
                                        tabViewChild.typeface = selectedTypeFace
                                    }

                                    tabViewChild.isAllCaps = isAllCaps
                                }
                            }
                        }
                    }
                }
                }




            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        val tab = StaticTabsLayout!!.getTabAt(0)
        val vg = StaticTabsLayout!!.getChildAt(0) as? ViewGroup
        if(vg!=null) {
            val tabsCount = vg.childCount
            for (j in 0 until tabsCount) {
                val vgTab = vg.getChildAt(j) as? ViewGroup
                if (vgTab != null) {
                    val tabChildCount = vgTab.childCount
                    if (j == tab!!.getPosition()) {
                        for (i in 0 until tabChildCount) {
                            val tabViewChild = vgTab.getChildAt(i)
                            if (tabViewChild is TextView) {
                                if (selectedTypeFace != null) {
                                    tabViewChild.typeface = selectedTypeFace
                                }

                                tabViewChild.isAllCaps = isAllCaps
                            }
                        }
                    } else {
                        for (i in 0 until tabChildCount) {
                            val tabViewChild = vgTab.getChildAt(i)
                            if (tabViewChild is TextView) {
                                if (selectedTypeFace != null) {
                                    tabViewChild.typeface = selectedTypeFace
                                }

                                tabViewChild.isAllCaps = isAllCaps
                            }
                        }
                    }
                }
            }
        }

        if (Icons != null) {
            for (icon in Icons!!) {

                try {
                    StaticTabsLayout!!.getTabAt(iconsPosition)!!.icon = icon
                    if (isFade && iconsPosition >= 1) {
                        StaticTabsLayout!!.getTabAt(iconsPosition)!!.icon!!.alpha = 128
                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

                iconsPosition++
            }
        }
        if (ResIdIcons != null) {
            for (icon in ResIdIcons!!) {
                try {
                    StaticTabsLayout!!.getTabAt(iconsPosition)!!.setIcon(icon)
                    if (isFade && iconsPosition >= 1) {
                        StaticTabsLayout!!.getTabAt(iconsPosition)!!.icon!!.alpha = 128
                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                }

                iconsPosition++
            }
        }


    }

    companion object {

        fun with(easyTabs: EasyTabs): EasyTabsBuilder {
            val builder = EasyTabsBuilder()
            builder.StaticTabsLayout = easyTabs.tabLayout
            builder.StaticViewPager = easyTabs.viewPager
            builder.StaticActivity = easyTabs.context as AppCompatActivity
            builder.easyTabs = easyTabs
            return builder
        }
    }


}