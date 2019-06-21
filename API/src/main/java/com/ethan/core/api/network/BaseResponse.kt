package com.ethan.core.api.network

data class BaseResponse<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)