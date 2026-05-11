package com.github.util.base

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.ViewModelLazy
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.github.util.BR
import com.github.util.extension.hideKeyboard
import com.github.util.extension.showKeyboard
import com.github.util.extension.toastShow
import com.github.util.ui.ProgressDialogUtil
import java.lang.reflect.ParameterizedType

abstract class BaseBottomSheetDialogFragment<B : ViewDataBinding, VM : BaseViewModel>(layoutResId: Int) :
    BottomSheetDialogFragment(layoutResId) {

    val TAG = javaClass.simpleName

    protected var binding: B? = null

    protected val viewModel by ViewModelLazy(
        ((javaClass.genericSuperclass as ParameterizedType?)?.actualTypeArguments?.get(1) as Class<VM>).kotlin,
        { requireActivity().viewModelStore },
        { requireActivity().defaultViewModelProviderFactory },
        { requireActivity().defaultViewModelCreationExtras }
    )
    var behavior: BottomSheetBehavior<*>? = null

    protected open var bindingApply: (B.() -> Unit)? = null
    protected open var vmApply: (VM.() -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(requireView())

        with(binding) {
            this?.lifecycleOwner = viewLifecycleOwner
            this?.setVariable(BR.vm, viewModel)
            this?.let { bindingApply?.invoke(it) }
        }

        with(dialog) {
            this?.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            this?.setOnShowListener { dialogInterface ->
                val sheetDialog = dialogInterface as? BottomSheetDialog
                val bottomsheet =
                    sheetDialog?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
                bottomsheet?.let {
                    val layoutParams = view.layoutParams
                    layoutParams.height = getBottomSheetDialogDefaultHeight()
                    view.layoutParams = layoutParams

                    behavior = BottomSheetBehavior.from(it)
                    behavior?.state = BottomSheetBehavior.STATE_EXPANDED
                    behavior?.skipCollapsed = true
                }
            }
        }

        with(viewModel) {
            lifecycle.addObserver(this)
            startActivity.observe(viewLifecycleOwner) {
                startActivity(Intent(activity, it.first))
                if (it.second) activity?.finish()
            }
//            toast.observe(viewLifecycleOwner) { toastShow(it) }
            finish.observe(viewLifecycleOwner) { requireActivity().finish() }
            isLoading.observe(viewLifecycleOwner) {
                if (it) ProgressDialogUtil.show(requireContext())
                else ProgressDialogUtil.dismiss()
            }
            startActivityIntent.observe(viewLifecycleOwner) {
                startActivity(Intent(requireActivity(), it.second).apply(it.first))
                if (it.third) requireActivity().finish()
            }
            isKeyboard.observe(viewLifecycleOwner) {
                if (it) {
                    showKeyboard()
                } else {
                    hideKeyboard()
                }
            }
            vmApply?.invoke(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 60 / 100
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}