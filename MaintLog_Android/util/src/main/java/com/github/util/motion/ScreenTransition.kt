package com.github.util.motion

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.util.R
import kotlin.jvm.java

enum class ScreenTransitionDirection {
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT,
    TOP_TO_BOTTOM,
    BOTTOM_TO_TOP,
    FADE,
    NONE;

    fun reverse(): ScreenTransitionDirection {
        return when (this) {
            LEFT_TO_RIGHT -> RIGHT_TO_LEFT
            RIGHT_TO_LEFT -> LEFT_TO_RIGHT
            TOP_TO_BOTTOM -> BOTTOM_TO_TOP
            BOTTOM_TO_TOP -> TOP_TO_BOTTOM
            FADE -> FADE
            NONE -> NONE
        }
    }
}

fun Activity.startActivityWithTransition(
    intent: Intent,
    direction: ScreenTransitionDirection = ScreenTransitionDirection.RIGHT_TO_LEFT
) {
    intent.putExtra("transition_direction", direction.name)
    startActivity(intent)
    applyEnterTransition(direction)
}

fun Activity.finishWithTransition() {
    val directionName = intent.getStringExtra("transition_direction")
    val direction = directionName
        ?.let { ScreenTransitionDirection.valueOf(it) }
        ?: ScreenTransitionDirection.RIGHT_TO_LEFT

    finish()
    applyExitTransition(direction.reverse())
}

fun Activity.applyEnterTransition(direction: ScreenTransitionDirection) {
    when (direction) {
        ScreenTransitionDirection.RIGHT_TO_LEFT ->
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        ScreenTransitionDirection.LEFT_TO_RIGHT ->
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

        ScreenTransitionDirection.BOTTOM_TO_TOP ->
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out_screen)

        ScreenTransitionDirection.TOP_TO_BOTTOM ->
            overridePendingTransition(R.anim.slide_in_top, R.anim.fade_out_screen)

        ScreenTransitionDirection.FADE ->
            overridePendingTransition(R.anim.fade_in_screen, R.anim.fade_out_screen)

        ScreenTransitionDirection.NONE ->
            overridePendingTransition(0, 0)
    }
}

fun Activity.applyExitTransition(direction: ScreenTransitionDirection) {
    applyEnterTransition(direction)
}

fun FragmentManager.replaceWithTransition(
    containerId: Int,
    fragment: Fragment,
    direction: ScreenTransitionDirection = ScreenTransitionDirection.RIGHT_TO_LEFT,
    addToBackStack: Boolean = true
) {
    val animations = direction.toFragmentAnimations()

    beginTransaction()
        .setCustomAnimations(
            animations.enter,
            animations.exit,
            animations.popEnter,
            animations.popExit
        )
        .replace(containerId, fragment)
        .apply {
            if (addToBackStack) addToBackStack(fragment::class.java.simpleName)
        }
        .commit()
}

data class FragmentAnimations(
    val enter: Int,
    val exit: Int,
    val popEnter: Int,
    val popExit: Int
)

fun ScreenTransitionDirection.toFragmentAnimations(): FragmentAnimations {
    return when (this) {
        ScreenTransitionDirection.RIGHT_TO_LEFT ->
            FragmentAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )

        ScreenTransitionDirection.LEFT_TO_RIGHT ->
            FragmentAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )

        ScreenTransitionDirection.BOTTOM_TO_TOP ->
            FragmentAnimations(
                R.anim.slide_in_bottom,
                R.anim.fade_out_screen,
                R.anim.fade_in_screen,
                R.anim.slide_out_bottom
            )

        ScreenTransitionDirection.TOP_TO_BOTTOM ->
            FragmentAnimations(
                R.anim.slide_in_top,
                R.anim.fade_out_screen,
                R.anim.fade_in_screen,
                R.anim.slide_out_top
            )

        ScreenTransitionDirection.FADE ->
            FragmentAnimations(
                R.anim.fade_in_screen,
                R.anim.fade_out_screen,
                R.anim.fade_in_screen,
                R.anim.fade_out_screen
            )

        ScreenTransitionDirection.NONE ->
            FragmentAnimations(0, 0, 0, 0)
    }
}

fun View.animatePageEnter(direction: ScreenTransitionDirection) {
    val distance = resources.displayMetrics.widthPixels.toFloat()

    when (direction) {
        ScreenTransitionDirection.RIGHT_TO_LEFT -> {
            translationX = distance
            animate().translationX(0f).setDuration(250).start()
        }

        ScreenTransitionDirection.LEFT_TO_RIGHT -> {
            translationX = -distance
            animate().translationX(0f).setDuration(250).start()
        }

        ScreenTransitionDirection.BOTTOM_TO_TOP -> {
            translationY = resources.displayMetrics.heightPixels.toFloat()
            animate().translationY(0f).setDuration(250).start()
        }

        ScreenTransitionDirection.TOP_TO_BOTTOM -> {
            translationY = -resources.displayMetrics.heightPixels.toFloat()
            animate().translationY(0f).setDuration(250).start()
        }

        ScreenTransitionDirection.FADE -> {
            alpha = 0f
            animate().alpha(1f).setDuration(250).start()
        }

        ScreenTransitionDirection.NONE -> Unit
    }
}