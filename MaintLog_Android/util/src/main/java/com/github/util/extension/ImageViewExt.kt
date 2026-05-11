package com.github.util.extension

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.widget.ImageView

fun ImageView.updateImageColorMatrix(isEnabled: Boolean) {
    val colorMatrix = ColorMatrix().apply {
        if (!isEnabled) {
            logE("updateImageColorMatrix 1")
            setSaturation(0f) // 비활성화 시 흑백 효과 적용
        } else {
            logE("updateImageColorMatrix 2")
            setSaturation(1f) // 활성화 시 원래 색상 유지
        }
    }
    colorFilter = ColorMatrixColorFilter(colorMatrix)
}