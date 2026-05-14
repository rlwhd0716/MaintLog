package com.github.data.api

import com.github.domain.model.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PublicDataApiService {

    @GET("1360000/VilageFcstInfoService_2.0/getUltraSrtFcst")
    suspend fun getUltraSrtFcst(
        @Query(value = "serviceKey", encoded = true) serviceKey: String,
        @Query("numOfRows") numOfRows: String,
        @Query("pageNo") pageNo: String,
        @Query("dataType") dataType: String,
        @Query("base_date") base_date: String,
        @Query("base_time") base_time: String,
        @Query("nx") nx: String,
        @Query("ny") ny: String,
    ): Response<WeatherResponse>
}