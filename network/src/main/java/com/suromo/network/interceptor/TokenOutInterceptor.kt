package com.suromo.network.interceptor

import com.google.gson.Gson
import com.suromo.network.impl.NetworkResponse
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/20
 * desc   : token过期拦截器
 */
class TokenOutInterceptor : Interceptor {

    private val gson: Gson by lazy { Gson() }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return if (response.body() != null && response.body()!!.contentType() != null) {
            val mediaType = response.body()!!.contentType()
            val string = response.body()!!.string()
            val responseBody = ResponseBody.create(mediaType, string)
            val apiResponse = gson.fromJson(string, NetworkResponse::class.java)
            //判断逻辑 模拟一下
            if (apiResponse.code == 99999) {
                //如果是普通的activity话 可以直接跳转，如果是navigation中的fragment，可以发送通知跳转
//                mApplicationContext.startActivity(Intent(mApplicationContext, TestActivity::class.java).apply {
//                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                })
            }
            response.newBuilder().body(responseBody).build()
        } else {
            response
        }
    }
}