package com.example.mapsproj

import android.annotation.SuppressLint
import android.content.Contextid
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mapsproj.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import com.google.maps.android.SphericalUtil

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var sydney:LatLng = LatLng(-34.0, 151.0)
    var TamWorth: LatLng = LatLng(-31.083332, 150.916672)
    var NewCastle: LatLng = LatLng(-32.916668, 151.750000)
    var Brisbane: LatLng = LatLng(-27.470125, 153.021072)

    var locations: ArrayList<LatLng> = ArrayList()
    var hybrid:Button?=null
    var satellite:Button?=null
    var terrain:Button?=null

    var searchView : SearchView?=null

    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //firebase
        db = FirebaseFirestore.getInstance()



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        locations.add(sydney)
        locations.add(TamWorth)
        locations.add(NewCastle)
        locations.add(Brisbane)

        //settings buttons for multiple map types

        hybrid = findViewById(R.id.idBtnHybridMap)
        terrain = findViewById(R.id.idBtnTerrainMap)
        satellite= findViewById(R.id.idBtnSatelliteMap)
        hybrid?.setOnClickListener {
            mMap.mapType=GoogleMap.MAP_TYPE_HYBRID
        }
        terrain?.setOnClickListener {
            mMap.mapType=GoogleMap.MAP_TYPE_TERRAIN
        }
        satellite?.setOnClickListener {
            mMap.mapType=GoogleMap.MAP_TYPE_SATELLITE
        }


        //search view

        searchView = findViewById(R.id.idSearchView)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var location: String = query ?: ""
                var addressList: List<android.location.Address>? = null
                if (location != null || location != "") {
                    var geocoder: android.location.Geocoder = android.location.Geocoder(this@MapsActivity)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    var address: android.location.Address = addressList!![0]
                    var latLng: LatLng = LatLng(address.latitude, address.longitude)
                    mMap.addMarker(MarkerOptions().position(latLng).title(location))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera

        for (loc in locations){
            mMap.addMarker(MarkerOptions().position(loc).title("Marker title ${loc.toString()}").icon(BitmapFromVector(this,R.drawable.baseline_add_location_24)))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(20.0f))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
        }
        //polyline
        mMap.addPolyline(com.google.android.gms.maps.model.PolylineOptions().add(sydney,TamWorth,NewCastle,Brisbane).width(10f).color(R.color.purple_200))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        //click listener
        mMap.setOnMarkerClickListener (object : GoogleMap.OnMarkerClickListener{
            override fun onMarkerClick(p0: Marker): Boolean {
                Toast.makeText(this@MapsActivity,"${p0.title}",Toast.LENGTH_SHORT).show()
                return false
            }
        })


        //distance
        var distance = SphericalUtil.computeDistanceBetween(sydney,TamWorth).toFloat()
        Toast.makeText(this@MapsActivity,"Distance between Sydney and TamWorth is $distance km",Toast.LENGTH_SHORT).show()



        val documentReference : DocumentReference = db.collection("MapsData").document("7QWDor9vozLaHdFYV9kh")
        documentReference.addSnapshotListener(object : EventListener<DocumentSnapshot> {
            override fun onEvent(value: DocumentSnapshot?, error: FirebaseFirestoreException?) {
                if (value != null && value.exists()) {

                    val geopoint = value.getGeoPoint("geoPoint")
                    var geoLocation = LatLng(geopoint!!.latitude, geopoint!!.longitude)
                    //above is 2 different methods to get the latlng from firebase
                    mMap.addMarker(
                        MarkerOptions().position(geoLocation)
                            .title("~India~").icon(
                            BitmapFromVector(
                                this@MapsActivity,
                                R.drawable.baseline_add_location_24
                            )
                        )
                    )
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(21.0f))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(geoLocation))

                } else {
                    Toast.makeText(
                        this@MapsActivity,
                        "Error while loading data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }
    private fun BitmapFromVector(context: Context, vectorResId:Int): BitmapDescriptor? {
        //drawable generator
        var vectorDrawable: Drawable
        vectorDrawable= ContextCompat.getDrawable(context,vectorResId)!!
        vectorDrawable.setBounds(0,0,vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight)
        //bitmap genarator
        var bitmap: Bitmap
        bitmap= Bitmap.createBitmap(vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        //canvas genaret
        var canvas: Canvas
        //pass bitmap in canvas constructor
        canvas= Canvas(bitmap)
        //pass canvas in drawable
        vectorDrawable.draw(canvas)
        //return BitmapDescriptorFactory
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}