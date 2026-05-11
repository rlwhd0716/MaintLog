package com.github.util.client

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebChromeClient.FileChooserParams
import android.webkit.WebView
import android.webkit.WebView.WebViewTransport
import com.github.util.R
import com.github.util.extension.logE


open class ComWebChromeClient(
    private val context: Context,
    private val webViewChromeInterface: WebViewChromeInterface? = null
) : WebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
    }

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    override fun onCreateWindow(
        view: WebView,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message
    ): Boolean {
        logE("window.open")
        val openWebView = WebView(view.context).apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.allowFileAccess = true
            settings.setSupportMultipleWindows(true)
            settings.loadWithOverviewMode = true
            settings.domStorageEnabled = true
        }

//         다이얼로그 생성되는 부분
        val dialog = Dialog(view.context, R.style.Theme_WebviewDialog).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(openWebView)
            setOnKeyListener { d, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (openWebView.canGoBack()) {
                        openWebView.goBack()
                    } else {
                        logE("Window.open 종료")
                        openWebView.visibility = View.GONE
                        openWebView.destroy()
                        d.dismiss()
                    }
                    return@setOnKeyListener true
                } else {
                    return@setOnKeyListener false
                }
            }
        }
        dialog.show()

        openWebView.webViewClient = ComWebViewClient(context)
        openWebView.webChromeClient = object : WebChromeClient() {
            override fun onCloseWindow(window: WebView) {
                logE("onCloseWindow 1")
                window.visibility = View.GONE
                window.destroy()
                dialog.dismiss()
                super.onCloseWindow(window)
            }
        }

        (resultMsg.obj as WebViewTransport).webView = openWebView
        resultMsg.sendToTarget()
        return true
    }

    override fun onCloseWindow(window: WebView) {
        logE("onCloseWindow 2")
        window.visibility = View.GONE
        window.destroy()
        super.onCloseWindow(window)
    }

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        webViewChromeInterface?.onShowFileChooser(webView, filePathCallback, fileChooserParams)
        return true
    }

    override fun onPermissionRequest(request: PermissionRequest?) {
        var resources = ""
        request?.resources?.forEach { resources += it }
        logE("onPermissionRequest - $resources")
        try {
            request?.grant(request.resources)
        } catch (e: Exception) {
            logE("permissionRequest: $e")
        }
    }


    override fun getDefaultVideoPoster(): Bitmap? {
        return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
    }
}

interface WebViewChromeInterface {
    fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    )
}