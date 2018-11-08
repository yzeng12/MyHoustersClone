package com.example.yzeng.myhoustersclone.signup;

import android.content.Intent;
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
import com.example.yzeng.myhoustersclone.login.LoginActivity;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorFragment extends Fragment implements SignupInterface.FragmentView {

    Button buttonSignUp;
    SignupPresenter presenter;
    private static final String TAG = "LandlordSignUpFragment";
    EditText editTextEmail, editTextPassword, editTextPassword2;
    boolean flag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiple_signup, container, false);
        presenter = new SignupPresenter(this);
        editTextEmail = view.findViewById(R.id.edit_email);
        editTextPassword = view.findViewById(R.id.edit_password);
        editTextPassword2 = view.findViewById(R.id.edit_password_confirm);
        buttonSignUp = view.findViewById(R.id.button_signup);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.checkPasswordMatch();
                if (!flag) {
                    return;
                }
                presenter.signup();
                Log.i(TAG, "1");
            }
        });

        return view;
    }

    @Override
    public void signupConfirm() {
        Log.i(TAG, "2");
        ApiService apiService = RetrofitInstance.getRetrofitStringInstance()
                .create(ApiService.class);
        Call<String> call = apiService.getSignUpReturn(editTextEmail.getText().toString(),
                "null@gmail.com", editTextPassword.getText().toString(), "Vendor" );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String message = response.body();
                Log.i(TAG, "success "+ message);
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), call.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, String.valueOf(t));
            }


        });
    }

    @Override
    public void checkPasswordMatchConfirm() {
        if (!editTextPassword.getText().toString().equals(editTextPassword2.getText().toString())) {
            Toast.makeText(getActivity(), "Password do not match, please input again",
                    Toast.LENGTH_SHORT).show();
            editTextPassword.setText("");
            editTextPassword2.setText("");
            flag = false;
        }
        else {
            flag = true;
        }
    }


}
