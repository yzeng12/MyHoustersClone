package com.example.yzeng.myhoustersclone.homepage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PropertyAddResponse {

    @SerializedName("msg")
    private List<String> addPropertyResponse;

    public PropertyAddResponse(List<String> addPropertyResponse) {
        this.addPropertyResponse = addPropertyResponse;
    }

    public List<String> getAddPropertyResponse() {
        return addPropertyResponse;
    }

    public void setAddPropertyResponse(List<String> addPropertyResponse) {
        this.addPropertyResponse = addPropertyResponse;
    }

}
