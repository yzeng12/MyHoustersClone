package com.example.yzeng.myhoustersclone.signup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public PageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LandlordSignUpFragment tab0 = new LandlordSignUpFragment();
                return tab0;
            case 1:
                PMSignUpFragment tab1 = new PMSignUpFragment();
                return tab1;
            case 2:
                TenantSignUpFragment tab2 = new TenantSignUpFragment();
                return tab2;
            case 3:
                VendorFragment tab3 = new VendorFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
