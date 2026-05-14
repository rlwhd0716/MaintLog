package com.github.maintlog_android.view.improvement.form

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityImprovementFormBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImprovementFormActivity :
    BaseActivity<ActivityImprovementFormBinding, MainViewModel>(R.layout.activity_improvement_form) {

    override var bindingApply: (ActivityImprovementFormBinding.() -> Unit)? = {

    }
}