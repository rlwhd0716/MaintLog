package com.github.maintlog_android.view.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.github.domain.model.common.StatusType
import com.github.maintlog_android.R

class StatusBadgeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    init {
        setStatus(StatusType.WAIT)
    }

    fun setStatus(statusType: StatusType?) {
        val status = statusType ?: StatusType.WAIT

        text = status.label

        when (status) {
            StatusType.WAIT -> {
                setTextColor(ContextCompat.getColor(context, com.github.util.R.color.colorNeutral))
                setBackgroundResource(R.drawable.bg_status_wait)
            }

            StatusType.PROGRESS -> {
                setTextColor(ContextCompat.getColor(context, com.github.util.R.color.colorInfo))
                setBackgroundResource(R.drawable.bg_status_progress)
            }

            StatusType.ACTION -> {
                setTextColor(ContextCompat.getColor(context, com.github.util.R.color.colorError))
                setBackgroundResource(R.drawable.bg_status_error)
            }

            StatusType.COMPLETE -> {
                setTextColor(ContextCompat.getColor(context, com.github.util.R.color.colorSuccess))
                setBackgroundResource(R.drawable.bg_status_success)
            }

            StatusType.HOLD -> {
                setTextColor(ContextCompat.getColor(context, com.github.util.R.color.colorWarning))
                setBackgroundResource(R.drawable.bg_status_hold)
            }
        }

        gravity = android.view.Gravity.CENTER
        textSize = 12f
        typeface = resources.getFont(com.github.util.R.font.pretendard_semibold)

        setPadding(
            dp(8),
            dp(4),
            dp(8),
            dp(4)
        )
    }

    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }
}

@BindingAdapter("statusType")
fun setStatusType(view: StatusBadgeView, statusType: StatusType?) {
    view.setStatus(statusType ?: StatusType.WAIT)
}

@BindingAdapter("statusText")
fun setStatusText(view: StatusBadgeView, statusText: String?) {
    view.setStatus(StatusType.from(statusText))
}