package com.ethan.playandroidkt.category

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ethan.core.api.base.BaseNetFragment
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.CategoryBean
import com.ethan.core.api.entity.Data
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.web.WebActivity

class CategoryFragment : BaseNetFragment(), CommonContract.IView<CategoryBean> {
    private val mPresenter by lazy { CategoryPresenter() }
    private lateinit var mCid: String
    private val mCategoryList = mutableListOf<Data>()
    private lateinit var mAdapter: CategoryAdapter

    override fun initWidget(savedInstanceState: Bundle?) {
        mCid = arguments!!.getString("cid")
        mSwipeRefreshLayout.setOnRefreshListener {
            requestDataStart()
        }
        mAdapter = CategoryAdapter(R.layout.item_category_fragment, mCategoryList).apply {
            mPresenter.requestEntryData(false, mapOf("page" to mCategoryList.size / 20, "cid" to mCid))
            setOnItemClickListener { _, _, position ->
                startActivity(Intent(activity, WebActivity::class.java).apply {
                    putExtra("link", mCategoryList[position].link)
                    putExtra("title", mCategoryList[position].title)
                })
            }
        }
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mAdapter
        }
    }

    override fun responseEntrySuccess(isRefresh: Boolean, entry: CategoryBean) {
        if (isRefresh) {
            mCategoryList.clear()
            mSwipeRefreshLayout.isRefreshing = false
        }
        mCategoryList.addAll(entry.datas)
        mAdapter.apply {
            loadMoreComplete()
            setEnableLoadMore(true)
            notifyDataSetChanged()
        }
        if (entry.datas.size < 20) {
            mAdapter.loadMoreEnd()
        }
        mMultipleStatusView.showContent()
    }

    override fun showError(errorMsg: String) {
        mSwipeRefreshLayout.isRefreshing = false
        mMultipleStatusView.showError()
    }

    override fun requestDataStart() {
        mCid = arguments!!.getString("cid")
        mPresenter.attachView(this)
        mPresenter.requestEntryData(true, mapOf("page" to 0, "cid" to mCid))
    }
}

object CategoryFactory {
    fun createCategoryFragment(cid: String): CategoryFragment {
        return CategoryFragment().apply {
            arguments = Bundle().apply {
                putString("cid", cid);
            }
        }
    }
}