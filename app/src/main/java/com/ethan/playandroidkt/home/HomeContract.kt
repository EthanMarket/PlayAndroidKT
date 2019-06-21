package com.ethan.playandroid.home

import com.ethan.core.api.base.BaseContract
import com.ethan.core.api.entity.ArticleListEntry

interface HomeContract {

    interface IHomeView : BaseContract.IBaseView {
        fun articleListEntrySuccess(entry: ArticleListEntry);
    }

    interface IHomePresenter : BaseContract.IPresenter<IHomeView> {
        fun requestArticleList();
    }
}