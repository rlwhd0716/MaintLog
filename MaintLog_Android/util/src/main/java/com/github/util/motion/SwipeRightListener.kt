package com.github.util.motion

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class SwipeRightListener(context: Context, val onSwipe: () -> Unit) : View.OnTouchListener {
    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val deltaX = e2.x - (e1?.x ?: 0.0f)
            val threshold = 100

            if (deltaX > threshold) { // 왼쪽으로 스와이프
                onSwipe.invoke()
                return true
            }
            return false
        }
    })

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return event?.let { gestureDetector.onTouchEvent(event) } == true
    }
}