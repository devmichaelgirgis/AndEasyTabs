package com.devmichaelgirgis.andtabs.customview;


import android.content.Context;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.devmichaelgirgis.andtabs.R;


/**
 * Created by gilgoldzweig on 24/10/2016.
 * Enjoy
 */

public class EasyTabs extends RelativeLayout {
    private TabLayout tabs;
    private ViewPager pager;
    private View rootView;

    public EasyTabs(Context context) {
        super(context);
        rootView = inflate(context, R.layout.easytabs, this);
        tabs = rootView.findViewById(R.id.tabs);
        pager = rootView.findViewById(R.id.pager);


    }

    public EasyTabs(Context context, AttributeSet attrs) {
        super(context, attrs);
        rootView = inflate(context, R.layout.easytabs, this);
        tabs = rootView.findViewById(R.id.tabs);
        pager = rootView.findViewById(R.id.pager);

    }

    public EasyTabs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = inflate(context, R.layout.easytabs, this);
        tabs = rootView.findViewById(R.id.tabs);
        pager = rootView.findViewById(R.id.pager);
    }


    public ViewPager getViewPager() {
        return pager;
    }

    public TabLayout getTabLayout() {
        return tabs;
    }
}
