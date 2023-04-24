package com.example.viewmodel1.network

import android.app.Application
import android.util.Log
import com.example.viewmodel1.viewmodel.MainViewModelFactory

class MyApplication : Application() {

    private val apiProvider = ApiProvider()
    val mainViewModelFactory = MainViewModelFactory(apiProvider)


    override fun onCreate() {
        super.onCreate()
        Log.d("My application", "starting ok!")
    }
}