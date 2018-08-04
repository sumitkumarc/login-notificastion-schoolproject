package com.skyzone18.ManMandirVidyalay.Studentlogin.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Fragment.DashboardFragment;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Fragment.LoginFragment;

public class LoginMainActivity extends AppCompatActivity {
    Fragment frag_login, frag_dashboard;
    Boolean Login;
    FrameLayout frag_container;
    public static EditText mainEditText1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);
        frag_container = (FrameLayout) findViewById(R.id.frag_container);
        SharedPreferences preferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        final String phoneno = preferences.getString("USER_PHONENO", null);
        Login = preferences.getBoolean("LOGIN", false);
        frag_login = new LoginFragment();
        frag_dashboard = new DashboardFragment();
        if (Login == true) {
            try{
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_dashboard).disallowAddToBackStack().commitAllowingStateLoss();
            }catch(Exception e){}
        } else {
          //  bindview();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_login).commit();
        }

        
    }



}
