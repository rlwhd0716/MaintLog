package com.github.maintlog_android.view.home

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.github.domain.model.action.ActionData
import com.github.maintlog_android.R
import com.github.maintlog_android.databinding.BottomSheetHomeAddBinding
import com.github.maintlog_android.databinding.FragmentHomeBinding
import com.github.maintlog_android.view.home.weather.WeatherActivity
import com.github.maintlog_android.view.improvement.detail.ImprovementDetailActivity
import com.github.maintlog_android.view.improvement.form.ImprovementFormActivity
import com.github.maintlog_android.view.incident.detail.IncidentDetailActivity
import com.github.maintlog_android.view.incident.form.IncidentFormActivity
import com.github.maintlog_android.view.main.MainViewModel
import com.github.util.base.BaseFragment
import com.github.util.base.BaseRecyclerViewAdapter
import com.github.util.extension.logE
import com.github.util.extension.startActivityInFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>(R.layout.fragment_home),
    BaseRecyclerViewAdapter.OnClickEvent<ActionData> {
    override var bindingApply: (FragmentHomeBinding.() -> Unit)? = {
        fabHomeAdd.setOnClickListener {
            val bottomSheet = BottomSheetDialog(requireContext())
            val sheetBinding = BottomSheetHomeAddBinding.inflate(layoutInflater)

            bottomSheet.setContentView(sheetBinding.root)

            sheetBinding.btnAddIncident.setOnClickListener {
                bottomSheet.dismiss()
                startActivityInFragment<IncidentFormActivity>()
            }

            sheetBinding.btnAddImprovement.setOnClickListener {
                bottomSheet.dismiss()
                startActivityInFragment<ImprovementFormActivity>()
            }

            bottomSheet.show()
        }

        // 메뉴 버튼
        layoutTopSearchBar.btnMenu.setOnClickListener {
            showHomeMenuBottomSheet()
        }

        // 상단 서치바
        layoutTopSearchBar.layoutSearchBar.setOnClickListener {
            showIntegratedSearch()
        }

        // 서치바 오버레이 닫기
        layoutSearchOverlay.btnCloseSearch.setOnClickListener {
            hideIntegratedSearch()
        }

        // 서치바 오버레이 루트
        layoutSearchOverlay.layoutSearchOverlayRoot.setOnClickListener {
            hideIntegratedSearch()
        }

        // 오버레이 서치바 클릭 시 닫히지 않도록 처리
        layoutSearchOverlay.layoutSearchInputBox.setOnClickListener {
            // 내부 클릭 시 닫히지 않도록 처리
        }

        // 날씨카드
        cvWeather.setOnClickListener {
            startActivityInFragment<WeatherActivity>()
        }

        // 최근 장애 목록
        layoutRecentIncidentsTitle.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_incidentFragment)
        }

        // 최근 장애 상세
        cardRecentIncident.setOnClickListener {
            startActivityInFragment<IncidentDetailActivity>()
        }

        // 최근 요청사항 목록
        layoutRecentImprovementTitle.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_improvementFragment)
        }
    }

    override var vmApply: (MainViewModel.() -> Unit)? = {
        homeActionAdapter.setOnClickEvent(this@HomeFragment)
    }

    private fun showHomeMenuBottomSheet() {
        val bottomSheet = BottomSheetDialog(requireContext())

        // TODO 메뉴 화면 레이아웃 만든 뒤 binding으로 교체
        // val sheetBinding = BottomSheetHomeMenuBinding.inflate(layoutInflater)
        // bottomSheet.setContentView(sheetBinding.root)

        bottomSheet.show()
    }

    private fun showIntegratedSearch() {
        binding?.layoutSearchOverlay?.root?.visibility = View.VISIBLE

        val fadeIn = AnimationUtils.loadAnimation(requireContext(), com.github.util.R.anim.fade_in)
        binding?.layoutSearchOverlay?.root?.startAnimation(fadeIn)

        binding?.layoutSearchOverlay?.etIntegratedSearch?.requestFocus()

        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(
            binding?.layoutSearchOverlay?.etIntegratedSearch,
            InputMethodManager.SHOW_IMPLICIT
        )
    }

    private fun hideIntegratedSearch() {
        val fadeOut = AnimationUtils.loadAnimation(requireContext(), com.github.util.R.anim.fade_out)

        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                binding?.layoutSearchOverlay?.root?.visibility = View.GONE
                binding?.layoutSearchOverlay?.etIntegratedSearch?.setText("")
            }

            override fun onAnimationRepeat(animation: Animation?) = Unit
        })

        binding?.layoutSearchOverlay?.root?.startAnimation(fadeOut)

        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            binding?.layoutSearchOverlay?.etIntegratedSearch?.windowToken,
            0
        )
    }

    override fun itemClick(item: ActionData) {
        startActivityInFragment<ImprovementDetailActivity>()
    }
}