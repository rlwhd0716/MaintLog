package com.github.domain.repository.weather

import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.model.weather.WeatherResponse
import com.github.util.base.RepositoryResult

interface WeatherRepository {
    suspend fun ultraShortForecast(data: WeatherRequestData): RepositoryResult<WeatherResponse>
}