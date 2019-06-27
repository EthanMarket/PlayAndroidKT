package com.ethan.playandroidkt.category

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.CategoryBean

class CategoryPresenter : BaseRxPresenter<CommonContract.IView<CategoryBean>>(),
    CommonContract.IPresenter<CategoryBean> {
    private val mModel: CategoryModel by lazy { CategoryModel() }

    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestCategoryBean(pathParam["page"] as Int, pathParam["cid"] as String).subscribe { entry ->
            mView?.responseEntrySuccess(isRefresh, entry.data)
        }
    }

}