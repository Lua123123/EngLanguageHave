package com.example.englanguage.offlinemode

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.englanguage.R
import com.example.englanguage.SignUpActivity
import com.example.englanguage.databinding.FragmentLoginOnBinding
import com.example.englanguage.viewmodel.LoginFragmentViewModel

class LoginFragmentOn : Fragment() {

    private lateinit var binding: FragmentLoginOnBinding
    private var tv_dontHaveAnAccount: TextView? = null
    private var email: String? = null
    private var password: String? = null

    //SHARED PREFERENCE
    private var sharedPref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_on, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ConstraintLayout>(R.id.layout_offline).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragmentOn_to_loginFragmentOff)
        }
        view.findViewById<TextView>(R.id.tv_dontHaveAnAccount).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragmentOn_to_signUpActivity)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SHARED PREFERENCE
        sharedPref = context?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPref?.edit()

        tv_dontHaveAnAccount = view.findViewById<TextView>(R.id.tv_dontHaveAnAccount)
        tv_dontHaveAnAccount?.setOnClickListener(View.OnClickListener {
            val intentLogin = Intent(context, SignUpActivity::class.java)
            startActivity(intentLogin)
        })

        binding.btnGetLogin.setOnClickListener(View.OnClickListener {
            email = binding.edtEmail.text.toString().trim()
            password = binding.edtPassword.text.toString().trim()
            editor?.putString("email", email)
            editor?.putString("password", password)
            editor?.apply()
            val loginViewModel = LoginFragmentViewModel(context!!, email!!, password!!)

            if (binding.checkBoxLogin.isChecked()) {
                if (email != null && password != null && !email?.isEmpty()!! && !password?.isEmpty()!!) {
                    if (haveNetwork()) {
                        loginViewModel.mClickLogin()
                    } else if (!haveNetwork()) {
                        val toast =
                            Toast.makeText(
                                context,
                                "Please connect to internet or switch to offline mode!",
                                Toast.LENGTH_LONG
                            )
                        loginViewModel.customToast(toast)
                    }
                } else {
                    val toast =
                        Toast.makeText(
                            context,
                            "EMAIL OR PASSWORD IS EMPTY",
                            Toast.LENGTH_SHORT
                        )
                    loginViewModel.customToast(toast)
                }
            } else {
                val toast = Toast.makeText(
                    context,
                    "Please agree to the terms of the app!",
                    Toast.LENGTH_LONG
                )
                loginViewModel.customToast(toast)
            }

            //SHARED PREFERENCE
            if (!binding.checkRememberMe.isChecked()) {
                editor?.remove("email")
                editor?.remove("password")
                editor?.apply()
            }

        })

        //SHARED PREFERENCE
        binding.edtEmail.setText(sharedPref?.getString("email", ""))
        binding.edtPassword.setText(sharedPref?.getString("password", ""))

    }

    private fun haveNetwork(): Boolean {
        var have_WIFI = false
        var have_MobileData = false
        val connectivityManager =
            context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfos = connectivityManager.allNetworkInfo
        for (info in networkInfos) {
            if (info.typeName.equals("WIFI", ignoreCase = true)) if (info.isConnected) have_WIFI =
                true
            if (info.typeName.equals(
                    "MOBILE",
                    ignoreCase = true
                )
            ) if (info.isConnected) have_MobileData = true
        }
        return have_WIFI || have_MobileData
    }
}