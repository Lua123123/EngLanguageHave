package com.example.englanguage.offlinemode.topicoffline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.example.englanguage.databinding.FragmentTopicBinding
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguage.R
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.model.topic.Success

class TopicFragment : Fragment() {
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: ListTopicAdapterOffline? = null
    private var postsList: MutableList<Success> = ArrayList()
    private lateinit var binding: FragmentTopicBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_topic, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ConstraintLayout>(R.id.imgBackOffline).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_topicFragment_to_mainFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListTopicAdapterOffline(postsList, context, "")
        layoutManager = LinearLayoutManager(context)
        binding.recyclerViewOffline.setLayoutManager(layoutManager)

        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        itemDecoration.setDrawable(this.resources.getDrawable(R.drawable.divider_rcv))
        binding.recyclerViewOffline?.addItemDecoration(itemDecoration)

        postsList = VocabularyDatabase.getInstance(context).topicDAO().getListTopic() as MutableList<Success>
        adapter?.setData(postsList)
        binding.recyclerViewOffline.setAdapter(adapter)
    }
}