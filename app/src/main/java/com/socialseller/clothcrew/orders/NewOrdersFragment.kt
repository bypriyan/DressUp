package com.socialseller.clothcrew.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bypriyan.sharemarketcourseinhindi.adapter.AdapterOnBordingScreen
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.AdapterOrders
import com.socialseller.clothcrew.adapter.AdapterStore
import com.socialseller.clothcrew.adapter.AdapyterBigCategory
import com.socialseller.clothcrew.adapter.AdapyterCategory
import com.socialseller.clothcrew.databinding.FragmentHomeBinding
import com.socialseller.clothcrew.databinding.FragmentNewOrdersBinding
import com.socialseller.clothcrew.model.Item

class NewOrdersFragment : Fragment() {


    private lateinit var binding: FragmentNewOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewOrdersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = listOf(
            Item(R.drawable.category_girl_img, "Traditional"),
            Item(R.drawable.category_girl_img, "Printed")
        )
        binding.ordersRv.adapter = AdapterOrders(requireContext(),itemList)
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