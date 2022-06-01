package com.example.englanguage.koin

import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.network.API
import retrofit2.Call
import javax.security.auth.callback.Callback

class KoinRepository(val apiInterface: ApiInterface) {
    fun getVocabulary() = apiInterface.getVocabulary(1, "")

}