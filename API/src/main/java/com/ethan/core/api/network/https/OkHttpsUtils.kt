package com.ethan.core.api.network.https

import com.ethan.core.api.network.https.HttpsUtils
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit

object OkHttpsUtils {
    var DEFAULT_TIME_OUT: Long = 10;//超时时间5s
    var DEFAULT_READ_TIME_OUT: Long = 10;//读取时间
    var DEFAULT_WRITE_TIME_OUT: Long = 10;//读取时间

    fun getNoSSLBuilder(): OkHttpClient.Builder {
        //设置可访问所有的Https网站，无需验证证书
        return getSSLBuilder(null, null, null)
    }

    fun getSSLBuilder(
        certificates: Array<InputStream>?,
        bksFile: InputStream?,
        password: String?
    ): OkHttpClient.Builder {
        var sslParams = HttpsUtils.getSslSocketFactory(certificates, bksFile, password);
        return getDefaultBuilder().hostnameVerifier { hostname, session -> true }
            .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
    }


    private fun getDefaultBuilder(): OkHttpClient.Builder = OkHttpClient().newBuilder()
        .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
}