package com.example.yzeng.myhoustersclone.TodoList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;

import java.util.List;

public class TodoListActivity extends AppCompatActivity implements TodoListInterface.View{
    Toolbar toolbar;
    TodoListPresenter todoListPresenter;
    ImageButton imageButtonContact, imageButtonAddTenant;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    RecyclerView rv;
    List<DataBaseTodoList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        toolbar = findViewById(R.id.toolbar_tenant);

        imageButtonAddTenant = findViewById(R.id.ib_add_tenant);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Document_content, new TodoListFragment()).
                addToBackStack(null)
                .commit();

        db = OurRoomDataBase.getDatabase(this);
        Dao = db.DatabaseDao();

        todoListPresenter = new TodoListPresenter(this);
        todoListPresenter.initView();
        //todo display recycle view
    }

    @Override
    public void initViewConfirm() {
        setSupportActionBar(toolbar);
        imageButtonAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Document_content,
                        new AddTodolistFragment(), null).addToBackStack(null).commit();
                toolbar.setTitle("Add TodoList");
            }
        });
    }

}
