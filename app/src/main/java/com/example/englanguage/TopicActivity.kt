package com.example.englanguage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguage.adapter.ListTopicAdapter
import com.example.englanguage.model.topic.Success
import com.example.englanguage.viewmodel.LoginFragmentViewModel
import com.example.englanguage.viewmodel.TopicViewModel

class TopicActivity : AppCompatActivity() {
    private var imgBack: ImageView? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: ListTopicAdapter? = null
    private val postsList: MutableList<Success> = ArrayList()
    private val context: Context = this@TopicActivity
    private var topicViewModel: TopicViewModel = TopicViewModel(context)
    private var Authorization: String? = null
    private var loginViewModel: LoginFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)
        supportActionBar?.hide()
        val intent = intent
        val bundle = intent.extras
        Authorization = bundle!!.getString("Authorization")

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.setLayoutManager(layoutManager)
        adapter = ListTopicAdapter(postsList, context, Authorization)
        recyclerView?.setAdapter(adapter)

        val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(this.resources.getDrawable(R.drawable.divider_rcv))
        recyclerView?.addItemDecoration(itemDecoration)

        loginViewModel?.mAuthor?.observe(this) {
            Authorization = it
        }

        imgBack = findViewById(R.id.imgBack)
        imgBack?.setOnClickListener(View.OnClickListener {
            val intentMainActivity = Intent(this@TopicActivity, MainActivity::class.java)
            val bundle1 = Bundle()
            bundle1.putString("Authorization", Authorization)
            intentMainActivity.putExtras(bundle1)
            startActivity(intentMainActivity)
        })

        topicViewModel.mClickGetTopic(postsList, adapter)
    }

    override fun onBackPressed() {
        val intent5 = Intent(this@TopicActivity, MainActivity::class.java)
        val bundleFlipCard = Bundle()
        bundleFlipCard.putString("Authorization", Authorization)
        intent5.putExtras(bundleFlipCard)
        startActivity(intent5)
        super.onBackPressed()
    }
}