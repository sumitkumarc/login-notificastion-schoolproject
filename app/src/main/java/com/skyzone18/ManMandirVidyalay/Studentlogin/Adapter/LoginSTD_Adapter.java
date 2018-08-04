package com.skyzone18.ManMandirVidyalay.Studentlogin.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Activity.LoginExamActivity;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginDatum;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class LoginSTD_Adapter extends RecyclerView.Adapter<LoginSTD_Adapter.ViewHolder> {
    List<LoginDatum> dataset;
    FragmentActivity context;
    String CONSTRING;
    String finalUrl;

    public LoginSTD_Adapter(FragmentActivity activity, List<LoginDatum> items) {
        this.dataset = items;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.login_single_std_item, null);
        return new ViewHolder(itemview);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        try {

            holder.txt_name.setText(dataset.get(i).getName());
            holder.txt_grno.setText(dataset.get(i).getGrNO());
            holder.txt_std.setText(dataset.get(i).getCurStd() + "-" + dataset.get(i).getCurDiv());
            holder.txt_rollno.setText(dataset.get(i).getRollNo());
            String url = dataset.get(i).getImg();

            finalUrl = url.replace("\\u0026", "&");
            //   holder.imageView.
            Glide.with(context).load(finalUrl).error(R.drawable.nopicstaff).into(holder.imageView);
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE).edit();
                    editor.putString("GRNO", dataset.get(i).getGrNO());
                    editor.putString("USER_DATASCREPT", dataset.get(i).getConstr());
                    editor.putString("USERNAME", dataset.get(i).getName());
                    editor.putString("USERIMAGE", dataset.get(i).getImg());
                    editor.commit();
                    Intent intent = new Intent(context, LoginExamActivity.class);
//                    intent.putExtra("GRNO",dataset.get(i).getGrNO());
//                    intent.putExtra("USER_DATASCREPT",dataset.get(i).getConstr());
                    context.startActivity(intent);
                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_grno, txt_fname, txt_std, txt_rollno;
        RelativeLayout relativeLayout;
        CardView card_view;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_grno = (TextView) itemView.findViewById(R.id.txt_grno);
            txt_fname = (TextView) itemView.findViewById(R.id.txt_fname);
            txt_std = (TextView) itemView.findViewById(R.id.txt_std);
            txt_rollno = (TextView) itemView.findViewById(R.id.txt_rollno);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
