package com.ethan.playandroid.home

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.core.api.rxjava.PlayConsumer

class HomePresenter : BaseRxPresenter<HomeContract.IHomeView>(), HomeContract.IHomePresenter {

    private val mModel: HomeModel by lazy { HomeModel() }
    override fun requestArticleList() {
        println("requestArticleList=============")
        mModel.requestArticleList().subscribe(object : PlayConsumer<ArticleListEntry>() {
            override fun accept(entry: ArticleListEntry) {
                println("entry=============" + entry.toString())
                mView?.articleListEntrySuccess(entry)
            }
        });
    }
}