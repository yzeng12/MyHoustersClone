package com.example.yzeng.myhoustersclone.homepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yzeng.myhoustersclone.R;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportFragmentManager().beginTransaction().replace(R.id.id_HomePageActivity, new HomePageFragment()).commit();
    }
}
