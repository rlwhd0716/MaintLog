package com.github.data.repositoryimpl.weather

import com.github.data.datasource.remote.PublicDataDataSource
import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.repository.weather.WeatherRepository
import com.github.util.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val remote: PublicDataDataSource
): BaseRepository(), WeatherRepository {
    override suspend fun ultraShortForecast(data: WeatherRequestData) = safeApiCall {
        remote.getUltraSrtFcst(data)
    }

}