package com.skyzone18.ManMandirVidyalay.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skyzone18.ManMandirVidyalay.JsonActivity.HomeDetailActvity;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 2/13/2018.
 */

public class detailadpter extends RecyclerView.Adapter<detailadpter.ViewHolder> {
    List<Datum> detailst;
    Context contet;
    String imurl;
    public String Hmdate;

    public detailadpter(HomeDetailActvity homeDetailActvity, ArrayList<Datum> items) {
        this.detailst = items;
        this.contet = homeDetailActvity;
    }

    @Override
    public detailadpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_homedtail_data, null);


        return new detailadpter.ViewHolder(itemview);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final detailadpter.ViewHolder holder, final int position) {

        holder.webDesc.loadDataWithBaseURL(null, ((Datum) this.detailst.get(position)).getHomeworkNote(), "text/html", "utf-8", null);
        holder.tvSubject.setText(detailst.get(position).getSubjectName());
        holder.tvDate.setText(detailst.get(position).getHomeworkDate());
        holder.webDescs.loadDataWithBaseURL(null, ((Datum) detailst.get(position)).getDescription(), "text/html", "utf-8", null);
        Glide.with(contet).load(detailst.get(position).getImagePath() + detailst.get(position).getHomeworkImage())
                .crossFade()
                //.thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.logo)
                .into(holder.ivImage);
        holder.rl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_des);

                WebView webView = (WebView) dialog.findViewById(R.id.webView);
                webView.loadDataWithBaseURL(null, ((Datum) detailst.get(position)).getDescription(), "text/html", "utf-8", null);
                dialog.show();
            }
        });
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                TouchImageView imageView = (TouchImageView) dialog.findViewById(R.id.IMAGEID);
                Glide.with(contet).load(detailst.get(position).getImagePath() + detailst.get(position).getHomeworkImage())
                        .crossFade()
                        .thumbnail(0.5f)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.logo)
                        .into(imageView);

                dialog.show();

            }
        });
//        byte[] imageAsBytes = Base64.decode(imurl.getBytes(), Base64.DEFAULT);
//        holder.ivImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));


    }

    @Override
    public int getItemCount() {
        return detailst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, tvDate, tvSubject, tvDiv;
        CardView cardView;
        ImageView ivImage;
        WebView webDesc,webDescs;
        RelativeLayout rl_more;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvSubject = (TextView) itemView.findViewById(R.id.tvSubject);
            cardView = (CardView) itemView.findViewById(R.id.card_gallery);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            rl_more= (RelativeLayout) itemView.findViewById(R.id.rl_more);
            this.webDesc = (WebView) itemView.findViewById(R.id.webDesc);
            webDescs = (WebView) itemView.findViewById(R.id.webDescs);

        }
    }
}
