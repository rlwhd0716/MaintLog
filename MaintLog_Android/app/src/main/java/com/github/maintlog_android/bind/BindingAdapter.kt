package com.github.maintlog_android.bind

import android.content.res.ColorStateList
import androidx.databinding.BindingAdapter
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.github.util.event.MutableListLiveData
import com.github.util.extension.logE
import com.google.android.material.button.MaterialButton


@BindingAdapter("bindAdapter")
fun setBindRecyclerViewAdapter(view: RecyclerView, datas: MutableListLiveData<*>) {
    view.adapter?.run {
        logE("${datas.value}")
        when (this) {
//            is PermissionsAdapter -> {
//                this.datas = (datas.value ?: mutableListOf()) as MutableList<PermissionData>
//            }
        }
        this.notifyDataSetChanged()
    }
}

@BindingAdapter("backgroundTint")
fun setBackgroundTint(button: MaterialButton, @ColorInt color: Int) {
    button.backgroundTintList = ColorStateList.valueOf(color)
}