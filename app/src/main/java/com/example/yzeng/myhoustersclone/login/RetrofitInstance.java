package com.example.yzeng.myhoustersclone.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
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
}
