package com.ethan.playandroidkt.home.engineering

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.entity.EngineeringBean
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EngineeringModel : AbsBaseModel() {
    fun requestEngineeringList(): Observable<BaseResponse<List<EngineeringBean>>> {
        return getApiService().selectEngineeringList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}