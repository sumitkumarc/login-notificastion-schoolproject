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
import com.skyzone18.ManMandirVidyalay.Studentlogin.Adapter.LoginSTD_Adapter;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginApiService;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginDatum;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginExample;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginRetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginStudentListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String CONSTRIN;
    String GRNO;
    Integer EXAMTYPE;
    ProgressDialog pDialog;
    LoginApiService loginApiService;
    ImageView iv_noInternet;
    String phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_student_list);
        getSupportActionBar().setTitle("Student List");
        SharedPreferences preferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        CONSTRIN = preferences.getString("USER_DATASCREPT", null);
        GRNO = preferences.getString("GRNO", null);
        phoneno = preferences.getString("USER_PHONENO", null);
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        recyclerView = (RecyclerView) findViewById(R.id.detail_recyle);

        iv_noInternet = (ImageView) findViewById(R.id.iv_noInternet);
        bindview(phoneno);
    }

    private void bindview(String phoneno) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LoginStudentListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);
        std_detail(phoneno);
    }

    private void std_detail(String phoneno) {
        Log.d("DATA", "DATABASEDDDDD" + phoneno);
        pDialog = new ProgressDialog(LoginStudentListActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<LoginExample> call = loginApiService.getPhonelogin(phoneno,"data_godadara");
        call.enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
                pDialog.dismiss();
                List<LoginDatum> items = response.body().getData();
                Log.d("", "Numbe " + items.size());
                if (response.body().getData().size() == 0) {
                    iv_noInternet.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(new LoginSTD_Adapter(LoginStudentListActivity.this, items));
                }

            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(LoginStudentListActivity.this, "Try..", Toast.LENGTH_SHORT).show();
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
                SharedPreferences settings = LoginStudentListActivity.this.getSharedPreferences("myPref", LoginStudentListActivity.this.MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(LoginStudentListActivity.this, LoginMainActivity.class);
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
