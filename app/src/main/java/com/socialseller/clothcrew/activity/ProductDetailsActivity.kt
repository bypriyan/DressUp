package com.socialseller.clothcrew.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.activity.cart.MyCartActivity
import com.socialseller.clothcrew.adapter.AdapterAddress
import com.socialseller.clothcrew.adapter.AdapterBodySize
import com.socialseller.clothcrew.adapter.AdapterProductHorizontal
import com.socialseller.clothcrew.adapter.AdapterProductImages
import com.socialseller.clothcrew.adapter.SizeAdapter
import com.socialseller.clothcrew.databinding.ActivityAddressBinding
import com.socialseller.clothcrew.databinding.ActivityLoginBinding
import com.socialseller.clothcrew.databinding.ActivityProductDetailsBinding
import com.socialseller.clothcrew.model.Item
import com.socialseller.clothcrew.utility.MyActivity
import com.socialseller.clothcrew.utility.ResponceHelper
import com.socialseller.clothcrew.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class ProductDetailsActivity : MyActivity() {

    private val binding by lazy { ActivityProductDetailsBinding.inflate(layoutInflater) }
    private val poductViewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBackButton(binding.back)

        poductViewModel.getProductDetails(33)
        observeProductDetails()

    }

    private fun observeProductDetails(){
        lifecycleScope.launch {
            poductViewModel.productDetails.collect { response->
                ResponceHelper.handleApiResponse(
                    response,
                    onSuccess = {
                        Log.d("prod", "observeCategoryProduct: ${it}")
                    },
                    logTag = "prod"
                )
            }
        }
    }
}