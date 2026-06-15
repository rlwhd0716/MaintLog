package com.github.domain.usecase

import com.github.domain.model.improvement.ImprovementData
import com.github.domain.model.weather.WeatherRequestData
import com.github.domain.model.weather.WeatherResponse
import com.github.domain.repository.improvement.ImprovementRepository
import com.github.domain.repository.weather.WeatherRepository
import com.github.util.base.BaseResponse
import com.github.util.base.BaseUseCase
import com.github.util.base.setChannelFlow
import com.github.util.event.Empty
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllImprovementUseCase @Inject constructor(private val repo: ImprovementRepository) :
    BaseUseCase<Nothing, BaseResponse<ImprovementData>> {
    override fun execute(input: Nothing) = setChannelFlow {
        repo.getAll()
    }
}