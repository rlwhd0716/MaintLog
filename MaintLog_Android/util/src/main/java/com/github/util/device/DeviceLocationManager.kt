package com.github.util.device

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

object DeviceLocationManager {
    private var locationManager: LocationManager? = null
    private var deviceCollectLocationListener: DeviceCollectLocationListener? = null

    var location: Location? = null
        private set

    val latitude: Double?
        get() = location?.latitude

    val longitude: Double?
        get() = location?.longitude

    fun init(context: Context) {
        if (locationManager == null) {
            locationManager =
                context.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
    }

    @Suppress("MissingPermission")
    fun startCollectionLocation(listener: CurrentLocationListener) {
        val minTime: Long = 1000
        val minDistance = 1f

        if (locationManager == null) {
            throw IllegalStateException("DeviceLocationManager must be initialized with init(context) before use.")
        }

        if (deviceCollectLocationListener == null) {
            deviceCollectLocationListener = DeviceCollectLocationListener(listener)
        }

        locationManager?.let {
            it.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                deviceCollectLocationListener!!
            )
            it.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime,
                minDistance,
                deviceCollectLocationListener!!
            )
        }
    }

    fun stopLocationUpdates() {
        locationManager?.let { lm ->
            deviceCollectLocationListener?.let {
                lm.removeUpdates(it)
            }
        }
        deviceCollectLocationListener = null
    }

    class DeviceCollectLocationListener(
        private val currentLocationListener: CurrentLocationListener
    ) : LocationListener {

        override fun onLocationChanged(_location: Location) {
            location = _location
            currentLocationListener.onLocationChanged(_location)
//            PrefObj.LOG_SAVE += getLogMsg("GPS longitude(x) = ${location.longitude}, latitude(y) = ${location.latitude}\n")
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String) {
        }

        override fun onProviderDisabled(provider: String) {
        }
    }

    interface CurrentLocationListener {
        fun onLocationChanged(location: Location)
    }
}