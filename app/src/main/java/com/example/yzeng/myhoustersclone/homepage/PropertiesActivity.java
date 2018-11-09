package com.example.yzeng.myhoustersclone.homepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;

public class PropertiesActivity extends AppCompatActivity {

    MySharedPrefences mySharedPrefences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);

        mySharedPrefences = new MySharedPrefences();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_PropertiesActivity,new PropertyListFragment()).commit();
    }
}
