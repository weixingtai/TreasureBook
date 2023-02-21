package com.suromo.network.impl

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/20
 * desc   :
 */
interface NetworkRequest {

    /**
     * 收藏文章
     */
    @GET(NetworkUrl.URL_GET_MARK_SIX_HISTORY_BY_YEAR)
    suspend fun getMarkSixHistoryByYear(
        @Query("lotteryInfoId") infoId: Int,
        @Query("year") year: Int
    ): NetworkResponse<Any?>

}