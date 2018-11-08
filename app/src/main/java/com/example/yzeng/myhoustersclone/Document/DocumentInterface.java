package com.example.yzeng.myhoustersclone.Document;

public interface DocumentInterface {
    
    interface View{
        void initViewConfirm();
    }
    
    interface Presenter{
        void initView();

        void initSpinner();

        void addTenant();
    }
    
    interface FragmentView{

        void initSpinnerConfirm();

        void addTenantConfirm();
    }
}
