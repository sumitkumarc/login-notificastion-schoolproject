package com.skyzone18.ManMandirVidyalay.Studentlogin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Adapter.LoginSTDExam_Adapter;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginApiService;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginDatum;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginExample;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginRetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class LoginExamlistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String CONSTRIN;
    String GRNO;
    Integer EXAMTYPE;
    ProgressDialog pDialog;
    LoginApiService loginApiService;
    ImageView iv_noInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_examlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EXAMTYPE = getIntent().getIntExtra("EXAM", 0);
        Log.d("DATA", "EXAMTYPE" + EXAMTYPE);
        SharedPreferences preferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        CONSTRIN = preferences.getString("USER_DATASCREPT", null);
        GRNO = preferences.getString("GRNO", null);
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        recyclerView = (RecyclerView) findViewById(R.id.detail_recyle);
        recyclerView.setVisibility(View.GONE);
        iv_noInternet = (ImageView) findViewById(R.id.iv_notfound);
        //  checkConnection();
        bindview();

    }

    private void bindview() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LoginExamlistActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (EXAMTYPE == 0) {
            getSupportActionBar().setTitle("Exame Results");
            getexaminfo(CONSTRIN, GRNO);
        } else {
            getSupportActionBar().setTitle("FA-SA Exame Results");
            getexaminfoFasaExame(GRNO, CONSTRIN);
        }
    }

    private void getexaminfoFasaExame(String GRNO, String CONSTRIN) {
        Log.d("DATA", "MAIN" + GRNO + "\n" + CONSTRIN);
        pDialog = new ProgressDialog(LoginExamlistActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<LoginExample> call = loginApiService.GetFasaExameList(CONSTRIN, GRNO);
        call.enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
                pDialog.dismiss();
                List<LoginDatum> items = response.body().getData();
                if (response.body().getData().size() == 0) {
                    iv_noInternet.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new LoginSTDExam_Adapter(LoginExamlistActivity.this, items, EXAMTYPE));
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(LoginExamlistActivity.this, "Try..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getexaminfo(String constrin, String grno) {
        pDialog = new ProgressDialog(LoginExamlistActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<LoginExample> call = loginApiService.GetExamList(grno, constrin);
        call.enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
                pDialog.dismiss();
                try
                {
                    List<LoginDatum> items = response.body().getData();
                    if (response.body().getData().size() == 0) {
                        iv_noInternet.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {

                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new LoginSTDExam_Adapter(LoginExamlistActivity.this, items, EXAMTYPE));
                    }
                }
                catch (Exception e) {
                    iv_noInternet.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                // Log error here since request failed
                Log.e(">>>>>>", t.toString());
                pDialog.dismiss();
                Toast.makeText(LoginExamlistActivity.this, "Try..", Toast.LENGTH_SHORT).show();
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
                SharedPreferences settings = LoginExamlistActivity.this.getSharedPreferences("myPref", LoginExamlistActivity.this.MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(LoginExamlistActivity.this, LoginMainActivity.class);
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
//            recyclerView.setVisibility(View.VISIBLE);
//            bindview();
//        } else {
//            iv_noInternet.setVisibility(View.VISIBLE);
//            recyclerView.setVisibility(View.GONE);
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
