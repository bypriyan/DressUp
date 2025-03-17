package com.socialseller.clothcrew.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.socialseller.clothcrew.R
import com.socialseller.clothcrew.adapter.SizeAdapter
import com.socialseller.clothcrew.databinding.ActivityAddToCartBinding
import com.socialseller.clothcrew.databinding.ActivitySetupProfileBinding

class AddToCartActivity : AppCompatActivity() {

    private lateinit var sizeAdapter: SizeAdapter
    private val sizes = listOf("S", "L", "XL", "XXL", "FREE SIZE")
    private lateinit var binding : ActivityAddToCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.sizeRV)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        sizeAdapter = SizeAdapter(sizes) { selectedSize ->
            Toast.makeText(this, "Selected: $selectedSize", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = sizeAdapter

        binding.note.setOnClickListener {
            showBottomSheet()
        }

        binding.back.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        //back pressed
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

    }

    private fun showBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_note, null)
        dialog.setContentView(view)

        val tvAddMedia = view.findViewById<TextView>(R.id.tvAddMedia)
        tvAddMedia.setOnClickListener {
            showMediaBottomSheet()
        }

        dialog.show()

    }

    private fun showMediaBottomSheet() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_addmedia, null)
        dialog.setContentView(view)

        val closeButton = view.findViewById<TextView>(R.id.tvAddMedia)
        closeButton.setOnClickListener {
            dialog.dismiss()
            showBottomSheet()
        }

        dialog.show()
    }
}