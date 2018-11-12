package com.example.yzeng.myhoustersclone.TodoList;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddTodolistFragment extends Fragment implements TodoListInterface.FragmentView {
    ImageView imageView;
    TodoListPresenter todoListPresenter;
    Button buttonadd, buttonpic;
    EditText et_summary, et_description, et_property, et_vendor, et_est_cost, et_act_cost;
    Spinner sp_priority, sp_status;
    TextView tv_date;
    private ArrayAdapter<String> adapter,adapter2;
    View view;
    private int year, month, day;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private List<String> list_priority, list_status;
    String priority, status, date;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    private static final String TAG = "AddDocumentFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_todolist,
                container, false);
        todoListPresenter = new TodoListPresenter(this);
        todoListPresenter.initFragView();
        buttonpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoListPresenter.TakePic();
            }
        });
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoListPresenter.addTodolist();
            }
        });

        return view;
    }

    @Override
    public void initFragViewConfirm() {
        buttonpic = view.findViewById(R.id.btn_todolist_picture);
        buttonadd = view.findViewById(R.id.btn_todolist_ADD);
        et_summary = view.findViewById(R.id.et_Todo_summary);
        et_description = view.findViewById(R.id.et_Todo_description);
        et_act_cost = view.findViewById(R.id.et_Todo_actual_cost);
        et_est_cost = view.findViewById(R.id.et_Todo_estimate_cost);
        et_vendor = view.findViewById(R.id.et_Todo_Vendor);
        et_property = view.findViewById(R.id.et_Todo_property);
        sp_priority = view.findViewById(R.id.sp_Todo_Priority);
        sp_status = view.findViewById(R.id.sp_Todo_Status);
        imageView = view.findViewById(R.id.iv_todolist_picture);
        db = OurRoomDataBase.getDatabase(getActivity());
        Dao = db.DatabaseDao();

        String[] array = {"Very low", "Low", "Medium", "High", "Very High"};
        list_priority = new ArrayList<>();
        list_priority.addAll(Arrays.asList(array));

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                list_priority);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        sp_priority.setAdapter(adapter);

        sp_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               priority= adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               priority= adapter.getItem(2);
            }
        });

        String[] array2 = {"Pending", "In Progress", "Complete", "On Hold"};
        list_status = new ArrayList<>();
        list_status.addAll(Arrays.asList(array2));

        adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,
                list_status);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);

        sp_status.setAdapter(adapter2);

        sp_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status=adapter2.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                status= adapter2.getItem(0);
            }
        });

        tv_date = (TextView) view.findViewById(R.id.tv_todolist_date);
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
        DataBaseTodoList todoList = new DataBaseTodoList(
                priority,
                et_summary.getText().toString(),
                et_description.getText().toString(),
                et_property.getText().toString(),
                date,
                et_vendor.getText().toString(),
                et_est_cost.getText().toString(),
                et_act_cost.getText().toString(),
                status
        );


        insert(todoList);
    }

    public void insert(DataBaseTodoList dataBaseTodoList) {
        new insertAsyncTask(Dao).execute(dataBaseTodoList);
        Toast.makeText(getActivity(), "Add Document Success", Toast.LENGTH_LONG).show();
//        getActivity().getSupportFragmentManager()
////                .beginTransaction()
////                .replace(R.id.TodoList_content, new TodoListFragment())
////                .commit();
        getActivity().onBackPressed();
    }

    private static class insertAsyncTask extends AsyncTask<DataBaseTodoList, Void, Void> {

        private DataBaseDao mAsyncTaskDao;

        insertAsyncTask(DataBaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataBaseTodoList... params) {
            mAsyncTaskDao.insertTodolist(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, et_summary.getText().toString(), et_description.getText().toString());


        imageView.setImageBitmap(bitmap);

    }
}
