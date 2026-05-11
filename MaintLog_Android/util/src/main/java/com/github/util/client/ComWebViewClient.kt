package com.github.util.client

import android.Manifest
import android.app.DownloadManager
import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.webkit.CookieManager
import android.webkit.DownloadListener
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.github.util.R
import com.github.util.extension.logE
import com.github.util.extension.setLocalStorage
import com.github.util.provider.ResourceProvider.getString
import com.github.util.ui.showAlertDialog
import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URISyntaxException
import java.net.URLDecoder


class ComWebViewClient(
    private val context: Context,
    private val webViewLoadingInterface: WebViewLoadingInterface? = null,
    private val localStorageData: List<Pair<String, String>> = listOf()
) : WebViewClient(), DownloadListener {
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        logE("request - ${request?.url} / error - ${error?.errorCode}")
        webViewLoadingInterface?.onReceivedError()
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        logE("onPageStarted : $url")
        super.onPageStarted(view, url, favicon)
        view?.setNetworkAvailable(true)
        view?.evaluateJavascript("window.webkit = { messageHandlers: { toApp: window.toApp} }") {}
        webViewLoadingInterface?.onPageStarted(url)
        localStorageData.forEach {
            view?.setLocalStorage(it.first, it.second)
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        logE("onPageFinished : $url")
        super.onPageFinished(view, url)
        webViewLoadingInterface?.onPageFinished(url)
    }

//    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
//        logE("ssl Error - $error")
//        handler?.proceed()
//    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        logE("requestUrl - ${request?.url}")
        if (!request?.url.toString().startsWith("http://") && !request?.url.toString()
                .startsWith("https://") && !request?.url.toString().startsWith("javascript:")
        ) {
            var intent: Intent? = null
            return try {
                intent = Intent.parseUri(request?.url.toString(), Intent.URI_INTENT_SCHEME)
                val uri: Uri? = intent.dataString?.toUri()
                startActivity(context, Intent(Intent.ACTION_VIEW, uri), null)
                true
            } catch (ex: URISyntaxException) {
                false
            } catch (e: ActivityNotFoundException) {
                if (intent == null) return false
                val packageName = intent.getPackage()
                if (packageName != null) {
                    startActivity(
                        context,
                        Intent(
                            Intent.ACTION_VIEW,
                            "market://details?id=$packageName".toUri()
                        ),
                        null
                    )
                    return true
                }
                false
            }
        }
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onDownloadStart(
        url: String?,
        userAgent: String?,
        contentDisposition: String?,
        mimetype: String?,
        contentLength: Long
    ) {
        logE("$url")
        if (isFileDownloadPermissionGranted()) {
            val request = DownloadManager.Request(url?.toUri())
            val cookies = CookieManager.getInstance().getCookie(url)
            var disposition = ""
            try {
                disposition = URLDecoder.decode(contentDisposition, "UTF-8")
            } catch (e: UnsupportedEncodingException) {
                logE("파일다운로드 - 인코딩 에러")
            }
            request.setMimeType(mimetype)
            request.addRequestHeader("cookie", cookies ?: "")
            request.addRequestHeader("User-Agent", userAgent ?: "")
            request.setDescription("Downloading file...")
            request.setAllowedOverMetered(true)
            request.setAllowedOverRoaming(true)
            request.setRequiresCharging(false)

//            val fileName = URLUtil.guessFileName(url, disposition, mimetype)
            var fileName = disposition.replace("attachment; filename=", "")
            if (fileName.isNotEmpty()) {
                val idxFileName = fileName.indexOf("filename =")
                if (idxFileName > -1) {
                    fileName = fileName.substring(idxFileName + 9).trim { it <= ' ' }
                }

                if (fileName.endsWith(";")) {
                    fileName = fileName.substring(0, fileName.length - 1)
                }

                if (fileName.startsWith("\"") && fileName.startsWith("\"")) {
                    fileName = fileName.substring(1, fileName.length - 1)
                }
            }
            request.setTitle(fileName)
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                File.separator + fileName
            )

            val dm = context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
            Toast.makeText(context.applicationContext, "파일을 다운로드합니다.", Toast.LENGTH_LONG).show()
        } else {
            context.showAlertDialog(
                title = getString(R.string.permission_setting_title),
                message = getString(R.string.permission_setting_body),
                ok = getString(R.string.permission_setting),
                okAction = {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val url = Uri.fromParts("package", context.packageName, null)
                    intent.data = url
                    context.startActivity(intent)
                }
            )
        }
    }

    private fun isFileDownloadPermissionGranted(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            true
        } else {
            context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }
}

interface WebViewLoadingInterface {
    fun onReceivedError()
    fun onPageStarted(url: String?)
    fun onPageFinished(url: String?)
}