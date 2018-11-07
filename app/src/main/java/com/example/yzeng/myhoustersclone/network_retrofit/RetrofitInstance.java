package com.example.yzeng.myhoustersclone.network_retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static Retrofit retrofit2;
  // http://rjtmobile.com/aamir/property-mgmt/pro_mgt_forgot_pass.php?email=aa@aa.com         ..... full url
    public static final String BASE_URL = "http://rjtmobile.com";

     public static Retrofit getRetrofitInstance()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getRetrofitInstance2()
    {
        if(retrofit2 == null)
        {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}
