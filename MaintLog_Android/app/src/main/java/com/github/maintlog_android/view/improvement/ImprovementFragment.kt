package com.github.maintlog_android.view.improvement

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentImprovementBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.maintlog_android.view.improvement.form.ImprovementFormActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import com.github.util.extension.startActivityInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImprovementFragment: BaseFragment<FragmentImprovementBinding, MainViewModel>(R.layout.fragment_improvement) {
    override var bindingApply: (FragmentImprovementBinding.() -> Unit)? = {
        itemRequestLoginError.setOnClickListener {
            startActivityInFragment<ImprovementDetailActivity>()
        }
        itemRequestPaymentError.setOnClickListener {
            startActivityInFragment<ImprovementDetailActivity>()
        }
        itemRequestDataFix.setOnClickListener {
            startActivityInFragment<ImprovementDetailActivity>()
        }
        itemRequestApiDelay.setOnClickListener {
            startActivityInFragment<ImprovementDetailActivity>()
        }
        itemRequestUiDesign.setOnClickListener {
            startActivityInFragment<ImprovementDetailActivity>()
        }
        fabRequestAdd.setOnClickListener {
            startActivityInFragment<ImprovementFormActivity>()
        }
    }
}