package com.socialseller.clothcrew.activity.address

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.databinding.ActivityAddAddressBinding
import com.socialseller.clothcrew.databinding.ActivityProductDetailsBinding
import com.socialseller.clothcrew.utility.MyActivity
import com.socialseller.clothcrew.viewModel.AddressViewModel
import com.socialseller.clothcrew.viewModel.ProductViewModel
import kotlin.getValue

class AddAddressActivity : MyActivity() {

    private val binding by lazy { ActivityAddAddressBinding.inflate(layoutInflater) }
    private val addressViewModel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //init
        setupBackButton(binding.back)

    }
}