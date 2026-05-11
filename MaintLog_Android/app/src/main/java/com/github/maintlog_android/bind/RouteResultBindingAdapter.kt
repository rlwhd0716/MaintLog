package com.github.maintlog_android.bind

import androidx.databinding.BindingAdapter
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button


//@BindingAdapter("timerText")
//fun Button.timerText(timer: Int) {
//    var result = getString(R.string.route_result_start_guide)
//    if (timer != 0) result += "($timer)"
//    text = result
//}

@BindingAdapter("setHighLight")
fun View.setHighLight(isOn: Boolean) {
    // 선택한 경로옵션 애니메이션 효과
    val alphaAnimation = AlphaAnimation(0.2f, 1.0f).apply {
        duration = 500
        startOffset = 10
        repeatMode = Animation.REVERSE
        repeatCount = Animation.INFINITE
    }

    visibility = if (isOn) {
        this.startAnimation(alphaAnimation)
        View.VISIBLE
    } else {
        this.clearAnimation()
        View.GONE
    }
}