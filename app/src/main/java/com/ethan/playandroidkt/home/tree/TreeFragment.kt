package com.ethan.playandroidkt.home.tree

import android.content.Intent
import android.os.Bundle
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.TreeBean
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.category.CategoryActivity
import com.ethan.playandroidkt.home.HomeAbsFragment

class TreeFragment : HomeAbsFragment(), CommonContract.IView<List<TreeBean>> {

    private val mTreeBeanList = mutableListOf<TreeBean>()
    private val mPresenter: TreePresenter by lazy { TreePresenter() }
    private lateinit var mAdapter: TreeAdapter
    override fun responseEntrySuccess(isRefresh: Boolean, entry: List<TreeBean>) {
        mSwipeRefreshLayout.isRefreshing = false
        mMultipleStatusView.apply {
            if (entry.isEmpty()) {
                showEmpty()
            } else {
                showContent()
                mTreeBeanList.clear()
                mTreeBeanList.addAll(entry)
                mAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun showError(errorMsg: String) {
        mSwipeRefreshLayout.isRefreshing = false
        mMultipleStatusView.showError()
    }

    override fun initWidget(savedInstanceState: Bundle?) {
        mAdapter = TreeAdapter(R.layout.item_tree_fragment, mTreeBeanList).apply {
            setOnItemClickListener { _, _, position ->
                showToast(mTreeBeanList[position].toString())
                startActivity(Intent(activity, CategoryActivity::class.java).apply {
                    putExtra("TreeBean",mTreeBeanList[position])
                })
            }
            setEnableLoadMore(false)
        }
        mRecyclerView.adapter = mAdapter;
        mSwipeRefreshLayout.setOnRefreshListener {
            mPresenter.requestEntryData(true)
        }
    }

    override fun requestDataStart() {
        mPresenter.apply {
            attachView(this@TreeFragment)
            requestEntryData(true)
        }
    }
}