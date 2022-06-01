package com.example.englanguage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.englanguage.fragmentflipcard.FlipCardFragment1
import com.example.englanguage.fragmentflipcard.FlipCardFragment2
import com.example.englanguage.fragmentflipcard.FlipCardFragment3
import com.example.englanguage.fragmentflipcard.FlipCardFragment4
import com.example.englanguage.fragmentflipcard.FlipCardFragment5
import com.example.englanguage.fragmentflipcard.FlipCardFragment6

class ViewPagerAdapterFlipCard(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FlipCardFragment1()
            1 -> FlipCardFragment2()
            2 -> FlipCardFragment3()
            3 -> FlipCardFragment4()
            4 -> FlipCardFragment5()
            5 -> FlipCardFragment6()
            else -> FlipCardFragment1()
        }
    }

    override fun getCount(): Int {
        return 6
    }
}