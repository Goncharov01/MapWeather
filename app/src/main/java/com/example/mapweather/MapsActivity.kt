package com.example.mapweather

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var latLng: LatLng
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fetchLocation()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        latLng = LatLng(53.6803665, 23.8290348)
        mMap.addMarker(MarkerOptions().position(latLng).title("Marker in Grodno"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.setMinZoomPreference(12.0F)
        mMap.setMaxZoomPreference(15.0F)

        mMap.setOnMyLocationButtonClickListener(object : GoogleMap.OnMyLocationButtonClickListener {
            override fun onMyLocationButtonClick(): Boolean {

                weather.isEnabled = true

                return false
            }
        })

        enableMyLocation()

    }

    fun getCurrentLocation(view: View) {

        var sity = editText.text.toString()

        var geocoder: Geocoder = Geocoder(this)

        if (sity != null && sity != "" && sity.isNotEmpty()) {

            weather.isEnabled = true

            var addressList: List<Address> =
                geocoder.getFromLocationName(sity, 1)

            var address: Address = addressList.get(0)

            latLng = LatLng(address.latitude, address.longitude)
            println(latLng)

            mMap.addMarker(MarkerOptions().position(latLng).title(editText.text.toString()))
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            mMap.setMinZoomPreference(12.0F)
            mMap.setMaxZoomPreference(20.0F)

        } else {

            fetchLocation()

        }

    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), permissionCode
            )
        }

        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            latLng = LatLng(location.getLatitude(), location.getLongitude())
            println("??????????????????${latLng}")
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    private fun enableMyLocation() {

        if (isPermissionGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                permissionCode
            )
        }
    }

    fun goIntent(view: View) {

        val intent = Intent(this, WeatherActivity::class.java)
        var b: Bundle = Bundle()

        b.putDouble("lat", latLng.latitude)
        b.putDouble("lon", latLng.longitude)
        intent.putExtras(b)

        startActivity(intent)

    }

}