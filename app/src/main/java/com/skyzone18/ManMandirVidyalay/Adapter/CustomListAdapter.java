package com.skyzone18.ManMandirVidyalay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyzone18.ManMandirVidyalay.Activity.MangementActivity;
import com.skyzone18.ManMandirVidyalay.R;

public class CustomListAdapter extends BaseAdapter {
    private Context context; //context
    int[] image;
    String[] maintital;
    String[] mainsubtitle;

    public CustomListAdapter(MangementActivity mangementActivity, int[] imagearry, String[] title, String[] subtitle) {
        this.context = mangementActivity;
        this.image = imagearry;
        this.maintital = title;
        this.mainsubtitle = subtitle;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.single_staff_data, parent, false);
        }

        TextView textViewItemName = (TextView) convertView.findViewById(R.id.sta_name);
        TextView textViewItemDescription = (TextView) convertView.findViewById(R.id.sta_quli);
        ImageView ImageView =(ImageView) convertView.findViewById(R.id.sta_img);
        TextView txtstafposition = (TextView) convertView.findViewById(R.id.txtstafposition);
        txtstafposition.setVisibility(View.GONE);
        //sets the text for item name and item description from the current item object
        textViewItemName.setText(maintital[position].toString());
        textViewItemDescription.setText(mainsubtitle[position].toString());
        ImageView.setImageResource(image[position]);

        // returns the view for the current row
        return convertView;
    }
}
