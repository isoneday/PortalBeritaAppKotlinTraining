package com.imastudio.portalberitaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.imastudio.portalberitaapp.R
import com.imastudio.portalberitaapp.model.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tampilanberita.view.*

class BeritaPolitikAdapter(
   var mainActivity: FragmentActivity?,
   var dataBerita: List<ArticlesItem?>?
) : RecyclerView.Adapter<BeritaPolitikAdapter.ViewHolder>(){


    var listener: BeritaPolitikListener?=null

   interface BeritaPolitikListener{
       fun onItemKlik(item : ArticlesItem)
   }
//nama aksi klik dari recyclerview yang akan di implmentasikan
   fun setOnKlikLenterner(aksiKlik : BeritaPolitikListener){
       listener = aksiKlik
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    = ViewHolder(
        LayoutInflater.from(mainActivity).inflate(R.layout.tampilanberita, parent, false))

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bindItem(item: ArticlesItem,listener :BeritaPolitikListener){
            with(itemView){
            //mewakilkan aksi klik di adapter untuk aksi klik di activity
                setOnClickListener { listener.onItemKlik(item) }

                txtjudulberita.text=item.title
                 Picasso.get().load(item.urlToImage).placeholder(R.drawable.noimage).error(
                     R.drawable.noimage).into(imgberita)
            }
        }
    }

    override fun getItemCount(): Int = dataBerita!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bindItem(dataBerita?.get(position)!!, this!!.listener!!)


}