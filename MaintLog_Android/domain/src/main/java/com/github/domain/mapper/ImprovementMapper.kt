package com.github.domain.mapper

import com.github.domain.entity.ImprovementEntity
import com.github.domain.model.improvement.ImprovementData

fun ImprovementEntity.toData(): ImprovementData {
    return ImprovementData(
        sn = sn,
    )
}

fun List<ImprovementEntity>.toDataList(): List<ImprovementData> {
    return map { it.toData() }
}