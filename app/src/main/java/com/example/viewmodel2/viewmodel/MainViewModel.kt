package com.example.viewmodel2.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodel2.network.ApiProvider
import com.example.viewmodel2.network.dto.Data
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

sealed class Response<out T>{
    object Loading : Response<Nothing>()
    data class Success<T>(val code : Int, val body : T?) : Response<T>()
    data class Error(val code : Int, val message: String) : Response<Nothing>()
}

class MainViewModel(private val dogApiProvider: ApiProvider) : ViewModel() {

    private val _dogImage = MutableStateFlow<Response<Data>>(Response.Loading)
    val dogImage: StateFlow<Response<Data>> = _dogImage


    fun getDogImageNetworkCall() {
        viewModelScope.launch {
            try {
                val response = dogApiProvider.getDogData()
                if (response.isSuccessful) {
                    val dogImage = response.body()
                    _dogImage.value = Response.Success(response.code(), dogImage)
                    Log.e("MainViewModel", "ok!: ${response.code()}")

                } else {
                    _dogImage.value = Response.Error(response.code(), response.message())
                    Log.e(
                        "MainViewModel",
                        "Response not successful: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _dogImage.value = Response.Error(500, "ci sono problemi")
                Log.e("MainViewModel", "Error: ${e.message}")
            }

        }


    }


}