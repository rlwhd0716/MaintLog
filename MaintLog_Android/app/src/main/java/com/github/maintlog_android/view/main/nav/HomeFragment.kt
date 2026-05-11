package com.github.maintlog_android.view.main.nav

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentHomeBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, MainViewModel>(R.layout.fragment_home) {
}