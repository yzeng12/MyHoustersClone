package com.example.yzeng.myhoustersclone.tenant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.yzeng.myhoustersclone.R;

public class TenantsActivity extends AppCompatActivity implements TenantInterface.View {

    Toolbar toolbar;
    TenantPresenter tenantPresenter;
    ImageButton imageButtonContact, imageButtonAddTenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenants);
        toolbar = findViewById(R.id.toolbar_tenant);
        imageButtonAddTenant = findViewById(R.id.ib_add_tenant);

        tenantPresenter = new TenantPresenter(this);

        tenantPresenter.initView();
    }

    @Override
    public void initViewConfirm() {
        setSupportActionBar(toolbar);
        imageButtonAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_tenant_home,
                        new AddTenantFragment(), null).addToBackStack(null).commit();
                toolbar.setTitle("Add Tenant");
            }
        });
    }
}
