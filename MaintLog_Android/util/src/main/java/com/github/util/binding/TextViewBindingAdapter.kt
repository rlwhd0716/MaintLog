package com.github.util.binding

import androidx.databinding.BindingAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.util.R
import com.github.util.extension.toDateFormat
import com.github.util.provider.getString

private val format_date_oneline = "yyyy.MM.dd HH:mm:ss"
private val format_time_oneline = "HH:mm:ss"
private val format_date_twoline = "yyyy-MM-dd\n HH:mm:ss"

@BindingAdapter("date", "dateFormat")
fun TextView.dateFormat(date:String?, format: String) {
    val f = when (format) {
        "one_line" -> format_date_oneline
        "two_line" -> format_date_twoline
        "one_line_time" -> format_time_oneline
        else -> format_date_oneline
    }
    this.text = date?.toDateFormat(f) ?: "-"
}

@BindingAdapter("checkDataNullHyphen")
fun TextView.checkDataNullHyphen(_data: String?) {
    text = _data ?: "-"
}

@BindingAdapter("isEmptyText")
fun TextView.isEmptyText(str: String?) {
    text = if(str.isNullOrEmpty()) "-" else str
}