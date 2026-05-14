package com.github.maintlog_android.view.work

import com.github.domain.model.work.WorkLogData
import com.github.maintlog_android.BR
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ItemWorkLogBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailViewModel
import com.github.util.base.BaseRecyclerViewAdapter

class WorkLogAdapter(
    val improvementDetailVM: ImprovementDetailViewModel? = null,
    val incidentDetailVM: ImprovementDetailViewModel? = null
): BaseRecyclerViewAdapter<ItemWorkLogBinding, WorkLogData>(
    R.layout.item_work_log
) {

    override fun onBindItem(
        binding: ItemWorkLogBinding?,
        data: WorkLogData,
        position: Int
    ) {
        binding?.apply {
            setVariable(BR.item, data)

            root.setOnClickListener {
                getOnClickEvent()?.itemClick(data)
            }
        }
    }
}