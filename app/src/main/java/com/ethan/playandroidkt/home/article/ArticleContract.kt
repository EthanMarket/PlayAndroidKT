package com.ethan.playandroidkt.home.article

import com.ethan.core.api.base.BaseContract
import com.ethan.core.api.entity.ArticleListEntry


interface ArticleContract {
    interface IArticleView : BaseContract.IBaseView {
        fun articleListEntrySuccess(isRefresh: Boolean,entry: ArticleListEntry);
    }

    interface IArticlePresenter : BaseContract.IPresenter<IArticleView> {
        fun requestArticleList(isRefresh: Boolean, page: Int = 0);
    }
}