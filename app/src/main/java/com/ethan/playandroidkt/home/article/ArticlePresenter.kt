package com.ethan.playandroidkt.home.article

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.ArticleListEntry
import com.ethan.core.api.rxjava.PlayConsumer

class ArticlePresenter : BaseRxPresenter<CommonContract.IView<ArticleListEntry>>(),
    CommonContract.IPresenter<ArticleListEntry> {

    private val mModel: ArticleModel by lazy { ArticleModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestArticleList(pathParam["page"] as Int).subscribe(object : PlayConsumer<ArticleListEntry>() {
            override fun accept(entry: ArticleListEntry) {
                mView?.responseEntrySuccess(isRefresh, entry)
            }
        })
    }
}