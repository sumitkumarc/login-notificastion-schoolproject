package com.skyzone18.ManMandirVidyalay.Model;

import android.os.Environment;

/**
 * Created by kevalt on 2/19/2018.
 */

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
