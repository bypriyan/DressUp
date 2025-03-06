package com.socialseller.clothcrew.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.bypriyan.sharemarketcourseinhindi.adapter.AdapterOnBordingScreen
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.OrdersPagerAdapter
import com.socialseller.clothcrew.databinding.FragmentHomeBinding
import com.socialseller.clothcrew.databinding.FragmentOrdersBinding
import kotlin.compareTo
import kotlin.text.compareTo

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        var adapter = AdapterOnBordingScreen(requireContext(), getListOfOnBordingScreenContent())
        binding.viewPager2.adapter = adapter

        binding.wormDotsIndicator.attachTo(binding.viewPager2)

        binding.viewPager2.isUserInputEnabled = true

        return binding.root
    }

    fun getListOfOnBordingScreenContent():List<Int>{
        return listOf(
            R.drawable.banner_zero,
            R.drawable.banner_one,
            R.drawable.banner_two,
            R.drawable.banner_three
        )
    }
}