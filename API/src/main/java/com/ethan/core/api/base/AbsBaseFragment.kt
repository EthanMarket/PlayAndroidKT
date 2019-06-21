package com.ethan.core.api.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ethan.core.api.ui.LoadingDialog

/**
 * 抽象fragment
 */
abstract class AbsBaseFragment : Fragment() {
    private var mLoadingView: LoadingDialog? = null
    private var rootView: ViewGroup? = null
    private var mHaveLoadData: Boolean = false//是否加载过数据
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var restId: Int = getRootViewId()
        rootView = rootView ?: inflater.inflate(restId, container, false) as ViewGroup?;
        addTopView(rootView, inflater)
        return rootView
    }

    protected open fun addTopView(rootView: ViewGroup?, inflater: LayoutInflater) {

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWidget(savedInstanceState);
        processLogic();
    }

    protected open fun processLogic() {}
    @LayoutRes
    protected abstract fun getRootViewId(): Int

    protected abstract fun initWidget(savedInstanceState: Bundle?)
    protected fun showLoadingView(): Unit {
        mLoadingView = mLoadingView ?: LoadingDialog(activity!!.baseContext);
        mLoadingView?.setCanceledOnTouchOutside(false)
        mLoadingView?.show()
    }

    protected fun dismissLoadingView(): Unit {
        mLoadingView?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!mHaveLoadData && isVisibleToUser) {
            requestDataStart()
            mHaveLoadData = true
        }

    }

    abstract fun requestDataStart()//懒加载数据

    protected fun getViewById(id: Int): View? {
        return rootView?.findViewById<View>(id)
    }

    protected fun showToast(msg: String): Unit {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}