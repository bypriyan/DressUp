package com.socialseller.clothcrew.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bypriyan.bustrackingsystem.utility.Constants
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.auth.LoginActivity
import com.socialseller.clothcrew.adapter.AdapterProducts
import com.socialseller.clothcrew.adapter.AdapterSearchProducts
import com.socialseller.clothcrew.adapter.AdapyterBigCategory
import com.socialseller.clothcrew.databinding.ActivityHomeBinding
import com.socialseller.clothcrew.databinding.ActivityLoginBinding
import com.socialseller.clothcrew.databinding.ActivitySearchBinding
import com.socialseller.clothcrew.databinding.FragmentSavedBinding
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.utility.KeyboardUtils
import com.socialseller.clothcrew.utility.MyActivity
import com.socialseller.clothcrew.utility.ResponceHelper
import com.socialseller.clothcrew.viewModel.AuthViewModel
import com.socialseller.clothcrew.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class SearchActivity : MyActivity() {

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }
    private val poductViewModel: ProductViewModel by viewModels()
    private val adapterProducts by lazy { AdapterProducts(this, mutableListOf()) }
    private val adapterSearchProducts by lazy { AdapterSearchProducts(this, mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        poductViewModel.getSearchProduct("leh")
        intent.getStringExtra(Constants.KEY_CATEGORY_ID)?.let {
            poductViewModel.getCategoriesProduct(it)
        }

        setupBackButton(binding.back)

        setupRecyclerViews()
        setupSearchListener()
        observeCategoryProduct()
        observeSearchProduct()

    }

    private fun setupRecyclerViews() = binding.apply {
        recyclerViewProduct.adapter = adapterProducts
    }


    private fun setupSearchListener() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isNotEmpty()) {
                    poductViewModel.getSearchProduct(query)
                    binding.recyclerViewProduct.adapter = adapterSearchProducts // Set search adapter
                } else {
                    binding.recyclerViewProduct.adapter = adapterProducts // Revert to category adapter
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    private fun observeCategoryProduct(){
       lifecycleScope.launch {
           poductViewModel.categoryProducts.collect { response->
               Log.d("product", "observeCategoryProduct: $response")
               ResponceHelper.handleApiResponse(
                   response,
                   onSuccess = {
                       Log.d("pd", "observeCategoryProduct: ${it.products}")
                       adapterProducts.updateData(it.products)
                               },
                   logTag = "Collections"
               )
           }
       }
    }

    private fun observeSearchProduct(){
        lifecycleScope.launch {
            poductViewModel.searchProduct.collect { response ->
                ResponceHelper.handleApiResponse(
                    response,
                    onSuccess = {
                        Log.d("srch", "observeSearchProduct: ${it.data}")
                        adapterSearchProducts.updateData(it.data) // Update search results
                    },
                    logTag = "search"
                )
            }
        }
    }
}