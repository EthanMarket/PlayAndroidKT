package com.ethan.playandroidkt.project

import android.text.TextUtils
import com.ethan.core.api.base.BaseRxPresenter
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.ProjectBean
import com.ethan.core.api.network.BaseResponse
import com.ethan.core.api.rxjava.PlayConsumer
import io.reactivex.Observable

class ProjectPresenter : BaseRxPresenter<CommonContract.IView<ProjectBean>>(), CommonContract.IPresenter<ProjectBean> {
    private val mModel: ProjectModel by lazy { ProjectModel() }
    override fun requestEntryData(isRefresh: Boolean, pathParam: Map<String, Any>) {
        var mObservable: Observable<BaseResponse<ProjectBean>>? = null
        if (pathParam["cid"] != null) {
            mObservable = mModel.requestProjectBean(pathParam["page"] as Int, pathParam["cid"] as String);
        } else {
            mObservable = mModel.requestSearchBean(pathParam["page"] as Int, pathParam["keyword"] as String)
        }
        println("=======keyword==========>>>"+pathParam["keyword"])
        mObservable?.subscribe(
            object : PlayConsumer<ProjectBean>() {
                override fun accept(entry: ProjectBean) {
                    mView?.responseEntrySuccess(isRefresh, entry)
                }
            }
        )
    }
}