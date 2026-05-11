package com.github.util.ui

import android.content.Context
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.withStyledAttributes
import com.github.util.R
import com.github.util.databinding.CustomTitleViewBinding

class CustomTitleView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    private val binding: CustomTitleViewBinding =
        CustomTitleViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var onBackClickMethod: String? = null
//    private var stringResId: Int = 0

    init {
        context?.withStyledAttributes(attrs, R.styleable.CustomTitleViewData) {
//            stringResId = getResourceId(R.styleable.CustomTitleViewData_itemTitleText, 0)

            setTitleText(getString(R.styleable.CustomTitleViewData_itemTitleText))
            onBackClickMethod = getString(R.styleable.CustomTitleViewData_onBackClick)
        }

        binding.btnTopBack.setOnClickListener {
            invokeClickMethod()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Locale이 변경되었을 때 다시 string을 로드
//        if (stringResId != 0) {
//            val updatedContext = context.createConfigurationContext(newConfig)
//            setTitleText(updatedContext.resources.getString(stringResId))
//        }
    }

    fun setTitleText(text: String?) {
        val layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f)
        binding.tvTopTitle.layoutParams = layoutParams
        binding.tvTopTitle.text = text

        onRefresh()
    }

    private fun invokeClickMethod() {
        if (!onBackClickMethod.isNullOrEmpty()) {
            try {
                val method = (context as? AppCompatActivity)?.javaClass?.getMethod(onBackClickMethod!!)
                method?.invoke(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun onRefresh() {
        invalidate()
        requestLayout()
    }
}
