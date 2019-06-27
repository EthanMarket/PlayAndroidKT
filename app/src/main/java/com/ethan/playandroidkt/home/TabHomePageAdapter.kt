package com.ethan.playandroidkt.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ethan.core.api.base.AbsBaseFragment

class TabHomePageAdapter(fm: FragmentManager, mFragmentList: List<AbsBaseFragment>, mTitleList: List<String>) :
    FragmentPagerAdapter(fm) {
    private val mFragmentList = mFragmentList
    private val mTitleList = mTitleList
    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position);
    }

    override fun getCount(): Int {
        return mFragmentList.size;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitleList.get(position)
    }

}