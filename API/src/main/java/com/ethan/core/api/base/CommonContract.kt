package com.ethan.core.api.base

interface CommonContract {

    interface IView <T> : BaseContract.IBaseView {
        fun responseEntrySuccess(isRefresh: Boolean, entry: T);
    }


    interface IPresenter<T> : BaseContract.IPresenter<IView<T>> {
        fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any> = HashMap());
    }
}