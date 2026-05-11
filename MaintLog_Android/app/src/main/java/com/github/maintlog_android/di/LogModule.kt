package com.github.maintlog_android.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LogModule {
//    @Provides
//    fun provideLogRepository(context: Application, remote: LogRemoteDataSource): LogRepository = LogRepositoryImpl(context, remote)
}