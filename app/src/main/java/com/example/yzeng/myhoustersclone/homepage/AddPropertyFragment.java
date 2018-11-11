package com.example.yzeng.myhoustersclone.homepage;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;
import com.example.yzeng.myhoustersclone.pojo.PropertyTable;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPropertyFragment extends Fragment {

    EditText et_address, et_city, et_state, et_mortgage, et_country, et_pro_status, et_purchasePrice, et_latitude, et_longitude;
    Button btn_AddPrperty_AP;
    MySharedPrefences mySharedPrefences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_property, container, false);



        mySharedPrefences = new MySharedPrefences();

        et_address = view.findViewById(R.id.et_address_AP);
        et_city = view.findViewById(R.id.et_city_AP);
        et_state = view.findViewById(R.id.et_state_AP);
        et_mortgage = view.findViewById(R.id.et_mortgage_AP);
        et_country = view.findViewById(R.id.et_country_AP);
        et_pro_status = view.findViewById(R.id.et_pro_status_AP);
        et_purchasePrice = view.findViewById(R.id.et_purchasePrice_AP);
        et_latitude = view.findViewById(R.id.et_latitude_AP);
        et_longitude = view.findViewById(R.id.et_longitude_AP);

        btn_AddPrperty_AP = view.findViewById(R.id.btn_AddProperty_AP);

        btn_AddPrperty_AP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address = et_address.getText().toString();
                String city = et_city.getText().toString();
                String state = et_state.getText().toString();
                String mortgage = et_mortgage.getText().toString();
                String country = et_country.getText().toString();
                String pro_status = et_pro_status.getText().toString();
                String purchasePrice = et_purchasePrice.getText().toString();
                String userId = mySharedPrefences.getSharePreference(getActivity()).getString("id", null);
                Log.i("MainActivity", "onClick: id: " + userId);
                String userType = mySharedPrefences.getSharePreference(getActivity()).getString("usertype", null);
                Log.i("MainActivity", "onClick: id: " + userType);
                String latitude = et_latitude.getText().toString();
                String longitude = et_longitude.getText().toString();


                if (!userType.equals("Landlord")) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Properties can be added only by Landlords");
                    builder.setCancelable(true);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            return;
                        }

                    });
                    builder.show();
                } else {
                    ApiService apiService = RetrofitInstance.getRetrofitJsonInstance().create(ApiService.class);
                    Call<PropertyAddResponse> call = apiService.getMessage(address, city, state, country, pro_status, purchasePrice, mortgage, userId, userType, latitude, longitude);
                    call.enqueue(new Callback<PropertyAddResponse>() {
                        @Override
                        public void onResponse(Call<PropertyAddResponse> call, Response<PropertyAddResponse> response) {

                            PropertyAddResponse propertyAddResponse = response.body();
                            String res =  propertyAddResponse.getAddPropertyResponse().get(0);
                            Log.i("checkirout", "onResponse: " + res);

                        }

                        @Override
                        public void onFailure(Call<PropertyAddResponse> call, Throwable t) {


                            Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i("checkirout", "oadd prop: " + call.request().url());

                        }
                    });

                   // PropertyTable propertyTable = new PropertyTable();
                   // insert(propertyTable);

                }
            }
        });


        return view;
    }

}
