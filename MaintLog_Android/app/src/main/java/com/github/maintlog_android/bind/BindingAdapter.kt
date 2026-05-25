package com.github.maintlog_android.bind

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.domain.model.action.ActionData
import com.github.domain.model.work.WorkLogData
import com.github.maintlog_android.view.home.HomeActionAdapter
import com.github.maintlog_android.view.work.WorkLogAdapter
import com.github.util.event.MutableListLiveData
import com.github.util.extension.logE
import com.google.android.material.button.MaterialButton


@BindingAdapter("bindAdapter")
fun setBindRecyclerViewAdapter(view: RecyclerView, datas: MutableListLiveData<*>) {
    view.adapter?.run {
        logE("${datas.value}")
        when (this) {
            is WorkLogAdapter -> {
                this.datas = (datas.value ?: mutableListOf()) as MutableList<WorkLogData>
            }

            is HomeActionAdapter -> {
                this.datas = ((datas.value ?: mutableListOf()) as MutableList<ActionData>)
                    .take(5)
                    .toMutableList()
            }
        }
        this.notifyDataSetChanged()
    }
}

@BindingAdapter("backgroundTint")
fun setBackgroundTint(button: MaterialButton, @ColorInt color: Int) {
    button.backgroundTintList = ColorStateList.valueOf(color)
}