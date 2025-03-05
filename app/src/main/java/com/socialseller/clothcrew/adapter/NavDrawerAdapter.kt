package com.socialseller.clothcrew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.model.NavMenuItem
import com.socialseller.clothcrew.R

class NavDrawerAdapter(
    private val items: List<NavMenuItem>,
    private val listener: (NavMenuItem) -> Unit
) : RecyclerView.Adapter<NavDrawerAdapter.NavViewHolder>() {

    class NavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val icon: ImageView = view.findViewById(R.id.item_icon)
//        val title: TextView = view.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nav_drawer, parent, false)
        return NavViewHolder(view)
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        val item = items[position]
//        holder.icon.setImageResource(item.icon)
//        holder.title.text = item.title
//
//        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = items.size
}
