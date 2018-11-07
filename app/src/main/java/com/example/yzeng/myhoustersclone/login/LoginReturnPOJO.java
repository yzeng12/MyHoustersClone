package com.example.yzeng.myhoustersclone.login;

import com.google.gson.annotations.SerializedName;

public class LoginReturnPOJO {

    @SerializedName("userid")
    String userid;
    @SerializedName("usertype")
    String usertype;
    @SerializedName("useremail")
    String useremail;
    @SerializedName("appapikey")
    String appapikey;

    public LoginReturnPOJO(String userid, String usertype, String useremail, String appapikey) {
        this.userid = userid;
        this.usertype = usertype;
        this.useremail = useremail;
        this.appapikey = appapikey;
    }

    @Override
    public String toString() {
        return "LoginReturnPOJO{" +
                "userid='" + userid + '\'' +
                ", usertype='" + usertype + '\'' +
                ", useremail='" + useremail + '\'' +
                ", appapikey='" + appapikey + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }
}
