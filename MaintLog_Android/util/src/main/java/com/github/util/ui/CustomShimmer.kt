package com.github.util.ui

import android.animation.ValueAnimator.INFINITE
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.toColorInt
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.github.util.R
import com.github.util.extension.logE

@SuppressLint("ClickableViewAccessibility")
class CustomShimmer @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    ShimmerFrameLayout(context, attrs) {
    private var baseColor: Int = 0
    private var highlightColor: Int = 0
    private var duration: Float = 1500f
    private var shimmer: Shimmer? = null

    init {
        context?.withStyledAttributes(attrs, R.styleable.CustomShimmerData) {
            baseColor = getColor(R.styleable.CustomShimmerData_baseColor, "#FFE97D".toColorInt())
            highlightColor =
                getColor(R.styleable.CustomShimmerData_highlightColor, "#FFF9C4".toColorInt())
            duration = getFloat(R.styleable.CustomShimmerData_shimmerDuration, 3000f)
        }

        logE("CustomShimmer : ${this.isEnabled}")
        shimmer = isEnabledShimmer(this.isEnabled)
//        shimmer = Shimmer.ColorHighlightBuilder()
//            .setBaseColor(baseColor)
//            .setHighlightColor(highlightColor)
//            .setBaseAlpha(0.6f)
//            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
//            .setRepeatMode(INFINITE)
//            .setAutoStart(true)
//            .setDuration(duration.toLong())
//            .build()

        shimmer?.let {
            setShimmer(it)
            startShimmer()
        }

        val decline = AnimationUtils.loadAnimation(context, R.anim.scale_dap_decline)
        val incline = AnimationUtils.loadAnimation(context, R.anim.scale_dap_incline)

        setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    this.startAnimation(decline)
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    this.startAnimation(incline)
                }
            }
            false
        }
    }

    fun changeShimmerColor(baseColor: Int, highlightColor: Int) {
        stopShimmer()
        shimmer = Shimmer.ColorHighlightBuilder()
            .setBaseColor(baseColor)
            .setHighlightColor(highlightColor)
            .setBaseAlpha(0.6f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setRepeatMode(INFINITE)
            .setAutoStart(true)
            .setDuration(duration.toLong())
            .build()

        shimmer?.let {
            setShimmer(it)
            startShimmer()
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        shimmer = isEnabledShimmer(enabled)
        setShimmer(shimmer)
    }

    fun isEnabledShimmer(enabled: Boolean) = if (enabled) {
        Shimmer.ColorHighlightBuilder()
            .setBaseColor(baseColor)
            .setHighlightColor(highlightColor)
            .setBaseAlpha(0.6f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setRepeatMode(INFINITE)
            .setAutoStart(true)
            .setDuration(duration.toLong())
            .build()
    } else {
        Shimmer.ColorHighlightBuilder()
            .setBaseColor(context.getColor(R.color.primaryDisabledColor))
            .setHighlightColor(context.getColor(R.color.primaryDisabledColor))
            .setBaseAlpha(1f)
            .build()
    }
}
