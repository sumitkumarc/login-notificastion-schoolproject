package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyzone18.ManMandirVidyalay.JsonActivity.GalleryActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.ShowPhotodActivity;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 2/1/2018.
 */

public class galleryadapter extends RecyclerView.Adapter<galleryadapter.ViewHolder> {

    List<Datum> gallerylist;
    Context contet;

    public galleryadapter(GalleryActivity galleryActivity, ArrayList<Datum> gallerylist) {
        this.gallerylist = (List<Datum>) gallerylist;
        this.contet = (Context) galleryActivity;
    }

    @Override
    public galleryadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_gallery_data, null);


        return new galleryadapter.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final galleryadapter.ViewHolder holder, final int position) {

        final String title = gallerylist.get(position).getGalleryTitle();


//        Log.e(">>>>>>", title);
        holder.title.setText(title);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos = gallerylist.get(position).getCatId();
                String CATNAME = gallerylist.get(position).getGalleryTitle();
                //Toast.makeText(contet, pos, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), ShowPhotodActivity.class);
                i.putExtra("img", pos);
                i.putExtra("CATNAME", CATNAME);
                contet.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return gallerylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.gallery_title);
            cardView = (CardView) itemView.findViewById(R.id.card_gallery);

        }
    }

}
