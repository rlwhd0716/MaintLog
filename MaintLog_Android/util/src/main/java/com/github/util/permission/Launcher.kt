package com.github.util.permission

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.github.util.extension.isNot
import com.github.util.extension.isTrue

/**
 * registerForActivityResult 의 경우,
 * 초기화 시 등록이 되어 있어야 함.
 * 앱위에 그리기 권한 요청을 위해, 중첩으로 등록 시 에러 발생.
 */
fun ComponentActivity.permissionLauncher(success: () -> Unit): ActivityResultLauncher<Intent> =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        checkPermissions().let { permissions ->
            permissions.filterValues { !it }
                .run {
                    isEmpty().isTrue {
                        success()
                    }.isNot {
                        permissionFail(map {
                            permissionToKr(applicationContext, it.key)
                        }.toList().distinct().joinToString())
                    }
                }
        }
    }

fun Fragment.permissionLauncher(success: () -> Unit): ActivityResultLauncher<Intent> =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        checkPermissions().let { permissions ->
            permissions.filterValues { !it }
                .run {
                    isEmpty().isTrue {
                        success()
                    }.isNot {
                        permissionFail(map {
                            permissionToKr(requireContext(), it.key)
                        }.toList().distinct().joinToString())
                    }
                }
        }
    }