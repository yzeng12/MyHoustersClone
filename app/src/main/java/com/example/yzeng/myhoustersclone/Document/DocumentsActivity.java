package com.example.yzeng.myhoustersclone.Document;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.tenant.TenantPresenter;

import java.util.List;

public class DocumentsActivity extends AppCompatActivity implements DocumentInterface.View{
    Toolbar toolbar;
    DocumentPresenter documentPresenter;
    ImageButton imageButtonContact, imageButtonAddTenant;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    RecyclerView rv;
    List<DataBaseDocument> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);
        toolbar = findViewById(R.id.toolbar_tenant);
        imageButtonAddTenant = findViewById(R.id.ib_add_tenant);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Document_content, new DocumentListFragment()).
                addToBackStack(null)
                .commit();

        db = OurRoomDataBase.getDatabase(this);
        Dao = db.DatabaseDao();

        documentPresenter = new DocumentPresenter(this);
        documentPresenter.initView();
        //todo display recycle view
    }

    @Override
    public void initViewConfirm() {
        setSupportActionBar(toolbar);
        imageButtonAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Document_content,
                        new AddDocumentFragment(), null).addToBackStack(null).commit();
                toolbar.setTitle("Add Document");
            }
        });
    }

}
