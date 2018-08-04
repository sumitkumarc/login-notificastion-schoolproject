package com.skyzone18.ManMandirVidyalay.Studentlogin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginApiService;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginDatum;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginExample;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginRetroClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginUserProfileActivity extends AppCompatActivity {
    String CONSTRIN;
    String GRNO,USERIMAGE;
    ProgressDialog pDialog;
    LoginApiService loginApiService;
    ImageView sta_img;
    TextView txt_grno,txt_std,txt_div,txt_fname,txt_eng_name,txt_birthdate,txt_end_date,txt_sex,txt_cast,txt_category,txt_rollno,txt_adharcard,txt_religion,txt_taluka,txt_district;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        SharedPreferences preferences = this.getSharedPreferences("myPref", MODE_PRIVATE);
        CONSTRIN = preferences.getString("USER_DATASCREPT", null);
        GRNO = preferences.getString("GRNO", null);
        GRNO = preferences.getString("GRNO", null);
        USERIMAGE = preferences.getString("USERIMAGE", null);
        bindview();
        std_detail(GRNO,CONSTRIN);
    }

    private void bindview() {
        txt_grno = (TextView)findViewById(R.id.txt_grno);
        txt_std = (TextView)findViewById(R.id.txt_std);
        txt_div = (TextView)findViewById(R.id.txt_div);
        txt_fname = (TextView)findViewById(R.id.txt_fname);
        txt_eng_name = (TextView)findViewById(R.id.txt_eng_name);
        txt_birthdate = (TextView)findViewById(R.id.txt_birthdate);
        txt_end_date = (TextView)findViewById(R.id.txt_end_date);
        txt_sex = (TextView)findViewById(R.id.txt_sex);
        txt_cast = (TextView)findViewById(R.id.txt_cast);
        txt_category = (TextView)findViewById(R.id.txt_category);
        txt_rollno = (TextView)findViewById(R.id.txt_rollno);
        txt_adharcard= (TextView)findViewById(R.id.txt_adharcard);
        txt_religion= (TextView)findViewById(R.id.txt_religion);
        txt_taluka= (TextView)findViewById(R.id.txt_taluka);
        txt_district= (TextView)findViewById(R.id.txt_district);
        sta_img= (ImageView)findViewById(R.id.sta_img);
        Glide.with(LoginUserProfileActivity.this).load(USERIMAGE).error(R.drawable.nopicstaff).into(sta_img);
    }

    private void std_detail(String grno, String constrin) {
            Log.d("DATA","DATATYPE" + grno + "\n" +constrin);
        pDialog = new ProgressDialog(LoginUserProfileActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Call<LoginExample> call = loginApiService.getuserinfo(grno,constrin);
        call.enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
                pDialog.dismiss();
                List<LoginDatum> items = response.body().getData();
                Log.d("", "Numbe " + items.size());
                if (response.body().getData().size() == 0) {
//                    ivnotfound.setVisibility(View.VISIBLE);
//                    recyclerView.setVisibility(View.GONE);
                } else {
                    txt_grno.setText("Gr No : " + items.get(0).getGrNO().toString());
                    txt_std.setText("Standard : " +items.get(0).getCurStd().toString());
                    txt_div.setText("Div : " +items.get(0).getCurDiv().toString());
                    txt_fname.setText(items.get(0).getName().toString());
                    txt_eng_name.setText(items.get(0).getEngName().toString());
                    txt_birthdate.setText(items.get(0).getBirthdate().toString());
                    txt_end_date.setText(items.get(0).getEngdate().toString());
                    txt_sex.setText(items.get(0).getSex().toString());
                    txt_cast.setText(items.get(0).getCast().toString());
                    txt_category.setText(items.get(0).getCategory().toString());
                    txt_rollno.setText(items.get(0).getRollNo().toString());
                    txt_adharcard.setText(items.get(0).getStdAdharID().toString());
                    txt_religion.setText(items.get(0).getReligion().toString());
                    txt_taluka.setText(items.get(0).getTaluko().toString());
                    txt_district.setText(items.get(0).getDistrict().toString());
                }

            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(LoginUserProfileActivity.this, "Try..", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
//
//            case R.id.share:
//                String appname = getString(R.string.app_name);
//                String ExternalString = getString(R.string.String);
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, appname + "\n" + ExternalString + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
//                sendIntent.setType("text/plain");
//                startActivity(sendIntent);
//                return true;
//
//            case R.id.rate:
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
//                return true;

            case R.id.logout:
                SharedPreferences settings = LoginUserProfileActivity.this.getSharedPreferences("myPref", LoginUserProfileActivity.this.MODE_PRIVATE);
                settings.edit().clear().commit();

                Intent intent = new Intent(LoginUserProfileActivity.this, LoginMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
