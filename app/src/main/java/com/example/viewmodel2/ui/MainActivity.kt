package com.example.viewmodel1.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodel1.viewmodel.MainViewModel
import com.example.viewmodel1.viewmodel.Response
import com.example.viewmodel1.network.dto.Data
import com.example.viewmodel1.network.MyApplication
import com.example.viewmodel2.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = (application as MyApplication).mainViewModelFactory.create(MainViewModel::class.java) //?


        viewModel.getDogImageNetworkCall()

        viewModel.dogImage.observe(this) { dogImage ->
            when (dogImage) {
                is Response.Error -> Toast.makeText(this, "no", LENGTH_LONG).show()
                Response.Loading -> Toast.makeText(this, "loading", LENGTH_LONG).show()
                is Response.Success<Data> -> Picasso.get().load(dogImage.body?.message)
                    .into(binding.dog)
            }
        }

        binding.dog.visibility = View.VISIBLE
    }
}