package com.ethan.core.api.rxjava

import android.util.Log
import android.widget.Toast
import com.ethan.core.api.base.PlayApplication
import com.ethan.core.api.network.ApiException
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.io.IOException

/**
 * notice：使用的是: 玩Android 开放API，因此采用的接口约束为，玩Android  API
 * Api 接口: https://www.wanandroid.com/blog/show/2
 */
abstract class PlayConsumer<T> : Observer<BaseResponse<T>> {
    private val TAG: String = PlayConsumer::class.java.simpleName
    private val DEFINE_ERROR_CODE = 0 //代表执行成功，不建议依赖任何非0的 errorCode.
    private var mDisposable: Disposable? = null
    override fun onError(e: Throwable) {
        Log.e(TAG, "请求网络错误:" + e.message.toString())
        var errorMsg: String? = null
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable?.dispose()
        }
        when (e) {
            is IOException -> {
                /** 没有网络 */
                errorMsg = "请检查你的网络!"
            }
            is ApiException -> {
                /** 网络请求成功，但是服务器逻辑错误 */
                errorMsg = e.msg
            }
            else -> {
                errorMsg =e.message?: "未知错误"
            }
        }
        errorMsg(errorMsg)
    }

    protected open fun errorMsg(errorMsg: String) {
        Toast.makeText(PlayApplication.context, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun onSubscribe(d: Disposable) {
        mDisposable = d;
    }

    override fun onNext(response: BaseResponse<T>) {
        if (response.errorCode != DEFINE_ERROR_CODE) {
            val apiException = ApiException(response.errorCode, response.errorMsg)
            onError(apiException)
        } else {
            accept(response.data)
        }
    }

    override fun onComplete() {
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable?.dispose()
        }
    }
    abstract fun accept(entry: T)

}


