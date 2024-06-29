package com.example.imageslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class Adapter(private val context: Context, private val imageUrls: List<sliderData>) :
    SliderViewAdapter<Adapter.SliderAdapterVH>() {
    class SliderAdapterVH(view: View) : SliderViewAdapter.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.idIVimage)
    }
    override fun getCount(): Int {
        return imageUrls.size
    }
    override fun onCreateViewHolder(parent: ViewGroup): Adapter.SliderAdapterVH {
        val slider = LayoutInflater.from(parent.context).inflate(R.layout.image_slider_item, parent, false)
        return Adapter.SliderAdapterVH(slider)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        val imageUrl = imageUrls[position]
        Picasso.get().load(imageUrl.imageUrl).into(viewHolder!!.imageView)
    }

}