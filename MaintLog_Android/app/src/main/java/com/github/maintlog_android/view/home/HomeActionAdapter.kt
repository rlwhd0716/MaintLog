package com.github.maintlog_android.view.home

import com.github.domain.model.action.ActionData
import com.github.maintlog_android.BR
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ItemHomeActionBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseRecyclerViewAdapter

class HomeActionAdapter(val vm: MainViewModel): BaseRecyclerViewAdapter<ItemHomeActionBinding, ActionData>(
    R.layout.item_home_action
) {

    override fun onBindItem(
        binding: ItemHomeActionBinding?,
        data: ActionData,
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