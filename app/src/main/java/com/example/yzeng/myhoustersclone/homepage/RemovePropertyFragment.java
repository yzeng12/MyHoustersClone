package com.example.yzeng.myhoustersclone.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemovePropertyFragment extends Fragment {

    Button btn_remove;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remove_property,container,false);

        btn_remove = view.findViewById(R.id.btn_removeProperty);

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle b = getArguments();
                String pid = b.getString("pid");
                ApiService apiService = RetrofitInstance.getRetrofitJsonInstance().create(ApiService.class);
                Call<PropertyAddResponse> call = apiService.getRemoveMessage(pid);
                call.enqueue(new Callback<PropertyAddResponse>() {
                    @Override
                    public void onResponse(Call<PropertyAddResponse> call, Response<PropertyAddResponse> response) {

                        PropertyAddResponse propertyAddResponse = response.body();
                        String res =  propertyAddResponse.getAddPropertyResponse().get(0);
                        Log.i("checkirout", "remove : " + res);

                    }

                    @Override
                    public void onFailure(Call<PropertyAddResponse> call, Throwable t) {


                        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("checkirout", "oadd prop: " + call.request().url());

                    }
                });

            }
        });


        return view;
    }
}
