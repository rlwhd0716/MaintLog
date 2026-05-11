package com.github.util.ui

import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import androidx.databinding.DataBindingUtil
import android.graphics.Color
import android.view.WindowManager
import androidx.core.graphics.drawable.toDrawable
import com.github.util.R
import com.github.util.databinding.DialogProgressBinding

object ProgressDialogUtil {
    private var progressDialog: Dialog? = null

    fun show(context: Context) {
        if (progressDialog == null) {
            progressDialog = Dialog(context).apply {
                val mBinding: DialogProgressBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.dialog_progress, null, true)

                mBinding.loadingText.apply {
                    val valueDot = ValueAnimator.ofInt(1, 6)
                    valueDot.duration = 2000
                    valueDot.repeatCount = ValueAnimator.INFINITE
                    valueDot.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
                        val value = animation.animatedValue
                        var dot = ""

                        if (value == 1) {
                            this.text = "Loading"
                        }

                        when (value) {
                            2 -> dot = "."
                            3 -> dot = ".."
                            4 -> dot = "..."
                            5 -> dot = "...."
                        }

                        this.text = "Loading${dot}"
                    })

                    valueDot.start()
                }

                setContentView(mBinding.root)
                setCancelable(false)
                // Dialog 호출시 배경화면이 검정색으로 바뀌는 것 막기
                window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            }
            progressDialog?.show()
        }  else {
            if (progressDialog?.isShowing == false) progressDialog?.show()
        }
    }

    fun dismiss() {
        progressDialog?.dismiss()
    }

    fun destroy() {
        progressDialog?.let { if (it.isShowing) it.dismiss() }
        progressDialog = null
    }

    fun isShowing() = progressDialog?.isShowing == true
}