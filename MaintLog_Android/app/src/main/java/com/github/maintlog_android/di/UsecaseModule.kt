package com.github.maintlog_android.di

import com.github.domain.repository.improvement.ImprovementRepository
import com.github.domain.repository.weather.WeatherRepository
import com.github.domain.usecase.GetAllImprovementUseCase
import com.github.domain.usecase.GetForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetForecastUseCase(repo: WeatherRepository) = GetForecastUseCase(repo)

    @Provides
    fun provideGetAllImprovementUseCase(repo: ImprovementRepository) = GetAllImprovementUseCase(repo)
}
