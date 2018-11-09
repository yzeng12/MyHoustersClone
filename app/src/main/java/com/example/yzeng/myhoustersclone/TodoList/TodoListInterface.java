package com.example.yzeng.myhoustersclone.TodoList;

import java.util.List;

public interface TodoListInterface {
    
    interface View{

        void initViewConfirm();
    }
    
    interface Presenter{
        void initView();

        void initFragView();

        void TakePic();

        void addTodolist();

        void rvadapter(List<DataBaseTodoList> list);

        void getDataFromDatabase();
    }
    
    interface FragmentView{

        void initFragViewConfirm();

        void TakePicConfirm();

        void addConfirm();

    }

     interface ListFragmentView {

         void rvadapterconfirm(List<DataBaseTodoList> list);
         void getdata();
     }
}
