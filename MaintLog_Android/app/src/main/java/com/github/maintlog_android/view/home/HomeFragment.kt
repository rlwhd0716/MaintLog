package com.github.maintlog_android.view.home

import com.github.domain.model.action.ActionData
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.BottomSheetHomeAddBinding
import com.github.maintlog_android.databinding.FragmentHomeBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.maintlog_android.view.improvement.form.ImprovementFormActivity
import com.github.maintlog_android.view.incident.form.IncidentFormActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import com.github.util.base.BaseRecyclerViewAdapter
import com.github.util.extension.startActivityInFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>(R.layout.fragment_home),
    BaseRecyclerViewAdapter.OnClickEvent<ActionData> {
    override var bindingApply: (FragmentHomeBinding.() -> Unit)? = {
        fabHomeAdd.setOnClickListener {
            val bottomSheet = BottomSheetDialog(requireContext())
            val sheetBinding = BottomSheetHomeAddBinding.inflate(layoutInflater)

            bottomSheet.setContentView(sheetBinding.root)

            sheetBinding.btnAddIncident.setOnClickListener {
                bottomSheet.dismiss()
                startActivityInFragment<IncidentFormActivity>()
            }

            sheetBinding.btnAddImprovement.setOnClickListener {
                bottomSheet.dismiss()
                startActivityInFragment<ImprovementFormActivity>()
            }

            bottomSheet.show()
        }
    }

    override fun itemClick(item: ActionData) {
        startActivityInFragment<ImprovementDetailActivity>()
    }
}