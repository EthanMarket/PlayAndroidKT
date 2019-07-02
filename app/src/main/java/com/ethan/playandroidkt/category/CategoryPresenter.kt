package com.ethan.playandroidkt.category

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.CategoryBean
import com.ethan.core.api.rxjava.PlayConsumer

class CategoryPresenter : BaseRxPresenter<CommonContract.IView<CategoryBean>>(),
    CommonContract.IPresenter<CategoryBean> {
    private val mModel: CategoryModel by lazy { CategoryModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestCategoryBean(pathParam["page"] as Int, pathParam["cid"] as String).subscribe(
            object : PlayConsumer<CategoryBean>() {
                override fun accept(entry: CategoryBean) {
                    mView?.responseEntrySuccess(isRefresh, entry)
                }
            }
        )
    }
}