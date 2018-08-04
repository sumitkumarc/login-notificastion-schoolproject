package com.skyzone18.ManMandirVidyalay.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.JsonActivity.NewsActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.TodayActivity;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 1/30/2018.
 */

public class newsadapter extends RecyclerView.Adapter<newsadapter.ViewHolder> {

    List<Datum> newslist;
    Context contet;
    public Dialog dialog;

    public newsadapter(TodayActivity todayActivity, ArrayList<Datum> items) {
        this.newslist = items;
        this.contet = (Context) todayActivity;
    }

    public newsadapter(NewsActivity newsActivity, List<Datum> items) {
        this.newslist =  items;
       this.contet = newsActivity;
    }

//    public newsadapter(TodayActivity newsActivity, ArrayList<Contact> newslist) {
//        this.newslist = (List<Contact>) newslist;
//        this.contet = (Context) newsActivity;
//    }



    @Override
    public newsadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_single_data, null);


        return new newsadapter.ViewHolder(itemview);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final newsadapter.ViewHolder holder, final int position) {

        Datum dis = newslist.get(position);
        holder.title.setText(newslist.get(position).getNewsTitle());
        holder.dis.setText(Html.fromHtml(dis.getNewsDescription()).toString());
        Glide.with(contet).load(newslist.get(position).getImagePath()+newslist.get(position).getNewsImage())
                .into(holder.imageView);
        holder.rl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_des);

                WebView webView = (WebView) dialog.findViewById(R.id.webView);
                webView.loadDataWithBaseURL(null, ((Datum) newslist.get(position)).getNewsDescription(), "text/html", "utf-8", null);
                dialog.show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                TouchImageView imageView = (TouchImageView) dialog.findViewById(R.id.IMAGEID);

                Glide.with(contet)
                        .load(newslist.get(position).getImagePath()+newslist.get(position).getNewsImage())
                        .into(imageView);

                dialog.show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return newslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, dis;
        ImageView imageView;
        CardView cardView;
        RelativeLayout rl_more;
        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.news_title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            dis = (TextView) itemView.findViewById(R.id.news_dis);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            rl_more= (RelativeLayout) itemView.findViewById(R.id.rl_more);

        }
    }
}
