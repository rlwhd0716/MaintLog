package com.github.domain.model.improvement

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImprovementData(
    val sn: Int
): Parcelable