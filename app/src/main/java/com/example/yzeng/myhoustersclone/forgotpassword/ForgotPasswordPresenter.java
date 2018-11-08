package com.example.yzeng.myhoustersclone.forgotpassword;



public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    ForgotPasswordContract.View view;

    public ForgotPasswordPresenter(ForgotPasswordActivity forgotPasswordActivity) {
        view = forgotPasswordActivity;
    }

    @Override
    public void initview() {
        view.initview();
    }
    @Override
    public void sentRetrofitRequest() {
        view.sentRetrofitRequest();
    }
}
