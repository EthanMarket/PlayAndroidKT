package com.ethan.playandroidkt.home.engineering

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.EngineeringBean
import com.ethan.core.api.rxjava.PlayConsumer

class EngineeringPresenter : BaseRxPresenter<CommonContract.IView<List<EngineeringBean>>>(),
    CommonContract.IPresenter<List<EngineeringBean>> {

    private val mModel: EngineeringModel by lazy { EngineeringModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestEngineeringList().subscribe(
            object : PlayConsumer<List<EngineeringBean>>() {
                override fun accept(entry: List<EngineeringBean>) {
                    mView?.responseEntrySuccess(isRefresh, entry)
                }
            }
        )
    }
}