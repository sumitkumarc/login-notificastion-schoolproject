package com.skyzone18.ManMandirVidyalay.parser;

/**
 * Created by n9xCh on 23-Aug-16.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class JSONParser {
    /********
     * URLS
     *******/
    public static final String SaveData="http://manmandir.skyzoneschoolsoftware.com/web_service/save_notification_key";
    /**
     * TAGs Defined Here...
     */
    public static final String TAG = "TAG";
    /**
     * Response
     */
    private static Response response;


    public static JSONObject insertData(String DeviceID) {
        try {
            OkHttpClient client = new OkHttpClient();
          //  Request request = new Request.Builder().url(Std+ID).build();

            RequestBody requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM) //this is what I say in my POSTman (Chrome plugin)
                    .addFormDataPart("serverkey", "AAAArahwbNY:APA91bEG-W7qiLA_SoFjyPKAHtlNJdOFi6P_UMmy43tzA9Z8PA9VDnf5ZRqvhlDRHwreUcdksGhbLbnzYbRwJFS90HtuvHaEUfBs1_12iwIimJrjDOblYGTpv1VPPkrhlYXOWmiamtWT1UpaTB7s8krqvV_430w_4A")
                    .addFormDataPart("senderid", "745855282390")
                    .addFormDataPart("devicetoken",DeviceID)
                    .build();

            Request request = new Request.Builder()
                    .url(SaveData)
                    .post(requestBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

}