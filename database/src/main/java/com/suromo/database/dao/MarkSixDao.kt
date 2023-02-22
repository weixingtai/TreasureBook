package com.suromo.database.dao

import androidx.room.*
import com.suromo.database.bean.MarkSix
import kotlinx.coroutines.flow.Flow

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/22
 * desc   :
 */
@Dao
interface MarkSixDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(markSix: MarkSix?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(markSixList: List<MarkSix>)

    @Transaction
    @Query("SELECT * FROM mark_six")
    fun getAllMarkSix(): Flow<List<MarkSix>>

}