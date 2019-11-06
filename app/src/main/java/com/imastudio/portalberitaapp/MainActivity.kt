package com.imastudio.portalberitaapp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.imastudio.portalberitaapp.adapter.BeritaPolitikAdapter
import com.imastudio.portalberitaapp.model.ResponseBeritaPolitik
import com.imastudio.portalberitaapp.network.InitRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get data dari server / api
        getBeritaPolitikFromNetwork()
    }

    private fun getBeritaPolitikFromNetwork() {
        var keyword = "politik"
        var sortby = "publishedAt"
        var key = "9ba80533c8274efe96cb442df3512e5b"
        //menampilkan loading saat get data ke server
        var loading = ProgressDialog.show(
            this, " Process get data"
            , "loading . . ."
        )
        InitRetrofit.getInstance().getBeritaPolitik(keyword, sortby, key).enqueue(
            object : Callback<ResponseBeritaPolitik> {
                override fun onFailure(call: Call<ResponseBeritaPolitik>, t: Throwable) {
                    Log.d("cekresponse", "gagal:" + t.localizedMessage)
                    loading.dismiss()
                }

                override fun onResponse(
                    call: Call<ResponseBeritaPolitik>, response
                    : Response<ResponseBeritaPolitik>
                ) {
                    Log.d("cekresponse", "berhasil:" + response.body()?.articles1)
                    loading.dismiss()
                    if (response.isSuccessful) {
                        var status = response.body()?.status1
                        if (status.equals("ok")) {
                            Toast.makeText(this@MainActivity, "ada data", Toast.LENGTH_SHORT).show()
                            var dataBerita = response.body()?.articles1
                            var adapter = BeritaPolitikAdapter(this@MainActivity, dataBerita)
                            recyclerView.adapter = adapter
                            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        } else {
                            Toast.makeText(this@MainActivity, "tidak ada data", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        )
    }
}
