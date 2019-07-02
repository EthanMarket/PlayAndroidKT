package com.ethan.playandroidkt.project

import com.ethan.core.api.base.BaseContract
import com.ethan.core.api.entity.ProjectBean
import com.ethan.core.api.entity.ProjectDetails

interface ProjectContract {

    interface IView : BaseContract.IBaseView {
        fun responseProjectSuccess(isRefresh: Boolean, entry: ProjectBean);
        fun responseSearchSuccess(isRefresh: Boolean, entry: ProjectDetails);
    }


    interface IPresenter : BaseContract.IPresenter<IView> {
        fun requestProjectData(isRefresh: Boolean, pathParam: Map<String, Any> = HashMap());
        fun requestSearchData(isRefresh: Boolean, pathParam: Map<String, Any> = HashMap());
    }
}