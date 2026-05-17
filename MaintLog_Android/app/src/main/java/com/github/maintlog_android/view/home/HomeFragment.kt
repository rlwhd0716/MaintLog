package com.github.maintlog_android.view.home

import androidx.navigation.fragment.findNavController
import com.github.domain.model.action.ActionData
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentHomeBinding
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.maintlog_android.view.improvement.form.ImprovementFormActivity
import com.github.util.base.BaseFragment
import com.github.util.base.BaseRecyclerViewAdapter
import com.github.util.extension.startActivityInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, MainViewModel>(R.layout.fragment_home), BaseRecyclerViewAdapter.OnClickEvent<ActionData>{
    override var bindingApply: (FragmentHomeBinding.() -> Unit)? = {

        /* 하단 버튼 */
        btnHomeAddRequest.setOnClickListener {
            startActivityInFragment<ImprovementFormActivity>()
        }
        btnHomeAddIncident.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_incidentFormFragment)
        }
    }

    override fun itemClick(item: ActionData) {
        startActivityInFragment<ImprovementDetailActivity>()
    }
}