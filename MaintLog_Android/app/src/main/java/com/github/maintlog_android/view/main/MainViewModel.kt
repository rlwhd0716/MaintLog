package com.github.maintlog_android.view.main

import com.github.domain.model.action.ActionData
import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.usecase.GetForecastUseCase
import com.github.maintlog_android.view.home.HomeActionAdapter
import com.github.util.base.BaseViewModel
import com.github.util.event.MutableListLiveData
import com.github.util.extension.logE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val forecastUseCase: GetForecastUseCase
) : BaseViewModel() {
    private val MAX_RECENT_COUNT = 3

    private val _improvementActionList = MutableListLiveData<ActionData>()
    val improvementActionList get() = _improvementActionList

    val homeActionAdapter = HomeActionAdapter(this@MainViewModel)

    fun getImprovementActions() {
        _improvementActionList.add(ActionData(""))
        _improvementActionList.add(ActionData(""))
        _improvementActionList.add(ActionData(""))
        _improvementActionList.add(ActionData(""))
        _improvementActionList.add(ActionData(""))
    }

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