package com.example.englanguage.fragmentflipcard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.englanguage.R
import com.example.englanguage.welcome.ViewPagerAdapterWelcome

class ViewPagerFragmentFlip : Fragment() {
    private var viewPager: ViewPager2? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager_flip, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        val fragmentList = arrayListOf<Fragment>(
            FlipCardFragment1(),
            FlipCardFragment2(),
            FlipCardFragment3(),
            FlipCardFragment4(),
            FlipCardFragment5(),
            FlipCardFragment6()
        )

        val adapter = ViewPagerAdapterWelcome(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        viewPager?.adapter = adapter

        return view
    }

}