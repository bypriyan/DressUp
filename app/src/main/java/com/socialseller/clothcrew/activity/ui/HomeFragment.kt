package com.socialseller.clothcrew.activity.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bypriyan.sharemarketcourseinhindi.adapter.AdapterOnBordingScreen
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.SearchActivity
import com.socialseller.clothcrew.adapter.AdapyterBigCategory
import com.socialseller.clothcrew.adapter.AdapyterCategory
import com.socialseller.clothcrew.adapter.AdapterStore
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.databinding.FragmentHomeBinding
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by viewModels()

    private val adapterCategory by lazy { AdapyterCategory(requireContext(), mutableListOf()) }
    private val adapterOnboarding by lazy { AdapterOnBordingScreen(requireContext(), mutableListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        observeData()

        // Search button click listener
        binding.searchClickView.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
    }

    private fun setupRecyclerViews() {
        binding.rvCategory.adapter = adapterCategory
        binding.viewPager2.adapter = adapterOnboarding
        binding.wormDotsIndicator.attachTo(binding.viewPager2)
        binding.viewPager2.isUserInputEnabled = true
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { observeCollections() }
                launch { observeBanners() }
            }
        }
    }

    private suspend fun observeBanners() {
        productViewModel.banners.collectLatest { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    // Handle loading state (if needed)
                }
                is ApiResponse.Success -> {
                    response.data?.data?.let { itemList ->
                        Log.d("banners", "observeBanners: $itemList")
                        adapterOnboarding.updateData(itemList)
                    }
                }
                is ApiResponse.Error -> {
                    Log.e("HomeFragment", "Error fetching banners: ${response.message}")
                }
            }
        }
    }

    private suspend fun observeCollections() {
        productViewModel.collections.collectLatest { response ->
            when (response) {
                is ApiResponse.Loading -> {
                    // Handle loading state (if needed)
                }
                is ApiResponse.Success -> {
                    response.data?.data?.let { itemList ->
                        adapterCategory.updateData(itemList)
                    }
                }
                is ApiResponse.Error -> {
                    Log.e("HomeFragment", "Error fetching categories: ${response.message}")
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding?.let {
            _binding = null // Prevent memory leaks
        }
        super.onDestroyView()
    }
}
