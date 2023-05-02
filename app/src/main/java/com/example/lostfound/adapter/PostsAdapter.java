package com.example.lostfound.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostfound.DetailActivity;
import com.example.lostfound.R;
import com.example.lostfound.entities.PostItem;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<PostItem> postItemList;
    private Context context;

    public PostsAdapter(List<PostItem> postItemList) {
        this.postItemList = postItemList;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_display_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        holder.postTitle.setText(postItemList.get(position).getDescription());
        holder.postDate.setText(postItemList.get(position).getDate());
        if (postItemList.get(position).isPostType()) {
            // found
            holder.cardLayout.setBackgroundColor(context.getColor(R.color.found_yellow));
        } else {
            holder.cardLayout.setBackgroundColor(context.getColor(R.color.lost_red));
        }

        holder.cardLayout.setOnClickListener(view -> {
            Intent toDetailIntent = new Intent(context, DetailActivity.class);
            toDetailIntent.putExtra("post_info", postItemList.get(position));
            context.startActivity(toDetailIntent);
        });
    }

    @Override
    public int getItemCount() {
        return postItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView postTitle, postDate;
        private LinearLayout cardLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = itemView.findViewById(R.id.post_title);
            postDate = itemView.findViewById(R.id.post_date);
            cardLayout = itemView.findViewById(R.id.card_layout);
        }
    }
}
