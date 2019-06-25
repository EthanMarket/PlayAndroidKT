package com.ethan.playandroidkt.home.article

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.core.api.rxjava.PlayConsumer

class ArticlePresenter:BaseRxPresenter<ArticleContract.IArticleView>(),ArticleContract.IArticlePresenter{
    private val mModel: ArticleModel by lazy { ArticleModel() }
    override fun requestArticleList(isRefresh: Boolean,page: Int) {
        mModel.requestArticleList(page).subscribe(object : PlayConsumer<ArticleListEntry>() {
            override fun accept(entry: ArticleListEntry) {
                mView?.articleListEntrySuccess(isRefresh,entry)
            }
        });
    }
}