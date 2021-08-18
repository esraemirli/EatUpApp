package com.emirli.eatup

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EatUpActivity : Application(){
    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}