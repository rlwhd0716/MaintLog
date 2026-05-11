package com.github.maintlog_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    /** main */
//    @Binds
//    abstract fun bindAppRemoteDataSource(dataSourceImpl: AppRemoteDataSourceImpl): AppRemoteDataSource
}
