package com.skyzone18.ManMandirVidyalay.Notification;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.skyzone18.ManMandirVidyalay.Model.InternetConnection;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;
import com.skyzone18.ManMandirVidyalay.parser.JSONParser;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by n9xCh on 24-Jan-17.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    ApiService apiService;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        apiService = RetroClient.getClient().create(ApiService.class);



        Log.d(refreshedToken, "onTokenRefresh:>>>> ");
        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.d(TAG, "sendRegistrationToServer: " + token);

        if (InternetConnection.checkConnection(getApplicationContext())) {
          //  setNotificationId(token);
        }

    }


//    private void setNotificationId(String token)
//    {
//        Call<Example> call = apiService.SubmitToken(token);
//
//        call.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                if (response.body().getSuccess()==0)
//                {
//                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean("TOKEN", false);
//                    editor.commit();
//                }
//                else
//                {
//                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean("TOKEN", true);
//                    editor.commit();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Example>call, Throwable t) {
//                // Log error here since request failed
//                Log.e("", t.toString());
//            }
//        });
//    }

    private void storeRegIdInPref(String token) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }
}
