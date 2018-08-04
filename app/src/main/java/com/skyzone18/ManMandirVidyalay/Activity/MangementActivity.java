package com.skyzone18.ManMandirVidyalay.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.Adapter.CustomListAdapter;
import com.skyzone18.ManMandirVidyalay.Model.TouchImageView;
import com.skyzone18.ManMandirVidyalay.R;

public class MangementActivity extends AppCompatActivity {
    WebView webView;
    private int[] imagearry = {
            R.drawable.main,
            R.drawable.bhavana,
            R.drawable.ghansyam,
            R.drawable.dhirubhai,
            R.drawable.naresh,
            R.drawable.haresh,
            R.drawable.pritibenmakvana,
            R.drawable.usha

    };
    private String[] title = {
            "શ્રી ધીરૂભાઈ બી. વેકરીયા",
            "શ્રી ભાવનાબેન ડી. વેકરીયા",
            "શ્રી ઘનશ્યામભાઈ વી. ઘેવરિયા",
            "શ્રી શશીકાંતભાઈ મુંગલપરા",
            "શ્રી નરેશભાઈ વળવી",
            "શ્રી હિરેનભાઈ ઈટાલીયા",
            "પ્રિતીબેન મકવાણા",
            "શ્રી ઉષાબેન ખંડહર"
    };
    private String[] subtitle = {
            "(ટ્રસ્ટીશ્રી)",
            "(મંત્રીશ્રી)",
            "(આચાર્યશ્રી)",
            "(કેમ્પસ ડિરેક્ટર)",
            "(સુપરવાયસર)",
            "(એપ્લિકેશન/સ્કૂલ.સોફ્ટવેર મેનેજર)",
            "(સુપરવાયસર)",
            "(ક્લાર્ક)"


    };
    public Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangement);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        webView = (WebView) findViewById(R.id.manwebview);
//        webView.loadUrl("file:///android_asset/management.html");
//        webView.setBackgroundColor(Color.TRANSPARENT);
        CustomListAdapter adapter = new CustomListAdapter(this, imagearry, title, subtitle);

// get the ListView and attach the adapter
        ListView itemsListView = (ListView) findViewById(R.id.list_view_items);
        itemsListView.setAdapter((ListAdapter) adapter);
        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog = new Dialog(MangementActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.show_fullscreen_image);
                TouchImageView viewPager = (TouchImageView) dialog.findViewById(R.id.IMAGEID);
                viewPager.setImageResource(imagearry[position]);
                dialog.show();
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
