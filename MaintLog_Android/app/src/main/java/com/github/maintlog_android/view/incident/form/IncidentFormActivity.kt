package com.github.maintlog_android.view.incident.form

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityIncidentFormBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentFormActivity :
    BaseActivity<ActivityIncidentFormBinding, MainViewModel>(R.layout.activity_incident_form) {

    override var bindingApply: (ActivityIncidentFormBinding.() -> Unit)? = {
//        btnIncidentFormSave.setOnClickListener {
//            findNavController().navigateUp()
//        }
//        btnIncidentFormCancel.setOnClickListener {
//            findNavController().navigateUp()
//        }
    }
}