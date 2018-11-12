package com.example.yzeng.myhoustersclone.Document;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yzeng.myhoustersclone.DataBase.DataBaseDao;
import com.example.yzeng.myhoustersclone.DataBase.OurRoomDataBase;
import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.network_retrofit.ApiService;
import com.example.yzeng.myhoustersclone.network_retrofit.RetrofitInstance;
import com.example.yzeng.myhoustersclone.tenant.TenantInterface;
import com.example.yzeng.myhoustersclone.tenant.TenantPresenter;
import com.example.yzeng.myhoustersclone.ui_and_other.MySharedPrefences;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDocumentFragment extends Fragment implements DocumentInterface.FragmentView {
    ImageView imageView;
    DocumentPresenter documentPresenter;
    Button buttonadd,buttonpic;
    EditText editTextName, editTextType;
    View view;
    private OurRoomDataBase db;
    private DataBaseDao Dao;
    private static final String TAG = "AddDocumentFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_document,
                container, false);
        documentPresenter = new DocumentPresenter(this);
        documentPresenter.initFragView();
        buttonpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentPresenter.TakePic();
            }
        });
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentPresenter.addDocument();
            }
        });

        return view;
    }

    @Override
    public void initFragViewConfirm() {
        buttonpic=view.findViewById(R.id.btn_Document_picture);
        buttonadd=view.findViewById(R.id.btn_Document_ADD);
        editTextName=view.findViewById(R.id.et_Document_name);
        editTextType=view.findViewById(R.id.et_Document_Type);
        imageView=view.findViewById(R.id.iv_Document_picture);
        db = OurRoomDataBase.getDatabase(getActivity());
        Dao = db.DatabaseDao();
    }

    @Override
    public void TakePicConfirm() {
        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    @Override
    public void addConfirm() {
        DataBaseDocument document=new DataBaseDocument(
                editTextName.getText().toString(),editTextType.getText().toString());


        insert(document);
    }
    public void insert(DataBaseDocument dataBaseDocument) {
        new insertAsyncTask(Dao).execute(dataBaseDocument);
        Toast.makeText(getActivity(), "Add Document Success", Toast.LENGTH_LONG).show();
        getActivity().onBackPressed();
    }
    private static class insertAsyncTask extends AsyncTask<DataBaseDocument, Void, Void> {

        private DataBaseDao mAsyncTaskDao;

        insertAsyncTask(DataBaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final DataBaseDocument... params) {
            mAsyncTaskDao.insertDocument(params[0]);
            return null;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "myImage" , "mydiscription");
//        String root = Environment.getExternalStorageDirectory().toString();
//        File myDir = new File(root);
//        myDir.mkdirs();
//        String fname = "Image-" + "imagename"+ ".jpg";
//        File file = new File(myDir, fname);
//        if (file.exists()) file.delete();
//        Log.i("LOAD", root + fname);
//        try {
//            FileOutputStream out = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        imageView.setImageBitmap(bitmap);

    }
}
