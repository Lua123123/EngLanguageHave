package com.example.englanguage.koin

import com.example.englanguage.model.vocabulary.Vocabulary
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("vocabulary/getVocabulary")
    fun getVocabulary(
        @Field("user_create") user_create: Int,
        @Field("search") search: String?
    ): Call<Vocabulary?>?
}