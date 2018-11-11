package com.example.yzeng.myhoustersclone.tenant;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantContactFrament extends Fragment implements TenantInterface.FragmentView, AdapterView.OnItemSelectedListener {

    private static final String TAG = "TenantContactFrament";
    EditText editTextName, editTextEmail, editTextAddress, editTextMobile;
    Button buttonSendMes;
    Spinner spinner;
    List<DatabaseTenant> tenantList;
    private ArrayAdapter<String> adapter;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    DatabaseTenant databaseTenant;

    TenantPresenter tenantPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_contact, container, false);
/*        editTextName = view.findViewById(R.id.et_contact_name);
        editTextEmail = view.findViewById(R.id.et_contact_email);
        editTextAddress = view.findViewById(R.id.et_contact_address);
        editTextMobile = view.findViewById(R.id.et_contact_mobile);*/
        buttonSendMes = view.findViewById(R.id.bt_contact_tenant);
        spinner = view.findViewById(R.id.sp_tenant_select);

        db = OurRoomDataBase.getDatabase(getActivity());
        Dao = db.DatabaseDao();

        tenantPresenter = new TenantPresenter(this);

        tenantPresenter.initSpinner();

        buttonSendMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tenantPresenter.sendMessage();

            }
        });

        return view;
    }

    @Override
    public void initSpinnerConfirm() {
        tenantList = new ArrayList<>();

        getAsyncTask ga = new getAsyncTask(Dao);

        ga.execute(tenantList);
    }

    @Override
    public void initSpinnerConfirm(List<DatabaseTenant> aVoid) {
        tenantList = aVoid;

        List<String> tenantInfoList = new ArrayList<>();

        for (int i = 0; i < tenantList.size(); i++) {
            DatabaseTenant databaseTenant = tenantList.get(i);
            String str = databaseTenant.getName() + " " + databaseTenant.getEmail() + " "
                    + databaseTenant.getAddress() + " " + databaseTenant.getMobile();
            tenantInfoList.add(str);
        }

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                tenantInfoList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    private class getAsyncTask extends AsyncTask<List<DatabaseTenant>, Void, List<DatabaseTenant>> {

        private DataBaseDao mAsyncTaskDao;

        getAsyncTask(DataBaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<DatabaseTenant> doInBackground(List<DatabaseTenant>... voids) {

            tenantList =  mAsyncTaskDao.getAllTenant();

            if (tenantList.size() == 0) {
                Log.i(TAG, "shit");
            }
            else {
                Log.i(TAG, tenantList.get(0).getName());
            }

            return tenantList;
        }

        @Override
        protected void onPostExecute(List<DatabaseTenant> aVoid) {
            super.onPostExecute(aVoid);
            tenantPresenter.initSpinner(aVoid);
        }
    }

    @Override
    public void addTenantConfirm() {

    }

    @Override
    public void sendMessageConfirm() {
        ApiService apiService = RetrofitInstance.getRetrofitJsonInstance()
                .create(ApiService.class);
/*        Call<TenantContactResponse> call = apiService.getTenantContact(editTextName.getText().toString(),
                editTextEmail.getText().toString(), editTextAddress.getText().toString(), editTextMobile.getText().toString());*/

        Call<TenantContactResponse> call = apiService.getTenantContact(databaseTenant.getName(),
                databaseTenant.getEmail(), databaseTenant.getAddress(), databaseTenant.getMobile());

        call.enqueue(new Callback<TenantContactResponse>() {
            @Override
            public void onResponse(Call<TenantContactResponse> call, Response<TenantContactResponse> response) {

                Log.i(TAG, "success");

                TenantContactResponse tenantResponse = response.body();
                List<TenantContactPOJO> mList = tenantResponse.getTenantContactPOJOList();

                for (int i = 0; i < mList.size(); i++) {
                    Log.i(TAG, String.valueOf(mList.get(i)));
                }

                Toast.makeText(getContext(), "Message sent successfully!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TenantContactResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, String.valueOf(t));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        databaseTenant = tenantList.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        databaseTenant = tenantList.get(0);
    }

    @Override
    public void onResume() {
        super.onResume();
/*        if (tenantList.size() == 0) {
            Log.i(TAG, "shit");
        }
        else {
            Log.i(TAG, tenantList.get(0).getName());
        }*/
    }
}
