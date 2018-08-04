package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 2/9/2018.
 */

public class showpageradapter extends PagerAdapter {

    List<Datum> pagerlist;
    Context context;
    LayoutInflater mLayoutInflater;

    public showpageradapter(Context contet, ArrayList<Datum> items) {
        this.pagerlist = items;
        this.context = contet;
        mLayoutInflater = (LayoutInflater) contet.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return pagerlist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager, container, false);

        final TouchImageView imageView = (TouchImageView) itemView.findViewById(R.id.category_image1);

//        Glide.with(context).load(pagerlist.get(position).getImagepath()).into(imageView);
//        Log.d("DATA","DADADADADAD" + pagerlist.get(position).getImagepath());
        Glide.with(context).load(pagerlist.get(position).getImagePath()+ pagerlist.get(position).getImageName())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.logo)
                .into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}
