package com.github.maintlog_android.view.request

import androidx.navigation.fragment.findNavController
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.FragmentRequestBinding
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestFragment: BaseFragment<FragmentRequestBinding, MainViewModel>(R.layout.fragment_request) {
    override var bindingApply: (FragmentRequestBinding.() -> Unit)? = {
        itemRequestLoginError.setOnClickListener {
            findNavController().navigate(R.id.action_requestFragment_to_requestDetailFragment)
        }
        itemRequestPaymentError.setOnClickListener {
            findNavController().navigate(R.id.action_requestFragment_to_requestDetailFragment)
        }
        itemRequestDataFix.setOnClickListener {
            findNavController().navigate(R.id.action_requestFragment_to_requestDetailFragment)
        }
        itemRequestApiDelay.setOnClickListener {
            findNavController().navigate(R.id.action_requestFragment_to_requestDetailFragment)
        }
        itemRequestUiDesign.setOnClickListener {
            findNavController().navigate(R.id.action_requestFragment_to_requestDetailFragment)
        }
        fabRequestAdd.setOnClickListener {
            findNavController().navigate(R.id.action_requestFragment_to_requestFormFragment)
        }
    }
}