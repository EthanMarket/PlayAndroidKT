package com.ethan.playandroidkt

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.view.View
import com.ethan.core.api.base.AbsBaseActivity
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.core.api.rxjava.rxbus.RxBus
import com.ethan.playandroid.home.HomeContract
import com.ethan.playandroid.home.HomePresenter
import com.ethan.playandroidkt.home.HomeAbsFragment
import com.ethan.playandroidkt.home.TabFragmentPageAdapter
import com.ethan.playandroidkt.home.article.ArticleFragment
import com.ethan.playandroidkt.home.tree.TreeFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AbsBaseActivity(), HomeContract.IHomeView, AppBarLayout.OnOffsetChangedListener {

    private val mFragmentList = mutableListOf<HomeAbsFragment>()
    override fun articleListEntrySuccess(entry: ArticleListEntry) {
        dismissLoadingView()
    }

    override fun showError(errorMsg: String) {
    }

    private val mPresenter: HomePresenter by lazy { HomePresenter() }

    override fun setContentViewId(): Int = R.layout.activity_home
    override fun processLogic() {
        super.processLogic()
        mAppBarLayout.addOnOffsetChangedListener(this)
        mHomeViewPager.adapter = TabFragmentPageAdapter(
            supportFragmentManager,
            getFragmentList(),
            getResources().getStringArray(R.array.home_activity_fragment_title).asList()
        )
        mHomeViewPager.setOffscreenPageLimit(5);
        mHomeTabLayout.setupWithViewPager(mHomeViewPager);
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        RxBus.getDefault().subscribe(this, object : RxBus.Callback<String>() {
            override fun onEvent(s: String) {
                for (homeAbsFragment in mFragmentList) {
                    homeAbsFragment?.getRecyclerView()?.setOnTouchListener { view, motionEvent ->
                        disDrag()
                        false
                    }
                }
            }
        })
    }

    private fun disDrag() {
        val laidOut = ViewCompat.isLaidOut(mAppBarLayout)
        if (laidOut) {
            val params = mAppBarLayout.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior as AppBarLayout.Behavior?
            behavior!!.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
                override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                    return false
                }
            })
        }
    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val offset = Math.abs(verticalOffset)
        var total = appBarLayout!!.getTotalScrollRange();
        var ratio = constrain(1 - Math.abs(offset) * 1.0f / total, 0f, 1f);
        mMiddleGridLayout.alpha = constrain(1 - Math.abs(offset) * 1f / (0.65f * appBarLayout.height), 0f, 1f)
        mToolbarUpperLayout.run {
            alpha = Math.max((ratio - 0.5f) / 0.5f, 0f)
            if (offset <= total / 2) {
                visibility = View.VISIBLE;
            } else {
                visibility = View.GONE;
            }
        }
        mToolbarBellowLayout.run {
            alpha = 1 - Math.min(1f, ratio / 0.5f)
            if (offset <= total / 2) {
                visibility = View.GONE;
            } else {
                visibility = View.VISIBLE;
            }
        }
    }

    fun constrain(amount: Float, low: Float, high: Float): Float {
        var ret = if (amount < low) low else amount
        ret = if (ret > high) high else ret
        return ret
    }

    private fun getFragmentList() = mFragmentList.apply {
        add(ArticleFragment())
        add(TreeFragment())
        add(ArticleFragment())
        add(ArticleFragment())
        add(ArticleFragment())
    }

    override fun initDecorView() {
        mPresenter.attachView(this)
    }
}


