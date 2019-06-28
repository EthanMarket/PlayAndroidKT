package com.ethan.core.api.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.ethan.core.api.R
import com.ethan.core.api.ui.view.MultipleStatusView

abstract class BaseNetActivity : AbsBaseActivity() {
    override fun setContentViewId(): Int = R.layout.base_activity_layout
    lateinit var mMultipleStatusView: MultipleStatusView
    protected lateinit var mRecyclerView: RecyclerView
    protected lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    override fun initDecorView() {
        mMultipleStatusView = findViewById(R.id.mMultipleStatusView);
        mMultipleStatusView.showLoading()
        mRecyclerView = findViewById(R.id.base_recyclerView)
        mSwipeRefreshLayout = findViewById(R.id.content_swipeRefreshLayout)
        findViewById<ImageView>(R.id.mToolBackIv).setOnClickListener { finish() }
        initContentView()
    }

    abstract fun initContentView()
}