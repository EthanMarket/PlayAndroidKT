package com.ethan.playandroidkt.home.homepage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ethan.core.api.base.AbsBaseFragment

class TabFragmentPageAdapter(fm: FragmentManager, mFragmentList: List<AbsBaseFragment>) : FragmentPagerAdapter(fm) {
    private val mFragmentList = mFragmentList
    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position);
    }

    override fun getCount(): Int {
        return mFragmentList.size;
    }

}