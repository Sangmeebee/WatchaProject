package com.sangmee.watchaproject

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}
