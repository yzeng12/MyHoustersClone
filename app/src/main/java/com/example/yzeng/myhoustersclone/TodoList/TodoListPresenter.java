package com.example.yzeng.myhoustersclone.TodoList;

public class TodoListPresenter implements TodoListInterface.Presenter {

    TodoListInterface.View view;
    AddTodolistFragment addTodolistFragment;
    TodoListFragment todoListFragment;

    public TodoListPresenter(TodoListActivity todoListActivity) {
        view = todoListActivity;
    }

    public TodoListPresenter(TodoListInterface.FragmentView addFragment) {
        this.addTodolistFragment = (AddTodolistFragment) addFragment;
    }
    public TodoListPresenter(TodoListInterface.ListFragmentView addListFragment) {
        this.todoListFragment = (TodoListFragment) addListFragment;
    }

    @Override
    public void initView() {
        view.initViewConfirm();
    }

    @Override
    public void initFragView() {
        addTodolistFragment.initFragViewConfirm();
    }
    @Override
    public void TakePic() {
        addTodolistFragment.TakePicConfirm();
    }
    @Override
    public void addDocument() {
        addTodolistFragment.addConfirm();
    }

    @Override
    public void rvadapter() {
        todoListFragment.rvadapterconfirm();
    }
@Override
    public void getDataFromDatabase() {
    todoListFragment.getdata();
    }
}
