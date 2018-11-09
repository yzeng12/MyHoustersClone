package com.example.yzeng.myhoustersclone.homepage;

import com.example.yzeng.myhoustersclone.pojo.PropertyListPOJO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertyListResponse {

    @SerializedName("Property")
    private List<PropertyListPOJO> addPropertyResponse;

    public PropertyListResponse(List<PropertyListPOJO> addPropertyResponse) {
        this.addPropertyResponse = addPropertyResponse;
    }

    public List<PropertyListPOJO> getAddPropertyResponse() {
        return addPropertyResponse;
    }

    public void setAddPropertyResponse(List<PropertyListPOJO> addPropertyResponse) {
        this.addPropertyResponse = addPropertyResponse;
    }
}
