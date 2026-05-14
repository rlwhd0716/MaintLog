package com.github.maintlog_android.di

import com.github.domain.repository.weather.WeatherRepository
import com.github.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideLoginUseCase(repo: WeatherRepository) = GetForecastUseCase(repo)
}
