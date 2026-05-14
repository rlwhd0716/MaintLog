package com.github.maintlog_android.view.improvement.detail

import com.github.domain.model.work.WorkLogData
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityImprovementDetailBinding
import com.github.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImprovementDetailActivity :
    BaseActivity<ActivityImprovementDetailBinding, ImprovementDetailViewModel>(R.layout.activity_improvement_detail) {

    override var bindingApply: (ActivityImprovementDetailBinding.() -> Unit)? = {

    }

    override var vmApply: (ImprovementDetailViewModel.() -> Unit)? = {
        workLogList.clearAndAddAll(WorkLogData.dummyList().toMutableList())

        // TODO API 연동 전 임시 더미 데이터 사용
    }
}