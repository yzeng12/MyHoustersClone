package com.example.yzeng.myhoustersclone.network_retrofit;

import com.example.yzeng.myhoustersclone.login.LoginReturnPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

/*
 @GET("/photos")
        //if response is JsonArray
    Call<List<PhotoAlbum>> getPhotos();
    //if response is JsonObject
    //Call<PhotoAlbum> getPhotos();
    */

    @GET("/aamir/property-mgmt/pro_mgt_login.php?")

    Call<LoginReturnPOJO> getLoginReturn(@Query("email") String email, @Query("password") String password);   // for multiple use Body instead of query,  use Multi for image

    @GET("/aamir/property-mgmt/pro_mgt_reg.php?")
    Call<String> getSignUpReturn(@Query("email") String email, @Query("landlord_email") String landlord_email,
                                 @Query("password") String password, @Query("account_for") String account_for);
}