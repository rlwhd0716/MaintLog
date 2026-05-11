package com.github.util.ui.selector

import androidx.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.util.BR
import com.github.util.R
import com.github.util.base.BaseRecyclerViewAdapter
import com.github.util.base.BaseRecyclerViewHolder
import com.github.util.databinding.ItemRadioDialogBinding
import com.github.util.databinding.ItemSelectDialogBinding
import com.github.util.extension.logE

class SelectDialogAdapter(
    override var datas: MutableList<String>,
    val dialogType: DialogType = DialogType.LIST,
    val itemClick: (String) -> Unit
) : BaseRecyclerViewAdapter<ViewDataBinding, String>(R.layout.item_select_dialog) {

    var selectCheck: ArrayList<Int> = arrayListOf()
//    init {
//        if(DialogType.RADIO == dialogType) {
//
//        }
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): BaseRecyclerViewHolder<ViewDataBinding, String> =
        LayoutInflater.from(parent.context).let {
            return when(dialogType) {
                DialogType.LIST -> SelectedViewHolder(it.inflate(R.layout.item_select_dialog, parent, false))
                DialogType.RADIO -> RadioViewHolder(it.inflate(R.layout.item_radio_dialog, parent, false))
            } as BaseRecyclerViewHolder<ViewDataBinding, String>
        }

    override fun onBindItem(binding: ViewDataBinding?, data: String, position: Int) {
        binding?.apply {
            when(binding) {
                is ItemSelectDialogBinding -> {
                    setVariable(BR.item, data)
                    root.setOnClickListener {
                        itemClick(data)
                    }
                }
                is ItemRadioDialogBinding -> {
                    setVariable(BR.item, data)

                    binding.rbSelectRow.apply {
                        isChecked = selectCheck[position] == 1
                        logE("isChecked - $isChecked")
                        setOnClickListener {
                            itemClick(data)
                            for (k in selectCheck.indices) {
                                if (k == position) {
                                    selectCheck[k] = 1
                                } else {
                                    selectCheck[k] = 0
                                }
                            }
                            notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}

class SelectedViewHolder(itemView: View) :
    BaseRecyclerViewHolder<ItemSelectDialogBinding, String>(itemView)

class RadioViewHolder(itemView: View) :
    BaseRecyclerViewHolder<ItemRadioDialogBinding, String>(itemView)