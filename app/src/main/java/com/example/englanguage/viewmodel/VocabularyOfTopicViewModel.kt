package com.example.englanguage.viewmodel

import android.content.Context
import com.example.englanguage.adapter.ListVocabularyOfTopicAdapter
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import androidx.lifecycle.ViewModel
import com.example.englanguage.network.API
import com.example.englanguage.model.vocabulary.Vocabulary
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class VocabularyOfTopicViewModel(
    private val adapter: ListVocabularyOfTopicAdapter,
    postsList: MutableList<SuccessVocabulary>,
    context: Context
) : ViewModel() {

    private var postsList: MutableList<SuccessVocabulary> = ArrayList()
    private val context: Context

    private var vocabulary: Vocabulary? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun mutableLiveDataClickGetVocabularyOfTopic(position: Int) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetVocabularyOfTopic(position)
        }
    }

    fun clickGetVocabularyOfTopic(position: Int) {
        API.api.getVocabularyOfTopic(1, position).enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                val vocabulary = response.body()
                for (i in vocabulary!!.success.indices) {
                    val successVocabulary = SuccessVocabulary(
                        0,
                        vocabulary.success[i].word,
                        vocabulary.success[i].mean,
                        vocabulary.success[i].example
                    )

                    postsList.add(successVocabulary)
                }
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
                Toast.makeText(context, "LOAD DATA VOCABULARY OF TOPIC FAILED", Toast.LENGTH_SHORT).show()
            }
        })
    }

    init {
        this.postsList = postsList
        this.context = context
    }
}