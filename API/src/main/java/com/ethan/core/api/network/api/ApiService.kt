package com.ethan.core.api.network.api

import com.ethan.core.api.entity.*
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 使用wanAndroid 提供API
 *https://www.wanandroid.com/blog/show/2
 *
 *
 */
interface ApiService {
    /**
     * 首页文章
     */
    @GET("/article/list/{page}/json")
    fun selectArticleList(@Path("page") page: Int): Observable<BaseResponse<ArticleListEntry>>

    /**
     * 2. 体系
    2.1 体系数据
    https://www.wanandroid.com/tree/json
    方法：GET
    参数：无
     */
    @GET("/tree/json")
    fun selectTreeList(): Observable<BaseResponse<List<TreeBean>>>

    /**
     * 2.2 知识体系下的文章
    https://www.wanandroid.com
    方法：GET
    参数：
    cid 分类的id，上述二级目录的id
    页码：拼接在链接上，从0开始。
     */

    @GET("/article/list/{page}/json")
    fun selectCategoryBean(@Path("page") page: Int,@Query("cid") cid:String): Observable<BaseResponse<CategoryBean>>


    /**
    4. 项目
    4.1 项目分类
    https://www.wanandroid.com/project/tree/json
    方法： GET
    参数： 无
     */
    @GET("/project/tree/json")
    fun selectEngineeringList(): Observable<BaseResponse<List<EngineeringBean>>>

    /**
    3. 导航
    3.1 导航数据
    https://www.wanandroid.com/navi/json
    方法：GET
    参数：无
     */
    @GET("/navi/json")
    fun selectNaviList(): Observable<BaseResponse<List<NaviBean>>>

}