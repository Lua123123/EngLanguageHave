package com.example.englanguage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.viewmodel.FlipViewModel
import com.example.englanguage.viewmodel.LoginFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FlipCardActivity : AppCompatActivity() {

    private var context: Context = this@FlipCardActivity
    private lateinit var mViewPager: ViewPager
    private var navigationView: BottomNavigationView? = null
    private var imgBack: ImageView? = null
    private var content_frame: FrameLayout? = null
    private var layoutConstraint: ConstraintLayout? = null
    private lateinit var successVocabulary: SuccessVocabulary
    private var vocabulary: Vocabulary? = null
    private var authorization: String? = null
    private var loginViewModel: LoginFragmentViewModel? = null
    private val flipViewModel: FlipViewModel = FlipViewModel(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card)
        supportActionBar?.hide()
        val intent = intent
        val bundle = intent.extras
        authorization = bundle!!.getString("Authorization")

        loginViewModel?.mAuthor?.observe(this) {
            authorization = it
        }

        imgBack = findViewById(R.id.imgBack)
        imgBack?.setOnClickListener {
            var intentVocabulary = Intent(context, MainActivity::class.java)
            val bundleFlipCard = Bundle()
            bundleFlipCard.putString("Authorization", authorization)
            intentVocabulary.putExtras(bundleFlipCard)
            startActivity(intentVocabulary)
        }
    }

    override fun onBackPressed() {
        val intent5 = Intent(this@FlipCardActivity, MainActivity::class.java)
        val bundleFlipCard = Bundle()
        bundleFlipCard.putString("Authorization", authorization)
        intent5.putExtras(bundleFlipCard)
        startActivity(intent5)
        super.onBackPressed()
    }
}