package com.github.maintlog_android.view.search

import androidx.navigation.fragment.findNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentSearchBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: BaseFragment<FragmentSearchBinding, MainViewModel>(R.layout.fragment_search) {
    override var bindingApply: (FragmentSearchBinding.() -> Unit)? = {
        itemSearchIncidentServer.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_incidentDetailFragment)
        }
        itemSearchRequestLogin.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_requestDetailFragment)
        }
        itemSearchRequestPayment.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_requestDetailFragment)
        }
        itemSearchIncidentDatabase.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_incidentDetailFragment)
        }
    }
}