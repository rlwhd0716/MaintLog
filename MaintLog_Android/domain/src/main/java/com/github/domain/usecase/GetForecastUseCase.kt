package com.github.domain.usecase

import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.model.weather.WeatherResponse
import com.github.domain.repository.weather.WeatherRepository
import com.github.util.base.BaseUseCase
import com.github.util.base.setChannelFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastUseCase @Inject constructor(private val repo: WeatherRepository) :
    BaseUseCase<WeatherRequestData, WeatherResponse> {
    override fun execute(input: WeatherRequestData) = setChannelFlow {
        repo.ultraShortForecast(input)
    }
}