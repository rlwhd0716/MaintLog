package com.github.data.datasource.local

import com.github.domain.model.improvement.ImprovementData
import com.github.util.base.BaseResponse

interface ImprovementDataSource {
    suspend fun getAllImprovement(): BaseResponse<ImprovementData>
}