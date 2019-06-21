package com.ethan.playandroid.home

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.network.BaseResponse
import com.ethan.core.api.entity.ArticleListEntry
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModel : AbsBaseModel() {

    fun requestArticleList(): Observable<BaseResponse<ArticleListEntry>> {
        return getHomeService().selectArticleList(0).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
