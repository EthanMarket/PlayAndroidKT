package com.ethan.playandroidkt.project

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.entity.ProjectBean
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProjectModel : AbsBaseModel() {
    fun requestProjectBean(page: Int, cid: String): Observable<BaseResponse<ProjectBean>> {
        return getApiService().selectProjectBean(page, cid).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestSearchBean(page: Int, keyword: String): Observable<BaseResponse<ProjectBean>> {
        return getApiService().selectSearchBean(page, keyword).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}