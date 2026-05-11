package com.github.util.ui.selector

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.github.util.BR
import com.github.util.databinding.DialogSelectBinding

/**
 * 선택 BottomSheet 다이얼로그
 */
class SelectDialog(
    mContext: Context,
    val title: String = "",
    val list: List<String>,
    val selected: ((String) -> Unit)? = null
): BottomSheetDialog(mContext) {

    private lateinit var binding: DialogSelectBinding
    private lateinit var adapter: SelectDialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSelectBinding.inflate(layoutInflater)
        binding.setVariable(BR.dialog, this)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

        adapter = SelectDialogAdapter(list.toMutableList(), DialogType.LIST) {
            selected?.invoke(it)
            dismiss()
        }
        rvSelectDialog.adapter = adapter

        binding.btnDialogClose.setOnClickListener {
            cancel()
        }
    }
}