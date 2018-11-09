package com.example.yzeng.myhoustersclone.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.yzeng.myhoustersclone.Document.DataBaseDocument;
import com.example.yzeng.myhoustersclone.TodoList.DataBaseTodoList;
import com.example.yzeng.myhoustersclone.trip.DataBaseTripList;

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
    @Query("SELECT * from trip_table ORDER BY ID ASC")
    List<DataBaseTripList> getAllTriplist();
    @Insert
    void insertTriplist(DataBaseTripList Trip);

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


}