package com.ethan.playandroidkt.project

import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.ProjectBean
import com.ethan.core.api.rxjava.PlayConsumer

class ProjectPresenter : BaseRxPresenter<CommonContract.IView<ProjectBean>>(), CommonContract.IPresenter<ProjectBean> {
    private val mModel: ProjectModel by lazy { ProjectModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        mModel.requestProjectBean(pathParam["page"] as Int, pathParam["cid"] as String).subscribe(
            object : PlayConsumer<ProjectBean>() {
                override fun accept(entry: ProjectBean) {
                    mView?.responseEntrySuccess(isRefresh, entry)
                }
            }
        )
    }
}