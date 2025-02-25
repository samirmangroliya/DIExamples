package com.kriyantechzone.hiltandroidapps

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.tag("MyApplication").d("Started App OnCreate Called...")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        Timber.tag("MyApplication").d("onConfigurationChanged Called...%s", newConfig.orientation)
    }

}