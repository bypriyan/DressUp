package com.socialseller.clothcrew.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
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
import com.socialseller.clothcrew.activity.SearchActivity
import com.socialseller.clothcrew.activity.stores.StoreLocationActivity
import com.socialseller.clothcrew.databinding.RowCategoriesBinding
import com.socialseller.clothcrew.modelResponce.Category
import com.socialseller.clothcrew.utility.GlideHelper

class AdapyterCategory(
    private val context: Context,
    private var itemList: MutableList<Category> // Changed to MutableList for modifications
) : RecyclerView.Adapter<AdapyterCategory.ItemViewHolder>() {

    // ViewHolder with View Binding
    inner class ItemViewHolder(val binding: RowCategoriesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowCategoriesBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        GlideHelper.loadImage(holder.binding.categoryImage,
            (Constants.KEY_IMAGE_PATH + item.thumbnail.formats.thumbnailFormat?.url) ?: ""
        )
        holder.binding.categoryName.text = item.name

        holder.binding.root.setOnClickListener {
            Log.d("prodId", "onBindViewHolder: ${item.id}")
            val intent = Intent(context, SearchActivity::class.java)
            intent.putExtra(Constants.KEY_CATEGORY_ID, item.id.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = itemList.size

    // Efficiently update the list using DiffUtil
    fun updateData(newList: List<Category>) {
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
