package com.bypriyan.sharemarketcourseinhindi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bypriyan.bustrackingsystem.utility.Constants
import com.google.android.material.card.MaterialCardView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.api.BannerData
import com.socialseller.clothcrew.databinding.RowBannerBinding
import com.socialseller.clothcrew.utility.GlideHelper

class AdapterOnBordingScreen(
    private val context: Context,
    private var elementList: MutableList<BannerData>
) : RecyclerView.Adapter<AdapterOnBordingScreen.HolderOnBordingScreen>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderOnBordingScreen {
        val binding = RowBannerBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderOnBordingScreen(binding)
    }

    override fun onBindViewHolder(holder: HolderOnBordingScreen, position: Int) {
        GlideHelper.loadImage(
            holder.binding.bannerImage,
            Constants.KEY_IMAGE_PATH + (elementList[position].attributes.image.data.attributes.formats.thumbnail?.url ?: "")
        )
    }

    override fun getItemCount(): Int = elementList.size

    // Function to update the dataset dynamically
    fun updateData(newList: List<BannerData>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = elementList.size
            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return elementList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return elementList[oldItemPosition] == newList[newItemPosition]
            }
        }

        val diffResult = DiffUtil.calculateDiff(diffCallback)
        elementList.clear()
        elementList.addAll(newList)
        diffResult.dispatchUpdatesTo(this) // Smart updates instead of full refresh
    }

    inner class HolderOnBordingScreen(val binding: RowBannerBinding) : RecyclerView.ViewHolder(binding.root)
}
