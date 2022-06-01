package com.example.englanguage

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private var topic: RelativeLayout? = null
    private var topicOffMode: RelativeLayout? = null
    private var vocabulary: RelativeLayout? = null
    private var speak: RelativeLayout? = null
    private var logout: RelativeLayout? = null
    private var flipcard: RelativeLayout? = null
    private var Authorization: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
//        val bundleNav = intent
//        state = bundleNav.getBooleanExtra("state", false)
        val intent = intent
        val bundle = intent.extras
        Authorization = bundle!!.getString("Authorization")

        topic = findViewById(R.id.topic)
        topic?.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this@MainActivity, TopicActivity::class.java)
            val bundle1 = Bundle()
            bundle1.putString("Authorization", Authorization)
            intent1.putExtras(bundle1)
            startActivity(intent1)
        })

        vocabulary = findViewById(R.id.vocabulary)
        vocabulary?.setOnClickListener(View.OnClickListener {
            val intent2 = Intent(this@MainActivity, VocabularyActivity::class.java)
            val bundle2 = Bundle()
            bundle2.putString("Authorization", Authorization)
            intent2.putExtras(bundle2)
            startActivity(intent2)
        })

        speak = findViewById(R.id.speak)
        speak?.setOnClickListener(View.OnClickListener {
            val intent3 = Intent(this@MainActivity, TextToSpeechActivity::class.java)
            val bundle3 = Bundle()
            bundle3.putString("Authorization", Authorization)
            intent3.putExtras(bundle3)
            startActivity(intent3)
        })

        logout = findViewById(R.id.logout)
        logout?.setOnClickListener(View.OnClickListener {
            openDialogInsertVocabulary(Gravity.CENTER)
        })

        flipcard = findViewById(R.id.flipcard)
        flipcard?.setOnClickListener(View.OnClickListener {
            val intent4 = Intent(this@MainActivity, FlipCardActivity::class.java)
            val bundle4 = Bundle()
            bundle4.putString("Authorization", Authorization)
            intent4.putExtras(bundle4)
            startActivity(intent4)
        })
    }

    fun openDialogInsertVocabulary(gravity: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_logout)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancelDialog)
        val btnConfirm = dialog.findViewById<Button>(R.id.btnConFirmDialog)
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            val intent4 = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent4)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun customToast(toast: Toast) {
        val toastView = toast.view
        val toastMessage = toastView!!.findViewById<View>(android.R.id.message) as TextView
        toastMessage.textSize = 13f
        toastMessage.setTextColor(Color.YELLOW)
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        toastMessage.gravity = Gravity.CENTER
        toastMessage.compoundDrawablePadding = 4
        toastView.setBackgroundColor(Color.BLACK)
        toastView.setBackgroundResource(R.drawable.bg_toast)
        toast.show()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val intent5 = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent5)
            super.onBackPressed()
        }
        this.doubleBackToExitPressedOnce = true
        val toast: Toast = Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT)
        customToast(toast)
        Handler().postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}