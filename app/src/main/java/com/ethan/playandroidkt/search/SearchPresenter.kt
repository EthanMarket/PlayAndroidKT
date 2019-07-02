package com.ethan.playandroidkt.search

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.HotkeyBean
import com.ethan.core.api.rxjava.PlayConsumer

class SearchPresenter : BaseRxPresenter<CommonContract.IView<List<HotkeyBean>>>(),
    CommonContract.IPresenter<List<HotkeyBean>> {
    private val mModel by lazy { SearchModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestHotkeyList().subscribe(
            object : PlayConsumer<List<HotkeyBean>>() {
                override fun accept(entry: List<HotkeyBean>) {
                    mView?.responseEntrySuccess(isRefresh, entry)
                }
            }
        )
    }
}