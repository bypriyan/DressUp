package com.socialseller.clothcrew.adapter

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.AddToCartActivity
import com.socialseller.clothcrew.activity.ProductDetailsActivity
import com.socialseller.clothcrew.activity.orders.OrderDetailsActivity
import com.socialseller.clothcrew.databinding.RowProductsBinding
import com.socialseller.clothcrew.modelResponce.CategoryProductResponce
import com.socialseller.clothcrew.modelResponce.ProductCategory
import com.socialseller.clothcrew.utility.GlideHelper
import kotlin.math.ceil

class AdapterProducts(
    private val context: Context,
    private var itemList: MutableList<ProductCategory>
) : RecyclerView.Adapter<AdapterProducts.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: RowProductsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = RowProductsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]

        holder.binding.productName.text = item.name
        holder.binding.productSellingPrice.text = "₹${item.product_variants[0].price}"
        holder.binding.productMrp.text = "₹${item.product_variants[0].strikePrice}".let {
            val spannable = SpannableString(it)
            spannable.setSpan(StrikethroughSpan(), 0, it.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable
        }
        holder.binding.productDiscountPercentage.text = "${calculateDiscount(
            item.product_variants[0].strikePrice,
            item.product_variants[0].price
        )}% OFF"

        GlideHelper.loadImage(holder.binding.productImage, (Constants.KEY_IMAGE_PATH + item.thumbnail?.formats?.thumbnail?.url) ?: "")

        holder.binding.root.setOnClickListener {
            context.startActivity(Intent(context, ProductDetailsActivity::class.java))
        }

        holder.binding.addToCartTv.setOnClickListener {
            context.startActivity(Intent(context, AddToCartActivity::class.java))
        }
    }

    override fun getItemCount(): Int = itemList.size

    // Efficiently update the list using DiffUtil
    fun updateData(newList: List<ProductCategory>) {
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


    fun calculateDiscount(mrp: Double, sellingPrice: Double): Int {
        if (mrp <= 0 || sellingPrice < 0 || sellingPrice > mrp) {
            throw IllegalArgumentException("Invalid prices: MRP should be greater than 0 and selling price should not exceed MRP")
        }
        return ceil(((mrp - sellingPrice) / mrp) * 100).toInt()
    }
}
