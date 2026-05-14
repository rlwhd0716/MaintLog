package com.github.maintlog_android.view.improvement.detail

import com.github.domain.model.work.WorkLogData
import com.github.maintlog_android.view.work.WorkLogAdapter
import com.github.util.base.BaseViewModel
import com.github.util.event.MutableListLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImprovementDetailViewModel @Inject constructor(): BaseViewModel() {

    // 개선 작업내역 어댑터
    val workLogAdapter = WorkLogAdapter(improvementDetailVM = this)

    // 작업내역 목록
    private val _workLogList = MutableListLiveData<WorkLogData>()
    val workLogList: MutableListLiveData<WorkLogData> get() = _workLogList


}