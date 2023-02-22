package com.suromo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.suromo.database.bean.MarkSix
import com.suromo.database.common.Converter
import com.suromo.database.common.DATABASE_NAME
import com.suromo.database.common.MARK_SIX_DATA_FILENAME
import com.suromo.database.dao.MarkSixDao
import com.suromo.database.worker.SeedDatabaseWorker
import com.suromo.database.worker.SeedDatabaseWorker.Companion.KEY_FILENAME

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/04/27
 * desc   : room数据库的使用方法
 *          1.在bean文件夹中创建表对应的实体类
 *          2.在dao文件夹中创建对应的数据库查询类
 *          3.在本类中添加新的实体类::class
 *          4.在本类中添加新的dao实例
 *          5.在使用处调用getInstance()方法并获取dao实例
 *          6.进行增删改查
 */
// 1.添加新的实体类::class
@Database(entities = [MarkSix::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TreasureDatabase : RoomDatabase() {

    // 2.添加新的dao实例
    abstract fun markSixDao() : MarkSixDao

    companion object {

        @Volatile private var instance: TreasureDatabase? = null
        // 3.在使用处调用并获取dao实例
        fun getInstance(context: Context): TreasureDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it}
            }
        }

        private fun buildDatabase(context: Context): TreasureDatabase {
            return Room.databaseBuilder(context, TreasureDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to MARK_SIX_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }

}