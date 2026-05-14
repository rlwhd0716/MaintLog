package com.github.domain.model.work

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkLogData(
    @SerializedName("sn")
    val sn: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("log_content")
    val logContent: String
) : Parcelable {

    companion object {
        fun dummyList(): List<WorkLogData> {
            return listOf(
                WorkLogData(
                    sn = 1,
                    date = "2026-05-14",
                    status = "조치완료",
                    logContent = "진행상태 변경"
                ),
                WorkLogData(
                    sn = 2,
                    date = "2026-05-13",
                    status = "조치중",
                    logContent = "작업내용 변경"
                ),
                WorkLogData(
                    sn = 3,
                    date = "2026-05-12",
                    status = "조치중",
                    logContent = "작업내용 변경"
                ),
                WorkLogData(
                    sn = 4,
                    date = "2026-05-11",
                    status = "조치중",
                    logContent = "진행상태 변경"
                ),
                WorkLogData(
                    sn = 5,
                    date = "2026-05-10",
                    status = "대기",
                    logContent = "신규 등록"
                )
            )
        }
    }
}