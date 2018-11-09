package com.example.yzeng.myhoustersclone.pojo;

import com.google.gson.annotations.SerializedName;

public class PropertyListPOJO {

    @SerializedName("id")
    private String PropertyId;
    @SerializedName("propertyaddress")
    private String PropertyAddress;
    @SerializedName("propertycity")
    private String PropertyCity;
    @SerializedName("propertystate")
    private String PropertyState;
    @SerializedName("propertycountry")
    private String PropertyCountry;
    @SerializedName("propertystatus")
    private String PropertyStatus;
    @SerializedName("propertypurchaseprice")
    private String PropertyPurchasePrice;
    @SerializedName("propertymortageinfo")
    private String PropertyMortgageInfo;

    public PropertyListPOJO(String propertyId, String propertyAddress, String propertyCity, String propertyState, String propertyCountry, String propertyStatus, String propertyPurchasePrice, String propertyMortgageInfo) {
        PropertyId = propertyId;
        PropertyAddress = propertyAddress;
        PropertyCity = propertyCity;
        PropertyState = propertyState;
        PropertyCountry = propertyCountry;
        PropertyStatus = propertyStatus;
        PropertyPurchasePrice = propertyPurchasePrice;
        PropertyMortgageInfo = propertyMortgageInfo;
    }

    public String getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(String propertyId) {
        PropertyId = propertyId;
    }

    public String getPropertyAddress() {
        return PropertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        PropertyAddress = propertyAddress;
    }

    public String getPropertyCity() {
        return PropertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        PropertyCity = propertyCity;
    }

    public String getPropertyState() {
        return PropertyState;
    }

    public void setPropertyState(String propertyState) {
        PropertyState = propertyState;
    }

    public String getPropertyCountry() {
        return PropertyCountry;
    }

    public void setPropertyCountry(String propertyCountry) {
        PropertyCountry = propertyCountry;
    }

    public String getPropertyStatus() {
        return PropertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        PropertyStatus = propertyStatus;
    }

    public String getPropertyPurchasePrice() {
        return PropertyPurchasePrice;
    }

    public void setPropertyPurchasePrice(String propertyPurchasePrice) {
        PropertyPurchasePrice = propertyPurchasePrice;
    }

    public String getPropertyMortgageInfo() {
        return PropertyMortgageInfo;
    }

    public void setPropertyMortgageInfo(String propertyMortgageInfo) {
        PropertyMortgageInfo = propertyMortgageInfo;
    }
}
