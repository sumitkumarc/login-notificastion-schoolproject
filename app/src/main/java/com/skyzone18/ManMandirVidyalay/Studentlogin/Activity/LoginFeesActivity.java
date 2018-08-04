package com.skyzone18.ManMandirVidyalay.Studentlogin.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginApiService;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginDatum;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginExample;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginRetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFeesActivity extends AppCompatActivity {
    String CONSTRIN;
    String GRNO;
    LoginApiService loginApiService;
    ProgressDialog pDialog;
    TextView txt_total, txt_totalmafi, txt_unpaid, txt_col;
    public static String posdata;
    ImageView iv_noInternet;
    LinearLayout ll_main;
    TextView txt_name;
    String USERNAME;
    String USERIMAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_fees);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Student Fees" );
        SharedPreferences preferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        CONSTRIN = preferences.getString("USER_DATASCREPT", null);
        GRNO = preferences.getString("GRNO", null);
        USERNAME= preferences.getString("USERNAME", null);
        USERIMAGE = preferences.getString("USERIMAGE", null);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(LoginFeesActivity.this).load(USERIMAGE).error(R.drawable.nopicstaff).into(imageView);
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        iv_noInternet = (ImageView)findViewById(R.id.iv_noInternet);
        ll_main =(LinearLayout)findViewById(R.id.ll_main);
        ll_main.setVisibility(View.GONE);
       // checkConnection();
        binview();
    }

    private void binview() {
        txt_name= (TextView) findViewById(R.id.txt_name);
        txt_name.setText(USERNAME);
        txt_total = (TextView) findViewById(R.id.txt_total);
        txt_totalmafi = (TextView) findViewById(R.id.txt_totalmafi);
        txt_col = (TextView) findViewById(R.id.txt_col);
        Getfeesdata(CONSTRIN, GRNO);
    }

    private void Getfeesdata(String constrin, String grno) {
        pDialog = new ProgressDialog(LoginFeesActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<LoginExample> call = loginApiService.GetFeesData(constrin, grno);
        call.enqueue(new Callback<LoginExample>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {
                pDialog.dismiss();
                final List<LoginDatum> items = response.body().getData();
                if (response.body().getSucess().equals("0")) {
                    iv_noInternet.setVisibility(View.VISIBLE);
                    ll_main.setVisibility(View.GONE);
                } else {
                    ll_main.setVisibility(View.VISIBLE);
                    txt_total.setText("TOTAL FEE" + "\n "+ " ₹" +response.body().getTotal().toString());
                    txt_totalmafi.setText("DUE FEE" + "\n " + " ₹"+response.body().getTotalmafi().toString());
                    txt_col.setText(" ₹" + response.body().getTotalcollection().toString());
                    TableLayout stk = (TableLayout) findViewById(R.id.table_main);
                    TableRow tbrow0 = new TableRow(LoginFeesActivity.this);

                    tbrow0.setBackground(getResources().getDrawable(R.drawable.table_row_bg));
                    TextView tv00 = new TextView(LoginFeesActivity.this);
                    tv00.setText("More Info");
                    tv00.setGravity(Gravity.CENTER);
                    tv00.setPadding(5, 5, 5, 5);
//                    tv0.setTextColor(Color.BLACK);
                    tbrow0.addView(tv00);
                    TextView tv0 = new TextView(LoginFeesActivity.this);
                    tv0.setText("RecNo");
                    tv0.setGravity(Gravity.CENTER);
                    tv0.setPadding(5, 5, 5, 5);
//                    tv0.setTextColor(Color.BLACK);
                    tbrow0.addView(tv0);
                    TextView tv1 = new TextView(LoginFeesActivity.this);
                    tv1.setText("Date");
                    tv1.setGravity(Gravity.CENTER);
                    tv1.setPadding(5, 5, 5, 5);
//                    tv1.setTextColor(Color.BLACK);
                    tbrow0.addView(tv1);
                    TextView tv2 = new TextView(LoginFeesActivity.this);
                    tv2.setText("AdmFees");
                    tv2.setGravity(Gravity.CENTER);
                    tv2.setPadding(5, 5, 5, 5);
//                    tv2.setTextColor(Color.BLACK);
                    tbrow0.addView(tv2);
                    TextView tv3 = new TextView(LoginFeesActivity.this);
                    tv3.setText("Total");
                    tv3.setGravity(Gravity.CENTER);
                    tv3.setPadding(5, 5, 5, 5);
//                    tv3.setTextColor(Color.BLACK);
                    tbrow0.addView(tv3);
//                    TextView tv4 = new TextView(LoginFeesActivity.this);
//                    tv4.setText("Mafi");
//                    tv4.setGravity(Gravity.CENTER);
//                    tv4.setPadding(5, 5, 5, 5);
////                    tv3.setTextColor(Color.BLACK);
//                    tbrow0.addView(tv4);
                    stk.addView(tbrow0);

                    for (int i = 0; i < items.size(); i++) {
                        TableRow tbrow = new TableRow(LoginFeesActivity.this);
                        tbrow.setPadding(5, 5, 5, 5);
                        tbrow.setBackground(getResources().getDrawable(R.drawable.table_row_last_bg));
                        TextView start = new TextView(LoginFeesActivity.this);
                        start.setText("MoreInfo");
                        start.setGravity(Gravity.CENTER);
                        start.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        start.setPaintFlags(start.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                        posdata  = items.get(i).getReceiptNo().toString();
                        start.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View pos) {
                                TableRow tablerow = (TableRow)pos.getParent();
                                TextView sample = (TextView) tablerow.getChildAt(1);
                                String result=sample.getText().toString();
                                getMoreInfo(result, CONSTRIN, GRNO);
                             //   Toast.makeText(LoginFeesActivity.this, "" + result, Toast.LENGTH_SHORT).show();
                            }
                        });
                        tbrow.addView(start);
                        TextView t1v = new TextView(LoginFeesActivity.this);
                        t1v.setText(items.get(i).getReceiptNo().toString());
//                        t1v.setTextColor(Color.BLACK);
                        t1v.setGravity(Gravity.CENTER);
                        tbrow.addView(t1v);
                        TextView t2v = new TextView(LoginFeesActivity.this);
                        t2v.setText(items.get(i).getRecDate().toString());
//                        t2v.setTextColor(Color.BLACK);
                        t2v.setGravity(Gravity.CENTER);
                        tbrow.addView(t2v);
                        TextView t3v = new TextView(LoginFeesActivity.this);
                        t3v.setText(items.get(i).getAdmissionFees().toString() + " ₹");
//                        t3v.setTextColor(Color.BLACK);
                        t3v.setGravity(Gravity.CENTER);
                        tbrow.addView(t3v);
                        TextView t4v = new TextView(LoginFeesActivity.this);
                        t4v.setText(items.get(i).getTotal().toString()+ " ₹");
//                        t4v.setTextColor(Color.BLACK);
                        t4v.setGravity(Gravity.CENTER);
                        tbrow.addView(t4v);
//                        TextView t5v = new TextView(LoginFeesActivity.this);
//                        t5v.setText(items.get(i).getMafi().toString()+ " ₹");
////                        t4v.setTextColor(Color.BLACK);
//                        t5v.setGravity(Gravity.CENTER);
//                        tbrow.addView(t5v);
                        stk.addView(tbrow);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                // Log error here since request failed
                Log.e(">>>>>>", t.toString());
                pDialog.dismiss();
                Toast.makeText(LoginFeesActivity.this, "Try..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMoreInfo(final String posdata, String constrin, String grno) {
        pDialog = new ProgressDialog(LoginFeesActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<LoginExample> call = loginApiService.GetFeesMoreInfo(posdata, constrin, grno);
        call.enqueue(new Callback<LoginExample>() {

            @Override
            public void onResponse(Call<LoginExample> call, Response<LoginExample> response) {
                pDialog.dismiss();
                List<LoginDatum> items = response.body().getData();
                if (response.body().getData().size() == 0) {

                } else {

                    final Dialog dialog = new Dialog(LoginFeesActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.login_dialog_exam_list);
                    final ImageView icon_cancel = (ImageView)dialog.findViewById(R.id.icon_cancel);
                    final TextView txt_EnrFee = (TextView)dialog.findViewById(R.id.txt_EnrFee);
                    final TextView txt_trem_fees = (TextView)dialog.findViewById(R.id.txt_trem_fees);
                    final TextView txt_resno = (TextView)dialog.findViewById(R.id.txt_resno);
                    final TextView txt_add_fees = (TextView)dialog.findViewById(R.id.txt_add_fees);
                    final TextView txt_examfees = (TextView)dialog.findViewById(R.id.txt_examfees);
                    final TextView txt_lab_fees = (TextView)dialog.findViewById(R.id.txt_lab_fees);
                    final TextView txt_com_fees = (TextView)dialog.findViewById(R.id.txt_com_fees);
                    final TextView txt_activity_fees = (TextView)dialog.findViewById(R.id.txt_activity_fees);
                    final TextView txt_academy_fees = (TextView)dialog.findViewById(R.id.txt_academy_fees);
                    final TextView txt_fees_mafi = (TextView)dialog.findViewById(R.id.txt_fees_mafi);
                    final TextView txt_etc_fees = (TextView)dialog.findViewById(R.id.txt_etc_fees);
                    icon_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    txt_resno.setText("Receipt No :" + posdata);
                    txt_EnrFee.setText(items.get(0).getEnrFee().toString() + " ₹");
                    txt_add_fees.setText(items.get(0).getAdmissionFees1().toString() + " ₹");
                    txt_examfees.setText(items.get(0).getExamFees().toString() + " ₹");
                    txt_lab_fees.setText(items.get(0).getLabFee().toString() + " ₹");
                    txt_com_fees.setText(items.get(0).getCompFees().toString() + " ₹");
                    txt_activity_fees.setText(items.get(0).getActivityfee().toString() + " ₹");
                    txt_academy_fees.setText(items.get(0).getEduFees().toString() + " ₹");
                    txt_fees_mafi.setText(items.get(0).getMafi1().toString() + " ₹");
                    txt_etc_fees.setText(items.get(0).getOtherfee().toString() + " ₹");
                    txt_trem_fees.setText(items.get(0).getTermFees().toString() + "₹");



                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(LoginFeesActivity.this, "Try..", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
            case R.id.logout:
                SharedPreferences settings = LoginFeesActivity.this.getSharedPreferences("myPref", LoginFeesActivity.this.MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(LoginFeesActivity.this, LoginMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
