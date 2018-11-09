package com.example.yzeng.myhoustersclone.trip;

import java.util.List;

public interface TripInterface {
    
    interface View{

        void initViewConfirm();
    }
    
    interface Presenter{
        void initView();

        void initFragView();

        void TakePic();

        void addTriplist();

        void rvadapter(List<DataBaseTripList> list);

        void getDataFromDatabase();

        void getlocation();
    }
    
    interface FragmentView{

        void initFragViewConfirm();

        void TakePicConfirm();

        void addConfirm();

        void getlocation();
    }

     interface ListFragmentView {

         void rvadapterconfirm(List<DataBaseTripList> list);
         void getdata();
     }
}
