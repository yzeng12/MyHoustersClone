package com.example.yzeng.myhoustersclone.transaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.Document.AddDocumentFragment;
import com.example.yzeng.myhoustersclone.Document.DataBaseDocument;
import com.example.yzeng.myhoustersclone.Document.DocumentInterface;
import com.example.yzeng.myhoustersclone.Document.DocumentListAdapter;
import com.example.yzeng.myhoustersclone.Document.DocumentListFragment;
import com.example.yzeng.myhoustersclone.Document.DocumentPresenter;
import com.example.yzeng.myhoustersclone.R;

import java.util.List;

public class TransactionListFragment extends Fragment implements TransactionInterface.FragmentView {

    TransactionPresenter transactionPresenter;
    ImageButton imageButtonAddTenant;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    RecyclerView rv;
    List<DataBaseDocument> list;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transaction_content,
                container, false);
        db = OurRoomDataBase.getDatabase(getActivity());
        rv=view.findViewById(R.id.rv_Transaction);
        Dao = db.DatabaseDao();
        transactionPresenter = new TransactionPresenter(this);

        transactionPresenter.getDataFromDatabase();

        //todo display recycle view
        return view;
    }


    @Override
    public void rvadapterconfirm() {
        DocumentListAdapter adapter = new DocumentListAdapter(getActivity(), list);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void spinnerInitConfirm() {

    }

    @Override
    public void initDatabaseConfirm() {

    }

    @Override
    public void getData() {
        getAsyncTask ee = new getAsyncTask(Dao);
        ee.execute();
    }

    private class getAsyncTask extends AsyncTask<Void, Void, Void> {

        private DataBaseDao mAsyncTaskDao;

        getAsyncTask(DataBaseDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            list =mAsyncTaskDao.getAllDocument();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            transactionPresenter.rvadapter();
        }
    }
}
