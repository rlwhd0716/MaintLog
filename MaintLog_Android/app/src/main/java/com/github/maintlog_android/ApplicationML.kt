package com.github.maintlog_android

import android.app.Application
import com.github.util.extension.logE
import com.github.util.pref.Pref
import com.github.util.provider.ResourceProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationML: Application() {

    override fun onCreate() {
        super.onCreate()

        logE("application oncreate")
        Pref.initializeApp(this)
        ResourceProvider.initializeApp(this)
    }
}