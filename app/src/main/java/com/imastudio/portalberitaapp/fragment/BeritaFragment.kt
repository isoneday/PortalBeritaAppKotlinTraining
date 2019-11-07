package com.imastudio.portalberitaapp.fragment


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.imastudio.portalberitaapp.R
import com.imastudio.portalberitaapp.activity.DetailBeritaActivity
import com.imastudio.portalberitaapp.adapter.BeritaPolitikAdapter
import com.imastudio.portalberitaapp.helper.MyConstant
import com.imastudio.portalberitaapp.model.ArticlesItem
import com.imastudio.portalberitaapp.model.ResponseBeritaPolitik
import com.imastudio.portalberitaapp.network.InitRetrofit
import kotlinx.android.synthetic.main.fragment_berita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeritaFragment : Fragment(), BeritaPolitikAdapter.BeritaPolitikListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v =inflater.inflate(R.layout.fragment_berita, container, false)
        //get data dari server / api
        getBeritaPolitikFromNetwork()
        return v
    }

    private fun getBeritaPolitikFromNetwork() {
        var keyword = "politik"
        var sortby = "publishedAt"
        var key = "9ba80533c8274efe96cb442df3512e5b"
        //menampilkan loading saat get data ke server
        var loading = ProgressDialog.show(
            activity, " Process get data"
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
                            Toast.makeText(activity, "ada data", Toast.LENGTH_SHORT).show()
                            var dataBerita = response.body()?.articles1
                            var adapter = BeritaPolitikAdapter(activity, dataBerita)
                            recyclerView.adapter = adapter
                            recyclerView.layoutManager = LinearLayoutManager(activity)
                            //aksi klik adapter
                            adapter.setOnKlikLenterner(this@BeritaFragment)
                        } else {
                            Toast.makeText(activity, "tidak ada data", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        )
    }

    override fun onItemKlik(item: ArticlesItem) {
        var intentKirimData = Intent(activity, DetailBeritaActivity::class.java)
        intentKirimData.putExtra(MyConstant.DATABERITA,item)
        startActivity(intentKirimData)
    }

}
