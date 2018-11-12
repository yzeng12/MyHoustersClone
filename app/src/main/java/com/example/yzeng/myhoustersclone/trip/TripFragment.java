package com.example.yzeng.myhoustersclone.trip;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;

import java.util.List;

public class TripFragment extends Fragment implements TripInterface.ListFragmentView{

    TripPresenter tripPresenter;
    ImageView imageButtonAddTenant;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    RecyclerView rv;
    List<DataBaseTripList> list;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trip_content,
                container, false);
        db = OurRoomDataBase.getDatabase(getActivity());

        Dao = db.DatabaseDao();
        rv=view.findViewById(R.id.rv_Trip);
        tripPresenter = new TripPresenter(this);


        tripPresenter.getDataFromDatabase();



        //todo display recycle view
        return view;
    }


    @Override
    public void rvadapterconfirm(List<DataBaseTripList> list) {
        TriplistAdapter adapter = new TriplistAdapter(getActivity(), list);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }

    @Override
    public void getdata() {
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
            list = mAsyncTaskDao.getAllTriplist();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tripPresenter.rvadapter(list);
        }
    }
}
