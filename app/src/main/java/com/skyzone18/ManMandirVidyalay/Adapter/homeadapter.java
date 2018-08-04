package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyzone18.ManMandirVidyalay.JsonActivity.HomeWorkActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.StanderActivity;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 2/2/2018.
 */

public class homeadapter extends RecyclerView.Adapter<homeadapter.ViewHolder> {
    List<Datum> homelist;
    Context contet;

    public homeadapter(HomeWorkActivity homeWorkActivity, ArrayList<Datum> homelist) {
        this.homelist = (List<Datum>) homelist;
        this.contet = (Context) homeWorkActivity;
    }
    @Override
    public homeadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_homework_data, null);

        return new homeadapter.ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(final homeadapter.ViewHolder holder, final int position) {
        final String titlee = homelist.get(position).getMediumName();


        //Log.e(">>>>>>", title);
        holder.name.setText(titlee);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String posii = homelist.get(position).getMediumId();
                String  name = homelist.get(position).getMediumName();
                //Toast.makeText(contet, pos, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), StanderActivity.class);
                i.putExtra("STDID", posii);
                i.putExtra("STDNAME", name);
                contet.startActivity(i);


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
