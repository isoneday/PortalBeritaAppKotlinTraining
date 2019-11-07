package com.imastudio.portalberitaapp.network

import com.google.gson.GsonBuilder
import com.imastudio.portalberitaapp.helper.MyConstant.Companion.BASE_INFORMASIMAP_URL
import com.imastudio.portalberitaapp.helper.MyConstant.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//inisialiasasi retrofit
object InitRetrofit {

    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .retryOnConnectionFailure(true)
        .connectTimeout(15,TimeUnit.SECONDS)
        .build()

    val gson = GsonBuilder().setLenient().create()
    //retrofit untuk berita
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    //get instance retrofit
    fun getInstance() : RestApi = retrofit.create(RestApi::class.java)

    //retrofit untuk informasi map
    val retrofitmap = Retrofit.Builder()
        .baseUrl(BASE_INFORMASIMAP_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    //get instance retrofit
    fun getInstanceInformasiMap() : RestApi = retrofitmap.create(RestApi::class.java)

}