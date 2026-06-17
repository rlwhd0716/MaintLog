package com.github.maintlog_android.view.improvement

import android.view.View
import androidx.lifecycle.MutableLiveData
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
    val originList: MutableList<ImprovementDetailData> = mutableListOf()

    private val _improvementType = MutableLiveData<STATUS>()
    val improvementType: MutableLiveData<STATUS> get() = _improvementType

    /**
     * 요청사항 목록 검색
     */
    fun searchImprovementList() {
        originList.clear()

        _improvementList.add(ImprovementDetailData(0, "IN_PROGRESS"))
        _improvementList.add(ImprovementDetailData(1, "IN_PROGRESS"))
        _improvementList.add(ImprovementDetailData(2, "DONE"))
        _improvementList.add(ImprovementDetailData(3, "DONE"))
        _improvementList.add(ImprovementDetailData(4, "WAIT"))
        _improvementList.add(ImprovementDetailData(5, "IN_PROGRESS"))
        _improvementList.add(ImprovementDetailData(6, "IN_PROGRESS"))
        _improvementList.add(ImprovementDetailData(7, "DONE"))
        _improvementList.add(ImprovementDetailData(8, "DONE"))
        _improvementList.add(ImprovementDetailData(9, "WAIT"))

        _improvementList.value?.let { originList.addAll(it) }
    }

    fun filteredList() {
        if (_improvementType.value == STATUS.ALL) {
            _improvementList.value = originList
        } else {
            _improvementList.value = originList.filter { _improvementType.value?.toString() == it.status }.toMutableList()
        }
    }

}

enum class STATUS {
    ALL,
    IN_PROGRESS,
    DONE,
    WAIT
}