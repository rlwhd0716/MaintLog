package com.github.data.database

import androidx.room.Dao
import androidx.room.Query
import com.github.domain.entity.ImprovementEntity

@Dao
interface ImprovementDao {
    @Query("SELECT * FROM ImprovementEntity")
    fun getAll(): List<ImprovementEntity>
}