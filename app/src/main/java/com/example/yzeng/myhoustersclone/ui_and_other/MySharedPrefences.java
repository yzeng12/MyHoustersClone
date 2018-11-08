package com.example.yzeng.myhoustersclone.ui_and_other;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPrefences {

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("MySharedPreferenceFile", Context.MODE_PRIVATE);
    }
    public static void setUserInfo(Context context, String id, String usertype,
                                   String email, String apikey, String password){
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        editor.putString("id", id);
        editor.putString("usertype", usertype);
        editor.putString("email", email);
        editor.putString("appapikey", apikey);
        editor.putString("password", password);
        editor.commit();
    }


}
