package com.example.yzeng.myhoustersclone.transaction;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Transaction_Table")
public class DataBaseTransaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    int id;
    @ColumnInfo(name = "Summary")
    String summary;
    @ColumnInfo(name = "Description")
    String description;
    @ColumnInfo(name = "Property")
    String property;
    @ColumnInfo(name = "Type")
    String type;
    @ColumnInfo(name = "Amount")
    String amount;
    @ColumnInfo(name = "Date")
    String date;

    public DataBaseTransaction(String summary, String description, String property, String type, String amount, String date) {

        this.summary = summary;
        this.description = description;
        this.property = property;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
