package com.imastudio.portalberitaapp.network

import com.imastudio.portalberitaapp.model.ResponseBeritaPolitik
import com.imastudio.portalberitaapp.model.ResponseInformasiMap
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("everything")
    fun getBeritaPolitik(
     @Query("q") keyword :String?,
     @Query("sortBy") sortby :String?,
     @Query("apiKey") api :String?
    ) : Call<ResponseBeritaPolitik>


    @GET("nearbysearch/json")
    fun getInformasi(
     @Query("location") location :String?,
     @Query("radius") radius :String?,
     @Query("types") types :String?,
     @Query("key") key :String?
    ) : Call<ResponseInformasiMap>

}