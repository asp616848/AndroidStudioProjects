import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Set map settings (optional)
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        // Add a marker
        val markerPosition = LatLng(latitude, longitude)
        val markerOptions = MarkerOptions()
            .position(markerPosition)
            .title("Custom Marker Title")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)) // Custom marker icon

        // Add marker to the map
        googleMap.addMarker(markerOptions)

        // Move camera to marker position
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 15f))
    }

    companion object {
        private const val latitude = 37.7749 // Replace with your desired latitude
        private const val longitude = -122.4194 // Replace with your desired longitude
    }
}
