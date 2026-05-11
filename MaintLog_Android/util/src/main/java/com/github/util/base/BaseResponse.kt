package com.github.util.base

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.github.util.code.NetworkCode
import kotlinx.parcelize.Parcelize

/**
 * 기본적으로 JSONArray 형태로 리턴받음
 */
@Parcelize
data class BaseResponse<T : Parcelable>(

    @SerializedName("resultCode")
    val code: String,
    @SerializedName("resultMsg")
    val message: String,
    @SerializedName("resultSize")
    val size: Int, //
    @SerializedName("totalSize")
    val totalSize: Int, // (페이징 처리할 때 사용)
    @SerializedName("resultData")
    val result: List<T> = listOf(),
) : Parcelable {
    fun getFirst(): T? {
        return if (result.isEmpty()) null
        else result[0]
    }

    fun isSuccess() = code == NetworkCode.SUCCESS.code
    fun isAlreadyUpdate() = code == NetworkCode.ALREADY_UPDATE.code

}

/**
 * JSONObject 형태로 리턴받음
 */
@Parcelize
data class BaseResponseObject<T : Parcelable>(
    @SerializedName("Param")
    val result: T,
) : Parcelable