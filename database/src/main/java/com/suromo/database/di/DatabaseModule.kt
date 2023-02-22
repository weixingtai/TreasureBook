package com.suromo.database.di

import android.content.Context
import com.suromo.database.TreasureDatabase
import com.suromo.database.dao.MarkSixDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/7/7
 * desc   :
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTreasureDatabase(@ApplicationContext context: Context): TreasureDatabase {
        return TreasureDatabase.getInstance(context)
    }

    @Provides
    fun provideMarkSixDao(treasureDatabase: TreasureDatabase): MarkSixDao {
        return treasureDatabase.markSixDao()
    }

}