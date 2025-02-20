package com.kriyantechzone.hiltandroidapps

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        Log.d("MyApplication", "Started App OnCreate Called...")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        Log.d("MyApplication", "onConfigurationChanged Called...${newConfig.orientation}")
    }

}