package com.skyzone18.ManMandirVidyalay.JsonActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.Adapter.showgalleryadapter;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ShowPhotodActivity extends AppCompatActivity {
   public static String image_id;
    String imagename;
    ProgressDialog pDialog;
    RecyclerView recyclerView;
    ArrayList<Datum> showgalleryist = new ArrayList<>();
    ApiService apiService;
    ImageView ivnotfound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photod);
        Intent intent = getIntent();
        intent.getStringExtra("img");
        imagename = getIntent().getStringExtra("CATNAME");
        image_id = getIntent().getStringExtra("img");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(imagename);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ivnotfound = (ImageView) findViewById(R.id.iv_notfound);
        recyclerView = (RecyclerView) findViewById(R.id.show_reycle);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiService = RetroClient.getClient().create(ApiService.class);


        showgalley();
    }

    public void showgalley() {
        pDialog = new ProgressDialog(ShowPhotodActivity.this);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<Example> call = apiService.getAllPhoto(image_id);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                pDialog.dismiss();
                List<Datum> items = response.body().getData();
                if (response.body().getData().size() == 0) {
                    ivnotfound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setAdapter(new showgalleryadapter(ShowPhotodActivity.this, (ArrayList<Datum>) items));
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(">>>>>>", t.toString());
                pDialog.dismiss();
                Toast.makeText(ShowPhotodActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
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
