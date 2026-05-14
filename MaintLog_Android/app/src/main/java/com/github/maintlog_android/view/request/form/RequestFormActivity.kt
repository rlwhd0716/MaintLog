package com.github.maintlog_android.view.request.form

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityRequestFormBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFormActivity :
    BaseActivity<ActivityRequestFormBinding, MainViewModel>(R.layout.activity_request_form) {

    override var bindingApply: (ActivityRequestFormBinding.() -> Unit)? = {

    }
}