package com.example.yzeng.myhoustersclone.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.yzeng.myhoustersclone.Document.DataBaseDocument;
import com.example.yzeng.myhoustersclone.TodoList.DataBaseTodoList;
import com.example.yzeng.myhoustersclone.tenant.DatabaseTenant;
import com.example.yzeng.myhoustersclone.transaction.DataBaseTransaction;
import com.example.yzeng.myhoustersclone.pojo.PropertyTable;
import com.example.yzeng.myhoustersclone.pojo.PropertyTable;

import java.util.List;

@Dao
public interface DataBaseDao {

    @Insert
    void insertDocument(DataBaseDocument document);

    @Query("SELECT * from Document ORDER BY ID ASC")
    List<DataBaseDocument> getAllDocument();

    @Insert
    void insertTodolist(DataBaseTodoList todoList);

    @Query("SELECT * from TodoList_Table ORDER BY ID ASC")
    List<DataBaseTodoList> getAllTodolist();

    @Insert
    void insertTransaction(DataBaseTransaction dataBaseTransaction);

    @Query("SELECT * from Transaction_Table ORDER BY ID ASC")
    List<DataBaseTransaction> getAllTransaction();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTenant(DatabaseTenant databaseTenant);

    @Query("SELECT * from Tenant_Table ORDER BY ID ASC")
    List<DatabaseTenant> getAllTenant();



/*    @Delete("Drop table Transaction_Table")
    void delete();*/

/*
    @Insert
    void insert(DataBaseDocument todoNote);

    @Query("DELETE FROM DataBaseDocument")
    void deleteAll();

    @Query("SELECT * from DataBaseDocument ORDER BY question ASC")
    List<DataBaseDocument> getAllWords();

    @Query("SELECT question from DataBaseDocument ")
    //List<String> getQuestion();
    String[] getQuestion();*/

    @Insert
    void insertProperty(PropertyTable property);

    @Query("DELETE FROM PropertyTable")
    void delete();


}