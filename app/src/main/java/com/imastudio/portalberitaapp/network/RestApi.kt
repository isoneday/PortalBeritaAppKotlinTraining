package com.imastudio.portalberitaapp.network

import com.imastudio.portalberitaapp.model.ResponseBeritaPolitik
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

}