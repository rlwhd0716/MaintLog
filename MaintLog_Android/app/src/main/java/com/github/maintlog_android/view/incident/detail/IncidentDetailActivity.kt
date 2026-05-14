package com.github.maintlog_android.view.incident.detail

import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.ActivityIncidentDetailBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncidentDetailActivity :
    BaseActivity<ActivityIncidentDetailBinding, MainViewModel>(R.layout.activity_incident_detail) {

    override var bindingApply: (ActivityIncidentDetailBinding.() -> Unit)? = {
//        btnIncidentDetailEdit.setOnClickListener {
//            findNavController().navigate(R.id.action_incidentDetailFragment_to_incidentFormFragment)
//        }
//        btnIncidentLogAdd.setOnClickListener {
//            showAlertDialog(message = "조치 로그 추가 화면은 데이터 연동 단계에서 연결 예정입니다.")
//        }
    }
}