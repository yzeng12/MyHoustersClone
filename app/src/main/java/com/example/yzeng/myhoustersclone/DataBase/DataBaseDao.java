package com.example.yzeng.myhoustersclone.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.yzeng.myhoustersclone.Document.DataBaseDocument;

import java.util.List;

@Dao
public interface DataBaseDao {


    @Insert
    void insertDocument(DataBaseDocument document);

    @Query("SELECT * from Document ORDER BY ID ASC")
    List<DataBaseDocument> getAllDocument();

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