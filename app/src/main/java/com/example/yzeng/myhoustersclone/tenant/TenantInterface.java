package com.example.yzeng.myhoustersclone.tenant;

public interface TenantInterface {
    
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
