package com.skyzone18.ManMandirVidyalay.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.Activity.AboutActivity;
import com.skyzone18.ManMandirVidyalay.Activity.AcademicActivity;
import com.skyzone18.ManMandirVidyalay.Activity.ActvitiesActivity;
import com.skyzone18.ManMandirVidyalay.Activity.AdmissionActivity;
import com.skyzone18.ManMandirVidyalay.Activity.AimActivity;
import com.skyzone18.ManMandirVidyalay.Activity.ContactActivity;
import com.skyzone18.ManMandirVidyalay.Activity.FacilityActivity;
import com.skyzone18.ManMandirVidyalay.Activity.MainActivity;
import com.skyzone18.ManMandirVidyalay.Activity.MangementActivity;
import com.skyzone18.ManMandirVidyalay.Activity.TrustActivity;
import com.skyzone18.ManMandirVidyalay.Adapter.silderadapter;
import com.skyzone18.ManMandirVidyalay.JsonActivity.EventActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.GalleryActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.HomeWorkActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.NewsActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.NoticeActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.StaffActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.StudentResultActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.TodayActivity;
import com.skyzone18.ManMandirVidyalay.JsonActivity.VideoyActivity;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Activity.StudentLoginActivity;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Activity.LoginMainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Sky05 on 3/7/2018.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {
    ApiService apiService;
    LinearLayout card_facility, card_admission, card_aim, card_man, card_stulogin, card_today, card_news, card_event, card_galler, card_video, card_notice, card_staff;
    Context context;
    LinearLayout card_about, card_academic, card_activity, card_schoolresults, card_homework, card_phoneno;
    FragmentManager mFragmentManager;
    private Animation animation;
    ImageView iv_homework, iv_phoneno;
    ImageView about, academic, actvity, faclity, admission, aim, staff, news, gallery, event, man, notice, video, login, aje, iv_schoolresults;
    ViewPager recyclerView;
    FragmentTransaction mFragmentTransaction;
    int min;
    Handler mHandler = new Handler();
    List<Datum> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        setHasOptionsMenu(true);
        apiService = RetroClient.getClient().create(ApiService.class);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        recyclerView = (ViewPager) view.findViewById(R.id.news_slider);
        min = recyclerView.getCurrentItem();
        mHandler.postDelayed(runnable, 3000);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int eventaction = motionEvent.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeCallbacks(runnable);
                        Log.d("HHHHHH", "onTouch:>>>> down ");
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.postDelayed(runnable, 100);
                        Log.d("HHHHHH", "onTouch:>>>> UP ");
                        break;
                }
                return true;
            }
        });
        slider();
        mFragmentManager = getActivity().getSupportFragmentManager();
//        card_phoneno = (LinearLayout) view.findViewById(R.id.card_phoneno);
        card_homework = (LinearLayout) view.findViewById(R.id.card_homework);
        card_about = (LinearLayout) view.findViewById(R.id.about_card);
        card_schoolresults = (LinearLayout) view.findViewById(R.id.card_schoolresults);
        card_academic = (LinearLayout) view.findViewById(R.id.card_academic);
        card_activity = (LinearLayout) view.findViewById(R.id.card_activity);
        card_facility = (LinearLayout) view.findViewById(R.id.card_faclity);
        card_admission = (LinearLayout) view.findViewById(R.id.card_admission);
        card_aim = (LinearLayout) view.findViewById(R.id.card_aim);
        card_man = (LinearLayout) view.findViewById(R.id.card_man);
        card_news = (LinearLayout) view.findViewById(R.id.card_news);
        card_stulogin = (LinearLayout) view.findViewById(R.id.card_login);
        card_today = (LinearLayout) view.findViewById(R.id.card_today);
        card_event = (LinearLayout) view.findViewById(R.id.card_event);
        card_galler = (LinearLayout) view.findViewById(R.id.card_galler);
        card_video = (LinearLayout) view.findViewById(R.id.card_video);
        card_notice = (LinearLayout) view.findViewById(R.id.card_notice);
        card_staff = (LinearLayout) view.findViewById(R.id.card_staff);
        about = (ImageView) view.findViewById(R.id.about);
        academic = (ImageView) view.findViewById(R.id.academic);
        actvity = (ImageView) view.findViewById(R.id.actvity);
        faclity = (ImageView) view.findViewById(R.id.faclity);
        admission = (ImageView) view.findViewById(R.id.admission);
        aim = (ImageView) view.findViewById(R.id.aim);
        staff = (ImageView) view.findViewById(R.id.staff);
        news = (ImageView) view.findViewById(R.id.news);
        gallery = (ImageView) view.findViewById(R.id.gallery);
        event = (ImageView) view.findViewById(R.id.event);
        man = (ImageView) view.findViewById(R.id.man);
        notice = (ImageView) view.findViewById(R.id.notice);
        video = (ImageView) view.findViewById(R.id.video);
        login = (ImageView) view.findViewById(R.id.login);
        aje = (ImageView) view.findViewById(R.id.aje);
//        iv_phoneno = (ImageView) view.findViewById(R.id.iv_phoneno);
        iv_homework = (ImageView) view.findViewById(R.id.iv_homework);
        iv_schoolresults = (ImageView) view.findViewById(R.id.iv_schoolresults);
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        about.startAnimation(animation);
        academic.startAnimation(animation);
        actvity.startAnimation(animation);
        faclity.startAnimation(animation);
        admission.startAnimation(animation);
        aim.startAnimation(animation);
        staff.startAnimation(animation);
        news.startAnimation(animation);
        gallery.startAnimation(animation);
        event.startAnimation(animation);
        man.startAnimation(animation);
        notice.startAnimation(animation);
        video.startAnimation(animation);
        login.startAnimation(animation);
        aje.startAnimation(animation);
        iv_schoolresults.startAnimation(animation);
        iv_homework.startAnimation(animation);
//        iv_phoneno.startAnimation(animation);
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intentLogout = new Intent(getActivity(), ContactActivity.class);
//                card_phoneno  MainFragment.this.startActivity(intentLogout);
//            }
//        });
        card_homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogout = new Intent(getActivity(), HomeWorkActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_schoolresults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(getActivity(), "coming soon ", Toast.LENGTH_SHORT).show();
                Intent intentLogout = new Intent(getActivity(), StudentResultActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), StaffActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getActivity(), "coming soon ", Toast.LENGTH_SHORT).show();
                Intent intentLogout = new Intent(getActivity(), NoticeActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), VideoyActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_galler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), GalleryActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), EventActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), NewsActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), AboutActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), TrustActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), ActvitiesActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), FacilityActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_admission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), AdmissionActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_aim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), AimActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), MangementActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_stulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), LoginMainActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        card_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(getActivity(), TodayActivity.class);
                MainFragment.this.startActivity(intentLogout);
            }
        });
        return view;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //  Collections.shuffle(os_versions);
            try {
                if (min < items.size()) {
                    min++;
                    recyclerView.setCurrentItem(min, true);
                } else {
                    min = 0;
                    recyclerView.setCurrentItem(min, true);
                }
                mHandler.postDelayed(this, 2000);
            } catch (Exception ex) {
            }

        }
    };

    public void slider() {
        Call<Example> call = apiService.GetSlider();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {

                items = response.body().getData();
                if (response.body().getData().size() == 0) {

                } else {
                    recyclerView.setAdapter(new silderadapter(getActivity(), (ArrayList<Datum>) items));
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                // Log error here since request failed
                Log.e(">>>>>>", t.toString());
                //  imageView.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
