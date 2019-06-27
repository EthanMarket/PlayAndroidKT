package com.ethan.playandroidkt.category

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.entity.CategoryBean
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoryModel : AbsBaseModel() {
    fun requestCategoryBean(page: Int = 0, cid: String): Observable<BaseResponse<CategoryBean>> {
        return getHomeService().selectCategoryBean(page, cid).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}