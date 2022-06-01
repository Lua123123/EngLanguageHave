package com.example.englanguage.offlinemode.topicoffline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englanguage.R;
import com.example.englanguage.VocabularyOfTopicActivity;
import com.example.englanguage.model.topic.Success;

import java.util.List;

public class ListTopicAdapterOffline extends RecyclerView.Adapter<ListTopicAdapterOffline.ViewHolder> {
    private List<Success> postsList;
    private Context context;
    private String Authorization;

    public ListTopicAdapterOffline(List<Success> postsList, Context context, String Authorization) {
        this.postsList = postsList;
        this.context = context;
        this.Authorization = Authorization;
    }

    public void setData(List<Success> postsList) {
        this.postsList = postsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_topic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Success success = postsList.get(position);
        if (success == null) {
            return;
        }
        String nameTopic = postsList.get(position).getName();
        int id = postsList.get(position).getId();
        holder.tvTitle.setText(success.getName());
        holder.tvMount.setText(String.valueOf(success.getSoluong()));
        holder.item_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, VocabularyOfTopicActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", id);
//                bundle.putString("nameTopic", nameTopic);
//                bundle.putString("Authorization", Authorization);
//                intent.putExtra("data", bundle);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (postsList != null) {
            return postsList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvMount;
        private LinearLayout item_topic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.topic);
            tvMount = itemView.findViewById(R.id.amount);
            item_topic = itemView.findViewById(R.id.item_topic);
        }
    }
}
