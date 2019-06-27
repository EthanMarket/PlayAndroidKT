package com.ethan.playandroidkt.home.navi

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.entity.NaviBean
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NaviModel : AbsBaseModel() {
    fun requestNaviList(): Observable<BaseResponse<List<NaviBean>>> {
        return getHomeService().selectNaviList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}