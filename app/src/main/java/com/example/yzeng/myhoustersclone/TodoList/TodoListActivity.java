package com.example.yzeng.myhoustersclone.TodoList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;

import java.util.List;

public class TodoListActivity extends AppCompatActivity implements TodoListInterface.View{
    Toolbar toolbar;
    TodoListPresenter todoListPresenter;
    ImageView imageButtonAddTenant;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    RecyclerView rv;
    List<DataBaseTodoList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        toolbar = findViewById(R.id.toolbar_tenant);
        toolbar.setTitle("Todo List");

        imageButtonAddTenant = findViewById(R.id.ib_add_tenant);

        todoListPresenter = new TodoListPresenter(this);
        todoListPresenter.initView();
        db = OurRoomDataBase.getDatabase(this);
        Dao = db.DatabaseDao();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.TodoList_content, new TodoListFragment())
                .commit();

        //todo display recycle view
    }

    @Override
    public void initViewConfirm() {
        setSupportActionBar(toolbar);
        imageButtonAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.TodoList_content,
                        new AddTodolistFragment(), null).addToBackStack(null).commit();
                toolbar.setTitle("Add TodoList");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toolbar.setTitle("Todo List");
    }
}
