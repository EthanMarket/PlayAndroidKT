package com.ethan.core.api.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open  class BaseRxPresenter<T : BaseContract.IBaseView> : BaseContract.IPresenter<T> {
    protected var mView: T? = null
    protected var mDisposable: CompositeDisposable? = null
    protected fun unSubscribe() {
        mDisposable?.dispose();
    }


    open fun addDisposable(subscription: Disposable): Unit {
        mDisposable =mDisposable?: CompositeDisposable()
        mDisposable?.add(subscription);
    }

    override  fun attachView(rootView: T) {
        this.mView = rootView;

    }

    override fun detachView() {
        this.mView = null;
        unSubscribe();
    }
}