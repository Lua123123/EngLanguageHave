package com.example.englanguage

import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    //SHARED PREFERENCE
    private var sharedPref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        //SHARED PREFERENCE
        sharedPref = getSharedPreferences("data", MODE_PRIVATE)
        val editor = sharedPref?.edit()
    }

//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            return
//        }
//        this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
//        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
//    }
}