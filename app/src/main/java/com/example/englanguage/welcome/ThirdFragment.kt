package com.example.englanguage.welcome

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.englanguage.R

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_third, container, false)
        view.findViewById<Button>(R.id.finish).setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginActivity)
            onBoardingFinished()
        }
        return view
    }

    //HÀM LƯU TRẠNG THÁI ĐẦU TIÊN ĐÃ VÀO APP, LẦN SAU SẼ KHÔNG HIỂN THỊ NỮA
    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}