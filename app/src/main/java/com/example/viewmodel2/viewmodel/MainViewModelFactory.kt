package com.example.viewmodel1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel1.network.ApiProvider

class MainViewModelFactory(private val dogApiProvider: ApiProvider) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dogApiProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
