package com.example.yzeng.myhoustersclone.tenant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantsActivity extends AppCompatActivity implements TenantInterface.View {

    Toolbar toolbar;
    TenantPresenter tenantPresenter;
    ImageButton imageButtonContact, imageButtonAddTenant;
    RecyclerView recyclerView;
    TenantRVAdapter tenantRVAdapter;
    List<TenantPOJO> mList;
    FloatingActionButton fab;

    private static final String TAG = "TenantsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenants);
        toolbar = findViewById(R.id.toolbar_tenant);
        imageButtonAddTenant = findViewById(R.id.ib_add_tenant);

        recyclerView = findViewById(R.id.rv_tenant_home);
        mList = new ArrayList<>();


        tenantPresenter = new TenantPresenter(this);

        tenantPresenter.initView();

        tenantPresenter.initRV();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                fab.hide();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_tenant_home,
                        new TenantContactFrament(), null).addToBackStack(null).commit();
                recyclerView.setVisibility(View.GONE);
                toolbar.setTitle("Contact Tenant");

            }
        });
    }

    @Override
    public void initViewConfirm() {
        setSupportActionBar(toolbar);
        imageButtonAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.hide();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_tenant_home,
                        new TenantAddFragment(), null).addToBackStack(null).commit();
                recyclerView.setVisibility(View.GONE);
                toolbar.setTitle("Add Tenant");
            }
        });
    }

    @Override
    public void initRVConfirm() {

/*        getSupportFragmentManager().beginTransaction().replace(
                R.id.content_tenant_home, new TenantContactFrament(), null).addToBackStack(null).commit();*/

        tenantPresenter.getTenant();

    }

    @Override
    public void getTenantConfirm() {
        ApiService apiService = RetrofitInstance.getRetrofitJsonInstance()
                .create(ApiService.class);
        Call<TenantResponse> call = apiService.getTenantList("32");

        call.enqueue(new Callback<TenantResponse>() {
            @Override
            public void onResponse(Call<TenantResponse> call, Response<TenantResponse> response) {

                Log.i(TAG, "success");

                TenantResponse tenantResponse = response.body();
                mList = tenantResponse.getTenantPOJOList();

                Log.i(TAG, String.valueOf(tenantResponse));

                for (int i = 0; i < mList.size(); i++) {
                    Log.i("???",     String.valueOf(mList.get(i)));
                }

                tenantRVAdapter = new TenantRVAdapter(mList);

                recyclerView.setLayoutManager(new LinearLayoutManager(TenantsActivity.this));

                recyclerView.setAdapter(tenantRVAdapter);

            }

            @Override
            public void onFailure(Call<TenantResponse> call, Throwable t) {
                Toast.makeText(TenantsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, String.valueOf(t));
            }
        });

    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0 ){
            findViewById(R.id.rv_tenant_home).setVisibility(View.VISIBLE);
            fab.show();
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else {
            super.onBackPressed();
        }
        toolbar.setTitle("MyHousterClone");
    }
}
