package com.ethan.core.api.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ethan.core.api.R
import com.ethan.core.api.ui.view.MultipleStatusView

/**
 * 根据不同网络状态，显示不同view状态
 *
 */
abstract class BaseNetFragment : AbsBaseFragment() {
    lateinit var mMultipleStatusView: MultipleStatusView
    protected lateinit var mRecyclerView: RecyclerView
    protected lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    override fun getRootViewId(): Int {
        return R.layout.base_fragment_layout
    }

    /**
     * 通过base_fragment_layout 布局
     * 添加ContentView，直接控制状态
     */
    override fun initView(rootView: ViewGroup?) {
        super.initView(rootView)
        mMultipleStatusView = getViewById(R.id.mMultipleStatusView) as MultipleStatusView;
        mRecyclerView = getViewById(R.id.base_recyclerView)
        mSwipeRefreshLayout = getViewById(R.id.content_swipeRefreshLayout)
        mMultipleStatusView.apply {
            setOnRetryClickListener { requestDataStart() }
            showLoading()
        }
    }
}