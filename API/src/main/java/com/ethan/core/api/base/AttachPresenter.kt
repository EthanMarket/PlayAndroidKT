package com.ethan.core.api.base

import com.ethan.core.api.base.BaseContract.IPresenter

open class AttachPresenter<T : BaseContract.IBaseView> : IPresenter<T> {
    var mDataComplete: T? = null

    override fun attachView(data: T) {
        mDataComplete = data
    }

    override fun detachView() {
        mDataComplete = null
    }
}