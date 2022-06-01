package com.example.englanguage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguage.adapter.ListVocabularyOfTopicAdapter
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import android.widget.TextView
import com.example.englanguage.viewmodel.VocabularyOfTopicViewModel
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import java.util.ArrayList

class VocabularyOfTopicActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: ListVocabularyOfTopicAdapter? = null
    private val postsList: List<SuccessVocabulary> = ArrayList()
    private val context: Context = this@VocabularyOfTopicActivity
    private var tv_topic: TextView? = null
    private var imgBack: ImageView? = null
    private var vocabularyOfTopicViewModel: VocabularyOfTopicViewModel? = null
    private var Authorization: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_of_topic)
        supportActionBar!!.hide()
        val intent = intent
        val bundle = intent.getBundleExtra("data")
        val position = bundle!!.getInt("id")
        val nameTopic = bundle.getString("nameTopic")
        Authorization = bundle.getString("Authorization")
        tv_topic = findViewById(R.id.tv_topic)
        tv_topic?.setText(nameTopic)
        recyclerView = findViewById(R.id.rcv_vocabulary_of_topic)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.setLayoutManager(layoutManager)
        adapter = ListVocabularyOfTopicAdapter(postsList, context)
        recyclerView?.setAdapter(adapter)
        val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(this.resources.getDrawable(R.drawable.divider_rcv))
        recyclerView?.addItemDecoration(itemDecoration)
        imgBack = findViewById(R.id.imgBack)
        imgBack?.setOnClickListener(View.OnClickListener {
            val intentTopicActivity =
                Intent(this@VocabularyOfTopicActivity, TopicActivity::class.java)
            val bundle = Bundle()
            bundle.putString("Authorization", Authorization)
            intentTopicActivity.putExtras(bundle)
            startActivity(intentTopicActivity)
        })
        vocabularyOfTopicViewModel = VocabularyOfTopicViewModel(adapter!!,
            postsList as MutableList<SuccessVocabulary>, context)
        vocabularyOfTopicViewModel!!.mutableLiveDataClickGetVocabularyOfTopic(position)
    }

    override fun onBackPressed() {
        val intent5 = Intent(this@VocabularyOfTopicActivity, TopicActivity::class.java)
        val bundleFlipCard = Bundle()
        bundleFlipCard.putString("Authorization", Authorization)
        intent5.putExtras(bundleFlipCard)
        startActivity(intent5)
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}