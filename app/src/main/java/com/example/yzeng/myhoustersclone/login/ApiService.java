package com.example.yzeng.myhoustersclone.login;

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

    @GET("http://rjtmobile.com/aamir/property-mgmt/pro_mgt_login.php?")

    Call<LoginReturnPOJO> getLoginReturn(@Query("email") String email, @Query("password") String password);   // for multiple use Body instead of query,  use Multi for image
}
