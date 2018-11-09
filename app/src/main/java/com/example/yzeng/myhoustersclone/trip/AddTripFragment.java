package com.example.yzeng.myhoustersclone.trip;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTripFragment extends Fragment implements TripInterface.FragmentView {
    ImageView ivstart,ivend;
    TripPresenter tripPresenter;
    Button buttonadd;
    EditText et_start, et_end, et_property;
    String roundtrip,totalmile,date;
    TextView tv_date;
    public double lat,lon;
    CheckBox cb_round,cb_totalmile;
    View view;
    private int year, month, day;
    Geocoder geocoder;
    Location mLastLocation;
    public static int flag;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    FusedLocationProviderClient mFusedLocationProviderClient;

    public static List<Address> addresses;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    private static final String TAG = "AddDocumentFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_trip,
                container, false);
        tripPresenter = new TripPresenter(this);
        tripPresenter.initFragView();

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tripPresenter.addTriplist();
            }
        });

        return view;
    }

    @Override
    public void initFragViewConfirm() {
        db = OurRoomDataBase.getDatabase(getActivity());
        Dao = db.DatabaseDao();
        buttonadd = view.findViewById(R.id.btn_trip_ADD);
        et_property = view.findViewById(R.id.et_Trip_desc);

        et_start = view.findViewById(R.id.et_Trip_StartAddress);
        ivstart=view.findViewById(R.id.iv_startaddress);
        ivstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                tripPresenter.getlocation();

            }
        });
        et_end = view.findViewById(R.id.et_Trip_EndAddress);
        ivend=view.findViewById(R.id.iv_endaddress);
        ivend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=1;
                tripPresenter.getlocation();
//                et_end.setText(addresses.get(0).getFeatureName() + "\n" + addresses.get(0).getLocality() +"\n"+ addresses.get(0).getAdminArea()
//                        + "\n" + addresses.get(0).getCountryName());
            }
        });
        cb_round=view.findViewById(R.id.cb_roundtrip);
        cb_totalmile=view.findViewById(R.id.cb_total_miles);
        tv_date = (TextView) view.findViewById(R.id.tv_trip_date);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity()
                        , android.R.style.Theme_Holo_Dialog_MinWidth
                        , mDateSetListener
                        , year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                date = month + "/" + day + "/" + year;
                tv_date.setText(date);
            }
        };
    }

    @Override
    public void TakePicConfirm() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

    @Override
    public void addConfirm() {
        if(cb_round.isChecked()){
            roundtrip="Round Trip";
        }
        else {
            roundtrip="Single Trip";
        }
        if(cb_totalmile.isChecked()){
            totalmile="2469 Mile";
        }
        else {
            totalmile="Add to total mile refused";
        }
        DataBaseTripList tripList = new DataBaseTripList(
                 date,et_property.getText().toString(),et_start.getText().toString(),et_end.getText().toString(),roundtrip,totalmile
        );


        insert(tripList);
    }

    @Override
    public void getlocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();

            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mLastLocation = location;
                        lat = mLastLocation.getLatitude();
                        lon = mLastLocation.getLongitude();
                        Log.i(TAG, "onSuccess: "+lat+lon);
                        getaddress();
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }

    }

    private void getaddress() {
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            geocoder.getFromLocation(lat,lon,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
            Log.i(TAG, "getlocation: "+addresses.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if(flag==0){
        et_start.setText(addresses.get(0).getFeatureName() + "\n" + addresses.get(0).getLocality() +"\n"+ addresses.get(0).getAdminArea()
                        + "\n" + addresses.get(0).getCountryName());
        } else if (flag==1){
            et_end.setText(addresses.get(0).getFeatureName() + "\n" + addresses.get(0).getLocality() +"\n"+ addresses.get(0).getAdminArea()
                    + "\n" + addresses.get(0).getCountryName());
        }
    }

    public void insert(DataBaseTripList dataBaseTripList) {
        new insertAsyncTask(Dao).execute(dataBaseTripList);
        Toast.makeText(getActivity(), "Add Document Success", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Trip_content, new TripFragment()).
                addToBackStack(null)
                .commit();
    }

    private static class insertAsyncTask extends AsyncTask<DataBaseTripList, Void, Void> {

        private DataBaseDao mAsyncTaskDao;

        insertAsyncTask(DataBaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataBaseTripList... params) {
            mAsyncTaskDao.insertTriplist(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }


}
