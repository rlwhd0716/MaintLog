package com.github.maintlog_android.di

import com.github.data.repositoryimpl.improvement.ImprovementRepositoryImpl
import com.github.data.repositoryimpl.weather.WeatherRepositoryImpl
import com.github.domain.repository.improvement.ImprovementRepository
import com.github.domain.repository.weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindWeatherRepository(repositoryImpl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindImprovementRepository(repositoryImpl: ImprovementRepositoryImpl): ImprovementRepository
}