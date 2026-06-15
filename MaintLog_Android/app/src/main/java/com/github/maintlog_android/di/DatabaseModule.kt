package com.github.maintlog_android.di

import android.content.Context
import androidx.room.Room
import com.github.data.database.ImprovementDao
import com.github.data.database.MAINTLOG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MAINTLOG =
        Room.databaseBuilder(context, MAINTLOG::class.java, "maint_log.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideImprovementDao(db: MAINTLOG): ImprovementDao = db.improvementDao()
}

