package com.example.yzeng.myhoustersclone.transaction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTransactionFragment extends Fragment implements TransactionInterface.FragmentView {

    TransactionPresenter transactionPresenter;
    Spinner spinnerPaymentType;
    private ArrayAdapter<String> adapterPaymentType;
    private List<String> listPaymentType;
    Button buttonadd, buttonpic;
    private OurRoomDataBase db;
    private DataBaseDao Dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        spinnerPaymentType = view.findViewById(R.id.sp_transaction_type);
        buttonpic=view.findViewById(R.id.btn_transaction_picture);
        buttonadd=view.findViewById(R.id.btn_transaction_add);

        transactionPresenter = new TransactionPresenter(this);
        transactionPresenter.initDatabase();
        transactionPresenter.spinnerInit();

        buttonpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionPresenter.TakePic();
            }
        });
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionPresenter.addDocument();
            }
        });


        return view;
    }

    @Override
    public void getData() {

    }

    @Override
    public void rvadapterconfirm() {

    }

    @Override
    public void spinnerInitConfirm() {

        String[] array = {"a", "b", "c", "d", "e"};
        listPaymentType = new ArrayList<>();
        listPaymentType.addAll(Arrays.asList(array));

        adapterPaymentType = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                listPaymentType);
        adapterPaymentType.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPaymentType.setAdapter(adapterPaymentType);

    }

    @Override
    public void initDatabaseConfirm() {
        db = OurRoomDataBase.getDatabase(getActivity());
        Dao = db.DatabaseDao();
    }
}
