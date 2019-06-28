package com.ethan.playandroidkt.home.article

import android.content.Intent
import android.os.Bundle
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.core.api.entity.DataX
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.home.HomeAbsFragment
import com.ethan.playandroidkt.web.WebActivity

class ArticleFragment : HomeAbsFragment(), CommonContract.IView<ArticleListEntry> {

    private val mPresenter: ArticlePresenter  by lazy { ArticlePresenter() }
    private val mArticleList = mutableListOf<DataX>()
    private lateinit var mArticleAdapter: ArticleAdapter

    override fun showError(errorMsg: String) {
        mSwipeRefreshLayout.isRefreshing = false
        mMultipleStatusView.showError()
    }

    override fun responseEntrySuccess(isRefresh: Boolean, entry: ArticleListEntry) {
        if (isRefresh) {
            mArticleList.clear()
            mSwipeRefreshLayout.isRefreshing = false
        } else {
            if (entry.datas.size < 20) {
                mArticleAdapter.loadMoreEnd()
            }
        }
        mArticleList.apply {
            addAll(entry.datas)
            if (size == 0) mMultipleStatusView.showEmpty() else mMultipleStatusView.showContent()
        }
        mArticleAdapter.apply {
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
                startActivity(Intent(activity, WebActivity::class.java).apply {
                    putExtra("link", mArticleList[position].link)
                    putExtra("title", mArticleList[position].title)
                })
            }
            setOnLoadMoreListener({
                mPresenter.requestEntryData(false, mapOf("page" to mArticleList.size / 20))
            }, mRecyclerView)
        }
        mSwipeRefreshLayout.setOnRefreshListener {
            mPresenter.requestEntryData(true, mapOf("page" to 0))
            mArticleAdapter.setEnableLoadMore(false)
        }
    }

    override fun requestDataStart() {
        mPresenter.apply {
            attachView(this@ArticleFragment)
            requestEntryData(true, mapOf("page" to 0))
        }
    }
}