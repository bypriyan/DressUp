package com.socialseller.clothcrew.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.stores.StoreLocationActivity
import com.socialseller.clothcrew.api.Category
import com.socialseller.clothcrew.databinding.RowCategoriesBinding
import com.socialseller.clothcrew.utility.GlideHelper

class AdapyterCategory(
    private val context: Context,
    private val itemList: List<Category>
) : RecyclerView.Adapter<AdapyterCategory.ItemViewHolder>() {

    // ViewHolder with View Binding
    inner class ItemViewHolder(val binding: RowCategoriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowCategoriesBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]

        GlideHelper.loadImage(holder.binding.categoryImage, item.thumbnail.formats.small?.url)
        holder.binding.categoryName.text = item.name

        holder.binding.root.setOnClickListener {
            context.startActivity(Intent(context, StoreLocationActivity::class.java))
        }
    }

    override fun getItemCount(): Int = itemList.size
}
