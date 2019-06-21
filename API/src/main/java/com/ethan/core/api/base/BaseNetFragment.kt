package com.ethan.core.api.base

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ethan.core.api.R
import kotlinx.android.synthetic.main.base_fragment_layout.*

/**
 * 根据不同网络状态，显示不同view状态
 *
 */
abstract class BaseNetFragment : AbsBaseFragment() {

    override fun getRootViewId(): Int {
        return R.layout.base_fragment_layout
    }
    /**
     * 通过base_fragment_layout 布局
     * 添加ContentView，直接控制状态
     */
    override fun addTopView(rootView: ViewGroup?, inflater: LayoutInflater) {
        super.addTopView(rootView, inflater)
        mMultipleStatusView.addView(inflater?.inflate(getContentViewId(), null))
        mMultipleStatusView.setOnRetryClickListener(mOnRetryClickListener)
    }

    @LayoutRes
    protected abstract fun getContentViewId(): Int

   var mOnRetryClickListener: View.OnClickListener =View.OnClickListener(){
       requestDataStart()
    }
}