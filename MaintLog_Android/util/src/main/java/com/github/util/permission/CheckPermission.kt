package com.github.util.permission

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

//fun Activity.checkPermissions(permissions: Array<String>): Map<String, Boolean> =
//    mutableMapOf<String, Boolean>().apply {
//        if (permissions.isEmpty()) return@apply
//
//        permissions.forEach {
//            put(
//                key = it,
//                value = ActivityCompat.checkSelfPermission(this@checkPermissions, it)
//                        == PackageManager.PERMISSION_GRANTED
//            )
//        }
//    }


fun Activity.checkPermissions(): Map<String, Boolean> =
    mutableMapOf<String, Boolean>().apply {
        if (getPermissionsArray().isEmpty()) return@apply

        getPermissionsArray().forEach {
            this[it] = (ActivityCompat.checkSelfPermission(this@checkPermissions, it)
                    == PackageManager.PERMISSION_GRANTED)
        }

        if (getPermissionsLocationArray().isEmpty()) return@apply

        getPermissionsLocationArray().forEach {
            this[it] = (ActivityCompat.checkSelfPermission(this@checkPermissions, it)
                    == PackageManager.PERMISSION_GRANTED)
        }
    }

fun Fragment.checkPermissions(): Map<String, Boolean> =
    mutableMapOf<String, Boolean>().apply {
        if (getPermissionsArray().isEmpty()) return@apply

        getPermissionsArray().forEach {
            this[it] = (ActivityCompat.checkSelfPermission(requireContext(), it)
                    == PackageManager.PERMISSION_GRANTED)
        }

        if (getPermissionsLocationArray().isEmpty()) return@apply

        getPermissionsLocationArray().forEach {
            this[it] = (ActivityCompat.checkSelfPermission(requireContext(), it)
                    == PackageManager.PERMISSION_GRANTED)
        }
    }