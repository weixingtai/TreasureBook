package com.suromo.database.repository

import com.suromo.database.dao.MarkSixDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23
 * desc   :
 */
@Singleton
class MarkSixRepository @Inject constructor(private val markSixDao: MarkSixDao) {

    fun getMarkSixList() = markSixDao.getAllMarkSix()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MarkSixRepository? = null

        fun getInstance(markSixDao: MarkSixDao) =
            instance ?: synchronized(this) {
                instance ?: MarkSixRepository(markSixDao).also { instance = it }
            }
    }
}