package com.imastudio.portalberitaapp.fragment


import android.app.ProgressDialog
import android.content.Context
import android.content.IntentSender
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.imastudio.portalberitaapp.R
import com.imastudio.portalberitaapp.adapter.InformasiMapAdapter
import com.imastudio.portalberitaapp.helper.GPSTracker
import com.imastudio.portalberitaapp.model.ResponseInformasiMap
import com.imastudio.portalberitaapp.model.ResultsItem
import com.imastudio.portalberitaapp.network.InitRetrofit
import kotlinx.android.synthetic.main.fragment_informasi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*


class InformasiFragment : Fragment(), InformasiMapAdapter.InformasiMapListener {
    override fun onItemKlik(item: ResultsItem, image: String) {

    }

    var lat:Double ? =null
    var lng:Double ? =null
    var detailAlamat :String ? =null
    var lokasi : LatLng? =null
    var googleApiClient :GoogleApiClient? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val v = inflater.inflate(R.layout.fragment_informasi, container, false)
            cekGPSdevice()
         getMyLocation()
        return v
    }

    private fun cekGPSdevice() {
        val manager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(activity, "Gps already enabled", Toast.LENGTH_SHORT).show()
            //     finish();
        }
        // Todo Location Already on  ... end
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(activity, "Gps not enabled", Toast.LENGTH_SHORT).show()
            //menampilkan popup untuk mengaktifkan gps
            enableLoc()
        }
    }
//untuk menampilkan popup agar user mengaktifkan gps
    private fun enableLoc() {
        if (googleApiClient == null) {
            googleApiClient = activity?.let {
                GoogleApiClient.Builder(it)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                        override fun onConnected(bundle: Bundle?) {

                        }

                        override fun onConnectionSuspended(i: Int) {
                            googleApiClient?.connect()
                        }
                    })
                    .addOnConnectionFailedListener { connectionResult ->
                        Log.d(
                            "Location error",
                            "Location error " + connectionResult.errorCode
                        )
                    }
                    .build()
            }
            googleApiClient?.connect()

            val locationRequest = LocationRequest.create()
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            locationRequest.setInterval(30 * 1000)
            locationRequest.setFastestInterval(5 * 1000)
            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

            builder.setAlwaysShow(true)

            val result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
            result.setResultCallback(object : ResultCallback<LocationSettingsResult> {
                override fun onResult(result: LocationSettingsResult) {
                    val status = result.getStatus()
                    when (status.getStatusCode()) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(activity, 1)

                            activity?.finish()
                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                        }

                    }
                }
            })
        }
    }

    private fun getMyLocation() {
        var gps = GPSTracker(activity)
        lat =gps.latitude
        lng =gps.longitude
        lokasi = LatLng(lat!!, lng!!)
        detailAlamat = getDetailAlamat(lat,lng)
        getInformasiMap(lat,lng)
    }

    private fun getInformasiMap(lat: Double?, lng: Double?) {
        var loading = ProgressDialog.show(
            activity, " Process get data"
            , "loading . . ."
        )

        var key= "AIzaSyA961qUUYeU2tnBuk4gS1fpiXVjnCFnbcQ"
        var radius = "500"
        var types = "mosque"
        InitRetrofit.getInstanceInformasiMap().getInformasi(
            "$lat,$lng",
            radius,
            types,
            key
        ).enqueue(
            object :Callback<ResponseInformasiMap>{
                override fun onFailure(call: Call<ResponseInformasiMap>, t: Throwable) {
                    Log.d("cekresponse", "gagal:" + t.localizedMessage)
                    loading.dismiss()

                }

                override fun onResponse(call: Call<ResponseInformasiMap>, response: Response<ResponseInformasiMap>) {
                    loading.dismiss()
               if (response.isSuccessful){
                   var status = response.body()?.status
                   if (status.equals("OK")){
                    var dataInformasiMap = response.body()?.results
                       Toast.makeText(activity,"tampil data",Toast.LENGTH_SHORT).show()
                       var adapter = InformasiMapAdapter(activity,dataInformasiMap)
                       recyclerinformasimap.adapter= adapter
                       recyclerinformasimap.layoutManager = LinearLayoutManager(activity)
                        adapter.setOnKlikLenterner(this@InformasiFragment)
                   }
               }
                }

            }
        )
    }

    private fun getDetailAlamat(lat: Double?, lng: Double?): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val list = geocoder.getFromLocation(lat!!, lng!!, 1)
            if (list != null && list.size > 0) {
                detailAlamat = list[0].getAddressLine(0) + "" + list[0].countryName
                //fetch data from addresses
            } else {
                Toast.makeText(activity,"tidak dapat lokasi",Toast.LENGTH_SHORT).show()
                //display Toast message
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }


        return detailAlamat
    }


}
