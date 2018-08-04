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
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.Adapter.StandardAdapter;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StanderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ApiService apiService;
    ImageView ivnotfound;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stander);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivnotfound = (ImageView) findViewById(R.id.iv_notfound);
        recyclerView = (RecyclerView) findViewById(R.id.txt_home);


        String name = getIntent().getStringExtra("STDNAME");
        String stdid = getIntent().getStringExtra("STDID");


        getSupportActionBar().setTitle(name);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiService = RetroClient.getClient().create(ApiService.class);

        getstander(stdid);
    }
    private void getstander(String stdid) {
        pDialog = new ProgressDialog(StanderActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<Example> call = apiService.GetStandard(stdid);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                //   List<Datum> items = response.body().getData();
                pDialog.dismiss();
                List<Datum> dataitem = response.body().getData();
                // Toast.makeText(StanderActivity.this, response.body().getData().get(1).getStdId(), Toast.LENGTH_LONG).show();

                if (response.body().getData().size() == 0) {
                    ivnotfound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setAdapter(new StandardAdapter(StanderActivity.this, (ArrayList<Datum>) dataitem));
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(">>>>>>", t.toString());
                Toast.makeText(StanderActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
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
