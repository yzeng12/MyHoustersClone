package com.example.yzeng.myhoustersclone.transaction;

public class TransactionPresenter implements TransactionInterface.Presenter{

    TransactionInterface.View view;
    TransactionInterface.FragmentView fragmentView;

    public TransactionPresenter(TransactionActivity transactionActivity) {
        view = transactionActivity;
    }

    public TransactionPresenter(TransactionInterface.FragmentView fragmentView) {
        this.fragmentView = fragmentView;
    }

    @Override
    public void getDataFromDatabase() {
        fragmentView.getData();
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void rvadapter() {
        fragmentView.rvadapterconfirm();
    }

    @Override
    public void spinnerInit() {
        fragmentView.spinnerInitConfirm();
    }

    @Override
    public void initDatabase() {
        fragmentView.initDatabaseConfirm();
    }

    @Override
    public void TakePic() {

    }

    @Override
    public void addTransaction() {
        fragmentView.addTransactionConfirm();
    }

}
