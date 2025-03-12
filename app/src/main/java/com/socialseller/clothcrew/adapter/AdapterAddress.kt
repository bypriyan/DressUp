package com.socialseller.clothcrew.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.AddToCartActivity
import com.socialseller.clothcrew.activity.ProductDetailsActivity
import com.socialseller.clothcrew.activity.address.EditAddressActivity
import com.socialseller.clothcrew.activity.orders.OrderDetailsActivity

class AdapterAddress (private val context: Context, private val itemList: List<Item>) : RecyclerView.Adapter<AdapterAddress.ItemViewHolder>() {

    // ViewHolder class
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val edit: TextView = itemView.findViewById(R.id.edit)
        val delete: TextView = itemView.findViewById(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_address, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.edit.setOnClickListener {
            context.startActivity(Intent(context, EditAddressActivity::class.java))
        }

        holder.delete.setOnClickListener {
            showdeleteDialog()
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun showdeleteDialog() {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(context).inflate(R.layout.delete_address_dialog, null)
        dialog.setContentView(view)

        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }
}