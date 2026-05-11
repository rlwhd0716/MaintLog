package com.github.util.ui

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import androidx.core.graphics.toColorInt
import androidx.core.graphics.withClip

class VNotchedCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BounceLinearLayout(context, attrs, defStyleAttr) {

    private val backgroundPath = Path()
    private val strokePath = Path()

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val blurPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val shimmerPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val cornerRadius = 16 * resources.displayMetrics.density
    private val inset = 5f
    private var glowRadius = 20f
    private var shimmerTranslate = 0f

    private var widthF = 0f
    private var heightF = 0f
    private var bgShader: Shader? = null
    private var strokeShader: Shader? = null

    private val shimmerAnimator = ValueAnimator.ofFloat(-4f, 6f)

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        setWillNotDraw(false)
        startGlowAnimation()
        startShimmerAnimation()

        strokePaint.strokeJoin = Paint.Join.ROUND
        strokePaint.strokeCap = Paint.Cap.ROUND
        strokePaint.style = Paint.Style.STROKE
        strokePaint.strokeWidth = 6f
    }

    private fun startGlowAnimation() {
        ValueAnimator.ofFloat(15f, 30f).apply {
            duration = 2000L
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener {
                glowRadius = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    private fun startShimmerAnimation() {
        shimmerAnimator.duration = 3000L
        shimmerAnimator.repeatCount = ValueAnimator.INFINITE
        shimmerAnimator.repeatMode = ValueAnimator.RESTART
        shimmerAnimator.interpolator = LinearInterpolator()
        shimmerAnimator.addUpdateListener {
            shimmerTranslate = it.animatedValue as Float
            invalidate()
        }
        shimmerAnimator.start()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        widthF = w.toFloat()
        heightF = h.toFloat()

        // Path 캐싱
        backgroundPath.reset()
        backgroundPath.addRoundRect(0f, 0f, widthF, heightF, cornerRadius, cornerRadius, Path.Direction.CW)

        strokePath.reset()
        strokePath.addRoundRect(
            inset, inset, widthF - inset, heightF - inset,
            cornerRadius - inset, cornerRadius - inset,
            Path.Direction.CW
        )

        // Shader 캐싱
        bgShader = LinearGradient(
            0f, 0f, widthF, heightF,
            intArrayOf(
                "#222222".toColorInt(),
                "#222222".toColorInt(),
                "#222222".toColorInt()
            ),
            floatArrayOf(0f, 0.5f, 1f),
            Shader.TileMode.CLAMP
        )

        strokeShader = LinearGradient(
            0f, 0f, widthF, heightF,
            intArrayOf("#FFE97D".toColorInt(), "#FFE97D".toColorInt()),
            null,
            Shader.TileMode.CLAMP
        )
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.withClip(backgroundPath) {
            // 배경
            fillPaint.shader = bgShader
            drawPath(backgroundPath, fillPaint)

            // Glow + Stroke
            strokePaint.shader = strokeShader
            blurPaint.set(strokePaint)
            blurPaint.maskFilter = BlurMaskFilter(glowRadius, BlurMaskFilter.Blur.NORMAL)
            drawPath(strokePath, blurPaint)
            drawPath(strokePath, strokePaint)

            // Shimmer
            val shimmerCenter = shimmerTranslate * widthF
            val shimmerWidth = widthF / 2f
            shimmerPaint.shader = LinearGradient(
                shimmerCenter - shimmerWidth, 0f,
                shimmerCenter + shimmerWidth, heightF,
                intArrayOf(Color.TRANSPARENT, "#80A9A9A9".toColorInt(), Color.TRANSPARENT),
                null,
                Shader.TileMode.CLAMP
            )
            shimmerPaint.style = Paint.Style.FILL
            drawRoundRect(0f, 0f, widthF, heightF, cornerRadius, cornerRadius, shimmerPaint)
        }
    }
}