package com.example.yzeng.myhoustersclone.ui_and_other;

import android.content.Context;
import android.content.SharedPreferences;

public class RememberPrefrence {

    public static SharedPreferences getSharePreference(Context context){
        return context.getSharedPreferences( "RememberPreferenceFile", Context.MODE_PRIVATE );
    }

    public static void setRememberOrNot(Context context, boolean b) {
        SharedPreferences.Editor editor = getSharePreference(context).edit();
        if (b) {
            editor.putBoolean("checked", true);
        }
        else {
            editor.putBoolean("checked", false);
        }
        editor.commit();
    }



    public boolean getRememberOrNot(Context context) {
        return getSharePreference(context).getBoolean("checked", false);
    }
}
