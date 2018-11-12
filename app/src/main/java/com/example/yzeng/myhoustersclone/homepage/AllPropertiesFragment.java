package com.example.yzeng.myhoustersclone.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;
import com.example.yzeng.myhoustersclone.pojo.PropertyListPOJO;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllPropertiesFragment extends Fragment {


    private List<PropertyListPOJO> myList;
    RecyclerView recyclerView;
    PropertyListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_properties, container, false);

        myList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerView_AllProperties);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ApiService apiService = RetrofitInstance.getRetrofitJsonInstance().create(ApiService.class);
        Call<PropertyListResponse> call = apiService.getAllProperties();

        call.enqueue(new Callback<PropertyListResponse>() {
            @Override
            public void onResponse(Call<PropertyListResponse> call, Response<PropertyListResponse> response) {

                PropertyListResponse propertyListResponse = response.body();
                myList = propertyListResponse.getAddPropertyResponse();


                adapter = new PropertyListAdapter(myList, getActivity());

                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<PropertyListResponse> call, Throwable t) {

            }
        });

        return view;
    }
}
