package com.github.util.extension

import android.content.Context
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import com.github.util.R

class AnimController {
    companion object {
        private var instance: AnimController? = null
        private lateinit var context: Context

        fun getInstance(
            _context: Context,
        ): AnimController {
            return instance ?: synchronized(this) {
                instance ?: AnimController().also {
                    instance = it
                    context = _context
                }
            }
        }
    }

    /*
    // 열기
    val movementBotAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.translate_bottom_movement)
    }

    // 닫기
    val movementTopAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.translate_top_movement)
    }

    // 열기
    val botNaviBotAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.translate_bottom_navi_bot)
    }

    // 닫기
    val botNaviTopAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.translate_top_navi_bot)
    }

    // 열기
    val tbtNaviInAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.translate_fade_in_navi_tbt)
    }

    // 닫기
    val tbtNaviOutAnim by lazy {
        AnimationUtils.loadAnimation(context, R.anim.translate_fade_out_navi_tbt)
    }
    */
}

fun Animation.setListener(
    _startBlock: (() -> Unit)? = null,
    _endBlock: (() -> Unit)? = null,
    _repeatBlock: (() -> Unit)? = null,
) = apply {
    this.setAnimationListener(object :AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            _startBlock?.invoke()
        }

        override fun onAnimationEnd(animation: Animation?) {
            _endBlock?.invoke()
        }

        override fun onAnimationRepeat(animation: Animation?) {
            _repeatBlock?.invoke()
        }
    })
}