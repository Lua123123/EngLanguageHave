package com.example.englanguage.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.englanguage.model.login.UserLogin
import com.example.englanguage.network.API
import com.example.englanguage.model.login.Login
import android.widget.Toast
import android.content.Intent
import android.graphics.Color
import com.example.englanguage.MainActivity
import android.os.Bundle
import android.widget.TextView
import android.view.Gravity
import com.example.englanguage.R
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragmentViewModel(
    private val context: Context,
    private val email: String,
    private val password: String
) : ViewModel() {
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val remember_me = true
    var mAuthor = MutableLiveData<String>()

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun mClickLogin() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickLogin()
        }
    }

    fun clickLogin() {
        val userLogin = UserLogin(email, password, remember_me)
        API.api.getUsers(userLogin).enqueue(object : Callback<Login?> {
            override fun onResponse(call: Call<Login?>, response: Response<Login?>) {
                val login = response.body()!!
                val status = login.status.toString()
                if (status == "true") {
                    val toast = Toast.makeText(context, "LOGIN SUCCESSFULLY", Toast.LENGTH_SHORT)
                    customToast(toast)
                    val intent = Intent(context, MainActivity::class.java)
                    val bundle = Bundle()
                    val tokenType = login.data.token_type.trim { it <= ' ' }
                    val accessToken = login.data.access_token.trim { it <= ' ' }
                    val Authorization = "$tokenType $accessToken"
                    bundle.putString("Authorization", Authorization)
                    intent.putExtras(bundle)
                    mAuthor.postValue(Authorization) /////////////////////////////////////
                    ///////////////////////////////////////////////////////////
                    context.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Login?>, t: Throwable) {
                val toast = Toast.makeText(context, "LOGIN FAILED!", Toast.LENGTH_SHORT)
                customToast(toast)
            }
        })
    }

    fun customToast(toast: Toast) {
        val toastView = toast.view
        val toastMessage = toastView!!.findViewById<TextView>(android.R.id.message)
        toastMessage.textSize = 13f
        toastMessage.setTextColor(Color.YELLOW)
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        toastMessage.gravity = Gravity.CENTER
        toastMessage.compoundDrawablePadding = 4
        toastView.setBackgroundColor(Color.BLACK)
        toastView.setBackgroundResource(R.drawable.bg_toast)
        toast.show()
    }
}