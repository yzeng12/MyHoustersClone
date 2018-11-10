package com.example.yzeng.myhoustersclone.pojo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "PropertyTable")
public class PropertyTable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "propertyId")
    private String propertyId;
    @ColumnInfo(name = "propertyAddress")
    private String propertyAddress;
    @ColumnInfo(name = "propertyCity")
    private String propertyCity;
    @ColumnInfo(name = "propertyCountry")
    private String propertyCountry;

    public PropertyTable(@NonNull String propertyId, String propertyAddress, String propertyCity, String propertyCountry) {
        this.propertyId = propertyId;
        this.propertyAddress = propertyAddress;
        this.propertyCity = propertyCity;
        this.propertyCountry = propertyCountry;
    }

    @NonNull
    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(@NonNull String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyCity() {
        return propertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    public String getPropertyCountry() {
        return propertyCountry;
    }

    public void setPropertyCountry(String propertyCountry) {
        this.propertyCountry = propertyCountry;
    }
}
