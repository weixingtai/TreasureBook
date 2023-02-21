package com.suromo.network.impl

import com.suromo.network.BaseNetworkResponse

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/20
 * desc   :
 */
class NetworkResponse <T>(val code: Int, val msg: String, val data: T) : BaseNetworkResponse<T>() {

    // 这里是示例，wan android 网站返回的 错误码为 0 就代表请求成功，请你根据自己的业务需求来改变
    override fun isSuccess() = code == 200

    override fun getResponseCode() = code

    override fun getResponseData() = data

    override fun getResponseMsg() = msg

}