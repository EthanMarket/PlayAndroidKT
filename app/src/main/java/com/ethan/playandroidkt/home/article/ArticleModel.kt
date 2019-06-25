package com.ethan.playandroidkt.home.article

import com.ethan.core.api.base.AbsBaseModel
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleModel : AbsBaseModel() {

    fun requestArticleList(page: Int = 0): Observable<BaseResponse<ArticleListEntry>> {
        return getHomeService().selectArticleList(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}