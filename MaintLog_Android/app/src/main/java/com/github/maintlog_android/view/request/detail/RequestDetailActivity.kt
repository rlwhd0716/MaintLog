package com.github.maintlog_android.view.request.detail

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityRequestDetailBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestDetailActivity :
    BaseActivity<ActivityRequestDetailBinding, MainViewModel>(R.layout.activity_request_detail) {

    override var bindingApply: (ActivityRequestDetailBinding.() -> Unit)? = {

    }
}