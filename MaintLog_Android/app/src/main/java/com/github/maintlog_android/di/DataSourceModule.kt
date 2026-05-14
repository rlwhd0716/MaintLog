package com.github.maintlog_android.di

import com.github.data.datasource.PublicDataDataSource
import com.github.data.datasource.impl.PublicDataDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    /** main */
    @Binds
    abstract fun bindPublicDataDataSource(dataSourceImpl: PublicDataDataSourceImpl): PublicDataDataSource
}
