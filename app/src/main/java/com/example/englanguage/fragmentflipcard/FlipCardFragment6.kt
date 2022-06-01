package com.example.englanguage.fragmentflipcard

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.englanguage.R
import com.example.englanguage.databinding.FragmentFlipCard6Binding
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.viewmodel.FlipViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FlipCardFragment6 : Fragment() {
    lateinit var frontAnim: AnimatorSet
    lateinit var behindAnim: AnimatorSet
    var isFront = true;
    private lateinit var binding: FragmentFlipCard6Binding
    var vocabulary: Vocabulary? = null
    private var mTTS: TextToSpeech? = null
    private val flipViewModel: FlipViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flip_card6, container, false)
        binding.lifecycleOwner = this
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.findViewById<ConstraintLayout>(R.id.layoutBack).setOnClickListener {
            viewPager?.currentItem = 4
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flipCart(binding.flipLayout, binding.cartFront, binding.cartBehind)
        flipCart(binding.flipLayout1, binding.cartFront1, binding.cartBehind1)
        flipCart(binding.flipLayout2, binding.cartFront2, binding.cartBehind2)
        flipCart(binding.flipLayout3, binding.cartFront3, binding.cartBehind3)
        flipCart(binding.flipLayout4, binding.cartFront4, binding.cartBehind4)
        flipCart(binding.flipLayout5, binding.cartFront5, binding.cartBehind5)

        callMTTS()

        binding.mButtonSpeak.setOnClickListener {
            val text: String? = flipViewModel.mVocabulary.value?.success?.get(30)?.word
            speak(text!!) }
        binding.mButtonSpeak1.setOnClickListener {
            val text1: String? = flipViewModel.mVocabulary.value?.success?.get(31)?.word
            speak(text1!!) }
        binding.mButtonSpeak2.setOnClickListener {
            val text2: String? = flipViewModel.mVocabulary.value?.success?.get(32)?.word
            speak(text2!!) }
        binding.mButtonSpeak3.setOnClickListener {
            val text3: String? = flipViewModel.mVocabulary.value?.success?.get(33)?.word
            speak(text3!!) }
        binding.mButtonSpeak4.setOnClickListener {
            val text4: String? = flipViewModel.mVocabulary.value?.success?.get(34)?.word
            speak(text4!!) }
        binding.mButtonSpeak5.setOnClickListener {
            val text5: String? = flipViewModel.mVocabulary.value?.success?.get(35)?.word
            speak(text5!!) }
        flipViewModel.mutableLiveDataClickGetVocabulary()

        flipViewModel.mVocabulary.observe(this)
        {
            binding.cartFront.text = it!!.success?.get(30)?.word
            binding.cartBehind.text = it.success?.get(30)?.mean
            binding.cartFront1.text = it.success?.get(31)?.word
            binding.cartBehind1.text = it.success?.get(31)?.mean
            binding.cartFront2.text = it.success?.get(32)?.word
            binding.cartBehind2.text = it.success?.get(32)?.mean
            binding.cartFront3.text = it.success?.get(33)?.word
            binding.cartBehind3.text = it.success?.get(33)?.mean
            binding.cartFront4.text = it.success?.get(34)?.word
            binding.cartBehind4.text = it.success?.get(34)?.mean
            binding.cartFront5.text = it.success?.get(35)?.word
            binding.cartBehind5.text = it.success?.get(35)?.mean
        }
    }

    private fun callMTTS() {
        mTTS = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(i: Int) {
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS!!.setLanguage(Locale.ENGLISH)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {}
                    else {
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

        frontAnim = AnimatorInflater.loadAnimator(activity, R.animator.front_animator) as AnimatorSet
        behindAnim = AnimatorInflater.loadAnimator(activity, R.animator.behind_animation) as AnimatorSet

        flipLayout.setOnClickListener {
            if (isFront) {
                frontAnim.setTarget(cartFront)
                behindAnim.setTarget(cartBehind)
                frontAnim.start()
                behindAnim.start()
                isFront = false
            } else {
                frontAnim.setTarget(cartBehind)
                behindAnim.setTarget(cartFront)
                behindAnim.start()
                frontAnim.start()
                isFront = true
            }
        }
    }
}