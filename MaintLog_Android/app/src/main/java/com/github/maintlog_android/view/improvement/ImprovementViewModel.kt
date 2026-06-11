package com.github.maintlog_android.view.improvement

import com.github.domain.model.action.ActionData
import com.github.domain.model.improvement.ImprovementDetailData
import com.github.util.base.BaseViewModel
import com.github.util.event.MutableListLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImprovementViewModel @Inject constructor() : BaseViewModel() {

    val improvementListAdapter = ImprovementListAdapter(this@ImprovementViewModel)

    private val _improvementList = MutableListLiveData<ImprovementDetailData>()
    val improvementList: MutableListLiveData<ImprovementDetailData> get() = _improvementList

    /**
     * 요청사항 목록 검색
     */
    fun searchImprovementList() {
        _improvementList.add(ImprovementDetailData(0))
        _improvementList.add(ImprovementDetailData(0))
        _improvementList.add(ImprovementDetailData(0))
        _improvementList.add(ImprovementDetailData(0))
        _improvementList.add(ImprovementDetailData(0))
    }

}