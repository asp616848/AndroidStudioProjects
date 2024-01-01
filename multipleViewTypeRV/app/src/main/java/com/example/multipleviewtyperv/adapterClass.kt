package com.example.multipleviewtyperv

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class adapterClass: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var itemsList: MutableList<itemClass>
    class ViewHolder_one(itemView :View) :RecyclerView.ViewHolder(itemView){
        val text: TextView = itemView.findViewById(R.id.text)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearlayout)
    }
    class ViewHolder_two(itemView :View) :RecyclerView.ViewHolder(itemView){
        val text_one: TextView = itemView.findViewById(R.id.text_one)
        val text_two: TextView = itemView.findViewById(R.id.text_two)
        val image: ImageView = itemView.findViewById(R.id.image)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearlayout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            0 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_one, parent, false)
                return ViewHolder_one(view)
            }
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_two, parent, false)
                return ViewHolder_two(view)
            }
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = itemsList[position]
        when(currentItem.viewType){
            0 -> {
                val viewHolder_one = holder as ViewHolder_one
                viewHolder_one.text.text = currentItem.text_one
                viewHolder_one.linearLayout.setOnClickListener { view ->
                    Toast.makeText(view.context,"first type clicked",Toast.LENGTH_SHORT).show()
                }
            }
            1 -> {
                val viewHolder_two = holder as ViewHolder_two
                viewHolder_two.text_one.text = currentItem.text_one
                viewHolder_two.text_two.text = currentItem.text_two
                viewHolder_two.image.setImageResource(currentItem.image)
                viewHolder_two.linearLayout.setOnClickListener { view ->
                    Toast.makeText(view.context,"second type clicked",Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when(itemsList[position].viewType){
            0 -> {
                0
            }
            1 -> {
                1
            }
            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
    fun itemToList(items :itemClass){
        itemsList.add(items)
    }
}