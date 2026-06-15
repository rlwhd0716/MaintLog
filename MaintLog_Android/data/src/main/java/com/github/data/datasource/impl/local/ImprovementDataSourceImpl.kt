package com.github.data.datasource.impl.local

import com.github.data.database.ImprovementDao
import com.github.data.datasource.local.ImprovementDataSource
import com.github.domain.model.improvement.ImprovementData
import com.github.domain.mapper.toDataList
import com.github.util.base.BaseResponse
import com.github.util.code.NetworkCode
import javax.inject.Inject

class ImprovementDataSourceImpl @Inject constructor(
    private val local: ImprovementDao
) : ImprovementDataSource {
    override suspend fun getAllImprovement(): BaseResponse<ImprovementData> {
        val data = local.getAll().toDataList()
        return BaseResponse(
            code = NetworkCode.QUERY_SUCCESS.code,
            message = "요청사항 전체 조회 완료",
            size = data.size,
            totalSize = data.size,
            result = data
        )
    }
}