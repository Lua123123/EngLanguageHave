package com.example.englanguage.offlinemode.fragmentflipcardoff

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.englanguage.R
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.databinding.FragmentFlipCard5OffBinding
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import java.util.*

class FlipCardFragment5Off : Fragment() {
    lateinit var frontAnim: AnimatorSet
    lateinit var behindAnim: AnimatorSet
    private var isFront = true
    private lateinit var binding: FragmentFlipCard5OffBinding
    private var mTTS: TextToSpeech? = null
    private var mListVocabulary: List<SuccessVocabulary> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_flip_card5_off, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mListVocabulary = VocabularyDatabase.getInstance(context).vocabularyDAO()
            .getListVocabulary() as MutableList<SuccessVocabulary>

        flipCart(binding.flipLayout, binding.cartFront, binding.cartBehind)
        flipCart(binding.flipLayout1, binding.cartFront1, binding.cartBehind1)
        flipCart(binding.flipLayout2, binding.cartFront2, binding.cartBehind2)
        flipCart(binding.flipLayout3, binding.cartFront3, binding.cartBehind3)
        flipCart(binding.flipLayout4, binding.cartFront4, binding.cartBehind4)
        flipCart(binding.flipLayout5, binding.cartFront5, binding.cartBehind5)

        callMTTS()

        binding.mButtonSpeak.setOnClickListener {
            val text: String = mListVocabulary[24].word
            speak(text)
        }
        binding.mButtonSpeak1.setOnClickListener {
            val text1: String = mListVocabulary[25].word
            speak(text1)
        }
        binding.mButtonSpeak2.setOnClickListener {
            val text2: String = mListVocabulary[26].word
            speak(text2)
        }
        binding.mButtonSpeak3.setOnClickListener {
            val text3: String = mListVocabulary[27].word
            speak(text3)
        }
        binding.mButtonSpeak4.setOnClickListener {
            val text4: String = mListVocabulary[28].word
            speak(text4)
        }
        binding.mButtonSpeak5.setOnClickListener {
            val text5: String = mListVocabulary[29].word
            speak(text5)
        }

        binding.cartFront.text = mListVocabulary[24].word
        binding.cartBehind.text = mListVocabulary[24].mean
        binding.cartFront1.text = mListVocabulary[25].word
        binding.cartBehind1.text = mListVocabulary[25].mean
        binding.cartFront2.text = mListVocabulary[26].word
        binding.cartBehind2.text = mListVocabulary[26].mean
        binding.cartFront3.text = mListVocabulary[27].word
        binding.cartBehind3.text = mListVocabulary[27].mean
        binding.cartFront4.text = mListVocabulary[28].word
        binding.cartBehind4.text = mListVocabulary[28].mean
        binding.cartFront5.text = mListVocabulary[29].word
        binding.cartBehind5.text = mListVocabulary[29].mean
    }


    private fun callMTTS() {
        mTTS = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(i: Int) {
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS!!.setLanguage(Locale.ENGLISH)
                    if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                        binding.mButtonSpeak.setEnabled(true)
                    }
                    run {}
                }
            }
        })
    }

    private fun speak(text: String) {
        mTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    private fun flipCart(flipLayout: ConstraintLayout, cartFront: TextView, cartBehind: TextView) {
        val scale: Float? = activity?.resources?.displayMetrics?.density
        cartFront.cameraDistance = 8000 * scale!!
        cartBehind.cameraDistance = 8000 * scale

        frontAnim =
            AnimatorInflater.loadAnimator(activity, R.animator.front_animator) as AnimatorSet
        behindAnim =
            AnimatorInflater.loadAnimator(activity, R.animator.behind_animation) as AnimatorSet

        flipLayout.setOnClickListener {
            isFront = if (isFront) {
                frontAnim.setTarget(cartFront)
                behindAnim.setTarget(cartBehind)
                frontAnim.start()
                behindAnim.start()
                false
            } else {
                frontAnim.setTarget(cartBehind)
                behindAnim.setTarget(cartFront)
                behindAnim.start()
                frontAnim.start()
                true
            }
        }
    }
}