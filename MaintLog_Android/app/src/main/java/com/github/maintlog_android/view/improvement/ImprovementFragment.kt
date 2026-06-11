package com.github.maintlog_android.view.improvement

import androidx.lifecycle.lifecycleScope
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentImprovementBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.maintlog_android.view.improvement.form.ImprovementFormActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import com.github.util.extension.startActivityInFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImprovementFragment: BaseFragment<FragmentImprovementBinding, ImprovementViewModel>(R.layout.fragment_improvement) {
    override var bindingApply: (FragmentImprovementBinding.() -> Unit)? = {
        fabRequestAdd.setOnClickListener {
            startActivityInFragment<ImprovementFormActivity>()
        }
    }

    override var vmApply: (ImprovementViewModel.() -> Unit)? = {
        lifecycleScope.launch {
            delay(100)
            searchImprovementList()
        }
    }
}