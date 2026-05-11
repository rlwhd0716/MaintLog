package com.github.util.permission

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.github.util.ui.showAlertDialog

fun ComponentActivity.permissionFail(map: String) {
    showAlertDialog(
        title = "알림",
        message = "필수 권한을 획득 하지 못하여 종료 합니다. 다시 실행해주세요.",
        cancel = "종료",
        cancelAction = { finishAffinity() })
}

fun Fragment.permissionFail(map: String) {
    requireContext().showAlertDialog(
        title = "알림",
        message = "필수 권한을 획득 하지 못하여 종료 합니다. 다시 실행해주세요.",
        cancel = "종료",
        cancelAction = { requireActivity().finishAffinity() })
}