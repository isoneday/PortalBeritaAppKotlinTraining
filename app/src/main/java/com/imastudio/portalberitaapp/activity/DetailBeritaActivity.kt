package com.imastudio.portalberitaapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imastudio.portalberitaapp.R
import com.imastudio.portalberitaapp.helper.MyConstant.Companion.DATABERITA
import com.imastudio.portalberitaapp.model.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_berita.*

class DetailBeritaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)
        var tangkap =intent.getParcelableExtra<ArticlesItem>(DATABERITA)
        txtnamaberita.text = tangkap.title
        Picasso.get().load(tangkap?.urlToImage).into(imgberita)
        txtpenulis.text =tangkap?.author
        txttanggal.text=tangkap?.publishedAt?.substring(0,10)
        txtdetail.text=tangkap?.content
    }


}
