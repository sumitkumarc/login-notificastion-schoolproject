package com.skyzone18.ManMandirVidyalay.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;


import java.util.List;

/**
 * Created by Rahul Patel on 1/30/2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    List<Datum> arralist;
    Context contet;
    public Dialog dialog;

    public adapter(Context staffActivity, List<Datum> stflist) {
        this.arralist = stflist;
        this.contet = staffActivity;
    }

    @Override
    public adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_staff_data, null);


        return new ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(final adapter.ViewHolder holder, final int position) {
        holder.name.setText(arralist.get(position).getName());
        holder.quli.setText(arralist.get(position).getEducation());
        holder.sttafpos.setText(arralist.get(position).getDesignation().toString());
        Log.d("DATAMAIN", "DATAMAINURL" + arralist.get(position).getImagePath()+arralist.get(position).getImage());
        Glide.with(contet).load(arralist.get(position).getImagePath()+arralist.get(position).getImage())
                .placeholder(R.drawable.nopicstaff).into(holder.imageView);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.IMAGEID);

                Glide.with(contet).load(arralist.get(position).getImagePath()+ arralist.get(position).getImage())
                        .placeholder(R.drawable.nopicstaff).into(imageView);

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arralist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, quli, sttafpos;
        ImageView imageView;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.sta_name);
            quli = (TextView) itemView.findViewById(R.id.sta_quli);
            imageView = (ImageView) itemView.findViewById(R.id.sta_img);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            sttafpos = (TextView) itemView.findViewById(R.id.txtstafposition);

        }
    }
}
