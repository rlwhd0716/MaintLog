package com.github.util.code

data class GisData(
    var lon: Double = 0.0,
    var lat: Double = 0.0,
    var featureId: String = "",
    var text: String = "",
    var iconStyle: String = "", // base64
    var maxZoom: Int = 17
)