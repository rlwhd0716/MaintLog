package com.github.maintlog_android.bind

import androidx.databinding.BindingAdapter
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat

@BindingAdapter("title", "searchText")
fun TextView.searchTextColor(title: String, searchText: String) {
    val spannableStringBuilder = SpannableStringBuilder(title)
    for (i in title.indices) {
        if (title.indexOf(searchText, i, true) != -1) {
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, com.github.util.R.color.primaryColor)),
                title.indexOf(searchText, i, true),
                title.indexOf(searchText, i, true) + searchText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
    text = spannableStringBuilder
}
