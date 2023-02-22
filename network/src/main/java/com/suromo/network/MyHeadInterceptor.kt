package com.suromo.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 自定义头部参数拦截器，传入heads
 */
class MyHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Content-type", "application/json; charset=utf-8").build()
        builder.addHeader("host_sign_code", "ce0bfd15059b68d67688884d7a3d3e8c").build()
        return chain.proceed(builder.build())
    }

}