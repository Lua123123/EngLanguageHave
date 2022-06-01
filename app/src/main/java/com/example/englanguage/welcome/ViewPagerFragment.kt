package com.example.englanguage.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.englanguage.R

class ViewPagerFragment : Fragment() {
    private var viewPager: ViewPager2? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        val fragmentList = arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
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