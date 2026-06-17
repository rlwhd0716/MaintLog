package com.github.maintlog_android.view.improvement

import androidx.lifecycle.lifecycleScope
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentImprovementBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.maintlog_android.view.improvement.form.ImprovementFormActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import com.github.util.extension.logE
import com.github.util.extension.startActivityInFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImprovementFragment: BaseFragment<FragmentImprovementBinding, ImprovementViewModel>(R.layout.fragment_improvement) {
    override var bindingApply: (FragmentImprovementBinding.() -> Unit)? = {
        fabImprovementAdd.setOnClickListener {
            startActivityInFragment<ImprovementFormActivity>()
        }

        tlImprovementList.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(t: TabLayout.Tab?) {
                when(t?.position) {
                    0 -> { viewModel.improvementType.value = STATUS.ALL }
                    1 -> { viewModel.improvementType.value = STATUS.IN_PROGRESS }
                    2 -> { viewModel.improvementType.value = STATUS.DONE }
                    3 -> { viewModel.improvementType.value = STATUS.WAIT }
                }
            }

            override fun onTabUnselected(t: TabLayout.Tab?) {

            }

            override fun onTabReselected(t: TabLayout.Tab?) {

            }
        })
    }

    override var vmApply: (ImprovementViewModel.() -> Unit)? = {
        lifecycleScope.launch {
            delay(100)
            searchImprovementList()
        }

        improvementType.observe(this@ImprovementFragment) {
            filteredList()
        }
    }
}