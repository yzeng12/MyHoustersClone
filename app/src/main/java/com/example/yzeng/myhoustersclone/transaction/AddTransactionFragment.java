package com.example.yzeng.myhoustersclone.transaction;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.TodoList.AddTodolistFragment;
import com.example.yzeng.myhoustersclone.TodoList.DataBaseTodoList;
import com.example.yzeng.myhoustersclone.TodoList.TodoListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddTransactionFragment extends Fragment implements TransactionInterface.FragmentView,
        AdapterView.OnItemSelectedListener{

    TransactionPresenter transactionPresenter;
    Spinner spinnerPaymentType;
    private ArrayAdapter<String> adapterPaymentType;
    private List<String> listPaymentType;
    Button buttonadd, buttonpic;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    TextView textViewDate;
    EditText editTextSummary, editTextDescription, editTextAmount;
    String property, date, paymentType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transaction, container, false);

        spinnerPaymentType = view.findViewById(R.id.sp_transaction_type);
        editTextSummary = view.findViewById(R.id.et_transaction_summary);
        editTextDescription = view.findViewById(R.id.et_transaction_description);
        editTextAmount = view.findViewById(R.id.et_transaction_amount);
        buttonadd=view.findViewById(R.id.btn_transaction_add);

        transactionPresenter = new TransactionPresenter(this);
        transactionPresenter.initDatabase();
        transactionPresenter.spinnerInit();

        textViewDate = view.findViewById(R.id.tv_transaction_date);
        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity()
                        , android.R.style.Theme_Holo_Dialog_MinWidth
                        , mDateSetListener
                        , year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                //dialog.getDatePicker().setMaxDate(System.currentTimeMillis() + 1000*60*60*24*100);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month += 1;
                date = month + "/" + day + "/" + year;
                textViewDate.setText(date);
            }
        };

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionPresenter.addTransaction();
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

        String[] array = {"Application fee", "Interest", "Security deposit", "Parking", "Rent"};
        listPaymentType = new ArrayList<>();
        listPaymentType.addAll(Arrays.asList(array));

        adapterPaymentType = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                listPaymentType);

        adapterPaymentType.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerPaymentType.setAdapter(adapterPaymentType);

        spinnerPaymentType.setOnItemSelectedListener(this);
    }

    @Override
    public void initDatabaseConfirm() {
        db = OurRoomDataBase.getDatabase(getActivity());
        Dao = db.DatabaseDao();
    }

    @Override
    public void addTransactionConfirm() {
        DataBaseTransaction dataBaseTransaction = new DataBaseTransaction(
                editTextSummary.getText().toString(), editTextDescription.getText().toString(),
                 "0", paymentType, editTextAmount.getText().toString(),
                editTextAmount.getText().toString());

        insert(dataBaseTransaction);
    }

    public void insert(DataBaseTransaction dataBaseTransaction) {
        new insertAsyncTask(Dao).execute(dataBaseTransaction);
        Toast.makeText(getActivity(), "Add Transaction Success", Toast.LENGTH_LONG).show();
/*        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.TodoList_content, new TodoListFragment()).
                addToBackStack(null)
                .commit();*/
    }

    private static class insertAsyncTask extends AsyncTask<DataBaseTransaction, Void, Void> {

        private DataBaseDao mAsyncTaskDao;

        insertAsyncTask(DataBaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataBaseTransaction... params) {
            mAsyncTaskDao.insertTransaction(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        paymentType = adapterPaymentType.getItem(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        paymentType = adapterPaymentType.getItem(0);
    }
}
