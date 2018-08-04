package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.JsonActivity.ShowVideoActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.VideoyActivity;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 2/1/2018.
 */

public class videoadapter extends RecyclerView.Adapter<videoadapter.ViewHolder> {

    List<Datum> videolist;
    Context contet;
    private static final int RECOVERY_REQUEST = 1;

    public videoadapter(VideoyActivity videoyActivity, ArrayList<Datum> videolist) {
        this.videolist = videolist;
        this.contet = videoyActivity;
    }

    @Override
    public videoadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_video_data, null);

        return new videoadapter.ViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(final videoadapter.ViewHolder holder, final int position) {
        String main = videolist.get(position).getVideoCode();
        String url = "https://img.youtube.com/vi/" + main + "/0.jpg";
        Glide.with(contet).load(url).into(holder.ivImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String main = videolist.get(position).getVideoCode();
                main = main.replace("https://www.youtube.com/embed/", "");
                Intent intent = new Intent(contet, ShowVideoActivity.class);
                intent.putExtra("VIDEO", main);
                contet.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivImage;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            cardView = (CardView) itemView.findViewById(R.id.cardview);

        }
    }
}
