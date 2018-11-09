package com.example.yzeng.myhoustersclone.trip;

import java.util.List;

public class TripPresenter implements TripInterface.Presenter {

    TripInterface.View view;
    AddTripFragment addTripFragment;
    TripFragment tripFragment;

    public TripPresenter(TripActivity tripActivity) {
        view = tripActivity;
    }

    public TripPresenter(TripInterface.FragmentView addFragment) {
        this.addTripFragment = (AddTripFragment) addFragment;
    }
    public TripPresenter(TripInterface.ListFragmentView addListFragment) {
        this.tripFragment = (TripFragment) addListFragment;
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void initFragView() {
        addTripFragment.initFragViewConfirm();
    }
    @Override
    public void TakePic() {
        addTripFragment.TakePicConfirm();
    }
    @Override
    public void addTriplist() {
        addTripFragment.addConfirm();
    }

    @Override
    public void rvadapter(List<DataBaseTripList> list) {
        tripFragment.rvadapterconfirm(list);
    }
@Override
    public void getDataFromDatabase() {
    tripFragment.getdata();
    }
@Override
    public void getlocation() {
        addTripFragment.getlocation();
    }
}
