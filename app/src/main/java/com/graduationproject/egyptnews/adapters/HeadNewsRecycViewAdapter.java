package com.graduationproject.egyptnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.models.headNews.Articles;
import com.graduationproject.egyptnews.views.activities.EntryActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HeadNewsRecycViewAdapter extends RecyclerView.Adapter<HeadNewsRecycViewAdapter.HeadNewsViewHolder> {

    //Create Parameters for Adapter Constructor
    private List<Articles> articlesList;
    private Context context;
    //Create Interface to Handle Click on RecyclerView Items
    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }
    private onItemClickListener onItemClickListener;

    //Generate constructor for the previous parameters
    public HeadNewsRecycViewAdapter(List<Articles> teamsList, Context context, HeadNewsRecycViewAdapter.onItemClickListener onItemClickListener) {
        this.articlesList = teamsList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HeadNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(context)
                .inflate(R.layout.news_rv,parent,false);
        return setUpItemCLickListener(view);
    }

    @NotNull
    private HeadNewsViewHolder setUpItemCLickListener(View view) {
        final HeadNewsViewHolder headNewsViewHolder=new HeadNewsViewHolder(view);
        headNewsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,headNewsViewHolder.getAdapterPosition());
            }
        });
        return headNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HeadNewsViewHolder holder, int position) {

        Articles articles = articlesList.get(position);
        holder.tvNewsTitle.setText(articles.getNewsTitle());
        holder.tvPublishWebsite.setText(articles.getNewsSources().getSourceName());
        holder.tvPublishDate.setText(articles.getPublishDate());

        //Make Website as a hyberlink
        //goToWebsite(holder);

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }


    public void filterlist(ArrayList<Articles> filteredList)
    {
        articlesList=filteredList;
        notifyDataSetChanged();
    }


    //View Holder Class For RecyclerView Adapter
    public class HeadNewsViewHolder extends RecyclerView.ViewHolder{

        //Declare RecyclerViewItems
        TextView tvNewsTitle;
        TextView tvPublishWebsite;
        TextView tvPublishDate;

        public HeadNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            //Initialize RecyclerView Items
            tvNewsTitle = itemView.findViewById(R.id.tvNewsTitle);
            tvPublishWebsite = itemView.findViewById(R.id.tvPublisherWebsite);
            tvPublishDate = itemView.findViewById(R.id.tvPublishDate);

        }
    }
}

