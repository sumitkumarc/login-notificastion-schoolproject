package com.skyzone18.ManMandirVidyalay.Adapter;

import android.app.Dialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.JsonActivity.StudentResultActivity;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevalt on 4/17/2018.
 */

public class StudentResultAdapter extends RecyclerView.Adapter<StudentResultAdapter.ViewHolder> {
    List<Datum> arralist;
    StudentResultActivity contet;
    public Dialog dialog;

    public StudentResultAdapter(StudentResultActivity todayActivity, ArrayList<Datum> items) {
        this.arralist = items;
        this.contet = todayActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_schoolresults, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.stu_name.setText(arralist.get(position).getName());
        holder.stu_std.setText("વર્ગ :- " + arralist.get(position).getStandard());
        holder.stu_per.setText("ટકાવારી :- " + arralist.get(position).getPercentage()+"%");
        holder.stu_year.setText("વર્ષ :- " + arralist.get(position).getYear());
        Glide.with(contet).load(arralist.get(position).getImagePath()+ arralist.get(position).getImage())
                .into(holder.sta_img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                TouchImageView viewPager = (TouchImageView) dialog.findViewById(R.id.IMAGEID);
                Glide.with(contet).load(arralist.get(position).getImagePath()+ arralist.get(position).getImage())
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

        ImageView sta_img;
        CardView cardView;
        TextView stu_name,stu_std,stu_per,stu_year;

        public ViewHolder(View itemView) {
            super(itemView);
            sta_img = (ImageView) itemView.findViewById(R.id.sta_img);
            cardView = (CardView) itemView.findViewById(R.id.imgcard);
            stu_name = (TextView)itemView.findViewById(R.id.stu_name);
            stu_std =(TextView)itemView.findViewById(R.id.stu_std);
            stu_per= (TextView)itemView.findViewById(R.id.stu_per);
            stu_year= (TextView)itemView.findViewById(R.id.stu_year);


        }
    }
}
