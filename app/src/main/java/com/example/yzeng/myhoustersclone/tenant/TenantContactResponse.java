package com.example.yzeng.myhoustersclone.tenant;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TenantContactResponse {

    @SerializedName("Tenants")
    private List<TenantContactPOJO> tenantContactPOJOList;


    public TenantContactResponse(List<TenantContactPOJO> tenantPOJOList) {
        this.tenantContactPOJOList = tenantPOJOList;
    }

    public List<TenantContactPOJO> getTenantContactPOJOList() {
        return tenantContactPOJOList;
    }

    public void setTenantContactPOJOList(List<TenantContactPOJO> tenantContactPOJOList) {
        this.tenantContactPOJOList = tenantContactPOJOList;
    }

}
