package com.github.maintlog_android.view.request.detail

import androidx.navigation.fragment.findNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentRequestDetailBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestDetailFragment :
    BaseFragment<FragmentRequestDetailBinding, MainViewModel>(R.layout.fragment_request_detail) {

    override var bindingApply: (FragmentRequestDetailBinding.() -> Unit)? = {
        btnRequestDetailEdit.setOnClickListener {
            findNavController().navigate(R.id.action_requestDetailFragment_to_requestFormFragment)
        }
        btnRequestLogAdd.setOnClickListener {
            showAlertDialog(message = "로그 추가 화면은 데이터 연동 단계에서 연결 예정입니다.")
        }
    }
}