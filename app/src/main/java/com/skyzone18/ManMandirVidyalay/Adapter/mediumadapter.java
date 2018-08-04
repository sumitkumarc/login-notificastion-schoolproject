package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyzone18.ManMandirVidyalay.JsonActivity.MediumstdActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.StanderActivity;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 2/3/2018.
 */

public class mediumadapter extends RecyclerView.Adapter<mediumadapter.ViewHolder> {

    List<Datum> mediumlist;
    Context contet;

    public mediumadapter(MediumstdActivity mediumstdActivity, ArrayList<Datum> stdlist) {

        this.mediumlist = stdlist;
        this.contet = mediumstdActivity;
    }
    @Override
    public mediumadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_medum_data, null);

        return new mediumadapter.ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(final mediumadapter.ViewHolder holder, final int position) {
        final String title = mediumlist.get(position).getMediumName();

        holder.name.setText(title);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SECID = mediumlist.get(position).getMediumId();
                String STDNAME = mediumlist.get(position).getMediumName();
                //Toast.makeText(contet, pos, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), StanderActivity.class);
                i.putExtra("SECID", SECID);
                i.putExtra("STDNAME",STDNAME);
                contet.startActivity(i);


            }
        });



    }

    @Override
    public int getItemCount() {
        return  mediumlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, dis;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.std_name);
            cardView = (CardView) itemView.findViewById(R.id.card_std);

        }
    }
}
