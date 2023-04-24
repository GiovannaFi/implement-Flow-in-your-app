package com.example.viewmodel2.network

import android.app.Application
import android.util.Log
import com.example.viewmodel2.viewmodel.MainViewModelFactory

class MyApplication : Application() {

    private val apiProvider = ApiProvider()
    val mainViewModelFactory = MainViewModelFactory(apiProvider)


    override fun onCreate() {
        super.onCreate()
        Log.d("My application", "starting ok!")
    }
}