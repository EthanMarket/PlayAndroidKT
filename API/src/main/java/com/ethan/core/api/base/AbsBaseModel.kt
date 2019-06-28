package com.ethan.core.api.base

import com.ethan.core.api.network.NetWork
import com.ethan.core.api.network.api.ApiService

abstract class AbsBaseModel {
    protected fun getApiService(): ApiService = NetWork.getBaseRetrofit().create(ApiService::class.java)

}