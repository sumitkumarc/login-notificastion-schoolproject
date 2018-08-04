package com.skyzone18.ManMandirVidyalay.Model;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by n9xCh on 23-Aug-16.
 */
public class InternetConnection
{
    public static String API_KEY ="AIzaSyCZLEbLwJIVh13nuU109fkXEaS8actN6QA";

    /** CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT */
    public static boolean checkConnection(Context context) {
        return  ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
