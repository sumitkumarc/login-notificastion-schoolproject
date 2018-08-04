package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.skyzone18.ManMandirVidyalay.JsonActivity.HomeDetailActvity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.StanderActivity;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevalt on 5/7/2018.
 */

public class StandardAdapter extends RecyclerView.Adapter<StandardAdapter.ViewHolder> {

    List<Datum> homelist;
    StanderActivity dactivity;

    public StandardAdapter(StanderActivity standerActivity, ArrayList<Datum> items) {
       this.homelist =items;
       this.dactivity =standerActivity;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_homework_data, null);

        return new ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String titlee = homelist.get(position).getStdName();


        //Log.e(">>>>>>", title);
        holder.name.setText(titlee);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String STDNAME = homelist.get(position).getStdName();
                String STDID = homelist.get(position).getStdId();

                //Toast.makeText(contet, pos, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), HomeDetailActvity.class);
                i.putExtra("STDNAME", STDNAME);
                i.putExtra("STDID", STDID);
                dactivity.startActivity(i);


            }
        });

    }

    @Override
    public int getItemCount() {
        return homelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, dis;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.midum_name);
            cardView = (CardView) itemView.findViewById(R.id.card_midum);

        }
    }
}
