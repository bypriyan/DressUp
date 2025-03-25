package com.socialseller.clothcrew.activity.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
    private lateinit var adapter: AdapyterCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //onsurve category
        observeCollections()

        var adapter = AdapterOnBordingScreen(requireContext(), getListOfOnBordingScreenContent())
        val itemList = listOf(
            Item(R.drawable.category_girl_img, "Traditional"),
            Item(R.drawable.category_girl_img, "Printed"),
            Item(R.drawable.category_girl_img, "Ethnic Wear"),
            Item(R.drawable.category_girl_img, "Gown")
        )
        var adaptercategory = AdapyterCategory(requireContext(),itemList)
        binding.rvCategory.adapter = adaptercategory

        var adaptercategoryBig = AdapyterBigCategory(requireContext(),itemList)
        var adapterStore = AdapterStore(requireContext(),itemList)
        binding.bigCategoriesRV.adapter = adaptercategoryBig
        binding.festBigCategoryBig.adapter = adaptercategoryBig
        binding.storeRv.adapter = adapterStore
        //view pager
        binding.viewPager2.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.viewPager2)
        binding.viewPager2.isUserInputEnabled = true

        binding.searchClickView.setOnClickListener {
            startActivity(Intent(requireContext(),SearchActivity::class.java))
        }
    }

    private fun observeCollections() {
        viewLifecycleOwner.lifecycleScope.launch {
            productViewModel.collections.collectLatest { response ->
                when (response) {
                    is ApiResponse.Loading -> {
                    }
                    is ApiResponse.Success -> {
                        Log.d("checking", "observeCollections: ${response.data?.data}")
                    }
                    is ApiResponse.Error -> {
//                        binding.progressBar.visibility = View.GONE
//                        Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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