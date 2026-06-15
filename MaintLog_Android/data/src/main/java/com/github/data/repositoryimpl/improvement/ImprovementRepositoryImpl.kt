package com.github.data.repositoryimpl.improvement

import com.github.data.datasource.local.ImprovementDataSource
import com.github.domain.repository.improvement.ImprovementRepository
import com.github.util.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImprovementRepositoryImpl @Inject constructor(
    private val local: ImprovementDataSource
): BaseRepository(), ImprovementRepository {
    override suspend fun getAll() = safeDBCall { local.getAllImprovement() }
}