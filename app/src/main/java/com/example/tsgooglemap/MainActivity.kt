package com.example.tsgooglemap

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {
    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as SupportMapFragment

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
        }

        mapFragment.getMapAsync{
            val klLocation = LatLng(3.139, 101.6869)
            googleMap = it
            googleMap.addMarker(MarkerOptions().position(klLocation).title("KL"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(klLocation,10f))
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode){
            requestCode ->{
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    googleMap.isMyLocationEnabled = false
                }else{
                    googleMap.isMyLocationEnabled = true
                }
            }
        }
    }

}