package com.example.yzeng.myhoustersclone.trip;

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

public class TripActivity extends AppCompatActivity implements TripInterface.View{
    Toolbar toolbar;
    TripPresenter todoListPresenter;
    ImageView imageButtonAddTenant;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    RecyclerView rv;
    List<DataBaseTripList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        toolbar = findViewById(R.id.toolbar_tenant);
        toolbar.setTitle("Trip List");

        imageButtonAddTenant = findViewById(R.id.ib_add_tenant);

        todoListPresenter = new TripPresenter(this);
        todoListPresenter.initView();
        db = OurRoomDataBase.getDatabase(this);
        Dao = db.DatabaseDao();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Trip_content, new TripFragment())
                .commit();

        //todo display recycle view
    }

    @Override
    public void initViewConfirm() {
        setSupportActionBar(toolbar);
        imageButtonAddTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.Trip_content,
                        new AddTripFragment(), null)
                        .addToBackStack(null)
                        .commit();
                toolbar.setTitle("Add Trip");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toolbar.setTitle("Trip List");
    }
}
