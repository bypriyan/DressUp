package com.socialseller.clothcrew.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.ProductDetailsActivity
import com.socialseller.clothcrew.activity.orders.OrderDetailsActivity

class AdapterProducts (private val context: Context,
                       private val itemList: List<Item>
) : RecyclerView.Adapter<AdapterProducts.ItemViewHolder>() {

    // ViewHolder class
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_products, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, ProductDetailsActivity::class.java))
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}