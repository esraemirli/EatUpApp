package com.emirli.eatup.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emirli.eatup.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}