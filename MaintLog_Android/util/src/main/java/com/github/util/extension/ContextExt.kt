package com.github.util.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val clipDataTitle = "address"
fun Context.copyData(addr: String) {
    val clipData =
        ClipData.newPlainText(clipDataTitle, addr)
    (getSystemService(AppCompatActivity.CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(
        clipData
    )
}


fun Context.showDialTel(tel:String) {
    Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$tel")
        startActivity(this)
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun AppCompatActivity.showKeyboard() {
    GlobalScope.launch {
        delay(100)
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(window.decorView, 0)
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun AppCompatActivity.hideKeyboard() {
    GlobalScope.launch {
        delay(100)
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }
}

@SuppressLint("HardwareIds")
fun Context.getAndroidId(): String {
    val androidId = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
    logE("androidId : $androidId")
    return androidId
}