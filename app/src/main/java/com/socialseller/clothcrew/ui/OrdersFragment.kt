package com.socialseller.clothcrew.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.OrdersPagerAdapter
import com.socialseller.clothcrew.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    private val tabTitles = listOf("New", "Accepted", "Rejected", "In Transit")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        val adapter = OrdersPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab, null)
            val tabText = tabView.findViewById<TextView>(R.id.tab_text)
            tabText.text = tabTitles[position]

            if (position == 0) {
                tabText.setBackgroundResource(R.drawable.tab_selected_background)
                tabText.setTextColor(resources.getColor(R.color.white, null))
            } else {
                tabText.setBackgroundResource(R.drawable.tab_unselected_background)
                tabText.setTextColor(resources.getColor(R.color.appColor, null))
            }
            tab.customView = tabView
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tab_text)?.apply {
                    setBackgroundResource(R.drawable.tab_selected_background)
                    setTextColor(resources.getColor(R.color.white, null))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.findViewById<TextView>(R.id.tab_text)?.apply {
                    setBackgroundResource(R.drawable.tab_unselected_background)
                    setTextColor(resources.getColor(R.color.appColor, null))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        return binding.root
    }
}