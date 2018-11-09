package com.example.yzeng.myhoustersclone.TodoList;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "TodoList_Table")
public class DataBaseTodoList {

    @PrimaryKey (autoGenerate=true)
    @NonNull
    @ColumnInfo(name = "ID")
    int id;
    @ColumnInfo(name = "priority")
    String priority;
    @ColumnInfo(name = "summary")
    String summary;
    @ColumnInfo(name = "description")
    String description;
    @ColumnInfo(name = "property")
    String property;
    @ColumnInfo(name = "duedate")
    String duedate;
    @ColumnInfo(name = "vendor")
    String vendor;
    @ColumnInfo(name = "estimatecost")
    String estimatecost;
    @ColumnInfo(name = "actualcost")
    String actualcost;
    @ColumnInfo(name = "status")
    String status;

    public DataBaseTodoList(@NonNull int id, String priority, String summary, String description, String property, String duedate, String vendor, String estimatecost, String actualcost, String status) {
        this.id = id;
        this.priority = priority;
        this.summary = summary;
        this.description = description;
        this.property = property;
        this.duedate = duedate;
        this.vendor = vendor;
        this.estimatecost = estimatecost;
        this.actualcost = actualcost;
        this.status = status;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getEstimatecost() {
        return estimatecost;
    }

    public void setEstimatecost(String estimatecost) {
        this.estimatecost = estimatecost;
    }

    public String getActualcost() {
        return actualcost;
    }

    public void setActualcost(String actualcost) {
        this.actualcost = actualcost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DataBaseTodoList{" +
                "id=" + id +
                ", priority='" + priority + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", property='" + property + '\'' +
                ", duedate='" + duedate + '\'' +
                ", vendor='" + vendor + '\'' +
                ", estimatecost='" + estimatecost + '\'' +
                ", actualcost='" + actualcost + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
