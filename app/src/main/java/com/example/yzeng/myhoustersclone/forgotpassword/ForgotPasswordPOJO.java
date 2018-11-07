package com.example.yzeng.myhoustersclone.forgotpassword;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordPOJO {
    @SerializedName("useremail")
    String email;
    @SerializedName("userpassword")
    String password;

    public ForgotPasswordPOJO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "ForgotPasswordPOJO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
