package com.suromo.treasurebook.data.repository

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/20
 * desc   :
 */
interface IHomeRepository {

    suspend fun getMarSixHistory(): Result<Any>

}