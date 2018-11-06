package com.example.yzeng.myhoustersclone.login;


public interface LoginContract {
    
    interface Presenter{
        void initListener();

        void signUp();

        void login();
    }
    
    interface View{
        void initListenerConfirm();

        void signUpConfirm();

        void loginConfirm();

//        void loginSuccess(LoginProfile loginProfile);
//
 //       void loginFailureMessage(String message);

        void forgot();
    }
}
