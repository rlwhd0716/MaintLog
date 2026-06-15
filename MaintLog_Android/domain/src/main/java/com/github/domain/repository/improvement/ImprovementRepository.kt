package com.github.domain.repository.improvement

import com.github.domain.model.improvement.ImprovementData
import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.model.weather.WeatherResponse
import com.github.util.base.BaseResponse
import com.github.util.base.RepositoryResult

interface ImprovementRepository {
    suspend fun getAll(): RepositoryResult<BaseResponse<ImprovementData>>
}