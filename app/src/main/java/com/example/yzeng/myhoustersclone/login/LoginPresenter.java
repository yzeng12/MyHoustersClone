package com.example.yzeng.myhoustersclone.login;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;

    public LoginPresenter(LoginActivity loginActivity) {
        view = loginActivity;
    }

    @Override
    public void initListener() {
        view.initListenerConfirm();
    }

    @Override
    public void signUp() {
        view.signUpConfirm();
    }

    @Override
    public void login() {
        view.loginConfirm();
    }

    public void forgot() {

        view.forgot();
    }
}
