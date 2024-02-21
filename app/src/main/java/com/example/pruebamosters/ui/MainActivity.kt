package com.example.pruebamosters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pruebamosters.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Router().goToScreens(screenChange.HOME, this, true)
    }
}