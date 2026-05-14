package com.github.maintlog_android.view.incident

import androidx.navigation.fragment.findNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentIncidentBinding
import com.github.maintlog_android.view.incident.form.IncidentFormActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import com.github.util.extension.startActivityInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentFragment :
    BaseFragment<FragmentIncidentBinding, MainViewModel>(R.layout.fragment_incident) {
    override var bindingApply: (FragmentIncidentBinding.() -> Unit)? = {
        itemIncidentServerDown.setOnClickListener {
            findNavController().navigate(R.id.action_incidentFragment_to_incidentDetailFragment)
        }
        itemIncidentPaymentError.setOnClickListener {
            findNavController().navigate(R.id.action_incidentFragment_to_incidentDetailFragment)
        }
        itemIncidentDatabaseError.setOnClickListener {
            findNavController().navigate(R.id.action_incidentFragment_to_incidentDetailFragment)
        }
        itemIncidentServerOverload.setOnClickListener {
            findNavController().navigate(R.id.action_incidentFragment_to_incidentDetailFragment)
        }
        fabIncidentAdd.setOnClickListener {
            startActivityInFragment<IncidentFormActivity>()
        }
    }
}