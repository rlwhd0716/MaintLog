package com.github.data.datasource

import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.model.weather.WeatherResponse
import retrofit2.Response

interface PublicDataDataSource {
    suspend fun getUltraSrtFcst(data: WeatherRequestData): Response<WeatherResponse>
}