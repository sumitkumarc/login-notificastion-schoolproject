package com.skyzone18.ManMandirVidyalay.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;

import java.util.ArrayList;


public class HolidayFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Datum> holidaylist = new ArrayList<>();
    ApiService apiService;
    ImageView ivnotfound;

    public HolidayFragment() {

    }


    public static HolidayFragment newInstance(String param1, String param2) {
        HolidayFragment fragment = new HolidayFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_holiday, container, false);
        ivnotfound = (ImageView) view.findViewById(R.id.iv_notfound);
        recyclerView = (RecyclerView)view.findViewById(R.id.holiday_recycle);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiService = RetroClient.getClient().create(ApiService.class);


    ///    Holiday();
        return view;
    }
//    public  void  Holiday(){
//        Call<Example> call = apiService.GetHoliday();
//        call.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
//
//                List<Datum> items = response.body().getData();
//                Log.d("","Numbe " + items.size());
//                if (response.body().getData().size() == 0) {
//                    recyclerView.setVisibility(View.GONE);
//                    ivnotfound.setVisibility(View.VISIBLE);
//                } else {
//                   // recyclerView.setAdapter(new holidayadapter(getContext(), (ArrayList<Datum>) items));
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Example>call, Throwable t) {
//                // Log error here since request failed
//                Log.e(">>>>>>", t.toString());
//                //  imageView.setVisibility(View.GONE);
//                Toast.makeText(getContext(), "Try Again", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    public void onDetach() {
        super.onDetach();

    }



}
