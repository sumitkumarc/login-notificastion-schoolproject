package com.skyzone18.ManMandirVidyalay.Studentlogin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class LoginExamResultViewActivity extends AppCompatActivity {
    ProgressDialog pDialog;

    LoginApiService loginApiService;
    TextView txt_name,txt_year, txt_grmo, txt_setno, txt_std, txt_div, txt_total, txt_mintotal, txt_rank, txt_per, txt_gread;
    ImageView iv_noInternet;
    LinearLayout ll_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_exam_result_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Student Exam Result");
        iv_noInternet = (ImageView) findViewById(R.id.iv_noInternet);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        ll_main.setVisibility(View.GONE);
        bindview();
    }

    private void bindview() {
        SharedPreferences preferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        String USERNAME = preferences.getString("USERNAME", null);
        String USERIMAGE = preferences.getString("USERIMAGE", null);
        String USER_DATASCREPT = getIntent().getStringExtra("USER_DATASCREPT");
        String GRNO = getIntent().getStringExtra("GRNO");
        String EXAMCODE = getIntent().getStringExtra("EXAMCODE");
        Integer EXAMTYPE = getIntent().getIntExtra("EXAMTYPE", 0);
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        txt_year = (TextView) findViewById(R.id.txt_year);
        txt_setno = (TextView) findViewById(R.id.txt_setno);
        txt_std = (TextView) findViewById(R.id.txt_std);
        txt_grmo = (TextView) findViewById(R.id.txt_grmo);
        txt_div = (TextView) findViewById(R.id.txt_div);
        txt_total = (TextView) findViewById(R.id.txt_total);
        txt_mintotal = (TextView) findViewById(R.id.txt_mintotal);
        txt_rank = (TextView) findViewById(R.id.txt_rank);
        txt_per = (TextView) findViewById(R.id.txt_per);
        txt_gread = (TextView) findViewById(R.id.txt_gread);
        txt_name= (TextView) findViewById(R.id.txt_name);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(LoginExamResultViewActivity.this).load(USERIMAGE).error(R.drawable.nopicstaff).into(imageView);
        txt_name.setText(USERNAME);
        getResult(USER_DATASCREPT, GRNO, EXAMCODE, EXAMTYPE);
    }

    private void getResult(String user_datascrept, String grno, String examcode, Integer examtype) {
        pDialog = new ProgressDialog(LoginExamResultViewActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<LoginExample> call = loginApiService.GetExameResult(user_datascrept, grno, examcode, examtype);
        call.enqueue(new Callback<LoginExample>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
                pDialog.dismiss();
                try
                {                  List<LoginDatum> items = response.body().getData();
                    if (response.body().getData().size() == 0) {
                        iv_noInternet.setVisibility(View.VISIBLE);
                        ll_main.setVisibility(View.GONE);
                    } else {

                        ll_main.setVisibility(View.VISIBLE);
                        txt_year.setText(response.body().getYear().toString());
                        txt_setno.setText(response.body().getExSeatNo().toString());
                        txt_std.setText(response.body().getStd().toString());
                        txt_grmo.setText(response.body().getGrNo1().toString());
                        txt_div.setText(response.body().getDiv().toString());
                        txt_total.setText(response.body().getTotalMax().toString());
                        txt_mintotal.setText(response.body().getTotal().toString());
                        txt_rank.setText(response.body().getRank().toString());
                        txt_per.setText(response.body().getPercent().toString() + "%");
                        txt_gread.setText(response.body().getResult().toString());
                        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
                        TableRow tbrow0 = new TableRow(LoginExamResultViewActivity.this);

                        tbrow0.setBackground(getResources().getDrawable(R.drawable.table_row_bg));
                        TextView tv0 = new TextView(LoginExamResultViewActivity.this);
                        tv0.setText("Subject");
                        tv0.setGravity(Gravity.CENTER);
                        tv0.setPadding(5, 5, 5, 5);
//                    tv0.setTextColor(Color.BLACK);
                        tbrow0.addView(tv0);
                        TextView tv1 = new TextView(LoginExamResultViewActivity.this);
                        tv1.setText("Total");
                        tv1.setGravity(Gravity.CENTER);
                        tv1.setPadding(5, 5, 5, 5);
//                    tv1.setTextColor(Color.BLACK);
                        tbrow0.addView(tv1);
                        TextView tv2 = new TextView(LoginExamResultViewActivity.this);
                        tv2.setText("Passing");
                        tv2.setGravity(Gravity.CENTER);
                        tv2.setPadding(5, 5, 5, 5);
//                    tv2.setTextColor(Color.BLACK);
                        tbrow0.addView(tv2);
                        TextView tv3 = new TextView(LoginExamResultViewActivity.this);
                        tv3.setText("Marks");
                        tv3.setGravity(Gravity.CENTER);
                        tv3.setPadding(5, 5, 5, 5);
//                    tv3.setTextColor(Color.BLACK);
                        tbrow0.addView(tv3);
                        stk.addView(tbrow0);

                        for (int i = 0; i < items.size(); i++) {
                            TableRow tbrow = new TableRow(LoginExamResultViewActivity.this);
                            tbrow.setPadding(5, 5, 5, 5);
                            tbrow.setBackground(getResources().getDrawable(R.drawable.table_row_bg));
                            TextView t1v = new TextView(LoginExamResultViewActivity.this);
                            t1v.setText(items.get(i).getSubName().toString());
//                        t1v.setTextColor(Color.BLACK);
                            t1v.setGravity(Gravity.CENTER);
                            tbrow.addView(t1v);
                            TextView t2v = new TextView(LoginExamResultViewActivity.this);
                            t2v.setText(items.get(i).getTotalTh().toString());
//                        t2v.setTextColor(Color.BLACK);
                            t2v.setGravity(Gravity.CENTER);
                            tbrow.addView(t2v);
                            TextView t3v = new TextView(LoginExamResultViewActivity.this);
                            t3v.setText(items.get(i).getMinTh().toString());
//                        t3v.setTextColor(Color.BLACK);
                            t3v.setGravity(Gravity.CENTER);
                            tbrow.addView(t3v);
                            TextView t4v = new TextView(LoginExamResultViewActivity.this);
                            t4v.setText(items.get(i).getObtainedTh().toString());
//                        t4v.setTextColor(Color.BLACK);
                            t4v.setGravity(Gravity.CENTER);
                            tbrow.addView(t4v);
                            stk.addView(tbrow);
                        }
                    }
                }
                catch (Exception e) {
                    iv_noInternet.setVisibility(View.VISIBLE);
                }


            }
                @Override
                public void onFailure (Call <LoginExample> call, Throwable t){
                    // Log error here since request failed
                    Log.e(">>>>>>", t.toString());
                    pDialog.dismiss();
                    Toast.makeText(LoginExamResultViewActivity.this, "Try..", Toast.LENGTH_SHORT).show();
                }
            });
        }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.login_menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected (MenuItem menuItem){
            switch (menuItem.getItemId()) {

                case android.R.id.home:
                    finish();
                    return true;

//                case R.id.share:
//                    String appname = getString(R.string.app_name);
//                    String ExternalString = getString(R.string.String);
//                    Intent sendIntent = new Intent();
//                    sendIntent.setAction(Intent.ACTION_SEND);
//                    sendIntent.putExtra(Intent.EXTRA_TEXT, appname + "\n" + ExternalString + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
//                    sendIntent.setType("text/plain");
//                    startActivity(sendIntent);
//                    return true;
//
//                case R.id.rate:
//                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
//                    return true;
                case R.id.logout:
                    SharedPreferences settings = LoginExamResultViewActivity.this.getSharedPreferences("myPref", LoginExamResultViewActivity.this.MODE_PRIVATE);
                    settings.edit().clear().commit();

                    Intent intent = new Intent(LoginExamResultViewActivity.this, LoginMainActivity.class);
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
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // register connection status listener
//        MyApplication.getInstance().setConnectivityListener(this);
//    }
//    @Override
//    public void onNetworkConnectionChanged(boolean isConnected) {
//        showSnack(isConnected);
//    }
//    // Method to manually check connection status
//    private void checkConnection() {
//        boolean isConnected = ConnectivityReceiver.isConnected();
//        showSnack(isConnected);
//    }
//    // Showing the status in Snackbar
//    private void showSnack(boolean isConnected) {
//        String message;
//        int color;
//        if (isConnected) {
//            iv_noInternet.setVisibility(View.GONE);
//            ll_main.setVisibility(View.VISIBLE);
//
//
//        } else {
//            iv_noInternet.setVisibility(View.VISIBLE);
//            ll_main.setVisibility(View.GONE);
//
//        }
//    }

    }
