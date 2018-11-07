package com.example.yzeng.myhoustersclone.signup;

public interface SignupInterface {

    interface Presenter{
        void signup();

        void checkPasswordMatch();
    }

    interface View{}

    interface FragmentView{
        void signupConfirm();

        void checkPasswordMatchConfirm();
    }
}
