package com.example.yzeng.myhoustersclone.transaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.Document.AddDocumentFragment;
import com.example.yzeng.myhoustersclone.Document.DataBaseDocument;
import com.example.yzeng.myhoustersclone.Document.DocumentListFragment;
import com.example.yzeng.myhoustersclone.Document.DocumentPresenter;
import com.example.yzeng.myhoustersclone.R;

import java.util.List;

public class TransactionActivity extends AppCompatActivity implements TransactionInterface.View{

    Toolbar toolbar;
    TransactionPresenter transactionPresenter;
    ImageButton imageButtonAddTransaction;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    RecyclerView rv;
    List<DataBaseTransaction> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        toolbar = findViewById(R.id.toolbar_tenant);
        imageButtonAddTransaction = findViewById(R.id.ib_add_tenant);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.transaction_content, new TransactionListFragment())
                .commit();

        db = OurRoomDataBase.getDatabase(this);
        Dao = db.DatabaseDao();

        transactionPresenter = new TransactionPresenter(this);
        transactionPresenter.initView();
        //todo display recycle view
    }

    @Override
    public void initViewConfirm() {
        setSupportActionBar(toolbar);
        imageButtonAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.transaction_content,
                        new AddTransactionFragment(), null).addToBackStack(null).commit();
                toolbar.setTitle("Add Transaction");
            }
        });
    }

}
