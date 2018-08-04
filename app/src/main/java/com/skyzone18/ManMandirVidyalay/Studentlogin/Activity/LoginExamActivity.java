package com.skyzone18.ManMandirVidyalay.Studentlogin.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginApiService;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginRetroClient;


public class LoginExamActivity extends AppCompatActivity {
    CardView card_ExameList, card_FasaExameList, card_Fees, card_myprofile, card_attendance, card_homework;
    public Dialog dialog;
    String CONSTRIN;
    String GRNO;
    String USERNAME;
    ProgressDialog pDialog;
    LoginApiService loginApiService;
    RecyclerView recyclerView;
    int ExameList = 0;
    int FasaExameList = 1;
    ImageView iv_noInternet;
    LinearLayout ll_main;
    TextView txt_name;
    String USERIMAGE;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_exam);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Menu List");
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_home);
        SharedPreferences preferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        CONSTRIN = preferences.getString("USER_DATASCREPT", null);
        GRNO = preferences.getString("GRNO", null);
        USERNAME = preferences.getString("USERNAME", null);
        USERIMAGE = preferences.getString("USERIMAGE", null);
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        Log.d("DATA", "DATATYPE" + USERIMAGE);
        iv_noInternet = (ImageView) findViewById(R.id.iv_noInternet);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        binview();
        //   checkConnection();

    }

    @SuppressLint("WrongViewCast")
    private void binview() {
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_name.setText(USERNAME);
        card_homework = (CardView) findViewById(R.id.card_homework);
        card_Fees = (CardView) findViewById(R.id.card_Fees);
        card_ExameList = (CardView) findViewById(R.id.card_ExameList);
        card_FasaExameList = (CardView) findViewById(R.id.card_FasaExameList);
        card_myprofile = (CardView) findViewById(R.id.card_myprofile);
        card_attendance = (CardView) findViewById(R.id.card_attendance);
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(LoginExamActivity.this).load(USERIMAGE).error(R.drawable.nopicstaff).into(imageView);

       // imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
//        Picasso.get()
//                .load(USERIMAGE)
//                .error(R.drawable.nopicstaff)
//                .into(imageView);
        card_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginExamActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        card_homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginExamActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
        card_ExameList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginExamActivity.this, LoginExamlistActivity.class);
                intent.putExtra("EXAM", ExameList);
                startActivity(intent);
            }

//            private void getexaminfo(String constrin, String grno) {
//                pDialog = new ProgressDialog(LoginExamActivity.this);
//                pDialog.setMessage("Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setCancelable(false);
//                pDialog.show();
//                Call<LoginExample> call = loginApiService.GetExamList(grno, constrin);
//                call.enqueue(new Callback<LoginExample>() {
//                    @Override
//                    public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
//                        List<LoginDatum> items = response.body().getData();
//                        if (response.body().getData().size() == 0) {
//
//                        } else {
//                            pDialog.dismiss();
//                            recyclerView.setAdapter(new LoginSTDExam_Adapter(LoginExamActivity.this, items));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginExample> call, Throwable t) {
//                        // Log error here since request failed
//                        Log.e(">>>>>>", t.toString());
//                        pDialog.dismiss();
//                        Toast.makeText(LoginExamActivity.this, "Try..", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
        });
        card_FasaExameList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginExamActivity.this, LoginExamlistActivity.class);
                intent.putExtra("EXAM", FasaExameList);
                startActivity(intent);
            }

//            private void getexaminfoFasaExame(String GRNO, String CONSTRIN) {
//                Log.d("DATA", "MAIN" + GRNO + "\n" + CONSTRIN);
//                pDialog = new ProgressDialog(LoginExamActivity.this);
//                pDialog.setMessage("Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setCancelable(false);
//                pDialog.show();
//                Call<LoginExample> call = loginApiService.GetFasaExameList(CONSTRIN, GRNO);
//                call.enqueue(new Callback<LoginExample>() {
//                    @Override
//                    public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
//                        List<LoginDatum> items = response.body().getData();
//                        if (response.body().getData().size() == 0) {
//
//                        } else {
//                            pDialog.dismiss();
//                            recyclerView.setAdapter(new LoginSTDExam_Adapter(LoginExamActivity.this, items));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<LoginExample> call, Throwable t) {
//                        // Log error here since request failed
//                        Log.e(">>>>>>", t.toString());
//                        pDialog.dismiss();
//                        Toast.makeText(LoginExamActivity.this, "Try..", Toast.LENGTH_SHORT).show();
//                    }
//                });
            //           }
        });
        card_Fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginExamActivity.this, LoginFeesActivity.class);
                startActivity(intent);
            }
        });
        card_myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginExamActivity.this, LoginUserProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

//            case R.id.share:
//                String appname = getString(R.string.app_name);
//                String ExternalString = getString(R.string.String);
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, appname + "\n" + ExternalString + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
//                return true;
//
//            case R.id.rate:
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
//                return true;

            case R.id.logout:
                SharedPreferences settings = LoginExamActivity.this.getSharedPreferences("myPref", LoginExamActivity.this.MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(LoginExamActivity.this, LoginMainActivity.class);
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
//    public void onNetworkConnectionChanged(boolean isConnected) {
//        showSnack(isConnected);
//    }
//
//    // Method to manually check connection status
//    private void checkConnection() {
//        boolean isConnected = ConnectivityReceiver.isConnected();
//        showSnack(isConnected);
//    }
//
//    // Showing the status in Snackbar
//    private void showSnack(boolean isConnected) {
//        String message;
//        int color;
//        if (isConnected) {
//            iv_noInternet.setVisibility(View.GONE);
//            ll_main.setVisibility(View.VISIBLE);
//            binview();
//        } else {
//            iv_noInternet.setVisibility(View.VISIBLE);
//            ll_main.setVisibility(View.GONE);
//            message = "Sorry! Not connected to internet";
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // register connection status listener
//        MyApplication.getInstance().setConnectivityListener(this);
//    }

}
