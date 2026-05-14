package com.github.maintlog_android.view.main

import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.usecase.GetForecastUseCase
import com.github.util.base.BaseViewModel
import com.github.util.extension.logE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val forecastUseCase: GetForecastUseCase
) : BaseViewModel() {


    fun getWeatherForecast() {
        forecastUseCase.execute(
            input = WeatherRequestData(
                numOfRows = 10,
                pageNo = 1,
                dataType = "JSON",
                baseDate = "20230701",
                baseTime = "0600",
                nx = "60",
                ny = "127"
            )
        ).getResult { response ->
            response?.apply {
                logE("$response")
            }
        }
    }
}