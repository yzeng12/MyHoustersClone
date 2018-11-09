package com.example.yzeng.myhoustersclone.tenant;

import com.google.gson.annotations.SerializedName;

public class TenantPOJO {

    String id;

    String tenantname;

    String tenantemail;

    String tenantaddress;

    String tenantmobile;

    String propertyid;

    String landlordid;

    public TenantPOJO(String id, String tenantname, String tenantemail,
                      String tenantaddress, String tenantmobile, String propertyid, String landlordid) {
        this.id = id;
        this.tenantname = tenantname;
        this.tenantemail = tenantemail;
        this.tenantaddress = tenantaddress;
        this.tenantmobile = tenantmobile;
        this.propertyid = propertyid;
        this.landlordid = landlordid;
    }

    @Override
    public String toString() {
        return "TenantPOJO{" +
                "id='" + id + '\'' +
                ", tenantname='" + tenantname + '\'' +
                ", tenantemail='" + tenantemail + '\'' +
                ", tenantaddress='" + tenantaddress + '\'' +
                ", tenantmobile='" + tenantmobile + '\'' +
                ", propertyid='" + propertyid + '\'' +
                ", landlordid='" + landlordid + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantname() {
        return tenantname;
    }

    public void setTenantname(String tenantname) {
        this.tenantname = tenantname;
    }

    public String getTenantemail() {
        return tenantemail;
    }

    public void setTenantemail(String tenantemail) {
        this.tenantemail = tenantemail;
    }

    public String getTenantaddress() {
        return tenantaddress;
    }

    public void setTenantaddress(String tenantaddress) {
        this.tenantaddress = tenantaddress;
    }

    public String getTenantmobile() {
        return tenantmobile;
    }

    public void setTenantmobile(String tenantmobile) {
        this.tenantmobile = tenantmobile;
    }

    public String getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid;
    }

    public String getLandlordid() {
        return landlordid;
    }

    public void setLandlordid(String landlordid) {
        this.landlordid = landlordid;
    }
}
