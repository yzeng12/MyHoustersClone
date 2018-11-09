package com.example.yzeng.myhoustersclone.transaction;

public class TransactionInterface {

    interface Presenter{
        void getDataFromDatabase();

        void initView();

        void rvadapter();

        void spinnerInit();

        void initDatabase();

        void TakePic();

        void addDocument();
    }

    interface View{
        void initViewConfirm();
    }

    interface FragmentView{
        void getData();

        void rvadapterconfirm();

        void spinnerInitConfirm();

        void initDatabaseConfirm();
    }

}
