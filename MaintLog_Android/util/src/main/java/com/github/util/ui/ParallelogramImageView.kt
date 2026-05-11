package com.github.util.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.toColorInt
import androidx.core.graphics.withClip
import com.github.util.R

class ParallelogramImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private val clipPath = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // 평행사변형 모양 정의
        clipPath.reset()
        clipPath.moveTo(0f, 0f)
        clipPath.lineTo(w * 0.8f, 0f)
        clipPath.lineTo(w.toFloat(), h.toFloat())
        clipPath.lineTo(w * 0.2f, h.toFloat())
        clipPath.close()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.withClip(clipPath) {
            super.onDraw(this)
        }
    }
}