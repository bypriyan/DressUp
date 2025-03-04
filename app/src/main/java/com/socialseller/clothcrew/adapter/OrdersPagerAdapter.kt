package com.socialseller.clothcrew.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.socialseller.clothcrew.orders.AcceptedOrdersFragment
import com.socialseller.clothcrew.orders.InTransitOrdersFragment
import com.socialseller.clothcrew.orders.NewOrdersFragment
import com.socialseller.clothcrew.orders.RejectedOrdersFragment


class OrdersPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewOrdersFragment()
            1 -> AcceptedOrdersFragment()
            2 -> RejectedOrdersFragment()
            3 -> InTransitOrdersFragment()
            else -> NewOrdersFragment()
        }
    }
}
