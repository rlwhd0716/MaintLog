package com.github.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImprovementEntity(
    @PrimaryKey val sn: Int
)