package com.example.englanguage.offlinemode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.englanguage.R
import com.example.englanguage.databinding.FragmentLoginOffBinding
import com.example.englanguage.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<RelativeLayout>(R.id.vocabularyOffline).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_vocabularyFragment)
        }
        view.findViewById<RelativeLayout>(R.id.topicOffline).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_topicFragment)
        }
        view.findViewById<RelativeLayout>(R.id.online_mode).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_loginFragmentOn)
        }
        view.findViewById<RelativeLayout>(R.id.flipcard).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_flipCardOffActivity)
        }
        view.findViewById<RelativeLayout>(R.id.speak).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_textToSpeechFragmentOff)
        }
        return view
    }

}