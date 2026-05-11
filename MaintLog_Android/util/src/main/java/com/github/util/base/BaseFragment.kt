package com.github.util.base

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelLazy
import com.github.util.BR
import com.github.util.R
import com.github.util.extension.hideKeyboard
import com.github.util.extension.showKeyboard
import com.github.util.extension.toastShow
import com.github.util.ui.ProgressDialogUtil
import com.github.util.ui.showAlertDialog
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(layoutResId: Int) :
    Fragment(layoutResId) {
    val TAG = javaClass.simpleName

    protected var binding: B? = null

    protected val viewModel by ViewModelLazy(
        ((javaClass.genericSuperclass as ParameterizedType?)?.actualTypeArguments?.get(1) as Class<VM>).kotlin,
        { requireActivity().viewModelStore },
        { requireActivity().defaultViewModelProviderFactory },
        { requireActivity().defaultViewModelCreationExtras }
    )


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


        with(viewModel) {
            lifecycle.addObserver(this)
            startActivity.observe(viewLifecycleOwner) {
                startActivity(Intent(activity, it.first))
                if (it.second) activity?.finish()
            }
            toast.observe(viewLifecycleOwner) { toastShow(it) }
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

    fun showAlertDialog(
        title: String = "",
        message: String,
        ok: String? = null,
        cancel: String? = getString(R.string.common_close),
        okAction: (() -> Unit)? = null,
        cancelAction: (() -> Unit)? = null
    ) {
        requireActivity().showAlertDialog(
            title = title,
            message = message,
            ok = ok,
            cancel = cancel,
            okAction = okAction,
            cancelAction = cancelAction
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}