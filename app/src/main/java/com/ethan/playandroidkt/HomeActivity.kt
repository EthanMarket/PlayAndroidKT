package com.ethan.playandroidkt

import android.support.design.widget.BottomNavigationView
import com.ethan.core.api.base.AbsBaseActivity
import com.ethan.core.api.base.AbsBaseFragment
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.playandroid.home.HomeContract
import com.ethan.playandroid.home.HomePresenter
import com.ethan.playandroidkt.home.homepage.HomePageFragment
import com.ethan.playandroidkt.home.homepage.TabFragmentPageAdapter
import com.ethan.playandroidkt.home.knowledge.KnowledgeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AbsBaseActivity(), HomeContract.IHomeView {
    override fun articleListEntrySuccess(entry: ArticleListEntry) {
        dismissLoadingView()
    }

    override fun showError(errorMsg: String) {
    }

    private val mPresenter: HomePresenter by lazy { HomePresenter() }

    override fun setContentViewId(): Int = R.layout.activity_home
    override fun processLogic() {
        super.processLogic()
        mHomeBottomNavLayout.setOnNavigationItemSelectedListener(mNavSelectListener)
        mHomeViewPager.adapter = TabFragmentPageAdapter(getSupportFragmentManager(), getFragmentList())
    }

    private fun getFragmentList(): List<AbsBaseFragment> {
        val list = mutableListOf<AbsBaseFragment>()
        list.add(HomePageFragment())
        list.add(KnowledgeFragment())
        list.add(HomePageFragment())
        list.add(HomePageFragment())
        return list
    }

    private val mNavSelectListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.home_bottom_homepage -> {
                mLoadTv.setText("home_bottom_homepage")
                return@OnNavigationItemSelectedListener true
            }
            R.id.home_bottom_knowledge -> {
                mLoadTv.setText("home_bottom_knowledge")
                return@OnNavigationItemSelectedListener true
            }
            R.id.home_bottom_project -> {
                mLoadTv.setText("home_bottom_engin")
                return@OnNavigationItemSelectedListener true
            }
            R.id.home_bottom_mine -> {
                mLoadTv.setText("home_bottom_mine")
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }


    override fun initDecorView() {
        mPresenter.attachView(this)
    }
}


