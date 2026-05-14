package com.github.domain.model.weather

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherData(
    val numOfRows: String,
    val pageNo: String,
    val totalCount: String,
    val resultCode: String,
    val resultMsg: String,
    val dataType: String,
    val baseDate: String,
    val baseTime: String,
    val nx: String,
    val ny: String,
    val category: String,
    val fcstDate: String,
    val fcstTime: String,
    val fcstValue: String,
) : Parcelable