package com.github.domain.model.improvement

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImprovementDetailData(
    @SerializedName("sn")
    val sn: Int,

    @SerializedName("status")
    val status: String
) : Parcelable