package com.example.yzeng.myhoustersclone.network_retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofitJson;
    private static Retrofit retrofitString;
  // http://rjtmobile.com/aamir/property-mgmt/pro_mgt_forgot_pass.php?email=aa@aa.com         ..... full url
    public static final String BASE_URL = "http://rjtmobile.com";

     public static Retrofit getRetrofitJsonInstance()
    {
        if(retrofitJson == null)
        {
            retrofitJson = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitJson;
    }

    public static Retrofit getRetrofitStringInstance()
    {
        if(retrofitString == null)
        {
            retrofitString = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofitString;
    }
}
