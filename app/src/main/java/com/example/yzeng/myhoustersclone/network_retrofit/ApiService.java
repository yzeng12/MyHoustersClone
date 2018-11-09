package com.example.yzeng.myhoustersclone.network_retrofit;

import com.example.yzeng.myhoustersclone.pojo.ForgotPasswordPOJO;

import com.example.yzeng.myhoustersclone.pojo.LoginReturnPOJO;
import com.example.yzeng.myhoustersclone.tenant.TenantContactResponse;
import com.example.yzeng.myhoustersclone.tenant.TenantPOJO;
import com.example.yzeng.myhoustersclone.tenant.TenantResponse;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
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

    @GET("/aamir/property-mgmt/pro_mgt_forgot_pass.php?")

    Call<ForgotPasswordPOJO> getForgotpassword(@Query("email") String email);

    @GET("/aamir/property-mgmt/pro_mgt_add_tenants.php?")
    Call<String> getAddTenantReturn(@Query("name") String name, @Query("email") String email, @Query("address") String address,
                                    @Query("mobile") String mobile, @Query("propertyid") String propertyid, @Query("landlordid") String landlordid);

    @GET("/aamir/property-mgmt/pro_mgt_tenent_details.php?")
    Call<TenantResponse> getTenantList(@Query("landlordid") String landlordid);

    @GET("/aamir/property-mgmt/pro_mgt_contact_tenants.php?")
    Call<TenantContactResponse> getTenantContact(@Query("name") String name, @Query("email") String email,
                                                 @Query("address") String address, @Query("mobile") String mobile);
}
