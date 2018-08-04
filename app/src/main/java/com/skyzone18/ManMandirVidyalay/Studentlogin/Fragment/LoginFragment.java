package com.skyzone18.ManMandirVidyalay.Studentlogin.Fragment;


import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.Model.Config;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginApiService;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginExample;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginRetroClient;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    View form_login, imglogo, label_signup, darkoverlay;
    public static EditText mainEditText1;
    String userno;
    private DisplayMetrics dm;
    LoginApiService loginApiService;
    ProgressBar pbar;
    ProgressDialog pDialog;
    View button_login, button_label, button_icon;
    Fragment frag_dashboard;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment_login, container, false);
        // db = new DatabaseHelper(getActivity());

        //  notesList.addAll(db.getAllNotes());
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        pbar = (ProgressBar) view.findViewById(R.id.mainProgressBar1);
        button_icon = view.findViewById(R.id.button_icon);
        button_label = view.findViewById(R.id.button_label);
        dm = getResources().getDisplayMetrics();
        button_login = view.findViewById(R.id.button_login);
        button_login.setTag(0);
        pbar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        dm = getResources().getDisplayMetrics();
        form_login = view.findViewById(R.id.form_login);
        mainEditText1 = view.findViewById(R.id.mainEditText1);
        frag_dashboard = new DashboardFragment();
        imglogo = view.findViewById(R.id.fragmentloginLogo);
//        kbv = (KenBurnsView) view.findViewById(R.id.fragmentloginKenBurnsView1);
        darkoverlay = view.findViewById(R.id.fragmentloginView1);
        label_signup = view.findViewById(R.id.label_signup);
        final ValueAnimator va = new ValueAnimator();
        va.setDuration(1500);
        va.setInterpolator(new DecelerateInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator p1) {
                RelativeLayout.LayoutParams button_login_lp = (RelativeLayout.LayoutParams) button_login.getLayoutParams();
                button_login_lp.width = Math.round((Float) p1.getAnimatedValue());
                button_login.setLayoutParams(button_login_lp);
            }
        });
        button_login.animate().translationX(dm.widthPixels + button_login.getMeasuredWidth()).setDuration(0).setStartDelay(0).start();
        button_login.animate().translationX(0).setStartDelay(500).setDuration(500).setInterpolator(new OvershootInterpolator()).start();
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View p1) {
                userno = mainEditText1.getText().toString();
                if (userno == null || userno.isEmpty() || userno.length() != 10) {
                    Toast.makeText(getActivity(), "Please Enter 10 Digit No.", Toast.LENGTH_SHORT).show();
                } else {

                    Log.d("DATA", "DATAbASED" + userno);
                    pDialog = new ProgressDialog(getActivity());
                    pDialog.setMessage("Please wait...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    Log.d("DATA","DATATYPE" +  Config.SCHOOLFOLDERNAME);
                    Call<LoginExample> call = loginApiService.getPhonelogin(userno, Config.SCHOOLFOLDERNAME);
                    call.enqueue(new Callback<LoginExample>() {
                        @Override
                        public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
                            pDialog.dismiss();
                            if (response.body().getData().size() == 0) {
                                Toast.makeText(getActivity(), "Try After Some Time", Toast.LENGTH_SHORT).show();
                            } else {
                                SharedPreferences.Editor editor = getActivity().getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE).edit();
                                editor.putString("USER_PHONENO", userno);
                                editor.putBoolean("LOGIN", true);
                                editor.commit();
//                                Intent intent = new Intent(getActivity(), LoginStudentListActivity.class);
//                                startActivity(intent);
                                try {

                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, frag_dashboard).disallowAddToBackStack().commitAllowingStateLoss();

                                } catch (Exception e) {

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginExample> call, Throwable t) {

                            Log.e(">>>>>>", t.toString());
                            pDialog.dismiss();
                            Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
     //   RandomTransitionGenerator generator = new RandomTransitionGenerator(1500, new AccelerateDecelerateInterpolator());
//        kbv.setTransitionGenerator(generator);
        imglogo.animate().setStartDelay(1500).setDuration(500).alpha(1).start();
        darkoverlay.animate().setStartDelay(1500).setDuration(500).alpha(0.6f).start();
        label_signup.animate().setStartDelay(1500).setDuration(500).alpha(1).start();
        form_login.animate().translationY(dm.heightPixels).setStartDelay(0).setDuration(0).start();
        form_login.animate().translationY(0).setDuration(500).alpha(1).setStartDelay(1500).start();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
