package com.example.yzeng.myhoustersclone.tenant;

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
    public void initSpinner() {
        tenantFragment.initSpinnerConfirm();
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
}
