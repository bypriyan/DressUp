package com.socialseller.clothcrew.activity.profile

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bypriyan.bustrackingsystem.utility.Constants
import com.bypriyan.bustrackingsystem.utility.DataStoreManager
import com.socialseller.clothcrew.activity.BodyMeasureActivity
import com.socialseller.clothcrew.api.UserRequest
import com.socialseller.clothcrew.databinding.ActivityLoginBinding
import com.socialseller.clothcrew.databinding.ActivitySetupProfileBinding
import com.socialseller.clothcrew.utility.MyActivity
import com.socialseller.clothcrew.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class SetupProfileActivity : MyActivity() {

    private val binding by lazy { ActivitySetupProfileBinding.inflate(layoutInflater) }
    private val authViewModel: AuthViewModel by viewModels()
    private var selectedImageUri: Uri? = null // Store the selected image URI
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private lateinit var phoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //init
        setupBackButton(binding.back)
        obsurvePhoneNumber()
        obsurveRegisterResponce()

        binding.profileFrame.setOnClickListener {
            pickImageLauncher= registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                selectedImageUri = it // Store the URI for later use
                displayImage(it)
            }
        }
        }

        binding.saveBtn.setOnClickListener {
            var name  = binding.nameET.text.toString()
            if(!name.isEmpty()){
                isLoading(true)
                authViewModel.registerUser(userRequest = UserRequest(name = name, phone = phoneNumber))
            }
        }
    }

    private fun displayImage(imageUri: Uri) {
        try {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(contentResolver, imageUri)
                ImageDecoder.decodeBitmap(source)
            } else {
                contentResolver.openInputStream(imageUri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)
                }
            }
            binding.profileImage.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun obsurvePhoneNumber(){
        authViewModel.userPhoneNumber.observe(this){phoneNumber->
            phoneNumber?.let {
                this.phoneNumber = it
            }
        }
    }

    private fun obsurveRegisterResponce(){
        authViewModel.registerRresponse.observe(this){name->
            isLoading(false)
            lifecycleScope.launch {
                dataStoreManager.putString(Constants.KEY_USER_NAME,name)
            }
            startActivity(Intent(this, BodyMeasureActivity::class.java))
            finish()
        }

    }

    private fun isLoading(isLoading: Boolean) {
        binding.progressbar.isVisible = isLoading
        binding.saveBtn.isVisible = !isLoading
    }

}