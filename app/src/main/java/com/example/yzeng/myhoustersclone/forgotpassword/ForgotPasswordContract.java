package com.example.yzeng.myhoustersclone.forgotpassword;


public interface ForgotPasswordContract {
    
    interface Presenter{
        void initview();
        void sentRetrofitRequest();


    }
    
    interface View{

        void initview();

        void sentRetrofitRequest();
    }
}
