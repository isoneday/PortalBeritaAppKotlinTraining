package com.imastudio.portalberitaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imastudio.portalberitaapp.MainActivity
import com.imastudio.portalberitaapp.R
import com.imastudio.portalberitaapp.model.ArticlesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tampilanberita.view.*

class BeritaPolitikAdapter(
   var mainActivity: MainActivity,
   var dataBerita: List<ArticlesItem?>?
) : RecyclerView.Adapter<BeritaPolitikAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    = ViewHolder(
        LayoutInflater.from(mainActivity).inflate(R.layout.tampilanberita, parent, false))

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bindItem(item: ArticlesItem){
            with(itemView){
                txtjudulberita.text=item.title
                 Picasso.get().load(item.urlToImage).placeholder(R.drawable.noimage).error(
                     R.drawable.noimage).into(imgberita)
            }
        }
    }

    override fun getItemCount(): Int = dataBerita!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bindItem(dataBerita?.get(position)!!)


}