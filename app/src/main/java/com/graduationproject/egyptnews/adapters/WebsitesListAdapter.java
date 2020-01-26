package com.graduationproject.egyptnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduationproject.egyptnews.R;
import com.graduationproject.egyptnews.models.details.EnvironmentalNewsModel;

import java.util.List;

public class WebsitesListAdapter  extends RecyclerView.Adapter<WebsitesListAdapter.WebsitesListViewHolder>{


    public WebsitesListAdapter(Context context, WebsitesListAdapter.onItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    //Create Parameters for Adapter Constructor
    private Context context;
    //Create Interface to Handle Click on RecyclerView Items
    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }
    private onItemClickListener onItemClickListener;

    @NonNull
    @Override
    public WebsitesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(context)
                .inflate(R.layout.websiteslist,parent,false);
        return setUpItemCLickListener(view);
    }

    private WebsitesListViewHolder setUpItemCLickListener(View view) {

        final WebsitesListViewHolder websitesListViewHolder=new WebsitesListViewHolder(view);
        websitesListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,websitesListViewHolder.getAdapterPosition());
            }
        });
        return websitesListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WebsitesListViewHolder holder, int position) {

        holder.tvWebsiteName.setText(EnvironmentalNewsModel.getWebsiteTitleList().get(position).toString());
        holder.imgWebsite.setImageResource(EnvironmentalNewsModel.getWebsiteImageList().get(position));

    }

    @Override
    public int getItemCount() {
        return EnvironmentalNewsModel.getWebsiteImageList().size();
    }



    public class WebsitesListViewHolder extends RecyclerView.ViewHolder{

        //Declare RecyclerViewItems
        TextView tvWebsiteName;
        ImageView imgWebsite;

        public WebsitesListViewHolder(@NonNull View itemView) {
            super(itemView);

            //Initialize RecyclerView Items
            tvWebsiteName = itemView.findViewById(R.id.tvWebsiteName);
            imgWebsite = itemView.findViewById(R.id.iv1);
        }
    }
}
