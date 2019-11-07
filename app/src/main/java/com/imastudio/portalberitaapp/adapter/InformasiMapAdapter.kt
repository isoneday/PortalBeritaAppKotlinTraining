package com.imastudio.portalberitaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.imastudio.portalberitaapp.R
import com.imastudio.portalberitaapp.helper.MyConstant.Companion.BASE_INFORMASIMAP_URL
import com.imastudio.portalberitaapp.model.PhotosItem
import com.imastudio.portalberitaapp.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tampilaninformasimap.view.*


class InformasiMapAdapter(var activity: FragmentActivity?,var dataInformasiMap: List<ResultsItem?>?)
    : RecyclerView.Adapter<InformasiMapAdapter.ViewHolder>(){



    var listener: InformasiMapListener?=null

    interface InformasiMapListener{
        fun onItemKlik(item : ResultsItem,image:String)
    }
    //nama aksi klik dari recyclerview yang akan di implmentasikan
    fun setOnKlikLenterner(aksiKlik : InformasiMapAdapter.InformasiMapListener){
        listener = aksiKlik
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformasiMapAdapter.ViewHolder =
    ViewHolder(LayoutInflater.from(activity).inflate(R.layout.tampilaninformasimap,parent,false))


    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        fun bindItem(item: ResultsItem, listener : InformasiMapAdapter.InformasiMapListener ){
            var dataphoto : List<PhotosItem?>?
            dataphoto = item.photos
            var itemPhoto = dataphoto?.size?.let { arrayOfNulls<String>(it) }
            if (dataphoto != null) {
                for (i in dataphoto.indices) {

                    itemPhoto?.set(i, dataphoto[i]?.photoReference)

                }
//                Log.d("dataphoto",itemPhoto.toString())
                var key = "AIzaSyA961qUUYeU2tnBuk4gS1fpiXVjnCFnbcQ"
                var gambarMasjid = itemPhoto?.contentToString().toString().substring(
                    1, itemPhoto?.contentToString()?.length!! - 1)
                var image = BASE_INFORMASIMAP_URL + "photo?maxwidth=600&photoreference=$gambarMasjid&key=$key"

                itemView.txtjudulinformasi.text = item.name.toString()
                Picasso.get().load(image).into(itemView.imginformasi)
                itemView.setOnClickListener{
                    listener.onItemKlik(item,image)
                }
            }
        }
    }

    override fun getItemCount(): Int  = dataInformasiMap?.size!!

    override fun onBindViewHolder(holder: InformasiMapAdapter.ViewHolder, position: Int) =
            holder.bindItem(dataInformasiMap?.get(position)!!, this.listener!! )

}
