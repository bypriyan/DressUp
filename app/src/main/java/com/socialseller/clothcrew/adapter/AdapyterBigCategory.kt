package com.socialseller.clothcrew.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.stores.StoreLocationActivity
import com.socialseller.clothcrew.databinding.RowBigCategoriesBinding
import com.socialseller.clothcrew.databinding.RowCategoriesBinding
import com.socialseller.clothcrew.modelResponce.Category
import com.socialseller.clothcrew.modelResponce.Categorycollection
import com.socialseller.clothcrew.utility.GlideHelper

class AdapyterBigCategory (
    private val context: Context,
    private var itemList: MutableList<Categorycollection> // Changed to MutableList for modifications
) : RecyclerView.Adapter<AdapyterBigCategory.ItemViewHolder>() {

    // ViewHolder with View Binding
    inner class ItemViewHolder(val binding: RowBigCategoriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowBigCategoriesBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        GlideHelper.loadImage(holder.binding.categoryBigImage, Constants.KEY_IMAGE_PATH + item.thumbnail.url)
        holder.binding.categoryBigName.text = item.name

        holder.binding.root.setOnClickListener {
            context.startActivity(Intent(context, StoreLocationActivity::class.java))
        }
    }

    override fun getItemCount(): Int = itemList.size

    // Efficiently update the list using DiffUtil
    fun updateData(newList: List<Categorycollection>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = itemList.size
            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return itemList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return itemList[oldItemPosition] == newList[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        itemList.clear()
        itemList.addAll(newList)
        diffResult.dispatchUpdatesTo(this) // Optimized updates instead of full refresh
    }
}
