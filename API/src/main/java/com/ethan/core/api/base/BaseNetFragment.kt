package com.ethan.core.api.base

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ethan.core.api.R
import com.ethan.core.api.ui.view.MultipleStatusView

/**
 * 根据不同网络状态，显示不同view状态
 *
 */
abstract class BaseNetFragment : AbsBaseFragment() {
    lateinit var mMultipleStatusView: MultipleStatusView
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
        mMultipleStatusView.apply {
            setOnRetryClickListener { requestDataStart() }
            showLoading()
        }
    }
}