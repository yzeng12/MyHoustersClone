package com.example.yzeng.myhoustersclone.tenant;

import com.example.yzeng.myhoustersclone.pojo.PropertyTable;

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

        void initSpinnerAddProperty(List<PropertyTable> propertyTables);
    }
    
    interface FragmentView{

        void initSpinnerConfirm();

        void addTenantConfirm();

        void sendMessageConfirm();

        void initSpinnerConfirm(List<DatabaseTenant> aVoid);

        void initSpinnerAddPropertyConfirm(List<PropertyTable> propertyTables);
    }
}
