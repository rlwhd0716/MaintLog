package com.github.util.permission

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Context.actionApplicationDetailsSettings(block: Intent.() -> Unit = {}) = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
    Uri.parse("package:$packageName")).apply(block)