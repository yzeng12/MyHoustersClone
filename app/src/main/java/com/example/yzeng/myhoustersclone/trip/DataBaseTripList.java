package com.example.yzeng.myhoustersclone.trip;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Trip_Table")
public class DataBaseTripList {

    @PrimaryKey (autoGenerate=true)
    @NonNull
    @ColumnInfo(name = "ID")
    int id;
    @ColumnInfo(name = "Date")
    String date;
    @ColumnInfo(name = "Description")
    String description;
    @ColumnInfo(name = "Start Address")
    String start_Address;
    @ColumnInfo(name = "End Address")
    String end_Address;
    @ColumnInfo(name = "Round Trip")
    String round_Trip;
    @ColumnInfo(name = "Total Mile")
    String total_Mile;

    public DataBaseTripList(String date, String description, String start_Address, String end_Address, String round_Trip, String total_Mile) {
        this.date = date;
        this.description = description;
        this.start_Address = start_Address;
        this.end_Address = end_Address;
        this.round_Trip = round_Trip;
        this.total_Mile = total_Mile;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_Address() {
        return start_Address;
    }

    public void setStart_Address(String start_Address) {
        this.start_Address = start_Address;
    }

    public String getEnd_Address() {
        return end_Address;
    }

    public void setEnd_Address(String end_Address) {
        this.end_Address = end_Address;
    }

    public String getRound_Trip() {
        return round_Trip;
    }

    public void setRound_Trip(String round_Trip) {
        this.round_Trip = round_Trip;
    }

    public String getTotal_Mile() {
        return total_Mile;
    }

    public void setTotal_Mile(String total_Mile) {
        this.total_Mile = total_Mile;
    }

    @Override
    public String toString() {
        return "Trip" +
                "id=" + id +"\n"+
                "date: " + date + '\n' +
                "description: " + description + '\n' +
                "start_Address: " + start_Address + '\n' +
                "end_Address: " + end_Address + '\n' +
                "round_Trip: " + round_Trip + '\n' +
                "total_Mile: " + total_Mile + '\n' ;
    }
}
