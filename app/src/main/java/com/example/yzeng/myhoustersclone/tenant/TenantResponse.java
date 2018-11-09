package com.example.yzeng.myhoustersclone.tenant;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TenantResponse {

    @SerializedName("Tenants")
    private List<TenantPOJO> tenantPOJOList;

    public TenantResponse(List<TenantPOJO> tenantPOJOList) {
        this.tenantPOJOList = tenantPOJOList;
    }

    public List<TenantPOJO> getTenantPOJOList() {
        return tenantPOJOList;
    }

    public void setTenantPOJOList(List<TenantPOJO> tenantPOJOList) {
        this.tenantPOJOList = tenantPOJOList;
    }

    @Override
    public String toString() {
        return "TenantResponse{" +
                "tenantPOJOList=" + tenantPOJOList +
                '}';
    }
}
