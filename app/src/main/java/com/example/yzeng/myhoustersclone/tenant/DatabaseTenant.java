package com.example.yzeng.myhoustersclone.tenant;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Tenant_Table")
public class DatabaseTenant {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "ID")
    int id;
    @ColumnInfo(name = "Name")
    String name;
    @ColumnInfo(name = "Email")
    String email;
    @ColumnInfo(name = "Address")
    String address;
    @ColumnInfo(name = "Mobile")
    String mobile;

    public DatabaseTenant(String name, String email, String address, String mobile) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobile = mobile;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
