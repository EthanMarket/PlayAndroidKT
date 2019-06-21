package com.ethan.core.api.network.api

import com.ethan.core.api.network.BaseResponse
import com.ethan.core.api.entity.ArticleListEntry
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/article/list/{page}/json")
    fun selectArticleList(@Path("page") page: Int): Observable<BaseResponse<ArticleListEntry>>

}