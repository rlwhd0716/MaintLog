package com.github.maintlog_android.view.request.form

import androidx.navigation.fragment.findNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentRequestFormBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFormFragment :
    BaseFragment<FragmentRequestFormBinding, MainViewModel>(R.layout.fragment_request_form) {

    override var bindingApply: (FragmentRequestFormBinding.() -> Unit)? = {
        btnRequestFormSave.setOnClickListener {
            findNavController().navigateUp()
        }
        btnRequestFormCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}