package com.github.util.event

import android.webkit.JavascriptInterface
import com.github.util.base.BaseWebViewModel
import org.json.JSONArray
import org.json.JSONObject

class JavaScriptBridge(val vm: BaseWebViewModel) {
    @JavascriptInterface
    fun postMessage(jsonString: String) {
//        logE(jsonString)
        val json = JSONObject(jsonString)

//        logE(json.getString("func"))
        when(json.getString("func")) {
            "reverseGeocodingCallback" -> {
                vm.reverseGeocodingCallback(checkGoogleDataString(json))
            }
            "geocodeCallback" -> {
                vm.geocodeCallback(checkGoogleDataString(json))
            }
            "placeCallback" -> {
                vm.placeCallback(checkGoogleDataString(json))
            }
            "autocompleteCallback" -> {
                vm.autocompleteCallback(checkGoogleDataJSONArray(json))
            }
            "selectMapTypeCallback" -> {
                vm.selectMapTypeCallback(json.getString("data"))
            }
            "touchCallback" -> {
                vm.touchCallback(json.getString("data"))
            }
            "doubleTouchCallback" -> {
                vm.doubleTouchCallback(json.getString("data"))
            }
            "longTouchCallback" -> {
                vm.longTouchCallback(json.getString("data"))
            }
            "poiTouchCallback" -> {
                vm.poiTouchCallback(json.getString("data"))
            }
        }
    }

    fun checkGoogleDataString(json: JSONObject): String =
        if (json.has("googleData")) json.getString("googleData") else json.getString("data")


    fun checkGoogleDataJSONArray(json: JSONObject): JSONArray =
        if (json.has("googleData")) json.getJSONArray("googleData") else json.getJSONArray("data")

}