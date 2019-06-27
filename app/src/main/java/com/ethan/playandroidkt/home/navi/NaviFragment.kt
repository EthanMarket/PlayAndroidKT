package com.ethan.playandroidkt.home.navi

import android.os.Bundle
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.NaviBean
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.home.HomeAbsFragment

class NaviFragment : HomeAbsFragment(), CommonContract.IView<List<NaviBean>> {
    private lateinit var mAdapter: NaviAdapter
    private var mNaviList = mutableListOf<NaviBean>()
    private var mSectionList= mutableListOf<NaviSection>()
    private val mPresenter: NaviPresenter by lazy { NaviPresenter() }
    override fun responseEntrySuccess(isRefresh: Boolean, entry: List<NaviBean>) {
        mSwipeRefreshLayout.isRefreshing = false
        if (isRefresh) {
            mNaviList.clear()
        }
        mNaviList.apply {
            addAll(entry)
            mMultipleStatusView.apply {
                if (size == 0) showEmpty() else showContent()
            }
            forEach {
                mSectionList.add(NaviSection(true,it.name))
                it.articles.forEach {
//                    println("=====articles======>>"+it.toString())
                    mSectionList.add(NaviSection(article=it))
                }
            }

        }
        mAdapter?.notifyDataSetChanged()
    }

    override fun initWidget(savedInstanceState: Bundle?) {
        mPresenter.apply {
            attachView(this@NaviFragment)
            requestEntryData(true)
            mSwipeRefreshLayout.setOnRefreshListener {
                requestEntryData(true)
            }
        }
        mAdapter = NaviAdapter(R.layout.item_navi_second_fragment,R.layout.item_navi_header_fragment, mSectionList)
        mAdapter.apply {
            setOnItemClickListener { _, _, position ->
                showToast(mNaviList[position].toString())
            }
            mRecyclerView.adapter = this
        }
    }

    override fun requestDataStart() {
        mPresenter.apply {
            requestEntryData(true)

        }
    }
}