package com.ethan.playandroidkt.home.tree

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.TreeBean

class TreePresenter : BaseRxPresenter<CommonContract.IView<List<TreeBean>>>(), CommonContract.IPresenter<List<TreeBean>> {
    private val mModel: TreeModel by lazy { TreeModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestTreeList().subscribe { entry ->
            mView?.responseEntrySuccess(isRefresh, entry.data)
        }
    }
}