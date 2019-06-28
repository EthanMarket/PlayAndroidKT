package com.ethan.core.api.network.api

import com.ethan.core.api.entity.*
import com.ethan.core.api.network.BaseResponse
import io.reactivex.Observable
import retrofit2.http.*

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
    fun selectCategoryBean(@Path("page") page: Int, @Query("cid") cid: String): Observable<BaseResponse<CategoryBean>>


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
     * 4.2 项目列表数据
    某一个分类下项目列表数据，分页展示
    https://www.wanandroid.com/project/list/1/json?cid=294
    方法：GET
    参数：
    cid 分类的id，上面项目分类接口
    页码：拼接在链接中，从1开始。
     */

    @GET("/project/list/{page}/json")
    fun selectProjectBean(@Path("page") page: Int = 1, @Query("cid") cid: String): Observable<BaseResponse<ProjectBean>>

    /**
    3. 导航
    3.1 导航数据
    https://www.wanandroid.com/navi/json
    方法：GET
    参数：无
     */
    @GET("/navi/json")
    fun selectNaviList(): Observable<BaseResponse<List<NaviBean>>>


    /**
     * 7. 搜索
    7.1 搜索
    https://www.wanandroid.com/article/query/0/json
    方法：POST
    参数：
    页码：拼接在链接上，从0开始。
    k ： 搜索关键词
     */
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    fun selectSearchBean(@Path("page") page: Int = 0, @Field("k") keyWord: String): Observable<BaseResponse<ProjectBean>>

}