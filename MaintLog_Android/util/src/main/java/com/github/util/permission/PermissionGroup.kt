package com.github.util.permission

import android.Manifest
import android.content.Context
import android.os.Build
import com.github.util.R

fun permissionToKr(c: Context, permission: String) =
    permissionGroupToKr(c, permissionToGroup(permission))

/*when (permission) {
    Manifest.permission.READ_CALENDAR,
    Manifest.permission.WRITE_CALENDAR -> PermissionGroup.CALENDAR
    Manifest.permission.CAMERA -> PermissionGroup.CAMERA
    Manifest.permission.READ_CONTACTS,
    Manifest.permission.WRITE_CONTACTS,
    Manifest.permission.GET_ACCOUNTS -> PermissionGroup.CONTACTS
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_BACKGROUND_LOCATION -> PermissionGroup.LOCATION
    Manifest.permission.RECORD_AUDIO -> PermissionGroup.MICROPHONE
    Manifest.permission.READ_PHONE_STATE,
    Manifest.permission.CALL_PHONE,
    Manifest.permission.ADD_VOICEMAIL,
    Manifest.permission.USE_SIP -> PermissionGroup.PHONE
    Manifest.permission.READ_CALL_LOG,
    Manifest.permission.WRITE_CALL_LOG,
    Manifest.permission.PROCESS_OUTGOING_CALLS -> PermissionGroup.CALL_LOG
    Manifest.permission.BODY_SENSORS -> PermissionGroup.SENSORS
    Manifest.permission.SEND_SMS,
    Manifest.permission.RECEIVE_SMS,
    Manifest.permission.READ_SMS,
    Manifest.permission.RECEIVE_WAP_PUSH,
    Manifest.permission.RECEIVE_MMS -> PermissionGroup.SMS
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE -> PermissionGroup.STORAGE
    Manifest.permission.BLUETOOTH,
    Manifest.permission.BLUETOOTH_ADMIN,
    Manifest.permission.BLUETOOTH_CONNECT,
    Manifest.permission.BLUETOOTH_ADVERTISE,
    Manifest.permission.BLUETOOTH_PRIVILEGED,
    Manifest.permission.BLUETOOTH_SCAN -> PermissionGroup.BLUETOOTH
    Manifest.permission.POST_NOTIFICATIONS -> PermissionGroup.NOTIFICATION
    else -> PermissionGroup.NON
}*/

@Suppress("DEPRECATION")
fun permissionToGroup(permission: String) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        when (permission) {
            Manifest.permission.CAMERA -> PermissionGroup.CAMERA
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION -> PermissionGroup.LOCATION

            Manifest.permission.RECORD_AUDIO -> PermissionGroup.MICROPHONE
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP -> PermissionGroup.PHONE

            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> PermissionGroup.STORAGE

            Manifest.permission.POST_NOTIFICATIONS -> PermissionGroup.NOTIFICATION
            else -> PermissionGroup.NON
        }
    } else {
        when (permission) {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION -> PermissionGroup.LOCATION

            Manifest.permission.RECORD_AUDIO -> PermissionGroup.MICROPHONE
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP -> PermissionGroup.PHONE

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE -> PermissionGroup.STORAGE

            else -> PermissionGroup.NON
        }
    }


/**
 * 권한 그룹별 반환 네임
 * Created by 이기종 on 2022-03-10
 */
fun permissionGroupToKr(c: Context, group: PermissionGroup) = when (group) {
    is PermissionGroup.CALENDAR -> c.resources.getString(R.string.permission_calendar)
    is PermissionGroup.CALL_LOG -> c.resources.getString(R.string.permission_call_log)
    is PermissionGroup.CAMERA -> c.resources.getString(R.string.permission_camera)
    is PermissionGroup.CONTACTS -> c.resources.getString(R.string.permission_contacts)
    is PermissionGroup.LOCATION -> c.resources.getString(R.string.permission_location)
    is PermissionGroup.MICROPHONE -> c.resources.getString(R.string.permission_microphone)
    is PermissionGroup.PHONE -> c.resources.getString(R.string.permission_phone)
    is PermissionGroup.SENSORS -> c.resources.getString(R.string.permission_sensors)
    is PermissionGroup.SMS -> c.resources.getString(R.string.permission_sms)
    is PermissionGroup.STORAGE -> c.resources.getString(R.string.permission_storage)
    is PermissionGroup.BLUETOOTH -> c.resources.getString(R.string.permission_bluetooth)
    is PermissionGroup.NOTIFICATION -> c.resources.getString(R.string.permission_notification)
    is PermissionGroup.NON -> ""
}

/**
 * 권한 그룹
 * Created by 이기종 on 2022-03-10
 */
sealed class PermissionGroup {
    object CALENDAR : PermissionGroup()
    object CALL_LOG : PermissionGroup()
    object CAMERA : PermissionGroup()
    object CONTACTS : PermissionGroup()
    object LOCATION : PermissionGroup()
    object MICROPHONE : PermissionGroup()
    object PHONE : PermissionGroup()
    object SENSORS : PermissionGroup()
    object SMS : PermissionGroup()
    object STORAGE : PermissionGroup()
    object BLUETOOTH : PermissionGroup()
    object NOTIFICATION : PermissionGroup()
    object NON : PermissionGroup()
}