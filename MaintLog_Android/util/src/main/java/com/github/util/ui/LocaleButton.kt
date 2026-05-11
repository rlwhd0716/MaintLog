package com.github.util.ui

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes
import com.google.android.material.button.MaterialButton

class LocaleButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : MaterialButton(context, attrs, defStyle) {

    private var stringResId: Int = 0

    init {
        if (attrs != null) {
            context.withStyledAttributes(attrs, intArrayOf(android.R.attr.text)) {
                val resId = getResourceId(0, 0)
                if (resId != 0) {
                    stringResId = resId
                    setText(resId) // 초기 세팅
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Locale이 변경되었을 때 다시 string을 로드
        if (stringResId != 0) {
            val updatedContext = context.createConfigurationContext(newConfig)
            text = updatedContext.resources.getString(stringResId)
        }
    }
}
