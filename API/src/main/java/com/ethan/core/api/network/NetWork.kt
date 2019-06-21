package com.ethan.core.api.network

import com.ethan.core.api.Global
import com.ethan.core.api.network.https.OkHttpsUtils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetWork {
    fun getBaseRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Global.BASE_URL)
            .client(OkHttpsUtils.getNoSSLBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    }
}