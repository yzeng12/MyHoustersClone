package com.example.yzeng.myhoustersclone.tenant;

import java.util.List;

public interface TenantInterface {
    
    interface View{
        void initViewConfirm();

        void initRVConfirm();

        void getTenantConfirm();

        void saveTenantConfirm(List<TenantPOJO> mList);
    }
    
    interface Presenter{
        void initView();

        void initSpinner();

        void addTenant();

        void initRV();

        void getTenant();

        void sendMessage();

        void saveTenant(List<TenantPOJO> mList);

        void initSpinner(List<DatabaseTenant> aVoid);
    }
    
    interface FragmentView{

        void initSpinnerConfirm();

        void addTenantConfirm();

        void sendMessageConfirm();

        void initSpinnerConfirm(List<DatabaseTenant> aVoid);
    }
}
