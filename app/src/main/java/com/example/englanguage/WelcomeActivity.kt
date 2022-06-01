package com.example.englanguage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.englanguage.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide()
    }
}