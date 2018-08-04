package com.skyzone18.ManMandirVidyalay.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.skyzone18.ManMandirVidyalay.Adapter.eventadapter;
import com.skyzone18.ManMandirVidyalay.JsonActivity.EventActivity;
import com.skyzone18.ManMandirVidyalay.Model.InternetConnection;
import com.skyzone18.ManMandirVidyalay.Notification.Config;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;
import com.skyzone18.ManMandirVidyalay.parser.JSONParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlashActvity extends AppCompatActivity {
    ImageView image;
    TextView textView;
    ApiService apiService;
    BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_slash);
        apiService = RetroClient.getClient().create(ApiService.class);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                }
            }
        };
        displayFirebaseRegId();
        image = (ImageView) findViewById(R.id.image);
        image.startAnimation(
                AnimationUtils.loadAnimation(SlashActvity.this, R.anim.alpha));
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SlashActvity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.open_next, R.anim.close_next);
            }
        }, 3500);

    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        Boolean Token = pref.getBoolean("TOKEN", false);

        Log.e(">>>>>>>>>", "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId)) {
            Log.i(">>>>>>>>>", "displayFirebaseRegId: " + regId);

            if (Token == false) {
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    setNotificationId(regId);
                }
            }

        } else {

        }

    }

    private void setNotificationId(String token) {
        Call<Example> call = apiService.GetNotification(token);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                if (response.body().getData().size() == 0) {

                } else {
                }

            }

            @Override
            public void onFailure(Call<Example>call, Throwable t) {
             //   Toast.makeText(SlashActvity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
       // JSONParser.insertData(token);
    }
}
