package com.example.yzeng.myhoustersclone.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yzeng.myhoustersclone.homepage.HomePageActivity;
import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;
import com.example.yzeng.myhoustersclone.signup.SignupActivity;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;
import com.example.yzeng.myhoustersclone.ui_and_other.RememberPrefrence;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter loginPresenter;
    TextView textViewSignup,textViewForgotPassword;
    EditText etEmail, etPassword;
    CheckBox mCbDisplayPassword, rememberMe;
    Button buttonLogin;
    RememberPrefrence rememberPreference;
    MySharedPrefences mySharedPrefences;

    private static final String TAG = "SplashActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginPresenter = new LoginPresenter(this);
        mySharedPrefences = new MySharedPrefences();
        rememberPreference = new RememberPrefrence();

        textViewSignup = findViewById(R.id.textViewSignup);
        textViewForgotPassword =findViewById(R.id.textViewForgotPassword);
        etEmail = findViewById(R.id.editTextLoginEmail);
        etPassword = findViewById(R.id.editTextLoginPassword);
        mCbDisplayPassword = findViewById(R.id.cbDisplayPassword);
        rememberMe = findViewById(R.id.checkBoxRemember);

        if (rememberPreference.getRememberOrNot(this)) {
            etEmail.setText(mySharedPrefences.getSharePreference(getApplicationContext()).getString("email", null));
            etPassword.setText(mySharedPrefences.getSharePreference(getApplicationContext()).getString("password", null));
            rememberMe.setChecked(true);
        }

        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rememberPreference.setRememberOrNot(LoginActivity.this, true);
                } else {
                    rememberPreference.setRememberOrNot(LoginActivity.this, false);
                }
            }
        });


        loginPresenter.initListener();

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signUp();
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.forgot();
            }
        });


        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login();
            }
        });
    }

    @Override
    public void initListenerConfirm() {
        mCbDisplayPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void signUpConfirm() {
        //TODO
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
        Toast.makeText(this, "to signup", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginConfirm() {


        ApiService apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<LoginReturnPOJO> call = apiService.getLoginReturn(etEmail.getText().toString(),etPassword.getText().toString());
        call.enqueue(new Callback<LoginReturnPOJO>() {
            @Override
            public void onResponse(Call<LoginReturnPOJO> call, Response<LoginReturnPOJO> response) {

                LoginReturnPOJO loginReturnPOJO = response.body();

                if(loginReturnPOJO != null) {
                    Log.i("MainActivity", "onResponse: " + loginReturnPOJO.toString());
                    mySharedPrefences.setUserInfo(LoginActivity.this, loginReturnPOJO.getUserid(), loginReturnPOJO.getUsertype(),
                            loginReturnPOJO.getUseremail(),loginReturnPOJO.getAppapikey(), etPassword.getText().toString());
                    //TODO
                    //login successfull   to main page
                            Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
                            startActivity(i);
                    Toast.makeText(LoginActivity.this, "Login Success" , Toast.LENGTH_SHORT).show();


                } else {
                    Log.i("MainActivity", "onResponse: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<LoginReturnPOJO> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void forgot() {
        //TODO
//        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
//        startActivity(i);
        Toast.makeText(this, "to forgot", Toast.LENGTH_SHORT).show();
    }

}
