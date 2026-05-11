package com.github.util.binding

import androidx.databinding.BindingAdapter
import android.view.View

@BindingAdapter(value = ["click"])
fun View.onClickListener(listener: ((View) -> Any?)? = null) {
    setOnClickListener { listener?.invoke(it) }
}

@BindingAdapter(value = ["longClick"])
fun View.onLongClickListener(listener: ((View) -> Boolean?)? = null) {
    setOnLongClickListener { listener?.invoke(it) ?: run { false } }
}

@BindingAdapter("setInvisible")
fun View.setInvisible(isVisible:Boolean) {
    visibility = if(isVisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("setGone")
fun View.setGone(isVisible:Boolean) {
    visibility = if(isVisible) View.GONE else View.VISIBLE
}