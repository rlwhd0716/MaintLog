package com.github.util.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.github.util.R

@SuppressLint("ClickableViewAccessibility")
open class BounceLinearLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs) {
    private var touchViewId: Int = View.NO_ID
    var targetView: View? = null

    private val decline = AnimationUtils.loadAnimation(context, R.anim.scale_dap_decline)
    private val incline = AnimationUtils.loadAnimation(context, R.anim.scale_dap_incline)

    init {
        context.withStyledAttributes(attrs, R.styleable.LayoutData) {
            touchViewId = getResourceId(R.styleable.LayoutData_touchViewId, View.NO_ID)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (touchViewId != View.NO_ID) {
            targetView = (rootView as? ViewGroup)?.findViewById<CustomShimmer>(touchViewId)
        }

        setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    this.startAnimation(decline)
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    this.startAnimation(incline)
                }
            }
            targetView?.dispatchTouchEvent(event)
            false
        }
    }
}