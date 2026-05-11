package com.github.util.helper

interface ItemTouchHelperListener {

    fun onItemMove(from: Int, to: Int): Boolean

    fun onItemSwipe(pos: Int)
}