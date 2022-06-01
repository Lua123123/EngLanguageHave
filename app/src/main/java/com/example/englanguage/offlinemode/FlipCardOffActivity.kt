package com.example.englanguage.offlinemode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.englanguage.R
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.databinding.ActivityFlipCardOffBinding
import com.example.englanguage.databinding.ActivityTextToSpeechBinding
import com.example.englanguage.model.vocabulary.SuccessVocabulary

class FlipCardOffActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityFlipCardOffBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card_off)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_flip_card_off)
        supportActionBar?.hide()
    }
}
