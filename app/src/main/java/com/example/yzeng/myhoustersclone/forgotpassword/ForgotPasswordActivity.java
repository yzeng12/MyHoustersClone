package com.example.yzeng.myhoustersclone.forgotpassword;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.login.LoginActivity;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;
import com.example.yzeng.myhoustersclone.pojo.ForgotPasswordPOJO;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordContract.View {
    ForgotPasswordPresenter forgotPasswordPresenter;
    EditText etEmail;
    Button buttonForgot;
    MySharedPrefences mySharedPrefences;

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotPasswordPresenter = new ForgotPasswordPresenter(this);
        forgotPasswordPresenter.initview();
        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPasswordPresenter.sentRetrofitRequest();
            }
        });
    }


    @Override
    public void initview() {
        etEmail = findViewById(R.id.editTextForgotpasswordEmail);
        buttonForgot = findViewById(R.id.buttonForgotPassword);
    }

    @Override
    public void sentRetrofitRequest() {
        ApiService apiService = RetrofitInstance.getRetrofitJsonInstance().create(ApiService.class);
        Call<ForgotPasswordPOJO> call = apiService.getForgotpassword(etEmail.getText().toString());
        call.enqueue(new Callback<ForgotPasswordPOJO>() {
            @Override
            public void onResponse(Call<ForgotPasswordPOJO> call, Response<ForgotPasswordPOJO> response) {

                ForgotPasswordPOJO forgotPasswordPOJO = response.body();

                if (forgotPasswordPOJO.getPassword() != null) {
                    Log.i("TransactionActivity", "onResponse: " + forgotPasswordPOJO.toString());
                    AlertDialog dialog = new AlertDialog.Builder(ForgotPasswordActivity.this).setMessage("Your Email: "+forgotPasswordPOJO.getEmail()+"\n"
                            +"Your Password: "+forgotPasswordPOJO.getPassword())
                            .setTitle("Your INFO").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                                    startActivity(i);
                                }
                            }).create();
                    dialog.setCancelable(false);
                    dialog.show();




                } else {
                    Log.i("TransactionActivity", "onResponse: " + response.body().toString());
                    Toast.makeText(ForgotPasswordActivity.this, "UserName or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordPOJO> call, Throwable t) {

                Toast.makeText(ForgotPasswordActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
