package com.example.yzeng.myhoustersclone.pojo;

import com.google.gson.annotations.SerializedName;

public class AddPropertyPOJO {

    @SerializedName("msg")
    private String message;

    public AddPropertyPOJO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
