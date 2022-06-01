package com.example.englanguage.offlinemode

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.englanguage.R
import com.example.englanguage.databinding.FragmentLoginOffBinding
import com.example.englanguage.viewmodel.LoginFragmentViewModel

class LoginFragmentOff : Fragment() {
    private lateinit var binding: FragmentLoginOffBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_off, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ConstraintLayout>(R.id.layout_online).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragmentOff_to_loginFragmentOn)
        }
        view.findViewById<Button>(R.id.btn_getOffline).setOnClickListener {
            val loginViewModel = LoginFragmentViewModel(context!!, "email", "password")
            if (binding.checkBoxLogin.isChecked()) {
                val bundle = bundleOf(
                    "state" to true
                )
                Navigation.findNavController(view).navigate(R.id.action_loginFragmentOff_to_mainFragment, bundle)
            } else {
                val toast = Toast.makeText(context, "Please agree to the terms of the app!", Toast.LENGTH_LONG)
                loginViewModel.customToast(toast)
            }
        }
        return view
    }
}
