package com.example.yzeng.myhoustersclone.tenant;

public class TenantPresenter implements TenantInterface.Presenter {

    TenantInterface.View view;
    AddTenantFragment addTenantFragment;

    public TenantPresenter(TenantsActivity tenantsActivity) {
        view = tenantsActivity;
    }

    public TenantPresenter(TenantInterface.FragmentView addTenantFragment) {
        this.addTenantFragment = (AddTenantFragment) addTenantFragment;
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void initSpinner() {
        addTenantFragment.initSpinnerConfirm();
    }

    @Override
    public void addTenant() {
        addTenantFragment.addTenantConfirm();
    }
}
