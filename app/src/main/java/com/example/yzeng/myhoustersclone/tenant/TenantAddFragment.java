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
import com.example.yzeng.myhoustersclone.pojo.PropertyTable;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantAddFragment extends Fragment implements TenantInterface.FragmentView, AdapterView.OnItemSelectedListener {

    Spinner spinner;
    TenantPresenter tenantPresenter;
    Button buttonAddTenant;
    EditText editTextName, editTextEmail, editTextAddress, editTextMobile;
    private String propertyId, landlordId;

    private ArrayAdapter<String> adapter;
    private List<PropertyTable> propertyList;
    MySharedPrefences mySharedPrefences;

    private OurRoomDataBase db;
    private DataBaseDao Dao;

    private static final String TAG = "TenantAddFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tenant,
                container, false);

        tenantPresenter = new TenantPresenter(this);
        editTextName = view.findViewById(R.id.et_tenant_name);
        editTextEmail = view.findViewById(R.id.et_tenant_email);
        editTextAddress = view.findViewById(R.id.et_tenant_address);
        editTextMobile = view.findViewById(R.id.et_tenant_mobile);
        buttonAddTenant = view.findViewById(R.id.bt_add_tenant);
        spinner = view.findViewById(R.id.sp_tenant);

        db = OurRoomDataBase.getDatabase(getActivity());
        Dao = db.DatabaseDao();

        mySharedPrefences = new MySharedPrefences();

        tenantPresenter.initSpinner();

        buttonAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenantPresenter.addTenant();
            }
        });

        return view;
    }

    @Override
    public void initSpinnerConfirm() {

        propertyList = new ArrayList<>();

        getAsyncTask ga = new getAsyncTask(Dao);

        ga.execute();

/*        String[] array = {"a", "b", "c", "d", "e"};
        propertyList = new ArrayList<>();
        propertyList.addAll(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                propertyList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);*/
    }

    @Override
    public void initSpinnerAddPropertyConfirm(List<PropertyTable> propertyList) {
        this.propertyList = propertyList;

        List<String> propertyInfoList = new ArrayList<>();

        for (int i = 0; i < propertyList.size(); i++) {
            PropertyTable propertyTable = propertyList.get(i);
            String str = "id: " + propertyTable.getPropertyId() + ", " + "address: " + propertyTable.getPropertyAddress() + ", "
                    + "city: " + propertyTable.getPropertyCity() + ", " + "country: " + propertyTable.getPropertyCountry();
            propertyInfoList.add(str);
        }

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                propertyInfoList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

    }

    private class getAsyncTask extends AsyncTask<Void, Void, List<PropertyTable>> {

        private DataBaseDao mAsyncTaskDao;

        getAsyncTask(DataBaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<PropertyTable> doInBackground(Void... voids) {

            propertyList =  mAsyncTaskDao.getAllProperty();

            return propertyList;
        }

        @Override
        protected void onPostExecute(List<PropertyTable> propertyList) {
            super.onPostExecute(propertyList);
            tenantPresenter.initSpinnerAddProperty(propertyList);
        }
    }




    @Override
    public void addTenantConfirm() {

        landlordId = mySharedPrefences.getSharePreference(getContext()).getString("id", null);

        ApiService apiService = RetrofitInstance.getRetrofitStringInstance()
                .create(ApiService.class);
        Call<String> call = apiService.getAddTenantReturn(editTextName.getText().toString(),
                editTextEmail.getText().toString(), editTextAddress.getText().toString(), editTextMobile.getText().toString(),
                propertyId, landlordId);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String message = response.body();
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), call.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, String.valueOf(t));
            }
        });
    }

    @Override
    public void sendMessageConfirm() {

    }

    @Override
    public void initSpinnerConfirm(List<DatabaseTenant> aVoid) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        propertyId = propertyList.get(position).getPropertyId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        propertyId = propertyList.get(0).getPropertyId();
    }
}
