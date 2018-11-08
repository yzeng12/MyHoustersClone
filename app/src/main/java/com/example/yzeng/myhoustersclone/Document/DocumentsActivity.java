package com.example.yzeng.myhoustersclone.Document;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.tenant.TenantPresenter;

public class DocumentsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TenantPresenter tenantPresenter;
    ImageButton imageButtonContact, imageButtonAddTenant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);


        //todo display recycle view
    }
}
