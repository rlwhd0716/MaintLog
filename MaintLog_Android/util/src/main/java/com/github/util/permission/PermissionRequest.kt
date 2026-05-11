package com.github.util.permission

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.util.ui.showAlertDialog

/**
 * registerForActivityResult 를 초기화 시 등록
 */
fun ComponentActivity.permissionsRequest(
    launcher: ActivityResultLauncher<Intent>,
    success: () -> Unit
) =
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
        map.filterValues { !it }.run {
            when {
                isEmpty() -> success()
                else -> {
                    if (isFinishing || isDestroyed) return@run
                    showAlertDialog(
                        title = "알림",
                        message = map {
                            permissionToKr(
                                applicationContext,
                                it.key
                            )
                        }.toList()
                            .distinct()
                            .joinToString() + " 권한을 위해 설정 화면으로 이동 하겠습니까?",
                        ok = "확인",
                        cancel = "종료",
                        okAction = {
                            launcher.launch(actionApplicationDetailsSettings())
                        },
                        cancelAction = {
                            finishAffinity()
                        }
                    )
                }
            }
        }
    }

fun ComponentActivity.permissionRequest(
    launcher: ActivityResultLauncher<Intent>,
    permission: String,
    success: () -> Unit
) =
    registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            success()
        } else {
            run {
                if (isFinishing || isDestroyed) return@run
                showAlertDialog(
                    title = "알림",
                    message = permissionToKr(
                        applicationContext,
                        permission
                    ) + " 권한을 위해 설정 화면으로 이동 하겠습니까?",
                    ok = "확인",
                    cancel = "종료",
                    okAction = {
                        launcher.launch(actionApplicationDetailsSettings())
                    },
                    cancelAction = {
                        finishAffinity()
                    }
                )
            }
        }
    }


fun AppCompatActivity.permissionsRequest(success: () -> Unit, fail: () -> Unit) =
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
        map.filterValues { !it }.run {
            when {
                isEmpty() -> success()
                else -> fail()
            }
        }
    }

fun Fragment.permissionsRequest(success: () -> Unit, fail: () -> Unit) =
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
        map.filterValues { !it }.run {
            when {
                isEmpty() -> success()
                else -> fail()
            }
        }
    }