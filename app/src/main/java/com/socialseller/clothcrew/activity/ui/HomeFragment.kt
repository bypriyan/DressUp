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
import com.bypriyan.bustrackingsystem.utility.Constants
import com.bypriyan.sharemarketcourseinhindi.adapter.AdapterOnBordingScreen
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.SearchActivity
import com.socialseller.clothcrew.adapter.AdapyterBigCategory
import com.socialseller.clothcrew.adapter.AdapyterCategory
import com.socialseller.clothcrew.adapter.AdapterStore
import com.socialseller.clothcrew.apiResponce.ApiResponse
import com.socialseller.clothcrew.databinding.FragmentHomeBinding
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.utility.ResponceHelper
import com.socialseller.clothcrew.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by viewModels()

    private val adapterBigCategory by lazy { AdapyterBigCategory(requireContext(), mutableListOf()) }
    private val adapterOnboarding by lazy { AdapterOnBordingScreen(requireContext(), mutableListOf()) }
    private val adapterCategory by lazy { AdapyterCategory(requireContext(), mutableListOf()) }
    private val adapterStore by lazy { AdapterStore(requireContext(), mutableListOf()) }

    private var slidingJob: Job? = null // Job for automatic sliding

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

        binding.searchClickView.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
    }

    private fun setupRecyclerViews() = binding.apply {
        bigCategoriesRV.adapter = adapterBigCategory
        storeRv.adapter = adapterStore
        rvCategory.adapter = adapterCategory
        viewPager2.adapter = adapterOnboarding
        wormDotsIndicator.attachTo(viewPager2)
        viewPager2.isUserInputEnabled = true
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeCollections()
                observeBanners()
                observeCategories()
                observeStores()
            }
        }
    }

    private  fun  observeStores() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.stores.collectLatest { response ->
                ResponceHelper.handleApiResponse(
                    response,
                    onSuccess = { adapterStore.updateData(it.data) },
                    logTag = "store"
                )
            }
        }
    }

    private fun observeCategories() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.categories.collectLatest { response ->
                ResponceHelper.handleApiResponse(
                    response,
                    onSuccess = { adapterCategory.updateData(it.categories) },
                    logTag = "Categories"
                )
            }
        }
    }

    private fun observeBanners() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.banners.collectLatest { response ->
                ResponceHelper.handleApiResponse(
                    response,
                    onSuccess = {
                        adapterOnboarding.updateData(it.data)
                        startAutoSlide()
                                },
                    logTag = "Banners"
                )
            }
        }
    }

    private fun observeCollections() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.collections.collectLatest { response ->
                ResponceHelper.handleApiResponse(
                    response,
                    onSuccess = { adapterBigCategory.updateData(it.data) },
                    logTag = "Collections"
                )
            }
        }
    }


    private fun startAutoSlide() {
        slidingJob?.cancel() // Cancel existing job if running
        slidingJob = viewLifecycleOwner.lifecycleScope.launch {
            while (true) {
                delay(3000) // Slide every 3 seconds
                binding.viewPager2.let { viewPager ->
                    val nextItem = (viewPager.currentItem + 1) % adapterOnboarding.itemCount
                    viewPager.setCurrentItem(nextItem, true)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null // Prevent memory leaks
        slidingJob?.cancel() // Stop sliding when view is destroyed
        super.onDestroyView()
    }
}
