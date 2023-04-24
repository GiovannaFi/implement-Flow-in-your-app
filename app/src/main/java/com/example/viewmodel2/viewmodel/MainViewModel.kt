package com.example.viewmodel1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodel1.network.ApiProvider
import com.example.viewmodel1.network.dto.Data
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

sealed class Response<out T>{
    object Loading : Response<Nothing>()
    data class Success<T>(val code : Int, val body : T?) : Response<T>()
    data class Error(val code : Int, val message: String) : Response<Nothing>()
}

class MainViewModel(private val dogApiProvider: ApiProvider) : ViewModel() {

    private var _dogImage = MutableLiveData<Response<Data>>()
    val dogImage: LiveData<Response<Data>>
        get() = _dogImage


    fun getDogImageNetworkCall() {
        _dogImage.postValue(Response.Loading) //postvalue è =
        viewModelScope.launch {
            try {
                val response = dogApiProvider.getDogData()
                if (response.isSuccessful) {
                    val dogImage = response.body()
                    _dogImage.postValue(Response.Success(response.code(), dogImage))
                    Log.e("MainViewModel", "ok!: ${response.code()}")

                } else {
                    _dogImage.postValue(Response.Error(response.code(), response.message()))
                    Log.e(
                        "MainViewModel",
                        "com.example.viewmodel1.ui.main.Response not successful: ${response.code()}"
                    )
                }
            } catch (e: Exception) {
                _dogImage.postValue(Response.Error(500, "ci sono problemi"))
                Log.e("MainViewModel", "Error: ${e.message}")
            }
        }
    }


}