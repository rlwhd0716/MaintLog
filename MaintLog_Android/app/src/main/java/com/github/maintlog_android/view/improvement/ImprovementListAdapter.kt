package com.github.maintlog_android.view.improvement

import com.github.domain.model.improvement.ImprovementDetailData
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ItemImprovementListBinding
import com.github.util.base.BaseRecyclerViewAdapter

class ImprovementListAdapter(val vm: ImprovementViewModel): BaseRecyclerViewAdapter<ItemImprovementListBinding, ImprovementDetailData>(
    R.layout.item_improvement_list
) {

    override fun onBindItem(
        binding: ItemImprovementListBinding?,
        data: ImprovementDetailData,
        position: Int
    ) {
        binding?.apply {
//            setVariable(BR.item, data)

            root.setOnClickListener {
                getOnClickEvent()?.itemClick(data)
            }
        }
    }
}