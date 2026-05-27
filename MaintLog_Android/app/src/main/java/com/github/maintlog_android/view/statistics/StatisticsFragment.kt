package com.github.maintlog_android.view.statistics

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentStatisticsBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment :
    BaseFragment<FragmentStatisticsBinding, MainViewModel>(R.layout.fragment_statistics) {
    override var bindingApply: (FragmentStatisticsBinding.() -> Unit)? = {

    }
}