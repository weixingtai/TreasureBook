package com.suromo.network

/**
 * 作者　: hegaojian
 * 时间　: 2020/5/4
 * 描述　: 处理协程的请求类
 */

val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}

class HttpRequestManger {

    suspend fun getMarkSix():ApiResponse<MarkHistory>{
        return apiService.getMarkSixHistoryByYear(22,2023)
    }
}