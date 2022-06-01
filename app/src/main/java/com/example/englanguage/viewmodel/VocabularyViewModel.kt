package com.example.englanguage.viewmodel

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.adapter.ListVocabularyAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import android.widget.ProgressBar
import com.example.englanguage.model.vocabulary.Vocabulary
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.englanguage.R
import android.view.WindowManager
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.englanguage.network.API
import com.example.englanguage.model.vocabulary.DeleteVocabulary
import android.widget.Toast
import android.widget.EditText
import com.example.englanguage.model.vocabulary.SuccessInsertVocabulary
import com.example.englanguage.database.VocabularyDatabase
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class VocabularyViewModel() : ViewModel() {
    private val mListVocabularyLiveData: MutableLiveData<List<SuccessVocabulary>>? = null
    private var mListVocabulary: MutableList<SuccessVocabulary> = ArrayList()
    private var vocabulary: Vocabulary? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
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

    fun mOpenDialogDeleteVocabulary(gravity: Int, Authorization: String?, position: Int, swipeRefreshLayout: SwipeRefreshLayout?, context: Context) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            openDialogDeleteVocabulary(gravity, Authorization, position, swipeRefreshLayout, context)
        } }

    fun openDialogDeleteVocabulary(
        gravity: Int,
        Authorization: String?,
        position: Int,
        swipeRefreshLayout: SwipeRefreshLayout?,
        context: Context
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_custom_delete)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val tv_vocabulary = dialog.findViewById<TextView>(R.id.tv_vocabulary)
        val btnCancel = dialog.findViewById<ConstraintLayout>(R.id.btn_cancel)
        val btnConfirm = dialog.findViewById<ConstraintLayout>(R.id.btn_confirm)
        val word = vocabulary!!.success[position].word
        tv_vocabulary.text = word
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            clickDeleteVocabulary(Authorization, word, position, swipeRefreshLayout, context)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun clickDeleteVocabulary(
        Authorization: String?,
        word: String?,
        position: Int,
        swipeRefreshLayout: SwipeRefreshLayout?,
        context: Context
    ) {
        API.api.deleteVocabulary(Authorization, 1, word)
            .enqueue(object : Callback<DeleteVocabulary?> {
                override fun onResponse(
                    call: Call<DeleteVocabulary?>,
                    response: Response<DeleteVocabulary?>
                ) {
                    val toast = Toast.makeText(
                        context,
                        "Deleted successfully, please reload the page!",
                        Toast.LENGTH_LONG
                    )
                    customToast(toast)
                }

                override fun onFailure(call: Call<DeleteVocabulary?>, t: Throwable) {
                    val toast =
                        Toast.makeText(context, "DELETE VOCABULARY FAILED!", Toast.LENGTH_SHORT)
                    customToast(toast)
                }
            })
    }

    fun mOpenDialogInsertVocabulary(gravity: Int, Authorization: String?, context: Context) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            openDialogInsertVocabulary(gravity, Authorization, context)
        }
    }

    fun openDialogInsertVocabulary(gravity: Int, Authorization: String?, context: Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_custom)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val edtWord = dialog.findViewById<EditText>(R.id.edt_word)
        val edtMean = dialog.findViewById<EditText>(R.id.edt_mean)
        val edtExample = dialog.findViewById<EditText>(R.id.edt_example)
        val btnCancel = dialog.findViewById<ConstraintLayout>(R.id.btn_cancel)
        val btnConfirm = dialog.findViewById<ConstraintLayout>(R.id.btn_confirm)
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            val word = edtWord.text.toString().trim { it <= ' ' }
            val mean = edtMean.text.toString().trim { it <= ' ' }
            val example = edtExample.text.toString().trim { it <= ' ' }
            clickInsertVocabulary(Authorization, word, mean, example, context)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun clickInsertVocabulary(
        Authorization: String?,
        word: String?,
        mean: String?,
        example: String?,
        context: Context
    ) {
        API.api.insertVocabulary(Authorization, word, mean, "image_path", example, 2, 1)
            .enqueue(object : Callback<SuccessInsertVocabulary?> {
                override fun onResponse(
                    call: Call<SuccessInsertVocabulary?>,
                    response: Response<SuccessInsertVocabulary?>
                ) {
                    val toast = Toast.makeText(
                        context,
                        "Insert successfully, please reload the page!",
                        Toast.LENGTH_SHORT
                    )
                    customToast(toast)
                }

                override fun onFailure(call: Call<SuccessInsertVocabulary?>, t: Throwable) {
                    val toast =
                        Toast.makeText(context, "INSERT VOCABULARY FAILED!", Toast.LENGTH_SHORT)
                    customToast(toast)
                }
            })
    }

    fun mClickGetVocabulary(recyclerView: RecyclerView, adapter: ListVocabularyAdapter, context: Context, search: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetVocabulary(recyclerView, adapter, context, search)
        } }

    fun clickGetVocabulary(recyclerView: RecyclerView, adapter: ListVocabularyAdapter, context: Context, search: String): List<SuccessVocabulary>? {
        API.api.getVocabulary(1, search, "30").enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                vocabulary = response.body()
                for (i in vocabulary!!.success.indices) {
                    val successVocabulary = SuccessVocabulary(
                        0, vocabulary!!.success[i].word,
                        vocabulary!!.success[i].mean, vocabulary!!.success[i].example
                    )
                    mListVocabulary.add(successVocabulary)
                    adapter.setData(mListVocabulary)
                    recyclerView.adapter = adapter

                    if (VocabularyDatabase.getInstance(context).vocabularyDAO()
                            .getListVocabulary().size <= mListVocabulary.size
                    ) {
                        val strWord = vocabulary!!.success[i].word
                        val strMean = vocabulary!!.success[i].mean
                        val strExample = vocabulary!!.success[i].example
                        val successDataRoom = SuccessVocabulary(0, strWord, strMean, strExample)
                        VocabularyDatabase.getInstance(context).vocabularyDAO()
                            .insertVocabulary(successDataRoom)
                    }
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
                val toast =
                    Toast.makeText(context, "SHOW LIST VOCABULARY FAILED", Toast.LENGTH_SHORT)
                customToast(toast)
            }
        })
        return null
    }

    private fun customToast(toast: Toast) {
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