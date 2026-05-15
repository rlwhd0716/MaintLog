package com.github.domain.model.action

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionData(

    @SerializedName("content")
    val content: String
) : Parcelable
