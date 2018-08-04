package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul Patel on 2/16/2018.
 */

public class holidayadapter extends RecyclerView.Adapter<holidayadapter.ViewHolder> {
    List<Datum> holilist;
    Context contet;
    String monthname;
    String day;

    public holidayadapter(Context context, ArrayList<Datum> items) {
        this.holilist = items;
        this.contet = context;
    }


    @Override
    public holidayadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_holiday_data, null);


        return new holidayadapter.ViewHolder(itemview);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final holidayadapter.ViewHolder holder, final int position) {

//        holder.holiday_name.setText(holilist.get(position).getDay());
//
//
//        ///  date setformat
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//        Date newDate = null;
//        try {
//            newDate = format.parse(holilist.get(position).getDateby());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        format = new SimpleDateFormat("dd-MM-yyyy");
//        String date = format.format(newDate);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        String newDateStr = null;
//        try {
//            newDateStr = simpleDateFormat.format(simpleDateFormat.parse(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String str[] = newDateStr.split("-");
//        day = str[0];
//        Integer month = Integer.valueOf(str[1]);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH, month);
//        SimpleDateFormat sortDateFormat = new SimpleDateFormat("MMM");
//        sortDateFormat.setCalendar(calendar);
//        monthname = sortDateFormat.format(calendar.getTime());
//        holder.tvday.setText(day);
//        holder.tvmonth.setText(monthname);
    }

    @Override
    public int getItemCount() {
        return holilist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView holiday_name;
        TextView tvday, tvmonth;

        public ViewHolder(View itemView) {
            super(itemView);

            holiday_name = (TextView) itemView.findViewById(R.id.holiday_name);
            tvday = (TextView) itemView.findViewById(R.id.tv_day);
            tvmonth = (TextView) itemView.findViewById(R.id.tv_month);

        }
    }
}
