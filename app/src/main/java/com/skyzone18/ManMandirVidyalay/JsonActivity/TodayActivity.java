package com.skyzone18.ManMandirVidyalay.JsonActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.Adapter.newsadapter;
import com.skyzone18.ManMandirVidyalay.Adapter.todayeventadapter;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class TodayActivity extends AppCompatActivity {

    ApiService apiService;
    ImageView ivnotfound;
    LinearLayout llmain;
    ProgressDialog pDialog;
    RecyclerView rvNews, rvEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.today);
        ivnotfound = (ImageView) findViewById(R.id.iv_notfound);
        llmain = (LinearLayout) findViewById(R.id.ll_main);
        apiService = RetroClient.getClient().create(ApiService.class);
        binview();
    }
    private void binview() {
        rvEvent = (RecyclerView) findViewById(R.id.rv_Event);
        rvNews = (RecyclerView) findViewById(R.id.news_recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvNews.setLayoutManager(layoutManager);
        rvNews.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvEvent.setLayoutManager(mLayoutManager);
        rvEvent.setItemAnimator(new DefaultItemAnimator());

        news();
      //  Event();
    }

    public void news() {
        pDialog = new ProgressDialog(TodayActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<Example> call = apiService.Getodaynews();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                pDialog.dismiss();
                List<Datum> items = response.body().getData();
                if (response.body().getData().size() == 0) {
                    rvNews.setVisibility(View.GONE);
                } else {
                    rvNews.setAdapter(new newsadapter(TodayActivity.this, (ArrayList<Datum>) items));
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(">>>>>>", t.toString());
                pDialog.dismiss();
                Toast.makeText(TodayActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Event() {
//        pDialog = new ProgressDialog(TodayActivity.this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        pDialog.show();
        Call<Example> call = apiService.GetTodayEvent();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                //pDialog.dismiss();
                List<Datum> items = response.body().getData();
                if (response.body().getData().size() == 0) {
                    rvEvent.setVisibility(View.GONE);
                } else {
                    rvEvent.setAdapter(new todayeventadapter(TodayActivity.this, (ArrayList<Datum>) items));
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(">>>>>>", t.toString());
              //  pDialog.dismiss();
                Toast.makeText(TodayActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuinv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            case R.id.share:
                String appname = getString(R.string.app_name);
                String ExternalString = getString(R.string.String);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, appname + "\n" + ExternalString + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;

            case R.id.rate:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
