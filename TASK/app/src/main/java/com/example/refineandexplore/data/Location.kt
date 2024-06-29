package com.example.refineandexplore.data

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class UserLocation(private val activity: Activity, private val context: Context) : LocationListener {

    private var locationManager: LocationManager? = null
    private var onLocationChangedListener: ((String) -> Unit)? = null

    // Function to start location updates
    suspend fun startLocationUpdates(
        scope: Context,
        locationChangeListener: (String) -> Unit
    ) {
        this.onLocationChangedListener = locationChangeListener

        // Check for runtime permissions
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
        } else {
            getLocation(scope)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(scope: Context) {
        try {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 5.toFloat(), this
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onLocationChanged(location: Location) {
        // Call getLocationAsString from within a coroutine
        CoroutineScope(Dispatchers.Main).launch {
            getLocationAsString(location)
        }
    }

    private suspend fun getLocationAsString(location: Location) {
        val address = withContext(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses: List<Address>? =
                    geocoder.getFromLocation(location.latitude, location.longitude, 1)
                addresses?.get(0)?.getAddressLine(0) ?: "Address not found"
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        address?.let {
            onLocationChangedListener?.invoke(it)
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onProviderDisabled(provider: String) {
    }
}
