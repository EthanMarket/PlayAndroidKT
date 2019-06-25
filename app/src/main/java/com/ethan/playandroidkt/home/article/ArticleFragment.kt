package com.ethan.playandroidkt.home.article

import android.os.Bundle
import android.widget.AdapterView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.core.api.entity.DataX
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.home.HomeAbsFragment

class ArticleFragment : HomeAbsFragment(), ArticleContract.IArticleView {

    private val mPresenter: ArticlePresenter  by lazy { ArticlePresenter() }
    private val mArticleList = mutableListOf<DataX>()
    private lateinit var mArticleAdapter: ArticleAdapter

    override fun showError(errorMsg: String) {
        mMultipleStatusView.showError()
    }

    override fun articleListEntrySuccess(isRefresh: Boolean, entry: ArticleListEntry) {
        if (isRefresh) {
            mArticleList.clear()
            mSwipeRefreshLayout.isRefreshing = false
        } else {
            if (entry.datas.size < 20) {
                mArticleAdapter.loadMoreEnd()
            }
        }
        mArticleList.run {
            addAll(entry.datas)
            if (size == 0) mMultipleStatusView.showEmpty() else mMultipleStatusView.showContent()
        }
        mArticleAdapter.run {
            loadMoreComplete()
            setEnableLoadMore(true)
            notifyDataSetChanged()
        }
    }

    override fun initWidget(savedInstanceState: Bundle?) {
        mArticleAdapter = ArticleAdapter(R.layout.item_article_fragment, mArticleList)
        mRecyclerView.adapter = mArticleAdapter
        mArticleAdapter.apply {
            setOnItemClickListener { _, _, position ->
                showToast("position" + mArticleList.get(position))
            }
            setOnLoadMoreListener({
                mPresenter.requestArticleList(false, mArticleList.size / 20)
            }, mRecyclerView)
        }
        mSwipeRefreshLayout.setOnRefreshListener {
            mPresenter.requestArticleList(true)
            mArticleAdapter.setEnableLoadMore(false)
        }
    }

    override fun requestDataStart() {
        mPresenter.run {
            attachView(this@ArticleFragment)
            requestArticleList(true)
        }
    }

}