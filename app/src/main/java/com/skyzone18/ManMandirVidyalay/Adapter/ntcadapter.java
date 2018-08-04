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
import com.skyzone18.ManMandirVidyalay.JsonActivity.NoticeActivity;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 1/30/2018.
 */

public class ntcadapter extends RecyclerView.Adapter<ntcadapter.ViewHolder> {

    List<Datum> ntclist;
    Context contet;
    String monthname;
    String day;

    public ntcadapter(NoticeActivity notic, ArrayList<Datum> ntclist) {
        this.ntclist = (List<Datum>) ntclist;
        this.contet = (Context) notic;
    }

    @Override
    public ntcadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_ntc_data, null);


        return new ViewHolder(itemview);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ntcadapter.ViewHolder holder, final int position) {
        holder.tv_title.setText(ntclist.get(position).getNoticeName());
        Glide.with(contet).load(ntclist.get(position).getImagePath() + ntclist.get(position).getNoticeImage())
                .into(holder.imageView);
        holder.name.setText(Html.fromHtml(ntclist.get(position).getNoticeDescription()));
        holder.rl_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_des);

                WebView webView = (WebView) dialog.findViewById(R.id.webView);
                webView.loadDataWithBaseURL(null, ((Datum) ntclist.get(position)).getNoticeDescription(), "text/html", "utf-8", null);
                dialog.show();
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                TouchImageView viewPager = (TouchImageView) dialog.findViewById(R.id.IMAGEID);
                Glide.with(contet).load(ntclist.get(position).getImagePath()+ ntclist.get(position).getNoticeImage())
                        .into(viewPager);
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return ntclist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, tv_title;
        TextView tvday, tvmonth;
        ImageView imageView;
        CardView cardView;
        RelativeLayout rl_more;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            name = (TextView) itemView.findViewById(R.id.news_dis);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            tvmonth = (TextView) itemView.findViewById(R.id.tv_month);
            rl_more= (RelativeLayout) itemView.findViewById(R.id.rl_more);
        }
    }
}