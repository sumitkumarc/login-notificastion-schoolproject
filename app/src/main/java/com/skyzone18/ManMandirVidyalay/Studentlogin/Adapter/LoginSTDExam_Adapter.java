package com.skyzone18.ManMandirVidyalay.Studentlogin.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Activity.LoginExamResultViewActivity;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Activity.LoginExamlistActivity;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginDatum;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class LoginSTDExam_Adapter extends RecyclerView.Adapter<LoginSTDExam_Adapter.ViewHolder> {

    List<LoginDatum> dataset;
    LoginExamlistActivity context;
    String CONSTRING;
    String GRNO;
    Integer extype;
    public LoginSTDExam_Adapter(LoginExamlistActivity loginExamlistActivity, List<LoginDatum> items, Integer examtype) {
        this.dataset = items;
        this.context = loginExamlistActivity;
        this.extype = examtype;
        SharedPreferences preferences = context.getSharedPreferences("myPref", MODE_PRIVATE);
        CONSTRING = preferences.getString("USER_DATASCREPT", null);
        GRNO = preferences.getString("GRNO", null);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.login_demo, null);
        return new ViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txt_name.setText(dataset.get(i).getExamName());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ExamCode = dataset.get(i).getExamCode().toString();
                Intent intent= new Intent(context,LoginExamResultViewActivity.class);
                intent.putExtra("USER_DATASCREPT",CONSTRING );
                intent.putExtra("GRNO",GRNO );
                intent.putExtra("EXAMCODE",ExamCode );
                intent.putExtra("EXAMTYPE" ,extype);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            cardView = (CardView) itemView.findViewById(R.id.cv_item);
        }
    }

}
