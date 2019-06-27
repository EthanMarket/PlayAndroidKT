package com.ethan.playandroidkt.home.tree

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.entity.TreeBean
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TreeModel : AbsBaseModel() {
    fun requestTreeList(): Observable<BaseResponse<List<TreeBean>>> =
        getHomeService().selectTreeList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}