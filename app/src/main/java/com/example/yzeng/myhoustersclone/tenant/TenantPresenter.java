package com.example.yzeng.myhoustersclone.tenant;

import com.example.yzeng.myhoustersclone.pojo.PropertyTable;

import java.util.List;

public class TenantPresenter implements TenantInterface.Presenter {

    TenantInterface.View view;
    TenantInterface.FragmentView tenantFragment;

    public TenantPresenter(TenantsActivity tenantsActivity) {
        view = tenantsActivity;
    }

    public TenantPresenter(TenantInterface.FragmentView tenantFragment) {
        this.tenantFragment = tenantFragment;
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void addTenant() {
        tenantFragment.addTenantConfirm();
    }

    @Override
    public void initRV() {
        view.initRVConfirm();
    }

    @Override
    public void getTenant() {
        view.getTenantConfirm();
    }

    @Override
    public void sendMessage() {
        tenantFragment.sendMessageConfirm();
    }

    @Override
    public void saveTenant(List<TenantPOJO> mList) {
        view.saveTenantConfirm(mList);
    }

    @Override
    public void initSpinner() {
        tenantFragment.initSpinnerConfirm();
    }

    @Override
    public void initSpinner(List<DatabaseTenant> aVoid) {
        tenantFragment.initSpinnerConfirm(aVoid);
    }

    @Override
    public void initSpinnerAddProperty(List<PropertyTable> propertyTables) {
        tenantFragment.initSpinnerAddPropertyConfirm(propertyTables);
    }
}
