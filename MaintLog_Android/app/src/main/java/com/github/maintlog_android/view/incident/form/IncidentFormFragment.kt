package com.github.maintlog_android.view.incident.form

import androidx.navigation.fragment.findNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentIncidentFormBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentFormFragment :
    BaseFragment<FragmentIncidentFormBinding, MainViewModel>(R.layout.fragment_incident_form) {

    override var bindingApply: (FragmentIncidentFormBinding.() -> Unit)? = {
        btnIncidentFormSave.setOnClickListener {
            findNavController().navigateUp()
        }
        btnIncidentFormCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}