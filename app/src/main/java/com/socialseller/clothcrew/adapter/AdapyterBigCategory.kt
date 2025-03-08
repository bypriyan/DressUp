package com.socialseller.clothcrew.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.R

class AdapyterBigCategory (private val context: Context,
                           private val itemList: List<Item>
) : RecyclerView.Adapter<AdapyterBigCategory.ItemViewHolder>() {

    // ViewHolder class
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.catImgBig)
        val titleTextView: TextView = itemView.findViewById(R.id.cattitleBigBig)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_big_categories, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]

        // Set the image and title
        holder.imageView.setImageResource(item.imageResId)
        holder.titleTextView.text = item.title
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}