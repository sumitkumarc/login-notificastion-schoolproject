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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;


import java.util.List;

/**
 * Created by Rahul Patel on 2/16/2018.
 */

public class eventadapter extends RecyclerView.Adapter<eventadapter.ViewHolder> {

    List<Datum> eventlist;
    Context contet;
    Boolean flag = false;
    String monthname;
    String day;

    public eventadapter(Context context, List<Datum> items) {
        this.eventlist = items;
        this.contet = context;
    }

    @Override
    public eventadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_single_data, null);


        return new eventadapter.ViewHolder(itemview);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final eventadapter.ViewHolder holder, final int position) {

//        holder.evet_name.setText(eventlist.get(position).getTitle());
//
//
//        // date set
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//        Date newDate = null;
//        try {
//            newDate = format.parse(eventlist.get(position).getEventdate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        format = new SimpleDateFormat("dd/MM/yyyy");
//        String maindate = format.format(newDate);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String newDateStr = null;
//        try {
//            newDateStr = simpleDateFormat.format(simpleDateFormat.parse(maindate));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String str[] = newDateStr.split("/");
//        day = str[0];
//        Integer month = Integer.valueOf(str[1]);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH, month);
//        SimpleDateFormat sortDateFormat = new SimpleDateFormat("MMM");
//        sortDateFormat.setCalendar(calendar);
//        monthname = sortDateFormat.format(calendar.getTime());
//        holder.tvday.setText(day);
//        holder.tvmonth.setText(monthname);
//
//
//
        try {
         //   holder.evet_dis.setText(eventlist.get(position).getPlace());
            holder.title.setText(eventlist.get(position).getEventName());
            holder.dis.setText(Html.fromHtml(eventlist.get(position).getEventDescription()).toString());
            // holder.time.setText(arralist.get(position).getImagePath() + arralist.get(position).getNoticeImage() );
            Glide.with(contet).load(eventlist.get(position).getImagePath() + eventlist.get(position).getEventImage())
                    .into(holder.imageView);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(contet);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.show_fullscreen_image);
                    TouchImageView viewPager = (TouchImageView) dialog.findViewById(R.id.IMAGEID);
                    Glide.with(contet).load(eventlist.get(position).getImagePath()+ eventlist.get(position).getEventImage())
                            .into(viewPager);
                    dialog.show();
                }
            });
        } catch (Exception e) {

        }
        //    holder.evet_dis.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return eventlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, dis;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.news_title);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            dis = (TextView) itemView.findViewById(R.id.news_dis);
            cardView = (CardView) itemView.findViewById(R.id.cardview);


        }
    }
}
