package com.ethan.core.api

import com.ethan.core.api.BuildConfig

/**
 * 全局类
 */
object Global {
    var BASE_URL: String = "http://www.wanandroid.com"


    fun isPrd(): Boolean {
        return BuildConfig.IS_BETA
    }
}