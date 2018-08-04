package com.skyzone18.ManMandirVidyalay.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.skyzone18.ManMandirVidyalay.Adapter.eventadapter;
import com.skyzone18.ManMandirVidyalay.R;
import com.skyzone18.ManMandirVidyalay.Rest.ApiService;
import com.skyzone18.ManMandirVidyalay.Rest.Datum;
import com.skyzone18.ManMandirVidyalay.Rest.Example;
import com.skyzone18.ManMandirVidyalay.Rest.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class EvantFragment extends Fragment {
        RecyclerView recyclerView;
        ArrayList<Datum> eventlist = new ArrayList<>();
        ApiService apiService;
        Context context;
        ImageView ivnotfound;
    public EvantFragment() {
        
    }


    public static EvantFragment newInstance(String param1, String param2) {
        EvantFragment fragment = new EvantFragment();
        Bundle args = new Bundle();
       
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_evant, container, false);
        ivnotfound = (ImageView) view.findViewById(R.id.iv_notfound);
        recyclerView = (RecyclerView)view.findViewById(R.id.today_recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiService = RetroClient.getClient().create(ApiService.class);
       
        Event();
        return view;
    }
    public  void  Event(){
        Call<Example> call = apiService.GetEvent();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, retrofit2.Response<Example> response) {
                List<Datum> items = response.body().getData();
                Log.d("","Numbe " + items.size());
                if (response.body().getData().size() == 0) {
                    ivnotfound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    recyclerView.setAdapter(new eventadapter(getContext(), items));
                }

            }

            @Override
            public void onFailure(Call<Example>call, Throwable t) {
                // Log error here since request failed
                Log.e(">>>>>>", t.toString());
                //  imageView.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
   
    public void onButtonPressed(Uri uri) {
      
    }

 

    @Override
    public void onDetach() {
        super.onDetach();
     
    }

  
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
