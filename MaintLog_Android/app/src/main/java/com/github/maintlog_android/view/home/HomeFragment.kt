package com.github.maintlog_android.view.home

import androidx.navigation.fragment.findNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentHomeBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.maintlog_android.view.request.form.RequestFormActivity
import com.github.util.base.BaseFragment
import com.github.util.extension.startActivityInFragment
import com.github.util.extension.startActivityIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding, MainViewModel>(R.layout.fragment_home) {
    override var bindingApply: (FragmentHomeBinding.() -> Unit)? = {
        itemHomeRequestLogin.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_requestDetailFragment)
        }
        itemHomeRequestData.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_requestDetailFragment)
        }
        itemHomeRequestApi.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_requestDetailFragment)
        }
        itemHomeIncidentServer.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_incidentDetailFragment)
        }
        itemHomeIncidentNetwork.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_incidentDetailFragment)
        }
        itemHomeIncidentDb.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_incidentDetailFragment)
        }

        /* 하단 버튼 */
        btnHomeAddRequest.setOnClickListener {
            startActivityInFragment<RequestFormActivity>()
        }
        btnHomeAddIncident.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_incidentFormFragment)
        }
    }
}