package com.ethan.playandroidkt.search

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.entity.HotkeyBean
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchModel : AbsBaseModel() {
    fun requestHotkeyList(): Observable<BaseResponse<List<HotkeyBean>>> {
        return getApiService().selectHotkeyList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}