package com.example.yzeng.myhoustersclone.signup;

public class SignupPresenter implements SignupInterface.Presenter{

    SignupInterface.FragmentView view;

    public SignupPresenter(SignupInterface.FragmentView view) {
        this.view = view;
    }

    @Override
    public void signup() {
        view.signupConfirm();
    }

    @Override
    public void checkPasswordMatch() {
        view.checkPasswordMatchConfirm();
    }
}
