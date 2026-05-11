package com.github.util.extension

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings.LOAD_NO_CACHE
import android.webkit.WebView
import android.webkit.WebViewClient
import com.github.util.event.JavaScriptBridge

@BindingAdapter(value = ["setKeyListener"])
fun WebView.setOnKeyListener(listener: ((WebView, Int, KeyEvent?) -> Boolean)? = null) {
    setOnKeyListener { _, keyCode, event ->
        listener?.invoke(this, keyCode, event) ?: run { false }
    }
}

@BindingAdapter(value = ["addJavascriptInterface"])
fun WebView.addJavascriptInterface(bridge: JavaScriptBridge) {
    addJavascriptInterface(bridge, "toApp")
}

@SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
@BindingAdapter(value = ["setWebViewClient"])
fun WebView.setWebViewClient(client: WebViewClient) {
    //clearCache(true)
    settings.apply {
        javaScriptEnabled = true
        setSupportMultipleWindows(true)
        javaScriptCanOpenWindowsAutomatically = true
        loadWithOverviewMode = true
        useWideViewPort = false
        domStorageEnabled = true
        allowFileAccess = true
        mediaPlaybackRequiresUserGesture = false
//        setSupportZoom(true)
        cacheMode = LOAD_NO_CACHE
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
//        setAppCacheEnabled(false)
//        builtInZoomControls = true
        textZoom = 100

        userAgentString += "/Danang_Android"
    }
    //isHorizontalScrollBarEnabled = false //가로 스크롤
    //isVerticalScrollBarEnabled = false   //세로 스크롤
    webViewClient = client
}

@BindingAdapter(value = ["setWebChromeClient"])
fun WebView.setWebChromeClient(client: WebChromeClient) {
    webChromeClient = client
}

fun WebView.setLocalStorage(key: String, value: String) {
    loadUrl("javascript:localStorage.setItem('$key','$value');")
}

@BindingAdapter(value = ["loadUrl"])
fun WebView.loadUrlWebView(url: String?) {
    logE("loadURL = $url")
    url?.run {
        loadUrl(this)
    }
}

fun WebView.loadJavaScript(function: String, value: Any) {
    logE("javascript:${function}(${value})")
    loadUrl("javascript:${function}(${value})")
}

fun WebView.runJavascript(javascript: String?, callback: ((String?) -> Unit)? = null) {
    if (!javascript.isNullOrBlank()) {
        this.evaluateJavascript("javascript:${javascript}") {
//            logE(javascript)
            callback?.invoke(javascript)
        }
    } else {
        logE("runJavascript Error : $javascript")
    }
}

//fun WebView.loadJavaScript(function: String, value: Boolean) {
//    logE("javascript:${function}(${value})")
//    loadUrl("javascript:${function}(${value})")
//}