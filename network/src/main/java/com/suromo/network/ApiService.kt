package com.suromo.network

import retrofit2.http.*

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 网络API
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "https://tb.tbxla.com/"
    }

    @GET("TbStat/GetMarkSixHistoryByYear")
    suspend fun getMarkSixHistoryByYear(
        @Query("lotteryInfoId") infoId: Int,
        @Query("year") year: Int
    ): ApiResponse<MarkHistory>
}