package com.github.domain.model.common

enum class StatusType(
    val label: String
) {
    WAIT("대기"),
    PROGRESS("진행중"),
    ACTION("조치중"),
    COMPLETE("완료"),
    HOLD("보류");

    companion object {
        fun from(value: String?): StatusType {
            return entries.find { it.label == value } ?: WAIT
        }
    }
}