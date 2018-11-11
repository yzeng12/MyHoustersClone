package com.example.yzeng.myhoustersclone.homepage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;
import com.example.yzeng.myhoustersclone.pojo.PropertyListPOJO;
import com.example.yzeng.myhoustersclone.pojo.PropertyTable;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PropertyListFragment extends Fragment {


    List<PropertyListPOJO> myList;
    Button btn_GoToAddProperty;
    MySharedPrefences mySharedPrefences;
    RecyclerView recyclerView;
    PropertyListAdapter adapter;

    private OurRoomDataBase db;
    DataBaseDao propertyDao;
    // private List<PropertyTable> myPropertyList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_properties, container, false);

        btn_GoToAddProperty = view.findViewById(R.id.btn_Add_Property);
        mySharedPrefences = new MySharedPrefences();

        myList = new ArrayList<>();

        //  myPropertyList = new ArrayList<>();


        recyclerView = view.findViewById(R.id.recyclerView_PropertyList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        String userId = mySharedPrefences.getSharePreference(getActivity()).getString("id", null);
        Log.i("MainActivity", "onClick: id: " + userId);
        String userType = mySharedPrefences.getSharePreference(getActivity()).getString("usertype", null);
        Log.i("MainActivity", "onClick: id: " + userType);

        ApiService apiService = RetrofitInstance.getRetrofitJsonInstance().create(ApiService.class);
        Call<PropertyListResponse> call = apiService.getPropertyDetails(userId, userType);

        call.enqueue(new Callback<PropertyListResponse>() {
            @Override
            public void onResponse(Call<PropertyListResponse> call, Response<PropertyListResponse> response) {

                PropertyListResponse propertyListResponse = response.body();
                myList = propertyListResponse.getAddPropertyResponse();

                //Log.i("checkirout", "onResponse: " + call.request().url());

                adapter = new PropertyListAdapter(myList, getActivity());
                adapter.setClickListener(new PropertyListAdapter.ClickListener() {
                    @Override
                    public void itemClicked(View view, int position) {

                        String pid = myList.get(position).getPropertyId();

                        RemovePropertyFragment removePropertyFragment = new RemovePropertyFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("pid", pid);
                        removePropertyFragment.setArguments(bundle);

                        getFragmentManager().beginTransaction().replace(R.id.id_PropertiesActivity, removePropertyFragment).addToBackStack("null").commit();
                    }
                });
                recyclerView.setAdapter(adapter);
                initializer();

            }


            @Override
            public void onFailure(Call<PropertyListResponse> call, Throwable t) {

            }
        });

        btn_GoToAddProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.id_PropertiesActivity, new AddPropertyFragment()).addToBackStack("null").commit();
            }
        });



        return view;


    }

    public void initializer() {
        db = OurRoomDataBase.getDatabase(getContext());
        propertyDao = db.DatabaseDao();
        for (int i = 0; i < myList.size(); i++) {
            Log.i("checkirout", "List ids:  " + myList.get(i).getPropertyId() + "\n");
            Log.i("checkirout", "List ids:  " + myList.get(i).getPropertyAddress() + "\n");

            PropertyTable propertyTable = new PropertyTable(myList.get(i).getPropertyId(), myList.get(i).getPropertyAddress(),
                    myList.get(i).getPropertyCity(), myList.get(i).getPropertyCountry());
            insert(propertyTable);
        }

    }

    public void insert(PropertyTable property) {
        new insertAsyncTask(propertyDao).execute(property);
    }

    public class insertAsyncTask extends AsyncTask<PropertyTable, Void, Void> {

        private DataBaseDao mAsyncTaskDao;

        public insertAsyncTask(DataBaseDao dao) {

            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(PropertyTable... propertyTables) {
            mAsyncTaskDao.insertProperty(propertyTables[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
