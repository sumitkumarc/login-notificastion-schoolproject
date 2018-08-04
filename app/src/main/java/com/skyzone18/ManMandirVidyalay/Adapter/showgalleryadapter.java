package com.skyzone18.ManMandirVidyalay.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.JsonActivity.ShowPhotodActivity;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Rahul Patel on 2/3/2018.
 */

public class showgalleryadapter extends RecyclerView.Adapter<showgalleryadapter.ViewHolder> {
    List<Datum> gallrylist;
    Context contet;
    String imgg;
    String image_id;
    ViewPager viewPager;
    ImageView imageView;
    ApiService apiService;

    public Dialog dialog;

    public showgalleryadapter(ShowPhotodActivity showPhotodActivity, ArrayList<Datum> showgalleryist) {
        this.gallrylist = showgalleryist;
        this.contet = showPhotodActivity;
    }

    @Override
    public showgalleryadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_showgallery_data, null);

        return new showgalleryadapter.ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(final showgalleryadapter.ViewHolder holder, final int position) {
        apiService = RetroClient.getClient().create(ApiService.class);


        Glide.with(contet).load(gallrylist.get(position).getImagePath()+ gallrylist.get(position).getImageName())
                .into(holder.ivimage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(contet);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.full_screen_image);
                viewPager = (ViewPager) dialog.findViewById(R.id.pager_img);
                showpager();

                dialog.show();
            }

            private void showpager() {
                String image_id = ShowPhotodActivity.image_id;
                Call<Example> call = apiService.getAllPhoto(image_id);
                call.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                        List<Datum> items = response.body().getData();
                        if (response.body().getSuccess() == 0) {
                            dialog.cancel();
                            Toast.makeText(contet, "Sorry Item Not Found..", Toast.LENGTH_SHORT).show();
                        } else {
                            viewPager.setAdapter(new showpageradapter(contet, (ArrayList<Datum>) items));
                            viewPager.setCurrentItem(position);
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Log.e(">>>>>>", t.toString());
                        Toast.makeText(contet, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return gallrylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivimage, dis;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            ivimage = (ImageView) itemView.findViewById(R.id.show_img);
            cardView = (CardView) itemView.findViewById(R.id.show_fullimg);

        }
    }
}
