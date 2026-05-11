package com.github.util.device

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.util.BuildConfig


private var phoneNumber = ""

fun getMyPhoneNumber(): String {
    Log.e("PhoneNumber Error", "휴대전화번호를 가져올 수 없습니다.")
    if(BuildConfig.DEBUG) return "01073381700"
    return phoneNumber
}


@SuppressLint("HardwareIds")
fun AppCompatActivity.setMyPhoneNumber() {
    if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
        val telephony = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
            if (telephony.line1Number != null) {
                phoneNumber = telephony.line1Number
            } else {
                if (telephony.simSerialNumber != null) {
                    phoneNumber = telephony.simSerialNumber
                }
            }
        } catch (e: Exception) {
            Log.e("PhoneNumber Error", "휴대전화번호를 저장할 수 없습니다.")
        }

        if (phoneNumber.startsWith("+82")) {
            phoneNumber = phoneNumber.replace("+82", "0")
        }
    }
}
