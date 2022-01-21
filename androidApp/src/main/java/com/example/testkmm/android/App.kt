package com.example.testkmm.android

import android.app.Application
import android.util.Log
import com.example.testkmm.di.MultiplatformSDK
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiplatformSDK.init(this)
    }
}