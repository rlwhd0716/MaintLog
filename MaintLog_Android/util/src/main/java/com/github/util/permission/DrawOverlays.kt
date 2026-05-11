package com.github.util.permission

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.provider.Settings.canDrawOverlays
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import com.github.util.extension.logE
import com.github.util.ui.showAlertDialog

/**
 * 앱위에 그리기 권한 허용 설정
 */
fun ComponentActivity.showOverlayPermission(
    register: ActivityResultLauncher<Intent>,
    title: String = "알림",
    message: String = "어플 사용을 위해서는 다른 앱 위에 표시 권한이 허용되어야 합니다.",
    block: (Boolean) -> Unit
) {
    if (!canDrawOverlays(this)) {
        if (isFinishing || isDestroyed) return
        showAlertDialog(
            title = title,
            message = message,
            ok = "확인",
            cancel = "종료",
            okAction = {
                logE("package:$packageName")
                register.launch(
                    Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                )
            },
            cancelAction = {
                block.invoke(false)
            }
        )
    } else {
        block.invoke(true)
    }
}

/**
 * 앱위에 그리기 권한 허용 여부 체크
 */
fun Context.canDrawOverlays() = canDrawOverlays(this)
