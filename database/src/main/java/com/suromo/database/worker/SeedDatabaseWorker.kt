package com.suromo.database.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.suromo.database.TreasureDatabase
import com.suromo.database.bean.MarkSix
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2023/2/23
 * desc   :
 */
class SeedDatabaseWorker (
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val markSixType = object : TypeToken<List<MarkSix>>() {}.type
                        val markSixList: List<MarkSix> = Gson().fromJson(jsonReader, markSixType)

                        val database = TreasureDatabase.getInstance(applicationContext)
                        database.markSixDao().insertAll(markSixList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "TREASURE_DATA_FILENAME"
    }
}