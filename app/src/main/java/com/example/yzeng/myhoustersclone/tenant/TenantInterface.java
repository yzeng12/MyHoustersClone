package com.example.yzeng.myhoustersclone.tenant;

public interface TenantInterface {
    
    interface View{
        void initViewConfirm();

        void initRVConfirm();

        void getTenantConfirm();
    }
    
    interface Presenter{
        void initView();

        void initSpinner();

        void addTenant();

        void initRV();

        void getTenant();

        void sendMessage();
    }
    
    interface FragmentView{

        void initSpinnerConfirm();

        void addTenantConfirm();

        void sendMessageConfirm();
    }
}
