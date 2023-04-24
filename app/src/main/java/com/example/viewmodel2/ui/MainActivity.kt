package com.example.viewmodel2.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.example.viewmodel2.databinding.ActivityMainBinding
import com.example.viewmodel2.network.MyApplication
import com.example.viewmodel2.network.dto.Data
import com.example.viewmodel2.viewmodel.MainViewModel
import com.example.viewmodel2.viewmodel.Response
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = (application as MyApplication).mainViewModelFactory.create(MainViewModel::class.java) //?


        viewModel.getDogImageNetworkCall()

        lifecycleScope.launch{
            viewModel.dogImage.collect() { dogImage ->
                when (dogImage) {
                    is Response.Error -> Toast.makeText(this@MainActivity, "no", LENGTH_LONG).show()
                    Response.Loading -> Toast.makeText(this@MainActivity, "loading", LENGTH_LONG).show()
                    is Response.Success<Data> -> Picasso.get().load(dogImage.body?.message)
                        .into(binding.dog)
                }
                binding.dog.visibility = View.VISIBLE
            }


        }


    }
}