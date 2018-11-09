package com.example.yzeng.myhoustersclone.Document;

import com.example.yzeng.myhoustersclone.tenant.AddTenantFragment;
import com.example.yzeng.myhoustersclone.tenant.TenantsActivity;

public class DocumentPresenter implements DocumentInterface.Presenter {

    DocumentInterface.View view;
    AddDocumentFragment addDocumentFragment;
    DocumentListFragment documentListFragment;

    public DocumentPresenter(DocumentsActivity documentsActivity) {
        view = documentsActivity;
    }

    public DocumentPresenter(DocumentInterface.FragmentView addFragment) {
        this.addDocumentFragment = (AddDocumentFragment) addFragment;
    }
    public DocumentPresenter(DocumentInterface.ListFragmentView addListFragment) {
        this.documentListFragment = (DocumentListFragment) addListFragment;
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void initFragView() {
        addDocumentFragment.initFragViewConfirm();
    }
    @Override
    public void TakePic() {
        addDocumentFragment.TakePicConfirm();
    }
    @Override
    public void addDocument() {
        addDocumentFragment.addConfirm();
    }

    @Override
    public void rvadapter() {
        documentListFragment.rvadapterconfirm();
    }
@Override
    public void getDataFromDatabase() {
    documentListFragment.getdata();
    }
}
