package com.github.maintlog_android.di

import com.github.data.datasource.impl.local.ImprovementDataSourceImpl
import com.github.data.datasource.impl.remote.PublicDataDataSourceImpl
import com.github.data.datasource.local.ImprovementDataSource
import com.github.data.datasource.remote.PublicDataDataSource
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

    @Binds
    abstract fun bindImprovementDataSource(dataSourceImpl: ImprovementDataSourceImpl): ImprovementDataSource
}
