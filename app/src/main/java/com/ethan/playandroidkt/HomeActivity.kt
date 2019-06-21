package com.ethan.playandroidkt

import com.ethan.core.api.base.AbsBaseActivity
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.playandroid.home.HomeContract
import com.ethan.playandroid.home.HomePresenter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AbsBaseActivity(), HomeContract.IHomeView {
    override fun articleListEntrySuccess(entry: ArticleListEntry) {
        mLoadTv.text = entry.toString()
        dismissLoadingView()
    }

    override fun showError(errorMsg: String) {
    }

    private val mPresenter: HomePresenter by lazy { HomePresenter() }

    override fun setContentViewId(): Int = R.layout.activity_home

    override fun initDecorView() {
        mPresenter.attachView(this)
        mLoadBtn.setOnClickListener(
            {
                showLoadingView()
                mPresenter.requestArticleList()
            }
        )
    }
}


