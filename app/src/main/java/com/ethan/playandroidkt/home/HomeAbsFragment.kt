package com.ethan.playandroidkt.home

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ethan.core.api.base.BaseNetFragment
import com.ethan.core.api.rxjava.rxbus.RxBus
import com.ethan.playandroidkt.R


abstract class HomeAbsFragment : BaseNetFragment() {
    protected lateinit var mRecyclerView: RecyclerView
    protected lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    override fun initView(rootView: ViewGroup?) {
        super.initView(rootView)
        mRecyclerView = getViewById(R.id.base_recyclerView) as RecyclerView
        mSwipeRefreshLayout = getViewById(R.id.content_swipeRefreshLayout) as SwipeRefreshLayout
        mRecyclerView.layoutManager = LinearLayoutManager(getActivity());
        RxBus.getDefault().post("mRecyclerView")
    }

    fun getRecyclerView(): RecyclerView {
        return mRecyclerView
    }

    open fun showError(errorMsg: String) {
        mSwipeRefreshLayout.isRefreshing = false
        mMultipleStatusView.showError()
    }

}