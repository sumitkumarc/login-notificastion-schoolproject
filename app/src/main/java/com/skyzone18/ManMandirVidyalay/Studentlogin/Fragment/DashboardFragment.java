package com.skyzone18.ManMandirVidyalay.Studentlogin.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.Model.Config;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Adapter.LoginSTD_Adapter;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginApiService;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginDatum;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginExample;
import com.skyzone18.ManMandirVidyalay.Studentlogin.Rest.LoginRetroClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static android.content.Context.MODE_PRIVATE;


public class DashboardFragment extends Fragment {
    LoginApiService loginApiService;
    ProgressDialog pDialog;
    RecyclerView recyclerView;
    ImageView iv_notfound;
    CardView card_Std_List, card_Std_Exam, card_attendance;

    public DashboardFragment() {
    }


    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();

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
        View view = inflater.inflate(R.layout.login_fragment_dashboard, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("myPref", MODE_PRIVATE);
        final String phoneno = preferences.getString("USER_PHONENO", null);
        final Boolean Login = preferences.getBoolean("LOGIN", false);
        loginApiService = LoginRetroClient.getClient().create(LoginApiService.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.detail_recyle);
        iv_notfound = (ImageView) view.findViewById(R.id.iv_notfound);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        std_detail(phoneno);
        return view;
    }

    private void std_detail(String phoneno) {
        Log.d("DATA", "DATABASEDDDDD" + phoneno);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        Log.d("DATA","DATATYPE" +  Config.SCHOOLFOLDERNAME);
        Call<LoginExample> call = loginApiService.getPhonelogin(phoneno, Config.SCHOOLFOLDERNAME);
        call.enqueue(new Callback<LoginExample>() {
            @Override
            public void onResponse(Call<LoginExample> call, retrofit2.Response<LoginExample> response) {
                pDialog.dismiss();
                try {
                    List<LoginDatum> items = response.body().getData();
                    Log.d("", "Numbe " + items.size());
                    if (response.body().getData().size() == 0) {
                        Toast.makeText(getActivity(), "Try After Some Time", Toast.LENGTH_SHORT).show();
                        iv_notfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    } else {
                        recyclerView.setAdapter(new LoginSTD_Adapter(getActivity(), items));
                    }
                } catch (Exception e) {
                    iv_notfound.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<LoginExample> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "Try..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
