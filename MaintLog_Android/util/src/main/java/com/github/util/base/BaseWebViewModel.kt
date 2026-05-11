package com.github.util.base

import android.content.Context
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.github.util.client.ComWebChromeClient
import com.github.util.client.ComWebViewClient
import com.github.util.client.WebViewChromeInterface
import com.github.util.client.WebViewLoadingInterface
import com.github.util.code.GisData
import com.github.util.event.EventLiveData
import com.github.util.event.JavaScriptBridge
import com.github.util.extension.fromJson
import com.github.util.extension.logE
import kotlinx.parcelize.Parcelize
import org.json.JSONArray

open class BaseWebViewModel : BaseViewModel() {
    /**
     * XML에 WebView 선언 후 바인딩
     * @param webClient
     * @param chromeClient
     * @param javaScriptBridge
     */
    var webClient: ComWebViewClient? = null
    var chromeClient: ComWebChromeClient? = null
    var javaScriptBridge: JavaScriptBridge = JavaScriptBridge(this)
    var mapType = GisMapType.GOOGLE

    //        var pageURL = BuildConfig.GIS_PAGE
    var pageURL = "file:///android_asset/WebViewTest.html"

    /**
     * Activity 에서 Observing 해야함.
     * 아래에 App to Web 호출시 필요.
     */
    val runJavascript = EventLiveData<Pair<String?, (() -> Unit)?>>()

    /**
     * WebView 사용시 반드시 호출
     * 클라이언트 초기화
     */
    fun initClient(
        context: Context,
        webViewLoadingInterface: WebViewLoadingInterface? = null,
        webViewChromeInterface: WebViewChromeInterface? = null
    ) {
        webClient = ComWebViewClient(context, webViewLoadingInterface)
        chromeClient = ComWebChromeClient(context, webViewChromeInterface)
    }


    /** ---------------------------------------- Web To App ---------------------------------------- */
    open fun reverseGeocodingCallback(data: String) {
        logE(data)
    }

    open fun geocodeCallback(data: String) {
        logE(data)
    }

    open fun placeCallback(data: String) {
        logE(data)
    }

    open fun autocompleteCallback(data: JSONArray) {
//        logE(data)
    }

    open fun selectMapTypeCallback(data: String) {
        logE(data)
        val model = data.fromJson<GisMapTypeData>()

        mapType = model?.getType() ?: GisMapType.GOOGLE
    }

    open fun touchCallback(data: String) {
        logE(data)
    }

    open fun doubleTouchCallback(data: String) {
        logE(data)
    }

    open fun longTouchCallback(data: String) {
        logE(data)
    }

    open fun poiTouchCallback(data: String) {
        logE(data)
    }
    /** -------------------------------------------------------------------------------------------- */


    /** ---------------------------------------- App To Web ---------------------------------------- */
    fun reverseGeocoding(data: GisData, onNext: (() -> Unit)? = null) {
        runJavascript.postEvent(
            Pair(
                "vdsLocation.reverseGeocoding(${data.lon}, ${data.lat}, '${mapType.code}')",
                onNext
            )
        )
    }

    fun place(ref_id: String, onNext: (() -> Unit)? = null) {
        runJavascript.postEvent(
            Pair(
                "vdsLocation.place(\"$ref_id\", '${mapType.code}')",
                onNext
            )
        )
    }

    fun autocomplete(keyword: String, pageNum: Int, onNext: (() -> Unit)? = null) {
        runJavascript.postEvent(
            Pair(
                "vdsLocation.autocomplete(\"$keyword\", $pageNum, '${mapType.code}')",
                onNext
            )
        )
    }

    fun selectMapType(onNext: (() -> Unit)? = null) {
        runJavascript.postEvent(
            Pair(
                "vdsLocation.selectMapType()",
                onNext
            )
        )
    }

    fun addMarker(data: GisData, onNext: (() -> Unit)? = null) {
        val imageDataUrl =
            if (data.iconStyle.isNotBlank()) "data:image/png;base64,${data.iconStyle}" else ""

        runJavascript.postEvent(
            Pair(
                "vdsLocation.addMarker(${data.lon}, ${data.lat}, '${data.featureId}', '${data.text}', '${imageDataUrl}')",
                onNext
            )
        )
    }

    fun addMarkerPersist(data: GisData, onNext: (() -> Unit)? = null) {
        val imageDataUrl =
            if (data.iconStyle.isNotBlank()) "data:image/png;base64,${data.iconStyle}" else ""

        runJavascript.postEvent(
            Pair(
                "vdsLocation.addMarkerPersist(${data.lon}, ${data.lat}, '${data.featureId}', '${data.text}', '${imageDataUrl}')",
                onNext
            )
        )
    }

    fun moveToCurrentLocation(onNext: (() -> Unit)? = null) {
        runJavascript.postEvent(
            Pair(
                "vdsLocation.moveToCurrentLocation()",
                onNext
            )
        )
    }

    fun moveToLocation(data: GisData, onNext: (() -> Unit)? = null) {
        runJavascript.postEvent(
            Pair(
                "vdsLocation.moveToLocation(${data.lon}, ${data.lat}, ${data.maxZoom})",
                onNext
            )
        )
    }

    fun clearMarker(dataList: List<GisData>, onNext: (() -> Unit)? = null) {
        val idList = mutableListOf<String>()
        dataList.forEach {
            idList.add(it.featureId)
        }

        val jsonArray = JSONArray(idList).toString()
        runJavascript.postEvent(
            Pair(
                "vdsLocation.clearMarker($jsonArray)",
                onNext
            )
        )
    }

    /** extend-------------------------------------------------------------------------------------- */
    fun addMarkerAndMoveToLocation(data: GisData, onNext: (() -> Unit)? = null) {
        moveToLocation(data) {
            addMarkerAndReverseGeocoding(data, onNext)
        }
    }

    fun addMarkerAndReverseGeocoding(data: GisData, onNext: (() -> Unit)? = null) {
        addMarker(data) {
            reverseGeocoding(data, onNext)
        }
    }

    /** -------------------------------------------------------------------------------------------- */
}

@Parcelize
data class GisMapTypeData(
    @SerializedName("mapType") val mapType: String
) : Parcelable {
    fun getType(): GisMapType = GisMapType.from(mapType)
}

enum class GisMapType(val code: String) {
    BASE("bm"),
    GOOGLE("gm");

    companion object {
        fun from(code: String): GisMapType {
            return GisMapType.entries.find { it.code == code } ?: GOOGLE
        }
    }
}