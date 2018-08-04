package com.skyzone18.ManMandirVidyalay.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.JsonActivity.TodayActivity;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevalt on 2/24/2018.
 */

public class todayeventadapter extends RecyclerView.Adapter<todayeventadapter.ViewHolder> {

    List<Datum> arralist;
    Context contet;
    public Dialog dialog;

    public todayeventadapter(TodayActivity todayActivity, ArrayList<Datum> items) {
        this.arralist = items;
        this.contet = todayActivity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_single_data, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(arralist.get(position).getEventName());
        holder.dis.setText(Html.fromHtml(arralist.get(position).getEventDescription()).toString());
       // holder.time.setText(arralist.get(position).getImagePath() + arralist.get(position).getNoticeImage() );
        Glide.with(contet).load(arralist.get(position).getImagePath()+arralist.get(position).getEventImage())
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                TouchImageView viewPager = (TouchImageView) dialog.findViewById(R.id.IMAGEID);
                Glide.with(contet).load(arralist.get(position).getImagePath()+ arralist.get(position).getEventImage())
                        .into(viewPager);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arralist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, dis;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            dis = (TextView) itemView.findViewById(R.id.news_dis);
            cardView = (CardView) itemView.findViewById(R.id.cardview);

        }
    }
}
