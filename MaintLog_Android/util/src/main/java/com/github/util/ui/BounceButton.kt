package com.github.util.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.github.util.R

@SuppressLint("ClickableViewAccessibility")
class BounceButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null): MaterialButton(context, attrs) {
    init {
        val decline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_decline)
        val incline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_incline)

        setOnTouchListener { view, event ->
            when ( event.action ) {
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
}

@SuppressLint("ClickableViewAccessibility")
class BounceImageButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null): androidx.appcompat.widget.AppCompatImageButton(context, attrs) {
    init {
        val decline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_decline)
        val incline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_incline)

        setOnTouchListener { view, event ->
            when ( event.action ) {
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
}


@SuppressLint("ClickableViewAccessibility")
class BounceCheckBox @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null): MaterialCheckBox(context, attrs) {
    init {
        val decline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_decline)
        val incline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_incline)

        setOnTouchListener { view, event ->
            when ( event.action ) {
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
}

@SuppressLint("ClickableViewAccessibility")
class BounceTextButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null): androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    init {
        val decline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_decline)
        val incline = AnimationUtils.loadAnimation(context, R.anim.scale_bounce_incline)

        setOnTouchListener { view, event ->
            when ( event.action ) {
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
}