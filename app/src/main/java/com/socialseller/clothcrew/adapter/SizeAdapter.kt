package com.socialseller.clothcrew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.R

class SizeAdapter(private val sizes: List<String>, private val onSizeSelected: (String) -> Unit) :
    RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    private var selectedPosition = -1

    inner class SizeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sizeText: TextView = itemView.findViewById(R.id.sizeText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.size_item, parent, false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.sizeText.text = sizes[position]

        // Highlight selected size
        holder.sizeText.isSelected = (position == selectedPosition)

        // Handle click event
        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            onSizeSelected(sizes[position])
        }
    }

    override fun getItemCount() = sizes.size
}
