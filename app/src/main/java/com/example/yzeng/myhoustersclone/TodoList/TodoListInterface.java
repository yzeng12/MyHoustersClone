package com.example.yzeng.myhoustersclone.TodoList;

public interface TodoListInterface {
    
    interface View{

        void initViewConfirm();
    }
    
    interface Presenter{
        void initView();

        void initFragView();

        void TakePic();

        void addDocument();

        void rvadapter();

        void getDataFromDatabase();
    }
    
    interface FragmentView{

        void initFragViewConfirm();

        void TakePicConfirm();

        void addConfirm();

    }

     interface ListFragmentView {

         void rvadapterconfirm();
         void getdata();
     }
}
