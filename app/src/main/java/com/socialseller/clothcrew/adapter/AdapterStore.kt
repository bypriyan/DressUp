package com.socialseller.clothcrew.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.stores.StorePageActivity
import com.socialseller.clothcrew.databinding.RowStoreBinding
import com.socialseller.clothcrew.modelResponce.Business
import com.socialseller.clothcrew.utility.GlideHelper

class AdapterStore(
    private val context: Context,
    private var itemList: MutableList<Business> // Changed to MutableList for modifications
) : RecyclerView.Adapter<AdapterStore.ItemViewHolder>() {

    // ViewHolder with View Binding
    inner class ItemViewHolder(val binding: RowStoreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowStoreBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]

        holder.binding.storeName.text = item.business_name
        holder.binding.storeLocation.text = item.address
        holder.binding.storePhoneNumber.text = item.seller.phone
        holder.binding.storeWorkingHours.text = "Working Hours: ${item.open_time}Am - ${item.close_time}Pm"

        GlideHelper.loadImage(holder.binding.storeImage, Constants.KEY_IMAGE_PATH + item.business_logo.formats.thumbnail?.url?:"")

        holder.binding.syncBtn.setOnClickListener {
            // Implement direction functionality
        }

    }

    override fun getItemCount(): Int = itemList.size

    // Efficiently update the list using DiffUtil
    fun updateData(newList: List<Business>) {
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
        diffResult.dispatchUpdatesTo(this)
    }
}
