package com.github.data.datasource.impl

import com.github.data.annotation.PublicDataApiKey
import com.github.data.api.PublicDataApiService
import com.github.data.datasource.PublicDataDataSource
import com.github.domain.model.weather.WeatherRequestData
import javax.inject.Inject

class PublicDataDataSourceImpl @Inject constructor(
    @PublicDataApiKey private val publicDataApiKey: String,
    private val api: PublicDataApiService
) : PublicDataDataSource {
    override suspend fun getUltraSrtFcst(
        data: WeatherRequestData
    ) = api.getUltraSrtFcst(
        serviceKey = publicDataApiKey,
        numOfRows = data.numOfRows.toString(),
        pageNo = data.pageNo.toString(),
        dataType = data.dataType,
        base_date = data.baseDate,
        base_time = data.baseTime,
        nx = data.nx,
        ny = data.ny
    )
}