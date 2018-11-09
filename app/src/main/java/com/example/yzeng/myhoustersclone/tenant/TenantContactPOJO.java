package com.example.yzeng.myhoustersclone.tenant;

public class TenantContactPOJO {

    String id;
    String tenantsname;
    String tenantsaddress;
    String tenantsemal;
    String tenantsmobile;

    public TenantContactPOJO(String id, String tenantsname, String tenantsaddress, String tenantsemal, String tenantsmobile) {
        this.id = id;
        this.tenantsname = tenantsname;
        this.tenantsaddress = tenantsaddress;
        this.tenantsemal = tenantsemal;
        this.tenantsmobile = tenantsmobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantsname() {
        return tenantsname;
    }

    public void setTenantsname(String tenantsname) {
        this.tenantsname = tenantsname;
    }

    public String getTenantsaddress() {
        return tenantsaddress;
    }

    public void setTenantsaddress(String tenantsaddress) {
        this.tenantsaddress = tenantsaddress;
    }

    public String getTenantsemal() {
        return tenantsemal;
    }

    public void setTenantsemal(String tenantsemal) {
        this.tenantsemal = tenantsemal;
    }

    public String getTenantsmobile() {
        return tenantsmobile;
    }

    public void setTenantsmobile(String tenantsmobile) {
        this.tenantsmobile = tenantsmobile;
    }
}
