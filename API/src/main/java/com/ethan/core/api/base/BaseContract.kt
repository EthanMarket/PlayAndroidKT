package com.ethan.core.api.base

/**
 * 契约
 * 接口回调
 * 1、IPresenter 由 view绑定到model
 * 2、IBaseView，任务结束，由model回调到view
 */
interface BaseContract {
    interface IPresenter<in T: IBaseView> {
        fun attachView(rootView: T)
        fun detachView();
    }

    interface IBaseView {
        fun showError(errorMsg: String)
    }
}