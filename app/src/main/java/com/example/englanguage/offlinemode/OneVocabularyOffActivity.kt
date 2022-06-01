package com.example.englanguage.offlinemode

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.englanguage.R
import java.util.*

class OneVocabularyOffActivity : AppCompatActivity() {
    private var mTTS: TextToSpeech? = null
    private var isFront = true;
    private lateinit var front_anim: AnimatorSet
    private lateinit var behind_anim: AnimatorSet
    private var flip_layout: ConstraintLayout? = null
    private var cart_front: TextView? = null
    private var cart_behind: TextView? = null
    private lateinit var seek_bar_pitch: SeekBar
    private lateinit var seek_bar_speed: SeekBar
    private var mButtonSpeak: Button? = null
    private var word: String? = null
    private var mean: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_vocabulary_offline)
        supportActionBar?.hide()
        val bundle = intent.extras
        word = bundle!!.getString("word")
        mean = bundle.getString("mean")
        flip_layout = findViewById(R.id.flip_layout)
        cart_front = findViewById(R.id.cart_front)
        cart_behind = findViewById(R.id.cart_behind)
        seek_bar_pitch = findViewById(R.id.seek_bar_pitch)
        seek_bar_speed = findViewById(R.id.seek_bar_speed)
        mButtonSpeak = findViewById(R.id.mButtonSpeak)
        cart_front?.setText(word)
        cart_behind?.setText(mean)

        flipCart()
        callMTTS()
        mButtonSpeak?.setOnClickListener {
            word?.let { speak(it) }
        }
    }

    private fun flipCart() {
        val scale: Float? = this?.resources?.displayMetrics?.density
        cart_front?.cameraDistance = 8000 * scale!!
        cart_behind?.cameraDistance = 8000 * scale
        front_anim = AnimatorInflater.loadAnimator(this, R.animator.front_animator) as AnimatorSet
        behind_anim = AnimatorInflater.loadAnimator(this, R.animator.behind_animation) as AnimatorSet
        flip_layout?.setOnClickListener {
            if (isFront) {
                front_anim.setTarget(cart_front)
                behind_anim.setTarget(cart_behind)
                front_anim.start()
                behind_anim.start()
                isFront = false
            } else {
                front_anim.setTarget(cart_behind)
                behind_anim.setTarget(cart_front)
                behind_anim.start()
                front_anim.start()
                isFront = true
            }
        }
    }

    private fun callMTTS() {
        mTTS = TextToSpeech(this, object : TextToSpeech.OnInitListener {
            override fun onInit(i: Int) {
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS!!.setLanguage(Locale.ENGLISH)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported")
                    } else {
                        mButtonSpeak?.setEnabled(true)
                    }
                    run { Log.e("TTS", "Initialization failed") }
                }
            }
        })
    }

    private fun speak(word: String) {
        val text: String? = word
        if (text != null) {
        }
        var pitch = seek_bar_pitch.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1F
        var speed: Float = seek_bar_speed.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1F
        mTTS!!.setPitch(pitch) //pitch
        mTTS!!.setSpeechRate(speed) //speed
        mTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onDestroy() {
        if (mTTS != null) {
            mTTS?.stop()
            mTTS?.shutdown()
        }
        super.onDestroy()
    }
}