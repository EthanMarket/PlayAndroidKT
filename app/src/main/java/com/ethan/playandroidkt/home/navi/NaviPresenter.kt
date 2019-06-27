package com.ethan.playandroidkt.home.navi

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.NaviBean

class NaviPresenter : BaseRxPresenter<CommonContract.IView<List<NaviBean>>>(),
    CommonContract.IPresenter<List<NaviBean>> {

    private val mModel: NaviModel by lazy { NaviModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestNaviList().subscribe { entry ->
            mView?.responseEntrySuccess(isRefresh, entry.data)
        }
    }

}