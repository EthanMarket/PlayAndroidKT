package com.ethan.playandroidkt.project

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import com.ethan.core.api.base.BaseNetActivity
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.ProjectBean
import com.ethan.core.api.entity.ProjectDetails
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.web.WebActivity

class ProjectActivity : BaseNetActivity(), CommonContract.IView<ProjectBean> {
    private val mPresenter by lazy { ProjectPresenter() }
    private val mDataList = mutableListOf<ProjectDetails>()
    private val mAdapter by lazy {
        ProjectAdapter(R.layout.item_project_activity, mDataList);
    }
    private lateinit var mCid: String

    override fun initContentView() {

        findViewById<TextView>(R.id.mToolBarTv).text = intent.getStringExtra("name")
        mCid = intent.getStringExtra("cid")
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProjectActivity)
            adapter = mAdapter
        }
        mPresenter.apply {
            attachView(this@ProjectActivity)
            requestEntryData(true, 1)
        }
        mSwipeRefreshLayout.setOnRefreshListener {
            requestEntryData(true, 1)
        }
        mAdapter.apply {
            setOnItemClickListener { _, _, position ->
                startActivity(
                    Intent(this@ProjectActivity, WebActivity::class.java).apply {
                        putExtra("link", mDataList[position].link)
                        putExtra("title", mDataList[position].title)
                    }
                )
            }
            setOnLoadMoreListener({
                requestEntryData(false, mDataList.size / 15 + 1)
            }, mRecyclerView)
        }
    }


    fun requestEntryData(isRefresh: Boolean, page: Int) {
        mPresenter.requestEntryData(isRefresh, mapOf("page" to page, "cid" to mCid))
    }

    override fun responseEntrySuccess(isRefresh: Boolean, entry: ProjectBean) {
        mSwipeRefreshLayout.isRefreshing = false
        mMultipleStatusView.showContent()
        mDataList.apply {
            if (isRefresh) {
                clear()
            }
            addAll(entry.datas)
        }
        mAdapter.apply {
            loadMoreComplete()
            setEnableLoadMore(true)
            notifyDataSetChanged()
            if (entry.datas.size < 15) {
                loadMoreEnd()
            }
        }
    }

    override fun showError(errorMsg: String) {
        mSwipeRefreshLayout.isRefreshing = false
        mMultipleStatusView.showError()
    }
}



