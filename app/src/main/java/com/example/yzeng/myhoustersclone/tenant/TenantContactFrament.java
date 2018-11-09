package com.example.yzeng.myhoustersclone.tenant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantContactFrament extends Fragment implements TenantInterface.FragmentView {

    private static final String TAG = "TenantContactFrament";
    EditText editTextName, editTextEmail, editTextAddress, editTextMobile;
    Button buttonSendMes;

    TenantPresenter tenantPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tenant_contact, container, false);
        editTextName = view.findViewById(R.id.et_contact_name);
        editTextEmail = view.findViewById(R.id.et_contact_email);
        editTextAddress = view.findViewById(R.id.et_contact_address);
        editTextMobile = view.findViewById(R.id.et_contact_mobile);
        buttonSendMes = view.findViewById(R.id.bt_contact_tenant);

        tenantPresenter = new TenantPresenter(this);

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

    }

    @Override
    public void addTenantConfirm() {

    }

    @Override
    public void sendMessageConfirm() {
        ApiService apiService = RetrofitInstance.getRetrofitJsonInstance()
                .create(ApiService.class);
        Call<TenantContactResponse> call = apiService.getTenantContact(editTextName.getText().toString(),
                editTextEmail.getText().toString(), editTextAddress.getText().toString(), editTextMobile.getText().toString());

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
}
