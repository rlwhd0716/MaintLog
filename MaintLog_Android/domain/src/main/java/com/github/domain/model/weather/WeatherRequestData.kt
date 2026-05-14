package com.github.domain.model.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherRequestData(
    var pageNo: Int = 1,
    var numOfRows: Int = 10,
    var dataType: String = "JSON",
    var baseDate: String,
    var baseTime: String,
    var nx: String,
    var ny: String
) : Parcelable